package org.androidtitlan.soniditos;

import java.util.ArrayList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Soniditos extends Activity {
    private Integer NumBotones; 
    
    private TableLayout tbLayout;
    ArrayList<View> rowList = new ArrayList<View>();
	private Button	boton[];
	private Integer	audio[] 	= {	R.raw.grillos,	R.raw.drama,		R.raw.chewbacca,
									R.raw.nooo, 	R.raw.doh,			R.raw.jaja,
									R.raw.triste,	R.raw.trololo,		R.raw.sparta,
									R.raw.zelda, 	R.raw.benny_hill, 	R.raw.badbrums,
									R.raw.bazzinga, R.raw.r2d2,			R.raw.birdtheword,	
									R.raw.inetporn, R.raw.kamehame,		R.raw.burned,
									R.raw.chan,		R.raw.chan_doble,	R.raw.evillaugh,
									R.raw.aleluya,	R.raw.keyboardcat,	R.raw.lalala,
									R.raw.mario,	R.raw.beisball,		R.raw.muppets,
									R.raw.penny,	R.raw.shhahh,		R.raw.pacman,
									};

	private MediaPlayer  mpRes = null;
	Integer lastItem;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main);
        
        NumBotones = audio.length;
        boton = new Button[NumBotones];
        
        tbLayout =(TableLayout)findViewById(R.id.tb_layout);
        TableRow row = new TableRow(this);
        
        lastItem =-1;
        int i;
        for (i=0; i<NumBotones; i++){
        	//Incializando todos los botones 
        	boton[i] = new Button(this);
        	boton[i].setWidth(TableRow.LayoutParams.FILL_PARENT);
        	boton[i].setWidth(TableRow.LayoutParams.WRAP_CONTENT);
        	boton[i].setText( getResources().getStringArray(R.array.nombre_botones)[i] );
        	
        	//Ligango los botones con la logica del prog
        	final int tmp =i;
        	boton[i].setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				tocarSonido(tmp, audio[tmp]);
    				lastItem = tmp;
    			}
        	});
        	//Insertando los botones en la UI
        	row.addView(boton[i]);
        	
        	if ( (i+1) %3 == 0) {
        		tbLayout.addView(row);
       		 	row = new TableRow(this);
        	}
        }
        
        if ((i+1) %3 != 0)	tbLayout.addView(row);
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	if(mpRes!= null) pararSonido();
    }
    
    private void tocarSonido(int i, int sonido){
    	if (lastItem == i && mpRes != null && mpRes.isPlaying())	//si el boton anterior y al actual son iguales
        		pararSonido();
    	else{				//si el boton anterior y al actual son diferentes
    		pararSonido();
    		mpRes = MediaPlayer.create(getApplicationContext(), sonido);
    		mpRes.start();
    	}
    }
    
    private void pararSonido(){
    	if (mpRes != null){
    		mpRes.stop();
    		mpRes.release();
    		mpRes = null; 
    	}
    }
 
}