## docker-compose configuration
### Docker images contains:
- **NGINX** — `80 – 80, 8080 – 8080`
- **MYSQL** — `3306 – 3306`
- **NODEJS** — `3000`
- **PHP-FPM** — `9000`
### How to run this project
You need to have docker and docker-compose installed on your machine.
```
git clone git@github.com:id-0x56/docker_nginx_php_mysql.git
```
```
cd docker_nginx_php_mysql && cp .env.example .env
```
```
docker-compose up -d
```
