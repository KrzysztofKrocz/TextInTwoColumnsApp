package pl.application.impl;

import java.util.ArrayList;
import java.util.List;

import pl.application.api.ITextInTwoColumnsPrinter;

/**
 * Klasa odpowiadająca za realizację wyswietlenia tekstu w dwóch kolumnach
 * 
 * @author Krzysztof Krocz
 *
 */
public class TextInTwoColumnsPrinter implements ITextInTwoColumnsPrinter {

	private String textToDisplay;
	private int rowWidth;

	/**
	 * 
	 * @param textToDisplayInTwoColumns
	 *            Łańcuch znaków, który ma być wyświetlony w dwóch kolumnach
	 * @param rowWidth
	 *            Długość wiersza.
	 */
	public TextInTwoColumnsPrinter(String textToDisplayInTwoColumns, int rowWidth) {
		super();
		this.textToDisplay = textToDisplayInTwoColumns;
		this.rowWidth = rowWidth;
	}

	@Override
	public void printTextInTwoColumns() {
		if (rowWidth <= 1) {
			System.out.println("Podano błędną długość wiersza");
			return;
		}
		char textCharTab[] = textToDisplay.toCharArray();
		List<Character> textCharsList = getcompleteTextCharsList(textCharTab);

		List<Character> firstColumnChars = new ArrayList<Character>();
		List<Character> secondColumnChars = new ArrayList<Character>();
		int numberOfSecondColumnChars = textCharsList.size() / 2;
		int numberOfFirstColumnChars = textCharsList.size() - numberOfSecondColumnChars;
		int columnsLength = (int) Math.ceil(Double.valueOf(numberOfFirstColumnChars) / Double.valueOf(rowWidth));
		numberOfFirstColumnChars = columnsLength * rowWidth;
		numberOfSecondColumnChars = textCharsList.size() - numberOfFirstColumnChars;
		if(numberOfSecondColumnChars <= 0){
			System.out.println(textToDisplay);
			return;
		}
		for (int i = 0; i < numberOfFirstColumnChars; i++) {
			firstColumnChars.add(textCharsList.get(i));
		}
		for (int i = numberOfFirstColumnChars; i < textCharsList.size(); i++) {
			secondColumnChars.add(textCharsList.get(i));
		}

		printTwoCharListsInTwoColumn(firstColumnChars, secondColumnChars, rowWidth, columnsLength);
	}

	/**
	 * Metoda zwracająca listę uzupełnioną o znaki końca linii i spacje aby nie
	 * bylo jednego znaku na końcu linii
	 * 
	 * @param textCharTab
	 *            Tablica znaków tekstu
	 * @return Lista znaków uzupełniona o '-' i ' '
	 */
	private List<Character> getcompleteTextCharsList(char textCharTab[]) {
		List<Character> textCharsList = new ArrayList<Character>();
		int charNumber = 0;
		for (int i = 0; i < textCharTab.length; i++) {
			charNumber = charNumber + 1;
			if (charNumber % rowWidth == 0) {
				if (i < textCharTab.length - 1 && textCharTab[i] != ' ' && textCharTab[i - 1] != ' ') {
					textCharsList.add('-');
					charNumber++;
				}
				if (textCharTab[i - 1] == ' ' && textCharTab[i] != ' ') {
					textCharsList.add(' ');
					charNumber++;
				}
			}
			textCharsList.add(textCharTab[i]);
		}
		return textCharsList;
	}

	/**
	 * Metoda wyświetlająca dwie listy znakowe w dwóch kolumnach o określonej
	 * długości wiersza
	 * 
	 * @param firstColumnChars
	 *            Lista pierwszej kolumny
	 * @param secondColumnChars
	 *            Lista drugiej kolumny
	 * @param rowSize
	 *            Liczba znaków w wierszu
	 * @param columnLength
	 *            Długość kolumny
	 */
	private void printTwoCharListsInTwoColumn(List<Character> firstColumnChars, List<Character> secondColumnChars,
			int rowSize, int columnLength) {
		String lineToPrint = "";
		int counter = 0;
		for (int i = 0; i < columnLength; i++) {

			while (counter + rowSize < firstColumnChars.size()) {
				lineToPrint += getCharsFromList(firstColumnChars, counter, counter + rowSize);

				if (counter + rowSize > secondColumnChars.size()) {
					lineToPrint += "  " + getCharsFromList(secondColumnChars, counter, secondColumnChars.size());
				} else {
					lineToPrint += "  " + getCharsFromList(secondColumnChars, counter, counter + rowSize);
				}
				System.out.println(lineToPrint);
				lineToPrint = "";
				counter += rowSize;
			}
			if (i == columnLength - 1) {
				System.out.println(getCharsFromList(firstColumnChars, counter, firstColumnChars.size()) + "  "
						+ getCharsFromList(secondColumnChars, counter, secondColumnChars.size()));
				break;
			}

		}

	}

	/**
	 * Metoda zwracajaca łańcuch znaków z listy o zadanym początku i końcu
	 * 
	 * @param charList
	 *            Lista z której wyciągamy znaki
	 * @param start
	 *            Początkowy indeks
	 * @param end
	 *            Końcowy indeks
	 * @return Łańcuch znaków
	 */
	private String getCharsFromList(List<Character> charList, int start, int end) {
		String chars = "";
		for (int i = start; i < end; i++) {
			chars += charList.get(i);
		}
		return chars;
	}
}
