package de.ricewaffle.ricematrix.system;

import de.ricewaffle.ricematrix.References;

public class Menu extends Program
{
	public Menu()
	{
		super("menu");
	}

	@Override
	public void run()
	{
		References.main.openProgram("snake");
		while (true)
		{
			
		}
	}
}
