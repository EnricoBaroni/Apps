package info.enrico.basketapp.Activity;

import info.enrico.basketapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // Un array con los elementos del menú
        String[] items = { 
        		getResources().getString(R.string.entrenamiento_title),
                getResources().getString(R.string.equipos_title),
                getResources().getString(R.string.calendario_title),
                getResources().getString(R.string.ayuda_title)};

        // Cargamos un ListView
        ListView menuLista = (ListView) findViewById(R.id.ListView_Menu);
        
        // Creamos un ArrayAdapter con los Strings del array y de paso lo formateamos con un layout por item
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.menu_item, items);
        
        // Le asociamos el ArrayAdapter con los elementos del menú a nuestro ListView
        menuLista.setAdapter(adaptador);

        // Definimos un Listener para controlar qué pasa cuando hacemos click en cada elemento de la lista
        menuLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked,
                int position, long id) {
            	Log.d("ENRICO","Click en el elemento: " + position);
            	// Según la posición del menú seleccionada hacemos determinada cosa
                switch (position) {
                	case 0:
                			startActivity(new Intent(MenuActivity.this, EntrenamientoActivity.class));
                			Log.d("ENRICO","Iniciada actividad: " + position);
                			break;
                	case 1:
                			startActivity(new Intent(MenuActivity.this, EquiposActivity.class));
                			Log.d("ENRICO","Iniciada actividad: " + position);
                			break;
                	case 2:
                			startActivity(new Intent(MenuActivity.this, CalendarioActivity.class));
                			Log.d("ENRICO","Iniciada actividad: " + position);
                			break;
                	case 3:
                			startActivity(new Intent(MenuActivity.this, AyudaActivity.class));
                			Log.d("ENRICO","Iniciada actividad: " + position);
                			break;                			
                	default:
                }            
              }
          }
        );               
    }
}
        