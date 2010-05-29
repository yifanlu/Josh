Josh (Java Output Console enHanced)
By Yifan Lu (www.yifanlu.com)
=======================================================================
Follow the development: http://twitter.com/projectjosh

What is this?
This is a Windows ONLY console library for Java.
It wraps native functions in wincon.h into Java methods.

This package:

-/JAR
Here is the JAR file you link with your application.
-/CLASS
The class files for advanced linking.
-/DOCS
The JavaDocs for Josh.
-/LIB
Contains the required lib for using Josh. This file must be in the same folder
as the .class file for your application.
-/SOURCE/CPP
The JNI CPP source
-/SOURCE/Java
The Java sources
-/SOURCE/Examples
Contains some examples for you to see. You must link Josh to it and move the dll for it to work.

How to Compile:
If you prefer to compile everything yourself, there's some steps you need to take:
For the CPP DLL file: Open build.bat in /SOURCE/CPP and specify the folder 
locations.
For the Java files: Compile like any other Java file.
For JavaDocs: Use javadoc_generate.bat and specify some paths to generate 
the JavaDocs.

How to Use:

1. Link Josh.jar with your application.
2. Choose a JoshCore.dll. The default will only work on Windows XP or 
higher. For more compatibility, use the Windows 2000 version, for more 
features, use the Windows Vista version.
3. Look at the examples and read the JavaDocs if you need any help.

=======================================================================
Copyright (C) 2010 Yifan Lu

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