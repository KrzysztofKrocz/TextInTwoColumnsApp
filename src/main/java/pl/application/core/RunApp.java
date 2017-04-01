package pl.application.core;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.application.api.ITextInTwoColumnsPrinter;
import pl.application.impl.TextInTwoColumnsPrinter;

/**
 * Klasa uruchomieniowa dla aplikacji.
 * 
 * @author Krzysztof Krocz
 *
 */
public class RunApp 
{
	

	
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner(System.in);

    	String text = scanner.nextLine();
    	ITextInTwoColumnsPrinter txt = new TextInTwoColumnsPrinter(text,10);
    	txt.printTextInTwoColumns();
     }
      
    
}
