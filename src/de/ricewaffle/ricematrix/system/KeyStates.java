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
	
	
	private static String lastInputType = "";
	private static String lastDir = "";
	private static String lastButton = "";
	
	
	
	public static void setUp(boolean up) { KeyStates.up = up; if (up) lastDir = "up"; lastInputType = "dir"; }
	public static void setDown(boolean down) { KeyStates.down = down; if (down) lastDir = "down"; lastInputType = "dir"; }
	public static void setLeft(boolean left) { KeyStates.left = left; if (left) lastDir = "left"; lastInputType = "dir"; }
	public static void setRight(boolean right) { KeyStates.right = right; if (right) lastDir = "right"; lastInputType = "dir"; }
	
	public static void setA(boolean a) { KeyStates.a = a; if (a) lastButton = "a"; lastInputType = "button"; }
	public static void setB(boolean b) { KeyStates.b = b; if (b) lastButton = "b"; lastInputType = "button"; }
	public static void setC(boolean c) { KeyStates.c = c; if (c) lastButton = "c"; lastInputType = "button"; }
	public static void setD(boolean d) { KeyStates.d = d; if (d) lastButton = "d"; lastInputType = "button"; }
	
	
	public static boolean up() { return up; }
	public static boolean down() { return down; }
	public static boolean left() { return left; }
	public static boolean right() { return right; }
	
	public static boolean a() { return a; }
	public static boolean b() { return b; }
	public static boolean c() { return c; }
	public static boolean d() { return d; }
	
	public static String lastInputType() { return lastInputType; }
	public static String lastDir() { return lastDir; }
	public static String lastButton() { return lastButton; }
}
