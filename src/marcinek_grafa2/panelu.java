package marcinek_grafa2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class panelu extends JPanel {
    static Histogramy histogramy = null;
		public static BufferedImage ten_namalowany, skladak, zapamietany1, zapamietany2,do_filtrow,original, oryginal;
		public static int []red;
        public static int[]green;
        public static int []blue;
		private static int  maxR;
        private static int maxG;
        private static int maxB;
		static int i,j, kolorro, wartosc_dod_odej,mac;
        public static boolean stan;
        private static int r,g,b;
        private static File plik;
        public static int szer,wys;
        //====gaussian
        private static double[][] rozklad;
        static int pomoc =0;

        panelu() {
        System.out.println("Musiałem zrobic bezparametrowy");
    }
public static void set_stan(boolean a){
        panelu.stan = a;
        }
public static void set_panel(BufferedImage obraz, int[]red, int[]green, int[] blue){
    if (histogramy != null)

        histogramy.setVisible(false);
            panelu.ten_namalowany=obraz;
			panelu.red=red;
			panelu.green=green;
			panelu.blue=blue;
            panelu.skladak = new BufferedImage(ten_namalowany.getWidth(), ten_namalowany.getHeight(), BufferedImage.TYPE_INT_RGB);
            panelu.do_filtrow = new BufferedImage(ten_namalowany.getWidth(), ten_namalowany.getHeight(), BufferedImage.TYPE_INT_RGB);
            histogramy = new Histogramy(obraz,1);
            histogramy.setVisible(true);
            if (pomoc == 0)
            {
                pomoc = 1;
                zap_oryg(ten_namalowany);
            }
}
    @Override
		public void paintComponent(Graphics g){
        if(!stan){
            System.out.println("teraz nie moge namalować ");
        }
        else {
            System.out.println("maluje ");
            setSize(1280,770);
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g2);
			if(ten_namalowany != null)
				g2.drawImage(ten_namalowany, 0, 0, null);
        }
    }
