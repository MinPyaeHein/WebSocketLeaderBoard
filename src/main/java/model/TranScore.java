package model;

import java.util.List;

public class TranScore {
	private int team_id;
	private int event_id;
	private int judge_id;
	private String token;
	private List<Score> tran_score;

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

	public List<Score> getTran_score() {
		return tran_score;
	}

	public void setTran_score(List<Score> tran_score) {
		this.tran_score = tran_score;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
