package com.example.projet_2_spring_mvc.controller;

import com.example.projet_2_spring_mvc.entity.Emprunt;
import com.example.projet_2_spring_mvc.service.EmpruntService;
import com.example.projet_2_spring_mvc.service.LivreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/emprunts")
public class EmpruntController {

    private final EmpruntService empruntService;
    private final LivreService livreService;

    @Autowired
    public EmpruntController(EmpruntService empruntService, LivreService livreService) {
        this.empruntService = empruntService;
        this.livreService = livreService;
    }

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("emprunts", empruntService.findAll());
        return "emprunts/liste";
    }

    @GetMapping("/nouveau")
    public String formulaireCreation(Model model) {
        model.addAttribute("emprunt", new Emprunt());
        model.addAttribute("livresDisponibles", livreService.findAll().stream()
                .filter(l -> l.isDisponible())
                .toList());
        return "emprunts/formulaire";
    }

    @PostMapping
    public String enregistrer(@Valid @ModelAttribute("emprunt") Emprunt emprunt,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("livresDisponibles", livreService.findAll().stream()
                    .filter(l -> l.isDisponible())
                    .toList());
            return "emprunts/formulaire";
        }
        try {
            empruntService.emprunter(emprunt);
        } catch (IllegalStateException e) {
            bindingResult.reject("livre.indisponible", e.getMessage());
            model.addAttribute("livresDisponibles", livreService.findAll().stream()
                    .filter(l -> l.isDisponible())
                    .toList());
            return "emprunts/formulaire";
        }
        redirectAttributes.addFlashAttribute("message", "Emprunt enregistré avec succès.");
        return "redirect:/emprunts";
    }

    @GetMapping("/retour/{id}")
    public String retourner(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        empruntService.retourner(id);
        redirectAttributes.addFlashAttribute("message", "Retour enregistré.");
        return "redirect:/emprunts";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        empruntService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Emprunt supprimé.");
        return "redirect:/emprunts";
    }
}
