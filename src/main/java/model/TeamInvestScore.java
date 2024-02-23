package model;

public class TeamInvestScore {
private	String teamName;
private  int team_id;	
private int event_id;
private double total_amount;
public TeamInvestScore() {
	
}
public TeamInvestScore(String teamName, int team_id, int event_id, double total_amount) {
	super();
	this.teamName = teamName;
	this.team_id = team_id;
	this.event_id = event_id;
	this.total_amount = total_amount;
}
public String getTeamName() {
	return teamName;
}
public void setTeamName(String teamName) {
	this.teamName = teamName;
}
public int getTeam_id() {
	return team_id;
}
public void setTeam_id(int team_id) {
	this.team_id = team_id;
}
public int getEvent_id() {
	return event_id;
}
public void setEvent_id(int event_id) {
	this.event_id = event_id;
}
public double getTotal_amount() {
	return total_amount;
}
public void setTotal_amount(double total_amount) {
	this.total_amount = total_amount;
}

}
