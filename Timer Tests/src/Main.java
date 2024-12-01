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
        Date timer = new Date();
        System.out.println(timer);
        Thread.sleep(70000);
        Date timer2 = new Date();
        System.out.println(timer2);

        long diferencia = timer2.getTime() - timer.getTime();
        long Totalseconds = diferencia / 1000;
        long hour = Totalseconds / 3600;
        long minutes = (Totalseconds % 3600) / 60;
        long seconds = Totalseconds % 60;
        System.out.println("DIFERENCIA: " + hour + ":" + minutes + ":" + seconds);


    }
}