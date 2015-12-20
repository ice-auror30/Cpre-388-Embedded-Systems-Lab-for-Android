package com.example.agenda;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.agenda.models.AgendaDataSource;
import com.agenda.models.Event;

/**
 * This is the main activity that is in control when the application is opened.
 * Its corresponding view is activity_event_list, and it displays a list of the titles
 * of the events stored in the database.
 */
public class EventListActivity extends ListActivity {
	
	private AgendaDataSource datasource;

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);
		
		datasource = AgendaDataSource.getInstance(this);
		datasource.open();
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		// Get ID of selected event based on position in list
		long eventId = datasource.getAllEvents().get(position).getId();
		
		// Redirect to EventDetailsActivity
		Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
		detailsIntent.putExtra("cpre492.lab4.agenda.EventID", eventId);
		startActivity(detailsIntent);
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
		
		// List of Events in database
		List<Event> values = datasource.getAllEvents();
		
		// Set source of ListView to List of Events in database
		ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this, 
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_event_list, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		    case R.id.menu_add_event:
	    	Intent addIntent = new Intent(this, AddEventActivity.class);
			startActivity(addIntent);
		    return true;
		    default:
		    return super.onOptionsItemSelected(item);
		}
	}
}
