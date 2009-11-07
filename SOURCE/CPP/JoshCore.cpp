// JOSH - Java Output Shell Hues
// A better console output implemention
//
// By Yifan Lu
// 

#include <windows.h>
#include "com_yifanlu_Josh.h"

HANDLE pointerToHandle
  (UINT_PTR pointer)
{
	return (HANDLE)pointer;
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_ADDCONSOLEALIAS
  (JNIEnv *env, jclass jcls, jstring source, jstring target, jstring exename)
{
	/*
	const wchar_t * cSource = (wchar_t*) env->GetStringChars(source, NULL);
	const wchar_t * cTarget = (wchar_t*) env->GetStringChars(target, NULL);
	const wchar_t * cExename = (wchar_t*) env->GetStringChars(exename, NULL);

	AddConsoleAlias(cSource, cTarget, cExename);
	*/
}

JNIEXPORT jlong JNICALL Java_com_yifanlu_Josh_CREATECONSOLESCREENBUFFER
  (JNIEnv *env, jclass jcls, jboolean read, jboolean write, jint shared)
{
	long readWrite = 0x00000000L;
	if(read)
		readWrite |= GENERIC_READ;
	if(write)
		readWrite |= GENERIC_WRITE;

    HANDLE hNewScreenBuffer = CreateConsoleScreenBuffer(readWrite, shared, NULL, CONSOLE_TEXTMODE_BUFFER, NULL);
	UINT_PTR pointer = (UINT_PTR)hNewScreenBuffer;
	
	return pointer;
}


JNIEXPORT void JNICALL Java_com_yifanlu_Josh_FILLCONSOLEOUTPUTATTRIBUTE
  (JNIEnv *env, jclass jcls, jlong pointer, jint attribute, jint length, jint x, jint y)
{
    HANDLE hConsole = pointerToHandle(pointer);
	COORD location = { x , y };

	FillConsoleOutputAttribute(hConsole, attribute, length, location, NULL);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_FILLCONSOLEOUTPUTCHARACTER
  (JNIEnv *env, jclass jcls, jlong pointer, jchar character, jint length, jint x, jint y)
{
    HANDLE hConsole = pointerToHandle(pointer);
	COORD location = { x , y };

	FillConsoleOutputCharacter(hConsole, character, length, location, NULL);
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_GETCONSOLECURSORINFO
  (JNIEnv *env, jclass jcls, jlong pointer)
{
    HANDLE hConsole = pointerToHandle(pointer);
	CONSOLE_CURSOR_INFO cursor;

	GetConsoleCursorInfo(hConsole, &cursor);

	jintArray info = env->NewIntArray(2);

	int cInfo[2] = {cursor.dwSize, cursor.bVisible};

	env->SetIntArrayRegion(info, 0, 2, (jint *)cInfo);

	return info;
}

JNIEXPORT jint JNICALL Java_com_yifanlu_Josh_GETCONSOLEDISPLAYMODE
  (JNIEnv *env, jclass jcls, jlong pointer)
{
    HANDLE hConsole = pointerToHandle(pointer);
	DWORD mode = 0;
	GetConsoleDisplayMode(&mode);

	return (int)mode;
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_GETCONSOLEFONTSIZE
  (JNIEnv *env, jclass jcls, jlong pointer)
{
    HANDLE hConsole = pointerToHandle(pointer);
	CONSOLE_FONT_INFO font;
	GetCurrentConsoleFont(hConsole, TRUE, &font);
	COORD size = GetConsoleFontSize(hConsole, font.nFont);

	jintArray info = env->NewIntArray(2);
	int cInfo[2] = {size.X, size.Y};
	env->SetIntArrayRegion(info, 0, 2, (jint *)cInfo);

	return info;
}


JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_GETCONSOLEHISTORYINFO
  (JNIEnv *env, jclass jcls)
{
	CONSOLE_HISTORY_INFO history;
	GetConsoleHistoryInfo(&history);

	jintArray info = env->NewIntArray(4);
	int cInfo[4] = {history.cbSize, history.HistoryBufferSize, history.NumberOfHistoryBuffers, history.dwFlags};
	env->SetIntArrayRegion(info, 0, 4, (jint *)cInfo);

	return info;
}

JNIEXPORT jint JNICALL Java_com_yifanlu_Josh_GETCONSOLEMODE
  (JNIEnv *env, jclass jcls, jlong pointer)
{
    HANDLE hConsole = pointerToHandle(pointer);
	DWORD info = 0;
	GetConsoleMode(hConsole, &info);

	return (int)zinfo;
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_GETCONSOLESCREENBUFFERSIZE
  (JNIEnv *env, jclass jcls, jlong pointer)
{
    HANDLE hConsole = pointerToHandle(pointer);
	CONSOLE_SCREEN_BUFFER_INFO info;
	GetConsoleScreenBufferInfo(hConsole, &info);

	jintArray size = env->NewIntArray(2);

	int cSize[2] = {info.dwSize.X, info.dwSize.Y};

	env->SetIntArrayRegion(size, 0, 2, (jint *)cSize);

	return size;
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETTEXTATTRIBUTE
  (JNIEnv *env, jclass jcls, jlong pointer, jint foreground, jint background)
{
    HANDLE hConsole = pointerToHandle(pointer);

    SetConsoleTextAttribute(hConsole, foreground + background);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLETITLE
  (JNIEnv *env, jclass jcls, jstring title)
{
	const char *cTitle = env->GetStringUTFChars(title, 0);
	SetConsoleTitle(cTitle);
	env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_CLEARSCREEN
  (JNIEnv *env, jclass jcls, jlong pointer, jint background)
{
	COORD coordScreen = { 0, 0 }; 
	DWORD cCharsWritten; 
	CONSOLE_SCREEN_BUFFER_INFO csbi;
	DWORD dwConSize; 
    HANDLE hConsole = pointerToHandle(pointer);
	
	if(background > 0)
		SetConsoleTextAttribute(hConsole, background);

	// Gets the console resolution
	GetConsoleScreenBufferInfo(hConsole, &csbi); 
	dwConSize = csbi.dwSize.X * csbi.dwSize.Y;
	// Fill every cell with spaces
	FillConsoleOutputCharacter(hConsole, TEXT(' '), dwConSize, coordScreen, &cCharsWritten);
	// Fill the console attributes
	FillConsoleOutputAttribute(hConsole, csbi.wAttributes, dwConSize, coordScreen, &cCharsWritten);
	// Sets the cursor position back to the first row and column
	SetConsoleCursorPosition(hConsole, coordScreen);
}


JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLECURSORPOSITION
  (JNIEnv *env, jclass jcls, jlong pointer, jint x, jint y)
{
    HANDLE hConsole = pointerToHandle(pointer);
	COORD pos = {x, y};
	SetConsoleCursorPosition ( hConsole, pos );
}

JNIEXPORT jlong JNICALL Java_com_yifanlu_Josh_GETSTDHANDLE
  (JNIEnv *env, jclass jcls)
{
	HANDLE hConsole = GetStdHandle ( STD_OUTPUT_HANDLE );
	UINT_PTR pointer = (UINT_PTR)hConsole;

	return pointer;
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_WRITECONSOLE
  (JNIEnv *env, jclass jcls, jlong pointer, jstring output)
{
    HANDLE hConsole = pointerToHandle(pointer);
	const char *cOutput = env->GetStringUTFChars(output, 0);

	WriteConsole(hConsole, cOutput, sizeof(cOutput), NULL, NULL);
	env->ReleaseStringUTFChars(output, cOutput);
}