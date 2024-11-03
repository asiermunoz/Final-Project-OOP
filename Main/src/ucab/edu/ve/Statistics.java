package ucab.edu.ve;

public class Statistics {
    private int totalScore;
    private Information information;

    public Statistics(int totalScore, Information information) {
        this.totalScore = totalScore;
        this.information = information;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public void setStatsAlias(String alias) {
        this.information.setAlias(alias);
    }

    public String getStatsAlias() {
        return information.getAlias();
    }
}
