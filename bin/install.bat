@echo off
echo [INFO] Install JeeLearn.
cd %~dp0
cd ..
call mvn clean install -pl . -Dmaven.test.skip=true  

echo [INFO] Install JeeLearn/jLearn-parent.
cd %~dp0
cd ../jLearn-parent
call mvn clean install -pl . -Dmaven.test.skip=true


echo [INFO] Install common.
cd %~dp0
cd ../common
call mvn clean install -pl .  -Dmaven.test.skip=true

echo [INFO] Install War.
cd %~dp0
cd ../web
call mvn clean install -pl .  -Dmaven.test.skip=true

cd ../bin
pause