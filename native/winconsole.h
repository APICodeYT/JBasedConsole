/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class net_apicode_jbasedconsole_platform_WindowsConsoleImpl */

#ifndef _Included_net_apicode_jbasedconsole_platform_WindowsConsoleImpl
#define _Included_net_apicode_jbasedconsole_platform_WindowsConsoleImpl
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    flash
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_flash
  (JNIEnv *, jobject);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    beep
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_beep
  (JNIEnv *, jobject);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    getSize0
 * Signature: ()[I
 */
JNIEXPORT jintArray JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_getSize0
  (JNIEnv *, jobject);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    readCharacter0
 * Signature: ()C
 */
JNIEXPORT jchar JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_readCharacter0
  (JNIEnv *, jobject);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    resetInputBuffer0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_resetInputBuffer0
  (JNIEnv *, jobject);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    setTitle0
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setTitle0
  (JNIEnv *, jobject, jstring);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    setCursorVisible0
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setCursorVisible0
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    setConsoleVisible0
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setConsoleVisible0
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    setScrollbarVisible0
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setScrollbarVisible0
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    setMode0
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setMode0
  (JNIEnv *, jobject, jint);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    isKeyPressed0
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_isKeyPressed0
  (JNIEnv *, jobject, jint);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    drawPixels0
 * Signature: ([I[I[I[I[I)V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_drawPixels0
  (JNIEnv *, jobject, jintArray, jintArray, jintArray, jintArray, jintArray);

/*
 * Class:     net_apicode_jbasedconsole_platform_WindowsConsoleImpl
 * Method:    removePixels0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_removePixels0
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
