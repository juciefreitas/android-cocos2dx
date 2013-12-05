//
//  HUD.cpp
//  moleit-x
//
//  Created by Todd Perkins on 6/26/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include "HUD.h"
#include "Utils.h"
#include "SimpleAudioEngine.h"
#include "Game.h"
#include "Mole.h"
#include "MainMenu.h"
#include "GameOver.h"
#include "Constants.h"

using namespace cocos2d;
using namespace CocosDenshion;

bool HUD::init()
{
    if (!CCLayer::init()) {
        return false;        
    }
    
    return true;
}

void HUD::didScore()
{

}
void HUD::missedMole()
{

}

void HUD::onExit()
{

}
