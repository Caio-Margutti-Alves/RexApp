package com.main.rexx;

import user.User;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import animal.Animal;
import camera.CameraActivity;


public class AnimalActivity extends CameraActivity {

	private static final int ACTION_TAKE_PHOTO = 1;
	// private static final int ACTION_TAKE_PHOTO_S = 2;
	//private static final int ACTION_TAKE_VIDEO = 3;

	private static final String TAG = "AnimalFragment";
	
	private CameraActivity cameraActivity;
	private ImageView imgvAnimal;
	private Spinner spnAnimals;
	private Button btnCadastrar;
	private Button btnPicture;

	Button.OnClickListener btnPictureOnClickListener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			dispatchTakePictureIntent(ACTION_TAKE_PHOTO);
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animal);
		
		btnPicture = (Button) findViewById(R.id.btnPhoto);
		imgvAnimal = (ImageView) findViewById(R.id.imgvAnimal);
		cameraActivity = new CameraActivity(imgvAnimal);
		super.setBtnListenerOrDisable(btnPicture,btnPictureOnClickListener, MediaStore.ACTION_IMAGE_CAPTURE);
	
		
		spnAnimals = (Spinner) findViewById(R.id.spnAnimais);
		//ArrayAdapter<Animal> adapter = new ArrayAdapter<Animal>(this,android.R.layout.simple_spinner_item, User.getAnimals());
		//spnAnimals.setAdapter(adapter)
				    
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.action_edit:
			intent = new Intent(this, RegisterAnimalActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_new:
			intent = new Intent(this, RegisterAnimalActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
