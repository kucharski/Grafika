package photoshopa;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.media.jai.Histogram;
import javax.swing.JPanel;




public final class DisplayPanel extends JPanel{
	  public Image displayImage;
	  public BufferedImage bi,bi_filtered, bi_filteredContrast=null,filteredimage, nowyObrot;
	  public Graphics2D big;
	  public LookupTable lookupTable;
	  public DisplayPanel dispanel;
	  public boolean blackAndWhite=true;
	  public File baza;
	  public int [][] histogram;
          public Histogram hist;
          public Arrays Arrays;
          int radius;
          public boolean cancelFlag = false;
          public boolean runningFlag = false;
          public int progress = 0,obrotrgb=0;
	  public LookupOp lookup;
	  private int counter=0;
	  private int[][]bins;
	  public boolean grayScale;
          public int[] red = new int[256];
          public int[] green = new int[256];
          public int[] blue = new int[256];
          public int[] rgb = new int[256];   
          int threshold = 0;
          private int histHeight = 256;
          public int x=0;
          public float skala=0;
          public int width,height;
          public boolean test = false;
	  
	 public  DisplayPanel() 
         {
            setBackground(Color.white); // panel background color
            setSize(1366,700);
	 }
         //#### OTWORZ OBRAZ ##################################################################

	 public void loadImage(File p) 
         {

            if(p!=null)
            {
                baza=p;
	 	displayImage = Toolkit.getDefaultToolkit().getImage(p.getAbsolutePath()); //ladowanie obrazu
	 	try 
                {
                    bi=ImageIO.read(p);
                    bi_filtered=ImageIO.read(p);
		} 
                catch (IOException e) 
                {		
		}

            }
	    MediaTracker mt = new MediaTracker(this);//czeka na załadowanie obrazka
	    mt.addImage(displayImage, 1);
	    try 
            {
	      mt.waitForAll();
	    } 
            catch (Exception e) 
            {
	      System.out.println("Exception while loading.");
	    }
	    if (displayImage.getWidth(this) == -1) 
            {
	      System.out.println("No jpg file");
	      System.exit(0);
	    }
	    else 
	    {
                setSize(displayImage.getWidth(this), displayImage.getWidth(this));	
		createBufferedImage();    	
	    }
            

	  }
        //### ZAPISANIE OBRAZKA #################################################
         public void saveImage(File p) 
         {
            if(p!=null)
            {
              try 
              {
                ImageIO.write(bi,"jpg", p);	
              } 
              catch (IOException e) 
              {
              }
            }
         }

     //### TWORZENIE OBRAZKA ##############################################################    
	  public void createBufferedImage() 
          {
	    big = bi.createGraphics();
	    big.drawImage(displayImage, 0, 0, this);
	    repaint();
	  }
     // ## WYKONYWANIE OPERCJI NA OBRAZIE ZA POMOCA MASKI ( FILTR I CONVOLVE)######################
	   private void Filtr(BufferedImageOp op)
	  {
	        BufferedImage obrazekFiltr = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
	        op.filter(bi, obrazekFiltr);// wykonuje zmaiany na obrazie
	        bi = obrazekFiltr;
	        repaint();
	    }
	  private void Convolve(float[] tabK)
	  {
	    Kernel kernel = new Kernel(3, 3, tabK); // definiuje macierz ktora opisuje piksel i jego sasiadujace
	    ConvolveOp op = new ConvolveOp(kernel);// zmienia obraz według macierzy
	    Filtr(op);
          }
       //### ROZMYCIE OBRAZU #########################################################
	    
	  public void Rozmycie()
	  {
	        float[] tab = {0.11111111f, 0.11111111f, 0.11111111f, 
                               0.11111111f, 0.11111111f, 0.11111111f, 
                               0.11111111f, 0.11111111f, 0.11111111f};
	        Convolve(tab);
	  }
//### WYTLACZANIE OBRAZU ################################################################  
	  public void Wytlaczanie()
	  {
	        float[] tab = {-2.0f, -1.0f, 0.0f, 
                               -1.0f, 1.0f, 1.0f,
                               0.0f, 1.0f, 2.0f};
	        Convolve(tab);
	  }
      //## WYOSTRZENIE ############################################################

	  public void Wyostrzanie()
	  {
	        float[] tab = {-1.0f, -1.0f, -1.0f,
                               -1.0f, 8.0f, -1.0f, 
                               -1.0f, -1.0f, -1.0f};
	        Convolve(tab);
	    }
       //## KRAWEDZ POZIOMA #########################################

	  public void KrawedzPoziome()
	  {
	        float[] tab = {-1.0f, -1.0f, -1.0f, 
                                2.0f, 2.0f, 2.0f, 
                               -1.0f, -1.0f, -1.0f};
	        Convolve(tab);
	    }
//### KRAWEDZ PIONOWA #################################################
	  public void KrawedzPionowe()
	  
          {
	        float[] tab = {-1.0f, 2.0f, -1.0f, 
                               -1.0f, 2.0f, -1.0f, 
                               -1.0f, 2.0f, -1.0f};
	        Convolve(tab);
	    }
     //### KRAWEDZ LEWA PRZEKATNA#####################################################

