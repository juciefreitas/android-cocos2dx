#include "HelloWorldScene.h"

USING_NS_CC;

CCScene* HelloWorld::scene()
{
    // 'scene' is an autorelease object
    CCScene *scene = CCScene::create();
    
    // 'layer' is an autorelease object
    HelloWorld *layer = HelloWorld::create();

    // add layer as a child to scene
    scene->addChild(layer);

    // return the scene
    return scene;
}

// on "init" you need to initialize your instance
bool HelloWorld::init()
{
    //////////////////////////////
    // 1. super init first
    if ( !CCLayer::init() )
    {
        return false;
    }
    // ask director the window size
    CCSize size = CCDirector::sharedDirector()->getWinSize();

    CCSprite *bg = CCSprite::create("moles_bg.png");
    bg->setPosition(ccp(size.width/2,size.height/2));
    this->addChild(bg,-1);

    float rX = size.width / bg->getContentSize().width;
    float rY = size.height / bg->getContentSize().height;

    bg->setScaleX(rX);
    bg->setScaleY(rY);

    mole = CCSprite::create("moles_icon.png");
    mole->setPosition(ccp(size.width/2,size.height/2));
    this->addChild(mole,1);

    this->setAccelerometerEnabled(true);

    return true;
}

void HelloWorld::didAccelerate(cocos2d::CCAcceleration *pAccelerationValue)
{
	CCSize s = CCDirector::sharedDirector()->getWinSize();
	float percentX = (pAccelerationValue->x + 1) / 2;
	float percentY = (pAccelerationValue->y + 1) / 2;

	//mole->setPosition(ccp(percentX * s.width, percentY * s.height));

	CCPoint p = mole->getPosition();
	mole->setPosition(ccpAdd(p,ccp(pAccelerationValue->x * 10,pAccelerationValue->y * 10)));
}

