package model;

public class TranInvestor {
	private int team_id;
	private int event_id;
	private int judge_id;
	private String investor_type;
	private String tran_type;
	
	
	public TranInvestor(int team_id, int event_id, int judge_id,String investor_type, String tran_type) {
        this.team_id = team_id;
        this.event_id = event_id;
        this.judge_id = judge_id;
        this.investor_type=investor_type;
        this.tran_type=tran_type;
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
	
	
	public String getInvestor_type() {
		return investor_type;
	}
	public void setInvestor_type(String investor_type) {
		this.investor_type = investor_type;
	}
	
	
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}
	@Override
	public String toString() {
		return "TranInvestor [team_id=" + team_id + ", event_id=" + event_id + ", judge_id=" + judge_id
				+ ", investor_type=" + investor_type + ", tran_type=" + tran_type + "]";
	}
	

}
