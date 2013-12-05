//
//  MainMenu.cpp
//  moleit-x
//
//  Created by Todd Perkins on 6/21/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#include "MainMenu.h"
#include "SimpleAudioEngine.h"
#include "GameButton.h"
#include "Game.h"
#include "Utils.h"
#include "Constants.h"

using namespace cocos2d;
using namespace CocosDenshion;

//USING_NS_CC;

CCScene* MainMenu::scene()
{
    CCScene *sc = CCScene::create();
    MainMenu *m = MainMenu::create();
    sc->addChild(m, 0);
    return sc;
}

bool MainMenu::init()
{
	if ( !CCLayer::init() )
	{
		return false;
	}
    
    s = CCDirector::sharedDirector()->getWinSize();
    SimpleAudioEngine::sharedEngine()->stopBackgroundMusic();
    SimpleAudioEngine::sharedEngine()->preloadBackgroundMusic("moles_bg.mp3");
    SimpleAudioEngine::sharedEngine()->preloadEffect(SOUND_SPLAT);
    CCString* file = (Utils::getArtScaleFactor() > 1) ? CCString::create("moles-hd.plist") : CCString::create("moles.plist");
    CCSpriteFrameCache::sharedSpriteFrameCache()->addSpriteFramesWithFile(file->getCString());
    
    file = (Utils::getArtScaleFactor() > 1) ? CCString::create("title-hd.png") : CCString::create("title.png");
    CCSprite *bg = CCSprite::create(file->getCString());
    bg->setPosition(ccp(s.width/2,s.height/2));
    Utils::scaleSprite(bg);
    this->addChild(bg,-1);

    CCMenuItemSprite *playButton = CCMenuItemSprite::create(GameButton::buttonWithText("PLAY!", true), NULL, this, menu_selector(MainMenu::playGame));
    CCMenu *menu = CCMenu::create(playButton,NULL);
    menu->setPosition(ccp(s.width/2,s.height/2 - s.height/3.5f));
    this->addChild(menu,2);

	return true;
}

void MainMenu::playGame()
{
    CCDirector::sharedDirector()->replaceScene(Game::scene());
}

void MainMenu::mainMenu()
{
    CCDirector::sharedDirector()->replaceScene(MainMenu::scene());
}
