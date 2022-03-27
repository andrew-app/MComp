import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.Scanner;

import org.junit.Test;

public class ousbtests {
    public static String help = "Available Options: \n [0]: Exit the program \n [1]: Display the HELP menu \n [2]: Read the switches (PINC) \n [3]: Read the trim pot (ADC5). \n [4]: Write value to the LEDs (PORTB). \n [5]: Read the LEDs (PORTB). \n [6]: Enter the temperature sensor mode.";
    public static boolean isConnected = false;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static String OUSB(String cmd){
        String os = System.getProperty("os.name");

        if (cmd.isEmpty()){
            return "Invalid command argument." + "\n" + "Available Commands: " + help;
        }
        
        String pipecmd = null;
        String base = null;
        String option = null;

        
        if (os.equals("Linux")){
            base = "sudo ousb";
        }

        else {
            base = "ousb.exe";
        }

        option = cmd.substring(0, 1);

        if (option != "4" & cmd.length() > 1){
            return "Invalid command argument." + "\n" + "Available Commands: " + help;
        }

        
        switch(option){

            case "0":
                return "Exiting...";
            
            case "1":

                return help;
            
            case "2":
                pipecmd = base + " io pinc";
                return Pipe(pipecmd);

            case "3":
                pipecmd = base + " adc 5";
                return Pipe(pipecmd);

            case "4":

                if(cmd.length() == 1){
                    return "No 2nd Arg.";
                }

                if(!isNumeric(cmd.substring(2,cmd.length()))){
                    return "Invalid Number.";
                }
                    

                pipecmd = base + " io portb " + cmd.substring(1,cmd.length());

                return Pipe(pipecmd);
            
            case "5":
                pipecmd = base + " io portb";

                return Pipe(pipecmd);
            
            case "6":
                return "Read Temp Sensor";
            
            default:
                return "Invalid command argument." + "\n" + "Available Commands: " + help;
        }
        

    }

    public static String Pipe(String ousbcmd){
        String output = null;
        try {
            Runtime run = Runtime.getRuntime(); // get runtime shell.
            Process pr = run.exec(ousbcmd);         // start command.
            pr.waitFor();                       // wait for command to finish.
            Scanner scanner = new Scanner(pr.getInputStream());

            while (scanner.hasNextLine())
            {
                scanner.close();

                output = scanner.nextLine();

                if (output.contains("Fatal Exception!")){
                    return "Error";
                }

                return output;
            }
            
        }
        catch (Exception e ) {
            // can get exceptions for exec, waitfor, parseInt
        }

        return null;
    }

    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){ 
          return false;  
        }  
    }

    @Test
    public void testPipe(){
        if (Pipe("sudo ousb io pinc") != "Error"){
            isConnected = true;
        }

        System.out.println(ANSI_GREEN + "Pipe Test OK" + ANSI_RESET);


    }

    @Test
    public void testOUSB() {
        assertEquals("Exiting...", OUSB("0"));
        assertEquals(help, OUSB("1"));
        assertEquals("Invalid command argument." + "\n" + "Available Commands: " + help, OUSB("1a"));
        if (isConnected){
            System.out.println("OUSB Detected.");
            assertThat(OUSB("2"), containsString("PINC ="));
            assertThat(OUSB("3"), containsString("ADC5 ="));
            assertEquals("No 2nd Arg.", OUSB("4"));
            assertEquals("Invalid Number.", OUSB("4 1-"));
            assertThat(OUSB("4 1"), containsString("PORTB ="));
            assertThat(OUSB("5"), containsString("PORTB ="));
            assertEquals("Read Temp Sensor", OUSB("6"));
            assertEquals("Invalid command argument." + "\n" + "Available Commands: " + help, OUSB("7"));
            assertEquals("Invalid command argument." + "\n" + "Available Commands: " + help, OUSB("a"));
            assertEquals("Invalid command argument." + "\n" + "Available Commands: " + help, OUSB(""));
        }
        System.out.println(ANSI_GREEN + "OUSB Tests OK." + ANSI_RESET);
    }

    @Test
    public void testisNumeric() {
        assertEquals(true, isNumeric("1"));

        String[] testcases = {"one","1a", "1-", "1 2"};
        for (int i = 0; i < testcases.length; i++){
            assertEquals(false, isNumeric(testcases[i]));
        }

        System.out.println(ANSI_GREEN + "isNumeric() Tests OK." + ANSI_RESET);
    }
}
