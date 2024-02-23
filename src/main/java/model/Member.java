package model;

public class Member {
	private int member_id;
	private String name;
	private String phone; 
	private boolean active;
	private String profile_url;
	private String address;
	private String org_name;
	public Member() {
		
	}
	public Member(int member_id, String name, String phone, boolean active, String profile_url,
			String address, String org_name) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.phone = phone;
		this.active = active;
		this.profile_url = profile_url;
		this.address = address;
		this.org_name = org_name;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getProfile_url() {
		return profile_url;
	}
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

}
