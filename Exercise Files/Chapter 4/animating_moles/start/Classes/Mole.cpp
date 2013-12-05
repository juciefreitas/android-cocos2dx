//
//  Mole.cpp
//  moleit-x
//
//  Created by Todd Perkins on 6/11/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#include "Mole.h"
#include "SimpleAudioEngine.h"
#include "Constants.h"
#include "Game.h"
#include <sstream>
#include <string>
#include "Utils.h"
#include "HUD.h"

using namespace std;
using namespace cocos2d;
using namespace CocosDenshion;

#define TAG_REPEAT_ANIM 6

bool Mole::init()
{
	if ( !CCSprite::init() )
	{
		return false;
	}
	upTime = 2.0f;
	isUp = false;
	this->setDisplayFrame(CCSpriteFrameCache::sharedSpriteFrameCache()->spriteFrameByName("a0001.png"));

	return true;
}

void Mole::start()
{

}

void Mole::startLoopAnimation()
{

}


void Mole::stop()
{

}

void Mole::reset()
{

}

void Mole::wasTapped()
{

}
bool Mole::getIsUp(){
    return isUp;
}

void Mole::stopEarly()
{

}
