package projekt2;

import java.awt.Color;
import java.awt.image.BufferedImage;
/**
 *
 * @author RoBerT
 */
public class Dylatacja {
	private static int[][] element = {
			{ 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1},
                        { 1, 1, 1, 1, 1, 1},
                        { 1, 1, 1, 1, 1, 1},
                        { 1, 1, 1, 1, 1, 1},
                        { 1, 1, 1, 1, 1, 1},
			};
    private static Color kolor, kolor2;
    private static int k1,k2;
	private static int width=6,height=6;
	private static void jazda(int x, int y, BufferedImage obraz, BufferedImage nowy)
	{
        kolor = new Color( obraz.getRGB(x, y) );
		k1 = kolor.getRed();
        if(k1==0)
        {
			for(int dx = -width/2,ex=0; dx <= width/2; dx++,ex++ )
            {
				for(int dy = -height/2,ey=0; dy <= height/2; dy++,ey++ )
                {
                     int ii=x+dx; if(ii<0 || ii>=obraz.getWidth()) ii=0;
                     int jj=y+dy; if(jj<0 || jj>=obraz.getHeight()) jj=0;
                    kolor2 = new Color( obraz.getRGB(ii, jj) );
                    k2 = kolor2.getRed();
                    int ee =ex; if(ex>=width ||ex<0) ee=0;
                    int yy = ey; if(ey>=height ||ey<0) yy=0;
					if(element[ee][yy] == 1 && k2 == 0)
						nowy.setRGB(ii, jj, new Color(255,255,255).getRGB());
                    else
						nowy.setRGB(ii, jj, new Color(k2,k2,k2).getRGB());
				}
            }
        }
		else
			nowy.setRGB(x, y, new Color(k1,k1,k1).getRGB());
	}

	public static BufferedImage dylatacja(BufferedImage obraz) {

	 BufferedImage nowy= new BufferedImage(obraz.getWidth(), obraz.getHeight(), BufferedImage.TYPE_INT_RGB);
     panelu.skala_szarosci(1);

		for(int x=0; x < obraz.getWidth(); x++)
			for(int y =0; y < obraz.getHeight(); y++){
				jazda(x,y,obraz,nowy);
			}
     panelu.set_panel(nowy, panelu.red, panelu.green, panelu.blue);
		return nowy;
	}
}

