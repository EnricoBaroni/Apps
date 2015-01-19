package info.enrico.basketapp.Activity;

import info.enrico.basketapp.R;
import info.enrico.basketapp.Class.Equipo;
import info.enrico.basketapp.Class.Jugador;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterEquipos extends BaseAdapter{

	protected Activity activity;
	protected ArrayList<Equipo> items;

	public AdapterEquipos(Activity activity, ArrayList<Equipo> items) {
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
		return items.get(position).getIdEquipo();
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
		Equipo equi = items.get(position);
		//Rellenamos el numero
		TextView numero = (TextView) v.findViewById(R.id.jugador_num);
		numero.setText(equi.getIdEquipo());
		//Rellenamos el nombre
		TextView nombre = (TextView) v.findViewById(R.id.jugador_nom);
		nombre.setText(equi.getNombreEquipo());

		// Retornamos la vista
		return v;
	}
}