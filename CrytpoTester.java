package Cryption;
import Cryption.Cryption;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class CrytpoTester {
	public static void main( String[] args ) {
		Cryption test = new Cryption();
        try {
            test.encryptFile( "src/Password.txt", "password" );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {      
            e.printStackTrace();
        }
    }
}
