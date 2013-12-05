#include "JniIAP.h"
#include "JniHelper.h"

#include <jni.h>

using namespace cocos2d;

extern "C"
{

    void buyItem(const char* sku)
    {
        JniMethodInfo t;
        if (JniHelper::getStaticMethodInfo(t, "com/toddperkins/moleitx/moleitx"
                        ,"buyItem"
                        ,"(Ljava/lang/String;)V"))
        {
            jstring StringArg1 = t.env->NewStringUTF(sku);
            t.env->CallStaticVoidMethod(t.classID,t.methodID, StringArg1);
        }

    }
}
