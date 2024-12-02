public class GameTimer {

    private long oldSeconds = 0;
    private long oldMinutes = 0;
    private long oldHours = 0;
    private long newSeconds = 0;
    private long newMinutes = 0;
    private long newHours = 0;
    private long finalSeconds = newSeconds + oldSeconds;
    private long finalMinutes = newMinutes + oldMinutes;
    private long finalHour = newHours + oldHours;

    public GameTimer(long oldSeconds, long oldMinutes, long oldHours, long newSeconds, long newMinutes, long newHours) {
        this.oldSeconds = oldSeconds;
        this.oldMinutes = oldMinutes;
        this.oldHours = oldHours;
        this.newSeconds = newSeconds;
        this.newMinutes = newMinutes;
        this.newHours = newHours;
    }

    //GETTER Y SETTER
    public long getOldSeconds() {        return oldSeconds;    }
    public void setOldSeconds(long oldSeconds) {        this.oldSeconds = oldSeconds;    }
    public long getOldMinutes() {        return oldMinutes;    }
    public void setOldMinutes(long oldMinutes) {        this.oldMinutes = oldMinutes;    }
    public long getOldHours() {        return oldHours;    }
    public void setOldHours(long oldHours) {        this.oldHours = oldHours;    }
    public long getNewSeconds() {        return newSeconds;    }
    public void setNewSeconds(long newSeconds) {        this.newSeconds = newSeconds;    }
    public long getNewMinutes() {        return newMinutes;    }
    public void setNewMinutes(long newMinutes) {        this.newMinutes = newMinutes;    }
    public long getNewHours() {        return newHours;    }
    public void setNewHours(long newHours) {        this.newHours = newHours;    }
    public long getFinalSeconds() {        return finalSeconds;    }
    public void setFinalSeconds(long finalSeconds) {        this.finalSeconds = finalSeconds;    }
    public long getFinalMinutes() {        return finalMinutes;    }
    public void setFinalMinutes(long finalMinutes) {        this.finalMinutes = finalMinutes;    }
    public long getFinalHour() {        return finalHour;    }
    public void setFinalHour(long finalHour) {        this.finalHour = finalHour;    }

    void calculateFinalTimer() {
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
    void showFinalTimer(){
        System.out.println("Timer: " + finalHour + ":" + finalMinutes + ":" + finalSeconds);
    }
}
