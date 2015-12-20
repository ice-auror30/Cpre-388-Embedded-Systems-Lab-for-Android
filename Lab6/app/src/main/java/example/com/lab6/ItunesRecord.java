package example.com.lab6;

/**
 * 
 * This class is used to hold an Itunes record
 *
 */
public class ItunesRecord {
	
	/**
	 * A string to represent the album title
	 */
	private String albumTitle;
	
	/**
	 * A string to represent the song title
	 */
	private String songTitle;

	/**
	 * Constructor for an ItunesRecord
	 * @param songTitle a String representation of the song title
	 * @param albumTitle a String representation of the album title
	 */
	public ItunesRecord(String albumTitle, String songTitle) {
		super();
		this.albumTitle = albumTitle;
		this.songTitle = songTitle;
	}
	
	public String getAlbumTitle() {
		return albumTitle;
	}
	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}
	public String getSongTitle() {
		return songTitle;
	}
	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

}
