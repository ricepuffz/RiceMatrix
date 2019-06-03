package de.ricewaffle.ricematrix.system;

import de.ricewaffle.ricematrix.References;
import de.ricewaffle.ricematrix.container.Element;

public class Menu extends Program
{
	Element red = new Element("test/rice.re", new boolean[] { true, false, false});
	Element green = red.tint(new boolean[] { false, true, false });
	Element blue = red.tint(new boolean[] { false, false, true });
	
	//private int[] pos = { 0, 0 };
	
	public Menu()
	{
		super("menu");
	}

	@Override
	public void run()
	{
		long last = 0;
		
		while (true)
		{
			leds.setState(0, 0, false, true, false);
			apply();
			
			if (KeyStates.up() && last + 2000 <= System.currentTimeMillis())
			{
				References.main.openProgram("snake");
				last = System.currentTimeMillis();
			}
		}
		
		/*
		while (true)
		{
			boolean change = false;
			leds.setState(pos[0], pos[1], false, false, false);
			
			if (KeyStates.up() && pos[1] - 1 >= 0)
			{
				pos[1]--;
				change = true;
			}
			else if (KeyStates.down() && pos[1] + 1 < References.leds.height())
			{
				pos[1]++;
				change = true;
			}
			else if (KeyStates.left() && pos[0] - 1 >= 0)
			{
				pos[0]--;
				change = true;
			}
			else if (KeyStates.right() && pos[0] + 1 < References.leds.width())
			{
				pos[0]++;
				change = true;
			}
			
			leds.setState(pos[0], pos[1], false, false, true);
			apply();
			if (change)
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} */
	}
}
