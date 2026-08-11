package com.example.projet_2_spring_mvc.controller;

import com.example.projet_2_spring_mvc.entity.Livre;
import com.example.projet_2_spring_mvc.service.LivreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livres")
public class LivreController {

    private final LivreService livreService;

    @Autowired
    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public String liste(@RequestParam(name = "recherche", required = false) String recherche, Model model) {
        model.addAttribute("livres",
                (recherche == null || recherche.isBlank())
                        ? livreService.findAll()
                        : livreService.rechercherParTitre(recherche));
        model.addAttribute("recherche", recherche);
        return "livres/liste";
    }

    @GetMapping("/nouveau")
    public String formulaireCreation(Model model) {
        model.addAttribute("livre", new Livre());
        return "livres/formulaire";
    }

    @GetMapping("/modifier/{id}")
    public String formulaireModification(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("livre", livreService.findById(id));
        return "livres/formulaire";
    }

    @PostMapping
    public String enregistrer(@Valid @ModelAttribute("livre") Livre livre,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "livres/formulaire";
        }
        livreService.save(livre);
        redirectAttributes.addFlashAttribute("message", "Livre enregistré avec succès.");
        return "redirect:/livres";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        livreService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Livre supprimé.");
        return "redirect:/livres";
    }
}
