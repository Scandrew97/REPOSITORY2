package corso.WelcomeToEsports.DB;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import corso.WelcomeToEsports.modelli.Teams;


public class TeamsDAO {
	
	@Autowired
	ApplicationContext context;
	@Autowired
	DB db;
	
	
	private HashMap<Integer, Teams> read(String query, String... params){
		ArrayList<HashMap<String, Object>> listaMappe= db.eseguiQuery(query, params);
		Teams t;
		HashMap<Integer, Teams> map= new HashMap<Integer, Teams>();
		for (HashMap<String, Object> hashMap : listaMappe) {
			t=context.getBean(Teams.class, hashMap);
			map.put(t.getId(), t);
		}
		return map;
	}
	
	public HashMap<Integer, Teams> readAll() {
		return read("select * from TEAMS");
	}
	
	public Teams read(int id) {
		return read("select * from TEAMS where id=?", id+"").get(id);
	}
	
	public HashMap<Integer, Teams> readNome(String nome){
		return read("select * from TEAMS where nome = ?", nome);
	}
	public HashMap<Integer, Teams> readNazione(String nazione){
		return read("select * from TEAMS where nazione = ?", nazione);
	}
	
	public HashMap<Integer, Teams> readIdGames(int idGames) {
		return read("select * from TEAMS where idGames=?", idGames+"");
	}
	
	public boolean create(Teams t){
		return db.eseguiUpdate("insert into TEAMS (nome, nazione, idGames) values (?,?,?)",
				t.getNome(),t.getNazione(),t.getIdGames()+"");
	}
	
	public boolean update(Teams t){
		return db.eseguiUpdate("update TEAMS set nome =?, nazione =?, idGames =?",
				t.getNome(),t.getNazione(),t.getIdGames()+"");
	}
	
	public boolean delete(int id){
		return db.eseguiUpdate("delete from TEAMS where id=?", id+"");
	}


}
