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
    score = 0;
    float padding = 10;
    
    scoreLabel = CCLabelTTF::create("0", CCString::createWithFormat("%s.ttf", FONT_MAIN)->getCString(),24 * Utils::getArtScaleFactor());
    scoreLabel->setAnchorPoint(ccp(0,1));
    scoreLabel->setScale(Utils::getScale());
    scoreLabel->setPosition(ccp(padding,Utils::s().height - padding));
    this->addChild(scoreLabel,1);

    return true;
}

void HUD::didScore()
{
	score++;
	scoreLabel->setString(CCString::createWithFormat("%d",score)->getCString());
}
void HUD::missedMole()
{

}

void HUD::onExit()
{

}
