// JOSH - Java Output Shell Hues
// A better console output implemention
//
// By Yifan Lu
// 

#ifndef TARGETVER
#define TARGETVER 0x0501
#endif

#include "2000.cpp"

BOOL WINAPI SetConsoleFont(HANDLE hOutput, DWORD fontIndex) {
	typedef BOOL (WINAPI *PSetConsoleFont)(HANDLE, DWORD);
	static PSetConsoleFont pSetConsoleFont = NULL;

	if(pSetConsoleFont == NULL)
		pSetConsoleFont = (PSetConsoleFont)::GetProcAddress(::GetModuleHandle("kernel32"), "SetConsoleFont");
	if(pSetConsoleFont == NULL) return FALSE;

	return pSetConsoleFont(hOutput, fontIndex);
}

JNIEXPORT jint JNICALL Java_com_yifanlu_Josh_Josh_GETCONSOLEDISPLAYMODE
  (JNIEnv *env, jclass jcls)
{
	DWORD mode;
	GetConsoleDisplayMode(&mode);

	return (int)mode;
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_Josh_GETCONSOLEFONTSIZE
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

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_Josh_GETCONSOLESELECTIONINFO
  (JNIEnv *env, jclass jcls)
{
	CONSOLE_SELECTION_INFO info;
	GetConsoleSelectionInfo(&info);

	jintArray jInfo = env->NewIntArray(7);

	int cInfo[7] = {info.dwFlags, info.dwSelectionAnchor.X, info.dwSelectionAnchor.Y, info.srSelection.Left, info.srSelection.Top, info.srSelection.Right, info.srSelection.Bottom};

	env->SetIntArrayRegion(jInfo, 0, 7, (jint *)cInfo);

	return jInfo;
}

JNIEXPORT jintArray JNICALL Java_com_yifanlu_Josh_Josh_GETCURRENTCONSOLEFONT
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

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_Josh_SETCONSOLEFONT
  (JNIEnv *env, jclass jcls, jlong pointer, jint font)
{
    HANDLE hConsole = pointerToHandle(pointer);
	SetConsoleFont(hConsole, font);
}

JNIEXPORT void JNICALL Java_com_yifanlu_Josh_Josh_SETCONSOLEDISPLAYMODE
  (JNIEnv *env, jclass jcls, jlong pointer, jint mode)
{
    HANDLE hConsole = pointerToHandle(pointer);

	SetConsoleDisplayMode(hConsole, mode, NULL);
}