package de.ricewaffle.ricematrix.output;

import de.ricewaffle.ricematrix.container.LedState;

public class Renderer implements Output
{
	@Override
	public void draw(LedState[][] states)
	{
		try {
			System.out.println("Renderer not implemented! Use argument 'emulated'");
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
