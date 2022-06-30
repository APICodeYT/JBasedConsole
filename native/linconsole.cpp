#include "linconsole.h"

#include <termios.h>
#include <iostream>
#include <stdio.h>
#include <sys/ioctl.h>
#include <unistd.h>

std::string toStringSequence(JNIEnv* env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray)env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t)env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char*)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

jintArray toJavaIntArray(int l[], JNIEnv* env) {
    jintArray newArray = env->NewIntArray(2);
    env->SetIntArrayRegion(newArray, 0, 2, reinterpret_cast<jint*>(l));
    return newArray;
}

static struct termios old, current;

void initTermios(int echo) 
{
  tcgetattr(0, &old);
  current = old; 
  current.c_lflag &= ~ICANON;
  if (echo) {
      current.c_lflag |= ECHO;
  } else {
      current.c_lflag &= ~ECHO;
  }
  tcsetattr(0, TCSANOW, &current);
}

void resetTermios() 
{
  tcsetattr(0, TCSANOW, &old);
}

char getch_(int echo) 
{
  char ch;
  initTermios(echo);
  ch = getchar();
  resetTermios();
  return ch;
}

char getch() 
{
  return getch_(0);
}

JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_LinuxConsoleImpl_beep
  (JNIEnv *, jobject) {

      std::cout << "\a";

  }


JNIEXPORT jintArray JNICALL Java_net_apicode_jbasedconsole_platform_LinuxConsoleImpl_getSize0
  (JNIEnv * e, jobject) {
    
    struct winsize w;
	ioctl(STDOUT_FILENO, TIOCGWINSZ, &w);
	int i[] = { w.ws_col, w.ws_row };
	return toJavaIntArray(i, e);

  }


JNIEXPORT jchar JNICALL Java_net_apicode_jbasedconsole_platform_LinuxConsoleImpl_readCharacter0
  (JNIEnv *, jobject) {
    return getch();
  }


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_LinuxConsoleImpl_resetInputBuffer0
  (JNIEnv *, jobject) {
      ioctl(0, TCFLSH, 0);
  }


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_LinuxConsoleImpl_setTitle0
  (JNIEnv * e, jobject, jstring s) {
      std::string str = toStringSequence(e, s);
      std::cout << "\033]0;" << str << "\007";
  }


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_LinuxConsoleImpl_setCursorVisible0
  (JNIEnv *, jobject, jboolean state) {
      if(state) {
			printf("\e[?25h");
	  } else {
			printf("\e[?25l");
	  }
  }