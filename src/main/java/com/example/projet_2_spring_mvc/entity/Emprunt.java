package com.example.projet_2_spring_mvc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{emprunt.dateEmprunt.obligatoire}")
    @PastOrPresent(message = "{emprunt.dateEmprunt.future}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_emprunt", nullable = false)
    private LocalDate dateEmprunt;

    // Null tant que le livre n'a pas été rendu.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_retour")
    private LocalDate dateRetour;

    // Côté propriétaire de la relation : porte la colonne de jointure (FK livre_id).
    @NotNull(message = "{emprunt.livre.obligatoire}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;

    public Emprunt() {
    }

    public Emprunt(LocalDate dateEmprunt, Livre livre) {
        this.dateEmprunt = dateEmprunt;
        this.livre = livre;
    }

    // --- Getters / Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}
