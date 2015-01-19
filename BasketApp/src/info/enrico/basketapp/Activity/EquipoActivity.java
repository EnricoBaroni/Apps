package info.enrico.basketapp.Activity;

import java.util.ArrayList;

import info.enrico.basketapp.R;
import info.enrico.basketapp.BD.DbAdapter;
import info.enrico.basketapp.Class.Jugador;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EquipoActivity extends Activity {
	private DbAdapter db;
	Button botonanadir;
	Button botoneliminar;
	Button botoneditar;
	ListView lista;
	ArrayList<Jugador> arrayjug;
	TextView txtSeleccionado;
    private int seleccionado = -1;
    int idEquipo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    	
               
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo);
        // Un textview que informa del equipo que se encuentra seleccionado actualmente
     	txtSeleccionado = (TextView) findViewById(R.id.txtSeleccionadoEquipo);
     	//Botones
        botonanadir = (Button) this.findViewById(R.id.botonAnadirJugadorEquipo);
        botoneliminar = (Button) this.findViewById(R.id.botonEliminarJugadorEquipo);
        botoneditar = (Button) this.findViewById(R.id.botonEditarEquipo);
        
        lista = (ListView) findViewById(R.id.lstJugadores);
		arrayjug = new ArrayList<Jugador>();

		// Crea un objeto manipulador de base de datos y abre una conexiï¿½n.
     	db = new DbAdapter(this);
     	db.open();
		
     	//Obtenemos el idEquipo enviado desde la anterior activity
     	Bundle b = getIntent().getExtras();
     	idEquipo = b.getInt("idEquipo");
     	
     	cargarLista(idEquipo);

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
			}
		});		
		
		Log.d("ENRICO","OnClick Añadir");
		botonanadir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Añadir");
				lanzarActivityJugadores(idEquipo);	
			}
		});
		Log.d("ENRICO","OnClick Editar");
		botoneditar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Editar");
				//TODO Que hacer aqui
			}
		});
    }
    
	private void cargarLista(int idEquipo) {
		arrayjug = db.obtenerJugadoresEquipo(idEquipo);	//Carga todos los jugadores del equipo
		// Creo el adapter personalizado
		AdapterJugadores adapter = new AdapterJugadores(this, arrayjug);
		// Lo aplico
		lista.setAdapter(adapter);
	}
	
    public void lanzarActivityJugadores(int idEquipo) {
		Intent intent = new Intent(EquipoActivity.this, JugadoresActivity.class);
		Bundle b = new Bundle();
		b.putInt("idEquipo", idEquipo);
		intent.putExtras(b);
		startActivity(intent);
		finish();
	}
    
    /**
	 * eliminarJugadorEquipo
	 * Recupera el id del jugador que habiamos seleccionado 
	 * y el manda a la BD que lo elimine del equipo
	 * @param v
	 */
	public void eliminarJugadorEquipo (View v) {
		// Si no tenemos nada seleccionado informamos y nos vamos.
		if (seleccionado == -1) {
			Toast.makeText(getApplicationContext(), "Selecciona un jugador a eliminar", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// Realizamos los cambios
		db.actualizarJugadorEquipo(db.obtenerJugador(seleccionado).getIdJugador(), 0);

		// Hasta que no vuelvan a seleccionar algún elemento
		// de la lista no podremos entrar aquí
		seleccionado = -1;
		
    	// Sacamos info por el textview
    	txtSeleccionado.setText("Jugador eliminado del equipo");

		
		// Actualiza los datos del elemento ListView.
		cargarLista(idEquipo);

		// Notificamos al usuario
		Toast.makeText(getApplicationContext(), "Jugador eliminado del equipo: " + seleccionado, Toast.LENGTH_SHORT).show();
	}
	
}     