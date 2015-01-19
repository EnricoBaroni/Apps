package info.enrico.basketapp.Class;

public class Entrenamiento {

	private int idEntrenamiento;
	private String nombreEntrenamiento;
	private String categoriasEntrenamiento;	
	private String dibujosEntrenamiento;
	private String explicacionEntrenamiento;
	private String notasEntrenamiento; 
	
	public Entrenamiento(){}
	
	public Entrenamiento(int idEntrenamiento, String nombreEntrenamiento, String categoriasEntrenamiento, 
			String dibujosEntrenamiento, String explicacionEntrenamiento, String notasEntrenamiento){
		this.idEntrenamiento = idEntrenamiento;
		this.nombreEntrenamiento = nombreEntrenamiento;
		this.categoriasEntrenamiento = categoriasEntrenamiento;		
		this.dibujosEntrenamiento = dibujosEntrenamiento;
		this.explicacionEntrenamiento = explicacionEntrenamiento;
		this.notasEntrenamiento = notasEntrenamiento;
	}

	public int getIdEntrenamiento() {
		return idEntrenamiento;
	}

	public void setIdEntrenamiento(int idEntrenamiento) {
		this.idEntrenamiento = idEntrenamiento;
	}

	public String getNombreEntrenamiento() {
		return nombreEntrenamiento;
	}

	public void setNombreEntrenamiento(String nombreEntrenamiento) {
		this.nombreEntrenamiento = nombreEntrenamiento;
	}

	public String getCategoriasEntrenamiento() {
		return categoriasEntrenamiento;
	}

	public void setCategoriasEntrenamiento(
			String categoriasEntrenamiento) {
		this.categoriasEntrenamiento = categoriasEntrenamiento;
	}

	public String getDibujosEntrenamiento() {
		return dibujosEntrenamiento;
	}

	public void setDibujosEntrenamiento(String dibujosEntrenamiento) {
		this.dibujosEntrenamiento = dibujosEntrenamiento;
	}

	public String getExplicacionEntrenamiento() {
		return explicacionEntrenamiento;
	}

	public void setExplicacionEntrenamiento(String explicacionEntrenamiento) {
		this.explicacionEntrenamiento = explicacionEntrenamiento;
	}

	public String getNotasEntrenamiento() {
		return notasEntrenamiento;
	}

	public void setNotasEntrenamiento(String notasEntrenamiento) {
		this.notasEntrenamiento = notasEntrenamiento;
	}

	@Override
	public String toString() {
		return "Entrenamiento [idEntrenamiento=" + idEntrenamiento
				+ ", nombreEntrenamiento=" + nombreEntrenamiento
				+ ", categoriasEntrenamiento=" + categoriasEntrenamiento
				+ ", dibujosEntrenamiento=" + dibujosEntrenamiento
				+ ", explicacionEntrenamiento=" + explicacionEntrenamiento
				+ ", notasEntrenamiento=" + notasEntrenamiento + "]";
	}
}