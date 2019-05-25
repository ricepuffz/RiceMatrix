package de.ricewaffle.ricematrix.system;

import de.ricewaffle.ricematrix.References;
import de.ricewaffle.ricematrix.container.LedScreen;

public class Program
{
	private LedScreen globalLeds = References.leds;
	protected LedScreen leds = new LedScreen(globalLeds.width(), globalLeds. height());
	
	protected void apply()
	{
		globalLeds.apply(leds);
	}
}
