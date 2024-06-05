# Projet de Génie Logiciel

## Organisation globale

- [Trello](https://trello.com/invite/espacedetravail99567008/ATTI49bfb43e7e76600b48407b52b617bc83340B516E)
- [Bruntdown Chart des sprints](https://docs.google.com/spreadsheets/d/12kLXkiJ2ryTRIxXRqjjd3rBuwMot19uwSo-VC-IIk34/edit?usp=sharing)

- Pour planifier les sprints, nous avons choisi de travailler avec la méthode Scrum. Nous avons donc décidé de faire 3 sprints de 1 semaine. Nous avons décidé de faire 3 sprints pour ce projet.
- Le troisième sprint concerne les demandes qui n'ont pas pu être réalisées lors des deux premiers sprints (il n'y aura donc pas de release pour le sprint 3).
- Pour les deux premiers sprints, nous avons essayé de prendre les demandes essentielles en premier et faire en sorte que le sprint ne soit pas trop conséquent. Nous avons une trentaine d'heures estimée par sprint.
- Avant chaque sprint, nous prenions le temps de nous réunir afin de réfléchir ensemble aux tâches à mettre dans le sprint à venir.
- Pour les estimations, nous en discutions lors de la rédaction des US.
- Lorsque nous prenions des US à réaliser, nous la déplacions dans la colonne "En cours" sur Trello et nous nous assignons dessus.
- Nous avons une colonne d'archive pour chaque sprint, cela nous permet de voir ce qui a été fait lors de chaque sprint.
- Nous avons également une colonne "À faire" pour les US qui n'ont pas été réalisées lors du sprint.
- C'est une fois le développement validé que nous déplacions l'US dans la colonne "Terminé" et que nous mettions la chart à jour.

## Organisation git

- Nous travaillons avec git et github pour la gestion de version.
- Notre repository contient deux branches principales : main et develop.
- La branche main est la branche de production, elle contient le code propre.
- La branche develop est la branche de développement, elle contient le code en cours de développement.
- Pour chaque US, nous créons une branche spécifique à partir develop. Le nommage de cette branche était conventionné : `US-<numéro de l'US>/<destination>/<petite_description>`. Cela permet de savoir à quelle US correspond la branche.
- Une fois l'US terminée, nous faisons une pull request de la branche de l'US vers la branche develop. Il fallait au moins une review pour pouvoir merger la branche.
- Chaque commit devait être clair et concis. Il devait contenir une description de ce qui a été fait. `US-<numéro de l'US> : <description>`
- Une fois la branche mergée, nous supprimions la branche de l'US. Et les membres du groupe sur leur branche pouvaient pull la branche develop pour récupérer les modifications.
- À la fin de chaque sprint, nous faisions un merge de la branche develop vers la branche main en ajoutant un tag correspondant au numéro du sprint. Cela permet d'avoir des releases propres sur le dépot.

## Git Groupe

- Julie Barthet : jbrht (deux utilisateurs avec ce nom car il y a eu un souci d'association entre git et GitHub
- Maxime Brasley : maxime-brsl
- Jade Fischer : jadefischr
- A noter que les commits dans contributors ne sont pas à jour et nous ignorons la raison

## Organisation du projet

- Nous avons décidé d'utiliser le framework Spring pour ce projet, car il est très complet et facilite la manipulation d'une base de données.
- Les entity sont les classes qui représentent les tables de la base de données.
- Les repository sont les classes qui permettent de manipuler les données de la base de données.
- Les services sont les classes qui se servent du repository pour effectuer des actions sur la base de données.
- Nous avons également utilisé flyway pour la gestion des migrations de la base de données.

## Installation

Si vous avez des difficultés à lancer le projet, voici quelques étapes pour vous aider :
- Après avoir récupéré le projet, activer postgresql avec le terminal
 - brew services start postgresql
- Retourner dans IntelliJ, onglet Database à droite
 - `+` > Data Source > PostgreSQL
 - à la fin de l'URL, mettre 'postgres'
 - clic droit sur postgres@localhost > new > database > nom = location_borne
- Lancer le launcher

## Contact
- Julie Barthet : julie.barthet1@etu.univ-lorraine.fr 
- Maxime Brasley : maxime.brasley3@etu.univ-lorraine.fr
- Jade Fischer : jade.fischer5@etu.univ-lorraine.fr
