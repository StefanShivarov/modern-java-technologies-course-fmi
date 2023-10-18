package intro_to_java;

public class BrokenKeyboard {

    public static int calculateFullyTypedWords(String message, String brokenKeys){

        if(message.isBlank()){
            return 0;
        }
        String[] words = message.trim().split("\\s+");
        int brokenKeysLength = brokenKeys.length(), fullyTypedWordsCounter = 0;

        for(String word: words){
            for(int i = 0; i < brokenKeysLength; i++){
                if(word.contains(String.valueOf(brokenKeys.charAt(i)))){
                    break;
                }
                if(i == brokenKeysLength - 1){
                    fullyTypedWordsCounter++;
                }
            }
        }
        return fullyTypedWordsCounter;
    }

    public static void main(String[] args) {

        System.out.println(calculateFullyTypedWords("i love mjt", "qsf3o"));
        System.out.println(calculateFullyTypedWords("secret      message info      ", "sms"));
        System.out.println(calculateFullyTypedWords("dve po 2 4isto novi beli kecove", "o2sf"));
        System.out.println(calculateFullyTypedWords("     ", "asd"));
        System.out.println(calculateFullyTypedWords(" - 1 @ - 4", "s"));
    }
}
