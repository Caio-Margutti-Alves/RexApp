package com.main.rexx;

import user.User;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.widget.ProfilePictureView;

public class ProfileActivity extends FragmentActivity {

	private static String firstName;
	private static String lastName;
	private static String login;
	private static String password;
	private static String rePassword;
	private static String mobileToken;
	// private static Bitmap profilePicture;
	private static int gender;
	private static String email;
	private static String dob;

	private EditText edtFisrtName;
	private EditText edtLastName;
	private EditText edtAge;
	private EditText edtGender;
	private EditText edtPassword;
	private EditText edtEmail;
	private EditText edtLogin;
	private Button btnMale;
	private Button btnFemale;
	private ProfilePictureView profilePicture;

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

	Button.OnClickListener lstnSeePassword = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
			edtPassword.setTransformationMethod(new PasswordTransformationMethod());
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		edtFisrtName = (EditText) findViewById(R.id.edtFirstName);
		edtLastName = (EditText) findViewById(R.id.edtLastName);
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		edtLogin = (EditText) findViewById(R.id.edtLogin);
		//edtAge = (EditText) findViewById(R.id.edtAge);
		btnMale = (Button) findViewById(R.id.btnProfileMale);
		btnFemale = (Button) findViewById(R.id.btnProfileFemale);
		profilePicture = (ProfilePictureView) findViewById(R.id.imgvProfile);

		edtFisrtName.setText(User.getFirstName());
		edtLastName.setText(User.getLastName());
		edtAge.setText(String.valueOf(User.getAge()));
		edtEmail.setText(User.getEmail());
		edtLogin.setText(User.getLogin());
		profilePicture.setProfileId(User.getFacebookId());

		if (User.getGender() == 1)
			btnMale.setBackgroundColor(Color.rgb(177, 235, 000));
		else
			btnFemale.setBackgroundColor(Color.rgb(177, 235, 000));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
