package com.example.agenda;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.agenda.models.AgendaDataSource;
import com.agenda.models.Event;

/**
 * This activity is called when an event title on the main page is pressed. Its
 * corresponding view is activity_event_details, which presents all of the information
 * about the selected event that is stored in the database.
 */
public class EventDetailsActivity extends Activity {
	
	private AgendaDataSource datasource;

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_details);
		
		datasource = AgendaDataSource.getInstance(this);
		datasource.open();
		
		// Determine which event was pressed
		Bundle extras = getIntent().getExtras();
		long eventId = extras.getLong("cpre492.lab4.agenda.EventID");
		
		// Selected event object
		Event selected = datasource.getEvent(eventId);
		
		/*
		 * Set views to reflect event data
		 */
		
		TextView temp = (TextView) findViewById(R.id.titleOutput);
		temp.setText(selected.getTitle());
		
		temp = (TextView) findViewById(R.id.locationOutput);
		temp.setText(selected.getLocation());
		
		temp = (TextView) findViewById(R.id.startOutput);
		temp.setText(selected.getReadableStartTime().toString());
		
		temp = (TextView) findViewById(R.id.endOutput);
		temp.setText(selected.getReadableEndTime().toString());
		
		temp = (TextView) findViewById(R.id.deetsOutput);
		temp.setText(selected.getDetails());
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_event_details, menu);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}
