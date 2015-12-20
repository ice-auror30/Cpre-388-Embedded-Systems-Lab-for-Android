package com.agenda.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class represents the Event object whose data will be stored in the database.
 */
public class Event {
	
	// Unique ID of event. User does not enter this information.
	private long id;
	
	// Title of event
	private String title;
	
	// Location of event
	private String location;
	
	// String containing start date and time of event
	private String startTime;
	
	// String containing end date and time of event
	private String endTime;
	
	// Optional details regarding event
	private String details;
	
	/**
	 * Constructs an event given data from database
	 * @param id
	 * @param title
	 * @param location
	 * @param startTime
	 * @param endTime
	 * @param details
	 */
	public Event(long id, String title, String location, String startTime, 
			String endTime, String details) {
		this.id = id;
		this.title = title;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
		this.details = details;
	}
	
	/**
	 * Constructs an empty Event
	 */
	public Event() { }

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the startTime
	 */
	public String getReadableStartTime() {
		return startTime;
	}
	
	/**
	 * Returns the start time in the form of a Date object
	 * @return
	 */
	public Date getStartTime() {
		try {
			return new SimpleDateFormat("MMMM d, yyyy, 'at' h:mm a", Locale.US).parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getReadableEndTime() {
		return endTime;
	}
	
	/**
	 * Returns the end time in the form of a Date object
	 * @return
	 */
	public Date getEndTime() {
		try {
			return new SimpleDateFormat("MMMM d, yyyy, 'at' h:mm a", Locale.US).parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return title;
	}

}
