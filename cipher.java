import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class cipher implements ActionListener {
    JFrame frame = new JFrame();
    JPanel choice = new JPanel();
    JPanel textOutput = new JPanel();
    
    JTextField decryptTF = new JTextField("Decrypted Text", 20);
    JTextField encryptTF = new JTextField("Encrypted Text", 20);
    
    JButton encrypt = new JButton("Encrypt");
    JButton decrypt = new JButton("Decrypt");

    JRadioButton scytale = new JRadioButton("Scytale");
    JRadioButton caesar = new JRadioButton("Caesar");
    JRadioButton vigenere = new JRadioButton("Vigenere");

    ButtonGroup group = new ButtonGroup();

    public cipher(){
        choice.add(scytale);
        choice.add(caesar);
        choice.add(vigenere);
        textOutput.add(encryptTF);
        textOutput.add(decryptTF);
        
        frame.setSize(750, 500);
        frame.setLayout(new BorderLayout());
        frame.add(encrypt, BorderLayout.WEST);
        encrypt.addActionListener(this);
        frame.add(decrypt, BorderLayout.EAST);
        decrypt.addActionListener(this);
        frame.add(choice, BorderLayout.NORTH);
        frame.add(textOutput, BorderLayout.CENTER);
        
        group.add(scytale);
        group.add(caesar);
        group.add(vigenere);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        String encryptedText;
        String plainText;
        if (e.getSource() == encrypt){
            if (scytale.isSelected()){
                
            }
        }
        else if (e.getSource() == decrypt){
            
        }
    }
    
    public static String scytaleEncrypt(String plainMessage, int numRows){
        String encryptedText = "";
        if (numRows >= plainMessage.length() || numRows <= 0){
            return plainMessage;
        }
        else{
            while (plainMessage.length() % numRows != 0){
                plainMessage += " ";
            }
            int numCols = plainMessage.length() / numRows;
            for (int i = 0; i < numCols; i++){
                for (int y = i; y < plainMessage.length(); y += numCols){
                    encryptedText += plainMessage.charAt(y);
                }
            }
        }
        return encryptedText;
    }
    
    public static String scytaleDecrypt(String encryptText, int numRows){
        String decryptedText = "";
        int numCols = encryptText.length() / numRows;
        decryptedText = scytaleEncrypt(encryptText, numCols);
        return decryptedText;
    }
    
    public static StringBuffer caesarEncrypt(String plainText, int shift){
        StringBuffer encrypted = new StringBuffer();
        for (int q = 0; q < plainText.length(); q++){
            if (Character.isUpperCase(plainText.charAt(q))){
                char ch = (char)(((int)plainText.charAt(q) + shift-65) % 26+65);
                encrypted.append(ch);
            }
            else{
                char ch = (char)(((int)plainText.charAt(q) + shift-97) % 26+97);
                encrypted.append(ch);
            }
        }
        return encrypted;
    }
    
    public String caesarDecrypt(String cipherText, int shift){
        String dec = "";
        for (int i = 0; i < cipherText.length(); i++){
            char ch = (char)((cipherText.codePointAt(i) - shift) % 26);
            dec += ch;
        }
        return dec;
    }
    
    public String generateKey(String message, String key){
        int x = message.length();
        for (int i = 0; ; i++){
            if (x == i){
                i = 0;
            }
            if (key.length() == message.length()){
                break;
            }
            key += (key.charAt(i));
        }
        return key;
    }
    
    public String vigenereEncrypt(String plainText, String key){
        String encrypted = "";
        for (int i = 0; i < plainText.length(); i++){
            int x = (plainText.charAt(i) + key.charAt(i)) % 26;
            x += 'A';
            encrypted += (char)(x);
        }
        return encrypted;
    }
    
    public String vigenereDecrypt(String decryptText, String key){
        String dec = "";
        for (int i = 0; i < decryptText.length() && i < key.length(); i++){
            int x = (decryptText.charAt(i) - key.charAt(i) + 26) % 26;
            x += 'A';
            dec += (char)(x);
        }
        return dec;
    }
    
    public static void main(String[] args){
        new cipher();
    }
}
