# AutoLoc API 🚗

AutoLoc est une application backend de gestion de location de véhicules développée avec **Spring Boot**.

## 📌 Description

L'application permet de gérer les différents éléments d'une agence de location de véhicules :

- Agences
- Véhicules
- Employés
- Clients
- Réservations
- Contrats
- Paiements
- Maintenances
- Équipements

Le projet utilise **Spring Data JPA** pour communiquer avec la base de données MySQL et expose des API REST accessibles via des outils comme Postman.

## 🛠️ Technologies utilisées

- Java 21
- Spring Boot 4.0.8
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Jakarta Validation
- Postman
- IntelliJ IDEA

## 📂 Structure du projet

```text
src/main/java/com/autoloc/api
│
├── domain
│   ├── Agence.java
│   ├── Client.java
│   ├── Contrat.java
│   ├── Employe.java
│   ├── Equipement.java
│   ├── Maintenance.java
│   ├── Paiement.java
│   ├── Reservation.java
│   └── Vehicule.java
│
├── repository
│   ├── AgenceRepository.java
│   └── VehiculeRepository.java
│
└── web
    └── controller
        ├── AgenceController.java
        └── VehiculeController.java
