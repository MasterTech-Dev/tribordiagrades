# Tribordia Grades
## Table des matières:
1. **Présentation**
2. **Commandes**
3. **Évènements**
4. **Paramètres**
5. **Groupes**
6. **Langages disponibles**
## Présentation:
> Le plugin est dédié à la gestion des grades et de la langue.  
> Il peut être utilisé comme librairie afin de récupérer la langue sélectionnée par un joueur sans avoir besoin d'utiliser des centaines de lignes de codes.  
> Il est vous est possible de créer autant de grades que souhaités.  
> La configuration est simple et intuitive. Vous pourrez configurer vos grades à l'aide d'un menu si l'option est active dans la configuration.
## Commandes:
> ### Configuration:
> ***/tribordiagrades config setprefix [prefix]* >** Définir le préfix du plugin.  
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
## Paramètres:
> ***prefix* >** '&7&l[&6&lTribordia&2&lGrades&7&l]'
> ***lang* >** 'fr'
> ***gui* >** true
## Groupes:
#### Groupe par défaut:
> ***prefix* >** '&7'
> ***suffix* >** ''
> ***separator* >** '&7&l>'
> ***default* >** true
> ***permissions* >** []