package Cryption;
import java.io.File;
import java.io.FileOutputStream;

import Cryption.Cryption;


public class CryptoTester {
	public static void main( String[] args ) throws Exception{
		Cryption td= new Cryption();

        String target="Amazon: AlphaRomea123@";
        String encrypted=td.encrypt(target);
        String decrypted=td.decrypt(encrypted);

        System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String:" + encrypted);
        System.out.println("Decrypted String: " + decrypted);
	}

}