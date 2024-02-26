package model;

public class TranInvestor {
	private int team_id;
	private int event_id;
	private int judge_id;
	
	public TranInvestor(int team_id, int event_id, int judge_id) {
        this.team_id = team_id;
        this.event_id = event_id;
        this.judge_id = judge_id;
    }
	public TranInvestor() {
		super();
		
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
	public int getJudge_id() {
		return judge_id;
	}
	public void setJudge_id(int judge_id) {
		this.judge_id = judge_id;
	}
	

}
