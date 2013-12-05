#include "JniHelper.h"
#include "cocos2d.h"
#include "Constants.h"
#include <jni.h>

using namespace cocos2d;

extern "C"
{

jstring Java_org_cocos2dx_lib_Cocos2dxActivity_nativeExample(JNIEnv*  env, jobject thiz)
    {
		return env->NewStringUTF("Hello from C++");
    }
}
