package corso.WelcomeToEsports.DB;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


import corso.WelcomeToEsports.modelli.Players;

public class PlayersDAO {
	
	@Autowired
	ApplicationContext context;
	@Autowired
	DB db;
	
	
	private HashMap<Integer, Players> read(String query, String... params){
		ArrayList<HashMap<String, Object>> listaMappe= db.eseguiQuery(query, params);
		Players p;
		HashMap<Integer, Players> map= new HashMap<Integer, Players>();
		for (HashMap<String, Object> hashMap : listaMappe) {
			p=context.getBean(Players.class, hashMap);
			map.put(p.getId(), p);
		}
		return map;
	}
	
	public HashMap<Integer, Players> readAll() {
		return read("select * from PLAYERS");
	}
	
	public Players read(int id) {
		return read("select * from PLAYERS where id=?", id+"").get(id);
	}
	
	public HashMap<Integer, Players> readNickname(String nickname){
		return read("select * from PLAYERS where nickname = ?", nickname);
	}
	public HashMap<Integer, Players> readNome(String nome){
		return read("select * from PLAYERS where nome = ?", nome);
	}
	public HashMap<Integer, Players> readCognome(String cognome){
		return read("select * from PLAYERS where cognome = ?", cognome);
	}
	public HashMap<Integer, Players> readDataNascita(Date dataNascita){
		return read("select * from PLAYERS where dataNascita = ?", dataNascita+"");
	}
	public HashMap<Integer, Players> readGamesRole(String gamesRole){
		return read("select * from PLAYERS where gamesRole = ?", gamesRole);
	}
	public HashMap<Integer, Players> readIdTeam(int idTeam){
		return read("select * from PLAYERS where idTeam = ?", idTeam+"");
	}
	
	public boolean create(Players p){
		return db.eseguiUpdate("insert into PLAYERS (nickname, nome, cognome, dataNascita, gamesRole, idTeam) values (?,?,?,?,?,?)",
				p.getNickname(),p.getNome(),p.getCognome(),p.getDataNascita()+"",p.getGamesRole(),p.getIdTeam()+"");
	}
	
	public boolean update(Players p){
		return db.eseguiUpdate("update PLAYERS set titolo =?, nome =?, cognome =?, dataNascita =?, gamesRole =?, idTeam =? ",
				p.getNickname(),p.getNome(),p.getCognome(),p.getDataNascita()+"",p.getGamesRole(),p.getIdTeam()+"");
	}
	
	public boolean delete(int id){
		return db.eseguiUpdate("delete from PLAYERS where id=?", id+"");
	}


}
