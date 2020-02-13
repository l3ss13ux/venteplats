# Mon application *venteplats*

## Logiciels à installer
* IntelliJ (ou un autre IDE du même style)
* WAMP ou au moins un SGBD (style MySQL)
* Postman (pour simuler les requêtes HTTP)
* Git Bash (pour lancer les commandes et envoyer notre code sur le repository GitHub)
* Git Kraken (ou un autre client Git juste pour voir les branches et les commit)
* avoir un compte GitHub

## Liaison avec mon SGBD (dans mon cas c'est MySQL)
Voici les informations que j'ai mis dans mon fichier "application.properties" :

    spring.datasource.url=jdbc:mysql://localhost:3308/vente_plats?serverTimezone=UTC
    spring.datasource.username=helene
    spring.datasource.password=toto
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
    spring.jpa.hibernate.ddl-auto=update
    server.port=8081

Libre à vous d'organiser votre fichier comme bon vous semble (avec des commentaires pour plus de précisions ou des
sauts de lignes).
Ici, j'y ai précisé le nom de ma base de données (vente_plats), le nom (helene) et le mot de passe (toto) d'un
utilisateur ayant accès à ma base de données et le port pour se connecter à la base de données (8081). Pour plus
 d'information concernant les propriétés que l'on peut mettre dans notre fichier "applications.properties" je vous
 invite à vous rendre sur
 [le lien suivant](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html).




