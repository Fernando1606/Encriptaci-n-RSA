//Imports

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class principal {
	public static void main(String[] args) throws Exception {

		// Lectura del archivo para cifrar

		InputStream IS = new FileInputStream("./ficheros/encriptar.txt");
		Scanner scanner = new Scanner(IS);
		while (scanner.hasNextLine()) {

			// Texto a cifrar
			String txt = scanner.nextLine();
			System.out.println("\nTexto a cifrar:");
			System.out.println(txt);

			// Instanciamos la clase
			RSA rsa = new RSA();

			// Generamos el par de claves
			rsa.genKeyPair(4096);

			// Cogemos la privada y la publica
			String file_private = "./ficheros/clave.pri";
			String file_public = "./ficheros/clave.pub";

			// Las guardamos asi podemos usarlas despues
			rsa.saveToDiskPrivateKey(file_private);
			rsa.saveToDiskPublicKey(file_public);

			// En sc encriptamos el texto y lo guardamos, asi lo mostramos despues

			/*
			 * El ejercicio de desencriptar es igual, solo hay que modificar la linea:
			 * 
			 * String sc=rsa.Encrypt(txt);
			 * 
			 * por:
			 * 
			 * String sc=rsa.Decrypt(txt);
			 */

			String sc = rsa.Encrypt(txt);

			// Ciframos
			System.out.println("-----------------------------");
			escribirArchivo("./ficheros/source.txt", sc);
			System.out.println("-----------------------------");
			System.out.println("\nCifrado:");
			System.out.println(sc);
		}

	}

	// Metodo para volcar resultado
	public static void escribirArchivo(String pos, String text) {
		try {
			FileWriter myWriter = new FileWriter(pos);
			myWriter.write(text);
			myWriter.close();
			System.out.println("\nCifrado creado");
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
