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
idCreateur ayant accès à ma base de données et le port pour se connecter à la base de données (8081). Pour plus
 d'information concernant les propriétés que l'on peut mettre dans notre fichier "applications.properties" je vous
 invite à vous rendre sur
 [le lien suivant](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html).

## Règles métier de l'application

Cette section présente les règles métier implémentées dans l'application VentePlats pour aider les nouveaux développeurs à comprendre la logique fonctionnelle.

### Règles de validation des données

#### Pour les Plats
* **Nom** : obligatoire, entre 3 et 27 caractères
* **Type** : obligatoire (ex: entrée, plat, dessert)
* **Prix** : obligatoire et ne peut pas être égal à 0
* **Date de disponibilité** : obligatoire et doit être dans le futur
* **Description** : optionnelle (valeur par défaut vide)
* **Créateur** : un plat doit obligatoirement être associé à un utilisateur

#### Pour les Utilisateurs
* **Nom** : obligatoire, entre 3 et 27 caractères
* **Date d'anniversaire** : doit être dans le passé

### Règles d'authentification et autorisation

#### Authentification
* Toutes les requêtes nécessitent un header `idCurrentUser`
* L'utilisateur doit exister en base de données pour effectuer des actions
* Si l'utilisateur n'existe pas → erreur 401 (Unauthorized)

#### Autorisation (qui peut faire quoi)
* Un utilisateur ne peut **supprimer** que ses propres plats
* Un utilisateur ne peut **modifier** que ses propres plats  
* Un utilisateur ne peut **consulter** que ses propres plats (endpoint `/plats/utilisateur/{id}`)
* Un utilisateur ne peut **supprimer** que son propre compte
* Un utilisateur ne peut **modifier** que son propre profil
* Toute tentative d'action non autorisée → erreur 403 (Forbidden)

### Règles de cohérence des données

#### Cascade et suppression
* Quand un utilisateur est supprimé, tous ses plats sont automatiquement supprimés
* La date de création des plats est générée automatiquement

#### Gestion des erreurs
* Si un plat demandé n'existe pas → erreur 404 (Not Found)
* Si un utilisateur demandé n'existe pas → erreur 404 (Not Found)

### Règles de recherche et filtrage

* Les plats peuvent être filtrés par **type** (égalité exacte)
* Les plats peuvent être filtrés par **prix maximum**
* Les plats peuvent être filtrés par **date de disponibilité** (avant une date donnée)
* Endpoint dédié : `/plats/filtre/date?disponibleAvant={dateTime}`




