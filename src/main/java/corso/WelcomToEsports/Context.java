package corso.WelcomToEsports;

import java.sql.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import corso.WelcomeToEsports.DB.DB;
import corso.WelcomeToEsports.DB.GamesDAO;
import corso.WelcomeToEsports.DB.MatchesDAO;
import corso.WelcomeToEsports.DB.PlayersDAO;
import corso.WelcomeToEsports.DB.TeamsDAO;
import corso.WelcomeToEsports.modelli.Games;
import corso.WelcomeToEsports.modelli.Matches;
import corso.WelcomeToEsports.modelli.Players;
import corso.WelcomeToEsports.modelli.Teams;

@Configuration
@Repository
public class Context {

	@Bean
	public DB database(@Value ("${db.adress}") String dbadress, @Value ("${db.user}") String dbuser, @Value ("${db.pass}") String dbpass) {
		return new DB(dbadress,dbuser,dbpass);
	}

	@Bean
	public GamesDAO games() {
		return new GamesDAO();
	}

	@Bean
	@Scope("prototype")
	public Games game(HashMap<String, Object> params) {
		Games g = new Games();
		g.setId(Integer.parseInt(params.get("id")+""));
		g.setTitolo(params.get("titolo")+"");
		return g;
	}

	@Bean
	public PlayersDAO players() {
		return new PlayersDAO();
	}

	@Bean
	@Scope("prototype")
	public Players player(HashMap<String, Object> params) {
		Players p = new Players();
		p.setId(Integer.parseInt(params.get("id")+""));
		p.setNickname(params.get("nickname")+"");
		p.setNome(params.get("nome")+"");
		p.setCognome(params.get("cognome")+"");
		p.setDataNascita(Date.valueOf(params.get("dataNascita")+""));
		p.setGamesRole(params.get("gamesRole")+"");
		p.setIdTeam(Integer.parseInt(params.get("idTeam")+""));
		return p;
	}

	@Bean
	public TeamsDAO teams() {
		return new TeamsDAO();
	}

	@Bean
	@Scope("prototype")
	public Teams team() {
		return new Teams();
	}

	@Bean
	public MatchesDAO matches() {
		return new MatchesDAO();
	}

	@Bean
	@Scope("prototype")
	public Matches match() {
		return new Matches();
	}


}
