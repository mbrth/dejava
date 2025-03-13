package com.monprojet;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty email;

    public User(int id, String nom, String prenom, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
    }

    // Getters (obligatoires pour TableView)
    public int getId() {
        return id.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getPrenom() {
        return prenom.get();
    }

    public String getEmail() {
        return email.get();
    }

    // Setters (si on veut modifier les valeurs)
    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    // Obligatoire pour JavaFX TableView
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }
}
