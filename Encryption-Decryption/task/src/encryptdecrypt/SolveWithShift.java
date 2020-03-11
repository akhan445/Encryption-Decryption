package encryptdecrypt;

public class SolveWithShift implements SolvingMethod {

    @Override
    public String solve(int mode, String message, int key) {
        if (mode == 1) {
            return encode(message, key);
        }

        return decode(message, key);
    }

    private String encode(String message, int key) {
        String result = "";

        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + key) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result = result + newCharacter;
            } else {
                result = result + character;
            }
        }
        return result;

    }

    private String decode(String message, int key) {
        //call encode method but complementing it
        return encode(message, 26 - (key % 26));

    }

}

