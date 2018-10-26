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
	public Double evalDistanceFromOrigin(Point p) {
		if(p == null) return null;
		double s_distance = p.getX()*p.getX() + p.getY()*p.getY();
		return Math.sqrt(s_distance);
	}
	
	public Double evalFarthestCrossPointOnThreeLines(
			double a0, double b0, double c0,
			double a1, double b1, double c1,
			double a2, double b2, double c2) {
		// judge if lines
		if(!(lm.isLine(a0,b0,c0) && 
				lm.isLine(a1, b1, c1) && 
				lm.isLine(a2, b2, c2))) {
			return null;
		}
		
		// get the cross points
		Point p0 = lm.evalCrossPoint(a0, b0, c0, a1, b1, c1);
		Point p1 = lm.evalCrossPoint(a0, b0, c0, a2, b2, c2);
		Point p2 = lm.evalCrossPoint(a2, b2, c2, a1, b1, c1);
		if(p0 == null && p1 == null && p2 == null)
		{
			return null;
		}
		
		// get the distance 
		Double dis0 = this.evalDistanceFromOrigin(p0);
		Double dis1 = this.evalDistanceFromOrigin(p1);
		Double dis2 = this.evalDistanceFromOrigin(p2);
		if(dis0 == null) dis0 = 0.0;
		if(dis1 == null) dis1 = 0.0;
		if(dis2 == null) dis2 = 0.0;
		double maxResult = dis0;
		if(maxResult < dis1) maxResult = dis1;
		if(maxResult < dis2) maxResult = dis2;
		
		return maxResult;
	}

	public String problem2(double a0, double b0, double c0,
			double a1, double b1, double c1,
			double a2, double b2, double c2) {
		Point point1 = lm.evalCrossPoint(a0, b0, c0, a1, b1, c1);
		Point point2 = lm.evalCrossPoint(a0, b0, c0, a2, b2, c2);
		Point point3 = lm.evalCrossPoint(a1, b1, c1, a2, b2, c2);
		if (point1 == null || point2 == null || point3 == null)
			return "非三角形";
		double e1 = (point1.getX() - point2.getX()) * (point1.getX() - point2.getX()) +
				(point1.getY() - point2.getY()) * (point1.getY() - point2.getY());
		double e2 = (point1.getX() - point3.getX()) * (point1.getX() - point3.getX()) +
				(point1.getY() - point3.getY()) * (point1.getY() - point3.getY());
		double e3 = (point2.getX() - point3.getX()) * (point2.getX() - point3.getX()) +
				(point2.getY() - point3.getY()) * (point2.getY() - point3.getY());
		if (e1 == e2 && e1 == e3)
			return "等边三角形";
		else if (e1 == e2 || e1 == e3 || e2 == e3)
			return "等腰三角形";
		return "一般三角形";
	
	}

}
