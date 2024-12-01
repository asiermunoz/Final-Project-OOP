public class Main {
    public static void main(String[] args) throws InterruptedException {
        Clock timer = new Clock();
        String tiempoFinal = "";
        while(timer.isStatus()) {
            timer.run();
            if(timer.getSeconds() == 4){
                tiempoFinal = timer.stop();
                System.out.println("\n el tiempo termino en: " + tiempoFinal);

            }

        }



    }
}