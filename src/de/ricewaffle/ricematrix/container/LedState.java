package de.ricewaffle.ricematrix.container;

public class LedState 
{
	boolean r, g, b;
	boolean enabled;
	
	public LedState()
	{
		r = false;
		g = false;
		b = false;
		enabled = true;
	}
	
	
	public void setState(boolean r, boolean g, boolean b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public boolean[] getState()
	{
		return new boolean[] { r, g, b };
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public boolean r() { return r; }
	public boolean g() { return g; }
	public boolean b() { return b; }
	public boolean enabled() { return enabled; }
}
