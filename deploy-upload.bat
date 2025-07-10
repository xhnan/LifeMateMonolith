@echo off
REM =============== 配置区 ===============
set SERVER_USER=root
set SERVER_IP=120.78.0.54
set SERVER_PATH=/usr/local/lifemate
set JAR_NAME=LifeMateMonolith-0.0.1-SNAPSHOT.jar

echo 📦 正在上传 JAR 包...
scp target\%JAR_NAME% %SERVER_USER%@%SERVER_IP%:%SERVER_PATH%/target/

echo ⚙️ 正在上传配置文件...
scp config\application.properties %SERVER_USER%@%SERVER_IP%:%SERVER_PATH%/config/

echo ✅ 上传完成。
echo 请登录服务器执行：
echo    cd %SERVER_PATH% && docker-compose up -d --build
pause
