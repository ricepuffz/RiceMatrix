package de.ricewaffle.ricematrix.system;

import de.ricewaffle.ricematrix.container.Element;

public class Menu extends Program
{
	public Menu()
	{
		Element rice = new Element("elements/test/rice.re");
		
		
		while (true)
		{
			leds.drawBox(0, 0, 13, 6, false, false, true);
			leds.drawLedScreen(rice.content(), 1, 1, false);
			
			apply();
		}
	}
}
