package projekt2;


import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Histogramy extends JFrame {

    private Histogram r, g, b, a;

    public Histogramy(BufferedImage img, int h) {
        initComponents();
        	this.setBounds(0, 0, 600, 400);
        	this.r = new Histogram(img, Histogram.RED_HISTOGRAM, 200, Color.RED, Color.WHITE);
        	this.r.setLocation(0, 0);
        	this.add(this.r);
        	this.repaint();

        	this.g = new Histogram(img, Histogram.BLUE_HISTOGRAM, 200, Color.BLUE, Color.WHITE);
        	this.g.setLocation(0, 200);
        	this.add(this.g);
        	this.repaint();

        	this.b = new Histogram(img, Histogram.GREEN_HISTOGRAM, 200, Color.GREEN, Color.WHITE);
	        this.b.setLocation(300, 0);
	        this.add(this.b);
	        this.repaint();

	    	this.a = new Histogram(img, Histogram.AVG_HISTOGRAM, 200, Color.BLACK, Color.WHITE);
	    	this.a.setLocation(300, 200);
	    	this.add(this.a);
	    	this.repaint();

        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")                    
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }                 
}
