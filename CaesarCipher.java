import java.util.Scanner;

public class CaesarCipher {

    // Encrypt method
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char ch = (char) (((character - 'A' + shift) % 26) + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(character)) {
                char ch = (char) (((character - 'a' + shift) % 26) + 'a');
                result.append(ch);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    // Decrypt method
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(message, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("Encrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);

        sc.close();
    }
}
