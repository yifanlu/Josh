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
-/DOCS
The JavaDocs for Josh.
-/LIB
For advanced users, we recommend hosting the LIB separate from the JAR file, more info below.
-/SOURCE/CPP
The JNI CPP source
-/SOURCE/Java
The Java sources in a NetBeans project. Includes examples under test.

How to Compile the Native Library:
If you prefer to compile everything yourself, there's some steps you need to take:
For the CPP DLL file: Open build.bat in /SOURCE/CPP and specify the folder 
locations.

To use, just link Josh.jar with your application. Advanced users may want to 
include JoshCore.dll separately (for more flexibility and speed), what you need 
to do is get the correct version from the LIB folder. There are three versions: 
JoshCore_2000.dll supports all Windows versions, but does not have all the features, 
JoshCore_XP.dll supports Windows XP and beyond, and JoshCore_Vista.dll supports Windows 
Vista and beyond. Rename one of your choice, include it with your application, and 
add the path to it to your "java.library.path" property, and Josh will find it.

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