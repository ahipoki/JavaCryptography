class Main extends AppCompatActivity implements View.OnClickListener {
  decryptTV = new JTextField(20);
  encryptTV = new JTextField(20);

  encrypt = new JButton("Encrypt");
  encrypt.setMnemonic(KeyEvent.VK_E);
  encrypt.setActionCommand("encrypt");

  decrypt = new JButton("Decrypt");
  decrypt.setMnemonic(KeyEvent.VK_D);
  decrypt.setActionCommand("decrypt");

  encrypt.addActionListener(this);
  decrypt.addActionListener(this);

  JRadioButton scytale = new JRadioButton(scytale);
  scytale.setMnemonic(KeyEvent.VK_S);

  JRadioButton caesar = new JRadioButton(caesar);
  caesar.setMnemonic(KeyEvent.VK_C);

  JRadioButton vigenere = new JRadioButton(vigenere);
  vigenere.setMnemonic(KeyEvent.VK_V);

  ButtonGroup group = new ButtonGroup();

  int message;
  shiftButton = new JButton(shift);
  private int key;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    decryptTV = (TextView) this.findViewById(R.id.decryptTV);
    encryptTV = (TextView) this.findViewById(R.id.encryptTV);
    encrypt = (Button) this.findViewById(R.id.encrypt);
    decrypt = (Button) this.findViewById(R.id.decrypt);
    scytale = (RadioButton) this.findViewById(R.id.radioScytale);
    caesar = (RadioButton) this,.findViewById(R.id.radioCaesar);
    vigenere = (RadioButton) this.findViewById(R.id.radioVigenere);
    scytale.setOnClickListener(this);
    caesar.setOnClickListener(this);
    vigenere.setOnClickListener(this);
    group = (RadioGroup) this.findViewById(R.id.group1);
    messageButton = (Button) this.findViewById(R.id.message);
    shiftButton = (Button) this.findViewBtId(R.id.enterShift);
    messageButton.setOnClickListener(new View.OnClickListener(){
      public void onClick(View view){
    
      }
    });
  }

  public void onClick(View v){
    int selectedId = group.getCheckedRadioButtonId();
    group = (RadioGroup) this.findViewById(selectedId);
    if (v.equals(encrypt){
      if (caesar.isChecked()){
        caesar.Encrypt(messageEdit.getText().toString(), Integer.parseInt(shiftEdit.getText().toString()));
      }
      else if (vigenere.isChecked()){
        vigenereEncrypt(messageEdit.getText().toString(), Integer.parseInt(shiftEdit.getText().toString()));
      }
      else{
        scytaleEncrypt(messageEdit.getText().toString(), Integer.parseInt(shiftEdit.getText().toString()));
      }
    }
    else if (v.equals(decrypt)){
      if (caesar.isChecked()){
        caesarDecrypt(messageEdit.getText().toString(), Integer.parseInt(shiftEdit.getText().toString()));
      }
      else if (vigenere.isChecked()){
        vigenereDecrypt(messageEdit.getText().toString(), Integer.parseInt(shiftEdit.getText().toString()));
      }
      else{
        scytaleDecrypt(messageEdit.getText().toString(), Integer.parseInt(shiftEdit.getText().toString()));
      }
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

  public void CaesarKey(int key){
    this.key = key;
  }

  public String caesarEncrypt(String plainText, int shift){
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
  }

  public String caesarDecrypt(String cipherText, int shift){
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
  }
}
