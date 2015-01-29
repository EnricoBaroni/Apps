package info.enrico.basketapp.activity;

import info.enrico.basketapp.R;
import info.enrico.basketapp.bd.DbAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AnadirJugadorActivity extends Activity {
	Button botonficha;
	Button botondetalles;
	Button botoncrear;
	private DbAdapter db;
	private EditText nuevoNombre;
	private EditText nuevoFechNac;
	private EditText nuevoPeso;
	private EditText nuevoAltura;
	private EditText nuevoTfn;
	private TextView txtResultado;
	private ImageView imagen;
	private Cursor cursor = null;
	

    private String selectedImagePath;
    private static final int SELECT_PICTURE = 1;
	
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
     	nuevoNombre = (EditText) findViewById(R.id.etAnadirNombreJugador);
     	nuevoFechNac = (EditText) findViewById(R.id.etAnadirFechNacJugador);
     	nuevoPeso = (EditText) findViewById(R.id.etAnadirPesoJugador);
     	nuevoAltura = (EditText) findViewById(R.id.etAnadirAlturaJugador);
     	nuevoTfn = (EditText) findViewById(R.id.etAnadirTfnJugador);
     	imagen = (ImageView) findViewById(R.id.fotoJugador);
     	//TODO MAYBE ¿Igual en un futuro? Un textview que informa si el nuevo jugador no se puede insertar
        txtResultado = (TextView) findViewById(R.id.txtResultadoAnadirJugador);
     	//Botones
        botoncrear = (Button) this.findViewById(R.id.botonCrearAnadirJugador);
     	
     	db = new DbAdapter(this);
     	db.open();
     	
     	Log.d("ENRICO","OnClick Imagen");
		imagen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Imagen");
				
				Intent intent = new Intent();
	            intent.setType("image/*");
	            intent.setAction(Intent.ACTION_GET_CONTENT);
	            startActivityForResult(
	                    Intent.createChooser(intent, "Select Picture"),
	                    SELECT_PICTURE);
				//TODO Esta sin terminar lo de las imagenes, preguntar a lacasta
			}
		});
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                //imagen.setVisibility(View.VISIBLE);
                imagen.setImageURI(selectedImageUri);
            }
        }
    }
    
    @SuppressWarnings("deprecation")
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
        
	/**
	 * insertarRegistro
	 * Toma la información de la caja de texto y la inserta
	 * como nuevo registro
	 * @param v
	 */
	public void insertarRegistro (View v) {
		String nombre = nuevoNombre.getText().toString();
		String fechNac = nuevoFechNac.getText().toString();
		int peso = Integer.parseInt(nuevoPeso.getText().toString());
		int altura = Integer.parseInt(nuevoAltura.getText().toString());
		int tfn = Integer.parseInt(nuevoTfn.getText().toString());
		
		// Inserta los valores de las cajas de texto en la tabla notas.
		if(nombre.length() == 0){
			txtResultado.setText("Introduce un nombre de jugador al menos");
		}else{
			db.insertarJugador(nombre, idEquipo, fechNac, peso, altura, tfn, "imagen", "detalles");

			// Notificamos al usuario
			Toast.makeText(getApplicationContext(), "Nuevo equipo introducido: " + nuevoNombre, Toast.LENGTH_SHORT).show();
		
			// Vacíamos la caja de texto
			nuevoNombre.setText("");
			nuevoFechNac.setText("");
			nuevoPeso.setText("");
			nuevoAltura.setText("");
			nuevoTfn.setText("");
			lanzarActivityJugadores(nuevoNombre.getText().toString());
		}		
	}
	
	public void lanzarActivityJugadores(String resultado) {
		txtResultado.setText("");
		Intent intent = new Intent(AnadirJugadorActivity.this, JugadoresActivity.class);
		Bundle b = new Bundle();
		b.putString("resultadoAnadirJugador", resultado);
		intent.putExtras(b);
		startActivity(intent);
		finish();
	}
}