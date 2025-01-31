package it.unisa.di.approidlab.threadno;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity {
	private ImageView imageView;
	private TextView counterTextView;
	private TextView loadingTextView;
	private int index = 1;
	private int counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		
		imageView = (ImageView)findViewById(R.id.imageView);
		counterTextView = (TextView)findViewById(R.id.counterTextView);
		loadingTextView = findViewById(R.id.textLoading);
	}
	
	public void loadButtonPressed(View v) {
		loadingTextView.setText("Loading ...");
		// Questo messaggio non verrà mai visualizzato perché il refresh della UI viene fatto alla dopo
		// l'esecuzione del metodo e quindi il testo verrà aggiornato con la stringa vuota dall'ultima istruzione (vedi sotto)

		/* Ritardo di 8 secondi */
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/* procediamo */
		Bitmap bmap = null;
		switch(index) {
		case 1:
			bmap = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
			index = 2;
			break;
		case 2:
			bmap = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
			index = 3;
			break;
		case 3:
			bmap = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
			index = 1;
			break;
		}
		imageView.setImageBitmap(bmap);
		// Togliamo la stringa "Loading ..." che in realtà non è mai stata visualizzata
		loadingTextView.setText("");
	}
	
	public void counterButtonPressed(View v) {
		counter++;
		counterTextView.setText(""+counter);
	}
}
