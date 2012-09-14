/*
 * Copyright 2010-2011, Thotpot Inc.
 *
 * Author: Michele Mastrogiovanni
 * Email: mmastrogiovanni@thotpot.com
 *
 */

#import <Foundation/Foundation.h>
#import "Flags.h"

/**
 * This class allows to send and get as answer a JSON object (no NSData).
 * The object in input and output can be complex: boolean, string, number, array,
 * dictionary...
 */
@interface TPGAEConnector : NSObject {

}

+ (id) asyncCallToMethod:(NSString*) method withData:(id) object;
+ (id) unreliableAsyncCallToMethod:(NSString*) method withData:(id) object;

@end
