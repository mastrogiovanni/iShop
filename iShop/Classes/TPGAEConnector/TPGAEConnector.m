/*
 * Copyright 2010-2011, Thotpot Inc.
 *
 * Author: Michele Mastrogiovanni
 * Email: mmastrogiovanni@thotpot.com
 *
 */

#import "TPGAEConnector.h"
// #import "CFStringEncodingExt.h"

#import "Flags.h"

#import "ErrorCodes.h"
#import "JSON.h"
// #import "NSString+Additions.h"

#ifdef USE_ASI_PROXY
#import "ASIHTTPRequest.h"
#endif

@implementation TPGAEConnector

+ (id) asyncCallToMethod:(NSString*) method withData:(id) object
{
	int count = GAE_RETRY_OPERATIONS;
	
	while ( count > 1 ) {
		
		@try {
            NSLog(@"---------------- CONNECT BEGIN ---------------");
			[[ UIApplication sharedApplication ] setNetworkActivityIndicatorVisible:YES ];
			id ret = [ TPGAEConnector unreliableAsyncCallToMethod:method withData:object ];
			[[ UIApplication sharedApplication ] setNetworkActivityIndicatorVisible:NO ];
            NSLog(@"---------------- CONNECT END ---------------");
			return ret;
		}
		@catch (NSException * e) {
			NSLog(@"Exception in method %@: RETRY", method);
			// NSLog(@"Exception: %@", e);
		}
		
		count --;
		
		[ NSThread sleepForTimeInterval:GAE_PAUSE_BETWEEN_RETRY ];
		
	}
	
	// NSLog(@"---------------- CONNECT BEGIN ---------------");
	[[ UIApplication sharedApplication ] setNetworkActivityIndicatorVisible:YES ];
	id ret = [ TPGAEConnector unreliableAsyncCallToMethod:method withData:object ];
	[[ UIApplication sharedApplication ] setNetworkActivityIndicatorVisible:NO ];
	// NSLog(@"---------------- CONNECT END ---------------");
	
	return ret;
	
}

/**
 * Call an async method with a data passed as parameter.
 * The parameter is transformed as a JSON fragment and delivered
 * to the host.
 * The result is parsed as a JSON fragment.
 * Result can be either a number less or equal than 0 (error result)
 * or a JSON object (number >= 0, string, dictionary, array...).
 */
