package corso.WelcomeToEsports.DB;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import corso.WelcomeToEsports.modelli.Matches;


public class MatchesDAO {
	
	@Autowired
	ApplicationContext context;
	@Autowired
	DB db;
	
	
	private HashMap<Integer, Matches> read(String query, String... params){
		ArrayList<HashMap<String, Object>> listaMappe= db.eseguiQuery(query, params);
		Matches m;
		HashMap<Integer, Matches> map= new HashMap<Integer, Matches>();
		for (HashMap<String, Object> hashMap : listaMappe) {
			m=context.getBean(Matches.class, hashMap);
			map.put(m.getId(), m);
		}
		return map;
	}
	
	public HashMap<Integer, Matches> readAll() {
		return read("select * from MATCHES");
	}
	
	public Matches read(int id) {
		return read("select * from MATCHES where id=?", id+"").get(id);
	}
	public HashMap<Integer, Matches> readIdGames(int idGames) {
		return read("select * from MATCHES where idGames=?", idGames+"");
	}

	public HashMap<Integer, Matches> readTeamHome(int idGames){
		return read("select dataMatch, nome, pointsHome from teams t join matches m on t.id=m.idTeamHome where t.idGames=?", idGames+"");
	}

	public HashMap<Integer, Matches> readTeamAway(int idGames){
		return read("select pointsAway, nome from teams t join matches m on t.id=m.idTeamAway where t.idGames=?", idGames+"");
	}
	
	public boolean create(Matches m){
		return db.eseguiUpdate("insert into MATCHES (dataMatch, idGames, idTeamHome, idTeamAway, pointsHome, pointsAway) values (?,?,?,?,?,?)",
				m.getDataMatch()+"",m.getIdGames()+"",m.getIdTeamHome()+"",m.getIdTeamAway()+"",m.getPointsHome()+"",m.getPointsAway()+"");
	}
	
	public boolean update(Matches m){
		return db.eseguiUpdate("update MATCHES set dataMatch =?, idGames =?, idTeamHome =?, idTeamAway =?, pointsHome =?, pointsAway =? where id=?",
				m.getDataMatch()+"",m.getIdGames()+"",m.getIdTeamHome()+"",m.getIdTeamAway()+"",m.getPointsHome()+"",m.getPointsAway()+"",m.getId()+"");
	}
	
	public boolean delete(int id){
		return db.eseguiUpdate("delete from MATCHES where id=?", id+"");
	}



}
