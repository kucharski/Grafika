
package grafikaprojekt2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.Raster;
import java.awt.image.RescaleOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MojPanel extends javax.swing.JPanel {

    File plik, plik2;
    BufferedImage obrazek, obrazek2;
    public int progress = 0;
    public boolean cancelFlag = false;
    int typ, typ2;
    int radius;
    int threshold = 0;
    public int [][] histogram;
    public Arrays Arrays;
    public static int []red;
    public static int []green;
    public static int []blue;
    private static int r,g,b;
    BufferedImage skladak;
    BufferedImage ten_namalowany;
    /** Creates new form MojPanel */
    public MojPanel() {
        obrazek = null;
        plik = null;
        obrazek2 = null;
        plik2 = null;
        setSize(600, 600);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics g)
    {
        if (obrazek != null) {
            g.drawImage(obrazek, 0, 0, obrazek.getWidth(), obrazek.getHeight() , null);
        }
    }

    public boolean WyborObrazka() throws IOException
    {
        boolean zmiana = false;
        jFileChooser1 = new JFileChooser();
        FileNameExtensionFilter filtr = new FileNameExtensionFilter("Pliki *.jpg *.png","jpg", "jpeg", "png");
        jFileChooser1.setFileFilter(filtr);
        int wynik = jFileChooser1.showOpenDialog(this);
        if (wynik == JFileChooser.APPROVE_OPTION) {
            plik = jFileChooser1.getSelectedFile();
            obrazek = ImageIO.read(plik);
            typ = obrazek.getType();
            if (plik != null) {
                this.setSize(obrazek.getWidth(), obrazek.getHeight());
                zmiana = true;
            }
        }
        repaint();
        return zmiana;
    }
    
    public boolean WyborObrazka2() throws IOException
    {
        jFileChooser1 = new JFileChooser();
        FileNameExtensionFilter filtr = new FileNameExtensionFilter("Pliki *.jpg *.png","jpg", "jpeg", "png");
        jFileChooser1.setFileFilter(filtr);
        int wynik = jFileChooser1.showOpenDialog(this);
        plik2 = jFileChooser1.getSelectedFile();
        obrazek2 = ImageIO.read(plik2);
        return false;
    }

    public void WczytajPonownie() throws IOException
    {
        obrazek = ImageIO.read(plik);
        repaint();

    }

    public void Zapis() throws IOException
    {
        jFileChooser1 = new JFileChooser();
        FileNameExtensionFilter filtr = new FileNameExtensionFilter("Pliki *.jpg *.png","jpg", "jpeg", "png");
        jFileChooser1.setFileFilter(filtr);
        jFileChooser1.showSaveDialog(this);
        String sciezka = jFileChooser1.getSelectedFile().toString();
        if (sciezka.contains(".jpg") || sciezka.contains(".jpeg"))
            ImageIO.write(obrazek, "jpg", new File(sciezka));
        else if (sciezka.contains(".png"))
            ImageIO.write(obrazek, "png", new File(sciezka));
        else {
            switch (typ)
            {
                case 5: sciezka += ".jpg";
                        ImageIO.write(obrazek, "jpg", new File(sciezka));
                        break;
                case 6: sciezka += ".png";
                        ImageIO.write(obrazek, "png", new File(sciezka));
                        break;
            }
        }
    }

    private void Filtr(BufferedImageOp op)
    {
        BufferedImage obrazekFiltr = new BufferedImage(obrazek.getWidth(), obrazek.getHeight(), obrazek.getType());
        op.filter(obrazek, obrazekFiltr);
        obrazek = obrazekFiltr;
        repaint();
    }
    
    public void dylacja(int radius)
        {   
                int width = obrazek.getWidth();
                int height = obrazek.getHeight();
                this.radius=radius;
                BufferedImage obrazekDylacja = new BufferedImage(obrazek.getWidth(), obrazek.getHeight(), obrazek.getType());
                
                for (int row = 0; row < height && cancelFlag == false; row++) {
                        progress = 100 * row / height;
                        for (int col = 0; col < width && cancelFlag == false; col++) {
                                obrazekDylacja.setRGB(col, row, maxAround(obrazek, row, col));
                        }
                }
                obrazek=obrazekDylacja;

        }
    public void erozja(int radius) {
                
                this.radius=radius;
                int width = obrazek.getWidth();
                int height = obrazek.getHeight();
                
                BufferedImage obrazekErozja = new BufferedImage(obrazek.getWidth(), obrazek.getHeight(), obrazek.getType());
                
                for (int row = 0; row < height; row++) {
                        progress = 100 * row / height;
                        for (int col = 0; col < width && cancelFlag == false; col++) {
                                obrazekErozja.setRGB(col, row, minAround(obrazek, row, col));
                        }
                }
              obrazek=obrazekErozja;      
               
        }
    
    
    private void Convolve(float[] tabK)
    {
        Kernel kernel = new Kernel(3, 3, tabK);
        ConvolveOp op = new ConvolveOp(kernel);
        Filtr(op);
    }

    public void Negatyw()
    {
        byte tabN[] = new byte[256];
        for (int i = 0; i < 256; i++)
                tabN[i] = (byte) (255 - i);
        ByteLookupTable tabL = new ByteLookupTable(0, tabN);
        LookupOp op = new LookupOp(tabL, null);
        Filtr(op);
    }

    public void Rozjasnienie()
    {
        float a = 1.5f;
        float b = -20.0f;
        RescaleOp op = new RescaleOp(a, b, null);
        Filtr(op);
    }

    public void Przyciemnienie()
    {
        float a = 1.0f;
        float b = -20.0f;
        RescaleOp op = new RescaleOp(a, b, null);
        Filtr(op);
    }

    public void SkalaSzarosci()
    {
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        Filtr(op);
    }

    public void Rozmycie()
    {
        float[] tab = {0.11111111f, 0.11111111f, 0.111111111f, 0.11111111f, 0.11111111f, 0.11111111f, 0.11111111f, 0.11111111f, 0.11111111f};
        Convolve(tab);
    }

    public void Wytlaczanie()
    {
        float[] tab = {-2.0f, -1.0f, 0.0f, -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 2.0f};
        Convolve(tab);
    }

    public void Wyostrzanie()
    {
        float[] tab = {-1.0f, -1.0f, -1.0f, -1.0f, 9.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        Convolve(tab);
    }

    public void KrawedzPoziome()
    {
        float[] tab = {-1.0f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, -1.0f, -1.0f, -1.0f};
        Convolve(tab);
    }

    public void KrawedzPionowe()
    {
        float[] tab = {-1.0f, 2.0f, -1.0f, -1.0f, 2.0f, -1.0f, -1.0f, 2.0f, -1.0f};
        Convolve(tab);
    }

    public void KrawedzLewaPrzekatna()
    {
        float[] tab = {2.0f, -1.0f, -1.0f, -1.0f, 2.0f, -1.0f, -1.0f, -1.0f, 2.0f};
        Convolve(tab);
    }

    public void KrawedzPrawaPrzekatna()
    {
        float[] tab = {-1.0f, -1.0f, 2.0f, -1.0f, 2.0f, -1.0f, 2.0f, -1.0f, -1.0f};
        Convolve(tab);
    }

    public void KrawedzWlasne()
    {
        float[] tab = {-1.0f, -1.0f, -1.0f, -1.0f, 8.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        Convolve(tab);
    }

    public void SobelPoziomy()
    {
        float[] tab = {-1.0f, -2.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f};
        Convolve(tab);
    }

    public void SobelPionowy()
    {
        float[] tab = {-1.0f, 0.0f, 1.0f, -2.0f, 0.0f, 2.0f, -1.0f, 0.0f, 1.0f};
        Convolve(tab);
    }

    public void Laplacian()
    {
        float[] tab = {-1.0f, -1.0f, -1.0f, -1.0f, 9.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        Convolve(tab);
    }
    
    private int maxAround(BufferedImage image, int row, int col) 
        {
                int maxR = 0;
                int maxG = 0;
                int maxB = 0;
                int radius2 = radius * radius;
                for (int dRow = -radius; dRow <= radius; dRow++) {
                        for (int dCol = -radius; dCol <= radius; dCol++) {
                                if (dRow * dRow + dCol * dCol <= radius2) {
                                        int c = ImagingUtility.getRGBExtended(image, row + dRow, col + dCol);
                                        maxR = Math.max(maxR, (c >> 16) & 0xFF);
                                        maxG = Math.max(maxG, (c >> 8) & 0xFF);
                                        maxB = Math.max(maxB, c & 0xFF);
                                }
                        }
                }
                return (maxR << 16) | (maxG << 8) | maxB;
        }
    
    private int minAround(BufferedImage image, int row, int col) {
                int minR = 255;
                int minG = 255;
                int minB = 255;
                int radius2 = radius * radius;
                for (int dRow = -radius; dRow <= radius; dRow++) {
                        for (int dCol = -radius; dCol <= radius; dCol++) {
                                if (dRow * dRow + dCol * dCol <= radius2) {
                                        int c = ImagingUtility.getRGBExtended(image, row + dRow, col + dCol);
                                        minR = Math.min(minR, (c >> 16) & 0xFF);
                                        minG = Math.min(minG, (c >> 8) & 0xFF);
                                        minB = Math.min(minB, c & 0xFF);
                                }
                        }
                }
                return (minR << 16) | (minG << 8) | minB;
        }
    public void odcieniePikseli(BufferedImage image)
          {
            histogram=new int[3][256];
            for (int i = 0; i < image.getHeight(); i++) 
            {
                for (int j = 0; j < image.getWidth(); j++) 
                {  
      		   Color kolor=new Color(image.getRGB(j,i));
      		   int r = kolor.getRed();
      		   int g = kolor.getGreen();
      		   int b = kolor.getBlue();
      		            
      		   histogram[0][r]++;
      		   histogram[1][g]++;
      		   histogram[2][b]++;
      		}
            }
          }
    public BufferedImage wyrHist()
        {
                    int height = obrazek.getHeight();
                    int width = obrazek.getWidth();       
                    BufferedImage wyrObrazek = new BufferedImage(width, height, obrazek.getType());
                    int[] reds = new int[256] ;
                    int[] greens = new int[256] ;
                    int[] blues = new int[256] ;
                    int[] tmpReds = new int[256];
                    int[] tmpGreens = new int[256];
                    int[] tmpBlues = new int[256];
                    long rSuma = 0, gSuma = 0, bSuma = 0;
                    int rNowy, gNowy, bNowy, alpha, red1, green1, blue1, nPix;
   
        for(int i=0; i<width ; i++)
        {
            for(int j=0; j<height ; j++)
            {
                int r = new Color(obrazek.getRGB (i, j)).getRed();
                    reds[r]++;
                int g = new Color(obrazek.getRGB (i, j)).getGreen();
                    greens[g]++;
                int b = new Color(obrazek.getRGB (i, j)).getBlue();
                    blues[b]++;
            }
        }
       
        for(int i=0; i<tmpReds.length; i++) tmpReds[i] = 0;
        for(int i=0; i<tmpGreens.length; i++) tmpGreens[i] = 0;
        for(int i=0; i<tmpBlues.length; i++) tmpBlues[i] = 0;

        float wsp = (float) (255.0/(obrazek.getWidth()*obrazek.getHeight()));
        
        for(int i=0; i<256; i++)
        {
            rSuma += reds[i];
            gSuma += greens[i];
            bSuma += blues[i];
            
            rNowy = (int) (rSuma * wsp);
            gNowy = (int) (gSuma * wsp);
            bNowy = (int) (bSuma * wsp);
            
            if(rNowy >= 255) tmpReds[i] = 255;
            else tmpReds[i] = rNowy;
            
            if(gNowy >= 255) tmpGreens[i] = 255;
            else tmpGreens[i] = gNowy;
            
            if(bNowy >= 255) tmpBlues[i] = 255;
            else tmpBlues[i] = bNowy;
        }

        for(int i=0; i<width; i++)
        {
            for(int j=0; j<height; j++)
            {

                alpha = new Color(obrazek.getRGB (i, j)).getAlpha();
                red1 = new Color(obrazek.getRGB (i, j)).getRed();
                green1 = new Color(obrazek.getRGB (i, j)).getGreen();
                blue1 = new Color(obrazek.getRGB (i, j)).getBlue();

                red1 = tmpReds[red1];
                green1 = tmpGreens[green1];
                blue1 = tmpBlues[blue1];

                nPix = 0;
                nPix += alpha;
                nPix = nPix << 8;
                nPix += red1;
                nPix = nPix << 8;
                nPix += green1;
                nPix = nPix << 8;
                nPix += blue1;
                wyrObrazek.setRGB(i, j, nPix);
            }
        }
       obrazek=wyrObrazek;
        return wyrObrazek;
}
    
    public void progowanie(int prog) {
        float []tab=new float[256];
        int[] tabPixela=new int[3];
        int[] r=new int[256];
        int[] g=new int[256];
        int[] b=new int[256];
        int sumaPixeli=obrazek.getHeight()*obrazek.getWidth();
        Raster rast=obrazek.getRaster();
        for(int i=0;i<obrazek.getWidth();i++){
            for(int j=0;j<obrazek.getHeight();j++){
                tabPixela=rast.getPixel(i, j, tabPixela);
                r[tabPixela[0]]++;
                g[tabPixela[1]]++;
                b[tabPixela[2]]++;
            }
        }
        
        for(int i=0;i<256;i++)
            tab[i]= (float)( (r[i]+g[i]+b[i]) / 3.0)/sumaPixeli;

	        float wariancja = 0,tmp=0, q1 = 0,q2 = 0, y1 = 0,y2 = 0;

	        for(int i=0;i<256;i++){
	            for(int j=0;j<=i;j++){
	                q1+=(float)tab[j];
	            }
	            for(int j=i+1;j<256;j++){
	                q2+=(float)tab[j];
	            }
	            for(int j=0;j<=i;j++){
	                y1+=(float)(j*tab[j])/q1;
	            }
	            for(int j=i+1;j<256;j++){
	                y2+=(float)(j*tab[j])/q2;
	            }
	            wariancja = (float) (q1 * (1 - q1) * Math.pow(y1 - y2, 2));
	            if(wariancja>tmp){
	                tmp=wariancja;
	                threshold=i;
	            }
	            y1=y2=q1=q2=0;
	        }
        

            for(int i = 0; i < obrazek.getHeight(); i++) {
					for(int j = 0; j <obrazek.getWidth(); j++) {
						Color kolor = new Color(obrazek.getRGB(j, i));
						int kolorNew1 = (kolor.getRed());
						int kolorNew2 = (kolor.getGreen()); 
						int kolorNew3 = (kolor.getBlue()); 
						if(kolorNew1<prog) {
							kolorNew1=0; 
						} else {
							kolorNew1=255; 						
						}
						if(kolorNew2<prog) {
							kolorNew2=0; 
						} else {
							kolorNew2=255; 						
						}
						if(kolorNew3<prog) {
							kolorNew3=0; 
						} else {
							kolorNew3=255; 						
						}
						obrazek.setRGB(j, i, new Color(kolorNew1,kolorNew2,kolorNew3).getRGB());
					}
				}
                    repaint();
    }
    
    public void Otsu() 
     {
		
        float []tab=new float[256];
        int[] tabPixela=new int[3];
        int[] r=new int[256];
        int[] g=new int[256];
        int[] b=new int[256];
        int sumaPixeli=obrazek.getHeight()*obrazek.getWidth();
        Raster rast=obrazek.getRaster();
        for(int i=0;i<obrazek.getWidth();i++){
            for(int j=0;j<obrazek.getHeight();j++){
                tabPixela=rast.getPixel(i, j, tabPixela);
                r[tabPixela[0]]++;
                g[tabPixela[1]]++;
                b[tabPixela[2]]++;
            }
        }
        
        for(int i=0;i<256;i++)
            tab[i]= (float)( (r[i]+g[i]+b[i]) / 3.0)/sumaPixeli;

	        float wariancja = 0,tmp=0, q1 = 0,q2 = 0, y1 = 0,y2 = 0;

	        for(int i=0;i<256;i++){
	            for(int j=0;j<=i;j++){
	                q1+=(float)tab[j];
	            }
	            for(int j=i+1;j<256;j++){
	                q2+=(float)tab[j];
	            }
	            for(int j=0;j<=i;j++){
	                y1+=(float)(j*tab[j])/q1;
	            }
	            for(int j=i+1;j<256;j++){
	                y2+=(float)(j*tab[j])/q2;
	            }
	            wariancja = (float) (q1 * (1 - q1) * Math.pow(y1 - y2, 2));
	            if(wariancja>tmp){
	                tmp=wariancja;
	                threshold=i;
	            }
	            y1=y2=q1=q2=0;
	        }
	        System.out.println("otsu prog: "+threshold);
        

            for(int i = 0; i < obrazek.getHeight(); i++) {
					for(int j = 0; j <obrazek.getWidth(); j++) {
						Color kolor = new Color(obrazek.getRGB(j, i));
						int kolorNew1 = (kolor.getRed());
						int kolorNew2 = (kolor.getGreen()); 
						int kolorNew3 = (kolor.getBlue()); 
						if(kolorNew1<threshold) {
							kolorNew1=0; 
						} else {
							kolorNew1=255; 						
						}
						if(kolorNew2<threshold) {
							kolorNew2=0; 
						} else {
							kolorNew2=255; 						
						}
						if(kolorNew3<threshold) {
							kolorNew3=0; 
						} else {
							kolorNew3=255; 						
						}
						obrazek.setRGB(j, i, new Color(kolorNew1,kolorNew2,kolorNew3).getRGB());
					}
				}
		}
    public void medianowy() 
           {
            int w = obrazek.getWidth();
            int h = obrazek.getHeight();
            BufferedImage obrazMedianowy = obrazek;
            int z = 3;
            int z1 = (2*z+1)*(2*z+1);
            int[] P = new int[z1];
 
            for (int v=z; v<=h-z-1; v++) 
            {
                for (int u=z; u<=w-z-1; u++) 
                {
                    int k = 0;
                    for (int j=-z; j<=z; j++) 
                    {
                        for (int i=-z; i<=z; i++) 
                        {
                            P[k]=obrazMedianowy.getRGB(u+i,v+j)+0xFFFFFF+1;
                            k++;
                        }
                    }
                    Arrays.sort(P);
                    obrazek.setRGB(u,v, P[(z1-1)/2]);
                }
            }
            obrazek=obrazMedianowy;
           }
    
    public void splotowy1()
        {
          
          float[] filter = {-1.0f, -2.0f, -1.0f, 
                            -2.0f, 12.0f, -2.0f,
                            -1.0f, -2.0f, -1.0f};

          int filterSize = (int) Math.sqrt(filter.length);
          Kernel k = new Kernel(filterSize, filterSize, filter);
          ConvolveOp cop = new ConvolveOp(k, ConvolveOp.EDGE_NO_OP, null);
          Filtr(cop);
          repaint();
        }   
    public void splotowy2()
        {
          
          float[] filter = {1/16, 2/16f, 1/16f, 
                            2/16f, 4/16f, 2/16f};

          int filterSize = (int) Math.sqrt(filter.length);
          Kernel k = new Kernel(filterSize, filterSize, filter);
          ConvolveOp cop = new ConvolveOp(k, ConvolveOp.EDGE_NO_OP, null);
          Filtr(cop);
          repaint();
        }
    
    public void dodaj_2_obrazki(){
        Color newColor;
        Color prevColor;
        int r, g, b;
        if(obrazek.getWidth() != obrazek2.getWidth() || obrazek.getHeight() != obrazek2.getHeight()){
            javax.swing.JOptionPane.showMessageDialog(this, "Można dodawać jedynie obrazy o takich samych wymiarach");
            return;
            }
        for (int i = 0; i < obrazek2.getWidth(); i++){
            for(int j = 0; j < obrazek2.getHeight(); j++){
            prevColor = new Color(obrazek.getRGB(i, j));
            newColor = new Color(obrazek2.getRGB(i, j));

            r = (newColor.getRed() + prevColor.getRed())/2;
            g = (newColor.getGreen() + prevColor.getGreen())/2;
            b = (newColor.getBlue() + prevColor.getBlue())/2;

            Color nowy = new Color (r, g, b);
            obrazek.setRGB(i, j, nowy.getRGB());
            }
            }
        repaint();
}
    
    
    
    
    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables

}
