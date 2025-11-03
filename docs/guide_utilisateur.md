## 🧠 Guide Utilisateur – IA de simulation d'entretien d'embauche
### 🎯 Description générale

Ce projet a pour but de proposer un simulateur d'entretien d'embauche intelligent, capable de reproduire les conditions réelles d’un entretien à partir du CV et de l’offre d’emploi du candidat.
L’objectif est d’aider les utilisateurs à s’entraîner, gagner en confiance, et améliorer leurs performances face aux recruteurs.
---
### ⚙️ Fonctionnement

1) Lancement du simulateur  
L’utilisateur démarre l’application via l'exécutable qui se trouve dans le dossier application :  
   - **lancement_windows.bat** pour les appareils **Windows**  
   - **lancement_linux.sh** pour les appareils **Linux**
2) Sélection des fichiers  
L’agent demande de sélectionner :  
   - Un CV au format PDF ou texte.  
   - Une offre d’emploi au format PDF ou texte.

3) Simulation de l’entretien  
L’agent génère des questions personnalisées en s’appuyant sur :
   - Le contenu du CV (expériences, compétences, formation).
   - Les attentes de l’offre d’emploi.
   - Les réponses précédentes du candidat.

4) Interaction avec l’utilisateur  
L’utilisateur saisit sa réponse directement dans la console.  
Optionnellement, il peut demander un feedback immédiat après chaque réponse (suggestions, axes d’amélioration).

5) Évaluation finale  
À la fin de la simulation, un rapport d’évaluation est généré :  
   - Une note globale sur la performance.
   - Des points forts identifiés.
   - Des recommandations personnalisées pour progresser.
---
### 🧩 Architecture fonctionnelle simplifiée
```
Utilisateur
   ↓
ConsoleUi  → Interface en ligne de commande
   ↓
InterviewAgent  → Gestion du déroulement de l'entretien
   ↓
FeedBackModule  → Fournit des conseils personnalisés
   ↓
EvaluationAgent → Génère un rapport et une note finale
   ↓
OllamaClient    → Communication avec le modèle d'IA
```
---
### 👥 Public cible

Ce simulateur s’adresse à :  
   - Toute personne préparant un entretien d’embauche.
   - Les étudiants ou jeunes diplômés souhaitant s’entraîner.
   - Les professionnels en reconversion ou en recherche d’emploi.
---
### 💡 Objectifs pédagogiques

- Offrir une expérience d’entretien réaliste et interactive.
- Permettre à l’utilisateur d’évaluer ses compétences comportementales et de communication.
- Fournir des retours constructifs pour s’améliorer.
- Proposer un accompagnement intelligent dans la préparation d’un entretien.
---
### 🔧 Utilité et bénéfices
- Gagner en confiance avant un entretien réel.
- Identifier ses points faibles et les corriger.
- Optimiser son discours et sa structure de réponse.
- Suivre sa progression à travers les entretiens simulés.
---
### 🚀 Améliorations possibles
- Enrichir le feedback avec des conseils basés sur la méthode STAR (Situation, Tâche, Action, Résultat).
- Ajouter un historique des entretiens pour suivre les progrès dans le temps.
- Intégrer une interface graphique (GUI) pour une utilisation plus intuitive.
- Proposer un mode recruteur pour évaluer plusieurs candidats.
- Implémenter un système de profil utilisateur pour adapter le niveau de difficulté.
---
### 🧭 Exemples d’utilisation
Exemple 1 — Simulation simple
```
Chemin du fichier 1 : /Users/antoine/Documents/CV.pdf
Chemin du fichier 2 : /Users/antoine/Documents/offre_dev_java.pdf
Souhaitez-vous un feedback après chaque réponse ? (y/n)
> y
```
➡️ L’agent pose une première question comme :
```
Question : Pouvez-vous me décrire un projet où vous avez travaillé en équipe ?
> Votre réponse : J’ai participé à un projet de développement d’une application mobile en Java.
```
💬 Feedback :
```
- Mettez davantage en avant votre rôle personnel.
- Donnez des résultats chiffrés ou des indicateurs de succès.
```

Exemple 2 — Évaluation finale

À la fin de l’entretien :
```
===== Bilan final =====
Score global : 82 / 100
Points forts : communication claire, bonnes motivations
Axes d’amélioration : approfondir les exemples techniques
=======================
```
