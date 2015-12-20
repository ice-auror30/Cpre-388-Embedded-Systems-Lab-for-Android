package com.example.mediaplayerpreferences;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * CPRE 388 - Labs
 * 
 * Copyright 2013
 */
public class MyMediaPlayerActivity extends Activity {

	private static final int REQUEST_CODE_CHOOSE_SONG = 1;
	private static final int REQUEST_CODE_PREF = 2;

	// preferences
	boolean shuffle;
	boolean repeat;
	String mediaType;
	boolean showAlbum;
	String album;

	/**
	 * Other view elements
	 */
	private TextView songTitleLabel;

	/**
	 *  media player:
	 *  http://developer.android.com/reference/android/media/MediaPlayer.html 
	 */
	private MediaPlayer mp;

	/**
	 * Index of the current song being played
	 */
	private int currentSongIndex = 0;

	/**
	 * List of Sounds that can be played in the form of SongObjects
	 */
	private static ArrayList<SongObject> songsList = new ArrayList<SongObject>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_player_main);

		// load preferences
		loadPreferences();

		songTitleLabel = (TextView) findViewById(R.id.songTitle);

		// Initialize the media player
		mp = new MediaPlayer();

		// Getting all songs in a list
		populateSongsList();

		// By default play first song if there is one in the list
		playSong(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.media_player_menu, menu);
		return true;
	} 

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_choose_song:
			// Open SongList to display a list of audio files to play
			//TODO
			Intent intentChoose = new Intent(this, SongList.class);
			startActivityForResult(intentChoose, REQUEST_CODE_CHOOSE_SONG);

			return true;
		case R.id.menu_preferences:
			// Display Settings page
			//TODO
			Intent intentPref = new Intent(this, MediaPreferences.class);
			startActivityForResult(intentPref, REQUEST_CODE_PREF);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onRestart() {
		super.onRestart();

		loadPreferences();
		songsList.clear();
		populateSongsList();
	}
	

	/**
	 * Helper function to play a song at a specific index of songsList
	 * @param songIndex - index of song to be played
	 */
	public void  playSong(int songIndex){
		// Play song if index is within the songsList
		if (songIndex < songsList.size() && songIndex >= 0) {
			try {
				mp.stop();
				mp.reset();
				mp.setDataSource(songsList.get(songIndex).getFilePath());
				mp.prepare();
				mp.start();
				// Displaying Song title
				String songTitle = songsList.get(songIndex).getTitle();
				songTitleLabel.setText(songTitle);

				// Changing Button Image to pause image
				((Button)findViewById(R.id.playpausebutton)).setBackgroundResource(R.drawable.btn_pause);

				// Update song index
				currentSongIndex = songIndex;

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} else if (songsList.size() > 0) {
			playSong(0);
		}
	}


	/** 
	 * Get list of info for all sounds to be played
	 */
	public void populateSongsList(){
		//TODO add all songs from audio content URI to this.songsList

		// Get a Cursor object from the content URI 
		Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA},
				mediaType + " = 1",
				null,
				MediaStore.Audio.Media.TITLE);

		// Use the cursor to loop through the results and add them to 
		//		the songsList as SongObjects
		while (cursor.moveToNext()) {
			String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
			String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
			String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
			songsList.add(new SongObject(title, path));
		}

		if (shuffle) {
			Random rand = new Random(System.nanoTime());
			int numSongs = songsList.size();

			for (int i = 0; i < numSongs; i++) {
				SongObject o = songsList.remove(i);
				songsList.add(rand.nextInt(numSongs), o);
			}
		}
		if(showAlbum){

			Toast toast = Toast.makeText(getApplicationContext(), "List Populated", Toast.LENGTH_LONG);
			toast.show();
		}

	}

	/**
	 * Get song list for display in ListView
	 * @return list of Songs 
	 */
	public static ArrayList<SongObject> getSongsList(){
		return songsList;
	}

	public void onPlayPause(View v) {
		if (mp.isPlaying()) {
			v.setBackgroundResource(R.drawable.btn_play);
			mp.pause();
		} else {
			v.setBackgroundResource(R.drawable.btn_pause);
			mp.start();
		}

	}

	public void onSkip(View v) {
		int lastSongIndex = songsList.size() - 1;

		if (currentSongIndex < lastSongIndex) {
			currentSongIndex++;
		} else if (repeat) {
			currentSongIndex = 0;
		}

		playSong(currentSongIndex);
	}

	public void onBack(View v) {
		if (currentSongIndex > 0) {
			currentSongIndex--;
		} else if (repeat) {
			// set to index of last song
			currentSongIndex = songsList.size() - 1;
		}

		playSong(currentSongIndex);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_SONG) {
			currentSongIndex = data.getIntExtra("songIndex", 0);
			playSong(currentSongIndex);
		}

	}

	public void loadPreferences() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

		Resources res = getResources();
		shuffle = prefs.getBoolean(res.getString(R.string.shuffle_pref), false);
		repeat = prefs.getBoolean(res.getString(R.string.repeat_pref), true);
		showAlbum = prefs.getBoolean(res.getString(R.string.show_album), false);
		mediaType = prefs.getString(res.getString(R.string.type_pref), MediaStore.Audio.Media.IS_MUSIC);
	}

}
