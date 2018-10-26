package project;
import project.code.*;

public class Application {
	public static void main(String[] args)
	{
		// -------------------------- //
		System.out.println("Hello World!");
		
		// -------------------------- //
		MultiLineProblem solver = new MultiLineProblem();
		Double k = solver.evalFarthestCrossPointOnThreeLines(1, 2, 3, 4, 5, 6, 7, 8, 9);
		if(k == null) {
			System.out.println("Something Wrong");
		}else {
			System.out.println(k);
		}
	}
}
