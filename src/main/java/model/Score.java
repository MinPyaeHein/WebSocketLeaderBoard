package model;

public class Score {
    private double score;
    private int score_matrix_id;
    private String desc;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getScore_matrix_id() {
        return score_matrix_id;
    }

    public void setScore_matrix_id(int score_matrix_id) {
        this.score_matrix_id = score_matrix_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
