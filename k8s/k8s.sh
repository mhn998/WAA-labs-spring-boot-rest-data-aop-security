kubectl apply -f postgres-config.yml # not used in our code
kubectl apply -f postgres-secret.yml # not used in our code
kubectl apply -f postgres.yml
kubectl apply -f webapp.yml
