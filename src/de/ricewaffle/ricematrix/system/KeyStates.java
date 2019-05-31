package de.ricewaffle.ricematrix.system;

public class KeyStates
{
	private static boolean up = false;
	private static boolean down = false;
	private static boolean left = false;
	private static boolean right = false;
	
	private static boolean a = false;
	private static boolean b = false;
	private static boolean c = false;
	private static boolean d = false;
	
	
	
	
	public static void setUp(boolean up) { KeyStates.up = up; }
	public static void setDown(boolean down) { KeyStates.down = down; }
	public static void setLeft(boolean left) { KeyStates.left = left; }
	public static void setRight(boolean right) { KeyStates.right = right; }
	
	public static void setA(boolean a) { KeyStates.a = a; }
	public static void setB(boolean b) { KeyStates.b = b; }
	public static void setC(boolean c) { KeyStates.c = c; }
	public static void setD(boolean d) { KeyStates.d = d; }
	
	
	public static boolean up() { return up; }
	public static boolean down() { return down; }
	public static boolean left() { return left; }
	public static boolean right() { return right; }
	
	public static boolean a() { return a; }
	public static boolean b() { return b; }
	public static boolean c() { return c; }
	public static boolean d() { return d; }
}
