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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class EntrenamientoActivity extends ListActivity {
	Button botonsiguiente;
	Button botonanterior;
	Button botoneditar;
	TextView titulo;
	TextView explicacion;
	TextView notas;
	ImageView dibujo;
	Spinner tema;
	
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
        setContentView(R.layout.entrenamiento);                   
        
        Log.d("ENRICO","Cargando botones");        
        botonsiguiente = (Button) this.findViewById(R.id.botonSiguiente_entrenamiento);
        botonanterior = (Button) this.findViewById(R.id.botonAnterior_entrenamiento);
        botoneditar = (Button) this.findViewById(R.id.botonEditar_entrenamiento);
        titulo = (TextView) this.findViewById(R.id.titulo_entrenamiento);
        dibujo = (ImageView) this.findViewById(R.id.imagen_entrenamiento);
        tema = (Spinner) this.findViewById(R.id.spinTema_entrenamiento);
        explicacion = (TextView) this.findViewById(R.id.explicacion_entrenamiento);
        
        
        Log.d("ENRICO","OnClick Editar");
        botoneditar.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Editar");
    	    	//TODO Va a la Activity para editar entrenamientos   	    	
    	    }
    	});
        Log.d("ENRICO","OnClick Siguiente");
        botonsiguiente.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Siguiente");
    	    	//TODO Pasa al dibujo siguiente	    	
    	    }
    	});
        Log.d("ENRICO","OnClick Anterior");
        botonanterior.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Anterior");
    	    	//TODO Pasa al dibujo anterior 	    	
    	    }
    	});
    }
}
