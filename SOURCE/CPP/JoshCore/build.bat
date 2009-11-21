@SET VSINSTALLDIR=C:\Program Files (x86)\Microsoft Visual Studio 9.0
@SET VCINSTALLDIR=C:\Program Files (x86)\Microsoft Visual Studio 9.0\VC
@SET WindowsSdkDir=C:\Program Files\Microsoft SDKs\Windows\v6.0A
@SET JREDIR=C:\eclipse\jre

@echo Setting up environment...

@rem
@rem Root of Visual Studio IDE installed files.
@rem
@set DevEnvDir=%VSINSTALLDIR%\Common7\IDE

@set PATH=%VSINSTALLDIR%\Common7\IDE;%VSINSTALLDIR%\VC\BIN;%VSINSTALLDIR%\Common7\Tools;%PATH%
@set INCLUDE=%WindowsSdkDir%\Include;%VCINSTALLDIR%\Include;%JREDIR%\include;%JREDIR%\include\win32;%INCLUDE%
@set LIB=%VCINSTALLDIR%\LIB;%WindowsSdkDir%\Lib;%LIB%
@set LIBPATH=%VCINSTALLDIR%\LIB;%LIBPATH%

@goto end

:end

@echo Compiling...
@cl /O2 /Oi /GL /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_USRDLL" /D "JOSHCORE_EXPORTS" /D "_WINDLL" /D "_UNICODE" /D "UNICODE" /EHsc /LD /Gy /W0 /Zi /TP JoshCore\JoshCore.cpp
@echo Done.

@PAUSE