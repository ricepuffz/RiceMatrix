package de.ricewaffle.ricematrix.io.renderer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class ShiftRegister
{
	private final GpioController gpio = GpioFactory.getInstance();
	private final GpioPinDigitalOutput data;
	private final GpioPinDigitalOutput latch;
	private final GpioPinDigitalOutput clock;
	
	public ShiftRegister(int data, int latch, int clock)
	{
		this.data = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(data));
		this.latch = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(latch));
		this.clock = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(clock));
	}
	
	
	public void shift(int value)
	{
		if (value == 0 || value == 1)
		{
			data.setState(PinState.getState(value));
			clock.setState(PinState.HIGH);
			clock.setState(PinState.LOW);
		}
	}
	
	public void show()
	{
		latch.setState(PinState.HIGH);
		latch.setState(PinState.LOW);
	}
}
