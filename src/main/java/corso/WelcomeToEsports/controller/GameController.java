package corso.WelcomeToEsports.controller;

import java.sql.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import corso.WelcomeToEsports.context.Context;
import corso.WelcomeToEsports.modelli.Games;
import corso.WelcomeToEsports.modelli.Matches;
import corso.WelcomeToEsports.modelli.Teams;

@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	private Context c;
	
	@Autowired
	private TeamController teamController;

	@GetMapping("/viewTeams")
	public ModelAndView viewTeams(@RequestParam("id") Integer id, ModelAndView mod) {
		// mod.setViewName("html/game");
		HashMap <Integer, Teams> teams= c.teams().readIdGames(id);
		for (Integer key : teams.keySet()) {
			teams.get(key).getNome();
		}
		mod.addObject("listaTeams", teams);
		// if (mod.getModelMap().getAttribute("idGioco") == null) {
		// 	mod.addObject("idGioco", id);
		// }
		// if (mod.getModelMap().getAttribute("nomeGioco") == null) {
		// 	mod.addObject("nomeGioco", c.games().read(id).getTitolo());
		// }

		viewAll(id, mod);

		return mod;
	}

	@PostMapping("/modGame")
	public ModelAndView modGame(@RequestParam("idGioco")Integer idGames,@RequestParam("titolo")String titolo ,ModelAndView mod) {
		Games g=new Games();
		g.setId(idGames);
		g.setTitolo(titolo);
		c.games().update(g);
		return viewAll(idGames,mod);
	}

	private ModelAndView viewAll(Integer idGames,ModelAndView mod){
		mod.setViewName("html/game");
		if (mod.getModelMap().getAttribute("idGioco") == null) {
			mod.addObject("idGioco", idGames);
		}
		if (mod.getModelMap().getAttribute("nomeGioco") == null) {
			mod.addObject("nomeGioco", c.games().read(idGames).getTitolo());
		}
		if (mod.getModelMap().getAttribute("listaTeams") == null) {
			mod.addObject("listaTeams", c.teams().readIdGames(idGames));
		}
		if (mod.getModelMap().getAttribute("listaMatch") == null) {
			mod.addObject("listaMatch", c.matches().readIdGames(idGames));
		}

		return mod;
	}

	// modale per inserire un nuovo team 
	@PostMapping("/addTeam")
	public ModelAndView addTeam(@RequestParam("nome")String nome, @RequestParam("nazione")String nazione, 
	@RequestParam("idGioco")Integer idGames, ModelAndView mod) {
		Teams t=new Teams();
		t.setNome(nome);
		t.setNazione(nazione);
		t.setIdGames(idGames);
		c.teams().create(t);
		return viewTeams(idGames, mod);
	}
	
	@GetMapping("/viewMatch")
	public ModelAndView viewMatch(@RequestParam("id") Integer id, ModelAndView mod) {
		// mod.setViewName("html/game");
		HashMap <Integer, Matches> matches= c.matches().readIdGames(id);
		for (Integer key : matches.keySet()) {
			String nomeTeamHome = c.teams().read(matches.get(key).getIdTeamHome()).getNome();
			String nomeTeamAway = c.teams().read(matches.get(key).getIdTeamAway()).getNome();
			matches.get(key).setNomeTeamHome(nomeTeamHome);
			matches.get(key).setNomeTeamAway(nomeTeamAway);			
		}
		mod.addObject("listaMatch", matches);
		// if (mod.getModelMap().getAttribute("idGioco") == null) {
		// 	mod.addObject("idGioco", id);
		// }
		viewAll(id, mod);
		return mod;
	}

	// modale per inserire un nuovo match 
	@PostMapping("/addMatch")
	public ModelAndView addMatch(@RequestParam("dataMatch")Date dataMatch, @RequestParam("idGioco")Integer idGames, 
	@RequestParam("idTeamAway")Integer idTeamAway, @RequestParam("idTeamHome")Integer idTeamHome, 
	@RequestParam("pointsHome")Integer pointsHome, @RequestParam("pointsAway")Integer pointsAway, ModelAndView mod) {
		Matches m= new Matches();
		m.setDataMatch(dataMatch);
		m.setIdGames(idGames);
		m.setIdTeamAway(idTeamAway);
		m.setIdTeamHome(idTeamHome);
		m.setPointsAway(pointsAway);
		m.setPointsHome(pointsHome);
		c.matches().create(m);
		return viewMatch(idGames, mod);
	}

	// modale per eliminare un team 
	@PostMapping("/deleteTeam")
	public ModelAndView deleteTeam(@RequestParam("id")Integer id, @RequestParam("idGioco")Integer idGames, ModelAndView mod) {
		c.teams().delete(id);
		return viewTeams(idGames, mod);
	}

	// modale per eliminare un match 
	@PostMapping("/deleteMatch")
	public ModelAndView deleteMatch(@RequestParam("id")Integer id, @RequestParam("idGioco")Integer idGames, ModelAndView mod) {
		c.matches().delete(id);
		return viewMatch(idGames, mod);
	}

	@PostMapping("/openTeam")
	public ModelAndView openTeam(@RequestParam("id") Integer id){
		ModelAndView mod = new ModelAndView("html/team");
		mod.addObject("listaPlayer",c.players().readIdTeam(id));
		mod.addObject("nomeTeam", c.teams().read(id).getNome());
		mod.addObject("idTeam", c.teams().read(id).getId());
		mod.addObject("idGioco", c.teams().read(id).getIdGames());
		teamController.viewPlayers(id, mod);
		return mod;
	}
	
}
