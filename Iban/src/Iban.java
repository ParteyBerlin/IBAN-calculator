import java.util.*;
import java.math.*;

public class Iban {

	/**
	 * generates an IBAN by entering account-number and bank-code
	 */
	public static void main(String[] args) 
	{
		//enter account number
		Scanner scn = new Scanner(System.in);
		System.out.println("Konto eingeben: ");
		String ktoEingabe = scn.next();
		
		
			//if account number has less than 10 digits, it will be extended to 10 digits by putting 0 at the beginning
				while(ktoEingabe.length() < 10)
				{
					ktoEingabe = "0" + ktoEingabe;
				}
			
		//enter bank-code
		System.out.println("BLZ eingeben: ");
		String blzEingabe = scn.next();
		
		//build a String by connecting bank code, account number and a check number for germany (131400)		
		String zeichenkette = blzEingabe+ktoEingabe+131400;
		
		
		//////////////////////////////////////////////////////
		//Prüfziffer berechnen und zurückgeben//calculate IBAN-checknumber//
		int pruefziffer = generateIbanChecksum(zeichenkette);
		
		
		//////////////////////////////////////////////////////
		//IBAN generieren und ausgeben//calculate IBAN//
		generateIban(pruefziffer, blzEingabe, ktoEingabe);
	}
	
	
	public static int generateIbanChecksum(String kompletteKette)
	{
		//BigInteger anlegen um Modulo zu berechnen und Werte zuweisen
		//use BigInteger for the given String
		BigInteger zeichenkette;
		BigInteger modulo;
		BigInteger restBig;
		zeichenkette = new BigInteger(kompletteKette);
		modulo = new BigInteger("97");
		
		
		//Modulo berechnen
		//calculate modulo 97 of the given String and save it in restBig
		restBig = zeichenkette.mod(modulo);
				
		
		//Integer anlegen und BigInteger in int umwandeln um Minus rechnen zu können
		//convert from BigInteger to Integer to be able to calculate the next step
		Integer restInt;
		restInt = restBig.intValue();
		
		
		//Minus rechnen
		int pruefzifferInt = 98 - restInt;
		//returns the IBAN-checknumber
		return pruefzifferInt;
	}
	
	public static void generateIban(int pruefzifferGenerateIban , String blzEingabeGenerateIban ,  String ktoEingabeGenerateIban)
	{
		
		//String anlegen und Prüfziffer von int in String umwandeln
		String pruefzifferStr;
		pruefzifferStr = Integer.toString(pruefzifferGenerateIban); 
		
		
		// 0 vorranstellen, wenn Prüfziffer kleiner 10
		if(pruefzifferGenerateIban < 10)
		{
			System.out.println("\nDeine IBAN lautet: DE0" + pruefzifferStr + blzEingabeGenerateIban  + ktoEingabeGenerateIban);
		}
		else
		{
			System.out.println("\nDeine IBAN lautet: DE" + pruefzifferStr + blzEingabeGenerateIban  + ktoEingabeGenerateIban);
		}
	}

}
