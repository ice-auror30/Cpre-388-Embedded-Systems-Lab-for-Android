package com.example.mediaplayerpreferences;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SongListAdapter extends ArrayAdapter<SongObject>{

	private Context context;
	private int layoutResourceId;
	private List<SongObject> data = null;

	public SongListAdapter(Context context, int layoutResourceId, List<SongObject> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		TextView tv;
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			tv = (TextView) row.findViewById(R.id.songTitleHolder);

			row.setTag(tv);
		} else {
			tv = (TextView) row.getTag();
		}
		
		SongObject song = data.get(position);

		tv.setText(song.getTitle());

		return row;
	}

}
