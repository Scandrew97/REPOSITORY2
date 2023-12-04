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
import corso.WelcomeToEsports.modelli.Players;
import corso.WelcomeToEsports.modelli.Teams;


@Controller
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private Context c;

	@GetMapping("/")
	public String openTeam(@RequestParam HashMap<String, String> params, Model model) {
		model.addAttribute("nomeGame", params.get("idGames"));
		model.addAttribute("nomeTeam", params.get("idTeam"));
		return "team.html";
	}

	@PostMapping("/updateTeam")
	public String updateTeam(Teams t) {
		c.teams().update(t);
		return "redirect:/team/";
	}

	@PostMapping("/deleteTeam")
	public String deleteTeam(int id) {
		c.teams().delete(id);
		return "redirect:/team/";
	}

	@PostMapping("/addPlayer")
	public String addPlayer(Players p) {
		c.players().create(p);
		return "redirect:/team/";
	}
	@PostMapping("/updatePlayer")
	public String updatePlayer(Players p) {
		c.players().update(p);
		return "redirect:/team/";
	}
	@PostMapping("/deletePlayers")
	public String deletePlayers(int id) {
		c.players().delete(id);
		return "redirect:/team/";
	}

}