	  public void KrawedzLewaPrzekatna()
	  {
	        float[] tab = {2.0f, -1.0f, -1.0f, 
                              -1.0f, 2.0f, -1.0f, 
                              -1.0f, -1.0f, 2.0f};
	        Convolve(tab);
	  }
     // ### KRAWEDZ PRAWA PRZEKATNA ####################################################

	  public void KrawedzPrawaPrzekatna()
	  {
	        float[] tab = {-1.0f, -1.0f, 2.0f, 
                               -1.0f, 2.0f, -1.0f, 
                                2.0f, -1.0f, -1.0f};
	        Convolve(tab);
	    }
          
         //#### EFEKT CZARNOBIALY #############################################
          public void blackAndWhite()
          {  
			  BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null);
			  op.filter(bi,bi);  
		  }
          //### DO MALOWANIA HISTOGRAMU ###########################################
		  
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
 height = bi.getHeight();
 width = bi.getWidth();       
 BufferedImage outp = new BufferedImage(width, height, bi.getType());
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
                int r = new Color(bi.getRGB (i, j)).getRed();
                    reds[r]++;
                int g = new Color(bi.getRGB (i, j)).getGreen();
                    greens[g]++;
                int b = new Color(bi.getRGB (i, j)).getBlue();
                    blues[b]++;
            }
        }
       
        for(int i=0; i<tmpReds.length; i++) tmpReds[i] = 0;
        for(int i=0; i<tmpGreens.length; i++) tmpGreens[i] = 0;
        for(int i=0; i<tmpBlues.length; i++) tmpBlues[i] = 0;

        float wsp = (float) (255.0/(bi.getWidth()*bi.getHeight()));
        
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

                alpha = new Color(bi.getRGB (i, j)).getAlpha();
                red1 = new Color(bi.getRGB (i, j)).getRed();
                green1 = new Color(bi.getRGB (i, j)).getGreen();
                blue1 = new Color(bi.getRGB (i, j)).getBlue();

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
                outp.setRGB(i, j, nPix);
            }
        }
       bi=outp;
        return outp;
}

	   public BufferedImage medianowy() 
           {
            int w = bi.getWidth();
            int h = bi.getHeight();
            BufferedImage copy = bi;
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
                            P[k]=copy.getRGB(u+i,v+j)+0xFFFFFF+1;
                            k++;
                        }
                    }
                    Arrays.sort(P);
                    bi.setRGB(u,v, P[(z1-1)/2]);
                }
            }
            return bi;
           }

     public void otsu(int prog) 
     {
		
        float []tab=new float[256];
        int[] tabPixela=new int[3];
        int[] r=new int[256];
        int[] g=new int[256];
        int[] b=new int[256];
        int sumaPixeli=bi.getHeight()*bi.getWidth();
        Raster rast=bi.getRaster();
        for(int i=0;i<bi.getWidth();i++){
            for(int j=0;j<bi.getHeight();j++){
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
        

            for(int i = 0; i < bi.getHeight(); i++) {
					for(int j = 0; j <bi.getWidth(); j++) {
						Color kolor = new Color(bi.getRGB(j, i));
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
						bi.setRGB(j, i, new Color(kolorNew1,kolorNew2,kolorNew3).getRGB());
					}
				}
                    repaint();
		}
public void progowanie() 
{
     for(int i = 0; i < bi.getHeight(); i++) 
     {
        for(int j = 0; j <bi.getWidth(); j++) 
        {
            Color kolor = new Color(bi.getRGB(j, i));
            int kolorNew = (kolor.getRed()+kolor.getGreen()+kolor.getBlue())/3; 
            if(kolorNew<127) 
            {
		kolorNew=0; 
            } 
            else
            {
		kolorNew=255; 						
            }
            bi.setRGB(j, i, new Color(kolorNew,kolorNew,kolorNew).getRGB());
	}
    }
}
public void splotowy()
{
          
          float[] filter = {0.0f, -1.0f, 0.0f, 
                               -1.0f, 4.0f, -1.0f,
                               0.0f, -1.0f, 0.0f};

          int filterSize = (int) Math.sqrt(filter.length);
          Kernel k = new Kernel(filterSize, filterSize, filter);
          ConvolveOp cop = new ConvolveOp(k, ConvolveOp.EDGE_NO_OP, null);
          Filtr(cop);
          repaint();
}
    
	  public void contrast(double param)
	  {
		 // bi_filteredContrast=bi;
		  short brighten[] = new short[256];
		    for (int i = 0; i < 256; i++) {
		      short pixelValue = (short) (i * param);
		      if (pixelValue > 255)
		        pixelValue = 255;
		      else if (pixelValue < 0)
		        pixelValue = 0;
		      brighten[i] = pixelValue;
		    }
		    lookupTable = new ShortLookupTable(0, brighten);
		  //  applyFilter();
		    LookupOp lop = new LookupOp(lookupTable, null);
		    lop.filter(bi_filteredContrast, bi_filteredContrast);
		  //  big = bi_filteredContrast.createGraphics();
		 //   big.drawImage(displayImage, 0, 0, this);
		    repaint();
	  }
	    
	  public void brightenLUT() {
	    short brighten[] = new short[256];
	    for (int i = 0; i < 256; i++) {
	      short pixelValue = (short) (i + 10);
	      if (pixelValue > 255)
	        pixelValue = 255;
	      else if (pixelValue < 0)
	        pixelValue = 0;
	      brighten[i] = pixelValue;
	    }
	    lookupTable = new ShortLookupTable(0, brighten);
	  }

		  public void darkenLUT() {
		    short brighten[] = new short[256];
		    for (int i = 0; i < 256; i++) {
		      short pixelValue = (short) (i - 10);
		      if (pixelValue > 255)
		        pixelValue = 255;
		      else if (pixelValue < 0)
		        pixelValue = 0;
		      brighten[i] = pixelValue;
		    }
		    lookupTable = new ShortLookupTable(0, brighten);
		  }

		  public void contrastIncLUT() {
		    short brighten[] = new short[256];
		    for (int i = 0; i < 256; i++) {
		      short pixelValue = (short) (i * 1.2);
		      if (pixelValue > 255)
		        pixelValue = 255;
		      else if (pixelValue < 0)
		        pixelValue = 0;
		      brighten[i] = pixelValue;
		    }
		    lookupTable = new ShortLookupTable(0, brighten);
		  }

		  public void contrastDecLUT() {
		    short brighten[] = new short[256];
		    for (int i = 0; i < 256; i++) {
		      short pixelValue = (short) (i * 0.9);
		      if (pixelValue > 255)
		        pixelValue = 255;
		      else if (pixelValue < 0)
		        pixelValue = 0;
		      brighten[i] = pixelValue;
		    }
		    lookupTable = new ShortLookupTable(0, brighten);
		  }

		  public void reverseLUT() {
		    byte reverse[] = new byte[256];
		    for (int i = 0; i < 256; i++) {
		      reverse[i] = (byte) (255 - i);
		    }
		    lookupTable = new ByteLookupTable(0, reverse);
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



        public void dylacja(int radius)
        {   
                int width = bi.getWidth();
                int height = bi.getHeight();
                this.radius=radius;
                BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                
                for (int row = 0; row < height && cancelFlag == false; row++) {
                        progress = 100 * row / height;
                        for (int col = 0; col < width && cancelFlag == false; col++) {
                                img.setRGB(col, row, maxAround(bi, row, col));
                        }
                }
                bi=img;

        }
        
        public void erozja(int radius) {
                
                this.radius=radius;
                int width = bi.getWidth();
                int height = bi.getHeight();
                
                BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                
                for (int row = 0; row < height; row++) {
                        progress = 100 * row / height;
                        for (int col = 0; col < width && cancelFlag == false; col++) {
                                img.setRGB(col, row, minAround(bi, row, col));
                        }
                }
              bi=img;      
               
        }
        public void obrot()
        {
          test = !test;
         
          BufferedImage nowyObrot = new BufferedImage(bi.getHeight(),bi.getWidth(), BufferedImage.TYPE_INT_RGB);

            width=bi.getWidth();
            height=bi.getHeight();

            for(int j=1; j < width; j++)
            {
                for(int k=1; k < height; k++)
                {
                 if(test == true)
                {
                 obrotrgb = bi.getRGB(j,height-k);
                 nowyObrot.setRGB(k, j, obrotrgb);
                }
                if(test == false)
                {
                 obrotrgb = bi.getRGB(j,k);
                 nowyObrot.setRGB(height-k,j, obrotrgb);
                }
                }
            }
            
            bi=nowyObrot;
            repaint();
        }
        
        public void skalowanie(float skala)
        {
            this.skala=skala;

           if(skala > 0 || skala < 0)
                    {
                        BufferedImage nowySkala = new BufferedImage((int)(bi.getWidth()),(int)(bi.getHeight()), BufferedImage.TYPE_INT_RGB);
                        nowySkala = bi;
                        
                        bi = new BufferedImage((int)(bi.getWidth()*skala),(int)(bi.getHeight()*skala), BufferedImage.TYPE_INT_RGB);

                        AffineTransform at = new AffineTransform();
                        at.scale(skala, skala);

                        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
 
                        bi = scaleOp.filter(nowySkala, null);
                        repaint();            
                    }
                
        }

      

		  public void reset() {
			  
			  try {
				bi=ImageIO.read(baza);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			bi_filtered=bi;
			createBufferedImage();
		  }

		    public void applyFilter() {
		    LookupOp lop = new LookupOp(lookupTable, null);
		    lop.filter(bi, bi);
		    
		   // lop.filter(bi_filteredBW, bi_filteredBW);
		  }

		  public void update(Graphics g) {
		    g.clearRect(0, 0, getWidth(), getHeight());
		    paintComponent(g);
		  }

		  public void paintComponent(Graphics g) 
                  {
		    super.paintComponent(g);
		    Graphics2D g2D = (Graphics2D) g;
		    if(bi_filteredContrast==null)
		    g2D.drawImage(bi, 0, 0, this);
		  }
}

