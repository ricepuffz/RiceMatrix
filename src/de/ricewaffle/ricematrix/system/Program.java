package de.ricewaffle.ricematrix.system;

import de.ricewaffle.ricematrix.References;
import de.ricewaffle.ricematrix.container.LedScreen;

public abstract class Program
{
	private String name;
	private LedScreen globalLeds = References.leds;
	protected LedScreen leds = new LedScreen(globalLeds.width(), globalLeds. height());
	
	public Program(String name)
	{
		this.name = name;
	}
	
	protected void apply()
	{
		globalLeds.apply(leds);
	}
	
	public String name()
	{
		return name;
	}
	
	public abstract void run();
}
