package ucab.edu.objects;

import java.time.Duration;
import java.time.LocalTime;

public class GameTimer {

    private int oldSeconds = 0;
    private int oldMinutes = 0;
    private int oldHours = 0;
    private int newSeconds = 0;
    private int newMinutes = 0;
    private int newHours = 0;
    private int finalSeconds = newSeconds + oldSeconds;
    private int finalMinutes = newMinutes + oldMinutes;
    private int finalHour = newHours + oldHours;

    public GameTimer(int oldSeconds, int oldMinutes, int oldHours, int newSeconds, int newMinutes, int newHours) {
        this.oldSeconds = oldSeconds;
        this.oldMinutes = oldMinutes;
        this.oldHours = oldHours;
        this.newSeconds = newSeconds;
        this.newMinutes = newMinutes;
        this.newHours = newHours;
    }

    //GETTER Y SETTER
    public long getOldSeconds() {        return oldSeconds;    }
    public void setOldSeconds(int oldSeconds) {        this.oldSeconds = oldSeconds;    }
    public long getOldMinutes() {        return oldMinutes;    }
    public void setOldMinutes(int oldMinutes) {        this.oldMinutes = oldMinutes;    }
    public long getOldHours() {        return oldHours;    }
    public void setOldHours(int oldHours) {        this.oldHours = oldHours;    }
    public long getNewSeconds() {        return newSeconds;    }
    public void setNewSeconds(int newSeconds) {        this.newSeconds = newSeconds;    }
    public long getNewMinutes() {        return newMinutes;    }
    public void setNewMinutes(int newMinutes) {        this.newMinutes = newMinutes;    }
    public long getNewHours() {        return newHours;    }
    public void setNewHours(int newHours) {        this.newHours = newHours;    }
    public long getFinalSeconds() {        return finalSeconds;    }
    public void setFinalSeconds(int finalSeconds) {        this.finalSeconds = finalSeconds;    }
    public long getFinalMinutes() {        return finalMinutes;    }
    public void setFinalMinutes(int finalMinutes) {        this.finalMinutes = finalMinutes;    }
    public long getFinalHour() {        return finalHour;    }
    public void setFinalHour(int finalHour) {        this.finalHour = finalHour;    }

    public void calculateFinalTimer() {
        int calculateMinutesExtra = 0;
        if ((newSeconds + oldSeconds) > 59) {
            finalSeconds = (newSeconds + oldSeconds) % 60;
            calculateMinutesExtra = (int) ((newSeconds + oldSeconds) / 60);
            finalMinutes = (calculateMinutesExtra) + (newMinutes + oldMinutes) % 60;
        }

        if ((newMinutes + oldMinutes + calculateMinutesExtra) > 59) {

            finalMinutes = (newMinutes + oldMinutes + calculateMinutesExtra) % 60;
            int calculateHourExtra = (int) ((newMinutes + oldMinutes + calculateMinutesExtra) / 60);
            finalHour += calculateHourExtra;

        }
    }

    public LocalTime calculateDiference(LocalTime oldTime, LocalTime newTime){
        Duration durationBetweenBothTimes = Duration.between(oldTime, newTime);
        long hours = durationBetweenBothTimes.toHours();
        long minutes = durationBetweenBothTimes.toMinutesPart();
        long seconds = durationBetweenBothTimes.toSecondsPart();
        return LocalTime.of((int) hours, (int) minutes, (int) seconds);



    }
    public void showFinalTimer(){
        System.out.println("Timer: " + finalHour + ":" + finalMinutes + ":" + finalSeconds);
    }
}

