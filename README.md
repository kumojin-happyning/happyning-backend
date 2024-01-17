# Backend for Happyning

## What is this? 🤔

Ce projet est le backend de l'application Happyning.

Il s'agit d'un test technique pour la société [Kumojin](https://kumojin.com/).

## Lancer le projet (mode dev) 🚀

### BDD (MySQL)

- Placez vous à la racine du projet
- Démarrez le backend avec `docker-compose -f docker-compose.dev.yml up -d`
- La BDD est accessible sur le port 3306. Pour changer : 
    - Editez le port dans `docker-compose.dev.yml` : `NOUVEAU_PORT:3306`
    - Editez le port dans `application.yml` : `spring.datasource.url:jdbc:mysql://localhost:NOUVEAU_PORT/happyning?serverTimezone=UTC`

> Le backend est accessible sur le port 8080. Vous pouvez changer cette configuration dans `application.yml`.

### Spring Boot

- Placez-vous à la racine du projet
- Démarrez le backend avec `./mvnw spring-boot:run`

> Sinon, démarrez directement le projet depuis votre IDE préféré !

## Lancer le projet (mode prod) 🚀

Si vous souhaitez lancer le projet en mode prod, basez-vous directement sur le fichier `docker-compose.yml`.
Il se chargera du build pour vous !

Vous pouvez également lancer le projet en utilisant la stack complète : Voir [Happyning](https://github.com/kumojin-happyning/happyning).

## Documentation

### API

Une fois le projet démarré, vous pouvez accéder au swagger de l'API à l'adresse suivante : `http://localhost:8080/api/events/docs`