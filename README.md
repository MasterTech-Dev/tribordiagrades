# Tribordia Grades
## Table des matières:
1. **Présentation**
2. **Commandes**
3. **Évènements**
4. **Permissions**
5. **Paramètres**
6. **Groupes**
7. **Langages disponibles**
## Présentation:
> Le plugin est dédié à la gestion des grades et de la langue.  
> Il peut être utilisé comme librairie afin de récupérer la langue sélectionnée par un joueur sans avoir besoin d'utiliser des centaines de lignes de codes.  
> Il est vous est possible de créer autant de grades que souhaités.  
> La configuration est simple et intuitive. Vous pourrez configurer vos grades à l'aide d'un menu si l'option est active dans la configuration.
## Commandes:
> ### Configuration:
> ***/tribordiagrades config setprefix [prefix]* >** Définir le préfix du plugin.  
> ***/tribordiagrades config logs [true/false]* >** Activer / Désactiver les logs.
> ***/tribordiagrades config gui [true/false]* >** Activer / Désactiver les GUI.  
> ***/tribordiagrades lang set [lang]* >** Définir la langue par défaut.  
> ### Gestion des grades:
> ***/grade* >** Ouvrir le menu.  
> ***/grade help* >** Afficher la liste des actions disponibles.  
> ***/grade create [name]* >** Créer un grade.  
> ***/grade delete [name]* >** Supprimer un grade.  
> ***/grade setdefault [name]* >** Définir le grade par défaut.  
> ***/grade setprefix [name] [prefix]* >** Définir le préfix d'un grade.  
> ***/grade setsuffix [name] [suffix]* >** Définir le suffix d'un grade.  
> ***/grade setseparator [name] [separator]* >** Définir le séparateur du chat d'un grade.  
> ***/grade setposition [name] [position]* >** Définir la position dans le "tab" d'un grade.  
> ***/grade addpermission [grade] [permission]* >** Ajouter une permission à un grade.  
> ***/grade removepermission [grade] [permission]* >** Retirer une permission à un grade.  
## Évènements:
- **AsyncPlayerChatEvent**
- **PlayerJoinEvent**
- **PlayerQuitEvent**
- **PlayerInteractEvent**
- **InventoryClickEvent**
## Permissions:
> #### Global:
> ***tribordia.\** >** Attribuer toutes les permissions du plugin.  
> ***tribordia.chat.\** >** Attribuer toutes les permissions relatives au chat.  
> ***tribordia.grades.\** >** Attribuer toutes les permissions relatives aux grades.  
> #### Chat:
> ***tribordia.chat.colors* >** Écrire en couleurs dans le chat.  
> ***tribordia.chat.mentions* >** Mentionner un joueur dans le chat.  
> #### Grades:
> ***tribordia.grades.create* >** Créer un grade.  
> ***tribordia.grades.modify* >** Modifier un grade.  
> ***tribordia.grades.list* >** Afficher la liste des grades.  
> ***tribordia.grades.delete* >** Supprimer un grade.  
## Paramètres:
> ***prefix* :** '&7&l[&6&lTribordia&2&lGrades&7&l]'  
> ***guis* :** true  
> ***logs* :** true  
> ***lang* :** 'fr'  
## Groupes:
#### Groupe par défaut:
> ***prefix* :** '&7'  
> ***suffix* :** ''  
> ***separator* :** '&7&l>'  
> ***position* :** ''
> ***default* :** true  
> ***permissions* :** []  
## Langues disponibles:
> ***fr* >** Langue française  
> ***en* >** Langue anglaise