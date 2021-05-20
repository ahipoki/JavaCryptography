import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class cipher implements ActionListener {
    JFrame frame = new JFrame();//New JFrame
    JPanel choice = new JPanel();//JPanel for encryption/decryption choices
    JPanel textOutput = new JPanel();//JPanel for text fields
    
    JTextField decryptTF = new JTextField("Decrypted Text", 20);//Decryption text
    JTextField encryptTF = new JTextField("Encrypted Text", 20);//Encryption text
    JTextField shiftTF = new JTextField("Shift/Key", 10);//Shift/key text
    
    JButton encrypt = new JButton("Encrypt");//Encrypt button
    JButton decrypt = new JButton("Decrypt");//Decrypt button

    JRadioButton scytale = new JRadioButton("Scytale");//Scytale cipher JRadioButton
    JRadioButton caesar = new JRadioButton("Caesar");//Caesar cipher JRadioButton
    JRadioButton vigenere = new JRadioButton("Vigenere");//Vigenere cipher JRadioButton

    ButtonGroup group = new ButtonGroup();//Button group for the 3 ciphers

    public cipher(){
        choice.add(scytale);//Add scytale to the choice jpanel
        choice.add(caesar);//Add caesar to the choice jpanel
        choice.add(vigenere);//Add vigenere to the choice jpanel
        textOutput.add(decryptTF);//Add decrypt to the text jpanel
        textOutput.add(encryptTF);//Add encrypt to the text jpanel
        textOutput.add(shiftTF);//Add shift to the text jpanel
        
        frame.setSize(750, 500);//Set the size of the jframe
        frame.setLayout(new BorderLayout());//Set the layout of the jframe
        frame.add(encrypt, BorderLayout.WEST);//Add encryption button to the left side of the jframe
        encrypt.addActionListener(this);//Add an action listener to the encryption button
        frame.add(decrypt, BorderLayout.EAST);//Add decryption button to the right side of the jframe
        decrypt.addActionListener(this);//Add action listener to decryption button
        frame.add(choice, BorderLayout.NORTH);//Add encryption/decryption choices to top of jframe
        frame.add(textOutput, BorderLayout.CENTER);//Add text fields to center of jframe
        
        group.add(scytale);//Add scytale to the button group
        group.add(caesar);//Add caesar to the button group
        group.add(vigenere);//Add vigenere to the button group
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close
        frame.setVisible(true);//Make the jframe visible
    }
    
    public void actionPerformed(ActionEvent e){//Action performed
        if (e.getSource() == encrypt){//If encrypt
            if (scytale.isSelected()){//If scytale cipher
                scytaleEncrypt(decryptTF.getText(), Integer.parseInt(shiftTF.getText().toString()));//Call scytale encryption w/decryption text + shift
            }
            else if (caesar.isSelected()){//If caesar cipher
                caesarEncrypt(decryptTF.getText(), Integer.parseInt(shiftTF.getText().toString()));//Call caesar encryption w/decryption text + shift
            }
            else if (vigenere.isSelected()){//If vigenere cipher
                vigenereEncrypt(decryptTF.getText().toString(), shiftTF.getText().toString());//Call vigenere encryption w/decryption text + key
            }
        }
        else if (e.getSource() == decrypt){//If decrypt
            if (scytale.isSelected()){//If scytale cipher
                scytaleDecrypt(encryptTF.getText(), Integer.parseInt(shiftTF.getText().toString()));//Call scytale decryption w/encryption text + shift
            }
            else if (caesar.isSelected()){//If caesar cipher
                caesarDecrypt(encryptTF.getText(), Integer.parseInt(shiftTF.getText().toString()));//Call caesar decryption w/encryption text + shift
            }
            else if (vigenere.isSelected()){//If vigenere cipher
                vigenereDecrypt(encryptTF.getText(), shiftTF.getText().toString());//Call vigenere decryption w/encryption text + key
            }
        }
    }
    
    public StringBuilder scytaleEncrypt(String plainMessage, int numRows){//Scytale encryption
        StringBuilder encrypted = new StringBuilder();//New stringbuilder encrypted
        plainMessage = plainMessage.replaceAll("\\s", "");//Remove any spaces from the text
        for (int i = 0; i < numRows; i++){//Loop through until you hit the shift
            for (int j = 0; i+j < plainMessage.length(); j += numRows){//Loop through until you hit the length of the original text
                encrypted.append(plainMessage.charAt(i+j));//Add the encrypted char to the stringbuilder
            }
        }
        String Encrypted = encrypted.toString();//Convert the stringbuilder to a string
        encryptTF.setText(toUpper(Encrypted));//Change the encrypted text to all uppercase results from scytale encryption
        return encrypted;//Return encrypted text
    }
    
    public String scytaleDecrypt(String encryptText, int numRows){//Scytale decryption
        String decryptedText = "";//Decrypted text
        encryptText = encryptText.replaceAll("\\s", "");//Remove all spaces from the text
        int numCols = encryptText.length() / numRows;//Number of columns is length of encrypted text divided by shift inputted
        decryptedText = scytaleEncrypt(encryptText, numCols).toString();//Decrypted text is encryption method with encryptText and # of columns and then converted to a string
        decryptTF.setText(toUpper(decryptedText));//Change the decrypted text field to uppercase decrypted text results
        return decryptedText;//Return decrypted text
    }
    
    public StringBuffer caesarEncrypt(String plainText, int shift){//Caesar encryption
        StringBuffer encrypted = new StringBuffer();//New stringbuffer encrypted
        plainText = plainText.replaceAll("\\s", "");//Remove all spaces from text
        for (int q = 0; q < plainText.length(); q++){//Loop through until original text's length
            if (Character.isUpperCase(plainText.charAt(q))){//If the character is uppercase
                char ch = (char)(((int)plainText.charAt(q) + shift-65) % 26+65);//Shift the current character
                encrypted.append(ch);//Add the shifted character to the encrypted text
            }
            else{//Anything else
                char ch = (char)(((int)plainText.charAt(q) + shift-97) % 26+97);//Shift the current character by lowercase amounts
                encrypted.append(ch);//Add the shifted character to the encrypted text
            }
        }
        String Encrypted = encrypted.toString();//Encrypted is encrypted converted to a string
        encryptTF.setText(toUpper(Encrypted));//Set the encrypted text field to the encrypted text
        return encrypted;//Return encrypted
    }
    
    public String caesarDecrypt(String cipherText, int shift){//caesar decryption
        String dec = "";
        cipherText = cipherText.replaceAll("\\s", "");//remove spaces
        char ch;
        for (int i = 0; i < cipherText.length(); i++){//loop through length of text and decrypt using the shift amount
            ch = cipherText.charAt(i);
            if (ch >= 'a' && ch <= 'z'){//lowercase
                ch = (char)(ch-shift);
                if (ch < 'a'){
                    ch = (char)(ch + 'z' - 'a' + 1);
                }
                dec += ch;
            }
            else if (ch >= 'A' && ch <= 'Z'){//uppercase
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
        decryptTF.setText(toUpper(dec));//set decrypted text to new decrypted text
        return dec;
    }
    
    public String vigenereEncrypt(String plainText, String key){//vigenere encryption
        String encrypted = "";
        key = key.toUpperCase();//make key uppercase
        plainText = plainText.toUpperCase();//make text uppercase
        for (int i = 0, j = 0; i < plainText.length(); i++){//loop through length of text
            char c = plainText.charAt(i);
            if (c < 'A' || c > 'Z'){//if it's not a letter
                continue;//skip that char
            }
            encrypted += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');//add char to encrypted text
            j = ++j % key.length();
        }
        encryptTF.setText(toUpper(encrypted));//set encrypted text to new encrypted text
        return encrypted;//return encrypted
    }
    
    public String vigenereDecrypt(String decryptText, String key){//vigenere decrypt
        String dec = "";
        key = key.toUpperCase();//make key uppercase
        decryptText = decryptText.toUpperCase();//make text uppercase
        for (int i = 0, j = 0; i < decryptText.length(); i++){//loop through text length
            char c = decryptText.charAt(i);
            if (c < 'A' || c > 'Z'){//if it's not a letter
                continue;//skip that char
            }
            dec += (char)((c - key.charAt(j) + 26) % 26 + 'A');//add the shifted char to the decrypted text
            j = ++j % key.length();
        }
        decryptTF.setText(toUpper(dec));//set decrypted text to new decrypted text
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
