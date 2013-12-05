#include "JniHelper.h"
#include "cocos2d.h"
#include "Constants.h"
#include <jni.h>

using namespace cocos2d;

extern "C"
{

void Java_com_toddperkins_moleitx_moleitx_nativeUnlockSkin(JNIEnv*  env, jobject thiz)
    {
		CCUserDefault::sharedUserDefault()->setBoolForKey(IAP_JHSKIN,true);
    }
}
