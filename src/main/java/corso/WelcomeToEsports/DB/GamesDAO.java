package corso.WelcomeToEsports.DB;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import corso.WelcomeToEsports.modelli.Games;

public class GamesDAO {
	@Autowired
	ApplicationContext context;
	@Autowired
	DB db;
	
	
	private HashMap<Integer, Games> read(String query, String... params){
		ArrayList<HashMap<String, Object>> listaMappe= db.eseguiQuery(query, params);
		Games g;
		HashMap<Integer, Games> map= new HashMap<Integer, Games>();
		for (HashMap<String, Object> hashMap : listaMappe) {
			g=context.getBean(Games.class, hashMap);
			map.put(g.getId(), g);
		}
		return map;
	}
	
	public HashMap<Integer, Games> readAll() {
		return read("select * from GAMES");
	}
	
	public Games read(int id) {
		return read("select * from GAMES where id=?", id+"").get(id);
	}
	
	public HashMap<Integer, Games> readTitolo(String titolo){
		return read("select * from GAMES where titolo = ?", titolo);
	}
	
	public boolean create(Games g){
		return db.eseguiUpdate("insert into GAMES (titolo) values (?)",g.getTitolo());
	}
	
	public boolean update(Games g){
		return db.eseguiUpdate("update GAMES set titolo =? where id=?", g.getTitolo()+"", g.getId()+"");
	}
	
	public boolean delete(int id){
		return db.eseguiUpdate("delete from GAMES where id=?", id+"");
	}


}
