package ucab.edu.objects;

public class TimePlayed {
    private int hours;
    private int minutes;
    private int seconds;

    public TimePlayed(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void printTime() {
        System.out.println(this.hours+":"+this.minutes+":"+this.seconds);
    }


}
