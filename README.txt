Josh (Java Output Console enHanced)
By Yifan Lu (www.yifanlu.com)
=======================================================================
Follow the development: http://twitter.com/projectjosh

What is this?
This is a Windows ONLY console library for Java.
The goal is to port ALL native console functions
found in wincon.h in the Windows API to Java, with
the top priority being the most common functions

Goals:
-As many functions as possible
-Extremely easy to use
-Extendable
-Well documented
-Small and fast

This package:

-/JAR
Here is the JAR file you can move to your ./jre/lib/ext for easy importing
-/CLASS
You can copy this folder to where your source is to import Josh. Allows you
to try Josh without modifying your JRE installation.
-/LIB
Contains the required lib for using Josh. This file must be in the same folder
as the .class file for your application.
-/SOURCE/CPP
The JNI CPP source
-/SOURCE/Java
The Java sources
-/SOURCE/Examples
Contains some examples for you to see. NOTE: They won't work unless you move the
.dll from -/LIB to the Examples folder and install Josh either with the jar or
via copying the package folder from -/CLASS

How to Compile:
If you prefer to compile everything yourself, there's some steps you need to take:
For the CPP DLL file: You must specify your jre/include & jre/include/win32 from your JRE installation as the includes (Google if you don't know how), and make sure to include the Windows SDK headers & libraries wherever you installed (Visual C++ should include them automatically if you have Windows SDK installed).
For the Java files: Compile like any other Java file javac -d com/yifanlu/ *.java

How to Use:

A well written documention will be written in time for the release, but for now:

-Get Josh imported into your application
1. Copy the JoshCore.dll to wherever your .class is
2. Copy either the .jar (from /JAR) or the "com" folder (from /CLASS) to where 
	your .class is

As of now, if you're going to redistribute your application that uses Josh, 
you must include these files also.

=======================================================================
Copyright (C) 2009  Yifan Lu

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.