# 选择一个合适的基础镜像
FROM openjdk:17

# 将本地的 .jar 文件复制到容器中的 /app.jar
COPY ./target/mind-0.0.1-SNAPSHOT.jar /app.jar

# 暴露容器的 8080 端口
EXPOSE 8088

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "/app.jar"]

LABEL authors="fxlnh"
