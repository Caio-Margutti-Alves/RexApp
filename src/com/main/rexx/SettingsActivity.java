package com.main.rexx;

import viewUtils.RangeSeekBar;
import viewUtils.RangeSeekBar.OnRangeSeekBarChangeListener;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends FragmentActivity {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_settings);

		btnMale = (Button) findViewById(R.id.btnLookingMale);
		btnFemale = (Button) findViewById(R.id.btnLookingFemale);
		btnMale = (Button) findViewById(R.id.btnLookingMale);
		btnFemale = (Button) findViewById(R.id.btnLookingFemale);
		rskbAgeBetween = new RangeSeekBar<Integer>(1, 50, this);
		((ViewGroup) findViewById(R.id.SettingsForm)).addView(rskbAgeBetween);
		skbRadius = (SeekBar) findViewById(R.id.skbRadius);
		txtvSearchAgeResp = (TextView) findViewById(R.id.txtvSearchAgeResp);
		txtvSearchRadiusResp = (TextView) findViewById(R.id.txtvSearchRadiusResp);

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}
}
