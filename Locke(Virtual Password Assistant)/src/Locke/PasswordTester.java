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
	//String propertiesFilePath = ("/Users/John/Desktop/Locke/files/config.properties");
	PasswordTester() throws Exception{
		Logs.log(Level.INFO, Logs.class.getName());
	}
	public String EncryptedGenerator(String propertiesFilePath, String wSite, int pwLength, boolean inclUpper, boolean inclLower, boolean inclNum, boolean inclSpecial) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {		
		prop.load(new FileInputStream(propertiesFilePath));
		if(prop.getProperty(wSite.toLowerCase()) != null) {
			return error;
		}else {
			pw = d.generatePassword(propertiesFilePath, pwLength, inclUpper, inclLower, inclNum, inclSpecial);
			encpw = c.encrypt(pw);
			d.storeInfo(propertiesFilePath, wSite, encpw);
			return wSite +  " password is: " + pw + " has been saved.";
		}
	}
	public String EncryptedSelfCreatePW(String propertiesFilePath, String wSite, String password) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		prop.load(new FileInputStream(propertiesFilePath));
		if(prop.getProperty(wSite.toLowerCase()) != null) {
			return error;
		}else {
			pw = d.selfCreatePassword(propertiesFilePath, wSite, password);
			encpw = c.encrypt(pw);
			d.storeInfo(propertiesFilePath, wSite, encpw);
			return wSite +  " password is: " + pw + " has been saved.";
		}
	}
	public String EncyptedPasswordUpdater(String propertiesFilePath, String wSite, String password) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException {
		prop.load(new FileInputStream(propertiesFilePath));
		if(prop.getProperty(wSite.toLowerCase()) != null) {
			encpw = c.encrypt(password);
			d.passwordUpdater(propertiesFilePath, wSite, encpw);
			return wSite +  " password has been updated to " + password + " and has been saved.";
			
		}else {
			return error2;
		}	
	}
	public String DisplayDecryptedPassword(String propertiesFilePath, String wSite) throws FileNotFoundException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		prop.load(new FileInputStream(propertiesFilePath));
		if(prop.getProperty(wSite.toLowerCase()) != null) {
			pw = d.displayPassword(propertiesFilePath, wSite);
			decpw = c.decrypt(pw);
			return wSite + " password is: " +  decpw;
		}else{
			return error2;
		}
		
	}
	public boolean loginChecker(String propertiesFilePath, String username, String pw) throws FileNotFoundException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(propertiesFilePath));
		if(prop.getProperty(username.toLowerCase()) != null) {
			String password = d.displayPassword(propertiesFilePath, username);
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
