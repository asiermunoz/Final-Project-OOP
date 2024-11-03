package ucab.edu.ve;

public class Information {
    private String alias;
    private boolean itsWinner;
    private int acumulatedPoints;
    private int testTotalTimePlayed;
    private int numberOfWords;

    public Information(String alias, boolean itsWinner, int acumulatedPoints, int testTotalTimePlayed, int numberOfWords) {
        this.alias = alias;
        this.itsWinner = itsWinner;
        this.acumulatedPoints = acumulatedPoints;
        this.testTotalTimePlayed = testTotalTimePlayed;
        this.numberOfWords = numberOfWords;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isItsWinner() {
        return itsWinner;
    }

    public void setItsWinner(boolean itsWinner) {
        this.itsWinner = itsWinner;
    }

    public int getTestTotalTimePlayed() {
        return testTotalTimePlayed;
    }

    public void setTestTotalTimePlayed(int testTotalTimePlayed) {
        this.testTotalTimePlayed = testTotalTimePlayed;
    }

    public int getAcumulatedPoints() {
        return acumulatedPoints;
    }

    public void setAcumulatedPoints(int acumulatedPoints) {
        this.acumulatedPoints = acumulatedPoints;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public void printAllInfo() {
        System.out.println("Alias: " + getAlias());
        System.out.println("Did the player win? " + isItsWinner());
        System.out.println("Acumulated points: " + getAcumulatedPoints());
        System.out.println("Total time played: " + getTestTotalTimePlayed());
        System.out.println("Number of words: " + getNumberOfWords());
    }
}