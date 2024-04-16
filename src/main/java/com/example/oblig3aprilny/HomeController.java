package com.example.oblig3aprilny;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {


    //få tak i kunderepositoryet:
    @Autowired
    private BillettRepository rep; //rep er en ny variabel av typen BillettRepository

    @GetMapping("/hentFilmer")
    public List<String>hentFilmer(){
        List<String> listFilmer = new ArrayList<>();
        listFilmer.add("Interstellar");
        listFilmer.add("Gone Girl");
        listFilmer.add("Shrek 3");
        return listFilmer;
    }

    @PostMapping("/lagre")
    public void lagreBillett (KinoBilletter innKinoBilletter){
        rep.lagreBillett(innKinoBilletter);
    }
    @GetMapping("/vis")
    public List<KinoBilletter> visBilletter(){
        return rep.visBilletter(); //Metoden fra repositoryet!
    }
    @GetMapping("/slett")
    public void slett(){
        rep.slettAlleBilletter(); // metoden fra repositoryet!
    }

}

