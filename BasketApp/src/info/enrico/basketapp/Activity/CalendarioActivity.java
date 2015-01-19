package info.enrico.basketapp.Activity;

import info.enrico.basketapp.R;
import info.enrico.basketapp.R.id;
import info.enrico.basketapp.R.layout;
import info.enrico.basketapp.R.xml;
import info.enrico.basketapp.Calendar.ExtendedCalendarView;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class CalendarioActivity extends PreferenceActivity {
	Button botonmenu;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
    	
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.calendario);
        
        ExtendedCalendarView calendar = (ExtendedCalendarView)findViewById(R.id.calendar);
        
        botonmenu = (Button) this.findViewById(R.id.botonmenu);
        botonmenu.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	//Intent miIntent = new Intent(CalendarioActivity.this, MenuActivity.class);
    	    	//startActivity(miIntent);
    	    	//CalendarioActivity.this.finish();
    	    }
    	});
    }
}