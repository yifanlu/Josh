@ECHO OFF
set JAVABIN=
set JOSH=
set OUTPUT=
set /P JAVABIN=Type in the path to your javadoc.exe directory (add final \): %=%
set /P JOSH=Type in the path to the source files for Josh: %=%
set /P OUTPUT=Type in the path to output the javadocs (absolute): %=%

%JAVABIN%javadoc.exe -sourcepath "%JOSH%" -d "%OUTPUT%" -keywords -nodeprecatedlist -footer "Copyright &copy; 2010 <a href="http://www.yifanlu.com/">Yifan Lu</a>" -header "<strong>Josh</strong><br />Native console access wrapper for Java" -doctitle "Josh" -windowtitle "Josh" -author -version -use *.java
ECHO Done.
PAUSE