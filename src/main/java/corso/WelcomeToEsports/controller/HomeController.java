package corso.WelcomeToEsports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import corso.WelcomeToEsports.context.Context;
import corso.WelcomeToEsports.modelli.Games;

@Controller
public class HomeController {
	@Autowired
	private Context c;

	@Autowired
	private GameController gameController;
	// ./
	// apre la pagina con la lista dei giochi
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView home=new ModelAndView("html/home");
		home.addObject("lista", c.games().readAll());
		return home;
	}

	// @GetMapping("/")
	// public String home(){
	// 	c.games().readAll();
	// 	return "home.html";
	// }
	
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

	@PostMapping("/viewGame")
	public ModelAndView viewGame(@RequestParam("id") Integer id){
		ModelAndView mod = new ModelAndView("html/game");
		mod.addObject("listaTeams", c.teams().readIdGames(id));
		mod.addObject("nomeGioco", c.games().read(id).getTitolo());
		mod.addObject("idGioco", c.games().read(id).getId());
		gameController.viewMatch(id, mod);
		return mod;
	}

}
