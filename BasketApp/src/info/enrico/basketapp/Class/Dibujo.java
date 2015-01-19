package info.enrico.basketapp.Class;

public class Dibujo {

	private String idDibujo;
	private String nombreDibujo;
	
	public Dibujo(){}
	
	public Dibujo(String idDibujo, String nombreDibujo){
		this.idDibujo = idDibujo;
		this.nombreDibujo = nombreDibujo;
	}

	public String getIdDibujo() {
		return idDibujo;
	}

	public void setIdDibujo(String idDibujo) {
		this.idDibujo = idDibujo;
	}

	public String getNombreDibujo() {
		return nombreDibujo;
	}

	public void setNombreDibujo(String nombreDibujo) {
		this.nombreDibujo = nombreDibujo;
	}

	@Override
	public String toString() {
		return "Dibujo [idDibujo=" + idDibujo + " / nombreDibujo=" + nombreDibujo + "]";
	}	
}
