package de.ricewaffle.ricematrix.container;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.ricewaffle.ricematrix.References;

public class Element
{
	LedScreen content;
	
	public Element(String path, boolean[] color)
	{
		content = readElement(path, color);
	}
	
	public Element(LedScreen content)
	{
		this.content = new LedScreen(content.width, content.height);
		
		for (int x = 0; x < content.width; x++)
		{
			for (int y = 0; y < content.height; y++)
			{
				boolean[] color = content.getState(x, y);
				this.content.setState(x, y, color[0], color[1], color[2]);
			}
		}
	}
	
	public LedScreen content()
	{
		return content;
	}
	
	
	public Element tint(boolean[] color)
	{
		Element tintedElement = new Element(content);
		LedScreen tintedElementContent = tintedElement.content();
		
		for (int x = 0; x < content.width; x++)
		{
			for (int y = 0; y < content.height; y++)
			{
				boolean[] state = tintedElementContent.getState(x, y);
				if (state[0] || state[1] || state[2])
				{
					tintedElementContent.setState(x, y, color[0], color[1], color[2]);
				} else {
					tintedElementContent.setEnabled(x, y, false);
				}
			}
		}
		
		return tintedElement;
	}
	

	public static LedScreen readElement(String path, boolean[] color)
	{
		path = References.path + "elements/" + path;
		LedScreen temp = null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			
			String[] dimensions = line.split(",");
			int width = Integer.parseInt(dimensions[0]);
			int height = Integer.parseInt(dimensions[1]);
			temp = new LedScreen(width, height);
			temp.disableAll();
			
			line = reader.readLine();
			while (line != null)
			{
				String[] parts = line.split(":");
				int y = Integer.parseInt(parts[0]);
				for (String xString : parts[1].split(","))
				{
					int x = Integer.parseInt(xString);
					temp.setState(x, y, color[0], color[1], color[2]);
					temp.setEnabled(x, y, true);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
}
