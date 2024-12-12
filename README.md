# Template Dilemme du Prisonnier

Ce template va avoir deux mains différentes :
* `Main` -> pour une application Java simple
* `PrisonersDilemmaApp` -> le nom complet de la seconde application doit être ici



Pour utilsier le projet : 

Lancer le serveur  : 
à la racine du projet :
mvn clean install -U   puis mvn spring-boot:run

Si vous rencontrez des erreurs, possiblement car trop anciene version de java, utiliser la 21 de préférence.

Pusi lancer le front (à retrouvez dans le dossier prisonners-dilemna-front) : 

ng serve

PS:

Du à la présence d'aléatoire, il se peut parfois que certains tests ne passent pas.

Pour le déploiment : utilisation de railway pour le back à l'adresse https://web-production-d29a4.up.railway.app
                                    et de netlify pour le front à l'adresse : https://candid-liger-6e7a01.netlify.app

Le serveur ne fonctionant que pour une partie en l'état actuel, en cas de mauvaise manip ou de partie deja jouer il faut redémarrer le serveur,
(envoie moi une message je le fais depuis mon téléphone, oui c est pas ouf)

Concernant le partage des stratégies, la tentative est sur la branche share-startegies et non sur main car cela cassait le déploiement et je n'ai pas eu le
temps de régler ca (la facon d ajouter mes jar dans le pom avec le scope system marchait pas pour le déploiment), en local ca tournait presque mais encore quelques problèmes.


