package drawer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import camera.CameraActivity;

import com.main.rexx.R;
import com.main.rexx.RegisterAnimalActivity;

import drawer.utils.Constant;
import drawer.utils.Menus;


public class AnimalFragment extends Fragment {

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
			cameraActivity.dispatchTakePictureIntent(ACTION_TAKE_PHOTO);
		}
	};
	
	public AnimalFragment newInstance(String text){
		AnimalFragment mFragment = new AnimalFragment();		
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.activity_find_partner, container, false);
		super.onCreate(savedInstanceState);
		
		btnPicture = (Button) rootView.findViewById(R.id.btnPhoto);
		imgvAnimal = (ImageView)  rootView.findViewById(R.id.imgvAnimal);
		cameraActivity = new CameraActivity(imgvAnimal);
		cameraActivity.setBtnListenerOrDisable(btnPicture,btnPictureOnClickListener, MediaStore.ACTION_IMAGE_CAPTURE);
	
		
		spnAnimals = (Spinner) rootView.findViewById(R.id.spnAnimais);
		//ArrayAdapter<Animal> adapter = new ArrayAdapter<Animal>(this,android.R.layout.simple_spinner_item, User.getAnimals());
		//spnAnimals.setAdapter(adapter)
				    
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;
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
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu items for use in the action bar
		super.onCreateOptionsMenu(menu, inflater);		
		inflater.inflate(R.menu.menu, menu);
		 	    
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(Menus.SEARCH));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.action_edit:
			intent = new Intent(this.getActivity(), RegisterAnimalActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_new:
			intent = new Intent(this.getActivity(), RegisterAnimalActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
