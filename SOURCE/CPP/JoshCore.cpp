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
  (JNIEnv *env, jclass jcls)
{
	DWORD mode;
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
	int cInfo[3] = {history.HistoryBufferSize, history.NumberOfHistoryBuffers, history.dwFlags};
	env->SetIntArrayRegion(info, 0, 3, (jint *)cInfo);

	return info;
}

JNIEXPORT jint JNICALL Java_com_yifanlu_Josh_GETCONSOLEMODE
  (JNIEnv *env, jclass jcls, jlong pointer)
{
    HANDLE hConsole = pointerToHandle(pointer);
	DWORD info;
	GetConsoleMode(hConsole, &info);

	return (int)info;
}

JNIEXPORT jstring JNICALL Java_com_yifanlu_Josh_GETCONSOLEORGINIALTITLE
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

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_GETCONSOLESCREENBUFFERINFO
  (JNIEnv *env, jclass jcls, jlong pointer)
{
    HANDLE hConsole = pointerToHandle(pointer);
	CONSOLE_SCREEN_BUFFER_INFO info;
	GetConsoleScreenBufferInfo(hConsole, &info);

	jintArray jInfo = env->NewIntArray(11);

	int cInfo[11] = {info.dwSize.X, info.dwSize.Y, info.dwCursorPosition.X, info.dwCursorPosition.Y, info.wAttributes, info.srWindow.Left, info.srWindow.Top, info.srWindow.Right, info.srWindow.Bottom, info.dwMaximumWindowSize.X, info.dwMaximumWindowSize.Y};

	env->SetIntArrayRegion(jInfo, 0, 11, (jint *)cInfo);

	return jInfo;
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_GETCONSOLESELECTIONINFO
  (JNIEnv *env, jclass jcls)
{
	CONSOLE_SELECTION_INFO info;
	GetConsoleSelectionInfo(&info);

	jintArray jInfo = env->NewIntArray(7);

	int cInfo[7] = {info.dwFlags, info.dwSelectionAnchor.X, info.dwSelectionAnchor.Y, info.srSelection.Left, info.srSelection.Top, info.srSelection.Right, info.srSelection.Bottom};

	env->SetIntArrayRegion(jInfo, 0, 7, (jint *)cInfo);

	return jInfo;
}

JNIEXPORT jstring JNICALL Java_com_yifanlu_Josh_GETCONSOLETITLE
  (JNIEnv *env, jclass jcls)
{
	TCHAR title[1024];
	GetConsoleTitle(title, 1024);

	int len = strlen(title);
	jchar* unicodeChars = new jchar[len];
	for (int i=0; i<len; i++)
		unicodeChars[i] = title[i];
	jstring jTitle = env->NewString(unicodeChars, len);
	delete[] unicodeChars;

	return jTitle;
}

JNIEXPORT jlong JNICALL Java_com_yifanlu_Josh_GETCONSOLEWINDOW
  (JNIEnv *env, jclass jcls)
{
    HWND handle = GetConsoleWindow();
	UINT_PTR pointer = (UINT_PTR)handle;
	
	return pointer;
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_GETLARGESTCONSOLEWINDOWSIZE
  (JNIEnv *env, jclass jcls, jlong pointer)
{
	HANDLE hConsole = pointerToHandle(pointer);
	COORD size = GetLargestConsoleWindowSize(hConsole);

	jintArray info = env->NewIntArray(2);
	int cInfo[2] = {size.X, size.Y};
	env->SetIntArrayRegion(info, 0, 2, (jint *)cInfo);

	return info;
}

JNIEXPORT jint JNICALL Java_com_yifanlu_Josh_GETNUMBEROFCONSOLEINPUTEVENTS
  (JNIEnv *env, jclass jcls, jlong pointer)
{
	HANDLE hIn = pointerToHandle(pointer);
	DWORD numEvents;
	GetNumberOfConsoleInputEvents(hIn, &numEvents);
	return numEvents;
}

JNIEXPORT jint JNICALL Java_com_yifanlu_Josh_GETNUMBEROFCONSOLEMOUSEBUTTONS
  (JNIEnv *env, jclass jcls)
{
	DWORD numEvents;
	GetNumberOfConsoleMouseButtons(&numEvents);
	return numEvents;
}

JNIEXPORT jlong JNICALL Java_com_yifanlu_Josh_GETSTDHANDLE
  (JNIEnv *env, jclass jcls, jint handle)
{
	HANDLE hConsole = GetStdHandle ( (DWORD)handle );
	UINT_PTR pointer = (UINT_PTR)hConsole;

	return pointer;
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

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_WRITECONSOLE
  (JNIEnv *env, jclass jcls, jlong pointer, jstring output)
{
    HANDLE hConsole = pointerToHandle(pointer);
	const char *cOutput = env->GetStringUTFChars(output, 0);

	WriteConsole(hConsole, cOutput, sizeof(cOutput), NULL, NULL);
	env->ReleaseStringUTFChars(output, cOutput);
}