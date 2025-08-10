@echo off
echo Java CLI Utility Suite - Setup Script
echo =====================================

echo.
echo Checking Java installation...
java -version
if %ERRORLEVEL% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17+ and add it to your PATH
    pause
    exit /b 1
)

echo.
echo Checking Maven installation...
mvn -version
if %ERRORLEVEL% neq 0 (
    echo WARNING: Maven is not installed
    echo.
    echo Please install Maven from: https://maven.apache.org/download.cgi
    echo.
    echo Alternative: You can use the Maven Wrapper (mvnw) if available
    echo.
    echo To install Maven:
    echo 1. Download Maven from https://maven.apache.org/download.cgi
    echo 2. Extract to C:\apache-maven-3.x.x
    echo 3. Add C:\apache-maven-3.x.x\bin to your PATH environment variable
    echo 4. Set MAVEN_HOME=C:\apache-maven-3.x.x
    echo 5. Restart your command prompt
    echo.
    pause
    exit /b 1
)

echo.
echo Setting up project...
if exist target (
    echo Cleaning previous build...
    rmdir /s /q target
)

echo.
echo Building project...
mvn clean compile
if %ERRORLEVEL% neq 0 (
    echo ERROR: Compilation failed
    pause
    exit /b 1
)

echo.
echo Running tests...
mvn test
if %ERRORLEVEL% neq 0 (
    echo WARNING: Some tests failed
)

echo.
echo Creating executable JAR...
mvn package
if %ERRORLEVEL% neq 0 (
    echo ERROR: Package creation failed
    pause
    exit /b 1
)

echo.
echo =====================================
echo Setup completed successfully!
echo.
echo Executable JAR created: target\cli-utility-suite.jar
echo.
echo Usage examples:
echo   java -jar target\cli-utility-suite.jar --help
echo   java -jar target\cli-utility-suite.jar scan . --recursive
echo.
pause
