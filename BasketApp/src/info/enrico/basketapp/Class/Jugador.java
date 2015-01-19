package info.enrico.basketapp.Class;

public class Jugador {

	private int idJugador;
	private String nombreJugador;
	private int idEquipoJugador;	
	private String fechNacJugador;
	private int pesoJugador;
	private int alturaJugador;
	private int tfnJugador;
	private String imagenJugador; //En principio lo ponemos string para evitar problemas
	private String detallesJugador; //Podran ser varios, pero los repartiremos con un split
	
	public Jugador(){}
	
	public Jugador(int idJugador, String nombreJugador, int idEquipoJugador, String fechNacJugador, 
			int pesoJugador, int alturaJugador, int tfnJugador, 
			String imagenJugador, String detallesJugador){
		this.idJugador = idJugador;
		this.nombreJugador = nombreJugador;
		this.idEquipoJugador = idEquipoJugador;		
		this.fechNacJugador = fechNacJugador;
		this.pesoJugador = pesoJugador;
		this.alturaJugador = alturaJugador;
		this.tfnJugador = tfnJugador;
		this.imagenJugador = imagenJugador;
		this.detallesJugador = detallesJugador;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}
	
	public int getIdEquipoJugador() {
		return idEquipoJugador;
	}

	public void setIdEquipoJugador(int idEquipoJugador) {
		this.idEquipoJugador = idEquipoJugador;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public String getFechNacJugador() {
		return fechNacJugador;
	}

	public void setFechNacJugador(String fechNacJugador) {
		this.fechNacJugador = fechNacJugador;
	}

	public int getPesoJugador() {
		return pesoJugador;
	}

	public void setPesoJugador(int pesoJugador) {
		this.pesoJugador = pesoJugador;
	}

	public int getAlturaJugador() {
		return alturaJugador;
	}

	public void setAlturaJugador(int alturaJugador) {
		this.alturaJugador = alturaJugador;
	}

	public int getTfnJugador() {
		return tfnJugador;
	}

	public void setTfnJugador(int tfnJugador) {
		this.tfnJugador = tfnJugador;
	}

	public String getImagenJugador() {
		return imagenJugador;
	}

	public void setImagenJugador(String imagenJugador) {
		this.imagenJugador = imagenJugador;
	}

	public String getDetallesJugador() {
		return detallesJugador;
	}

	public void setDetallesJugador(String detallesJugador) {
		this.detallesJugador = detallesJugador;
	}

	@Override
	public String toString() {
		return "Jugador [idJugador=" + idJugador + " / idEquipoJugador=" + idEquipoJugador 
				+ " / nombreJugador=" + nombreJugador + " /  fechNacJugador=" + fechNacJugador 
				+ " /  pesoJugador=" + pesoJugador + " /  alturaJugador="+ alturaJugador 
				+ " /  tfnJugador=" + tfnJugador + " / imagenJugador=" + imagenJugador 
				+ " /  detallesJugador=" + detallesJugador + "]";
	}
}