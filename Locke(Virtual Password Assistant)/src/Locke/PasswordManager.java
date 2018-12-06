package Locke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang3.RandomStringUtils;
import Locke.Logs;
public class PasswordManager {
	char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
	String password;
	Properties prop = new Properties();
	String propertiesFilePath = ("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties");
	public PasswordManager(){
		Logs.log(Level.INFO, Logs.class.getName());
	}
	
	public String generatePassword(int pwLength) throws IOException {
		FileInputStream fis = new FileInputStream(propertiesFilePath);
		prop.load(fis);
		String randomPW = RandomStringUtils.random( pwLength, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
		Logs.log(Level.INFO, randomPW);
		return randomPW;
	}
	public String selfCreatePassword(String website, String pw) throws IOException {
		FileInputStream fis = new FileInputStream(propertiesFilePath);
		prop.load(fis);
		//storeInfo(website, pw);
		Logs.log(Level.INFO, website + ":" + pw);
		return pw;
	}
	public void passwordUpdater(String website, String pw) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(propertiesFilePath);
		prop.load(fis);
		prop.setProperty(website, pw);
		FileOutputStream fos = new FileOutputStream(propertiesFilePath);
        prop.store(fos,null);
        Logs.log(Level.INFO, "website + \" password updated to \"+ pw");
	}
	public String deletePassword(String website) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(propertiesFilePath);
		prop.load(fis);
		String a = "\nRemoving "+ website + " and related password";
		String b = "\nWebsite does not exist!";
		if(prop.getProperty(website.toLowerCase()) != null) {
			prop.remove(website);
			FileOutputStream fos = new FileOutputStream(propertiesFilePath);
	        prop.store(fos,null);
	        Logs.log(Level.INFO, a);
			return a;
		}else {
			Logs.log(Level.INFO, b);
			return b;
		}
		
	}
	public void storeInfo(String website, String pw) throws IOException {
		File file = new File("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties");
		if(file.exists()) {
			file.setWritable(true);
			FileWriter fout = new FileWriter("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties", true);
			
			fout.write(website.toLowerCase() + ":" + pw + System.lineSeparator());
			fout.close();
		}
		else {
			Logs.log(Level.INFO, "File does not exist");
		}
	}
	public String displayPassword(String website) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\John\\Desktop\\Locke\\files\\config.properties"));
		String go = prop.getProperty(website);
		Logs.log(Level.INFO, go);
		return go;
		
	}
	

}
