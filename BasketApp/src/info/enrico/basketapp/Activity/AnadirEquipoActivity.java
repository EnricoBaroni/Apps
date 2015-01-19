package info.enrico.basketapp.Activity;

import java.util.ArrayList;

import info.enrico.basketapp.R;
import info.enrico.basketapp.BD.DbAdapter;
import info.enrico.basketapp.Class.Equipo;
import info.enrico.basketapp.Class.Jugador;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AnadirEquipoActivity extends ListActivity {
	Button botonanadir;	
		private DbAdapter db;
		private EditText nuevoEquipo;
		private TextView txtResultado;
	    private Cursor cursor = null;
	
	TextView game_title;
	String mode;
	boolean confirm;
	static boolean yesOrNo = false;
	ListView lstJugadores;
	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    	
               
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_equipos);
        
        // En esta caja de texto meteremos nuevos equipos
     	nuevoEquipo = (EditText) findViewById(R.id.etAnadirEquipo);
     	//TODO MAYBE ¿Igual en un futuro? Un textview que informa si el nuevo equipo no se puede insertar
        txtResultado = (TextView) findViewById(R.id.txtResultadoAnadirEquipo);
     	//Botones
        botonanadir = (Button) this.findViewById(R.id.botonAnadirNuevoEquipo);
     	
     	db = new DbAdapter(this);
     	db.open();
    }
        
	/**
	 * insertarRegistro
	 * Toma la información de la caja de texto y la inserta
	 * como nuevo registro
	 * @param v
	 */
	public void insertarRegistro (View v) {
		String texto = nuevoEquipo.getText().toString();
		// Inserta los valores de las cajas de texto en la tabla notas.
		if(texto.length() == 0){
			txtResultado.setText("Introduce un nombre de equipo");
		}else{
			db.insertarEquipo(texto);

			// Notificamos al usuario
			Toast.makeText(getApplicationContext(), "Nuevo equipo introducido: " + texto, Toast.LENGTH_SHORT).show();
		
			// Vacíamos la caja de texto
			nuevoEquipo.setText("");
			lanzarActivityEquipos(txtResultado.getText().toString());
		}		
	}
	
	public void lanzarActivityEquipos(String resultado) {
		txtResultado.setText("");
		Intent intent = new Intent(AnadirEquipoActivity.this, EquiposActivity.class);
		Bundle b = new Bundle();
		b.putString("resultadoAnadirEquipo", resultado);
		intent.putExtras(b);
		startActivity(intent);
		finish();
	}
}