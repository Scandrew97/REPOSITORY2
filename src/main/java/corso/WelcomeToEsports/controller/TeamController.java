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
import corso.WelcomeToEsports.modelli.Players;
import corso.WelcomeToEsports.modelli.Teams;


@Controller
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private Context c;

	// @GetMapping("/")
	// public String openTeam(@RequestParam HashMap<String, String> params, Model model) {
	// 	model.addAttribute("nomeGame", params.get("idGames"));
	// 	model.addAttribute("nomeTeam", params.get("idTeam"));
	// 	return "team.html";
	// }
	private ModelAndView viewAllPlayers(Integer idTeam,ModelAndView mod){
		mod.setViewName("html/team");
		if (mod.getModelMap().getAttribute("idTeam") == null) {
			mod.addObject("idTeam", idTeam);
		}
		if (mod.getModelMap().getAttribute("idGioco") == null) {
			mod.addObject("idGioco", c.teams().read(idTeam).getIdGames());
		}
		if (mod.getModelMap().getAttribute("nomeTeam") == null) {
			mod.addObject("nomeTeam", c.teams().read(idTeam).getNome());
		}
		if (mod.getModelMap().getAttribute("listaPlayer") == null) {
			mod.addObject("listaPlayer", c.players().readIdTeam(idTeam));
		}

		return mod;
	}

	// @PostMapping("/updateTeam")
	// public ModelAndView updateTeam(@RequestParam("idTeam")Integer idTeam, Teams t, ModelAndView mod) {
	// 	c.teams().update(t);
	// 	return viewAllPlayers(idTeam, mod);
	// }

	@GetMapping("/viewPlayers")
	public ModelAndView viewPlayers(@RequestParam("idTeam") Integer id, ModelAndView mod) {
		HashMap <Integer, Players> players= c.players().readIdTeam(id);
		for (Integer key : players.keySet()) {
			players.get(key).getNome();
		}
		mod.addObject("listaPlayer", players);
		viewAllPlayers(id, mod);

		return mod;
	}

	@PostMapping("/addPlayer")
	public ModelAndView addPlayer(@RequestParam("idTeam")Integer idTeam, @RequestParam("nickname")String nickname, 
	@RequestParam("nome")String nome, @RequestParam("cognome")String cognome, @RequestParam("dataNascita")Date dataNascita, @RequestParam("gamesRole")String gamesRole, ModelAndView mod) {
		Players p= new Players();
		p.setNickname(nickname);
		p.setNome(nome);
		p.setCognome(cognome);
		p.setDataNascita(dataNascita);
		p.setGamesRole(gamesRole);
		p.setIdTeam(idTeam);
		c.players().create(p);
		return viewPlayers(idTeam, mod);
	}

	@PostMapping("/modPlayer")
	public ModelAndView modPlayer(@RequestParam("idTeam")Integer idTeam,@RequestParam("idPlayer")Integer idPlayer, @RequestParam("nickname")String nickname, 
	@RequestParam("nome")String nome, @RequestParam("cognome")String cognome, @RequestParam("dataNascita")Date dataNascita, @RequestParam("gamesRole")String gamesRole, ModelAndView mod) {
		Players p= new Players();
		p.setId(idPlayer);
		p.setNickname(nickname);
		p.setNome(nome);
		p.setCognome(cognome);
		p.setDataNascita(dataNascita);
		p.setGamesRole(gamesRole);
		p.setIdTeam(idTeam);
		c.players().update(p);
		return viewPlayers(idTeam, mod);
	}
	// @PostMapping("/updatePlayer")
	// public String updatePlayer(Players p) {
	// 	c.players().update(p);
	// 	return "redirect:/team/";
	// }
	@PostMapping("/deletePlayers")
	public ModelAndView deletePlayers(@RequestParam("id")Integer id, @RequestParam("idTeam")Integer idTeam, ModelAndView mod) {
		c.players().delete(id);
		return viewPlayers(idTeam, mod);
	}

	@PostMapping("/modTeam")
	public ModelAndView modTeam(@RequestParam("idGioco")Integer idGames,@RequestParam("idTeam")Integer idTeam,@RequestParam("nome")String nome, 
	@RequestParam("nazione")String nazione ,ModelAndView mod) {
		Teams t= new Teams();
		t.setId(idTeam);
		t.setIdGames(idGames);
		t.setNome(nome);
		t.setNazione(nazione);
		c.teams().update(t);
		return viewAllPlayers(idTeam,mod);
	}


}
