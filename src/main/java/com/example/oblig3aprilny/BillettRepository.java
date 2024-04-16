package com.example.oblig3aprilny;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

//I repositoryet skal vi koble til databasen
@Repository
public class BillettRepository {

    //Knytter til databasen ved å opprette et objekt kalt db av typen JdbcTemplate
    @Autowired
    private JdbcTemplate db;




    //Det kommer en billett inn i denne metoden, som skal lagres i databasen
    //Spørsmålstegnene i spørringen vil bli fylt inn  i linjen under, der parameterne taes inn
    public void lagreBillett(KinoBilletter innbillett) {
        String sql = "INSERT INTO tabell (film,antall,fornavn,etternavn,telefonnr,epost) VALUES(?,?,?,?,?,?)";
        db.update(sql, innbillett.getFilm(), innbillett.getAntall(), innbillett.getFornavn(), innbillett.getEtternavn(),
                innbillett.getTelefonnr(), innbillett.getTelefonnr());
    }


    // Her skal alle billetter vises, i en liste, vi oppretter en ny liste her:
    public List<KinoBilletter> visBilletter(){
        String sql = "SELECT * FROM tabell order by etternavn"; //tabellen i schema
        List<KinoBilletter> alleBilletter = db.query(sql,new BeanPropertyRowMapper(KinoBilletter.class)); //ny liste!
        return alleBilletter; //returnerer den nye listen med alle billetter
    }

    public void slettAlleBilletter(){
        String sql = "DELETE FROM tabell";
        db.update(sql);
    }

}


