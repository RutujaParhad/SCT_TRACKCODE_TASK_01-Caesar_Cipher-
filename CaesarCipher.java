import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;

public class CaesarCipherGUI extends JFrame {

    private JTextField messageField;
    private JTextField shiftField;
    private JTextArea outputArea;

    public CaesarCipherGUI() {
        setTitle("Caesar Cipher Encryption Tool");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel for inputs
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Enter Message:"));
        messageField = new JTextField();
        inputPanel.add(messageField);

        inputPanel.add(new JLabel("Enter Shift Value:"));
        shiftField = new JTextField();
        inputPanel.add(shiftField);

        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        inputPanel.add(encryptButton);
        inputPanel.add(decryptButton);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button actions
        encryptButton.addActionListener(this::handleEncrypt);
        decryptButton.addActionListener(this::handleDecrypt);
    }

    private void handleEncrypt(ActionEvent e) {
        String message = messageField.getText();
        int shift = getShiftValue();
        if (shift == -1) return;

        String encrypted = encrypt(message, shift);
        outputArea.setText("Encrypted Message:\n" + encrypted);
    }

    private void handleDecrypt(ActionEvent e) {
        String message = messageField.getText();
        int shift = getShiftValue();
        if (shift == -1) return;

        String decrypted = decrypt(message, shift);
        outputArea.setText("Decrypted Message:\n" + decrypted);
    }

    private int getShiftValue() {
        try {
            return Integer.parseInt(shiftField.getText()) % 26;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Shift value must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    // Caesar Cipher logic
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

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CaesarCipherGUI().setVisible(true));
    }
}