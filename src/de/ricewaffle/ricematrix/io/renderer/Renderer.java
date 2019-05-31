package de.ricewaffle.ricematrix.io.renderer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;

import de.ricewaffle.ricematrix.container.LedState;
import de.ricewaffle.ricematrix.io.Medium;
import de.ricewaffle.ricematrix.system.KeyStates;

public class Renderer implements Medium
{
	private final GpioController gpio = GpioFactory.getInstance();
	
	private final ShiftRegister cathodes;
	private final ShiftRegister anodes;
	
	private final GpioPinDigitalInput up = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(15));
	private final GpioPinDigitalInput down = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(16));
	private final GpioPinDigitalInput left = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(1));
	private final GpioPinDigitalInput right = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(4));
	
	private final GpioPinDigitalInput a = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(6));
	private final GpioPinDigitalInput b = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(10));
	private final GpioPinDigitalInput c = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(11));
	private final GpioPinDigitalInput d = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(31));
	
	public Renderer()
	{
		cathodes = new ShiftRegister(21, 22, 23);
		anodes = new ShiftRegister(27, 28, 29);
	}
	
	
	@Override
	public void draw(LedState[][] states)
	{
		updateInput();
		
		for (int y = 0; y < 7; y++)
		{
			for (int x = 0; x < 14; x++)
			{
				stateLed(x, y, states[6 - y][x].getState());
			}
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
		if (color[0])
			anodes.shift(1);
		else
			anodes.shift(0);
		if (color[1])
			anodes.shift(1);
		else
			anodes.shift(0);
		
		for (int i = 0; i < before; i++)
			anodes.shift(0);
	}
	
	
	private void updateInput()
	{
		if (up.isHigh())
			KeyStates.setUp(true);
		else
			KeyStates.setUp(false);
		if (down.isHigh())
			KeyStates.setDown(true);
		else
			KeyStates.setDown(false);
		if (left.isHigh())
			KeyStates.setLeft(true);
		else
			KeyStates.setLeft(false);
		if (right.isHigh())
			KeyStates.setRight(true);
		else
			KeyStates.setRight(false);
		
		if (a.isHigh())
			KeyStates.setA(true);
		else
			KeyStates.setA(false);
		if (b.isHigh())
			KeyStates.setB(true);
		else
			KeyStates.setB(false);
		if (c.isHigh())
			KeyStates.setC(true);
		else
			KeyStates.setC(false);
		if (d.isHigh())
			KeyStates.setD(true);
		else
			KeyStates.setD(false);
	}
}
