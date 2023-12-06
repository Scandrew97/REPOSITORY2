package corso.WelcomeToEsports.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	@GetMapping("/viewMatch")
	public ModelAndView viewMatch(@RequestParam("id") Integer id, ModelAndView mod) {
		HashMap <Integer, Matches> matches= c.matches().readIdGames(id);
		for (Integer key : matches.keySet()) {
			String nomeTeamCasa = c.teams().read(matches.get(key).getIdTeamHome()).getNome();
			String nomeTeamAway = c.teams().read(matches.get(key).getIdteamAway()).getNome();
			matches.get(key).setNomeTeamHome(nomeTeamCasa);
			matches.get(key).setNomeTeamAway(nomeTeamAway);			
		}
		mod.addObject("listaMatch", matches);
		return mod;
	}
	@GetMapping("/viewTeams")
	public ModelAndView viewTeams(@RequestParam("id") Integer id, ModelAndView mod) {
		HashMap <Integer, Teams> teams= c.teams().readIdGames(id);
		for (Integer key : teams.keySet()) {
			teams.get(key).getNome();
		}
		mod.addObject("listaTeams", teams);
		return mod;
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
