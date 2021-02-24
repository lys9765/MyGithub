
public class MovieVO {
	private int movieCode;
	private String movieName;
	private String directorName;
	private int jenreCode;
	private String releaseDate;
	private String mrating;
	private String rtime;
	private String summary;
	private String rgtrdate;
	
	public MovieVO() {
		
	}
	public MovieVO(String movieName, String directorName, int jenreCode,
			String releaseDate, String mrating, String rtime, String summary) {
		
		this.movieName = movieName;
		this.directorName = directorName;
		this.jenreCode = jenreCode;
		this.releaseDate = releaseDate;
		this.mrating = mrating;
		this.rtime = rtime;
		this.summary = summary;
	}
	public MovieVO(int movieCode, String movieName, String directorName, int jenreCode,
			String releaseDate, String mrating, String rtime, String summary, String rgtrdate) {
		
		this(movieName, directorName, jenreCode, releaseDate, mrating, rtime, summary);
		this.movieCode = movieCode;
		this.rgtrdate = rgtrdate;
	}
	public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public int getJenreCode() {
		return jenreCode;
	}

	public void setJenreCode(int jenreCode) {
		this.jenreCode = jenreCode;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMrating() {
		return mrating;
	}

	public void setMrating(String mrating) {
		this.mrating = mrating;
	}

	public String getRtime() {
		return rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRgtrdate() {
		return rgtrdate;
	}

	public void setRgtrdate(String rgtrdate) {
		this.rgtrdate = rgtrdate;
	}
	
}
