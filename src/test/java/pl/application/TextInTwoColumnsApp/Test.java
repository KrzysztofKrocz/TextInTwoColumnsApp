package pl.application.TextInTwoColumnsApp;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import junit.framework.TestCase;
import pl.application.impl.TextInTwoColumnsPrinter;
import static org.hamcrest.CoreMatchers.*;

public class Test extends TestCase {
	
	@org.junit.Test
	public void testMethodGetCharFromList(){
		TextInTwoColumnsPrinter txtPrinter = new TextInTwoColumnsPrinter("Tekst",10);
		List<Character> charList = new ArrayList<Character>();
		charList.add('a');
		charList.add('b');
		charList.add('c');
		charList.add('d');
		assertThat(txtPrinter.getCharsFromList(charList, 0, 2),is(equalTo("ab")));
	}
	
	@org.junit.Test
	public void testMethodgetcompleteTextCharsList(){
		char tab[] = {'A','l','a',' ','m','a',' ','k','o','t','a'};
		TextInTwoColumnsPrinter txtPrinter = new TextInTwoColumnsPrinter("Tekst",10);
		List<Character> charList = new ArrayList<Character>();
		charList.add('A');
		charList.add('l');
		charList.add('a');
		charList.add(' ');
		charList.add(' ');
		charList.add('m');
		charList.add('a');
		charList.add(' ');
		charList.add('k');
		charList.add('-');
		charList.add('o');
		charList.add('t');
		charList.add('a');
		assertThat(txtPrinter.getcompleteTextCharsList(tab, 5),is(charList));
	}
	
	public void testMethodprintTextInTwoColumns(){
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     TextInTwoColumnsPrinter txtPrinter = new TextInTwoColumnsPrinter("Ala ma kota a kot ma Ale",10);
	     txtPrinter.printTextInTwoColumns();
	     assertEquals("Ala ma ko-" + "  " + "ma Ale"+ '\r'+'\n' + "ta a kot    " + '\r'+'\n', outContent.toString());
	     outContent.reset();
	     txtPrinter.setTextToDisplay("Tekst");
	     txtPrinter.printTextInTwoColumns();
	     assertEquals("Tekst"+'\r'+'\n',outContent.toString());
	}
}
