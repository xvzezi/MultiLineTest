package project.code;
import project.util.*;
import java.math.*;

/**
 * Multi Line Problem
 * @author Sturmfy
 * @version 0
 * @since 2018-10-25
 * Problem Description
 * 	1. Given 3 individual lines in the form of (a, b, c), which
 * stands for a line formula a*x+b*y=c, we want the farthest
 * cross point's distance from the origin point.
 * 	2. Given 3 lines and judge whether the surrounded area is a
 * triangle and what kind of triangle
 */
public class MultiLineProblem {
	LineMath lm;
	
	public MultiLineProblem() {
		this.lm = new LineMath();
	}
	
	
	public Point evalCrossPoint(double a0, double b0, double c0, 
			double a1, double b1, double c1) {
		if(!(lm.isLine(a0,b0,c0) && lm.isLine(a1, b1, c1))) {
			return null;
		}
		if(lm.isParallel(a0,b0,c0,a1,b1,c1)) {
			return null;
		}
		if(lm.isSameLine(a0, b0, c0, a1, b1, c1)) {
			return null;
		}
		double x = (c0*b1 - c1*b0) / (a0*b1 - a1*b0);
		double y = (c0*a1 - c1*a0) / (b0*a1 - b1*a0);
		return new Point(x,y);
	}

	public boolean isRightTriangle(
			double a0, double b0, double c0,
			double a1, double b1, double c1,
			double a2, double b2, double c2) {
		// judge if lines
		if(!(lm.isLine(a0,b0,c0) && 
				lm.isLine(a1, b1, c1) && 
				lm.isLine(a2, b2, c2))) {
			return false;
		}
		if (lm.isParallel(a0, b0, c0, a1, b1, c1) || 
				lm.isParallel(a0, b0, c0, a2, b2, c2) || 
				lm.isParallel(a1, b1, c1, a2, b2, c2))
			return false;
		if (a0 * a1 + b0 * b1 == 0 || a0 * a2 + b0 * b2 == 0 || a1 * a2 + b1 * b2 == 0)
			return true;
		return false;
		
	
	}
	

}
