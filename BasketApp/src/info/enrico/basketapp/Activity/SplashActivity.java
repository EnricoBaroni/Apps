package info.enrico.basketapp.Activity;

import info.enrico.basketapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity {
    /** 
     * onCreate
     *  método al que se llama la primera vez que se crea la actividad
     *  */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//Pantalla Completa
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);        
    	
    	// Debemos llamar al onCreate de la clase padre
        super.onCreate(savedInstanceState);
        
        // Establecemos el layout o diseño de página
        setContentView(R.layout.splash);
        	
        // Cargamos el título (Un TextView)
        TextView titulo = (TextView) findViewById(R.id.titulo);
        
        // Cargamos una animación desde resources
        Animation animacionFundido = AnimationUtils.loadAnimation(this, R.anim.fade);
        
        // Le asociamos la animación al TextView
        titulo.startAnimation(animacionFundido);

        // Cargamos el logo (un ImageView)
        ImageView logo = (ImageView) findViewById(R.id.LogoSplash);
        
        // Asignamos un onClickListener al logo: si se le hace
        // click se ejecutará el método onClick -comentado de momento-
        logo.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Intent miIntent = new Intent(SplashActivity.this, MenuActivity.class);
    	    	startActivity(miIntent);
    	    }
    	});

        // Asignamos otro listener a la animación de Fundido.
        // Hacemos que cuando termine la animación se cargue otro
        // activity
        animacionFundido.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,
                		MenuActivity.class));
            }

			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
        });
    }
}