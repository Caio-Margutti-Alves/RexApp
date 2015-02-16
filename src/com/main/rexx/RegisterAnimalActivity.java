package com.main.rexx;

import java.util.Date;

import viewUtils.HintSpinner;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import camera.CameraActivity;

public class RegisterAnimalActivity extends CameraActivity {

	private String id;
	private String name;
	private int age;
	private String family;
	private String breed;
	private int weight;
	private Bitmap animalPicture;
	private String color1;
	private String color2;
	private String color3;
	private int gender;
	private String description;
	private Date dob;
	private int pedigree;

	private int max_age = 50;
	private int max_weight = 160;

	private CameraActivity cameraActivity;
	private Button btnTakePhoto;
	private Button btnUploadPhoto;
	private ImageView imgvRegisterAnimal;

	private Button btnMale;
	private Button btnFemale;
	private Button btnIncreaseAge;
	private Button btnDecreaseAge;
	private Button btnIncreaseWeight;
	private Button btnDecreaseWeight;
	private TextView txtvValueAge;
	private TextView txtvValueWeight;
	private HintSpinner spnColor1;
	private HintSpinner spnColor2;
	private HintSpinner spnColor3;
	private HintSpinner spnFamily;

	private static final int ACTION_TAKE_PHOTO = 1;

	Button.OnClickListener btnPhotoOnClickListener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			dispatchTakePictureIntent(ACTION_TAKE_PHOTO);
		}
	};

	Button.OnClickListener lstnMale = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			view.setBackgroundColor(Color.rgb(177, 235, 000));
			btnFemale.setBackgroundColor(Color.rgb(243, 243, 243));
			gender = 1;
		}
	};

	Button.OnClickListener lstnFemale = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			view.setBackgroundColor(Color.rgb(177, 235, 000));
			btnMale.setBackgroundColor(Color.rgb(243, 243, 243));
			gender = 2;
		}
	};

	Button.OnClickListener lstnIncrease = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.btnIncreaseAge:
				increaseNumber(1);
				break;
			case R.id.btnIncreaseWeight:
				increaseNumber(2);
				break;
			}
		}
	};

	Button.OnClickListener lstnDecrease = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.btnDecreaseAge:
				decreaseNumber(1);
				break;
			case R.id.btnDecreaseWeight:
				decreaseNumber(2);
				break;
			}
		}
	};

	private void increaseNumber(int op) {
		if (op == 1) {
			if (age < max_age) {
				age++;
				txtvValueAge.setText(age + "yr");
				return;
			}
		} else {
			if (weight < max_weight) {
				weight++;
				txtvValueWeight.setText(weight + "kg");
				return;
			}
		}
	}

	private void decreaseNumber(int op) {
		if (op == 1) {
			if (age > 0) {
				age--;
				txtvValueAge.setText(age + "yr");
				return;
			}
		} else {
			if (weight > 0) {
				weight--;
				txtvValueWeight.setText(weight + "kg");
				return;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_animal);

		btnTakePhoto = (Button) findViewById(R.id.btnTakePicture);
		imgvRegisterAnimal = (ImageView) findViewById(R.id.imgvRegisterAnimal);
		cameraActivity = new CameraActivity(imgvRegisterAnimal);
		super.setBtnListenerOrDisable(btnTakePhoto, btnPhotoOnClickListener,MediaStore.ACTION_IMAGE_CAPTURE);

		spnColor1 = (HintSpinner) findViewById(R.id.spnColor1);
		spnColor2 = (HintSpinner) findViewById(R.id.spnColor2);
		spnColor3 = (HintSpinner) findViewById(R.id.spnColor3);
		spnFamily = (HintSpinner) findViewById(R.id.spnFamily);
		btnMale = (Button) findViewById(R.id.btnAnimalMale);
		btnFemale = (Button) findViewById(R.id.btnAnimalFemale);
		btnIncreaseAge = (Button) findViewById(R.id.btnIncreaseAge);
		btnDecreaseAge = (Button) findViewById(R.id.btnDecreaseAge);
		btnIncreaseWeight = (Button) findViewById(R.id.btnIncreaseWeight);
		btnDecreaseWeight = (Button) findViewById(R.id.btnDecreaseWeight);
		txtvValueAge = (TextView) findViewById(R.id.txtvValueAge);
		txtvValueWeight = (TextView) findViewById(R.id.txtvValueWeight);

		ArrayAdapter<String> colors = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.colors_array));
		spnColor1.setAdapter(colors);
		spnColor2.setAdapter(colors);
		spnColor3.setAdapter(colors);

		ArrayAdapter<String> animals_family = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.animal_family_array));

		spnFamily.setAdapter(animals_family);
		
		btnIncreaseAge.setOnClickListener(lstnIncrease);
		btnIncreaseWeight.setOnClickListener(lstnIncrease);
		btnDecreaseAge.setOnClickListener(lstnDecrease);
		btnDecreaseWeight.setOnClickListener(lstnDecrease);

		btnMale.setBackgroundColor(Color.rgb(177, 235, 000));
		gender = 1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_animal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			// openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
