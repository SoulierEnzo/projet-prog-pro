# 🧠 Simulateur d’entretien avec IA

**Document de suivi de projet**
**Auteur :** [Ton Nom]
**Encadrant :** [Nom de l’enseignant]
**Date :** Octobre 2025

---

## 🎯 Contexte et objectifs

Le projet **Simulateur d’entretien avec IA** vise à créer une application Java Spring Boot permettant de simuler un entretien professionnel entre un **utilisateur** (candidat) et une **intelligence artificielle** (intervieweur).
Le but est de reproduire les conditions d’un entretien réel afin de **préparer le candidat** et d’**analyser ses réponses** via des modules intelligents (agents).

Le système repose sur plusieurs agents :

* **InterviewAgent** : conduit l’entretien et gère la logique des questions.
* **EvaluationAgent** : évalue la qualité des réponses du candidat.
* **FeedbackModule** : fournit un retour constructif basé sur l’évaluation.
* **Memory** : stocke l’historique et le contexte.
* **FileLoader** *(ajouté récemment)* : gère les fichiers fournis par l’utilisateur (ex. CV) et les convertit en **Base64** pour les transmettre à l’IA.
* **OllamaClient** : interface avec le modèle IA (type LLM local ou distant).

L’objectif final est d’obtenir une **application console fonctionnelle**, extensible et documentée, simulant un échange humain/IA crédible et personnalisable.

---

## 🧩 Étapes réalisées

| Étape | Description                                                                                                                                 | Statut      | Date      |
| :---- | :------------------------------------------------------------------------------------------------------------------------------------------ |:------------|:----------|
| 1     | Initialisation du projet Gradle + configuration Spring Boot (Java 21, Checkstyle, Jacoco, Spotbugs, Google Format)                          | ✅ Terminé   | séance 1 |
| 2     | Conception UML des agents principaux (InterviewAgent, EvaluationAgent, FeedbackModule, Memory, OllamaClient, Question, Response, ConsoleUI) | ✅ Terminé   | séance 1 |
| 3     | Ajout du `FileLoader` dans le diagramme UML et intégration prévue pour gérer les fichiers utilisateurs                                      | ✅ Terminé   | séance 2 |
| 4     | Implémentation des classes de base et des squelettes de méthodes                                                                            | ✅ Terminé   | séance 2 |
| 5     | Développement du flux principal de simulation (Console → Agents → IA → Feedback)                                                            | 🕓 En cours | séance 3 |
| 6     | Intégration de l’IA via OllamaClient (connexion locale)                                                                                     | 🕓 En cours | séance 3 |
| 7     | Phase de test, analyse de couverture et vérification Checkstyle                                                                             | ⏳ À venir   | séance 4 |
| 8     | Rapport final et soutenance                                                                                                                 | ⏳ À venir   | séance 4 |

---

## 📊 Suivi d’avancement

| Domaine                                         | Avancement | Commentaire                                          |
| :---------------------------------------------- |:-----------|:-----------------------------------------------------|
| Configuration du projet                         | 🟩 100%    | Gradle, plugins, et structure validés                |
| Architecture UML                                | 🟩 100%    | Diagramme clair et validé avec ajout du `FileLoader` |
| Implémentation des agents                       | 🟨 50%     | Classes en place, logique à compléter                |
| Interaction IA (Ollama)                         | 🟥 0%      | Dépend de l’étape suivante                           |
| Interface console                               | 🟨 30%     | Entrée utilisateur a testé, logique à enrichir       |
| Tests et qualité (Jacoco, Spotbugs, Checkstyle) | 🟨 20%     | Configurés mais non exécutés sur code complet        |
| Documentation & rapport                         | 🟩 80%     | Suivi de projet et diagrammes prêts                  |

---

## 🕒 Diagramme de Gantt simplifié (Semaine 1 → 7)

```
séance :     1       2       3        4   
-----------------------------------------------------------
Initialisation [#####]                
UML / Design   [#####]                   
Implémentation        [######]
IA / Ollama               [#--------]
Tests / Qualité       [###----------]
Rapport final                         [#--]
-----------------------------------------------------------
# : travail effectué
- : reste à faire
```

---

## 🔍 Prochaines étapes

1. Implémenter la logique des agents (InterviewAgent ↔ EvaluationAgent).
2. Connecter OllamaClient à une IA locale (test avec modèle de test).
3. Exécuter les tests automatiques et générer les rapports Jacoco.
4. Finaliser la documentation technique et la démonstration.

---

## 📚 Livrables

* Code source Java (Gradle Project)
* Diagrammes UML (PlantUML)
* Rapport technique et suivi de projet
* Tests unitaires et rapport de couverture
* Démonstration console

---

**Statut actuel du projet :** 🟡 *En développement intermédiaire*
**Prochaine revue prévue :** fin de la séance 3
