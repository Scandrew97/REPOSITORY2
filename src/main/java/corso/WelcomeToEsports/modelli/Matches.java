package corso.WelcomeToEsports.modelli;

import java.sql.Date;

public class Matches extends Entity{
	
	Date dataMatch;
	int idGames;
	int idTeamHome;
	int idteamAway;
	int pointsHome;
	int pointsAway;
	
	@Override
	public String toString() {
		return "Matches [dataMatch=" + dataMatch + ", idGames=" + idGames + ", idTeamHome=" + idTeamHome
				+ ", idteamAway=" + idteamAway + ", pointsHome=" + pointsHome + ", pointsAway=" + pointsAway
				+ ", toString()=" + super.toString() + "]";
	}
	public Date getDataMatch() {
		return dataMatch;
	}
	public void setDataMatch(Date dataMatch) {
		this.dataMatch = dataMatch;
	}
	public int getIdGames() {
		return idGames;
	}
	public void setIdGames(int idGames) {
		this.idGames = idGames;
	}
	public int getIdTeamHome() {
		return idTeamHome;
	}
	public void setIdTeamHome(int idTeamHome) {
		this.idTeamHome = idTeamHome;
	}
	public int getIdteamAway() {
		return idteamAway;
	}
	public void setIdteamAway(int idteamAway) {
		this.idteamAway = idteamAway;
	}
	public int getPointsHome() {
		return pointsHome;
	}
	public void setPointsHome(int pointsHome) {
		this.pointsHome = pointsHome;
	}
	public int getPointsAway() {
		return pointsAway;
	}
	public void setPointsAway(int pointsAway) {
		this.pointsAway = pointsAway;
	}
	
	

}