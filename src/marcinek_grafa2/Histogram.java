package marcinek_grafa2;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Histogram extends JPanel {

    public final static int RED_HISTOGRAM = 0;
    public final static int GREEN_HISTOGRAM = 1;
    public final static int BLUE_HISTOGRAM = 2;
    public final static int AVG_HISTOGRAM = 3;
    public final static int padding = 20;
    private int height;
    private Color color;
    private Color bgColor;
    private int wartosci[] = null;
    private BufferedImage graph = null;
    private int max = -1;

    public Histogram(BufferedImage img, int type, int height, Color color, Color bgColor) {
        this.height = height;
        this.color = color;
        this.bgColor = bgColor;

        this.wartosci = new int[256];
        java.util.Arrays.fill(this.wartosci, 0);

        int imgH = img.getHeight();
        int imgW = img.getWidth();

        if (type == Histogram.RED_HISTOGRAM) {
            for (int h = 0; h < imgH; h++) {
                for (int w = 0; w < imgW; w++) {
                    int tmp = new Color(img.getRGB(w, h)).getRed();

                   if (tmp > this.max) {
                        this.max = tmp;
                    }

                    this.wartosci[tmp]++;
                }
            }
        } else if (type == Histogram.GREEN_HISTOGRAM) {
            for (int h = 0; h < imgH; h++) {
                for (int w = 0; w < imgW; w++) {
                    int tmp = new Color(img.getRGB(w, h)).getGreen();

                    if (tmp > this.max) {
                        this.max = tmp;
                    }

                    this.wartosci[tmp]++;
                }
            }
        } else if (type == Histogram.BLUE_HISTOGRAM) {
            for (int h = 0; h < imgH; h++) {
                for (int w = 0; w < imgW; w++) {
                    int tmp = new Color(img.getRGB(w, h)).getBlue();

                    if (tmp > this.max) {
                        this.max = tmp;
                    }

                    this.wartosci[tmp]++;
                }
            }
        } else {
            for (int h = 0; h < imgH; h++) {
                for (int w = 0; w < imgW; w++) {
                    Color tmpCol = new Color(img.getRGB(w, h));
                    int tmp = Math.round(((float) (tmpCol.getRed() + tmpCol.getGreen() + tmpCol.getBlue())) / 3);

                    if (tmp > this.max) {
                        this.max = tmp;
                    }

                    this.wartosci[tmp]++;
                }
            }
        }

        this.Generate();
    }

    public Histogram(int wartosci[], int height, Color color, Color bgColor) {
        this.wartosci = wartosci;
        this.height = height;

        this.color = color;
        this.bgColor = bgColor;

        this.Generate();
        repaint();
    }

    private int GetMax() {
        int max = 0;

        for (int i = 0; i < this.wartosci.length; i++) {
            if (max < this.wartosci[i]) {
                max = this.wartosci[i];
            }
        }

        return max;
    }

    public void setWartosci(int[] wartosci) {
        this.wartosci = wartosci;

        this.Generate();
    }

//  imageheight - GetMax
//  lineheight  - rgbilosc
//
//  (imageheigh * rgbilosc)/ GetMax = lineh
//
    public void Generate() {
        if (this.wartosci != null) {
            int w = (Histogram.padding * 2 + this.wartosci.length * 1);
            int h = (this.height - 10);

            this.setBounds(0, 0, w, h);

            this.graph = new BufferedImage(w, h + 50, BufferedImage.TYPE_INT_RGB);


            long max;
//            if (this.max < 0) {
                max = this.GetMax();
//            } else {
//                max = this.max;
//            }

            max += 10;

            Graphics2D graph = (Graphics2D) this.graph.getGraphics();

            graph.setColor(this.bgColor);
            graph.fillRect(0, 0, w, h);

            graph.setColor(this.color);

            for (int i = 0; i < this.wartosci.length; i++) {
                //graph.drawLine(Histogram.padding+i, Histogram.padding, Histogram.padding+i, (int) ((h * this.wartosci[i]) / max));

                int y2 = 0;
                try {
                    y2 = h - ((int) ((double) ((h - Histogram.padding * 2) * this.wartosci[i]) / max));
                } catch (ArithmeticException e) {
                    y2 = h;
                }

                graph.drawLine(Histogram.padding + i, h - Histogram.padding, Histogram.padding + i, y2 - Histogram.padding);
            }

            graph.setColor(Color.BLACK);

            graph.drawString(""+max, 0 + Histogram.padding+2, Histogram.padding+4);
            graph.drawLine(Histogram.padding-1, 0 + Histogram.padding, Histogram.padding-1, h - Histogram.padding);
            graph.drawLine(0 + Histogram.padding, h - Histogram.padding+1, w - Histogram.padding, h - Histogram.padding+1);

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (this.graph != null) {
            g2d.drawImage(this.graph, null, 0, 0);
        }
    }
}
