package project.util;

import java.util.ArrayList;

public class UseCaseGenerator {
	public static void main(String[] args) {
//		int[] a = {-1, 0, 100, 400, 600};
//		int[] b = {-1, 0, 50, 200, 600};
//		int[] c = {1, 4, 8, -1, 20};
		String[] a = {"1-1-4","","","","1-1-5"};
		String[] b = {"1-2-4","","","","1-2-5"};
		String[] c = {"","","","1-3-4","1-3-5"};
		for(int i = 0;i<5;i++)
		{
			for(int j=0;j<5;j++)
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
				}
			}
		}
	}
}
