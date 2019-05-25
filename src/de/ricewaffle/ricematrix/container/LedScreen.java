package de.ricewaffle.ricematrix.container;

public class LedScreen
{
	private LedState[][] states;
	int width, height;
	
	public LedScreen(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		states = new LedState[height][width];
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
				states[y][x] = new LedState();
		}
	}
	
	public void disableAll()
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
				setEnabled(x, y, false);
		}
	}
	
	public LedState[][] stateArray()
	{
		return states;
	}
	
	public void setState(int x, int y, boolean r, boolean g, boolean b)
	{
		states[y][x].setState(r, g, b);
	}
	
	public boolean[] getState(int x, int y)
	{
		return states[y][x].getState();
	}
	
	public void setEnabled(int x, int y, boolean enabled)
	{
		states[y][x].setEnabled(enabled);
	}
	
	public boolean getEnabled(int x, int y)
	{
		return states[y][x].enabled();
	}
	
	
	public void apply(LedScreen screen)
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				boolean[] states = screen.getState(x, y);
				setState(x, y, states[0], states[1], states[2]);
			}
		}
	}
	
	
	public void drawBox(int x1, int y1, int x2, int y2, boolean r, boolean g, boolean b)
	{
		if (x1 > x2)
		{
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if (y1 > y2)
		{
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		
		for (int y = y1; y <= y2; y++)
		{
			for (int x = x1; x <= x2; x++)
				states[y][x].setState(r, g, b);
		}
	}
	
	public void drawLedScreen(LedScreen screen, int xOffset, int yOffset, boolean includeDisabled)
	{
		for (int y = 0; y < screen.height; y++)
		{
			for (int x = 0; x < screen.width; x++)
			{
				boolean[] states = screen.getState(x, y);
				if (screen.getEnabled(x, y) || includeDisabled)
				{
					this.states[y + yOffset][x + xOffset].setState(states[0], states[1], states[2]);
				}
			}
		}
	}
	
	
	public int width()
	{
		return width;
	}
	
	public int height()
	{
		return height;
	}
}
