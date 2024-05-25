package finalProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Myframe extends JFrame implements ActionListener, ChangeListener {
	
	JFrame frame;
	
	//set size for frame (1200x800) is the size of the projector
	private int height = 1200;
	private int width = 800;
	
	//COLORES
	protected int redValue = 0, greenValue = 0, blueValue = 0;
	
	//labels
	JLabel redValueLabel, greenValueLabel, blueValueLabel;
	
	//export import buttons
	JButton save, open, clear, png, jpg, gif;
	//colored buttons 
	JButton grayButton,blueButton,blackButton,orangeButton,
	purpleButton,pinkButton,currentSliderColor;
	//color sliders
	JSlider redSlider, greenSlider, blueSlider;
	//JPanels 
	JPanel panel1, panel2, subpanelButtons, subpanelSliders,gridPanelButtons, 
	gridPanelColors, gridPanelCurrentColor, currentColorPanel;
	Canvas canvas;
	//Color currentColor = new Color(0,0,0);
	Colors functionsForPaint;
	Color lapiz;
	//setCurrentColor
	protected String filename = "", extension = "";
	
	//private int's need get() functions so no one can alter them
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	
	//The constructor uses a BorderLayout as a manager so i can make it cute
	Myframe(){
		frame = new JFrame();
		frame.setTitle("Pixel art: Final Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(getHeight(),getWidth());
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setVisible(true);
		
		functionsForPaint = new Colors();
		
		insideFunction();
		outsideFunctions();
		
		frame.add(panel1,BorderLayout.WEST);
		frame.add(canvas,BorderLayout.EAST);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
//this is for the buttons sliders and labels
	public void insideFunction() {
		//create the buttons
				save = new JButton();
				save.setBackground(Color.white);
				save.setText("SAVE");
				save.setPreferredSize(new Dimension(193,20));
				save.setFocusable(false);
				save.addActionListener((ActionListener) this);
				
				open = new JButton();
				open.setBackground(Color.white);
				open.setText("OPEN");
				open.setPreferredSize(new Dimension(193,20));
				open.setFocusable(false);
				open.addActionListener(this);
				
				clear = new JButton();
				clear.setBackground(Color.white);
				clear.setText("CLEAR");
				clear.setPreferredSize(new Dimension(193,20));
				clear.setFocusable(false);
				clear.addActionListener(this);
				
				//color buttons
				grayButton = new JButton();
				grayButton.setBackground(new Color(128,128,128));
				grayButton.setPreferredSize(new Dimension(60,60));
				grayButton.setFocusable(false);
				grayButton.addActionListener(this);
				
				blackButton = new JButton();
				blackButton.setBackground(new Color(0,0,0));
				blackButton.setPreferredSize(new Dimension(60,60));
				blackButton.setFocusable(false);
				blackButton.addActionListener(this);
				
				blueButton = new JButton();
				blueButton.setBackground(new Color(153,204,255));
				blueButton.setPreferredSize(new Dimension(60,60));
				blueButton.setFocusable(false);
				blueButton.addActionListener(this);
				
				orangeButton = new JButton();
				orangeButton.setBackground(new Color(255,178,102));
				orangeButton.setPreferredSize(new Dimension(60,60));
				orangeButton.setFocusable(false);
				orangeButton.addActionListener(this);
				
				purpleButton= new JButton();
				purpleButton.setBackground(new Color(255, 0, 255));
				purpleButton.setPreferredSize(new Dimension(60,60));
				purpleButton.setFocusable(false);
				purpleButton.addActionListener(this);
				
				pinkButton = new JButton();
				pinkButton.setBackground(new Color(255,153,255));
				pinkButton.setPreferredSize(new Dimension(60,60));
				pinkButton.setFocusable(false);
				pinkButton.addActionListener(this);
				
				//sliders
				redSlider = new JSlider(0,255,redValue);
				greenSlider = new JSlider(0,255,greenValue);
				blueSlider = new JSlider(0,255,blueValue);
				
				
				redSlider.setPreferredSize(new Dimension(200,100));
				redSlider.setPaintTicks(true);
				redSlider.setPaintTrack(true);
				redSlider.setMajorTickSpacing(51);
				redSlider.setOpaque(false);
				redSlider.setPaintLabels(true);
				redSlider.setOrientation(SwingConstants.HORIZONTAL);
				redSlider.addChangeListener(this);
				//label for value
				redValueLabel = new JLabel();
				
				greenSlider.setPreferredSize(new Dimension(200,100));
				greenSlider.setPaintTicks(true);
				greenSlider.setPaintTrack(true);
				greenSlider.setMajorTickSpacing(51);
				greenSlider.setOpaque(false);
				greenSlider.setPaintLabels(true);
				greenSlider.setOrientation(SwingConstants.HORIZONTAL);
				greenSlider.addChangeListener(this);
				//label for value
				greenValueLabel = new JLabel();
				
				blueSlider.setPreferredSize(new Dimension(200,100));
				blueSlider.setPaintTicks(true);
				blueSlider.setPaintTrack(true);
				blueSlider.setMajorTickSpacing(51);
				blueSlider.setOpaque(false);
				blueSlider.setPaintLabels(true);
				blueSlider.setOrientation(SwingConstants.HORIZONTAL);
				blueSlider.addChangeListener(this);
				//label for value
				blueValueLabel = new JLabel();
	}
	
	/*public void saveImage() {
		switch(extension) {
		case "jpg":
			try {
				ImageIO.write(null, extension, null);
			}catch(IOException ex) {
				System.err.println("Problem occurred...");
			}
			break;
			
		case "png": 
			
			break;
			
		case "gif": 
			
			break;
		}
	}*/

//this creates the panels 
	public void outsideFunctions() {
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		
		panel1.setBackground(Color.black);
		panel2.setBackground(Color.gray);
		panel1.setPreferredSize(new Dimension(200,800));
		panel2.setPreferredSize(new Dimension(1000,800));
		
		//subpanels
		subpanelButtons = new JPanel();
		subpanelSliders = new JPanel();
		
		subpanelButtons.setLayout(new FlowLayout(3,3,3));
		
		currentColorPanel = new JPanel();
		
		//grid panels
		gridPanelButtons = new JPanel();
		gridPanelButtons.setLayout(new GridLayout(3,1,2,2));
		gridPanelButtons.setBackground(Color.white);
		gridPanelColors = new JPanel();
		gridPanelColors.setLayout(new GridLayout(3,3,2,2));
		gridPanelColors.setBackground(Color.WHITE);
		gridPanelCurrentColor = new JPanel();
		gridPanelCurrentColor.setLayout(new FlowLayout(1,1,1));
		gridPanelCurrentColor.setBackground(Color.white);
		
		//subpanel1
		subpanelButtons.setBackground(new Color(128,128,128));
		subpanelButtons.setPreferredSize(new Dimension(200,400));
		
		currentColorPanel.setBackground(new Color(128,128,128));
		currentColorPanel.setPreferredSize(new Dimension(60,180));
		
		//canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(1000,800));
		canvas.setBackground(Color.black);
		
		//Subpanel2
		subpanelSliders.setBackground(new Color(128,128,128));
		subpanelSliders.setPreferredSize(new Dimension(200,400));
		
		panel1.setLayout(new BorderLayout());
		
		
		panel1.add(subpanelButtons, BorderLayout.NORTH);
		panel1.add(subpanelSliders, BorderLayout.SOUTH);
		panel2.add(canvas);
		
		
		//adding the buttons to each layout
		gridPanelButtons.add(save);
		gridPanelButtons.add(open);
		gridPanelButtons.add(clear);
		
		gridPanelColors.add(grayButton);
		gridPanelColors.add(blackButton);
		gridPanelColors.add(pinkButton);
		gridPanelColors.add(blueButton);
		gridPanelColors.add(purpleButton);
		gridPanelColors.add(orangeButton);
		
		subpanelButtons.add(gridPanelButtons);
		subpanelButtons.add(gridPanelColors);
		subpanelButtons.add(currentColorPanel);
		
		
		subpanelSliders.add(redSlider);
		subpanelSliders.add(redValueLabel);
		subpanelSliders.add(greenSlider);
		subpanelSliders.add(greenValueLabel);
		subpanelSliders.add(blueSlider);
		subpanelSliders.add(blueValueLabel);
	}
	
	public JPanel setCurrentPanel() {
		currentColorPanel.setBackground(functionsForPaint.getCurrColor());
		lapiz = currentColorPanel.getBackground();
		return currentColorPanel;
	}
	
	public void saveFile(String filename, String type) {
		try {
			ImageIO.write(canvas.getImage(), type, new File(filename+"."+type));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFile(BufferedImage image, String filename) {
		try {
			File openFile = new File(filename);
			image = new BufferedImage(1200,800,BufferedImage.TYPE_3BYTE_BGR);
			image = ImageIO.read(openFile);
			canvas.openImage(image);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==save) {
			System.out.println("Please kill yourself");		
		    JDialog dialog = new JDialog(frame, "Chose an option...");
		    //png, jpg, gif
		    JPanel panelForSaving = new JPanel();
		    jpg = new JButton("JPG");
		    png = new JButton("PNG");
		    gif = new JButton("GIF");

		    JTextField fileNameReciever = new JTextField(10);
		    panelForSaving.setLayout(new FlowLayout(1, 3, 1));
		    panelForSaving.add(fileNameReciever);
		    panelForSaving.add(jpg);
		    panelForSaving.add(png);
		    panelForSaving.add(gif);
		    
		    dialog.add(panelForSaving);
		    
			jpg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filename = fileNameReciever.getText();
					extension = "jpg";
					saveFile(filename, extension);
					dialog.dispose();
				}
				
			});
			png.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filename = fileNameReciever.getText();
					extension = "png";
					saveFile(filename, extension);
					dialog.dispose();
				}
				
			});
			gif.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filename = fileNameReciever.getText();
					extension = "gif";
					saveFile(filename, extension);
					dialog.dispose();
				}
				
			});
			
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.pack();
			dialog.setLocationRelativeTo(frame);
	        dialog.setVisible(true);
			
			/*JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setDialogTitle("Save file");*/
			//only jpeg or gif or png files
			//FileNameExtensionFilter restrictPNG = new FileNameExtensionFilter("Save png", "png");
			//FileNameExtensionFilter restrictGIF = new FileNameExtensionFilter("Save gif", "gif");
			//fileChooser.showSaveDialog(null);//select file to save
		}
		else if(e.getSource()==open) {
			System.out.println("Or dont");
			JFileChooser fileChooser= new JFileChooser();
			//fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setDialogTitle("open file");
			//only jpeg or gif or png files
			FileNameExtensionFilter restrictIMAGE = new FileNameExtensionFilter("Open image", "jpg", "png","gif");
			int response = fileChooser.showOpenDialog(null); //select file to open
			if(response == fileChooser.APPROVE_OPTION) {
				readFile(canvas.getImage(), fileChooser.getSelectedFile().getAbsolutePath());
			}
			
		}
		else if(e.getSource()==clear) {
			System.out.println("Please finish this quick");
			canvas.clearCanvas();
		}
		
		if(e.getSource()==grayButton) {
			redValue = 128;
			greenValue = 128;
			blueValue = 128;
			System.out.println(redValue + " "+ greenValue+" "+blueValue+"\n");
			//Color changedColors = new Color(redValue, greenValue, blueValue);
			functionsForPaint.setCurrentColor(redValue, greenValue, blueValue);
			Color changedColors = new Color(redValue, greenValue, blueValue);
			canvas.getPaint(changedColors);
			setCurrentPanel();
		}
		if(e.getSource()==blackButton) {
			//0,204,0
			redValue = 0;
			greenValue = 0;
			blueValue = 0;
			System.out.println(redValue + " "+ greenValue+" "+blueValue+"\n");
			Color changedColors = new Color(redValue, greenValue, blueValue);
			canvas.getPaint(changedColors);
			functionsForPaint.setCurrentColor(redValue, greenValue, blueValue);
			setCurrentPanel();
		}
		if(e.getSource()==blueButton) {
			//153,204,255
			redValue = 153;
			greenValue = 204;
			blueValue = 255;
			System.out.println(redValue + " "+ greenValue+" "+blueValue+"\n");
			Color changedColors = new Color(redValue, greenValue, blueValue);
			canvas.getPaint(changedColors);
			functionsForPaint.setCurrentColor(redValue, greenValue, blueValue);
			setCurrentPanel();
		}
		if(e.getSource()==orangeButton) {
			//255,178,102
			redValue = 255;
			greenValue = 178;
			blueValue = 102;
			System.out.println(redValue + " "+ greenValue+" "+blueValue+"\n");
			Color changedColors = new Color(redValue, greenValue, blueValue);
			canvas.getPaint(changedColors);
			functionsForPaint.setCurrentColor(redValue, greenValue, blueValue);
			setCurrentPanel();
		}
		if(e.getSource()==purpleButton) {
			//255, 0, 255
			redValue = 255;
			greenValue = 0;
			blueValue = 255;
			System.out.println(redValue + " "+ greenValue+" "+blueValue+"\n");
			Color changedColors = new Color(redValue, greenValue, blueValue);
			canvas.getPaint(changedColors);
			functionsForPaint.setCurrentColor(redValue, greenValue, blueValue);
			setCurrentPanel();
		}
		if(e.getSource()==pinkButton) {
			//(255,153,255)
			redValue = 255;
			greenValue = 153;
			blueValue = 255;
			System.out.println(redValue + " "+ greenValue+" "+blueValue+"\n");
			Color changedColors = new Color(redValue, greenValue, blueValue);
			canvas.getPaint(changedColors);
			functionsForPaint.setCurrentColor(redValue, greenValue, blueValue);
			setCurrentPanel();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		redValueLabel.setText("R: "+redSlider.getValue());
		redValue=redSlider.getValue();
		greenValueLabel.setText("G: "+greenSlider.getValue());
		greenValue=greenSlider.getValue();
		blueValueLabel.setText("B: "+blueSlider.getValue());
		blueValue=blueSlider.getValue();
		System.out.println(redValue + " " + greenValue + " " + blueValue);
		Color changedColors = new Color(redValue, greenValue, blueValue);
		canvas.getPaint(changedColors);
		functionsForPaint.setCurrentColor(redValue, greenValue, blueValue);
		System.out.println(functionsForPaint.getCurrColor());
		setCurrentPanel();
	}
}