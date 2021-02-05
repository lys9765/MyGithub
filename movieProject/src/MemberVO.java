
public class MemberVO {
	private String mbrID;
	private String pwd;
	private String name;
	private String tel;
	private String email;
	private String birth;
	private int gender;
	private String rgtrdate;
	public MemberVO() {
		
	}
	public MemberVO(String email) {
		this.email = email;
		
	}
	public MemberVO(String mbrID, String pwd, String name, String tel ,String email,  String birth, int gender, String rgtrdate) {
		this(email);
		this.mbrID = mbrID;
		this.pwd = pwd;
		this.name = name;
		this.tel = tel;
		this.birth = birth;
		this.gender = gender;
		this.rgtrdate = rgtrdate;
	}
	
	public String getMbrID() {
		return mbrID;
	}
	public void setMbrID(String mbrID) {
		this.mbrID = mbrID;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getRgtrdate() {
		return rgtrdate;
	}
	public void setRgtrdate(String rgtrdate) {
		this.rgtrdate = rgtrdate;
	}
	
}
