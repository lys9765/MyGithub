
public class ReviewVO {
	private int gradecode;
	private int rcode;
	private int grade;
	private String review;
	private String rgtrdate;
	
	public ReviewVO() {
		
	}
	public ReviewVO(int grade, String review) {
		this.grade = grade;
		this.review = review;
	}
	public ReviewVO(int gradecode, int rcode, int grade, String review, String rgtrdate) {
		this(grade, review);
		this.gradecode = gradecode;
		this.rcode = rcode;
		this.rgtrdate = rgtrdate;
	}

	public int getGradecode() {
		return gradecode;
	}

	public void setGradecode(int gradecode) {
		this.gradecode = gradecode;
	}

	public int getRcode() {
		return rcode;
	}

	public void setRcode(int rcode) {
		this.rcode = rcode;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getRgtrdate() {
		return rgtrdate;
	}

	public void setRgtrdate(String rgtrdate) {
		this.rgtrdate = rgtrdate;
	}
	
}