public static void skala_szarosci(int jaka_skala_szarosci) {
    try{
        for (i = 0; i < ten_namalowany.getWidth(); i++) {
            for (j = 0; j < ten_namalowany.getHeight(); j++) {
                Color color = new Color(ten_namalowany.getRGB(i, j));
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                if (jaka_skala_szarosci == 1) {
                    kolorro = (r * 299 + g * 587 + b * 114) / 1000;
                } else if (jaka_skala_szarosci == 2) {
                    kolorro = (r + g + b) / 3;
                }
                Color sredni = new Color(kolorro, kolorro, kolorro);
                red[kolorro]++;
                green[kolorro]++;
                blue[kolorro]++;

                skladak.setRGB(i, j, sredni.getRGB());
            }
        }
        set_panel(skladak, red, green, blue);
        } catch (NullPointerException e) {System.out.println("Nie ma obrazka do poddania skali szarości "+e);}

}
 public static int szukaj_poczatku(int [] tab){
        for(i=0;i<256;i++)
            if(tab[i]!=0)
                    break;
        System.out.println(i+ " szukaj poczatku");
        return i;
    }
 public static int szukaj_konca(int [] tab){
        for(i=255;i>=0;i--)
            if(tab[i]!=0)
                break;
        System.out.println(i+ " szukaj końca");
        return i;
    }
 public static void norma(BufferedImage bi){
     try{
            int poczR = szukaj_poczatku(red);
            int poczG = szukaj_poczatku(green);
            int poczB = szukaj_poczatku(blue);
            int konR = szukaj_konca(red);
            int konG = szukaj_konca(green);
            int konB = szukaj_konca(blue);
            int dlugoscHR = konR-poczR;
            int dlugoscHG = konG-poczG;
            int dlugoscHB = konB-poczB;

            double ileR, ileG, ileB;
            ileR = 255.0/dlugoscHR;
            ileG = 255.0/dlugoscHG;
            ileB = 255.0/dlugoscHB;
            System.out.println(ileR+" "+ileG+" "+ileB+" ");

            for (i = 0; i < bi.getWidth(); i++){
                for (j = 0; j < bi.getHeight(); j++){
                    Color color = new Color(bi.getRGB(i, j));
                    r =color.getRed();
                    g =color.getGreen();
                    b =color.getBlue();

                    r = r - poczR;
                    g = g - poczG;
                    b = b - poczB;

                    r=(int) (r * ileR);
                       if(r>255) r=255;
                       if(r<0) r=0;
                    g = (int) (g * ileG);
                        if(g>255) g=255;
                        if(g<0) g=0;
                    b = (int) (b * ileB);
                        if(b>255) b=255;
                        if(b<0) b=0;
                    red[r]++;
                    green[g]++;
                    blue[b]++;

                      Color kolor = new Color(r, g, b);
                      skladak.setRGB(i, j, kolor.getRGB());
                }
            }
           set_panel(skladak, red, green, blue);
    } catch (NullPointerException e) {System.out.println("Nie ma obrazka do normalizowania: "+e);}
 }
 public static void dodawanie_odejmowanie(BufferedImage bi){
     try{
            String wartoscS = JOptionPane.showInputDialog("Podaj wartość: ");
            wartosc_dod_odej = Integer.parseInt(wartoscS);
            for (i = 0; i < bi.getWidth(); i++) {
                for (j = 0; j < bi.getHeight(); j++) {
                    Color color = new Color(bi.getRGB(i, j));
                    r = color.getRed() + wartosc_dod_odej;
                    if (r > 255) {
                        r = 255;
                    }
                    if (r < 0) {
                        r = 0;
                    }
                    g = color.getGreen() + wartosc_dod_odej;
                    if (g > 255) {
                        g = 255;
                    }
                    if (g < 0) {
                        g = 0;
                    }
                    b = color.getBlue() + wartosc_dod_odej;
                    if (b > 255) {
                        b = 255;
                    }
                    if (b < 0) {
                        b = 0;
                    }
                            red[r]++;
                            green[g]++;
                            blue[b]++;
                    Color kolor = new Color(r, g, b);
                    skladak.setRGB(i, j, kolor.getRGB());
                }
            }
            set_panel(skladak, red, green, blue);
    }catch (NullPointerException e) {System.out.println("Nie ma obrazka do dodawania/odejmowania wartośći: "+e);}
     catch (NumberFormatException e) {System.out.println("Zły format liczb: "+e);}
 }
 public static void mnozenie(BufferedImage bi){
     try{
            String wartoscS = JOptionPane.showInputDialog("Podaj wartość: ");
            wartosc_dod_odej = Integer.parseInt(wartoscS);
            for (i = 0; i < bi.getWidth(); i++) {
                for (j = 0; j < bi.getHeight(); j++) {
                    Color color = new Color(bi.getRGB(i, j));
                            r = color.getRed() * wartosc_dod_odej;
                            if (r > 255) r = 255;
                            if (r < 0) r = 0;

                            g = color.getGreen() * wartosc_dod_odej;
                            if (g > 255) g = 255;
                            if (g < 0) g = 0;

                            b = color.getBlue() * wartosc_dod_odej;
                            if (b > 255) b = 255;
                            if (b < 0)  b = 0;
    
                            red[r]++;
                            green[g]++;
                            blue[b]++;
                    Color kolor = new Color(r, g, b);
                    skladak.setRGB(i, j, kolor.getRGB());
                }
            }
            set_panel(skladak, red, green, blue);
        }catch (NullPointerException e) {System.out.println("Nie ma obrazka do mnozenia: "+e);}
         catch (NumberFormatException e) {System.out.println("Zły format liczb: "+e);}
 }
 public static void dzielenie(BufferedImage bi){
     try{
     String wartoscS = JOptionPane.showInputDialog("Podaj wartość: ");
            wartosc_dod_odej = Integer.parseInt(wartoscS);
            for (i = 0; i < bi.getWidth(); i++) {
                for (j = 0; j < bi.getHeight(); j++) {
                    Color color = new Color(bi.getRGB(i, j));
                            r = color.getRed() / wartosc_dod_odej;
                            if (r > 255) r = 255;
                            if (r < 0) r = 0;

                            g = color.getGreen() / wartosc_dod_odej;
                            if (r > 255) r = 255;
                            if (r < 0) r = 0;

                            b = color.getBlue() / wartosc_dod_odej;
                            if (r > 255) r = 255;
                            if (r < 0) r = 0;

                            red[r]++;
                            green[g]++;
                            blue[b]++;
                    Color kolor = new Color(r, g, b);
                    skladak.setRGB(i, j, kolor.getRGB());
                }
            }
         set_panel(skladak, red, green, blue);
        }catch (NullPointerException e) {System.out.println("Nie ma obrazka do dzielenia: "+e);}
        catch (NumberFormatException e) {System.out.println("Zły format liczb: "+e);}
        catch (ArithmeticException e) {System.out.println("Nie można dzielić przez 0: "+e);}
 }
