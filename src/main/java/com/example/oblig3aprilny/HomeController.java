package com.example.oblig3aprilny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {


    //Connect to the repository:
    @Autowired
    private BillettRepository rep;


    // Array for dropdown menu with all the available movies:
    @GetMapping("/hentFilmer")
    public List<String>hentFilmer(){
        List<String> listFilmer = new ArrayList<>();
        listFilmer.add("Interstellar");
        listFilmer.add("Gone Girl");
        listFilmer.add("Shrek 3");
        return listFilmer;
    }

    // 3 methods for saving, showing and deleting tickets:

    @PostMapping("/lagre")
    public void lagreBillett (KinoBilletter innKinoBilletter){
        rep.lagreBillett(innKinoBilletter);
    }
    @GetMapping("/vis")
    public List<KinoBilletter> visBilletter(){
        return rep.visBilletter(); //visBilletter() is a method from the reposity
    }
    @GetMapping("/slett")
    public void slett(){
        rep.slettAlleBilletter(); //visBilletter() is a method from the reposity
    }

}

