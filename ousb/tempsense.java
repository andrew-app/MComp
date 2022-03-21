import java.io.*;
import java.util.Scanner;
import java.lang.Thread;
class tempsense{

    public static void main(String[] args) throws IOException {

        String os = System.getProperty("os.name");
        String adc = null;
        String portb = null;
        float x;
        double T;
        float A = 140;
        float B = -100;
        float C = -20;

        if(os.equals("Linux")){
            adc = "sudo ousb -r adc 5";
            portb = "sudo ousb io portb ";
        }

        String trimpot = readtrimpot(adc);

        try {
            x = Float.parseFloat(trimpot);
        }
        catch (NumberFormatException e) {
            x = 0;
        }


        T = A * Math.exp(B/x) - C;

        T = Math.round(T* 100.0) / 100.0;

        System.out.println(T + " DEG C");

        String[] LTR = {"1","2","4","16","32","64","128"};
        String[] RTL = {"128","64","32","16","4","2","1"};
        String[] sflash = null;

        if(T < 30){
            sflash = LTR;
        }

        else if(T > 80){
            sflash = RTL;
        }

        for (int i = 0; i < sflash.length; i++) {
  
            // accessing each element of array
            
            writeportb(portb + sflash[i]);
            try        
            {
                Thread.sleep(500);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
        }

        writeportb(portb + "0");
    }

    public static void writeportb(String ousbcmd) throws IOException {
        try {
            Runtime run = Runtime.getRuntime(); // get runtime shell.
            Process pr = run.exec(ousbcmd);         // start command.
            pr.waitFor();                       // wait for command to finish.
            Scanner scanner = new Scanner(pr.getInputStream());
            scanner.close();
        }
        catch (Exception e ) {
            // can get exceptions for exec, waitfor, parseInt
        }
    }

    public static String readtrimpot(String ousbcmd) throws IOException {
        String retval = null;
        try {
            Runtime run = Runtime.getRuntime(); // get runtime shell.
            Process pr = run.exec(ousbcmd);         // start command.
            pr.waitFor();                       // wait for command to finish.
            Scanner scanner = new Scanner(pr.getInputStream());
            while (scanner.hasNextLine())
            {
                retval = scanner.nextLine();
            }
            scanner.close();
        }
        catch (Exception e ) {
            // can get exceptions for exec, waitfor, parseInt
        }

        return retval;

    }

}