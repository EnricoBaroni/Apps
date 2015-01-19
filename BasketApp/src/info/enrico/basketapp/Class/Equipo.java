package info.enrico.basketapp.Class;

public class Equipo {

	private int idEquipo;
	private String nombreEquipo;
	
	public Equipo(){}
	
	public Equipo(int idEquipo, String nombreEquipo){
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + " / nombreEquipo=" + nombreEquipo + "]";
	}	
}
