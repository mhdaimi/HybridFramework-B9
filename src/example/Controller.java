package example;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

	public static void main(String[] args) throws Exception {

	HashMap<Integer, ArrayList<String>> testData =	Excel.getSheetData("Goibibo");
	System.out.println(testData);
	
	for (Integer key : testData.keySet()) {
		ArrayList<String> values = testData.get(key);
		System.out.println(values);
		KeywordLibrary.processor(values.get(0), values.get(1), values.get(2), values.get(3));
	}
	}

}
