package drawer.fragment;

import viewUtils.RangeSeekBar;
import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.SeekBar;
import android.widget.TextView;

import com.main.rexx.R;

import drawer.utils.Constant;
import drawer.utils.Menus;

public class SettingsFragment extends Fragment {

	private int lookingFor;
	private int ageStart;
	private int ageEnd;
	private int radius;

	private Button btnMale;
	private Button btnFemale;
	private SeekBar skbRadius;
	private SeekBar skbAgeBetween;
	private RangeSeekBar<Integer> rskbAgeBetween;
	private TextView txtvSearchAgeResp;
	private TextView txtvSearchRadiusResp;

	Button.OnClickListener lstnMale = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			view.setBackgroundColor(Color.rgb(177, 235, 000));
			btnFemale.setBackgroundColor(Color.rgb(243, 243, 243));
		}
	};

	Button.OnClickListener lstnFemale = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			view.setBackgroundColor(Color.rgb(177, 235, 000));
			btnMale.setBackgroundColor(Color.rgb(243, 243, 243));
		}
	};

	SeekBar.OnSeekBarChangeListener skbRadiusListerner = new SeekBar.OnSeekBarChangeListener(){

	    @Override       
	    public void onStopTrackingTouch(SeekBar seekBar) {      
	        // TODO Auto-generated method stub      
	    }       

	    @Override       
	    public void onStartTrackingTouch(SeekBar seekBar) {     
	        // TODO Auto-generated method stub      
	    }       

	    @Override       
	    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {     
	    	radius = progress;
			txtvSearchRadiusResp.setText(radius + " km");
	    } 	
	};
	
	RangeSeekBar.OnRangeSeekBarChangeListener<Integer> rskbAgeListener = new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
		@Override
		public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
			// handle changed range values
			ageStart = minValue;
			ageEnd = maxValue;
			txtvSearchAgeResp.setText("From: " + ageStart + " to " + ageEnd);
		}
	};

	public SettingsFragment newInstance(String text){
		SettingsFragment mFragment = new SettingsFragment();		
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.activity_settings, container, false);
		super.onCreate(savedInstanceState);

		btnMale = (Button) rootView.findViewById(R.id.btnLookingMale);
		btnFemale = (Button) rootView.findViewById(R.id.btnLookingFemale);
		btnMale = (Button) rootView.findViewById(R.id.btnLookingMale);
		btnFemale = (Button) rootView.findViewById(R.id.btnLookingFemale);
		rskbAgeBetween = new RangeSeekBar<Integer>(1, 50, this.getActivity());
		((ViewGroup) rootView.findViewById(R.id.SettingsForm)).addView(rskbAgeBetween);
		skbRadius = (SeekBar) rootView.findViewById(R.id.skbRadius);
		txtvSearchAgeResp = (TextView) rootView.findViewById(R.id.txtvSearchAgeResp);
		txtvSearchRadiusResp = (TextView) rootView.findViewById(R.id.txtvSearchRadiusResp);

		btnMale.setOnClickListener(lstnMale);
		btnFemale.setOnClickListener(lstnFemale);
		rskbAgeBetween.setOnRangeSeekBarChangeListener(rskbAgeListener);
		skbRadius.setOnSeekBarChangeListener(skbRadiusListerner);
		
		skbRadius.setMax(100);
		skbRadius.setProgress(0);

		btnMale.setBackgroundColor(Color.rgb(177, 235, 000));

		// ageStart = skbAgeBetween.getProgress();
		// txtvSearchAgeResp.setText(ageEnd);

		// radius = skbRadius.getProgress();
		// txtvSearchAgeResp.setText(radius);
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;
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
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}
}
