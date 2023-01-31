./mvnw clean package -DskipTests
mkdir docker
cp target/WAA_first_demo-0.0.1-SNAPSHOT.jar docker
docker build  -t 15719981948/spring_boot_postgres:v1 .
docker push 15719981948/spring_boot_postgres:v1
