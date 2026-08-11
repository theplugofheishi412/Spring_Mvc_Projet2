package com.example.projet_2_spring_mvc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{livre.titre.obligatoire}")
    @Column(nullable = false)
    private String titre;

    @NotBlank(message = "{livre.auteur.obligatoire}")
    @Column(nullable = false)
    private String auteur;

    @NotBlank(message = "{livre.isbn.obligatoire}")
    @Pattern(regexp = "^[0-9-]{10,17}$", message = "{livre.isbn.invalide}")
    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private boolean disponible = true;

    // Côté "inverse" de la relation : un livre peut avoir plusieurs emprunts.
    // mappedBy = "livre" -> c'est Emprunt.livre qui possède la colonne de jointure (FK).
    // cascade = ALL : supprimer/persister un Livre propage aux Emprunts liés (adapter si besoin).
    // orphanRemoval = true : retirer un Emprunt de la liste le supprime de la base.
    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Emprunt> emprunts = new ArrayList<>();

    public Livre() {
    }

    public Livre(String titre, String auteur, String isbn) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    // --- Méthode utilitaire pour garder les deux côtés de la relation synchronisés ---
    public void ajouterEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
        emprunt.setLivre(this);
    }

    public void retirerEmprunt(Emprunt emprunt) {
        emprunts.remove(emprunt);
        emprunt.setLivre(null);
    }

    // --- Getters / Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }
}
