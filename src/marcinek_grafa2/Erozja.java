package marcinek_grafa2;


import java.awt.Color;
import java.awt.image.BufferedImage;



public class Erozja{

	BufferedImage image;

	boolean matrix[][] ={
			{false, false, false, false, false},
			{false, false, true, true, true},
			{false, false, true, true, true},
			{false, false, true, true, true},
			{false, false, false, false, false}
	};

	int [][]kernel2 =
    {
        {1,0,0},
        {1,0,0},
        {0,0,0}
    };


    public Erozja(BufferedImage src1) {
       this.image = src1;
    }


    public BufferedImage run(){

	     int sumx, sumy, sum;
	     BufferedImage tmp = new BufferedImage((int) Math.round(image.getWidth()), (int) Math.round(image.getHeight()), BufferedImage.TYPE_INT_RGB);


	     int kernelLen2 = (int)Math.floor(3.f / 2);

	     for (int i = 0; i < image.getWidth(); i++)
	     {
	         for (int j = 0; j < image.getHeight(); j++)
	         {
	             sumx = 0;
	             sumy = 0;
	             sum = 0;
	             for (int a = -kernelLen2; a <= kernelLen2; a++)
	             {
	                 for (int b = -kernelLen2; b <= kernelLen2; b++)
	                 {
	                     if (i + a < 0 || i + a >= image.getWidth() || j + b < 0 || j + b >= image.getHeight())
	                     {
	                         sum = 255;
	                         continue;
	                     }

	                     if( kernel2[a+kernelLen2][b+kernelLen2] == 1)
	                     {
	                         Color kol=new Color ( image.getRGB(i + a, j + b));
	                         if(sum<kol.getRed()) sum = kol.getRed();
	                     }
	                 }
	             }

	             tmp.setRGB(i, j, new Color(sum,sum,sum).getRGB());
	         }}

	         return tmp;
	    }
	}