package intro_to_java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IPValidator {

    public static boolean validateIPv4Address(String str){

        String[] octets = str.split("\\.");

        if(octets.length != 4){
            return false;
        }

        for(String s: octets){
            if(s.isBlank()){
                return false;
            }
            int number = Integer.parseInt(s);
            if(number < 0 || number > 255 || s.startsWith("00")){
                    return false;
            }
        }

        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String input = scanner.nextLine();
        System.out.println(validateIPv4Address(input));
    }
}