+ (id) unreliableAsyncCallToMethod:(NSString*) method withData:(id) object
{
	// Get content for call as a JSON fragment
	NSString * content = [ object JSONFragment ];
	
#ifdef DEBUG_JSON
    NSLog(@"%@", [ NSString stringWithFormat:@"\tinvoke: %@ (%@)", method, content ]);

	
#endif
	
	NSUInteger encoding = NSASCIIStringEncoding;
    
	encoding = NSUTF8StringEncoding;
    // encoding = NSUnicodeStringEncoding;
	
	// encoding = NSUnicodeStringEncoding;
	// encoding = NSUTF16StringEncoding;
	// encoding = NSMacOSRomanStringEncoding;
	// encoding = NSISOLatin1StringEncoding;
	// encoding = NSISOLatin2StringEncoding;
	// encoding = CFStringConvertEncodingToNSStringEncoding(kCFStringEncodingISOLatin9);
    
	// Setup request content
	NSString * post = [ NSString stringWithFormat:@"%@\r\n", content ];
	
	// Transform post string in data
	NSData *postData = [ post dataUsingEncoding:encoding ];
	
	// Calculate body length
	NSString *postLength = [ NSString stringWithFormat:@"%d", [ postData length ]];

	// URL: host/serviceMethod
	NSURL * urlRequest = [ NSURL URLWithString:[ NSString stringWithFormat:@"%@/%@", GAE_HOST, method ]];
	
	NSLog(@"URL: %@", [ urlRequest description ]);

#ifndef USE_ASI_PROXY

	// Make a request
	NSMutableURLRequest *request = [[[ NSMutableURLRequest alloc ] init ] autorelease ];
		
	// [ request setTimeOutSeconds:GAE_TIMEOUT_OPERATION ];
	[ request setURL:urlRequest ];

	if ( object == nil ) {
        [ request setHTTPMethod:@"GET" ];
    }
    else {
        [ request setHTTPMethod:@"POST" ];
        [ request setValue:postLength forHTTPHeaderField:@"Content-Length"];
        [ request setValue:@"text/plain; charset=UTF-8" forHTTPHeaderField:@"Content-Type" ];
        [ request setHTTPBody:postData ];
    }
		
	// Get response and errors
	NSError *error = nil;
	// NSURLResponse *response;
	NSHTTPURLResponse * response;
	NSData * urlData = [ NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error ];
	
	if ( [ response statusCode ] != 200 ) {
		NSLog(@"Error: returned status code %d", [ response statusCode ]);
		if ( [ response statusCode ] == 407 )
			[ NSException raise:PROXY_ERROR format:@"\tClient is behind a proxy: authentication required" ];
		else
			[ NSException raise:NETWORK_ERROR format:@"\tProblems in connection with network. Please try again." ];
	}
	
	if ( error != nil ) {
		// Connection error
		NSLog(@"Error: %@", [ error description ]);
		[ NSException raise:NETWORK_ERROR format:@"\tProblems in connection with network. Please try again." ];
	}
	
	NSString * data = [[[ NSString alloc] initWithData:urlData encoding:NSASCIIStringEncoding ] autorelease ];
			
#else
	
	ASIHTTPRequest * request = [ ASIHTTPRequest  requestWithURL:urlRequest ];

	// FIXED: timeout for a single request
	[ request setTimeOutSeconds:GAE_TIMEOUT_OPERATION ];
	
	// [ request setShouldCompressRequestBody:NO ];
	
    if ( object != nil ) {
        [ request setRequestMethod:@"POST" ];
        [ request addRequestHeader:@"Content-Length" value:postLength ];
        [ request addRequestHeader:@"Content-Type" value:@"text/plain; charset=UTF-8" ];
        [ request setPostBody:[ NSMutableData dataWithData:postData ]];
    }
    else {
        [ request setRequestMethod:@"GET" ];
    }
	
	[ request setUseSessionPersistence:YES ];
	
	[ request startSynchronous ];
	
    // TODO handle all other status code. If 200 OK else errors.
	if ( [ request responseStatusCode ] != 200 )
		NSLog(@"\tResponse: %d", [ request responseStatusCode ]);
	
	if ( [ request responseStatusCode ] == 500 ) {
		NSLog(@"\tError: returned status code %d", [ request responseStatusCode ]);
		[ NSException raise:NETWORK_ERROR format:@"Problems in connection with network. Please try again." ];
	}
    
    // 503 is the response code that we will receive when GAE has finished all available resources.
	if ( [ request responseStatusCode ] == 503 ) {
	}
		
	NSError *error = [ request error ];
	if ( error != nil ) {
		NSLog(@"Error: %@", [ error description ]);
		[ NSException raise:NETWORK_ERROR format:@"Problems in connection with network. Please try again." ];
	}	
    
	NSString * data = [[[ NSString alloc] initWithData:[ request responseData ] encoding:NSUTF8StringEncoding ] autorelease ];

#endif
		
	@try {
		id ret = [ data JSONFragmentValue ];
		
#ifdef DEBUG_JSON
        NSLog(@"\t%@", [ NSString stringWithFormat:@"Response JSON: (class: %@) %@", [[ ret class ] description ], [ ret description ]]);
#endif
		
		return ret;
	}
	@catch (NSException * e) {
		[ NSException raise:JSON_PARING_ERROR format:@"Problems in reading the data coming from the service." ];
	}
	
	return nil;
}

@end
