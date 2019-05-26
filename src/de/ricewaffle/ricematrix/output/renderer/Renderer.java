package de.ricewaffle.ricematrix.output.renderer;

import de.ricewaffle.ricematrix.container.LedState;
import de.ricewaffle.ricematrix.output.Output;

public class Renderer implements Output
{
	private final ShiftRegister cathodes;
	private final ShiftRegister anodes;
	
	public Renderer()
	{
		cathodes = new ShiftRegister(21, 22, 23);
		anodes = new ShiftRegister(27, 28, 29);
	}
	
	
	@Override
	public void draw(LedState[][] states)
	{
		for (int y = 0; y < 7; y++)
		{
			for (int x = 0; x < 14; x++)
			{
				stateLed(x, y, states[y][x].getState());
			}
		}
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void stateLed(int x, int y, boolean[] color)
	{
		stateCathode(x);
		stateAnodes(y, color);
		cathodes.show();
		anodes.show();
	}
	
	private void stateCathode(int index)
	{
		int after = index;
		int before = 13 - index;
		
		for (int i = 0; i < after; i++)
			cathodes.shift(1);
		
		cathodes.shift(0);
		
		for (int i = 0; i < before; i++)
			cathodes.shift(1);
	}
	
	private void stateAnodes(int index, boolean[] color)
	{
		int after = 3 * (6 - index);
		int before = 3 * index;
		
		for (int i = 0; i < after; i++)
			anodes.shift(0);
		
		if (color[2])
			anodes.shift(1);
		else
			anodes.shift(0);
		if (color[1])
			anodes.shift(1);
		else
			anodes.shift(0);
		if (color[0])
			anodes.shift(1);
		else
			anodes.shift(0);
		
		for (int i = 0; i < before; i++)
			anodes.shift(0);
	}
}
