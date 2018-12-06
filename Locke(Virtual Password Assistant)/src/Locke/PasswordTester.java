package Locke;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Properties;
import java.util.logging.*;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import Locke.Cryption;
import Locke.PasswordManager;
public class PasswordTester {
	Cryption c = new Cryption();
	PasswordManager d = new PasswordManager();
	String pw;
	String encpw;
	String decpw;
	String error = "Website already exists in your saved records!";
	String error2 = "Website does not exist in your saved records!";
	Properties prop = new Properties();
	String propertiesFilePath = ("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties");
	PasswordTester() throws Exception{
		Logs.log(Level.INFO, Logs.class.getName());
	}
	public String EncryptedGenerator(String wSite, int pwLength) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {		
		prop.load(new FileInputStream("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties"));
		if(prop.getProperty(wSite.toLowerCase()) != null) {
			return error;
		}else {
			pw = d.generatePassword(pwLength);
			encpw = c.encrypt(pw);
			d.storeInfo(wSite, encpw);
			return wSite +  " password is: " + pw + " has been saved.";
		}
	}
	public String EncryptedSelfCreatePW(String wSite, String password) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		prop.load(new FileInputStream("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties"));
		if(prop.getProperty(wSite.toLowerCase()) != null) {
			return error;
		}else {
			pw = d.selfCreatePassword(wSite, password);
			encpw = c.encrypt(pw);
			d.storeInfo(wSite, encpw);
			return wSite +  " password is: " + pw + " has been saved.";
		}
	}
	public String EncyptedPasswordUpdater(String wSite, String password) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException {
		prop.load(new FileInputStream("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties"));
		if(prop.getProperty(wSite.toLowerCase()) != null) {
			encpw = c.encrypt(password);
			d.passwordUpdater(wSite, encpw);
			return wSite +  " password has been updated and has been saved.";
			
		}else {
			return error2;
		}	
	}
	public String DisplayDecryptedPassword(String wSite) throws FileNotFoundException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		prop.load(new FileInputStream("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties"));
		if(prop.getProperty(wSite.toLowerCase()) != null) {
			pw = d.displayPassword(wSite);
			decpw = c.decrypt(pw);
			return wSite + " password is: " +  decpw;
		}else{
			return error2;
		}
		
	}
	public boolean loginChecker(String username, String pw) throws FileNotFoundException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties"));
		if(prop.getProperty(username.toLowerCase()) != null) {
			String password = d.displayPassword(username);
			decpw = c.decrypt(password);
			if(decpw.equals(pw)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}	
	}
}
