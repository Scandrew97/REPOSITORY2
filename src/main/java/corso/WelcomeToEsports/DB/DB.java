package corso.WelcomeToEsports.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DB {
	
	private Connection conn;
	private String dbAddress;
	private String dbUser;
	private String dbPassword;
	
	public DB(@Value("${db.adress}")String dbadress,@Value("${db.user}")String dbuser,@Value("${db.pass}")String dbpass) {
		this.dbAddress = dbadress;
		this.dbUser = dbuser;
		this.dbPassword = dbpass;
		apriConnection();
	}

	private void apriConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbAddress, dbUser, dbPassword);
		} catch (SQLException e) {
			System.err.println("ERRORE CONNESSIONE DATABASE");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("DRIVER NON TROVATO");
			e.printStackTrace();
		}
	}

	public void chiudiConnection() {
		if (conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("ERRORE CHIUSURA CONNESSIONE AL DATABASE");
				e.printStackTrace();
			}
	}

	private static void chiudiItems(PreparedStatement ps, ResultSet rs) {
		try {
			if (ps!=null) ps.close();
			if (rs!=null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ArrayList<HashMap<String, Object>> eseguiQuery(String query,String...params){
		ArrayList<HashMap<String, Object>> listaMappe = new ArrayList<HashMap<String,Object>>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			for (int i = 0; i < params.length; i++) {
				ps.setString(i+1, params[i]);
			}
			rs = ps.executeQuery();

			HashMap<String, Object> mappe= new HashMap<String, Object>();
			HashMap<String, Object> result;
			while(rs.next()) {
				result=(HashMap<String, Object>) mappe.clone();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					result.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
				}
				listaMappe.add(result);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			chiudiItems(ps, rs);
		}
		return listaMappe;
	}

	public boolean eseguiUpdate(String query,String... params) {
		try {
			PreparedStatement ps= conn.prepareStatement(query);
			for (int i = 0; i < params.length; i++) {
				ps.setString(i+1, params[i]);
			}
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
