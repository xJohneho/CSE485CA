package Locke;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Base64;
   

public class Cryption {
	private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_FORMAT = "DESede";
    
    private String encKey;
    private String encFormat;
    private KeySpec kSpec;
    private SecretKeyFactory secretKeyFac;
    SecretKey key;
    private Cipher cipher;
    byte[] arrBytes;
    
    public Cryption() throws Exception {
        encKey = "TahtBaKpvrtdTadsOsSprfvz";
        encFormat = DESEDE_ENCRYPTION_FORMAT;
        arrBytes = encKey.getBytes(UNICODE_FORMAT);//Encode string as bytes
        kSpec = new DESedeKeySpec(arrBytes);
        secretKeyFac = SecretKeyFactory.getInstance(encFormat);
        cipher = Cipher.getInstance(encFormat);
        key = secretKeyFac.generateSecret(kSpec);
    }


    public String encrypt(String unencryptedString) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        String encryptedString = null;

        cipher.init(Cipher.ENCRYPT_MODE, key);//Cipher is instantiated with Encrypt Mode
        byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);//Encode string as bytes
        byte[] encryptedText = cipher.doFinal(plainText);
        encryptedString = new String(Base64.getEncoder().encode(encryptedText));
        
        return encryptedString;
    }

    public String decrypt(String encryptedString) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String decryptedText = null;
        
        cipher.init(Cipher.DECRYPT_MODE, key);//Cipher is instantiated with Decrypt Mode
        byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
        byte[] plainText = cipher.doFinal(encryptedText);
        decryptedText= new String(plainText);
        return decryptedText;
    }

    
}