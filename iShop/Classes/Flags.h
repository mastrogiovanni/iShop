/*
 * Copyright 2010-2011, Thotpot Inc.
 *
 * Author: Michele Mastrogiovanni
 * Email: mmastrogiovanni@thotpot.com
 *
 * Author: Jacopo Mangiavacchi
 * Email: jacopo.mangiavacchi@thotpot.com
 *
 */

#define GAE_HOST		@"http://localhost/~mastrogiovannim"
// #define GAE_HOST		@"http://3.husbandshopper.appspot.com"

//------------------------------------------------------
// Connection parameter (tuning)
//------------------------------------------------------

// Number of time to retry for a single call if there are problems
#define GAE_RETRY_OPERATIONS			0

// Pause between two retries
#define GAE_PAUSE_BETWEEN_RETRY			1.5

// 0 means to timeout
#define GAE_TIMEOUT_OPERATION			20

//------------------------------------------------------
// Used for local development
//------------------------------------------------------

// Disable synchronizer
// #define DISABLE_SYNCHRONIZER

// When defined it will be used SHA1 on any operation with CommUser password on GAEConnector
#define ENCODE_PASSWORD

// Debug json messages
#define DEBUG_JSON

// #define DEBUG_OLD

// Bypass on the fly Michele's office proxy
#define FIXED_PROXY_USERNAME @"micmastr"
#define FIXED_PROXY_PASSWORD @"apriti80"

//------------------------------------------------------
// Facebook connection
//------------------------------------------------------

// In startup phase 
#define FACEBOOK_STARTUP_RESTART		5.0

// In connection phase
#define FACEBOOK_DEFAULT_CHECK			30.0

// In connection phase
#define SHORT_PAUSE						1.0

//------------------------------------------------------

// User proxy feature
// #define USE_ASI_PROXY

// Number of picture for contacts downloeaded in a round
#define CONTACT_PICTURES_PER_ROUND				1

// Short pause before next check (when user is not logged)
// #define SYNCHRONIZATION_ALL_FRIEND_TIME			60.0

// Short pause before retry registration of device token
#define SYNCHRONIZATION_DEVICE_TOKEN_REPETITION	60.0

// Short pause before next check (when user is not logged)
#define SYNCHRONIZATION_PAUSE					0.25

// Time interval between two synchronizations (this must be low)
#define SYNCHRONIZATION_INTERVAL				15.0

// Time before an update of an account: 5 minutes (300 seconds) 
// (this must be very high if user is updated: the only modification can be the picture)
#define USER_INFO_TIME_THRESHOLD				300.0

// Limit of users searched
#define USER_LIMIT_IN_SEARCH					10

// Size of an image in a pot
// 150.0  //480.0
#define SIZE_POT_IMAGE							150.0

// Size of an image in a pot
#define SIZE_BUDDY_IMAGE						64.0
