package info.enrico.basketapp.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SqLiteHelper
 * Digamos que en lugar de meter mano a SQLite a nuestra bola con jdbc
 * lo hacemos usando el SQLiteOpenHelper que es lo que le gusta a Android
 * Esta clase se requiere por otra de tipo DbAdapter.
 * La clase se encarga de inicializar la BBDD -si no existe-, de actualizarla
 * 
 * @author pello xabier altadill
 * @modify Enrico Martínez
 *
 */
class SqLiteHelper extends SQLiteOpenHelper {
	
	// Nombre de BBDD
	public static final String NOMBRE_BD = "basket.db";
	
	// Número de versión, número arbitrario 
	// que decidimos nosotros
	public static final int VERSION_BD = 1;

	// String Sql de creación de la tabla equipos, jugadores, entrenamientos, dibujos y categorias 
	//TODO Crear o no tablas dibujos y categorias para tema arrays.
	// se ejecutará si no existe la BD, o sea, la primera vez
	public final String SQLCREAREQUIPOS = "create table equipos "+
			 " (idEquipo integer primary key autoincrement, " +
			 " nombreEquipo text not null);";
	public final String SQLCREARJUGADORES = "create table jugadores " +
			 " (idJugador integer primary key autoincrement, " +
			 " nombreJugador text not null, " +
			 " idEquipoJugador text, " +
			 " fechNacJugador text, " +
			 " pesoJugador integer, " +
			 " alturaJugador integer, " +
			 " tfnJugador integer, " +
			 " imagenJugador text, " +
			 " detallesJugador text);";
	public final String SQLCREARENTRENAMIENTOS = "create table entrenamientos " +
			 " (idEntrenamiento integer primary key autoincrement, " +
			 " nombreEntrenamiento text not null, " +
			 " idCategoriaEntrenamiento text, " +
			 " idDibujoEntrenamiento text, " +
			 " explicacionEntrenamiento text, " +
			 " notasEntrenamiento text);";
	public final String SQLCREARDIBUJOS = "create table dibujos "+
			 " (idDibujo string primary key, " +
			 " nombreDibujo text not null);";
	public final String SQLCREARCATEGORIAS = "create table categorias "+
			 " (idCategoria string primary key, " +
			 " nombreCategoria text not null);";

	/**
	 * Constructor
	 * llama al constructor padre
	 * Le pasa el contexto, que se refiere al activity actual
	 * @param contexto 
	 */
	SqLiteHelper(Context contexto) {
		super(contexto, NOMBRE_BD, null, VERSION_BD);
	}

	/**
	 * onCreate
	 * Se ejecuta en caso de que la BD no exista
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia de creación de la tabla equipo.
		db.execSQL("DROP TABLE IF EXISTS equipos");
		db.execSQL("DROP TABLE IF EXISTS jugadores");
		db.execSQL("DROP TABLE IF EXISTS entrenamientos");
		db.execSQL("DROP TABLE IF EXISTS dibujos");
		db.execSQL("DROP TABLE IF EXISTS categorias");
		db.execSQL(SQLCREAREQUIPOS);
		Log.d("DEBUG","Ok, BD equipos creada");
		db.execSQL(SQLCREARJUGADORES);
		Log.d("DEBUG","Ok, BD jugadores creada");
		db.execSQL(SQLCREARENTRENAMIENTOS);
		Log.d("DEBUG","Ok, BD entrenamientos creada");
		db.execSQL(SQLCREARDIBUJOS);
		Log.d("DEBUG","Ok, BD dibujos creada");
		db.execSQL(SQLCREARCATEGORIAS);
		Log.d("DEBUG","Ok, BD categorias creada");
	}
		  
	/**
	 * onUpgrade
	 * Se ejecuta de forma automáticamente en caso de que
	 * estemos en una nueva versión.
	 *  
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Sacamos un log para contar lo que ha pasado
		Log.w("SqLiteHelper", "Actualizando desde versión " + oldVersion
				+ " a " + newVersion + ", los datos serán eliminados");

		// En este caso en el upgrade realmente
		// lo que hacemos es cargarnos lo que hay...
		//db.execSQL("DROP TABLE IF EXISTS equipos");

		// ... y lo volvemos a generar
		onCreate(db);
		Log.d("DEBUG","Ok, BD regenerada");
	}
	
}