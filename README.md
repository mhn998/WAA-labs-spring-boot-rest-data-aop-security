```
docker run -d \               
    --name some-postgres1 \
    -e POSTGRES_USER:postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -v "$(pwd)"/data:/var/lib/postgresql/data \
    -p 5432:5432 postgres

```

```
echo -n mongouser | base64  
```

1. ```./mvnw clean package -DskipTests ```
2. ```mkdir docker```
3. ```cp target/WAA_first_demo-0.0.1-SNAPSHOT.jar docker```
4. ```docker tag 82950bb4816a 15719981948/waa_spring_postgres:first```
5. ```docker push 15719981948/waa_spring_postgres:first```



```
kubectl apply -f k8s/postgres-config.yml
kubectl apply -f k8s/postgres-secret.yml
kubectl apply -f k8s/postgres.yml
kubectl apply -f k8s/webapp.yml
```

Use : 
```
kubectl --help
kubectl get all
kubectl get pod 
kubectl logs pod-id
```