package com.cma.gestion.web;
												
import com.cma.gestion.data.Infodata;
import com.cma.gestion.entity.Agriculteur;
import com.cma.gestion.entity.Commande;
import com.cma.gestion.entity.Produit;
import com.cma.gestion.repository.AgriculteurRepository;
import com.cma.gestion.repository.CommandeRepository;
import com.cma.gestion.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Set;

@Controller
public class WebController {
    @Autowired
    private AgriculteurRepository agriculteurRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @GetMapping("/")
    public String accueil() {
    	return "accueil";
    }

    @GetMapping("/listag")
    public String listAgri(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
                           @RequestParam(name = "size", defaultValue = "3") int s,
                           @RequestParam(name = "rech", defaultValue = "") String mot) {
        Page<Agriculteur> agriclteurPage = agriculteurRepository.rechereche("%" + mot + "%", PageRequest.of(p, s));

        model.addAttribute("ListAgriculteur", agriclteurPage.getContent());
        int[] pages = new int[agriclteurPage.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourant", p);
        model.addAttribute("size", s);
        model.addAttribute("rech", mot);
        return "agriculteur";
    }

    @RequestMapping(value = "/formagri", method = RequestMethod.GET)
    public String formag(Model model) {
        model.addAttribute("agriculteur", new Agriculteur());
        return "formagri";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Agriculteur agriculteur, BindingResult res, Model model) {
        if (res.hasErrors()) {
            return "formagri";

        }
        agriculteurRepository.save(agriculteur);
        return "confirmation";
    }

    @RequestMapping(value = "/sup", method = RequestMethod.GET)
    public String suppression(int id) {
        agriculteurRepository.deleteById(id);
        return "redirect:/listag";
    }

    @RequestMapping(value = "/modif", method = RequestMethod.GET)
    public String modification(Model model, int id) {
        Agriculteur ag = agriculteurRepository.findById(id).get();
        model.addAttribute("agriculteur", ag);
        return "modif";

    }

    @RequestMapping(value = "/modifag", method = RequestMethod.POST)
    public String savemodif(@Valid Agriculteur agriculteur, BindingResult res, Model model) {
        if (res.hasErrors()) {
            return "formagri";

        }
        agriculteurRepository.save(agriculteur);
        return "confirmation";
    }

    //ajoute   a  2 table  produit  et  commande  //
    
    @RequestMapping(value = "/ajout", method = RequestMethod.GET)
    public String ajouteprod(Model model, Integer id) {
        model.addAttribute("produit", new Produit());

        model.addAttribute("agrid", id);

        return "ajouterProd";
    }

    @RequestMapping(value = "/ajoutprod", method = RequestMethod.POST)
    public String saveprod(@ModelAttribute Produit produit) {
        Integer id = produit.getIdProduit();
        Date au = new Date();
        produit.setDate(au);
        produit.setIdProduit(null);
        produit = produitRepository.save(produit);

        Commande cmd = new Commande();
        cmd.setAgriculteur(agriculteurRepository.findById(id).get());
        cmd.setProduit(produit);
        commandeRepository.save(cmd);
        return "redirect:/listag";
    }

    @RequestMapping(value = "/stat", method = RequestMethod.GET)
    public String stat(Model model) {
        model.addAttribute("totale", produitRepository.totaleProduit());
        model.addAttribute("nbg", agriculteurRepository.NbAg());
        model.addAttribute("cmdnb", commandeRepository.nbcommande());
        model.addAttribute("min", produitRepository.minProduit());
        model.addAttribute("max", produitRepository.maxProduit());
        model.addAttribute("nbp",produitRepository.nbProduit());
        Set<Infodata> dag =agriculteurRepository.info();
        model.addAttribute("ListAgriculteur",dag);
        return "statcma";
    }
    @RequestMapping(value = "/supproduit", method = RequestMethod.GET)
    public String supp(int id) {
    	
    	Integer res = Integer.valueOf(id);	
    	produitRepository.deleteById(res);

        return "redirect:/statcma";
    }


}
