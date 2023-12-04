package corso.WelcomeToEsports.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import corso.WelcomeToEsports.context.Context;
import corso.WelcomeToEsports.modelli.Matches;
import corso.WelcomeToEsports.modelli.Teams;

@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	private Context c;
	
	// Apriamo la pagina del gioco scelto, avendo quindi un parametro in
	// input che dalla pagina home che ci dice quale gioco aprire e con quali
	@GetMapping("/")
	public String openGame(@RequestParam HashMap<String, String> params, Model model) {
		model.addAttribute("idGames", params.get("idGames"));
		c.games().readAll();
		return "game.html";
	}

	// modale per inserire un nuovo team 
	@PostMapping("/addTeam")
	public String addNewTeam(Teams t) {
		c.teams().create(t);
		return "redirect:/game/";
	}

	// modale per inserire un nuovo match 
	@PostMapping("/addMatch")
	public String addNewMatch(Matches m) {
		c.matches().create(m);
		return "redirect:/game/";
	}




}
