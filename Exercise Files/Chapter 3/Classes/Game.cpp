#include "Game.h"
#include "SimpleAudioEngine.h"
#include "Mole.h"
#include "Constants.h"
#include "HUD.h"
#include "Utils.h"
#include "Pause.h"
#include "MainMenu.h"
#include "GameOver.h"

using namespace cocos2d;
using namespace CocosDenshion;

CCScene* Game::scene()
{
    CCScene *sc = CCScene::create();
    sc->setTag(TAG_GAME_SCENE);
    Game *g = Game::create();
    sc->addChild(g, 0, TAG_GAME_LAYER);

    return sc;
}

bool Game::init()
{
	if ( !CCLayer::init() )
	{
		return false;
	}
	
	return true;
}

void Game::initializeGame()
{

}

void Game::tick(float dt)
{

}

void Game::ccTouchesBegan(cocos2d::CCSet *pTouches, cocos2d::CCEvent *pEvent)
{
    
}

void Game::showMole()
{

}

CCArray* Game::getMoles(bool isUp)
{
    return NULL;
}

void Game::onExit()
{

}

void Game::onEnterTransitionDidFinish()
{

}
