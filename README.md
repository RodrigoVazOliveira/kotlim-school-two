URL: https://protected-mountain-14763.herokuapp.com/

DOCKER

1. instalar docker do mariadb na porta 3307

docker pull mariadb
docker run -d -p 3307:3307 --name mariadb-container -e MARIADB_USER=fk -e MARIADB_PASSWORD=kotlim -e
MARIADB_ROOT_PASSWORD=root mariadb:latest --port 3307

depois crie o database e seda os privilegios ao usuario para esse database