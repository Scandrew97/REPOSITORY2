package corso.WelcomToEsports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import corso.WelcomToEsports.Context;
import corso.WelcomeToEsports.modelli.Games;

@Controller
public class HomeController {
	@Autowired
	private Context c;
	// ./
	// apre la pagina con la lista dei giochi
	@GetMapping("/")
	public String home() {
		c.games().readAll();
		return "home.html";
	}
	
	// Opzionale mapping per aprire una pagina con 
	// form di inserimento nuovo games
	
	// ./newGames
	// che aggiunge il nuovo gioco al DB(insert into GAMES)
	// 1opzione: aprire una pagina di conferma inserimento
	// 2opzione: redirect:/
	@PostMapping("/newGame")
	public String newGame(Games g) {
		c.games().create(g);
		return "redirect:/";
	}
	
	
	
	

}
