FROM eclipse-temurin:17-jdk

WORKDIR /app

# 仅拷贝 jar 文件
COPY target/LifeMateMonolith.jar app.jar

# 公开 9000 端口
EXPOSE 9000

# Spring Boot 默认会自动加载 application.properties，优先加载当前目录的
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/config/application.properties"]
