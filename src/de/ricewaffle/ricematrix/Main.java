package de.ricewaffle.ricematrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import de.ricewaffle.ricematrix.container.LedScreen;
import de.ricewaffle.ricematrix.output.Emulator;
import de.ricewaffle.ricematrix.output.Output;
import de.ricewaffle.ricematrix.output.Renderer;
import de.ricewaffle.ricematrix.system.Menu;
import de.ricewaffle.ricematrix.system.Program;

public class Main
{
	private boolean running = true;
	
	private Output output = null;
	private LedScreen leds;
	
	private Map<String, Program> programs;
	private Stack<Program> context;
	
	
	Thread drawThread = new Thread(new Runnable() {
	    @Override
	    public void run() {
	    	while (running)
				output.draw(leds.stateArray());
	    	System.exit(1);
	    }
	});  
	
	
	public Main(boolean emulated)
	{
		References.main = this;
		leds = References.leds;
		
		if (emulated)
			output = new Emulator();
		else
			output = new Renderer();
		
		programs = new HashMap<String, Program>();
		context = new Stack<Program>();
		
		drawThread.start();
		
		registerSystemPrograms();
		openProgram("menu");
		running = false;
	}
	
	public Main()
	{
		this(false);
	}
	
	
	private void registerSystemPrograms()
	{
		registerProgram("menu", new Menu());
	}
	
	private void registerProgram(String name, Program program)
	{
		if (!programs.containsKey(name))
			programs.put(name, program);
		else
			System.out.println("Program with the name " + name + " does already exist! Skipping registration");
	}
	
	public void openProgram(String name)
	{
		Class<? extends Program> programclass = programs.get(name).getClass();
		try {
			context.push((Program) programclass.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void closeCurrentProgram()
	{
		context.pop();
	}
	
	
	
	public static void main(String[] args)
	{
		if (args.length == 1 && args[0].equalsIgnoreCase("emulated"))
			new Main(true);
		else
			new Main();
	}
}
