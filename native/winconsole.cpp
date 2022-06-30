#include "winconsole.h"

#include <conio.h>
#include <Windows.h>
#include <iostream>

const HDC hdc = GetDC(GetConsoleWindow());

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

JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_flash
(JNIEnv*, jobject) {
    HWND window = GetConsoleWindow();
    FlashWindow(window, true);
}


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_beep
(JNIEnv*, jobject) {
    std::cout << "\a";
}


JNIEXPORT jintArray JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_getSize0
(JNIEnv* env, jobject) {
    CONSOLE_SCREEN_BUFFER_INFO csbi;
    int columns, rows;
    GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &csbi);
    rows = csbi.srWindow.Bottom - csbi.srWindow.Top + 1;
    columns = csbi.srWindow.Right - csbi.srWindow.Left + 1;
    int i[] = { columns, rows };
    return toJavaIntArray(i, env);
}


JNIEXPORT jchar JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_readCharacter0
(JNIEnv*, jobject) {
    return _getch();
}


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_resetInputBuffer0
(JNIEnv*, jobject) {
    FlushConsoleInputBuffer(GetStdHandle(STD_INPUT_HANDLE));
}


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setTitle0
(JNIEnv* env, jobject, jstring s) {
    std::string str = toStringSequence(env, s);
    SetConsoleTitleA(str.c_str());
}


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setCursorVisible0
(JNIEnv*, jobject, jboolean b) {
    HANDLE consoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_CURSOR_INFO info;
    info.dwSize = 100;
    info.bVisible = b;
    SetConsoleCursorInfo(consoleHandle, &info);
}


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setConsoleVisible0
(JNIEnv*, jobject, jboolean state) {
    if (state) {
        ShowWindow(GetConsoleWindow(), SW_SHOW);
    }
    else {
        ShowWindow(GetConsoleWindow(), SW_HIDE);
    }
}

JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setScrollbarVisible0
(JNIEnv*, jobject, jboolean b) {
    if (b) {
        ShowScrollBar(GetConsoleWindow(), SB_VERT, 1);
    }
    else {
        ShowScrollBar(GetConsoleWindow(), SB_VERT, 0);
    }
}


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_setMode0
(JNIEnv*, jobject, jint mode) {
    SetConsoleMode(GetStdHandle(STD_OUTPUT_HANDLE), mode);
}


JNIEXPORT jboolean JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_isKeyPressed0
(JNIEnv*, jobject, jint keycode) {
    if (GetConsoleWindow() != GetActiveWindow()) return false;
    int state = GetKeyState(keycode);
    return state != 0 && state != 1;
}


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_drawPixels0
(JNIEnv *env, jobject, jintArray xArray, jintArray yArray, jintArray rArr, jintArray gArr, jintArray bArr) {

    jint* xCoords = env->GetIntArrayElements(xArray, NULL);
    jint* yCoords = env->GetIntArrayElements(yArray, NULL);
    jint* redColors = env->GetIntArrayElements(rArr, NULL);
    jint* blueColors = env->GetIntArrayElements(bArr, NULL);
    jint* greenColors = env->GetIntArrayElements(gArr, NULL);

    size_t length = env->GetArrayLength(xArray);
    for (int pixel = 0; pixel < length; pixel++) {
        jint x = xCoords[pixel];
        jint y = yCoords[pixel];
        jint r = redColors[pixel];
        jint g = greenColors[pixel];
        jint b = blueColors[pixel];

        COLORREF color = RGB(r, g, b);

        SetPixel(hdc, x, y, color);
    }
    ReleaseDC(GetConsoleWindow(), hdc);
    
}


JNIEXPORT void JNICALL Java_net_apicode_jbasedconsole_platform_WindowsConsoleImpl_removePixels0
(JNIEnv*, jobject) {
    DeleteDC(hdc);
}


