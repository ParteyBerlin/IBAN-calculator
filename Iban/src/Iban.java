import java.util.*;
import java.math.*;

public class Iban {

	public static void main(String[] args) 
	{
		//Eingabe
		Scanner scn = new Scanner(System.in);
		System.out.println("Konto eingeben: ");
		String ktoEingabe = scn.next();
		
		
			//Kurze Kontonummern mit Nullen auffüllen
				while(ktoEingabe.length() < 10)
				{
					ktoEingabe = "0" + ktoEingabe;
				}
			
			
		System.out.println("BLZ eingeben: ");
		String blzEingabe = scn.next();
		String zeichenkette = blzEingabe+ktoEingabe+131400;
		
		//////////////////////////////////////////////////////
		//Prüfziffer berechnen und zurückgeben//
		int pruefziffer = generateIbanChecksum(zeichenkette);
		
		
		//////////////////////////////////////////////////////
		//IBAN generieren und ausgeben//
		generateIban(pruefziffer, blzEingabe, ktoEingabe);
	}
	
	
	public static int generateIbanChecksum(String kompletteKette)
	{
		//BigInteger anlegen um Modulo zu berechnen und Werter zuweisen
		BigInteger zeichenkette;
		BigInteger modulo;
		BigInteger restBig;
		zeichenkette = new BigInteger(kompletteKette);
		modulo = new BigInteger("97");
		
		
		//Modulo berechnen
		restBig = zeichenkette.mod(modulo);
				
		
		//Integer anlegen und BigInteger in int umwandeln um Minus rechnen zu können
		Integer restInt;
		restInt = restBig.intValue();
		
		
		//Minus rechnen
		int pruefzifferInt = 98 - restInt;
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
