import edu.duke.*;
/**
 * Write a description of CaesarCipher_new here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher_new {
    public String encrypt(String input, int key){
        String alphabet_u = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet_l = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet_u = alphabet_u.substring(key) + alphabet_u.substring(0,key);
        String shiftedAlphabet_l = alphabet_l.substring(key) + alphabet_l.substring(0,key);
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx_u = alphabet_u.indexOf(currChar);
            if(idx_u != -1){
                char newChar = shiftedAlphabet_u.charAt(idx_u);
                encrypted.setCharAt(i,newChar);
            }
            int idx_l = alphabet_l.indexOf(currChar);
            if(idx_l != -1){
                char newChar = shiftedAlphabet_l.charAt(idx_l);
                encrypted.setCharAt(i,newChar);
            }
        }
        return encrypted.toString();
    }
    public void testCaesar(){
        // FileResource fr = new FileResource();
        // String message = fr.toString();
        String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26 - 15);
        System.out.println(decrypted);
    }
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet_u = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet_l = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet_u1 = alphabet_u.substring(key1) + alphabet_u.substring(0,key1);
        String shiftedAlphabet_l1 = alphabet_l.substring(key1) + alphabet_l.substring(0,key1);
        String shiftedAlphabet_u2 = alphabet_u.substring(key2) + alphabet_u.substring(0,key2);
        String shiftedAlphabet_l2 = alphabet_l.substring(key2) + alphabet_l.substring(0,key2);
        for(int i = 0; i < input.length(); i = i + 2){
            char currChar = encrypted.charAt(i);
            int idx_u = alphabet_u.indexOf(currChar);
            if(idx_u != -1){
                char newChar = shiftedAlphabet_u1.charAt(idx_u);
                encrypted.setCharAt(i,newChar);
            }
            int idx_l = alphabet_l.indexOf(currChar);
            if(idx_l != -1){
                char newChar = shiftedAlphabet_l1.charAt(idx_l);
                encrypted.setCharAt(i,newChar);
            }
        }
        for(int i = 1; i < input.length(); i = i + 2){
            char currChar = encrypted.charAt(i);
            int idx_u = alphabet_u.indexOf(currChar);
            if(idx_u != -1){
                char newChar = shiftedAlphabet_u2.charAt(idx_u);
                encrypted.setCharAt(i,newChar);
            }
            int idx_l = alphabet_l.indexOf(currChar);
            if(idx_l != -1){
                char newChar = shiftedAlphabet_l2.charAt(idx_l);
                encrypted.setCharAt(i,newChar);
            }
        }
        return encrypted.toString();
    }
    public void testEncryptTwoKeys(){
        String encrypted = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(encrypted);
        String decrypted = encryptTwoKeys(encrypted, 26-8, 26-21);
        System.out.println(decrypted);
    }
}
