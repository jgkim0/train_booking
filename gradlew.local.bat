@echo off
rem Runs gradlew with JAVA_HOME pinned to a valid JDK, scoped to this
rem process only (setlocal/endlocal). The system/user JAVA_HOME env var
rem is left untouched.
rem
rem Usage: gradlew.local.bat test
setlocal
set "JAVA_HOME=C:\Program Files\Java\jdk-17"
call "%~dp0gradlew.bat" %*
endlocal
