// JOSH - Java Output Shell Hues
// A better console output implemention
//
// By Yifan Lu
// 

#ifndef TARGETVER
#define TARGETVER 0x0600
#endif

#include "XP.cpp"

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_Josh_GETCONSOLEHISTORYINFO
  (JNIEnv *env, jclass jcls)
{
	CONSOLE_HISTORY_INFO history;
	GetConsoleHistoryInfo(&history);

	jintArray info = env->NewIntArray(4);
	int cInfo[3] = {history.HistoryBufferSize, history.NumberOfHistoryBuffers, history.dwFlags};
	env->SetIntArrayRegion(info, 0, 3, (jint *)cInfo);

	return info;
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_Josh_SETCONSOLEHISTORYINFO
  (JNIEnv *env, jclass jcls, jint bufferSize, jint numberOfBuffers, jint flags)
{
	CONSOLE_HISTORY_INFO info;
	info.cbSize = sizeof(CONSOLE_HISTORY_INFO);
	info.HistoryBufferSize = bufferSize;
	info.NumberOfHistoryBuffers = numberOfBuffers;
	info.dwFlags = flags;

	SetConsoleHistoryInfo(&info);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_Josh_SETCONSOLESCREENBUFFERINFOEX
  (JNIEnv *env, jclass jcls, jlong pointer, jint sizeX, jint sizeY, jint cursorX, jint cursorY, jint attributes, jint windowLeft, jint windowTop, jint windowRight, jint windowBottom, jint maxX, jint maxY)
{
    HANDLE hConsole = pointerToHandle(pointer);

	COORD dwSize = {sizeX, sizeY};
	COORD dwCursorPosition = {cursorX, cursorY};
	WORD wAttributes = attributes;
	SMALL_RECT srWindow = {windowLeft, windowTop, windowRight, windowBottom};
	COORD dwMaximumWindowSize = {maxX, maxY};

	CONSOLE_SCREEN_BUFFER_INFOEX bufferInfo;
	GetConsoleScreenBufferInfoEx(hConsole, &bufferInfo);

	bufferInfo.dwSize = dwSize;
	bufferInfo.dwCursorPosition = dwCursorPosition;
	bufferInfo.wAttributes = wAttributes;
	bufferInfo.srWindow = srWindow;
	bufferInfo.dwMaximumWindowSize = dwMaximumWindowSize;

	SetConsoleScreenBufferInfoEx(hConsole, &bufferInfo);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_Josh_SETCURRENTCONSOLEFONTEX
  (JNIEnv *env, jclass jcls, jlong pointer, jboolean maximum, jint font, jint sizeX, jint sizeY)
{
    HANDLE hConsole = pointerToHandle(pointer);
	CONSOLE_FONT_INFOEX extendedInfo;
	GetCurrentConsoleFontEx(hConsole, (maximum ? TRUE : FALSE), &extendedInfo);
	
	extendedInfo.nFont = font;
	extendedInfo.dwFontSize.X = sizeX;
	extendedInfo.dwFontSize.Y = sizeY;

	SetCurrentConsoleFontEx(hConsole, (maximum ? TRUE : FALSE), &extendedInfo);
}

JNIEXPORT jstring JNICALL Java_com_yifanlu_Josh_Josh_GETCONSOLEORGINIALTITLE
  (JNIEnv *env, jclass jcls)
{
	TCHAR title[1024];
	GetConsoleOriginalTitle(title, 1024);

	int len = strlen(title);
	jchar* unicodeChars = new jchar[len];
	for (int i=0; i<len; i++)
		unicodeChars[i] = title[i];
	jstring jTitle = env->NewString(unicodeChars, len);
	delete[] unicodeChars;

	return jTitle;
}