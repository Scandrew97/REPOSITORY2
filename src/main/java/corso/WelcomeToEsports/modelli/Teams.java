package corso.WelcomeToEsports.modelli;

public class Teams extends Entity{

	String nome;
	String nazione;
	int idGames;

	@Override
	public String toString() {
		return "Teams [nome=" + nome + ", nazione=" + nazione + ", idGames=" + idGames + ", toString()="
				+ super.toString() + "]";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public int getIdGames() {
		return idGames;
	}
	public void setIdGames(int idGames) {
		this.idGames = idGames;
	}



}
