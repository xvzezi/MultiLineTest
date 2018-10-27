package project.util;

import java.util.ArrayList;

public class UseCaseGenerator {
	public static void main(String[] args) {
//		int[] a = {0, 1, 300, 499, 500};
//		int[] b = {0, 1, 100, 499, 500};
		String[] a= {"2-1-1","2-1-2","2-1-2","2-1-3","2-1-3"};
		String[] b= {"2-2-1","2-2-2","2-2-2","2-2-3","2-2-3"};
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
//				System.out.println(""+ a[i] + '\t' + b[j]);
				String aa = a[i];
				String bb = b[j];
				if(!"".equals(aa)) {
					System.out.print(aa+';');
				}
				if(!"".equals(bb)) {
					System.out.print(bb+';');
				}
				System.out.println();
			}
		}
	}
	
	public void generator1() {
//		int[] a = {-1, 0, 1};
//		int[] b = {-1, 0, 20};
//		int[] c = {1, 0, 8, -1, 20};
		String[] a = {"3-1-3","", ""};
		String[] b = {"3-2-3","", ""};
		String[] c = {"", "", "","3-3-4","3-3-5"};
		for(int i = 0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				for(int k=0;k<5;k++)
				{
					String aa = a[i];
					String bb = b[j];
					String cc = c[k];
					// System.out.println();
					if(!"".equals(aa)) {
						System.out.print(aa+';');
					}
					if(!"".equals(bb)) {
						System.out.print(bb+';');
					}
					if(!"".equals(cc)) {
						System.out.print(cc+';');
					}
					System.out.println();
					
//					System.out.println(""+
//							a[i]+'\t'+
//							b[j]+'\t'+
//							c[k]);
				}
			}
		}
	}
}
