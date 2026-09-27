# 베이스 이미지 선택
FROM eclipse-temurin:21-jdk

# 작업 디렉토리 설정
WORKDIR /app

# jar 파일 복사
COPY build/libs/*.jar app.jar

# 실행 명령
ENTRYPOINT ["java", "-jar", "app.jar"]