package info.enrico.basketapp.Activity;

import info.enrico.basketapp.R;
import info.enrico.basketapp.Class.Jugador;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterJugadores extends BaseAdapter{

	protected Activity activity;
	protected ArrayList<Jugador> items;

	public AdapterJugadores(Activity activity, ArrayList<Jugador> items) {
	    this.activity = activity;
	    this.items = items;
	  }

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		return items.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getIdJugador();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Generamos una convertView por motivos de eficiencia
		View v = convertView;

		//Asociamos el layout de la lista que hemos creado
		if(convertView == null){
			LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.equipos_item, null);
		}

		// Creamos un objeto jugador
		Jugador jug = items.get(position);
		//Rellenamos el nombre
		TextView numero = (TextView) v.findViewById(R.id.jugador_num);
		numero.setText(jug.getIdJugador());
		//Rellenamos la fecha nacimiento
		TextView nombre = (TextView) v.findViewById(R.id.jugador_nom);
		nombre.setText(jug.getNombreJugador());

		// Retornamos la vista
		return v;
	}
}