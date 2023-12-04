package corso.WelcomeToEsports.modelli;

public class Games extends Entity{
	
	String titolo;

	@Override
	public String toString() {
		return "Games [titolo=" + titolo + ", toString()=" + super.toString() + "]";
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	

}
