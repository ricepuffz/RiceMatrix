package de.ricewaffle.ricematrix.system;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ProgramLoader
{
	@SuppressWarnings("rawtypes")
	public static List<Program> loadFrom(String path)
	{
		List<Program> programs = new ArrayList<Program>();
		File dir = new File(path);
		File[] files = dir.listFiles();
		
		for (File file : files)
		{
			if (file.getName().endsWith(".jar"))
			{
				String jarPath = file.getPath();
				JarFile jarFile;
				Class extractedClass = null;
				try {
					jarFile = new JarFile(jarPath);
					Enumeration<JarEntry> e = jarFile.entries();
	
					URL[] urls = { new URL("jar:file:" + jarPath + "!/") };
					URLClassLoader cl = URLClassLoader.newInstance(urls);
	
					while (e.hasMoreElements())
					{
					    JarEntry je = e.nextElement();
					    if(je.isDirectory() || !je.getName().endsWith(".class"))
					    		continue;
					    
					    // -6 because of .class
					    String className = je.getName().substring(0,je.getName().length()-6);
					    className = className.replace('/', '.');
					    extractedClass = cl.loadClass(className).asSubclass(Program.class);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				if (extractedClass != null)
					try {
						programs.add((Program) extractedClass.newInstance());
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
			}
		}
		return programs;
	}
}
