import java.io.*;
import java.util.Scanner;

class ousb_help {
    public static void main(String[] args) throws IOException {
        String os = System.getProperty("os.name");
        System.out.println("Running on: " + os);

        if (os.equals("Linux")){
            // StringBuilder cmdResult = new StringBuilder();
            try {
                Runtime run = Runtime.getRuntime(); // get runtime shell.
                Process pr = run.exec("ousb io portb 0");         // start command.
                pr.waitFor();                       // wait for command to finish.       
                // BufferedReader buf = new BufferedReader(new InputStreamReader());
                
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
        
    }

}

// while( (line = buf.readLine()) != null) {
                //     String[] tokens = line.split("\\s");
                //     System.out.println(Arrays.toString(tokens));
                // }