package finalProject;

import java.awt.Color;

public class Colors {
	Color currentColor;
	private int redValue = 0, greenValue = 0, blueValue = 0;
	
	
	public void setCurrentColor(int x, int y, int z) {
		Color burner = new Color(x,y,z);
		redValue = x;
		greenValue = y;
		blueValue = z;
		currentColor = new Color(redValue, greenValue, blueValue);
		System.out.println(redValue + " "+ greenValue + " " + blueValue);
		System.out.println(currentColor);
	}
	
	public Color getCurrColor() {
		return currentColor;
	}
	
}
