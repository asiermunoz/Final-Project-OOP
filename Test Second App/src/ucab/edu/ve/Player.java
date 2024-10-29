package ucab.edu.ve;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private String email;
    private String name;
    private Statistics stats;

    public Player(String email, String name, Statistics stats) {
        this.email = email;
        this.name = name;
        this.stats = stats;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String alias) {
        this.name = alias;
    }

    public Statistics getStats() {
        return stats;
    }

    public void setStats(Statistics stats) {
        this.stats = stats;
    }

    public boolean validateEmail() {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(this.email);
        return matcher.matches();
    }

    public void setPlayerAlias(String alias) {
        this.stats.setStatsAlias(alias);
    }

    public String getPlayerAlias() {
        return stats.getStatsAlias();
    }

    public Information getPlayerInfo() {
        return getStats().getInformation();
    }

    public void printPlayerStats() {
        System.out.println("Total score: " + this.getStats().getTotalScore());
        this.getPlayerInfo().printAllInfo();
    }
}
