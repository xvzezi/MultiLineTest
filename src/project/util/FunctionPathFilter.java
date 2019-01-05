package project.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Random;

public class FunctionPathFilter {
	public FunctionPathFilter(String test_case_file) throws Exception {
		// 1. read in the UC_ALL_PATH.uc
		FileReader fr = new FileReader(test_case_file);
		BufferedReader br = new BufferedReader(fr);
		System.out.println("File readed..." + test_case_file);
		this.loadTestCases(br);
		br.close();
		System.out.println("File closed..." + test_case_file);
		
		pathTags = new ArrayList<>();
		funcTags = new ArrayList<>();
	}
	
	public void addPathTag(String funcTag) {
		pathTags.add(funcTag);
	}
	
	public void addFuncTag(String funcTag) {
		funcTags.add(funcTag);
	}
	
	public void prepareTestCases(int threshold, double chance) {
		glines = new ArrayList<>();
		HashMap<Integer, Integer> chosen = new HashMap<>();
		Random r = new Random();
		
		// 1. check the func tags 
		for(String tag : funcTags) {
			ArrayList<Integer> records = funcTagCases.get(tag);
			for(Integer i : records) {
				if(!chosen.containsKey(i)) {
					chosen.put(i, 0);
				}
			}
		}
		
		// 2. check the path tags 
		for(String tag : pathTags) {
			ArrayList<Integer> records = pathTagCases.get(tag);
			int r_amount = records.size();
			for(Integer i : records) {
				if(r_amount > threshold && r.nextDouble() < chance) {
					continue;
				}
				if(!chosen.containsKey(i)) {
					chosen.put(i, 0);
				}
			}
		}
		
		// 3. get all the lines 
		Set<Integer> lindex = chosen.keySet();
		Set<Integer> sort_lindex = new TreeSet<>();
		sort_lindex.addAll(lindex);
		for(Integer i : sort_lindex) {
			System.out.println(i);
			glines.add(raw_cases.get(i));
		}
	}
	
	public void generateTestCases(String test_case_file) throws Exception {
		// write the glines into file
		FileWriter fw = new FileWriter(test_case_file);
		
		for(String line : glines) {
			fw.write(line + '\n');
		}
		
		fw.flush();
		fw.close();
		System.out.println("File Written..." + test_case_file);
	}
	
	/*************************************/
	void loadTestCases(BufferedReader br) throws Exception {
		raw_cases = new ArrayList<>();
		pathTagCases = new HashMap<>();
		funcTagCases = new HashMap<>();
		
		// read in line by line 
		String line = br.readLine();
		int case_index = 0;
		while(line != null) {
			raw_cases.add(line);
			// process the line 
			String[] tmp = line.split("\t");
			String tagName = tmp[0];
			int param_count = Integer.parseInt(tmp[1]);
			int path_i = 2 + param_count;
			int path_count = Integer.parseInt(tmp[path_i]);
			
			// add the index to the func tag
			if(!funcTagCases.containsKey(tagName)) {
				funcTagCases.put(tagName, new ArrayList<>());
			}
			funcTagCases.get(tagName).add(case_index);
			
			// add the corresponding index to each path tags 
			for(int i = 0; i < path_count; i++) {
				String pathTag = tmp[path_i + i + 1];
				if(!pathTagCases.containsKey(pathTag)) {
					pathTagCases.put(pathTag, new ArrayList<>());
				}
				pathTagCases.get(pathTag).add(case_index);
			}
			
			line = br.readLine();
			case_index++;
		}
	}
	
	/*************************************/
	// 1. basic test cases
	ArrayList<String> raw_cases;
	HashMap<String, ArrayList<Integer>> pathTagCases;
	HashMap<String, ArrayList<Integer>> funcTagCases;
	
	// 2. recorder
	ArrayList<String> pathTags;
	ArrayList<String> funcTags;
	
	// 3. generated
	ArrayList<String> glines;
	

	/*************************************/
	public static void main(String[] args) {
		try {
			FunctionPathFilter filter = new FunctionPathFilter("UC_ALL_PATH.uc");
			filter.addPathTag("CFM.PMPC");
			filter.addPathTag("MC.DOM");
//			filter.addFuncTag("CFM.PS");
			filter.prepareTestCases(5, 0.2);
			filter.generateTestCases("UC_ALL_v2.uc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
