package com.example.projet_2_spring_mvc.service.impl;

import com.example.projet_2_spring_mvc.entity.Emprunt;
import com.example.projet_2_spring_mvc.entity.Livre;
import com.example.projet_2_spring_mvc.repository.EmpruntRepository;
import com.example.projet_2_spring_mvc.repository.LivreRepository;
import com.example.projet_2_spring_mvc.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EmpruntServiceImpl implements EmpruntService {

    private final EmpruntRepository empruntRepository;
    private final LivreRepository livreRepository;

    @Autowired
    public EmpruntServiceImpl(EmpruntRepository empruntRepository, LivreRepository livreRepository) {
        this.empruntRepository = empruntRepository;
        this.livreRepository = livreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Emprunt> findAll() {
        // Use a fetch join to initialize associated Livre entities to avoid LazyInitializationException in JSP views
        return empruntRepository.findAllWithLivre();
    }

    @Override
    @Transactional(readOnly = true)
    public Emprunt findById(Long id) {
        return empruntRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Emprunt introuvable, id=" + id));
    }

    @Override
    public Emprunt emprunter(Emprunt emprunt) {
        // On recharge le livre géré par JPA (celui du formulaire n'a que l'id renseigné)
        Livre livre = livreRepository.findById(emprunt.getLivre().getId())
                .orElseThrow(() -> new NoSuchElementException("Livre introuvable"));

        if (!livre.isDisponible()) {
            throw new IllegalStateException("Ce livre n'est pas disponible actuellement.");
        }

        livre.setDisponible(false);
        emprunt.setLivre(livre);
        return empruntRepository.save(emprunt);
    }

    @Override
    public void retourner(Long empruntId) {
        Emprunt emprunt = findById(empruntId);
        emprunt.setDateRetour(LocalDate.now());
        emprunt.getLivre().setDisponible(true);
    }

    @Override
    public void deleteById(Long id) {
        empruntRepository.deleteById(id);
    }
}
