public class IPValidator {

    public static boolean validateIPv4Address(String str){

        String[] octets = str.split("\\.");

        if(octets.length != 4){
            return false;
        }

        for(String s: octets){
            if(s.isBlank() || s.contains(" ")){
                return false;
            }
            int number = Integer.parseInt(s);
            if(number < 0 || number > 255 || s.startsWith("00")){
                    return false;
            }
        }

        return true;
    }
}
