package de.ricewaffle.ricematrix.output;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.ricewaffle.ricematrix.container.EmuColor;
import de.ricewaffle.ricematrix.container.LedState;

public class Emulator extends JPanel implements Output
{
	private static final long serialVersionUID = 7864940670197445872L;
	
	int ledsize = 40;
	BufferedImage img;
	
	public Emulator()
	{
		init();
	}
	
	
	private void init()
	{
		img = new BufferedImage(14 * ledsize, 7 * ledsize, BufferedImage.TYPE_INT_RGB);
		ImageIcon icon = new ImageIcon(img);
		add(new JLabel(icon));
		
		JFrame frame = new JFrame("RiceMatrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(14 * ledsize + 10, 7 * ledsize + 30));
        frame.setMinimumSize(new Dimension(14 * ledsize + 10, 7 * ledsize + 30));
        frame.setResizable(false);
        frame.add(this);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
	}
	
	
	@Override
	public void draw(LedState[][] states)
	{
		this.setIgnoreRepaint(true);
		
		for (int y = 0; y < 7; y++)
		{
			for (int x = 0; x < 14; x++)
			{
				int color = 0;
				
				if (states[y][x].r())
					color = EmuColor.red;
				else if (states[y][x].g())
					color = EmuColor.green;
				else if (states[y][x].b())
					color = EmuColor.blue;
				
				int drawX = x * ledsize;
				int drawY = y * ledsize;
				
				screenDraw(drawX, drawY, drawX + ledsize, drawY + ledsize, color);
			}
		}
		
		this.setIgnoreRepaint(false);
		this.repaint();
	}
	
	public void screenDraw(int fromX, int fromY, int toX, int toY, int color)
	{
		for (int x = fromX; x < toX; x++)
		{
			for (int y = fromY; y < toY; y++)
			{
				img.setRGB(x, y, color);
			}
		}
	}
}
