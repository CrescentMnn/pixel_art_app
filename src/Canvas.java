package finalProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseMotionListener, MouseListener{
	
	private int x,y;
	protected static BufferedImage img;
	protected static Graphics2D gfx;
	protected static int PIXEL_SIZE = 40;
	Colors colorFunction;
	protected Color paintPlease = new Color(0,0,0);
	//file
	
	
	
	public Canvas() {		
		addMouseMotionListener(this);
		addMouseListener(this);
		setBackground(Color.white);
		img = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
		gfx = img.createGraphics();
		drawBackground(30,Color.white);
		drawSquareGrid(30, Color.black);
		colorFunction = new Colors();	
		
	}
	 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0, null);
	    g.setColor(Color.yellow);
	    g.fillOval(x-5, y-5, 10, 10);
	}

	public void setImage(BufferedImage image) {
		img = image;
	}
	static void drawSquareGrid(int size, Color c) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {                
                gfx.setColor(c);
                gfx.drawRect(i * PIXEL_SIZE, j * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
            }
        }
    }
    private static void drawBackground(int size, Color c) {
    	for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gfx.setColor(c);
                gfx.fillRect(i * PIXEL_SIZE, j * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);                
            }
        }
    }
    
    public void clearCanvas() {
    	setBackground(Color.white);
		img = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
		gfx = img.createGraphics();
		drawBackground(30,Color.white);
		drawSquareGrid(30, Color.black);
    }
    public void openImage(BufferedImage image) {
    	img = image;
    	gfx=image.createGraphics();
		drawSquareGrid(30, Color.black);
    }
    
    public Color getPaint(Color c) {
    	paintPlease = c;
    	return paintPlease;
    }
    
    public BufferedImage getImage() {
    	return img;
    }
    
	
	@Override
	public void mouseDragged(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		while(x%40!=0) {
			/*if(x%40>=20) {
				x++;
			}
			else if(x%40<20) {
				x--;
			}*/
			x--;
		}
		while(y%40!=0) {
			/*if(y%40>=20) {
				y++;
			}
			else if(y%40<20) {
				y--;
			}*/
			y--;
		}
		gfx.setColor(paintPlease);
		gfx.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX(); //get the x and y coordinates of
		y = e.getY(); //the mouse click point
		
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		while(x%40!=0) {
			/*if(x%40>=20) {
				x++;
			}
			else if(x%40<20) {
				x--;
			}*/
			x--;
		}
		while(y%40!=0) {
			/*if(y%40>=20) {
				y++;
			}
			else if(y%40<20) {
				y--;
			}*/
			y--;
		}
		System.out.println(x + " "+ y);
		gfx.setColor(paintPlease);
		gfx.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Graphics getGraphic() {
		return gfx;
	}
	
}