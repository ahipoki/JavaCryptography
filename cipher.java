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
    
    public static void main(String[] args){
        new cipher();
    }
}

/*public String caesarEncrypt(String plainText, int shift){
    StringBuilder encrypted = new StringBuilder(message);
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabet2 = alphabet.toLowerCase();
    String keyedalphabet = alphabet.substring(key) + alphabet.substring(0, key);
    for (int q = 0; q < encrypted.length(); q++){
        char currchar = encrypted.charAt(q);
        int index = alphabet.indexOf(currchar);
        if (index != -1){
            char newChar = keyedalphabet.charAt(index);
            encrypted.setCharAt(q, newChar);
        }
        index = alphabet2.indexOf(currchar);
        if (index != -1){
            String keyedalphabet2 = keyedalphabet.toLowerCase();
            char newChar = keyedalphabet2.charAt(index);
            encrypted.setCharAt(q, newChar);
        }
    }
    return encrypted.toString();
}*/

/*public String caesarDecrypt(String cipherText, int shift){
    String dec = "";
    for (int i = 0; i < cipherText.length(); i++){
        char ch = (char)((cipherText.codePointAt(i) - key) % 26);
        dec += ch;
    }
    return dec;
}

public String vigenereEncrypt(String plainText, int shift){
    StringBuilder encrypted = new StringBuilder(plainText);
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabet2 = alphabet.toLowerCase();
    shift = Integer.parseInt(shiftEdit.getText().toString());
    String keyedalphabet = alphabet.substring(shift) + alphabet.substring(0, shift);
    for (int q = 0; q < encrypted.length(); q++){
        char currchar = encrypted.charAt(q);
        int index = alphabet.indexOf(currchar);
        if (index != -1){
            char newChar = keyedAlphabet.charAt(index);
            encrypted.setCharAt(q, newChar);
        }
        index = alphabet2.indexOf(currchar);
        if (index != -1){
            String keyedalphabet2 = keyedalphabet.toLowerCase();
            char newChar = keyedalphabet2.charAt(index);
            encrypted.setCharAt(q, newChar);
        }
    }
    return encrypted.toString();
}

public String vigenereDecrypt(String decryptText, int key){
    String dec = "";
    for (int i = 0; i < decryptText.length(); i++){
        char ch = (char)(decryptText.codePointAt(i) - key);
        dec += ch;
    }
    return dec;
}*/
