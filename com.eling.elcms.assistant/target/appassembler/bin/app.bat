@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\config;"%REPO%"\org\apache\mina\mina-core\2.0.9\mina-core-2.0.9.jar;"%REPO%"\org\slf4j\slf4j-api\1.7.7\slf4j-api-1.7.7.jar;"%REPO%"\org\slf4j\jcl-over-slf4j\1.7.12\jcl-over-slf4j-1.7.12.jar;"%REPO%"\ch\qos\logback\logback-classic\1.1.3\logback-classic-1.1.3.jar;"%REPO%"\ch\qos\logback\logback-core\1.1.3\logback-core-1.1.3.jar;"%REPO%"\org\springframework\spring-jdbc\4.1.5.RELEASE\spring-jdbc-4.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-beans\4.1.5.RELEASE\spring-beans-4.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-core\4.1.5.RELEASE\spring-core-4.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-tx\4.1.5.RELEASE\spring-tx-4.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-context\4.1.5.RELEASE\spring-context-4.1.5.RELEASE.jar;"%REPO%"\org\springframework\spring-aop\4.1.5.RELEASE\spring-aop-4.1.5.RELEASE.jar;"%REPO%"\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;"%REPO%"\org\springframework\spring-expression\4.1.5.RELEASE\spring-expression-4.1.5.RELEASE.jar;"%REPO%"\org\apache\commons\commons-lang3\3.3.2\commons-lang3-3.3.2.jar;"%REPO%"\org\apache\httpcomponents\httpclient\4.3.3\httpclient-4.3.3.jar;"%REPO%"\org\apache\httpcomponents\httpcore\4.3.2\httpcore-4.3.2.jar;"%REPO%"\commons-logging\commons-logging\1.1.3\commons-logging-1.1.3.jar;"%REPO%"\commons-codec\commons-codec\1.6\commons-codec-1.6.jar;"%REPO%"\commons-io\commons-io\2.4\commons-io-2.4.jar;"%REPO%"\com\mchange\c3p0\0.9.2.1\c3p0-0.9.2.1.jar;"%REPO%"\com\mchange\mchange-commons-java\0.2.3.4\mchange-commons-java-0.2.3.4.jar;"%REPO%"\mysql\mysql-connector-java\5.1.35\mysql-connector-java-5.1.35.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.4.2\jackson-databind-2.4.2.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.4.0\jackson-annotations-2.4.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.4.2\jackson-core-2.4.2.jar;"%REPO%"\com\eling\com.eling.elcms.assistant\1.0.0-SNAPSHOT\com.eling.elcms.assistant-1.0.0-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="app" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" com.eling.elcms.assistant.App applicationContext.xml %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
