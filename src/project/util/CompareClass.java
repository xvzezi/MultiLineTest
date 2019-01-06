package project.util;

import java.io.BufferedReader;
import java.io.FileReader;

import project.code.MyCalendar;
import project.code.MyCalendar_v1;

public class CompareClass {
	
	public String Compare(String fileName, String oldFileName) throws Exception {
		String result = "";
		FileReader fr1 = new FileReader(fileName);
		FileReader fr2 = new FileReader(oldFileName);
		BufferedReader br = new BufferedReader(fr1);
		BufferedReader brOld = new BufferedReader(fr2);
		String line = br.readLine();
		String lineOld = brOld.readLine();
		String method0 = "produceManPowerCost";
		String method1 = "sellingManPowerCost";
		String method2 = "profitsSelf";
		while(!line.contains(method0)) {
			line = br.readLine();
		}
		while(!lineOld.contains(method0)) {
			lineOld = brOld.readLine();
		}
		while(!line.contains(method1) && !lineOld.contains(method1)) {
			if(!line.equals(lineOld)) {
				result += method0;
				break;
			}
			line = br.readLine();
			lineOld = brOld.readLine();
		}
		
		while(!line.contains(method1)) {
			line = br.readLine();
		}
		while(!lineOld.contains(method1)) {
			lineOld = brOld.readLine();
		}
		while(!line.contains(method2) && !lineOld.contains(method2)) {
			if(!line.equals(lineOld)) {
				result += method1;
				break;
			}
			line = br.readLine();
			lineOld = br.readLine();
		}
		
		while(!line.contains(method2)) {
			line = br.readLine();
		}
		while(!lineOld.contains(method2)) {
			lineOld = brOld.readLine();
		}
		while(line != null && lineOld != null) {
			if(!line.equals(lineOld)) {
				result += method2;
				break;
			}
			line = br.readLine();
			lineOld = brOld.readLine();
		}
		br.close();
		brOld.close();
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		CompareClass cc = new CompareClass();
		System.out.println(cc.Compare("src/project/code/ComFinManager.java", "src/project/code/ComFinManager_old.java"));
	}
}
