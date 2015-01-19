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

public class EquiposActivity extends ListActivity {
	Button botonanadir;
	Button botoneliminar;
	ListView lista;
	// Variable para manejar la base de datos.
		private DbAdapter db;
		private TextView txtSeleccionado;
	    private Cursor cursor = null;
	    private int seleccionado = -1;
	
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
        setContentView(R.layout.equipos);
        
     	// Un textview que informa del equipo que se encuentra seleccionado actualmente
     	txtSeleccionado = (TextView) findViewById(R.id.txtSeleccionadoEquipos);
     	//Botones
        botonanadir = (Button) this.findViewById(R.id.botonAnadirEquipos);
        botoneliminar = (Button) this.findViewById(R.id.botonEliminarEquipos);
        //Lista
     	lista = (ListView) findViewById(R.id.lstEquipos);
		ArrayList<Equipo> arrayequip = new ArrayList<Equipo>();
	
     	db = new DbAdapter(this);
     	db.open();
     	
     	//En principio se muestra un mensaje al usuario en una ventanita, 
     	//pero por si queremos mostrarlo de otra manera lo pillamos. 
     	//Nombre del equipo añadido
     	Bundle b = getIntent().getExtras();
     	String resultadoAnadir = b.getString("resultadoAnadir");
		
     	arrayequip = db.obtenerEquipos(); //CARGA TODOS LOS EQUIPOS
     	
     	AdapterEquipos adapter = new AdapterEquipos(this, arrayequip);
		lista.setAdapter(adapter);
     	
		// Le asociamos un listener para saber cuál clickamos
		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// Sacamos el registro de la posición que han seleccionado (arg2)
				Cursor elementoSeleccionado = (Cursor) arg0.getItemAtPosition(arg2);
				// Nos guardamos el ID del registro
				seleccionado = elementoSeleccionado.getInt(0);		
				
				// Sacamos info por el textview
				txtSeleccionado.setText("Has seleccionado: " + seleccionado);				    	
				Log.d("ENRICO","Clickado el elemento con el identificador: " + seleccionado);				
				lanzarActivityEquipo(db.obtenerEquipo(seleccionado).getIdEquipo());				
			}
		});
    }
	
	/**
	 * actualizarLista
	 * La lista tipo ListView tiene asociado un Cursor,
	 * nos basta con hacer un requery para que se refresque
	 */
	private void actualizarLista () {
		cursor.requery();
	}
	
	public void lanzarActivityEquipo(int idEquipo) {
		Intent intent = new Intent(EquiposActivity.this, EquipoActivity.class);
		Bundle b = new Bundle();
		b.putInt("idEquipo", seleccionado);
		intent.putExtras(b);
		startActivity(intent);
		finish();
	}

	public void lanzarActivityAnadir() {
		Intent intent = new Intent(EquiposActivity.this, AnadirEquipoActivity.class);		
		startActivity(intent);
		finish();
	}
	/**
	 * eliminarEquipo
	 * Recupera el id del equipo que habiamos seleccionado 
	 * y el manda a la BD que lo elimine
	 * @param v
	 */
	public void eliminarEquipo (View v) {
		// Si no tenemos nada seleccionado nos vamos.
		if (seleccionado == -1) {return;}
		
		// nos cargamos el registro de la BD
		db.borrarEquipo(seleccionado);

		// Hasta que no vuelvan a seleccionar algún elemento
		// de la lista no podremos entrar aquí
		seleccionado = -1;
		
    	// Sacamos info por el textview
    	txtSeleccionado.setText("Elemento eliminado");

		
		// Actualiza los datos del elemento ListView.
		actualizarLista();

		// Notificamos al usuario
		Toast.makeText(getApplicationContext(), "Registro eliminado: " + seleccionado, Toast.LENGTH_SHORT).show();
	}
}