public static void zapisz(BufferedImage bi){
    if(bi==null) System.out.println("Nie ma czego zapisywać ");
    else {
            JFileChooser fc = new JFileChooser();
            int n = fc.showSaveDialog(null);
            if (n == JFileChooser.APPROVE_OPTION);
            plik = fc.getSelectedFile();

            try {
                ImageIO.write(bi, "jpg", plik);
            } catch (IOException ex) {
                System.out.println("Jakiś wyjątek z serii IOException " + ex);
            } catch (IllegalArgumentException ex) {
                System.out.println("Anulowałeś zapisywanie pliku "+ex);
            }
        }
}
public static void zap_oryg(BufferedImage bi)
{
    try{
        oryginal =  new BufferedImage(ten_namalowany.getWidth(), ten_namalowany.getHeight(), BufferedImage.TYPE_INT_RGB);
        oryginal = bi;
    } catch (NullPointerException e) {System.out.println("Nie ma obrazka nr 1 do zapisania: "+e);}
}

public static void otw_oryg(BufferedImage bi)
{
        try{
        ten_namalowany =  oryginal;
    } catch (NullPointerException e) {System.out.println("Nie ma obrazka nr 1 do zapisania: "+e);}
}
public static void zapamietaj_obrazek1(BufferedImage bi){
     try{
        zapamietany1 =  new BufferedImage(ten_namalowany.getWidth(), ten_namalowany.getHeight(), BufferedImage.TYPE_INT_RGB);
        zapamietany1 = bi;
    } catch (NullPointerException e) {System.out.println("Nie ma obrazka nr 1 do zapisania: "+e);}
}
public static void zapamietaj_obrazek2(BufferedImage bi){
    try{
        zapamietany2 =  new BufferedImage(ten_namalowany.getWidth(), ten_namalowany.getHeight(), BufferedImage.TYPE_INT_RGB);
        zapamietany2 = bi;
    } catch (NullPointerException e) {System.out.println("Nie ma obrazka nr 2 do zapisania: "+e);}
}
public static void dodaj_2_obrazki(BufferedImage ob1, BufferedImage ob2){
    try{
   int r1,r2,g1,g2,b1,b2;

     for (i = 0; i< ob1.getWidth(); i++) {
                    for (j = 0; j < ob1.getHeight(); j++) {
                        Color color1 = new Color(ob1.getRGB(i, j));
                        Color color2 = new Color(ob2.getRGB(i, j));

                        r1 = color1.getRed();
                        r2 = color2.getRed();
                        r = (r1+r2)/2;
                        red[r]++;

                        g1 = color1.getGreen();
                        g2 = color2.getGreen();
                        g = (g1+g2)/2;
                        green[g]++;

                        b1 = color1.getBlue();
                        b2 = color2.getBlue();
                        b = (b1+b2)/2;
                        blue[b]++;
                        Color nowy = new Color (r,g,b);
                        skladak.setRGB(i, j, nowy.getRGB());
                    }
        }
    set_panel(skladak, red, green, blue);
    }catch(NullPointerException e){System.out.println("Nie ma obrazkow do dodania "+e);}
     catch(ArrayIndexOutOfBoundsException e){System.out.println("wymiary obrazków znacznie sie róznią: "+e);}
 }
