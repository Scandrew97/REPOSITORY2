package corso.WelcomeToEsports.modelli;

import java.sql.Date;

public class Players extends Entity{
	
	String nickname;
	String nome;
	String cognome;
	Date dataNascita;
	String gamesRole;
	int idTeam;
	
	@Override
	public String toString() {
		return "Players [nickname=" + nickname + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita="
				+ dataNascita + ", gamesRole=" + gamesRole + ", idTeam=" + idTeam + ", toString()=" + super.toString()
				+ "]";
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getGamesRole() {
		return gamesRole;
	}
	public void setGamesRole(String gamesRole) {
		this.gamesRole = gamesRole;
	}
	public int getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}
	
	

}
