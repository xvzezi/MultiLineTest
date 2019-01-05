package project.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Function Path Monitor
 * @author Xu Zezi
 * @version 0.1
 * Description:
 * 	1. Load all the use cases from the files and format it into Trees
 * 	2. It can provide test cases by the Tag
 * 	3. It can help the stub function to record the function path
 * 	4. It can generate the test case files with function paths
 */
public class FunctionPathMonitor {
	public FunctionPathMonitor(String test_case_file) throws Exception {
		FileReader fr = new FileReader(test_case_file);
		BufferedReader br = new BufferedReader(fr);
		System.out.println("File readed..." + test_case_file);
		this.loadTestCases(br);
		br.close();
		System.out.println("File closed..." + test_case_file);
		inited = true;
	}

	public int prepareTestCases(String tagName) {
		// check if initialized
		if(!inited)
		{
			return 0;
		}
		
		// select the test cases and initialize the corresponding path records
		curCases = cases.get(tagName);
		cursor = 0;
		if(!cfpaths.containsKey(tagName)) {
			cfpaths.put(tagName, new ArrayList<>());
		}
		curPaths = cfpaths.get(tagName);
		return curCases.size();
	}
	
	public ArrayList<String> fetchNextTestCase(){
		if(cursor < curCases.size()) {
			ArrayList<String> result = curCases.get(cursor);
			cursor++;
			return result;
		}
		return null;
	}
	
	public void addCurTestCaseFuncPath(String funcTag) {
//		System.out.println(funcTag);
		int curp = cursor - 1;
		if(curp < curCases.size()) {
			while(curp >= curPaths.size()) {
				curPaths.add(new ArrayList<>());
			}
			curPaths.get(curp).add(funcTag);
		}
	}

	public void generateTestCases(String test_case_file) throws Exception {
		FileWriter fw = new FileWriter(test_case_file);
		cases.forEach((tagName, tagCases) -> {
			// prepare the func paths
			curPaths = cfpaths.get(tagName);
			int case_amount = tagCases.size();
			
			// prepare the output string and write
			for(int i = 0; i < case_amount; i++) {
				String line = tagName + '\t';				// tag name
				line += tagCases.get(i).size();				// param counts
				for(String param : tagCases.get(i)) {
					line += '\t' + param;
				}											// params
				if(i < curPaths.size()) {
					line += "\t" + curPaths.get(i).size();	// path counts
					for(String funcTag: curPaths.get(i)) {
						line += '\t' + funcTag;
					}										// paths
				}
				else {
					line += "\t" + 0;	// path counts
				}
				line += '\n';
				try {
					fw.write(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		fw.flush();
		fw.close();
		System.out.println("File Generated..." + test_case_file);
	}
	
	
	/********************************************/
	void loadTestCases(BufferedReader br) throws Exception {
		cases = new HashMap<>();
		cfpaths = new HashMap<>();
		// read in line by line 
		String line = br.readLine();
		while(line != null) {
			// process the line 
			String[] tmp = line.split("\t");
			String tagName = tmp[0];
			int param_count = Integer.parseInt(tmp[1]);
			
			// form a test case 
			ArrayList<String> testCase = new ArrayList<>();
			for(int i = 0; i < param_count; i++) {
				testCase.add(tmp[2 + i]);
			}
			
			// add it into the cases pool
			if(!cases.containsKey(tagName)) {
				cases.put(tagName, new ArrayList<>());
				cfpaths.put(tagName, new ArrayList<>());
			}
			cases.get(tagName).add(testCase);
			line = br.readLine();
		}
	}
	
	/********************************************/
	boolean inited = false;
	
	HashMap<String, ArrayList<ArrayList<String>>> cases;
	ArrayList<ArrayList<String>> curCases;
	int cursor;

	HashMap<String, ArrayList<ArrayList<String>>> cfpaths;
	ArrayList<ArrayList<String>> curPaths;
	
	
	/******************************************/
	public static void main(String[] args) {
		String uc_all = "UC_ALL.uc";
		try {
			System.out.println("begin...");
			FunctionPathMonitor fpm = new FunctionPathMonitor(uc_all);
			fpm.generateTestCases("UC_ALL_PATH.uc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
