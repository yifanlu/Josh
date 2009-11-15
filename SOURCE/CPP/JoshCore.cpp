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

int CharCount(const char *str)
{
	int count = 0;
	if (str != '\0')
		for (; *(str + count) != '\0'; ++count);
	return count;
}

BOOL WINAPI SetConsoleFont(HANDLE hOutput, DWORD fontIndex) {
	typedef BOOL (WINAPI *PSetConsoleFont)(HANDLE, DWORD);
	static PSetConsoleFont pSetConsoleFont = NULL;

	if(pSetConsoleFont == NULL)
		pSetConsoleFont = (PSetConsoleFont)::GetProcAddress(::GetModuleHandle("kernel32"), "SetConsoleFont");
	if(pSetConsoleFont == NULL) return FALSE;

	return pSetConsoleFont(hOutput, fontIndex);
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

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_GETCURRENTCONSOLEFONT
  (JNIEnv *env, jclass jcls, jlong pointer, jboolean maximum)
{
	HANDLE hConsole = pointerToHandle(pointer);
	CONSOLE_FONT_INFO fontInfo;
	GetCurrentConsoleFont(hConsole, (maximum ? TRUE : FALSE), &fontInfo);

	jintArray jInfo = env->NewIntArray(3);
	int cInfo[3] = {fontInfo.nFont, fontInfo.dwFontSize.X, fontInfo.dwFontSize.Y};
	env->SetIntArrayRegion(jInfo, 0, 3, (jint *)cInfo);
	return jInfo;
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

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_PEEKCONSOLEINPUT
  (JNIEnv *env, jclass jcls, jlong pointer, jint length)
{
    HANDLE hIn = pointerToHandle(pointer);
    INPUT_RECORD InRec;
    DWORD NumRead;

	PeekConsoleInput(hIn, &InRec, 1, &NumRead);

	jintArray info;

	if (InRec.EventType == KEY_EVENT)
	{
		int cInfo[7] = {InRec.EventType, (int)InRec.Event.KeyEvent.bKeyDown, InRec.Event.KeyEvent.wRepeatCount, InRec.Event.KeyEvent.wVirtualKeyCode, InRec.Event.KeyEvent.wVirtualScanCode, (int)InRec.Event.KeyEvent.uChar.UnicodeChar, InRec.Event.KeyEvent.dwControlKeyState};
		info = env->NewIntArray(7);
		env->SetIntArrayRegion(info, 0, 7, (jint *)cInfo);
	}
	else if (InRec.EventType == MOUSE_EVENT)
	{
		int cInfo[6] = {InRec.EventType, InRec.Event.MouseEvent.dwMousePosition.X, InRec.Event.MouseEvent.dwMousePosition.Y, InRec.Event.MouseEvent.dwButtonState, InRec.Event.MouseEvent.dwControlKeyState, InRec.Event.MouseEvent.dwEventFlags};
		info = env->NewIntArray(6);
		env->SetIntArrayRegion(info, 0, 6, (jint *)cInfo);
	}
	else if (InRec.EventType == WINDOW_BUFFER_SIZE_EVENT)
	{
		int cInfo[3] = {InRec.EventType, InRec.Event.WindowBufferSizeEvent.dwSize.X, InRec.Event.WindowBufferSizeEvent.dwSize.Y};
		info = env->NewIntArray(3);
		env->SetIntArrayRegion(info, 0, 3, (jint *)cInfo);
	}
	else
	{
		int cInfo[1] = {InRec.EventType};
		info = env->NewIntArray(1);
		env->SetIntArrayRegion(info, 0, 1, (jint *)cInfo);
	}

	return info;
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_READCONSOLEINPUT
  (JNIEnv *env, jclass jcls, jlong pointer, jint length)
{
    HANDLE hIn = pointerToHandle(pointer);
    INPUT_RECORD InRec;
    DWORD NumRead;

	ReadConsoleInput(hIn, &InRec, 1, &NumRead);

	jintArray info;

	if (InRec.EventType == KEY_EVENT)
	{
		int cInfo[7] = {InRec.EventType, (int)InRec.Event.KeyEvent.bKeyDown, InRec.Event.KeyEvent.wRepeatCount, InRec.Event.KeyEvent.wVirtualKeyCode, InRec.Event.KeyEvent.wVirtualScanCode, (int)InRec.Event.KeyEvent.uChar.UnicodeChar, InRec.Event.KeyEvent.dwControlKeyState};
		info = env->NewIntArray(7);
		env->SetIntArrayRegion(info, 0, 7, (jint *)cInfo);
	}
	else if (InRec.EventType == MOUSE_EVENT)
	{
		int cInfo[6] = {InRec.EventType, InRec.Event.MouseEvent.dwMousePosition.X, InRec.Event.MouseEvent.dwMousePosition.Y, InRec.Event.MouseEvent.dwButtonState, InRec.Event.MouseEvent.dwControlKeyState, InRec.Event.MouseEvent.dwEventFlags};
		info = env->NewIntArray(6);
		env->SetIntArrayRegion(info, 0, 6, (jint *)cInfo);
	}
	else if (InRec.EventType == WINDOW_BUFFER_SIZE_EVENT)
	{
		int cInfo[3] = {InRec.EventType, InRec.Event.WindowBufferSizeEvent.dwSize.X, InRec.Event.WindowBufferSizeEvent.dwSize.Y};
		info = env->NewIntArray(3);
		env->SetIntArrayRegion(info, 0, 3, (jint *)cInfo);
	}
	else
	{
		int cInfo[1] = {InRec.EventType};
		info = env->NewIntArray(1);
		env->SetIntArrayRegion(info, 0, 1, (jint *)cInfo);
	}

	return info;
}

JNIEXPORT jstring JNICALL Java_com_yifanlu_Josh_READCONSOLE
  (JNIEnv *env, jclass jcls, jlong pointer)
{
    HANDLE hIn = pointerToHandle(pointer);
	char ReadBuffer[1024] = {0};
	DWORD dwBytesRead = 0;

	ReadConsole(hIn, &ReadBuffer, 1024, &dwBytesRead, NULL);

	char input[1024];
	int count = 0;
	for(int i=0; i<1024; i++)
		if(ReadBuffer[i] != '\0' && count < dwBytesRead)
		{
			input[count] = ReadBuffer[i];
			count++;
		}

	int len = dwBytesRead - 2;
	jchar* unicodeChars = new jchar[len];
	for (int i=0; i<len; i++)
		unicodeChars[i] = input[i];
	jstring jInput = env->NewString(unicodeChars, len);
	delete[] unicodeChars;

	return jInput;
}

JNIEXPORT jobjectArray JNICALL Java_com_yifanlu_Josh_READCONSOLEOUTPUT
  (JNIEnv *env, jclass jcls, jlong pointer, jint sizeX, jint sizeY, jint coordX, jint coordY)
{
    HANDLE hConsole = pointerToHandle(pointer);
    CHAR_INFO *chiBuffer = new CHAR_INFO[sizeX * sizeY];
    COORD coordBufSize;
    COORD coordBufCoord;
	SMALL_RECT srctReadRect;

    srctReadRect.Top = coordX;
    srctReadRect.Left = coordY; 
    srctReadRect.Bottom = sizeY - 1;
    srctReadRect.Right = sizeX - 1;
    coordBufSize.Y = sizeY;
    coordBufSize.X = sizeX;
    coordBufCoord.X = coordX;
    coordBufCoord.Y = coordY;

	ReadConsoleOutput(hConsole, chiBuffer, coordBufSize, coordBufCoord, &srctReadRect);

	jintArray blank = env->NewIntArray(2);
	jobjectArray jConsoleOutput = env->NewObjectArray(sizeX*sizeY, env->GetObjectClass(blank), blank);
	for(int x=0; x<(sizeX*sizeY); x++)
	{
		jintArray info = env->NewIntArray(2);
		int cInfo[2] = {(int)chiBuffer[x].Char.UnicodeChar, chiBuffer[x].Attributes};
		env->SetIntArrayRegion(info, 0, 2, (jint *)cInfo);
		env->SetObjectArrayElement(jConsoleOutput,x,info);
	}

	return jConsoleOutput;
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_READCONSOLEOUTPUTATTRIBUTE
  (JNIEnv *env, jclass jcls, jlong pointer, jint x, jint y, jint length)
{
    HANDLE hConsole = pointerToHandle(pointer);
	COORD coordBufCoord = {x , y};
	WORD *buffer = new WORD[length];
	DWORD numRead;

	ReadConsoleOutputAttribute(hConsole, buffer, length, coordBufCoord, &numRead);

	int *cInfo = new int[length];

	for(int x = 0; x < length; x++)
		cInfo[x] = (int)buffer[x];

	jintArray jInfo = env->NewIntArray(length);
	env->SetIntArrayRegion(jInfo, 0, length, (jint *)cInfo);

	return jInfo;
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_READCONSOLEOUTPUTCHARACTER
  (JNIEnv *env, jclass jcls, jlong pointer, jint x, jint y, jint length)
{
    HANDLE hConsole = pointerToHandle(pointer);
	COORD coordBufCoord = {x , y};
	char *buffer = new char[length];
	DWORD numRead;

	ReadConsoleOutputCharacter(hConsole, (LPTSTR)buffer, 10, coordBufCoord, &numRead);

	int *cInfo = new int[length];

	for(int x = 0; x < length; x++)
		cInfo[x] = (int)buffer[x];

	jintArray jInfo = env->NewIntArray(length);
	env->SetIntArrayRegion(jInfo, 0, length, (jint *)cInfo);

	return jInfo;
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SCROLLCONSOLESCREENBUFFER
  (JNIEnv *env, jclass jcls, jlong pointer, jint scrollLeft, jint scrollTop, jint scrollRight, jint scrollBottom, jint clipLeft, jint clipTop, jint clipRight, jint clipBottom, jint toX, jint toY, jchar fillChar, jint fillAttribute)
{
    HANDLE hConsole = pointerToHandle(pointer);
	SMALL_RECT scroll = {scrollLeft, scrollTop, scrollRight, scrollBottom};
	SMALL_RECT clip = {clipLeft, clipTop, clipRight, clipBottom};
	COORD to = {toX, toY};
	CHAR_INFO fill;
    fill.Attributes = fillAttribute; 
    fill.Char.UnicodeChar = (char)fillChar;

	ScrollConsoleScreenBuffer(hConsole, &scroll, clipLeft == -1 ? NULL : &clip, to, &fill);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLEACTIVESCREENBUFFER
  (JNIEnv *env, jclass jcls, jlong pointer)
{
	SetConsoleActiveScreenBuffer(pointerToHandle(pointer));
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLECURSORPOSITION
  (JNIEnv *env, jclass jcls, jlong pointer, jint x, jint y)
{
    HANDLE hConsole = pointerToHandle(pointer);
	COORD pos = {x, y};
	SetConsoleCursorPosition ( hConsole, pos );
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLECURSORINFO
  (JNIEnv *env, jclass jcls, jlong pointer, jint size, jboolean visible)
{
    HANDLE hConsole = pointerToHandle(pointer);
	CONSOLE_CURSOR_INFO info;
	info.dwSize = size;
	info.bVisible = visible ? TRUE : FALSE;

	SetConsoleCursorInfo(hConsole, &info);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLEDISPLAYMODE
  (JNIEnv *env, jclass jcls, jlong pointer, jint mode)
{
    HANDLE hConsole = pointerToHandle(pointer);

	SetConsoleDisplayMode(hConsole, mode, NULL);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLEHISTORYINFO
  (JNIEnv *env, jclass jcls, jint bufferSize, jint numberOfBuffers, jint flags)
{
	CONSOLE_HISTORY_INFO info;
	info.cbSize = sizeof(CONSOLE_HISTORY_INFO);
	info.HistoryBufferSize = bufferSize;
	info.NumberOfHistoryBuffers = numberOfBuffers;
	info.dwFlags = flags;

	SetConsoleHistoryInfo(&info);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLEMODE
  (JNIEnv *env, jclass jcls, jlong pointer, jint flags)
{
    HANDLE hConsole = pointerToHandle(pointer);

	SetConsoleMode(hConsole, flags);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLESCREENBUFFERINFOEX
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

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLESCREENBUFFERSIZE
  (JNIEnv *env, jclass jcls, jlong pointer, jint x, jint y)
{
    HANDLE hConsole = pointerToHandle(pointer);
	COORD size = {x, y};

	SetConsoleScreenBufferSize(hConsole, size);
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


JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLEWINDOWINFO
  (JNIEnv *env, jclass jcls, jlong pointer, jboolean absolute, jint left, jint top, jint right, jint bottom)
{
    HANDLE hConsole = pointerToHandle(pointer);

	SMALL_RECT newSize = {left, top, right, bottom};

	SetConsoleWindowInfo(hConsole, (absolute ? TRUE : FALSE), &newSize);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCURRENTCONSOLEFONTEX
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

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETSTDHANDLE
  (JNIEnv *env, jclass jcls, jint handle, jlong pointer)
{
    HANDLE newHandle = pointerToHandle(pointer);
	SetStdHandle(handle, newHandle);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_WRITECONSOLE
  (JNIEnv *env, jclass jcls, jlong pointer, jstring output)
{
    HANDLE hConsole = pointerToHandle(pointer);
	const char *cOutput = env->GetStringUTFChars(output, 0);

	WriteConsole(hConsole, cOutput, CharCount(cOutput), NULL, NULL);
	env->ReleaseStringUTFChars(output, cOutput);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_WRITECONSOLEINPUT
  (JNIEnv *env, jclass jcls, jlong pointer, jint eventType, jintArray data, jint length)
{
    HANDLE hIn = pointerToHandle(pointer);
    INPUT_RECORD InRec;
    DWORD NumWrote;

	jint *body = env->GetIntArrayElements(data, 0);
	
	InRec.EventType = eventType;
	if (InRec.EventType == KEY_EVENT)
	{
		InRec.Event.KeyEvent.bKeyDown = (body[1] == 1 ? TRUE : FALSE);
		InRec.Event.KeyEvent.wRepeatCount = body[2];
		InRec.Event.KeyEvent.wVirtualKeyCode = body[3];
		InRec.Event.KeyEvent.wVirtualScanCode = body[4];
		InRec.Event.KeyEvent.uChar.UnicodeChar = (char)body[5];
		InRec.Event.KeyEvent.dwControlKeyState = body[6];
	}
	else if (InRec.EventType == MOUSE_EVENT)
	{
		InRec.Event.MouseEvent.dwMousePosition.X = body[1];
		InRec.Event.MouseEvent.dwMousePosition.Y = body[2];
		InRec.Event.MouseEvent.dwButtonState = body[3];
		InRec.Event.MouseEvent.dwControlKeyState = body[4];
		InRec.Event.MouseEvent.dwEventFlags = body[5];
	}
	else if (InRec.EventType == WINDOW_BUFFER_SIZE_EVENT)
	{
		InRec.Event.WindowBufferSizeEvent.dwSize.X = body[1];
		InRec.Event.WindowBufferSizeEvent.dwSize.Y = body[2];
	}

	WriteConsoleInput(hIn, &InRec, length, &NumWrote);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_WRITECONSOLEOUTPUT
  (JNIEnv *env, jclass jcls, jlong pointer, jobjectArray data, jint sizeX, jint sizeY, jint coordX, jint coordY)
{
    HANDLE hConsole = pointerToHandle(pointer);
    CHAR_INFO *chiBuffer = new CHAR_INFO[sizeX * sizeY];
    COORD coordBufSize;
    COORD coordBufCoord;
	SMALL_RECT srctWriteRect;

    srctWriteRect.Top = coordX;
    srctWriteRect.Left = coordY; 
    srctWriteRect.Bottom = sizeY - 1;
    srctWriteRect.Right = sizeX - 1;
    coordBufSize.Y = sizeY;
    coordBufSize.X = sizeX;
    coordBufCoord.X = coordX;
    coordBufCoord.Y = coordY;

	for(int x=0; x<(sizeX*sizeY); x++)
	{
		jintArray element = (jintArray)env->GetObjectArrayElement(data,x);
		jint *body = env->GetIntArrayElements(element, 0);
		chiBuffer[x].Char.UnicodeChar = (char)body[0];
		chiBuffer[x].Attributes = body[1];
	}

	WriteConsoleOutput(hConsole, chiBuffer,	coordBufSize, coordBufCoord, &srctWriteRect);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_WRITECONSOLEOUTPUTATTRIBUTE
  (JNIEnv *env, jclass jcls, jlong pointer, jintArray data, jint length, jint x, jint y)
{
	HANDLE hConsole = pointerToHandle(pointer);
	COORD coordBufCoord = {x , y};
	WORD *buffer = new WORD[length];
	DWORD numWrote;

	jint *body = env->GetIntArrayElements(data, 0);

	for(int x = 0; x < length; x++)
		buffer[x] = (int)body[x];

	WriteConsoleOutputAttribute(hConsole, buffer, length, coordBufCoord, &numWrote);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_WRITECONSOLEOUTPUTCHARACTER
  (JNIEnv *env, jclass jcls, jlong pointer, jintArray data, jint length, jint x, jint y)
{
	HANDLE hConsole = pointerToHandle(pointer);
	COORD coordBufCoord = {x , y};
	char *buffer = new char[length];
	DWORD numWrote;

	jint *body = env->GetIntArrayElements(data, 0);

	for(int x = 0; x < length; x++)
		buffer[x] = (char)body[x];

	WriteConsoleOutputCharacter(hConsole, buffer, length, coordBufCoord, &numWrote);
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


JNIEXPORT void JNICALL Java_com_yifanlu_Josh_BEEP
  (JNIEnv *env, jclass jcls, jint freq, jint duration)
{
	Beep(freq, duration);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_SETCONSOLEFONT
  (JNIEnv *env, jclass jcls, jlong pointer, jint font)
{
    HANDLE hConsole = pointerToHandle(pointer);
	SetConsoleFont(hConsole, font);
}