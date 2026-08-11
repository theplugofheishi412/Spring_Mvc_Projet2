package com.example.projet_2_spring_mvc.repository;

import com.example.projet_2_spring_mvc.entity.Emprunt;
import com.example.projet_2_spring_mvc.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    List<Emprunt> findByLivre(Livre livre);

    // Emprunts en cours = pas encore de date de retour
    List<Emprunt> findByDateRetourIsNull();

    @Query("select e from Emprunt e join fetch e.livre")
    List<Emprunt> findAllWithLivre();
}
