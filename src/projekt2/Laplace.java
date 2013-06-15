package projekt2;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;


public class Laplace {

	BufferedImage src;
	private static final float[] laplace = {
                  0.f,	-1.f,	0.f,
		 -1.f,	 4.f,	-1.f,
		 0.f,	-1.f,	0.f
	};

	public Laplace(BufferedImage src){
		this.src = src;
	}

	public BufferedImage run(){

		BufferedImage tmp = new BufferedImage(src.getWidth(),src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, laplace),ConvolveOp.EDGE_NO_OP, null);

        return cop.filter(src, null);

	}

}
