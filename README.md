# Cantine

## Introduction
Le sujet choisi est le Sujet 3 (Cantine)
Le projet utilise l'architecture rest.

## Installation en local

### Prérequis

La réalisation de ce projet est resté dans un environnement local.
  * Pour lancer le back, il vous faudra une installation de tomcat (la version 9.0.19 a été utilisée dans ce projet)
  * Pour lancer le front, il vous faudra un serveur local comme WampServer par exemple.
  
Si le projet est lancé en local, il faut ajouter un filtre à la configuration de tomcat.
  * Ajoutez les lignes suivantes dans le fichier `cheminverstomcat/conf/web.xml` :
  
  ```xml
  <filter>
  <filter-name>CorsFilter</filter-name>
  <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
  <init-param>
    <param-name>cors.allowed.origins</param-name>
    <param-value>http://localhost</param-value>
  </init-param>
  <init-param>
    <param-name>cors.allowed.methods</param-name>
    <param-value>GET,POST,HEAD,OPTIONS,PUT,DELETE</param-value>
  </init-param>
  <init-param>
    <param-name>cors.allowed.headers</param-name>
    <param-value>Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers</param-value>
  </init-param>
  <init-param>
    <param-name>cors.exposed.headers</param-name>
    <param-value>Access-Control-Allow-Origin,Access-Control-Allow-Credentials</param-value>
  </init-param>
  <init-param>
    <param-name>cors.support.credentials</param-name>
    <param-value>true</param-value>
  </init-param>
  <init-param>
    <param-name>cors.preflight.maxage</param-name>
    <param-value>10</param-value>
  </init-param>
</filter>
<filter-mapping>
  <filter-name>CorsFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```

  

### Installation du back

  * Ouvrir une base de donnée mySQL et exécuter le script (CantineBack/ScriptBDD) pour générer la base de donnée du projet (La base de donnée intégrée à WampServer a été utilisée pour ce projet)
  * Ouvrez le projet CantineBack et exécutez la commande `mvn clean install`.
  * Ajoutez une configuration pour le projet et sélectionnez tomcat server Local
  * L'url utilisée est `http://localhost:8080/ProjetJavaEEService/`
  * Dans l'onglet déploiement, ajoutez l'artefact `ProjetJavaEEService:war exploded` et entrez `/ProjetJavaEEService` dans `Application context`
  * *Remarque : tomcat 9.0.19 a été utilisé pour ce projet*
  * Lancez le serveur.
  
  
### Installation du Front

  * Placez le répertoire CantineFront dans le répertoire `www` de votre serveur local.
  * Lancez votre serveur local *Remarque : WampServer a été utilisé pour la réalisation de ce projet*
  * Depuis votre navigateur, accédez à l'adresse `http://localhost/ProjetJavaEEFrontBS/Auth/Connexion`
