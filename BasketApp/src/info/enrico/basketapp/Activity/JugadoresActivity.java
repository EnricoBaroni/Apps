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
import android.widget.AdapterView.OnItemClickListener;

public class JugadoresActivity extends Activity {
	private DbAdapter db;
	Button botonanadir;
	Button botoneliminar;
	Button botoncrear;
	ListView lista;
	ArrayList<Jugador> arrayjug;
	TextView txtSeleccionado;
    private int seleccionado = -1;
    int idEquipo;
    int idJugador;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    	
               
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadores);
        // Para eliminar jugador. Un textview que informa del equipo que se encuentra seleccionado actualmente
     	txtSeleccionado = (TextView) findViewById(R.id.txtSeleccionadoJugadores);
     	//Botones
        botonanadir = (Button) this.findViewById(R.id.botonAnadirJugador);
        botoneliminar = (Button) this.findViewById(R.id.botonEliminarJugador);
        botoncrear = (Button) this.findViewById(R.id.botonCrearJugador);
        
        lista = (ListView) findViewById(R.id.lstJugadores);
		arrayjug = new ArrayList<Jugador>();

		// Crea un objeto manipulador de base de datos y abre una conexiï¿½n.
     	db = new DbAdapter(this);
     	db.open();
     	
     	arrayjug = db.obtenerJugadores(); //CARGA TODOS LOS JUGADORES EXISTENTES
     	
     	//Obtenemos el idEquipo enviado desde la anterior activity
     	Bundle b = getIntent().getExtras();
     	idEquipo = b.getInt("idEquipo");
     	//Por si en el futuro quiero mostrar de otra manera el resultado de añadir un jugador
     	//NombreJugador añadido
     	//String resultadoAnadir = b.getString("resultadoAnadirJugador");
     	
     	
		// Creo el adapter personalizado
		AdapterJugadores adapter = new AdapterJugadores(this, arrayjug);
 
		// Lo aplico
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
			}
		});		
		
		Log.d("ENRICO","OnClick Añadir");
		botonanadir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Añadir");
				//TODO Añadir al equipo con seleccion multiple
			}
		});
		Log.d("ENRICO","OnClick Eliminar");
		botoneliminar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Eliminar");
				//TODO Eliminar del equipo con seleccion multiple
			}
		});
		Log.d("ENRICO","OnClick Crear");
		botoncrear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Crear");
				lanzarActivityCrearJugador(idEquipo);
			}
		});
    }
    public void lanzarActivityCrearJugador(int idEquipo) {
		Intent intent = new Intent(JugadoresActivity.this, AnadirJugadorActivity.class);
		Bundle b = new Bundle();
		b.putInt("idEquipo", idEquipo);
		intent.putExtras(b);
		startActivity(intent);
		finish();
	}
}     