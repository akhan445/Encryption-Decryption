package encryptdecrypt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {
        //flag to keep track of what mode to execute    1- enc  2-dec
        int mode = 1;
        int key  = 0; //default
        boolean in = false;
        String inFilaName = "";
        boolean out = false;
        String outFileName = "";
        String message = "";
        String alg = "";
        String result;



        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    if ("dec".equals(args[i+1])){
                        mode = 2;
                    }
                    break;
                case "-key":
                    key = Integer.parseInt(args[i+1]);
                    break;
                case "-data":
                    message += args[i+1];
                    break;
                case "-in":
                    in = true;
                    inFilaName += args[i+1];
                    break;
                case "-out":
                    out = true;
                    outFileName += args[i+1];
                    break;
                case "-alg":
                    alg = args[i+1];
                    break;
            }
    }

        //if both in and data arguments are passed, prefer data over in
        if (in && !message.isEmpty()) {
            in = false;
        }

        // If there is an -in argument, will read message from file otherwise will skip this code
        if (in) {
            File file = new File(inFilaName);

            try (Scanner scanner = new Scanner(file)) {
                message += scanner.nextLine();
            } catch (FileNotFoundException e) {
                System.out.println("Error- No File Found: " + inFilaName);
            }
        }

        // Determine which algorithm to use and use Strategy Pattern based on chosen method
        MessageSolver solver = new MessageSolver();
        switch (alg.toUpperCase()) {
            case "SHIFT":
                solver.setMethod(new SolveWithShift());
                break;
            case "UNICODE":
                solver.setMethod(new SolveWithUnicode());
                break;

        }

        // default to shift if no algorithm entered
        if (alg.isEmpty()) {
            solver.setMethod((new SolveWithShift()));
        }

        result = solver.solve(mode, message, key);

        //Output to file if argument exists otherwise print to standard output
        if (out) {
            File file = new File(outFileName);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(result);
            } catch (IOException e) {
                System.out.printf("Error occurred attempting to write to file: ", e.getMessage());
            }
        } else {
            System.out.println(result);
        }
    }
}

