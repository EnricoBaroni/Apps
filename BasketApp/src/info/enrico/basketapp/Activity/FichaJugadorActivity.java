package info.enrico.basketapp.Activity;

import info.enrico.basketapp.R;
import info.enrico.basketapp.BD.DbAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FichaJugadorActivity extends ListActivity {
	Button botonficha;
	Button botondetalles;
	Button botoneditar;
	private DbAdapter db;
	private TextView nombre;
	private TextView fechNac;
	private TextView peso;
	private TextView altura;
	private TextView tfn;
	private TextView detalles;
	private ImageView imagen;
	private Cursor cursor = null;
	
	TextView game_title;
	String mode;
	boolean confirm;
	static boolean yesOrNo = false;
	ListView lstJugadores;
	int idEquipo;
	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    	
               
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_jugador);
        
        //Obtenemos el idEquipo enviado desde la anterior activity
     	Bundle b = getIntent().getExtras();
     	idEquipo = b.getInt("idEquipo");
        //TODO Los elementos con //// son cajas de texto para poder 
     	//visualizar graficamente los layout, luego descomentarlos alli y aqui
     	
        // En esta caja de texto meteremos nuevos jugadores
     	nombre = (EditText) findViewById(R.id.txtNombreFicha);
     	fechNac = (EditText) findViewById(R.id.txtFechaNacimientoFicha);
     	peso = (EditText) findViewById(R.id.txtPesoFicha);
     	altura = (EditText) findViewById(R.id.txtAlturaFicha);
     	tfn = (EditText) findViewById(R.id.txtTfnFicha);
     	//TODO Como hacer con los detalles
     	//detalles = (EditText) findViewById(R.id.txtTfnFicha);
     	imagen = (ImageView) findViewById(R.id.fotoJugadorFicha);
     	//TODO MAYBE ¿Igual en un futuro? Un textview que informa si el nuevo jugador no se puede insertar
        
     	//Botones
        botoneditar = (Button) this.findViewById(R.id.botonEditarFicha);
        botonficha = (Button) this.findViewById(R.id.botonFichaFicha);
        botondetalles = (Button) this.findViewById(R.id.botonDetallesFicha);
     	
     	db = new DbAdapter(this);
     	db.open();
    }        
	
	public void lanzarActivityJugadores(String resultado) {		
		Intent intent = new Intent(FichaJugadorActivity.this, JugadoresActivity.class);
		Bundle b = new Bundle();
		b.putString("resultadoAnadirJugador", resultado);
		intent.putExtras(b);
		startActivity(intent);
		finish();
	}
}