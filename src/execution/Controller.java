package execution;

import java.util.ArrayList;
import java.util.HashMap;

import keywordLibrary.KeywordLibrary;
import utilities.Excel;

public class Controller {

	public static void main(String[] args) throws Exception {
		
		HashMap<Integer, ArrayList<String>> testCases =	Excel.getSheetData("TestCases");
		for (Integer keyTC : testCases.keySet()) {
			ArrayList<String> testCaseDetail = testCases.get(keyTC);
			if(testCaseDetail.get(1).equals("run")) {
				HashMap<Integer, ArrayList<String>> testData =	Excel.getSheetData(testCaseDetail.get(0));
				System.out.println(testData);
				for (Integer key : testData.keySet()) {
					ArrayList<String> values = testData.get(key);
					System.out.println(values);
					KeywordLibrary.processor(values.get(0), values.get(1), values.get(2), values.get(3),
							testCaseDetail.get(0), key);
				}
			
			} else {
				System.out.println("Testcase: " + testCaseDetail.get(0) + " was skipped!");
			}
		}
		
	}

}
