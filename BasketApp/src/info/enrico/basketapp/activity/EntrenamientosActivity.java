package info.enrico.basketapp.activity;

import info.enrico.basketapp.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class EntrenamientosActivity extends Activity {
	Button botonbuscar;
	Button botoneliminar;
	Button botonanadir;
	ListView lstEntrenamientos;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d("ENRICO","onCreate Entrenamiento");
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
    	
        super.onCreate(savedInstanceState);        
        //addPreferencesFromResource(R.xml.preferences);
        Log.d("ENRICO","Cargando layout");
        setContentView(R.layout.entrenamientos);   
        
        String[] items = { "a","b"};
        
        Log.d("ENRICO","Cargando botones");        
        botonbuscar = (Button) this.findViewById(R.id.botonbuscar_entrenamientos);
        botoneliminar = (Button) this.findViewById(R.id.botoneliminar_entrenamientos);
        botonanadir = (Button) this.findViewById(R.id.botonanadir_entrenamientos);
        lstEntrenamientos = (ListView) findViewById(R.id.lstEntrenamientos);
        
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
        	    R.layout.menu_item, items);        
        lstEntrenamientos.setAdapter(adaptador);
        
        Log.d("ENRICO","OnClick Buscar");
        botonbuscar.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Buscar");
    	    	//TODO Buscar por categoria
    	    	//El buscar por nombre se actualiza letra a letra
    	    }
    	});
        Log.d("ENRICO","OnClick Eliminar");
        botoneliminar.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Eliminar");
    	    	//TODO Elimina el entrenamiento seleccionado
    	    }
    	});
        Log.d("ENRICO","OnClick Añadir");
        botonanadir.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Añadir");
    	    	//TODO Va a la creación de entrenamiento
    	    }
    	});
    }
}