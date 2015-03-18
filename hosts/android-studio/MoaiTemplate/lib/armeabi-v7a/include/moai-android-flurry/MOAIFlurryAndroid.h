// Copyright (c) 2010-2011 Zipline Games, Inc. All Rights Reserved.
// http://getmoai.com

#ifndef MOAIFLURRYANDROID_H
#define MOAIFLURRYANDROID_H

#include <moai-core/headers.h>
#include <moai-android/JniUtils.h>

//================================================================//
// MOAIFlurryAndroid
//================================================================//
class MOAIFlurryAndroid :
	public MOAIGlobalClass < MOAIFlurryAndroid, MOAILuaObject >,
	public JniUtils {
private:

	//----------------------------------------------------------------//
	static int _logEvent			( lua_State* L );
	static int _logTimedEvent		( lua_State* L );
	static int _endTimedEvent		( lua_State* L );
	static int _setAppVersion		( lua_State* L );
	static int _startSession		( lua_State* L );

public:

	DECL_LUA_SINGLETON ( MOAIFlurryAndroid );

	//----------------------------------------------------------------//
			MOAIFlurryAndroid		();
			~MOAIFlurryAndroid		();
	void	RegisterLuaClass		( MOAILuaState& state );
};

#endif
