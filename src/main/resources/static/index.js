$(function(){
    velgFilm();
});
//får feilmelding fra consollen..

function velgFilm(){
    $.get("/hentFilmer", function(data){
        formaterFilmer(data);
    });
}

function formaterFilmer(data){
    let ut="<select id='valgtFilm'>";
    ut += "<option value='' disabled selected>Velg</option>"; //Standard-valg på nedtrekksmenyen
    for (const film of data) {
        ut += "<option value='" + film + "'>" + film + "</option>";

    }
    ut+="</select>";
    $("#filmene").html(ut);
}





//Plasserer arrayet <div>-tagg i index.html der arrayet med billettene blir presentert
// document.getElementById("visAlleBilletter").innerHTML=ut;
//}



function bestill() {
    // valideringsfeil-boolean for å få if-setningene riktig og at feilmeldinger skal slettes
    // når man fortsetter å fylle ut
    let valideringsfeil = false;
    const billetter = {
        film: $("#valgtFilm").val(),
        antall: $("#antall").val(),
        fornavn: $("#fornavn").val(),
        etternavn: $("#etternavn").val(),
        telefonnr: $("#telefonnr").val(),
        epost: $("#epost").val()
    };



    // valideringsjekk med if-setninger. Booleanen nederst i hver hindrer billettene å registreres
    // om inputen ikke er gyldig

    if (billetter.film === "Velg") {
        $("#feilFilm").html("Feil, velg en film.");
        valideringsfeil = true;
    }


    if (isNaN(billetter.antall) || billetter.antall < 1) {
        $("#feilAntall").html("Feil, velg antall billetter.");
        valideringsfeil = true;
    }

    if (billetter.fornavn.trim() === "") {
        $("#feilFornavn").html("Feil, ugyldig fornavn.");
        valideringsfeil = true;
    }

    if (billetter.etternavn.trim() === "") {
        $("#feilEtternavn").html("Feil, ugyldig etternavn.");
        valideringsfeil = true;
    }
    if (isNaN(billetter.telefonnr) || billetter.telefonnr.trim() === "") {
        $("#feilTelefonnr").html("Feil, manglende eller ugyldig telefonnr!");
        valideringsfeil = true;
    }
    if (billetter.epost.trim() === "" || !/\S+@\S+\.\S+/.test(billetter.epost)) {
        $("#feilEpost").html("Feil, ugyldig eller manglende e-post.");
        valideringsfeil = true;
    }

    // Flytter opbjektet inn i serveren!
    if (!valideringsfeil) {
        $.post("/lagre", billetter, function () {
            console.log("Sendte objekt til tjener. ", billetter);
            visBilletter();
        });

        // Tømmer skrivefeltene når billetene vises under
        document.getElementById("valgtFilm").value = "";
        document.getElementById("antall").value = "";
        document.getElementById("fornavn").value = "";
        document.getElementById("etternavn").value = "";
        document.getElementById("telefonnr").value = "";
        document.getElementById("epost").value = "";
    }
}


// Bestill-funksjonen avsluttes med at VisBilletter-funksjonen kjøres
function visBilletter() {
    $.get("/vis", function(data) {
        formaterData(data);
    })
}


function formaterData(data) {
    let ut = "<table class='table table-striped table-bordered' ><tr>" + "<th>Film</th><th>Antall billetter</th><th>Fornavn</th><th>Etternavn</th><th>Telefonnr</th><th>Epost</th>" + "</tr>";
    for (let p of data) {
        ut += "<tr>";
        ut += "<td>" + p.film + "</td><td>" + p.antall + "</td><td>" + p.fornavn + "</td><td>" + p.etternavn + "</td><td>" + p.telefonnr + "</td><td>" + p.epost + "</td>";
    }
    ut += "</tr>";
    $("#visAlleBilletter").html(ut);


    // Tømmer feilmeldinger når de er blitt rettet opp i
    $("#feilFilm").html("");
    $("#feilAntall").html("");
    $("#feilFornavn").html("");
    $("#feilEtternavn").html("");
    $("#feilTelefonnr").html("");
    $("#feilEpost").html("");

}





// funksjon for å velge antall
function antall(){
    document.getElementById("antall").value;
}



// funksjon for å slette hele arrayet fra serveren, med en alert-melding fulgt av en if-setning som sletter
function slettBilletter(){
    const ok=confirm("OBS! Alle billettene vil slettes. Er du sikker?");
    if(ok) {
        $.get("/slett", function () {
            visBilletter();
        })
    }

}












