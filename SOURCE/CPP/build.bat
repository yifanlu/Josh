@ECHO OFF
@SET VSINSTALLDIR=C:\Program Files (x86)\Microsoft Visual Studio 9.0
@SET VCINSTALLDIR=C:\Program Files (x86)\Microsoft Visual Studio 9.0\VC
@SET WindowsSdkDir=C:\Program Files\Microsoft SDKs\Windows\v7.0
@SET JREDIR=C:\eclipse\jre

set /P VSINSTALLDIR=Type in the path to your Visual Studio installation: %=%
set /P VCINSTALLDIR=Type in the path to your VC folder (usually inside VS installation folder): %=%
set /P WindowsSdkDir=Type in the path to the Windows SDK folder (ending in v#.#): %=%
set /P JREDIR=Type in the path to your JRE folder: %=%

@SET TARGET=XP
@SET USRINPUT=0

@ECHO Josh Compiler
@ECHO Choose your minimum Windows version support
@ECHO 1 = Windows 2000
@ECHO 2 = Windows XP/2003
@ECHO 3 = Windows Vista/2008/7

@SET /P USRINPUT=

@IF %USRINPUT% == 1 (@SET TARGET=2000) ELSE IF %USRINPUT% == 2 (@SET TARGET=XP) ELSE (@SET TARGET=Vista)

@echo Setting up environment...

@rem
@rem Root of Visual Studio IDE installed files.
@rem
@set DevEnvDir=%VSINSTALLDIR%\Common7\IDE

@set PATH=%WindowsSdkDir%\Bin;%VSINSTALLDIR%\Common7\IDE;%VSINSTALLDIR%\VC\BIN;%VSINSTALLDIR%\Common7\Tools;%PATH%
@set INCLUDE=%WindowsSdkDir%\Include;%VCINSTALLDIR%\Include;%JREDIR%\include;%JREDIR%\include\win32;%INCLUDE%
@set LIB=%VCINSTALLDIR%\LIB;%WindowsSdkDir%\Lib;%LIB%
@set LIBPATH=%VCINSTALLDIR%\LIB;%LIBPATH%

@goto end

:end

@echo Compiling library...
@cl /O2 /Oi /GL /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_USRDLL" /D "JOSHCORE_EXPORTS" /D "_WINDLL" /EHsc /LD /Gy /W0 /TP /FeJoshCore JoshCore\%TARGET%.cpp
@echo Done.

@PAUSE