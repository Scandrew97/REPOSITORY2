package corso.WelcomeToEsports.modelli;

public abstract class Entity {
	
	int id;

	@Override
	public String toString() {
		return "Entity [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
