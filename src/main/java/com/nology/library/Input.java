package com.nology.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Input {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String[] inputs = {"login","logout","create","loans","books","report","help","loan","return","quit"};

    public static boolean confirm() {
        String input = null;
        try {
            input = br.readLine().toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean correctInput = false;
        while (!correctInput){
            correctInput = input.matches("y") || input.matches("n");
            if (!correctInput){
                Output.error("Only input y or n");
                try {
                    input = br.readLine().toLowerCase();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return input.matches("y");
    }

    public static String command(){
        String input = null;
        try {
            input = br.readLine().toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean correctInput = false;
        while (!correctInput) {
            correctInput = Arrays.asList(inputs).contains(input);
            if (!correctInput){
                Output.error("Invalid input, type 'help' for commands");
                try {
                    input = br.readLine().toLowerCase();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return input;
    }

    public static String getInput(){
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getInt(){
        String input;
        try {
            input = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int retVal = 0;

        boolean validInput = false;

        while (!validInput) {
            try {
                retVal = Integer.parseInt(input);

                if(retVal < 0){
                    Output.error("Please enter in an valid number");
                    input = br.readLine();
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                Output.error("Only input a number");
                try {
                    input = br.readLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return retVal;
    }
}
