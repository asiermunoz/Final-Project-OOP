import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        /*
        Clock timer = new Clock();
        String tiempoFinal = "";
        while(timer.isStatus()) {
            timer.run();
            if(timer.getSeconds() == 4){
                tiempoFinal = timer.stop();
                System.out.println("\n el tiempo termino en: " + tiempoFinal);
            }
        }
        */
        /*Date timer = new Date();
        System.out.println(timer);
        Thread.sleep(70000);
        Date timer2 = new Date();
        System.out.println(timer2);

        long diferencia = timer2.getTime() - timer.getTime();
        long Totalseconds = diferencia / 1000;
        long hour = Totalseconds / 3600;
        long minutes = (Totalseconds % 3600) / 60;
        long seconds = Totalseconds % 60;
        System.out.println("DIFERENCIA: " + hour + ":" + minutes + ":" + seconds);*/

        long seconds1 = 60;
        long minutes2 = 60;
        long hour3 = 60;

        long seconds = 0;
        long minutes = 0;
        long hour = 0;

        long finalSeconds = seconds + seconds1;
        long finalMinutes = minutes + minutes2;
        long finalHour = hour + hour3;

        int calculateMinutesExtra = 0;

        if ((seconds + seconds1) > 59) {
            finalSeconds = (seconds + seconds1) % 60;
            calculateMinutesExtra = (int) ((seconds + seconds1) / 60);
            finalMinutes = (calculateMinutesExtra) + (minutes + minutes2) % 60;
        }

        if ((minutes + minutes2 + calculateMinutesExtra) > 59) {

            finalMinutes = (minutes + minutes2 + calculateMinutesExtra) % 60;
            int calculateHourExtra = (int) ((minutes + minutes2 + calculateMinutesExtra) / 60);
            finalHour += calculateHourExtra;

        }


        System.out.println("DIFERENCIA: " + finalHour + ":" + finalMinutes + ":" + finalSeconds);


    }
}