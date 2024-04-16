package com.example.oblig3aprilny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


//The repository is for connecting to the database
@Repository
public class BillettRepository {

    // Connecting to the database by making a private "db" of the type JdbcTemplate
    @Autowired
    private JdbcTemplate db;


    //This method takes in a ticket, and saves it to the databse
    // the questionmarks will be added with value in the line below
    public void lagreBillett(KinoBilletter innbillett) {
        String sql = "INSERT INTO tabell (film,antall,fornavn,etternavn,telefonnr,epost) VALUES(?,?,?,?,?,?)";
        db.update(sql, innbillett.getFilm(), innbillett.getAntall(), innbillett.getFornavn(), innbillett.getEtternavn(),
                innbillett.getTelefonnr(), innbillett.getTelefonnr());
    }


    // This is where all the tickets will show, and is making a new array called alleBilletter
    public List<KinoBilletter> visBilletter(){
        String sql = "SELECT * FROM tabell order by etternavn"; //tabellen i schema
        List<KinoBilletter> alleBilletter = db.query(sql,new BeanPropertyRowMapper(KinoBilletter.class)); //new list
        return alleBilletter; //returns the new array with all the tickets
    }
    //deleting all the tickets for the database table
    public void slettAlleBilletter(){
        String sql = "DELETE FROM tabell";
        db.update(sql);
    }

}


