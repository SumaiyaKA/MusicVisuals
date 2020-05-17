package ie.tudublin;

import d17126680.CubeVisual;
import d17126680.MyVisual;
import d17126680.Stars;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Stars());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}