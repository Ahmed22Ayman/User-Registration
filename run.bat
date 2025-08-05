@echo off
setlocal

:: MySQL Connector JAR name
set JAR=mysql-connector-java-9.4.0.jar

:: Classpath
set CP=.;%JAR%

echo.
echo 🔧 Compiling Java files...
javac -cp "%CP%" App.java dao\UserDAO.java model\User.java util\DBUtil.java

echo.
echo 🚀 Running Application...
java -cp "%CP%" App

echo.
pause
