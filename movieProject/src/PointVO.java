
public class PointVO {
	private String mbrID;
	private int point;
	private int rcode;
	private String rgtrdate;
	
	public PointVO() {

	}
	public PointVO(int point, int rcode) {
		this.point = point;
		this.rcode = rcode;
	}
	public PointVO(String mbrID, int point, int rcode, String rgtrdate) {
		this(point, rcode);
		this.mbrID = mbrID;
		this.rgtrdate = rgtrdate;
	}	
	public String getMbrID() {
		return mbrID;
	}
	public void setMbrID(String mbrID) {
		this.mbrID = mbrID;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getRcode() {
		return rcode;
	}
	public void setRcode(int rcode) {
		this.rcode = rcode;
	}
	public String getRgtrdate() {
		return rgtrdate;
	}
	public void setRgtrdate(String rgtrdate) {
		this.rgtrdate = rgtrdate;
	}
	
}
