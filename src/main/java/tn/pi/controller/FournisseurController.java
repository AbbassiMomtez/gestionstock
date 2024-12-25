package tn.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.model.Fournisseur;
import tn.pi.service.FournisseurService;

import java.util.List;

@Controller
@RequestMapping("/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public String getAllFournisseurs(Model model) {
        List<Fournisseur> fournisseurs = fournisseurService.getAllFournisseurs();
        model.addAttribute("fournisseurs", fournisseurs);
        return "fournisseurs/fournisseurs";
    }

    @GetMapping("/add")
    public String addFournisseurForm(Model model) {
        model.addAttribute("fournisseur", new Fournisseur());
        return "fournisseurs/add_fournisseur";
    }

    @PostMapping
    public String createFournisseur(@ModelAttribute Fournisseur fournisseur) {
        fournisseurService.createFournisseur(fournisseur);
        return "redirect:/fournisseurs";
    }

    @GetMapping("/edit/{id}")
    public String editFournisseurForm(@PathVariable Long id, Model model) {
        fournisseurService.getFournisseurById(id).ifPresent(f -> model.addAttribute("fournisseur", f));
        return "fournisseurs/edit_fournisseur";
    }

    @PostMapping("/edit/{id}")
    public String updateFournisseur(@PathVariable Long id, @ModelAttribute Fournisseur fournisseur) {
        fournisseurService.updateFournisseur(id, fournisseur);
        return "redirect:/fournisseurs";
    }

    @GetMapping("/delete/{id}")
    public String deleteFournisseur(@PathVariable Long id) {
        fournisseurService.deleteFournisseur(id);
        return "redirect:/fournisseurs";
    }
}
