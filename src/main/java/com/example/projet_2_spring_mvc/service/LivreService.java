package com.example.projet_2_spring_mvc.service;

import com.example.projet_2_spring_mvc.entity.Livre;

import java.util.List;

public interface LivreService {

    List<Livre> findAll();

    Livre findById(Long id);

    Livre save(Livre livre);

    void deleteById(Long id);

    List<Livre> rechercherParTitre(String titre);
}
