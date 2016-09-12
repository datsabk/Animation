package shape;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


final public class ShapeAnimation {

	JFrame frame;
	DrawPanel drawPanel;

	private int xPos = 530;
	private int xPos2 = 530;
	private int xPos3=0;
	private int multi = 1;
	private int yPos = 480;
	private int yPos2=100;
	boolean up = false;
	boolean down = true;
	boolean left = false;
	boolean right = true;
	int i=1;
	int j=1;
	
	//Main function. Contains just constructor call to start the program
	public static void main(String[] args) {
		new ShapeAnimation().go();
	}
	//Function to create the main JFrame to display animation
	private void go() {
		frame = new JFrame("Shape Animation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();

		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(600, 600);
		frame.setLocation(100, 100);
		throwBall();
	}

	class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			try {
				//Read various images to be displayed
				Image image = ImageIO.read(new File("frame_"+i+"_delay-0.08s.gif"));	//Names selected logically to show walk movement
				Image image2 = ImageIO.read(new File("frame_"+j+"_delay-0.01s.gif"));
				//increment to keep the character moving
				i++;
				j++;	
				if (i==12)
					i=1;
				if(j==29)
					j=1;
				g.drawImage(ImageIO.read(new File("bg.png")), 0, 0, 600, 600,null);	//Add background image to the frame
				g.drawImage(image,xPos,440,multi*100,140,null);
				g.drawImage(image2,xPos3,440,multi*100,140,null);
				g.setColor(Color.ORANGE);						//Draw sun of diameter 50
				g.fillOval(xPos2, yPos2, 50, 50);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void throwBall() {
		while(true){
			
//Reverse the character direction when they meet each other
			if(xPos3>xPos-50)
			{
				multi*= (-1);
				xPos+=100;
				xPos3+=100;
			}
			//Reverse again once they reach their endpoints
			if(xPos>530 && xPos3<0)
			{
				multi*= (-1);
			}
			if(multi==1)
			{   
				xPos-=5;
				xPos3+=3;
			}
			else
			{
				xPos+=5;
				xPos3-=3;
			}

			xPos2-=2;

			if(up)
				yPos2+=1;
			else
				yPos2-=1;
			//Move the sun upward and then again downward
			if(yPos>=150 && up==true)
			{
				up=false;
				down=true;
			}
			else
			{
				down=false;
				up=true;
			}
			try{
				Thread.sleep(100);
			} catch (Exception exc){}
			frame.repaint();
		}
	}
}