// Copyright (c) 2010-2011 Zipline Games, Inc. All Rights Reserved.
// http://getmoai.com

#ifndef	MOAIFACEBOOKANDROID_H
#define	MOAIFACEBOOKANDROID_H

#include <moai-core/headers.h>
#include <moai-android/JniUtils.h>

//================================================================//
// MOAIFacebookAndroid
//================================================================//
/**	@lua	MOAIFacebookAndroid
	@text	Wrapper for Facebook integration on Android devices.
			Facebook provides social integration for sharing on
			www.facebook.com. Exposed to Lua via MOAIFacebook on
			all mobile platforms.

	@const	DIALOG_DID_COMPLETE			Event code for a successfully completed Facebook dialog.
	@const	DIALOG_DID_NOT_COMPLETE		Event code for a failed (or canceled) Facebook dialog.
	@const	REQUEST_RESPONSE			Event code for graph request responses.
	@const	REQUEST_RESPONSE_FAILED		Event code for failed graph request responses.
	@const	SESSION_DID_LOGIN			Event code for a successfully completed Facebook login.
	@const	SESSION_DID_NOT_LOGIN		Event code for a failed (or canceled) Facebook login.
	@const	PERMISSIONS_DENIED			Event code for a failed get permissions.
	@const	PERMISSIONS_GRANTED			Event code for a seccessfully get permissions.
*/
class MOAIFacebookAndroid :
	public MOAIGlobalClass < MOAIFacebookAndroid, MOAIGlobalEventSource >,
	public JniUtils {
private:

	jclass		mJava_AppEventsConstants;
	jmethodID	mJava_DeclinedPermissions;
	jmethodID	mJava_GraphRequest;
	jmethodID	mJava_HasGranted;
	jmethodID	mJava_Init;
	jmethodID	mJava_LogEvent;
	jmethodID	mJava_LogPurchase;
	jmethodID	mJava_Login;
	jmethodID	mJava_Logout;
	jmethodID	mJava_PostToFeed;
	jmethodID	mJava_RefreshPermissions;
	jmethodID	mJava_RequestPublishPermissions;
	jmethodID	mJava_RequestReadPermissions;
	jmethodID	mJava_RestoreSession;
	jmethodID	mJava_SendRequest;
	jmethodID	mJava_SessionValid;

	//----------------------------------------------------------------//
    static int  _declinedPermissions        ( lua_State* L );
	static int	_graphRequest				( lua_State* L );
    static int  _hasGranted                 ( lua_State* L );
	static int	_init						( lua_State* L );
	static int	_logEvent					( lua_State* L );
    static int  _logPurchase                ( lua_State* L );
	static int	_login						( lua_State* L );
	static int	_logout						( lua_State* L );
	static int	_postToFeed					( lua_State* L );
    static int  _refreshPermissions         ( lua_State* L );
	static int	_restoreSession				( lua_State* L );
	static int	_requestPublishPermissions	( lua_State* L );
	static int	_requestReadPermissions		( lua_State* L );
	static int	_sendRequest				( lua_State* L );
	static int	_sessionValid				( lua_State* L );
	static int	_setListener 				( lua_State* L );

public:

	DECL_LUA_SINGLETON ( MOAIFacebookAndroid );

	enum {
		DIALOG_DID_COMPLETE,
		DIALOG_DID_NOT_COMPLETE,
		PERMISSIONS_DENIED,
		PERMISSIONS_GRANTED,
        REQUEST_RESPONSE,
		REQUEST_RESPONSE_FAILED,
		SESSION_DID_LOGIN,
		SESSION_DID_NOT_LOGIN,
		TOTAL_EVENTS,
	};

	enum {
        DIALOG_RESULT_SUCCESS,
        DIALOG_RESULT_CANCEL,
        DIALOG_RESULT_ERROR,
	};

	MOAILuaStrongRef		mListeners [ TOTAL_EVENTS ];

			MOAIFacebookAndroid		();
			~MOAIFacebookAndroid	();
	void	DialogDidNotComplete	();
	void	DialogDidComplete		();
	void	DialogDidComplete		( cc8* error );
    void    Logout                  ();
	void	PermissionsDenied		( cc8* error );
	void	PermissionsGranted		();
	void	ReceivedRequestResponse	( bool error, cc8* result, int callbackIdx );
	void	RegisterLuaClass		( MOAILuaState& state );
	void	SessionDidLogin			();
	void	SessionDidNotLogin		();
};

#endif