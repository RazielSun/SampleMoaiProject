// Copyright (c) 2010-2011 Zipline Games, Inc. All Rights Reserved.
// http://getmoai.com

#include <ParticlePresets.h>

#include <stdlib.h>
#include <math.h>
#include <moai-sim/host_particles.h>
#include <moai-sim/MOAIParticle.h>

#define D2R	( M_PI / 180.0 )
#define R2D	( 180.0 / M_PI )

//----------------------------------------------------------------//
static void     _randVec        ( float& x, float& y, float minMag, float maxMag, float minAngle, float maxAngle );
static float    _rand           ( float min, float max );
static float    _easeExtraSharp ( float t );

//----------------------------------------------------------------//
static void     _miningInit     ( float* particle, float* registers, bool isResource );
static void     _miningRender   ( float* particle, float* registers, AKUParticleSprite* sprite, float t0, float t1, float term, bool isResource );

//----------------------------------------------------------------//
static void		_miningPropInit		( float* particle, float* registers );
static void		_miningPropRender 	( float* particle, float* registers, AKUParticleSprite* sprite, float t0, float t1, float term );
static void		_miningResInit		( float* particle, float* registers );
static void		_miningResRender 	( float* particle, float* registers, AKUParticleSprite* sprite, float t0, float t1, float term );


// Globals
static const float G = -500.0f;
static const float MIN_V0 = 100.0f;
static const float MAX_V0 = 120.0f;
static const float ANGLE_VAR = 15.0f;

// Registers
static const int INDEX = 0;
static const int GROUND = 1;
static const int ROT = 2;

//----------------------------------------------------------------//
float _rand ( float min, float max ) {
    float random = (( float ) rand ()) / ( float ) RAND_MAX;
    return min + random * ( max - min );
}

//----------------------------------------------------------------//
void _randVec ( float& x, float& y, float minMag, float maxMag, float minAngle, float maxAngle ) {

	float minA = M_PI_2 - D2R * minAngle;
	float maxA = M_PI_2 - D2R * maxAngle;
	float a = _rand ( minA, maxA );
	float r = _rand ( minMag, maxMag );
	x = r * cosf ( a );
	y = r * sinf ( a );
}

//----------------------------------------------------------------//
float _easeExtraSharp ( float t ) {
    
    t = t * t * t * t;
    t = t * t;
    t = t * t;
    return t;
}

//----------------------------------------------------------------//
void _miningInit ( float* particle, float* registers, bool isResource ) {
    
    float x = 0.0f;
	float y = 0.0f;
	float angle = particle [ MOAIParticle::PARTICLE_DX ];
	float angleVar = ANGLE_VAR;
    
    registers [ INDEX ] = particle [ MOAIParticle::PARTICLE_DY ];
    registers [ GROUND ] = particle [ MOAIParticle::PARTICLE_Y ];
    registers [ ROT ] = isResource ? _rand ( 0.0f, 360.0f ) : 0.0f;
    
	if ( fabs ( angle ) > 0.1f ) {
		angleVar *= 0.1f;
	}
    
	_randVec ( x, y, MIN_V0, MAX_V0, angle - angleVar, angle + angleVar );
	particle [ MOAIParticle::PARTICLE_DX ] = x;
	particle [ MOAIParticle::PARTICLE_DY ] = y;
}

//----------------------------------------------------------------//
void _miningRender ( float* particle, float* registers, AKUParticleSprite* sprite, float t0, float t1, float term, bool isResource ) {

    float y = particle [ MOAIParticle::PARTICLE_Y ];
    float dy = particle [ MOAIParticle::PARTICLE_DY ];
    float ground = registers [ GROUND ];
    
	if ( y <= ground ) {
		particle [ MOAIParticle::PARTICLE_DX ] = 0.0f;
		particle [ MOAIParticle::PARTICLE_DY ] = 0.0f;
		particle [ MOAIParticle::PARTICLE_Y ] = ground;
	}
    else {
        particle [ MOAIParticle::PARTICLE_DY ] = dy + G * term * ( t1 - t0 );
    }
    
	float fade = 1.0f - _easeExtraSharp ( t1 );
    
	sprite->mXLoc = particle [ MOAIParticle::PARTICLE_X ];
	sprite->mYLoc = particle [ MOAIParticle::PARTICLE_Y ];
    sprite->mXScl = isResource ? 0.7f : 1.0f;
    sprite->mYScl = isResource ? 0.7f : 1.0f;
    sprite->mZRot = registers [ ROT ];
    
	sprite->mRed	= fade;
	sprite->mGreen	= fade;
	sprite->mBlue	= fade;
	sprite->mAlpha	= fade;
    
	sprite->mGfxID = ( int ) registers [ INDEX ];
}

//----------------------------------------------------------------//
void _miningResInit ( float* particle, float* registers ) {
    _miningInit ( particle, registers, true );
}

//----------------------------------------------------------------//
void _miningResRender ( float* particle, float* registers, AKUParticleSprite* sprite, float t0, float t1, float term ) {
    _miningRender ( particle, registers, sprite, t0, t1, term, true );
}

//----------------------------------------------------------------//
void _miningPropInit ( float* particle, float* registers ) {
    _miningInit ( particle, registers, false );
}

//----------------------------------------------------------------//
void _miningPropRender ( float* particle, float* registers, AKUParticleSprite* sprite, float t0, float t1, float term ) {
    _miningRender ( particle, registers, sprite, t0, t1, term, false );
}


//================================================================//
// ParticlePresets
//================================================================//

//----------------------------------------------------------------//
void ParticlePresets () {
    
	AKUSetParticlePreset ( "ParticlePresets", "miningProp", _miningPropInit,    _miningPropRender, 3 );
    AKUSetParticlePreset ( "ParticlePresets", "miningRes",  _miningResInit,     _miningResRender,  3 );
}
