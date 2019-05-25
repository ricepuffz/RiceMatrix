package de.ricewaffle.ricematrix.container;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Element
{
	LedScreen content;
	
	public Element(String path)
	{
		content = readElement(path);
	}
	
	public LedScreen content()
	{
		return content;
	}
	
	
	public static LedScreen readElement(String path)
	{
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
					temp.setState(x, y, true, false, false);
					temp.setEnabled(x, y, true);
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
}
