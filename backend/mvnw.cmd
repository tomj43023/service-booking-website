@echo off
setlocal

for %%i in ("%~dp0.") do set "MAVEN_PROJECTBASEDIR=%%~fi"
if "%MAVEN_USER_HOME%"=="" set "MAVEN_USER_HOME=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-user-home"
set "MAVEN_OPTS=-Dmaven.repo.local=%MAVEN_PROJECTBASEDIR%\.mvn\repository %MAVEN_OPTS%"

set "WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
if not exist "%WRAPPER_JAR%" (
  echo Maven wrapper jar is missing.
  echo Download it with:
  echo powershell -ExecutionPolicy Bypass -Command "Invoke-WebRequest -Uri https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.4/maven-wrapper-3.3.4.jar -OutFile backend/.mvn/wrapper/maven-wrapper.jar"
  exit /b 1
)

java "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" -classpath "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
endlocal
