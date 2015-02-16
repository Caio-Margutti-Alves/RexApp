package com.main.rexx;

import gcm.Common;
import gcm.DataProvider;
import gcm.GcmListener;
import gcm.GcmUtil;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ContactsActivity extends ListActivity implements
		LoaderManager.LoaderCallbacks<Cursor>, GcmListener {

	private SimpleCursorAdapter adapter;
	private AlertDialog disclaimer;

	private GcmUtil gcmUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		adapter = new SimpleCursorAdapter(this, 
				R.layout.chat_list_item, 
				null, 
				new String[]{DataProvider.COL_NAME, DataProvider.COL_COUNT}, 
				new int[]{R.id.text1, R.id.text2},
				0);
		
		adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
			
			@Override
			public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
				switch(view.getId()) {
				case R.id.text2:
					int count = cursor.getInt(columnIndex);
					if (count > 0) {
						((TextView)view).setText(String.format("%d new message%s", count, count==1 ? "" : "s"));
					}
					return true;					
				}
				return false;
			}
		});
		
		setListAdapter(adapter);
		
		gcmUtil = new GcmUtil(getApplicationContext());
		connect();
		
		getLoaderManager().initLoader(0, null, this);
	}

	private void connect() {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(Common.getChatId());
		actionBar.setSubtitle("connecting...");

		if (!TextUtils.isEmpty(Common.getServerUrl())
				&& !TextUtils.isEmpty(Common.getSenderId())
				&& gcmUtil.register(this)) {
			onRegister(true);
		} else {
			onRegister(false);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (disclaimer == null) {
			if (TextUtils.isEmpty(Common.getChatId())) {
				connect();
			}
		}
	}

	@Override
	public void onRegister(boolean status) {
		if (status) {
			getActionBar().setTitle(Common.getChatId());
			getActionBar().setSubtitle("online");
		} else {
			getActionBar().setSubtitle("offline");
		}
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		Intent intent = new Intent(this, ChatActivity.class);
		intent.putExtra(Common.PROFILE_ID, String.valueOf(id));
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacts, menu);
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
	protected void onDestroy() {
		if (disclaimer != null)
			disclaimer.dismiss();
		gcmUtil.cleanup();
		super.onDestroy();
	}

	// ----------------------------------------------------------------------------

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		CursorLoader loader = new CursorLoader(this,DataProvider.CONTENT_URI_PROFILE, new String[] {
						DataProvider.COL_ID, DataProvider.COL_NAME,DataProvider.COL_COUNT }, null, null,
				DataProvider.COL_COUNT + " DESC, " + DataProvider.COL_ID
						+ " DESC");
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}

}
