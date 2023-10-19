public class BrokenKeyboard {

    public static int calculateFullyTypedWords(String message, String brokenKeys){

        if(message.isBlank()){
            return 0;
        }
        String[] words = message.trim().split("\\s+");
        String trimmedBrokenKeys = brokenKeys.trim();
        int brokenKeysLength = trimmedBrokenKeys.length(), fullyTypedWordsCounter = 0;

        if(brokenKeysLength == 0){
            return words.length;
        }

        for(String word: words){
            for(int i = 0; i < brokenKeysLength; i++){
                if(word.contains(String.valueOf(trimmedBrokenKeys.charAt(i)))){
                    break;
                }
                if(i == brokenKeysLength - 1){
                    fullyTypedWordsCounter++;
                }
            }
        }
        return fullyTypedWordsCounter;
    }

}
