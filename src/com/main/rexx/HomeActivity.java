package com.main.rexx;

import java.util.ArrayList;
import java.util.List;

import user.User;
import viewUtils.ListArrayAdpater;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import animal.Animal;

public class HomeActivity extends ListActivity {

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		if (item.equals(getString(R.string.find_partners))) {
			Intent intent = new Intent(this, FindPartnerActivity.class);
			startActivity(intent);
		} else if (item.equals(getString(R.string.chat))) {
			Intent intent = new Intent(this, ContactsActivity.class);
			startActivity(intent);
		} else if (item.equals(getString(R.string.animals))) {
			Intent intent = new Intent(this, AnimalActivity.class);
			startActivity(intent);
		} else if (item.equals(getString(R.string.profile))) {
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		} else if (item.equals(getString(R.string.settings))) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		} else if (item.equals(getString(R.string.invite))) {
			invite(v);
		} else {
			Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// lstvOptions = (ListView) findViewById(R.id.lstvOptions);

		User.setAnimals(Animal.getAnimalByUserId(User.getId()));

		String[] values = new String[] { getString(R.string.find_partners),
				getString(R.string.chat), getString(R.string.animals),
				getString(R.string.profile), getString(R.string.settings),
				getString(R.string.invite) };

		ListArrayAdpater adapter = new ListArrayAdpater(this, values);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_search:
			// openSearch();
			return true;
		case R.id.action_settings:
			// openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void invite(View v) {

		Resources resources = getResources();

		Intent emailIntent = new Intent();
		emailIntent.setAction(Intent.ACTION_SEND);

		emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.invite_message)));
		emailIntent.putExtra(Intent.EXTRA_SUBJECT,resources.getString(R.string.subject));
		emailIntent.setType("message/rfc822");

		PackageManager pm = getPackageManager();
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		sendIntent.setType("text/plain");

		Intent openInChooser = Intent.createChooser(emailIntent,resources.getString(R.string.title_activity_invite));

		List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
		List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
		for (int i = 0; i < resInfo.size(); i++) {
			// Extract the label, append it, and repackage it in a LabeledIntent
			ResolveInfo ri = resInfo.get(i);
			String packageName = ri.activityInfo.packageName;
			if (packageName.contains("android.email")) {
				emailIntent.setPackage(packageName);
			} else if (packageName.contains("twitter")
					|| packageName.contains("facebook")
					|| packageName.contains("mms")
					|| packageName.contains("android.gm")) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(packageName,ri.activityInfo.name));
				intent.setAction(Intent.ACTION_SEND);
				intent.setType("text/plain");
				if (packageName.contains("twitter")) {
					intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.invite_message));
				} else if (packageName.contains("facebook")) {
					intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.url_invite_message));
				} else if (packageName.contains("mms")) {
					intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.invite_message));
				} else if (packageName.contains("android.gm")) {
					intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.invite_message)));
					intent.putExtra(Intent.EXTRA_SUBJECT,resources.getString(R.string.subject));
					intent.setType("message/rfc822");
				}

				intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
			}
		}

		// convert intentList to array
		LabeledIntent[] extraIntents = intentList
				.toArray(new LabeledIntent[intentList.size()]);

		openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
		startActivity(openInChooser);

		/*
		 * Intent sendIntent = new Intent(Intent.ACTION_SEND); Intent chooser =
		 * Intent.createChooser(sendIntent,
		 * getString(R.string.title_activity_invite));
		 * sendIntent.setType("text/plain");
		 * 
		 * List<ResolveInfo> activityList =
		 * getPackageManager().queryIntentActivities(sendIntent, 0);
		 * 
		 * boolean resolved = false; for (final ResolveInfo app : activityList)
		 * { if ((app.activityInfo.name).contains("facebook")) { final
		 * ActivityInfo activity = app.activityInfo; final ComponentName name =
		 * new ComponentName(activity.applicationInfo.packageName,
		 * activity.name); sendIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		 * sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
		 * Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		 * sendIntent.setComponent(name); resolved = true; break; } else if
		 * (app.activityInfo.name.equals("com.twitter.android.PostActivity")) {
		 * final ActivityInfo activity = app.activityInfo; final ComponentName
		 * name = new ComponentName(activity.applicationInfo.packageName,
		 * activity.name); sendIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		 * sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
		 * Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		 * sendIntent.setComponent(name); resolved = true; break; } else
		 * if((app.activityInfo.name).contains("gmail")){
		 * sendIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.subject); resolved
		 * = true; break; } } //if(resolved){ startActivity(chooser); //}else{
		 * Toast.makeText(this, "Twitter app isn't found",
		 * Toast.LENGTH_LONG).show(); //}
		 */
	}

}
