package project.code;

import project.util.Point;

public class LineMath {
	/**
	 * isLine
	 * @param a
	 * @param b
	 * @param c
	 * @return whether can be a formula as a*x+b*y=z
	 */
	public boolean isLine(double a, double b, double c) {
		if(a == 0 && b == 0 && c != 0)
		{
			return false;
		}
		return true;
	}
	
	public boolean isParallel(double a0, double b0, double c0, 
			double a1, double b1, double c1) {
		if(a0 == 0 && a1 == 0)
		{
			// b*y = z
			return true;
		}
		if(b0 == 0 && b1 == 0)
		{
			// a*x = z
			return true;
		}
		
		// intercept
		double k0 = a0 / b0;
		double k1 = a1 / b1;
		double k2 = b0 / a0;
		double k3 = b1 / a1;
		double dis0 = k0 - k1;
		double dis1 = k2 - k3;
		if(dis0 < 0) dis0 = -dis0;
		if(dis1 < 0) dis1 = -dis1;
		if(dis0 < 1e-8 && dis1 < 1e-8)
			return true;
		
		// 
		return false;
	}
	
	public Point evalCrossPoint(double a0, double b0, double c0, 
			double a1, double b1, double c1) {
		if(!(isLine(a0,b0,c0) && isLine(a1, b1, c1))) {
			return null;
		}
		if(isParallel(a0,b0,c0,a1,b1,c1)) {
			return null;
		}
		double x = (c0*b1 - c1*b0) / (a0*b1 - a1*b0);
		double y = (c0*a1 - c1*a0) / (b0*a1 - b1*a0);
		return new Point(x,y);
	}
	
	public boolean isSameLine(double a0, double b0, double c0,
			double a1, double b1, double c1) {
		if(!(isLine(a0, b0, c0) && isLine(a1, b1, c1))) {
			return false;
		}
		if(!isParallel(a0, b0, c0, a1, b1, c1)) {
			return false;
		}
		
		//
		double div0, div1;
		if(a0 == 0 && a1 == 0) {
			div0 = c0 / b0;
			div1 = c1 / b1;
		}
		else {
			div0 = c0 / a0;
			div1 = c1 / a1;
		}
		double dis = div0 - div1;
		if(dis < 0) dis = -dis;
		return dis < 1e-8;
	}
}
