package fr.uga.l3miage.pc.prisonersdilemma.model;

public class ResultMessage {
    private String reponseJ1;
    private String reponseJ2;

    private int scoreJ1;
    private int scoreJ2;

    public ResultMessage() {
        this.reponseJ1 = "";
        this.reponseJ2 = "";
        this.scoreJ1 = 0;
        this.scoreJ2 = 0;
    }

    public ResultMessage(String reponseJ1,String reponseJ2, int scoreJ1, int scoreJ2) {
        this.reponseJ1 = reponseJ1;
        this.reponseJ2 = reponseJ2;
        this.scoreJ1 = scoreJ1;
        this.scoreJ2 = scoreJ2;
    }


    public void setReponseJ1(String reponseJ1) {
        this.reponseJ1 = reponseJ1;
    }
    public void setReponseJ2(String reponseJ2) {
        this.reponseJ2 = reponseJ2;
    }
    public void setScoreJ1(int scoreJ1) {
        this.scoreJ1 = scoreJ1;
    }
    public void setScoreJ2(int scoreJ2) {
        this.scoreJ2 = scoreJ2;
    }

    public String getReponseJ1() {
        return reponseJ1;
    }
    public String getReponseJ2() {
        return reponseJ2;
    }
    public int getScoreJ1() {
        return scoreJ1;
    }
    public int getScoreJ2() {
        return scoreJ2;
    }
}
