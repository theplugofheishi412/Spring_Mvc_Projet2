package com.example.projet_2_spring_mvc.repository;

import com.example.projet_2_spring_mvc.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivreRepository extends JpaRepository<Livre, Long> {

    Optional<Livre> findByIsbn(String isbn);

    List<Livre> findByDisponibleTrue();

    // Requête dérivée du nom de méthode : recherche insensible à la casse sur le titre
    List<Livre> findByTitreContainingIgnoreCase(String titre);
}
