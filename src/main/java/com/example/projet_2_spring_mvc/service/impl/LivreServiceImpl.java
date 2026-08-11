package com.example.projet_2_spring_mvc.service.impl;

import com.example.projet_2_spring_mvc.entity.Livre;
import com.example.projet_2_spring_mvc.repository.LivreRepository;
import com.example.projet_2_spring_mvc.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class LivreServiceImpl implements LivreService {

    private final LivreRepository livreRepository;

    @Autowired
    public LivreServiceImpl(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Livre findById(Long id) {
        return livreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Livre introuvable, id=" + id));
    }

    @Override
    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public void deleteById(Long id) {
        livreRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Livre> rechercherParTitre(String titre) {
        return livreRepository.findByTitreContainingIgnoreCase(titre);
    }
}