public static void binaryzacja(BufferedImage bi, int wartosc){
    int [] tab = new int[256];
    int liczba;
    int liczba2;
    if (pomoc ==1)
    {
        pomoc = 2;
        zapamietaj_obrazek1(ten_namalowany);
    }
    for(i=wartosc;i<256;i++)
        tab[i]=255;
            try{
            if(!stan) System.out.println("Jeszcze nie moge binaryzować ");
            else if (stan == true){
            for (i = 0; i< bi.getWidth(); i++) {
                    for (j = 0; j < bi.getHeight(); j++) {
                         Color color = new Color(bi.getRGB(i, j));
                         r = color.getRed();
                         g = color.getGreen();
                         b = color.getBlue();
                         liczba2=(r * 299 + g * 587 + b * 114) / 1000;
                         liczba = tab[liczba2];
                         red[liczba]++;
                         green[liczba]++;
                         blue[liczba]++;

                         Color nowy = new Color(liczba, liczba, liczba);
                         skladak.setRGB(i,j,nowy.getRGB());
                    }
            }
            set_panel(skladak, red, green, blue);

            }
            }catch(NullPointerException e){ System.out.println("Brak pliku do zbinaryzowania "+e); }
    }
    public static void sobel(){
        int sumx=0,sumy=0,sum=0,k,l;
        int [][] h = {  {-1, 0, 1},
                        {-2, 0, 2},
                        {-1, 0, 1},
                     };
        int [][] v = {  { 1,  2,  1},
                        { 0,  0,  0},
                        {-1, -2, -1},  
                     };
        skala_szarosci(1);
         for (i = 0; i< ten_namalowany.getWidth(); i++)
         {
             for (j = 0; j < ten_namalowany.getHeight(); j++)
             {
                 sumx=0;sumy=0;sum=0;
                 for(k=-1;k<=1;k++)
                 {
                     for(l=-1;l<=1;l++)
                     {
                         int ii=i+k; if(ii<0 || ii>=ten_namalowany.getWidth()) ii=0;
                         int jj=j+l; if(jj<0 || jj>=ten_namalowany.getHeight()) jj=0;
                         int pixel=ten_namalowany.getRGB(ii,jj);
                         sumx+=pixel*h[k+1][l+1];
                         sumy+=pixel*v[k+1][l+1];
                     }
                    
                 }
                  sum+=Math.abs(sumx)+Math.abs(sumy);
                 do_filtrow.setRGB(i, j, sum);
             }
         }
                    for(i=0;i<256;i++)
                    {
                        red[i]=0;
                        green[i]=0;
                        blue[i]=0;
                    }
                    for (i = 0; i < do_filtrow.getWidth(); i++) {
                        for (j = 0; j < do_filtrow.getHeight(); j++)
                        {
                            Color color = new Color(do_filtrow.getRGB(i, j));
                            r = color.getRed();
                            g = color.getGreen();
                            b = color.getBlue();

                            red[r]++;
                            green[g]++;
                            blue[b]++;
                        }
                    }
         set_panel(do_filtrow, red, green, blue);
    }
    public static void prewit(){
        int sumx=0,sumy=0,sum=0,k,l;
        int [][] h = {  {-1, 0, 1},
                        {-1, 0, 1},
                        {-1, 0, 1},
                     };
        int [][] v = {  {-1,  -1, -1},
                        { 0,   0,  0},
                        { 1,   1,  1},
                     };
        skala_szarosci(1);
         for (i = 0; i< ten_namalowany.getWidth(); i++)
         {
             for (j = 0; j < ten_namalowany.getHeight(); j++)
             {
                 sumx=0;sumy=0;sum=0;
                 for(k=-1;k<=1;k++)
                 {
                     for(l=-1;l<=1;l++)
                     {
                         int ii=i+k; if(ii<0 || ii>=ten_namalowany.getWidth()) ii=0;
                         int jj=j+l; if(jj<0 || jj>=ten_namalowany.getHeight()) jj=0;
                         int pixel=ten_namalowany.getRGB(ii,jj);
                         sumx+=pixel*h[k+1][l+1];
                         sumy+=pixel*v[k+1][l+1];
                     }

                 }
                  sum+=Math.abs(sumx)+Math.abs(sumy);
                 do_filtrow.setRGB(i, j, sum);
             }
         }
                    for(i=0;i<256;i++)
                    {
                        red[i]=0;
                        green[i]=0;
                        blue[i]=0;
                    }
                    for (i = 0; i < do_filtrow.getWidth(); i++) {
                        for (j = 0; j < do_filtrow.getHeight(); j++)
                        {
                            Color color = new Color(do_filtrow.getRGB(i, j));
                            r = color.getRed();
                            g = color.getGreen();
                            b = color.getBlue();

                            red[r]++;
                            green[g]++;
                            blue[b]++;
                        }
                    }
         set_panel(do_filtrow, red, green, blue);
    }
     public static void mean(){
        int sum=0,k,l;
        int sumr=0,sumg=0,sumb=0;
         Color color=null;

         for (i = 0; i< ten_namalowany.getWidth(); i++)
         {
             for (j = 0; j < ten_namalowany.getHeight(); j++)
             {
                 sum=0;sumr=0;sumg=0;sumb=0;
                 for(k=-1;k<=1;k++)
                 {
                     for(l=-1;l<=1;l++)
                     {
                         int ii=i+k; if(ii<0 || ii>=ten_namalowany.getWidth()) ii=0;
                         int jj=j+l; if(jj<0 || jj>=ten_namalowany.getHeight()) jj=0;
                            color = new Color(ten_namalowany.getRGB(ii, jj));
                            r = color.getRed();
                            g = color.getGreen();
                            b = color.getBlue();

                            sumr+=r;
                            sumg+=g;
                            sumb+=b;
                            color=new Color(sumr/9,sumg/9,sumb/9);

                     }
                     
                 }
                 do_filtrow.setRGB(i, j, color.getRGB());
             }
         }
                    for(i=0;i<256;i++)
                    {
                        red[i]=0;
                        green[i]=0;
                        blue[i]=0;
                    }
                    for (i = 0; i < do_filtrow.getWidth(); i++) {
                        for (j = 0; j < do_filtrow.getHeight(); j++)
                        {
                            Color colorr = new Color(do_filtrow.getRGB(i, j));
                            r = colorr.getRed();
                            g = colorr.getGreen();
                            b = colorr.getBlue();

                            red[r]++;
                            green[g]++;
                            blue[b]++;
                        }
                    }
         set_panel(do_filtrow, red, green, blue);
    }
     public static void mediana(){
        int k,l,licznik=0;
      Color [] tabK=new Color[9];
      Color ms=null;
         for (i = 0; i< ten_namalowany.getWidth(); i++)
         {
             for (j = 0; j < ten_namalowany.getHeight(); j++)
             {
                 ms=null;
                 for(k=-1;k<=1;k++)
                 {
                     for(l=-1;l<=1;l++)
                     {
                         int ii=i+k; if(ii<0 || ii>=ten_namalowany.getWidth()) ii=0;
                         int jj=j+l; if(jj<0 || jj>=ten_namalowany.getHeight()) jj=0;
                          Color color = new Color(ten_namalowany.getRGB(ii, jj));
                            r = color.getRed();
                            g = color.getGreen();
                            b = color.getBlue();
                         tabK[licznik]=color;
                         licznik++;
                     }
                 }
                             tabK=bubbleSort(tabK);
                             ms=tabK[4];
                             licznik=0;


                 do_filtrow.setRGB(i, j, ms.getRGB());
             }
         }
                    for(i=0;i<256;i++)
                    {
                        red[i]=0;
                        green[i]=0;
                        blue[i]=0;
                    }
                    for (i = 0; i < do_filtrow.getWidth(); i++) {
                        for (j = 0; j < do_filtrow.getHeight(); j++)
                        {
                            Color color = new Color(do_filtrow.getRGB(i, j));
                            r = color.getRed();
                            g = color.getGreen();
                            b = color.getBlue();

                            red[r]++;
                            green[g]++;
                            blue[b]++;
                        }
                    }
         set_panel(do_filtrow, red, green, blue);
    }
      public static Color [] bubbleSort(Color[] pikseloza) {
        int c,z,s1,s2;
        Color pom;
         for(z = 8; z >= 0; z--)
         {
                for(c = 1; c <= z; c++)
                {
                   s1=((pikseloza[c-1].getRed()+pikseloza[c-1].getGreen()+pikseloza[c-1].getBlue())/3);
                   s2=((pikseloza[c].getRed()+pikseloza[c].getGreen()+pikseloza[c].getBlue())/3);
                  if(s1 > s2)
                  {
                      pom = pikseloza[c-1];
                      pikseloza[c-1]=pikseloza[c];
                      pikseloza[c]=pom;
                  }
                }
         }
        return pikseloza;
    }
	public static void wypelnianie_tablicy_dla_Gaussa(int macc)
	{
		if( macc < 3)  macc = 3;
		if( macc % 2 == 0)macc++;
		wys=mac;
        szer = mac;
        double sigma=mac/3;
		rozklad = new double[szer][wys];
		for(int x = szer/2; x < szer; x++){
			for(int y =wys/2; y < wys; y++){
                double w0 = -(x*x + y*y) / (2*sigma*sigma);
                double w1 = Math.exp(w0);
                double w2 = (1 / (Math.sqrt(2*Math.PI) * (sigma*sigma)));
                double wartosc=w1*w2;
                rozklad[x][y] = wartosc;
                rozklad[szer-x-1][wys-y-1] = wartosc;
                rozklad[x][wys-y-1] = wartosc;
                rozklad[szer-x-1][y] = wartosc;
			}
		}
	}
	public static void GaussianBlur() {
        int k,l,dl;
        double max=0;
        String wartoscS = JOptionPane.showInputDialog("Podaj rozmiar macierzy: np: 3");
        mac = Integer.parseInt(wartoscS);
        skala_szarosci(1);
        wypelnianie_tablicy_dla_Gaussa(mac);
        dl=szer/2;
        System.out.println(dl);
        for(int x = 0; x < szer; x++){
			for(int y = 0; y < szer; y++)
				System.out.print(rozklad[x][y]+ " ");
			System.out.println();
        }
		for(i =0; i< ten_namalowany.getWidth(); i++)
        {
			for(j =0; j < ten_namalowany.getHeight(); j++)
            {
				double sum = 0;
				for(k = -dl; k <= dl; k++)
                {
					for(l = -dl; l <= dl; l++)
                    {
                         int ii=i+k;  if(ii<0 || ii>=ten_namalowany.getWidth()) ii=0;
                         int jj=j+l;  if(jj<0 || jj>=ten_namalowany.getHeight()) jj=0;

                         Color kk=new Color(ten_namalowany.getRGB(ii, jj));
                            r = kk.getRed();
                            g = kk.getGreen();
                            b = kk.getBlue();
                           sum += Math.abs(r* rozklad[k+dl][l+dl]);
					}   
                }
                    if(sum > max)max = sum;
                    if (sum > 255) sum = 255;
                    if (sum < 0) sum = 0;
                    do_filtrow.setRGB(i, j,new Color((int) sum,(int) sum,(int) sum).getRGB());
            }
        }
        System.out.println(max);
        double mnoznik = 255/max;
        System.out.println(mnoznik);
        for(int x =0; x < do_filtrow.getWidth(); x++)
        {
			for(int y =0; y < do_filtrow.getHeight(); y++)
            {
                Color nowiusienki=new Color (do_filtrow.getRGB(x, y));
                    r = nowiusienki.getRed();
                    g = nowiusienki.getGreen();
                    b = nowiusienki.getBlue();
                    r=(int) (r * mnoznik);
				do_filtrow.setRGB(x, y,new Color(r,r,r).getRGB());
            }
        }

                    for(i=0;i<256;i++)
                    {
                        red[i]=0;
                        green[i]=0;
                        blue[i]=0;
                    }
                    for (i = 0; i < do_filtrow.getWidth(); i++) {
                        for (j = 0; j < do_filtrow.getHeight(); j++)
                        {
                            Color colo = new Color(do_filtrow.getRGB(i, j));
                            r = colo.getRed();
                            g = colo.getGreen();
                            b = colo.getBlue();

                            red[r]++;
                            green[g]++;
                            blue[b]++;
                        }
                    }
        set_panel(do_filtrow, red, green, blue);
       
    }
    public void jasnosc(BufferedImage obrazek)
    {

    }
}