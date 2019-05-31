package de.ricewaffle.ricematrix.io.emulator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.ricewaffle.ricematrix.system.KeyStates;

public class EmulatorKeyListener implements KeyListener
{
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case 38:
				KeyStates.setUp(true);
				break;
			case 40:
				KeyStates.setDown(true);
				break;
			case 37:
				KeyStates.setLeft(true);
				break;
			case 39:
				KeyStates.setRight(true);
				break;
			case 87:
				KeyStates.setA(true);
				break;
			case 68:
				KeyStates.setB(true);
				break;
			case 81:
				KeyStates.setC(true);
				break;
			case 83:
				KeyStates.setD(true);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case 38:
				KeyStates.setUp(false);
				break;
			case 40:
				KeyStates.setDown(false);
				break;
			case 37:
				KeyStates.setLeft(false);
				break;
			case 39:
				KeyStates.setRight(false);
				break;
			case 87:
				KeyStates.setA(false);
				break;
			case 68:
				KeyStates.setB(false);
				break;
			case 81:
				KeyStates.setC(false);
				break;
			case 83:
				KeyStates.setD(false);
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

}
