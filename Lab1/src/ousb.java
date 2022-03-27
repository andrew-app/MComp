import java.io.*;
import java.util.Scanner;

class ousb {
    public static void main(String[] args) throws IOException {
        String os = System.getProperty("os.name");
        
        Scanner cmdarg = new Scanner(System.in);

        String pipecmd = null;
        String base = null;

        String help = "Available Options: \n [0]: Exit the program \n [1]: Display the HELP menu \n [2]: Read the switches (PINC) \n [3]: Read the trim pot (ADC5). \n [4]: Write value to the LEDs (PORTB). \n [5]: Read the LEDs (PORTB). \n [6]: Enter the temperature sensor mode.";
        if (os.equals("Linux")){ // appropriate command based on OS.
            base = "sudo ousb";
        }

        else {
            base = "ousb.exe";
        }


        while(true){
            System.out.println("Select an option:");

            String cmd = cmdarg.nextLine();

            String portbval = null;

            if (cmd.isEmpty()){ //Handling for when user inputs nothing
                System.out.println("Invalid command argument.");
                System.out.println("Available Commands: " + help);
                break;
            }

            String option = cmd.substring(0, 1);

            if (option != "4" & cmd.length() > 1){ //Input handling for different ousb interface options making sure explicit values.
                System.out.println("Invalid command argument.");
                System.out.println("Available Commands: " + help);
                break;
            }
            
            // Use switch statement to handle different OUSB functinality
            switch(cmd){

                case "0": //exit program
                    System.out.println("Exiting...");
                    cmdarg.close();
                    System.exit(0);
                
                case "1": //print help dialog
                    System.out.println(help);
                    break;
                
                case "2": // read PINC
                    pipecmd = base + " io pinc";
                    pipe(pipecmd);
                    break;

                case "3": // Read trimpot
                    pipecmd = base + " adc 5";
                    pipe(pipecmd);
                    break;

                case "4": // Write to portb

                    if(option.length() == 1){ //event that no 2nd argument given
                        System.out.println("Unable to write to PORTB. No 2nd command argument given.");
                        break;
                    }


                    if(!isNumeric(cmd.substring(2,cmd.length()))){ //Determine if write value is a number.
                        System.out.println("2nd command argument invalid.");
                        break;
                    }

                    
                    pipecmd = base + " io portb " + cmd.substring(2,cmd.length());
                    pipe(pipecmd);
                    break;
                
                case "5": // Read portb
                    pipecmd = base + " io portb";
                    pipe(pipecmd);
                    break;
                
                case "6": // Initiate temperature sensor function.
                    tempsense.main(null);
                    break;
                
                default: // Handling of all other invalid options
                    System.out.println("Invalid command.");
                    System.out.println("Available Commands: " + help);
            }
            System.out.println("\n");
        }

    }

    public static void pipe(String ousbcmd) throws IOException {
        try {
            Runtime run = Runtime.getRuntime(); // get runtime shell.
            Process pr = run.exec(ousbcmd);         // start command.
            pr.waitFor();                       // wait for command to finish.
            Scanner scanner = new Scanner(pr.getInputStream());

            while (scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        }
        catch (Exception e ) {
            // can get exceptions for exec, waitfor, parseInt
        }
    }

    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  //attempt to parse string as double iif unable return false.
          return true;
        } catch(NumberFormatException e){ 
          return false;  
        }  
    }
}

