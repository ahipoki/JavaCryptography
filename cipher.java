import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class cipher implements ActionListener {
    JFrame frame = new JFrame();
    JPanel choice = new JPanel();
    JPanel textOutput = new JPanel();
    
    JTextField decryptTF = new JTextField("Decrypted Text", 20);
    JTextField encryptTF = new JTextField("Encrypted Text", 20);
    JTextField shiftTF = new JTextField("Shift/Key", 10);
    
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
        textOutput.add(decryptTF);
        textOutput.add(encryptTF);
        textOutput.add(shiftTF);
        
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
                scytaleEncrypt(decryptTF.getText(), Integer.parseInt(shiftTF.getText().toString()));
            }
            else if (caesar.isSelected()){
                caesarEncrypt(decryptTF.getText(), Integer.parseInt(shiftTF.getText().toString()));
            }
            else if (vigenere.isSelected()){
                vigenereEncrypt(decryptTF.getText().toString(), shiftTF.getText().toString());
            }
        }
        else if (e.getSource() == decrypt){
            if (scytale.isSelected()){
                scytaleDecrypt(encryptTF.getText(), Integer.parseInt(shiftTF.getText().toString()));
            }
            else if (caesar.isSelected()){
                caesarDecrypt(encryptTF.getText(), Integer.parseInt(shiftTF.getText().toString()));
            }
            else if (vigenere.isSelected()){
                vigenereDecrypt(encryptTF.getText(), shiftTF.getText().toString());
            }
        }
    }
    
    public StringBuilder scytaleEncrypt(String plainMessage, int numRows){
        StringBuilder encrypted = new StringBuilder();
        plainMessage = plainMessage.replaceAll("\\s", "");
        for (int i = 0; i < numRows; i++){
            for (int j = 0; i+j < plainMessage.length(); j += numRows){
                encrypted.append(plainMessage.charAt(i+j));
            }
        }
        String Encrypted = encrypted.toString();
        encryptTF.setText(toUpper(Encrypted));
        return encrypted;
    }
    
    public String scytaleDecrypt(String encryptText, int numRows){
        String decryptedText = "";
        encryptText = encryptText.replaceAll("\\s", "");
        int numCols = encryptText.length() / numRows;
        decryptedText = scytaleEncrypt(encryptText, numCols).toString();
        decryptTF.setText(toUpper(decryptedText));
        return decryptedText;
    }
    
    public StringBuffer caesarEncrypt(String plainText, int shift){
        StringBuffer encrypted = new StringBuffer();
        plainText = plainText.replaceAll("\\s", "");
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
        String Encrypted = encrypted.toString();
        encryptTF.setText(toUpper(Encrypted));
        return encrypted;
    }
    
    public String caesarDecrypt(String cipherText, int shift){
        String dec = "";
        cipherText = cipherText.replaceAll("\\s", "");
        char ch;
        for (int i = 0; i < cipherText.length(); i++){
            ch = cipherText.charAt(i);
            if (ch >= 'a' && ch <= 'z'){
                ch = (char)(ch-shift);
                if (ch < 'a'){
                    ch = (char)(ch + 'z' - 'a' + 1);
                }
                dec += ch;
            }
            else if (ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch-shift);
                if (ch < 'A'){
                    ch = (char)(ch + 'Z' - 'A' + 1);
                }
                dec += ch;
            }
            else{
                dec += ch;
            }
        }
        decryptTF.setText(toUpper(dec));
        return dec;
    }
    
    public String vigenereEncrypt(String plainText, String key){
        String encrypted = "";
        plainText = plainText.replaceAll("\\s", "");
        for (int i = 0; i < plainText.length(); i++){
            int x = (plainText.charAt(i) + key.charAt(i)) % 26;
            x += 'A';
            encrypted += (char)(x);
        }
        encryptTF.setText(toUpper(encrypted));
        return encrypted;
    }
    
    public String vigenereDecrypt(String decryptText, String key){
        String dec = "";
        decryptText = decryptText.replaceAll("\\s", "");
        for (int i = 0; i < decryptText.length() && i < key.length(); i++){
            int x = (decryptText.charAt(i) - key.charAt(i) + 26) % 26;
            x += 'A';
            dec += (char)(x);
        }
        decryptTF.setText(toUpper(dec));
        return dec;
    }
    
    static String toUpper(String s){//Make inputs and outputs uppercase
        StringBuffer str = new StringBuffer(s);
        for (int i = 0; i < s.length(); i++){
            if (Character.isLowerCase(s.charAt(i))){
                str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
            }
        }
        s = str.toString();
        return s;
    }
    
    public static void main(String[] args){
        new cipher();
    }
}
