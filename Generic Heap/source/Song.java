/**
 * This is a Song object
 * which consist of a song
 * title, its artist, the
 * album and an user's rating
 * 
 * @author Chou Thao
 * 
 */
public class Song implements Comparable<Song>{
	/**
	 * the title of a song
	 * 
	 */
	private String title;
	/**
	 * the artist of a song
	 * 
	 */
	private String artist;
	/**
	 * the album of a song
	 * 
	 */
	private String album;
	/**
	 * a user's rating of a song
	 * 
	 */
	private int rating;
	/**
	 * a constructor for a song
	 * which initializes the title,
	 * the artist, the album, and
	 * the rating
	 * 
	 * @param t    a title
	 * @param art  an artist
	 * @param alb  an album
	 * @param rate a rating
	 */
	public Song(String t, String art, String alb, int rate) {
		title = t;
		artist = art;
		album = alb;
		rating = rate;
	}
	/**
	 * obtains the title of a song
	 * 
	 * @return the title of a song
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * obtains an artist of a song
	 * 
	 * @return the artist of a song
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * obtains the album of a song
	 * 
	 * @return the album of a song
	 */
	public String getAlbum() {
		return album;
	}
	/**
	 * obtains the rating of a song
	 * 
	 * @return the rating of a song
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * obtain a song converted
	 * into a String
	 * 
	 */
	public String toString() {
		return title + ", " + artist + ", " + album + ", " + rating;
	}
	/**
	 * @override
	 * overrides the compareTo method
	 * to allow comparison between two
	 * Song object
	 * returns a -1 if less than, a 1
	 * if greater than and a 0 if both
	 * are equal
	 * 
	 */
	public int compareTo(Song s) {
		if (rating < s.getRating()) {
			return -1;
		}
		else if (rating > s.getRating()) {
			return 1;
		}
		else {
			return title.compareTo(s.getTitle());
		}
	}
	
	
}
