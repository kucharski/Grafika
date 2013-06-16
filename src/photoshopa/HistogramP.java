package photoshopa;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;


public class HistogramP extends JPanel{
int max=255,R,G,B,C;
int[] r,g,b;
HistogramP(int[] r,int[] g,int[] b){
	
this.r=r;
this.g=g;
this.b=b;
	
	R=0;G=0;B=0;
	for(int i=0;i<256;i++)
	{
		if(r[i]>R) R=r[i];
		if(g[i]>G) G=g[i];
		if(b[i]>B) B=b[i];
	}
	this.setVisible(true);
repaint();
}
 @Override
	public void paintComponent(Graphics g2) {
	
		BufferedImage wykres = new BufferedImage(max, max, BufferedImage.TYPE_INT_RGB);
	 	Graphics2D wykresG = wykres.createGraphics();
	 	wykresG.setColor(Color.black);
	 	for(int i=0;i<max;i++)
	 	{	
	 		int tmp=(int)(((double)r[i]/R)*max)/2;
	 		wykresG.setColor(Color.red);
	 		wykresG.fillRect(i, max-tmp, 1, tmp);
	 	}
	 	
	 	ImageObserver observer = null;
		g2.drawImage(wykres, 0, 0, max, 75, observer);
		
		wykres = new BufferedImage(max, max, BufferedImage.TYPE_INT_RGB);
		wykresG = wykres.createGraphics();
		wykresG.setColor(Color.black);
	 	for(int i=0;i<255;i++)
	 	{	
	 		int tmp=(int)(((double)g[i]/G)*255)/2;
	 		wykresG.setColor(Color.green);
	 		wykresG.fillRect(i, max-tmp, 1, tmp);
	 	}
	        observer = null;
		g2.drawImage(wykres, 0, 95, max, 75, observer);
		
		wykres = new BufferedImage(max, max, BufferedImage.TYPE_INT_RGB);
		wykresG = wykres.createGraphics();
		wykresG.setColor(Color.black);
	 	for(int i=0;i<255;i++)
	 	{	
	 		int tmp=(int)(((double)b[i]/B)*255)/2;
	 		wykresG.setColor(Color.blue);
	 		wykresG.fillRect(i, max-tmp, 1, tmp);
	 	}
	        observer = null;
		g2.drawImage(wykres, 0, 190, max, 75, observer);
				
}
 
}
