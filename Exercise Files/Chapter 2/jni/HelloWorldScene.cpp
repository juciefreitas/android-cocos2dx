#include "HelloWorldScene.h"
#include "JniURL.h"
#include "MessageJni.h"

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

    CCString *file = (size.width > 480) ? CCString::create("moles_bg-hd.png") : CCString::create("moles_bg.png");

    CCSprite *bg = CCSprite::create(file->getCString());
    bg->setPosition(ccp(size.width/2,size.height/2));
    this->addChild(bg,-1);

    float rX = size.width / bg->getContentSize().width;
    float rY = size.height / bg->getContentSize().height;

    bg->setScaleX(rX);
    bg->setScaleY(rY);

    file = (size.width > 480) ? CCString::create("moles-hd.plist") : CCString::create("moles.plist");
    CCSpriteFrameCache::sharedSpriteFrameCache()->addSpriteFramesWithFile(file->getCString());

    mole = CCSprite::createWithSpriteFrameName("b0011.png");
    mole->setPosition(ccp(size.width/2,size.height/2));
    mole->setScale(rX);
    this->addChild(mole,1);

    this->setTouchEnabled(true);

    return true;
}

void HelloWorld::ccTouchesBegan(cocos2d::CCSet *pTouches, cocos2d::CCEvent *pEvent)
{
	mole->stopAllActions();

	CCArray *frames = CCArray::create();
	for(int i = 1; i <= 11; i++)
	{
		CCString *frame = CCString::createWithFormat("b%04d.png", i);
		frames->addObject(CCSpriteFrameCache::sharedSpriteFrameCache()->spriteFrameByName(frame->getCString()));
	}
	mole->runAction(CCAnimate::create(CCAnimation::create(frames,.15)));

    openURLJNI("http://www.toddperkins.com");
	//showMessageBoxJNI("test","test");

}

