package encryptdecrypt;

public class SolveWithUnicode implements SolvingMethod {

    @Override
    public String solve(int mode, String message, int key) {
        if (mode == 1) {
            return encode(message, key);
        }

        return decode(message, key);
    }

    private String encode(String message, int key) {
        String encrypted = "";

        for (int i = 0; i < message.length(); i++) {
            char currentLetter = message.charAt(i);

            int temp = (int) currentLetter + key;
            encrypted = encrypted + (char) temp;

        }
        return encrypted;
    }

    private String decode(String message, int key) {
        String decrypted = "";

        for (int i = 0; i < message.length(); i++) {
            char currentLetter = message.charAt(i);

            int temp = (int) currentLetter - key;
            decrypted = decrypted + (char) temp;
        }
        return decrypted;
    }
}
