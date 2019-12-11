

*Deploy no cluster*

export $(cat .env | xargs)

docker stack deploy --with-registry-auth -c docker-compose.yml app-jax-rs

*Atualização Serviços*

docker-compose down; docker-compose up -d
(docker service update --force app-jax-rs)

*Logs*

docker logs app-jax-rs -f
(docker service logs app-jax-rs -f )

*Servico*

docker ps app-jax-rs
(docker service ps app-jax-rs )

