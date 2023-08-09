## docker-compose configuration
### Docker images contains:
- **NGINX** — `3000 – 3000 ,80 – 80, 8080 – 8080`
- **NODEJS** — `3000, 24678 – 24678`
- **PHP-FPM** — `9000`
- **OPENJDK** — `8080`
- **MYSQL** — `3306 – 3306`
- **POSTGRES** — `5432 – 5432`
- **PGADMIN** — `15432 – 80`
- **RABBITMQ** — `5672 – 5672, 15672 – 15672`
- **MINIO** — `9090 – 9090, 9000 – 9000`
### How to run this project
You need to have docker and docker compose installed on your machine.
```
git clone git@github.com:id-0x56/workspace.git
```
```
cd workspace && cp .env.example .env
```
```
docker compose up -d
```
