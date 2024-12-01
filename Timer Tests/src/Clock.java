public class Clock {
    private int seconds = 0;
    private int minutes = 0;
    private int hour = 0;
    private boolean  status = true;
    private String finalTimer;

    public Clock() {
        this.seconds = 0;
        this.minutes = 0;
        this.hour = 0;
        this.status = true;

    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHour() {
        return hour;
    }

    public String getFinalTimer() {
        return finalTimer;
    }

    public boolean isStatus() {
        return status;
    }

    public void run(){
        String timer = null;
        timer = String.format("%02d:%02d:%02d", hour, minutes, seconds);

        System.out.print("\r" + timer);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }

        }
        finalTimer = timer;

    }

    public String stop(){
        status = false;
        return finalTimer;

    }



}
