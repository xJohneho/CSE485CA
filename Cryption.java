package Cryption;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
public class Cryption {
	

    //Arbitrarily selected 8-byte salt sequence:
    private static final byte[] salt = {
        (byte) 0x43, (byte) 0x76, (byte) 0x95, (byte) 0xc7, (byte) 0x5b, (byte) 0xd7, (byte) 0x45, (byte) 0x17 
    };

    private static Cipher createCipher(String pass, Boolean decryptMode) throws GeneralSecurityException{

        //Use a KeyFactory to derive the corresponding key from the passphrase:
        PBEKeySpec keySpec = new PBEKeySpec(pass.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(keySpec);

        //Create parameters from the salt and an arbitrary number of iterations:
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 52);

        /*Dump the key to a file for testing: */
        Cryption.keyToFile(key);

        //Set up the cipher:
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");

        //Set the cipher mode to decryption or encryption:
        if(decryptMode){
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParamSpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParamSpec);
        }

        return cipher;
    }

    //Encrypts a file using key from createCipher class
    public static void encryptFile(String fileName, String pass) throws IOException, GeneralSecurityException{
        byte[] decryptData;
        byte[] encryptData;
        File inFile = new File(fileName);
        //Generate the cipher using pass:
        Cipher c = Cryption.createCipher(pass, true);

        //Read in the file:
        FileInputStream inStream = new FileInputStream(inFile);

        int blockSize = 8;
        //Figure out how many bytes are padded
        int paddedCount = blockSize - ((int)inFile.length()  % blockSize );

        //Figure out full size including padding
        int padded = (int)inFile.length() + paddedCount;

        decryptData = new byte[padded];


        inStream.read(decryptData);

        inStream.close();

        //Write out padding bytes as per PKCS5 algorithm
        for( int i = (int)inFile.length(); i < padded; ++i ) {
        	decryptData[i] = (byte)paddedCount;
        }

        //Encrypt the file data:
        encryptData = c.doFinal(decryptData);


        //Write the encrypted data to a new file:
        FileOutputStream outStream = new FileOutputStream(new File(fileName + ".encrypted"));
        outStream.write(encryptData);
        outStream.close();
    }

    public static void decryptFiles(String fileName, String pass) throws IOException, GeneralSecurityException{
    	byte[] decryptData;
        byte[] encryptData;
        File inFile = new File(fileName);
    	//Cipher 
    }
    //Key put into text file 
    private static void keyToFile(SecretKey key){
        try {
            File keyFile = new File("src/CipherKey.txt");
            FileWriter keyStream = new FileWriter(keyFile);
            String encodedKey = "\n" + "Encoded version of key:  " + key.getEncoded().toString();
            keyStream.write(key.toString());
            keyStream.write(encodedKey);
            keyStream.close();
        } catch (IOException e) {
            System.err.println("Failure writing key to file");
            e.printStackTrace();
        }

    }
    
}
