function pulisci_home(){
    document.getElementById("titolo").value="";
}

function chiudi_home(){
    document.getElementById("home-mod-back").style.display="none";
    pulisci_home();
}

function chiudi_home2(){
    document.getElementById("home-mod-back2").style.display="none";
    pulisci_home();
}

function pulisci_team(){
    document.getElementById("nome").value="";
    document.getElementById("nazione").value="";
}

function chiudi_team(){
    document.getElementById("team-mod-back").style.display="none";
    pulisci_team();
}
function chiudi_team2(){
    document.getElementById("team-mod-back2").style.display="none";
    pulisci_team();
}

function pulisci_match(){
    document.getElementById("dataMatch").value="";
    document.getElementById("pointsHome").value="";
    document.getElementById("pointsAway").value="";
}

function chiudi_match(){
    document.getElementById("match-mod-back").style.display="none";
    pulisci_match();
}

function pulisci_player(){
    document.getElementById("nickname").value="";
    document.getElementById("nome").value="";
    document.getElementById("cognome").value="";
    document.getElementById("dataNascita").value="";
    document.getElementById("gamesRole").value="";
}

function chiudi_player(){
    document.getElementById("player-mod-back").style.display="none";
    pulisci_player();
}

function chiudi_player2(){
    document.getElementById("player-mod-back2").style.display="none";
    pulisci_player();
}
