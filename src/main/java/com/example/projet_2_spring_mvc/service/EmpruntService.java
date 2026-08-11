package com.example.projet_2_spring_mvc.service;

import com.example.projet_2_spring_mvc.entity.Emprunt;

import java.util.List;

public interface EmpruntService {

    List<Emprunt> findAll();

    Emprunt findById(Long id);

    // Crée l'emprunt et marque le livre comme indisponible
    Emprunt emprunter(Emprunt emprunt);

    // Fixe la date de retour à aujourd'hui et remet le livre disponible
    void retourner(Long empruntId);

    void deleteById(Long id);
}
