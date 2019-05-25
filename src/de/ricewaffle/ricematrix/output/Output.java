package de.ricewaffle.ricematrix.output;

import de.ricewaffle.ricematrix.container.LedState;

public interface Output
{
	public void draw(LedState[][] states);
}
