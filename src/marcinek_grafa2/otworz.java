package marcinek_grafa2;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class otworz {
   static Histogramy histogramy;
   private static File plik, out;
   private static BufferedReader p3;
   private static DataInputStream p6;
   private static PrintWriter bw;
   public static BufferedImage bi, obraz, obraz2;
   public static int[] redH = new int[256];
   public static int[] greenH = new int[256];
   public static int[] blueH = new int[256];
   private static int r,g,b;

public static BufferedImage obrazZPliku() throws FileNotFoundException, IOException{
		try{
			JFileChooser fc = new JFileChooser();
			int n = fc.showOpenDialog(null);
				if(n==JFileChooser.APPROVE_OPTION);
						plik = fc.getSelectedFile();
		}
		catch(Exception e){
        System.out.println("Blad w JFileChooser"+e);     }

        try {
            Ramka.treshold.setValue(0);
            int czytnik = 0;
            String typ = "", linia = "";
            int height = 0, width = 0, maxColor = 0;
            int red = 0, green = 0, blue = 0, poz = 0, pion = 0, pomR = 0, pomG = 0, pomB = 0;
            StringTokenizer st1;
            p3 = new BufferedReader(new FileReader(plik));
            p6 = new DataInputStream(new FileInputStream(plik));
            out = new File("D:\\Robert.ppm");
            bw = new PrintWriter(new BufferedWriter(new FileWriter(out)));

            wybieraj info = new wybieraj();
            typ = info.get(p3);

            if (typ.equals("P3")) {
                System.out.println(typ);
                width = Integer.parseInt(info.get(p3));
                System.out.println(width);
                height = Integer.parseInt(info.get(p3));
                System.out.println(height);
                maxColor = Integer.parseInt(info.get(p3));
                System.out.println(maxColor);

                bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                while ((linia = p3.readLine()) != null) {
                    st1 = new StringTokenizer(linia);
                    while (st1.hasMoreTokens()) {
                        czytnik++;
                        if (czytnik == 1) {
                            red = Integer.parseInt(st1.nextToken());
                            red = (int) red * (255 / maxColor);
                            redH[red]++;
                        } else if (czytnik == 2) {
                            green = Integer.parseInt(st1.nextToken());
                            green = (int) green * (255 / maxColor);
                            greenH[green]++;
                        } else {
                            blue = Integer.parseInt(st1.nextToken());
                            blue = (int) blue * (255 / maxColor);
                            blueH[blue]++;

                            bi.setRGB(poz, pion, new Color(red, green, blue).getRGB());

                            poz++;
                            czytnik = 0;
                            if (poz == width) {
                                poz = 0;
                                pion++;
                            }
                        }
                    }
                }
                p3.close();
            } else if (typ.equals("P6")) {
                System.out.println(typ);
                info.get(p6);
                width = Integer.parseInt(info.get(p6));
                System.out.println(width);
                height = Integer.parseInt(info.get(p6));
                System.out.println(height);
                maxColor = Integer.parseInt(info.get(p6));
                System.out.println(maxColor);

                bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                if (maxColor <= 255) {
                    while ((red = p6.read()) != -1) {
                        redH[red]++;
                        green = p6.read();
                        greenH[green]++;
                        blue = p6.read();
                        blueH[blue]++;

                        Color color = new Color(red * 255 / maxColor, green * 255 / maxColor, blue * 255 / maxColor);

                        bi.setRGB(poz, pion, color.getRGB());

                        poz++;
                        if (poz == width) {
                            poz = 0;
                            pion++;
                        }
                    }
                    p6.close();
                } else if (maxColor > 255 && maxColor <= 65536) {
                    System.out.println(typ);
                    while ((red = p6.read()) != -1) {
                        pomR = p6.read();
                        red = (256 * red + pomR) / maxColor;
                        redH[red]++;

                        green = p6.read();
                        pomG = p6.read();
                        green = (256 * green + pomG) / maxColor;
                        greenH[green]++;

                        blue = p6.read();
                        pomB = p6.read();
                        blue = (256 * blue + pomB) / maxColor;
                        blueH[blue]++;

                        Color color = new Color(red * 255 / maxColor, green * 255 / maxColor, blue * 255 / maxColor);

                        bi.setRGB(poz, pion, color.getRGB());

                        poz++;
                        if (poz == width) {
                            poz = 0;
                            pion++;
                        }
                    }
                }
                p6.close();
            } else {
                obraz = ImageIO.read(plik);
                System.out.println(plik);
                System.out.println(obraz.getWidth() + " " + obraz.getHeight() + " ");
                bw.println("P3");
                bw.println(obraz.getWidth());
                bw.println(obraz.getHeight());
                bw.println(255);

                bi = new BufferedImage(obraz.getWidth(), obraz.getHeight(), BufferedImage.TYPE_INT_RGB);
                for (int l = 0; l < obraz.getWidth(); l++) {
                    for (int k = 0; k < obraz.getHeight(); k++) {
                        Color color = new Color(obraz.getRGB(l, k));
                        r = color.getRed();
                        redH[r]++;
                        bw.print(r + " ");
                        g = color.getGreen();
                        greenH[g]++;
                        bw.print(g + " ");
                        b = color.getBlue();
                        blueH[b]++;
                        bw.println(b + " ");
                        bi.setRGB(l, k, obraz.getRGB(l, k));
                    }

                }
                bw.close();
            }
            panelu.set_stan(true);
            panelu.set_panel(otworz.bi, otworz.redH, otworz.greenH, otworz.blueH);

        } catch (FileNotFoundException e) {
            System.out.println("Nie znalazłem pliku " + e);
        } catch (NullPointerException e) {
            System.out.println("nie pasują mi dane w pliku " + e);
        } catch (NumberFormatException e) {
            System.out.print("pamietaj-4 linijki info-reszta piksele. ");
            System.out.println("coś popsuleś znów " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Plik uszkodzony" + e);
        }
        return obraz;
    }
}


