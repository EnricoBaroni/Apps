package info.enrico.basketapp.Activity;

import info.enrico.basketapp.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class EntrenamientoActivity extends ListActivity {
	Button botonbuscar;
	Button botoneliminar;
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
        botonbuscar = (Button) this.findViewById(R.id.botonbuscar_entrenamiento);
        botoneliminar = (Button) this.findViewById(R.id.botoneliminar_entrenamiento);
        lstEntrenamientos = getListView();
        
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
        	    R.layout.menu_item, items);        
        lstEntrenamientos.setAdapter(adaptador);
        
        Log.d("ENRICO","OnClick Buscar");
        botonbuscar.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Buscar");
    	    	//Intent miIntent = new Intent(EntrenamientoActivity.this, EquiposActivity.class);
    	    	//startActivity(miIntent);
    	    	//EntrenamientoActivity.this.finish();
    	    }
    	});
        Log.d("ENRICO","OnClick Eliminar");
        botoneliminar.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Eliminar");
    	    	//Intent miIntent = new Intent(EntrenamientoActivity.this, EquiposActivity.class);
    	    	//startActivity(miIntent);
    	    	//EntrenamientoActivity.this.finish();
    	    }
    	});
    }
}