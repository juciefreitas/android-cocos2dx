#include "JniURL.h"
#include "JniHelper.h"

#include <jni.h>

using namespace cocos2d;

extern "C"
{

    void openURLJNI(const char* url)
    {
        JniMethodInfo t;
        if (JniHelper::getStaticMethodInfo(t, "org/cocos2dx/lib/Cocos2dxActivity"
                        ,"openURL"
                        ,"(Ljava/lang/String;)V"))
        {
            jstring StringArg1 = t.env->NewStringUTF(url);
            t.env->CallStaticVoidMethod(t.classID,t.methodID, StringArg1);
        }

    }
}
