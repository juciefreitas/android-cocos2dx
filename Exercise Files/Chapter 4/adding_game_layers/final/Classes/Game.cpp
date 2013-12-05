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
    HUD *h = HUD::create();
    sc->addChild(h,1,TAG_HUD);
    Pause *p = Pause::create();
    sc->addChild(p,1,TAG_PAUSE);
    GameOver *go = GameOver::create();
    sc->addChild(go,1,TAG_GAMEOVER);
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
	CCString *file = (Utils::getArtScaleFactor() > 1) ? ccs("moles_bg-hd.png") : ccs("moles_bg.png");
	CCSprite *bg = CCSprite::create(file->getCString());
	bg->setPosition(ccp(Utils::s().width/2,Utils::s().height/2));
	Utils::scaleSprite(bg);
	this->addChild(bg,0);
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
	moles->release();
	CCLayer::onExit();
}

void Game::onEnterTransitionDidFinish()
{
	this->setTouchEnabled(true);
	this->initializeGame();
}
