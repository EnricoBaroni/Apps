package info.enrico.basketapp.activity;

import java.util.ArrayList;

import info.enrico.basketapp.R;
import info.enrico.basketapp.bd.DbAdapter;
import info.enrico.basketapp.entity.Equipo;
import info.enrico.basketapp.entity.Jugador;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
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

public class EquiposActivity extends Activity {
	Button botonanadir;
	Button botoneliminar;
	Button botonchange;
	ListView lista;
	int change = 0; //0 Single, 1 Multiple
	// Variable para manejar la base de datos.
		private DbAdapter db;
		private TextView txtSeleccionado;
	    private Cursor cursor = null;
	    private int seleccionado = -1;
	    
	    AdapterEquipos adapter;
	    ArrayList<Equipo> arrayequip = new ArrayList<Equipo>();
	    
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
        botonchange = (Button) this.findViewById(R.id.botonChangeEquipos);
        //Lista
        Log.d("ENRICO","Id de la lista: " + R.id.lstEquipos);
     	lista = (ListView) findViewById(R.id.lstEquipos);
		
	
     	db = new DbAdapter(this);
     	db.open();
     	
     	//En principio se muestra un mensaje al usuario en una ventanita, 
     	//pero por si queremos mostrarlo de otra manera lo pillamos. 
     	//Nombre del equipo añadido
     	Bundle b = getIntent().getExtras();
     	if(this.getIntent().getExtras() != null){
     		String resultadoAnadir = b.getString("resultadoAnadir");
     	}
		
     	arrayequip = db.obtenerEquipos(); //CARGA TODOS LOS EQUIPOS
     	
     	adapter = new AdapterEquipos(this, arrayequip);
     	lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lista.setAdapter(adapter);
		
		lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {        	
			public boolean onItemLongClick(AdapterView<?> arg0, View v, int position, long id) {
				if(change == 0){
					Log.d("ENRICO", "Item longClick en modo Single");
					// Sacamos el registro de la posición que han seleccionado (arg2)
					Cursor elementoSeleccionado = (Cursor) arg0.getItemAtPosition(position);
					// Nos guardamos el ID del registro
					seleccionado = elementoSeleccionado.getInt(0);		
					Log.d("ENRICO","Click en el elemento con el identificador: " + seleccionado);				
					lanzarActivityEquipo(db.obtenerEquipo(seleccionado).getIdEquipo());		
				}else{
					Log.d("ENRICO", "Item longClick en modo Multiple. Nada deberia ocurrir");
				}
				return false;
			}
        });
				
		Log.d("ENRICO","OnClick Añadir");
		botonanadir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Añadir");
				lanzarActivityAnadir();	
			}
		});
		Log.d("ENRICO","OnClick Eliminar");
		botoneliminar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Eliminar");
				eliminarEquipo(view);	
			}
		});
		Log.d("ENRICO","OnClick Change");
		botonchange.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Change");
				change(view);	
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
		
		if(change == 1){ //Si esta en modo multiple hacemos un bucle para eliminar todos
			String informacion = "";
			String[] listaCheck = checkeados();
			for(int i = 0; i < checkeados().length; i++){
				db.borrarEquipoN(listaCheck[i]);
				informacion += listaCheck[i] + " ";
			}		
			
			//Notificamos al usuario			
			Toast.makeText(getApplicationContext(), "Registros eliminados: " + informacion, Toast.LENGTH_SHORT).show();
		}else{			
		
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
	
    public void change(View v){
    	if(change==0){         	
         	adapter = new AdapterEquipos(this, arrayequip);
         	lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    		lista.setAdapter(adapter);
            change = 1;
    	}else{         	
         	adapter = new AdapterEquipos(this, arrayequip);
         	lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    		lista.setAdapter(adapter);
            change = 0;
    	}
    }
    /**
     * Devuelve un String[] con todos los nombres de los equipos checkeados
     * @return
     */
    public String[] checkeados(){
		SparseBooleanArray checked = lista.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getEquipoNombre(position));
        }
 
        String[] outputStrArr = new String[selectedItems.size()];
        
        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }
        return outputStrArr;
    }
}