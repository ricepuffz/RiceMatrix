package de.ricewaffle.ricematrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import de.ricewaffle.ricematrix.container.LedScreen;
import de.ricewaffle.ricematrix.io.Medium;
import de.ricewaffle.ricematrix.io.emulator.Emulator;
import de.ricewaffle.ricematrix.io.renderer.Renderer;
import de.ricewaffle.ricematrix.system.Menu;
import de.ricewaffle.ricematrix.system.Program;
import de.ricewaffle.ricematrix.system.ProgramLoader;

public class Main
{
	private boolean running = true;
	
	private Medium output = null;
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
	
	
	public Main()
	{
		this(false);
	}
	
	public Main(boolean emulated)
	{
		this(emulated, "");
	}
	
	public Main(boolean emulated, String path)
	{
		References.main = this;
		References.path = path;
		
		leds = References.leds;
		
		if (emulated)
			output = new Emulator();
		else
			output = new Renderer();
		
		programs = new HashMap<String, Program>();
		context = new Stack<Program>();
		
		drawThread.start();
		
		registerSystemPrograms();
		registerAdditionalPrograms();
		openProgram("menu");
		running = false;
	}
	
	
	private void registerSystemPrograms()
	{
		registerProgram(new Menu());
	}
	
	private void registerAdditionalPrograms()
	{
		//for (Program program : ProgramLoader.loadFrom("programs"))
		for (Program program : ProgramLoader.loadFrom(References.path + "programs"))
		{
			registerProgram(program);
		}
	}
	
	private void registerProgram(Program program)
	{
		if (!programs.containsKey(program.name()))
			programs.put(program.name(), program);
		else
			System.out.println("Program with the name " + program.name() + " does already exist! Skipping registration");
	}
	
	public void openProgram(String name)
	{
		Class<? extends Program> programclass = programs.get(name).getClass();
		try {
			context.push((Program) programclass.newInstance());
			context.peek().run();
		} catch (InstantiationException e) {
			System.out.println("oops");
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
		{
			new Main(true);
		}
		else if (args.length == 2 && args[0].equalsIgnoreCase("emulated"))
		{
			new Main(true, args[1]);
		}
		else
			new Main();
	}
}
