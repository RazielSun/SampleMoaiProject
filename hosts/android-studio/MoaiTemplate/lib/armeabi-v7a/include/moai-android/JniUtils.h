// Copyright (c) 2010-2011 Zipline Games, Inc. All Rights Reserved.
// http://getmoai.com

#ifndef MOAIJNIUTILS_H
#define MOAIJNIUTILS_H

#include "moai-core/pch.h"
#include "moai-sim/pch.h"

#include <jni.h>

#include <moai-android/moaiext-jni.h>

#include <jansson.h>

#define MOAI_JAVA_LUA_SETUP(type,str)						\
	MOAILuaState state ( L );								\
	if ( !state.CheckParams ( 1, str, true )) return 0;		\
	type* self = &type::Get ();
	
//================================================================//
// JniUtils
//================================================================//
class JniUtils {
protected:

	jclass		mClass;
	//jobject		mActivity;

public:

	//----------------------------------------------------------------//
	jobject				BundleFromLua				( lua_State* L, int index );
	bool				CallStaticBooleanMethod 	( jmethodID method, ... );
	long				CallStaticLongMethod		( jmethodID method, ... );
	jobject				CallStaticObjectMethod		( jmethodID method, ... );
	void				CallStaticVoidMethod		( jmethodID method, ... );
	void				ClearException				();
	jobject				CreateObjectOfClass			();
	JNIEnv*				Env							();
	jclass				GetClass					( cc8* className );
	jclass				GetClassViaLoader			( cc8* className );
	cc8*				GetCString					( jstring jstr );
	jstring				GetJString					( cc8* cstr );
	jfieldID			GetStaticField				( cc8* fieldName );
	jfieldID			GetStaticField				( jclass clazz, cc8* fieldName );
	jstring				GetStaticObjectField		( jfieldID fieldId );
	jstring				GetStaticObjectField		( jclass clazz, jfieldID fieldId );
	jstring				GetStaticObjectField		( cc8* fieldName );
	jstring				GetStaticObjectField		( jclass clazz, cc8* fieldName );
	jmethodID			GetMethod					( cc8* methodName, cc8* methodSignature );
	jmethodID			GetMethod					( jclass clazz, cc8* methodName, cc8* methodSignature );
	jmethodID			GetStaticMethod				( cc8* methodName, cc8* methodSignature );
	jmethodID			GetStaticMethod				( jclass clazz, cc8* methodName, cc8* methodSignature );
	void				JsonArrayToLua				( lua_State* L, json_t* json );
	void				JsonObjectToLua				( lua_State* L, json_t* json );
	void				JsonToLua					( lua_State* L, json_t* json );
	jobject				HashMapFromLua				( lua_State* L, int index );
						JniUtils					();
						~JniUtils					();
	void 				RegisterStringConstant 		( lua_State* L, cc8* fieldKey, cc8* fieldName );
	void 				RegisterStringConstant 		( lua_State* L, jclass clazz, cc8* fieldKey, cc8* fieldName );
	void				ReleaseCString				( jstring jstr, cc8* cstr );
	bool				SetClass					( cc8* className );
	bool				SetClassViaLoader			( cc8* className );
	jobjectArray		StringArrayFromLua			( lua_State* L, int index );
};

#endif