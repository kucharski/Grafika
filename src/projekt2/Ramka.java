package projekt2;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.LookupOp;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class Ramka extends javax.swing.JFrame {
    private BufferedImage obraz, obraz2;
    private Point loc = new Point(0,0);
    private Histogramy histogramy;
   int cossss;

    public Ramka() {
        initComponents();
       // histogramy = new Histogramy(obraz, 0);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new panelu();
        treshold = new javax.swing.JSlider();
        Plik = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Menu_Otwórz_ = new javax.swing.JMenuItem();
        Zapisywanie_ = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        Zapamietaj_2_obrazek_ = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        SkalaSzarosci_1_ = new javax.swing.JMenuItem();
        SkalaSzarosci_2_ = new javax.swing.JMenuItem();
        Normalizacja_ = new javax.swing.JMenuItem();
        Dodawanie_Odejmowanie_ = new javax.swing.JMenuItem();
        Mnozenie_ = new javax.swing.JMenuItem();
        Dzielenie_ = new javax.swing.JMenuItem();
        Dodawanie_obrazkow_ = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        Sobel_ = new javax.swing.JMenuItem();
        Prewit_ = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        Mean_ = new javax.swing.JMenuItem();
        Mediana_ = new javax.swing.JMenuItem();
        Gaussian_blur = new javax.swing.JMenuItem();
        Segmentaacjaa = new javax.swing.JMenu();
        Erozja_ = new javax.swing.JMenuItem();
        Dylacja_ = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple IrfanView");
        setName("Ramka"); // NOI18N

        treshold.setMaximum(255);
        treshold.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tresholdStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, treshold, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(366, Short.MAX_VALUE)
                .add(treshold, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("Plik");

        Menu_Otwórz_.setText("Otwórz...");
        Menu_Otwórz_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Otwórz_ActionPerformed(evt);
            }
        });
        jMenu1.add(Menu_Otwórz_);

        Zapisywanie_.setText("Zapisz...");
        Zapisywanie_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Zapisywanie_ActionPerformed(evt);
            }
        });
        jMenu1.add(Zapisywanie_);
        jMenu1.add(jSeparator1);

        Zapamietaj_2_obrazek_.setText("Wyjdz");
        Zapamietaj_2_obrazek_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Zapamietaj_2_obrazek_ActionPerformed(evt);
            }
        });
        jMenu1.add(Zapamietaj_2_obrazek_);

        Plik.add(jMenu1);

        jMenu4.setText("Edycja");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Oryginal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        Plik.add(jMenu4);

        jMenu2.setText("Obraz");

        SkalaSzarosci_1_.setText("Skala szarości [ 1 ]");
        SkalaSzarosci_1_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkalaSzarosci_1_ActionPerformed(evt);
            }
        });
        jMenu2.add(SkalaSzarosci_1_);

        SkalaSzarosci_2_.setText("Skala szarości [ 2 ]");
        SkalaSzarosci_2_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkalaSzarosci_2_ActionPerformed(evt);
            }
        });
        jMenu2.add(SkalaSzarosci_2_);

        Normalizacja_.setText("Normalizacja");
        Normalizacja_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Normalizacja_ActionPerformed(evt);
            }
        });
        jMenu2.add(Normalizacja_);

        Dodawanie_Odejmowanie_.setText("Dodawanie/odejmowanie");
        Dodawanie_Odejmowanie_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dodawanie_Odejmowanie_ActionPerformed(evt);
            }
        });
        jMenu2.add(Dodawanie_Odejmowanie_);

        Mnozenie_.setText("Mnozenie");
        Mnozenie_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mnozenie_ActionPerformed(evt);
            }
        });
        jMenu2.add(Mnozenie_);

        Dzielenie_.setText("Dzielenie");
        Dzielenie_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dzielenie_ActionPerformed(evt);
            }
        });
        jMenu2.add(Dzielenie_);

        Dodawanie_obrazkow_.setText("Dodawanie 2 obrazków");
        Dodawanie_obrazkow_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dodawanie_obrazkow_ActionPerformed(evt);
            }
        });
        jMenu2.add(Dodawanie_obrazkow_);

        jMenuItem2.setText("Negatyw");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        Plik.add(jMenu2);

        jMenu3.setText("Filtry");

        Sobel_.setText("Sobel Edge Detection");
        Sobel_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sobel_ActionPerformed(evt);
            }
        });
        jMenu3.add(Sobel_);

        Prewit_.setText("Prewit Edge Detection");
        Prewit_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Prewit_ActionPerformed(evt);
            }
        });
        jMenu3.add(Prewit_);

        jMenuItem3.setText("Laplace");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        Mean_.setText("Mean");
        Mean_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mean_ActionPerformed(evt);
            }
        });
        jMenu3.add(Mean_);

        Mediana_.setText("Mediana");
        Mediana_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mediana_ActionPerformed(evt);
            }
        });
        jMenu3.add(Mediana_);

        Gaussian_blur.setText("Gaussian Blur");
        Gaussian_blur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gaussian_blurActionPerformed(evt);
            }
        });
        jMenu3.add(Gaussian_blur);

        Plik.add(jMenu3);

        Segmentaacjaa.setText("Morfologiczne");
        Segmentaacjaa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SegmentaacjaaMouseClicked(evt);
            }
        });

        Erozja_.setText("Erozja");
        Erozja_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Erozja_ActionPerformed(evt);
            }
        });
        Segmentaacjaa.add(Erozja_);

        Dylacja_.setText("Dylatacja");
        Dylacja_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dylacja_ActionPerformed(evt);
            }
        });
        Segmentaacjaa.add(Dylacja_);

        Plik.add(Segmentaacjaa);

        setJMenuBar(Plik);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Menu_Otwórz_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Otwórz_ActionPerformed
            try {
                obraz2 = otworz.obrazZPliku();
            } catch (IOException ex) {
             System.out.println("Błąd z Open.obrazZPliku() "+ex);        }
            jPanel1.repaint();
}//GEN-LAST:event_Menu_Otwórz_ActionPerformed

    private void Zapisywanie_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Zapisywanie_ActionPerformed
      //  MyPanel.zapisz(MyPanel.ten_namalowany);
        panelu.zapamietaj_obrazek1(panelu.ten_namalowany);
    }//GEN-LAST:event_Zapisywanie_ActionPerformed

    private void Zapamietaj_2_obrazek_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Zapamietaj_2_obrazek_ActionPerformed
         panelu.zapamietaj_obrazek2(panelu.ten_namalowany);
    }//GEN-LAST:event_Zapamietaj_2_obrazek_ActionPerformed

    private void Dodawanie_obrazkow_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dodawanie_obrazkow_ActionPerformed
        panelu.dodaj_2_obrazki(panelu.zapamietany1, panelu.zapamietany2);
        jPanel1.repaint();
}//GEN-LAST:event_Dodawanie_obrazkow_ActionPerformed

    private void Dzielenie_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dzielenie_ActionPerformed
        panelu.dzielenie(panelu.ten_namalowany);
        jPanel1.repaint();
}//GEN-LAST:event_Dzielenie_ActionPerformed

    private void Mnozenie_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mnozenie_ActionPerformed
        panelu.mnozenie(panelu.ten_namalowany);
        jPanel1.repaint();
}//GEN-LAST:event_Mnozenie_ActionPerformed

    private void Dodawanie_Odejmowanie_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dodawanie_Odejmowanie_ActionPerformed
        panelu.dodawanie_odejmowanie(panelu.ten_namalowany);
        jPanel1.repaint();
}//GEN-LAST:event_Dodawanie_Odejmowanie_ActionPerformed

    private void Normalizacja_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Normalizacja_ActionPerformed
        panelu.norma(panelu.ten_namalowany);
        jPanel1.repaint();
}//GEN-LAST:event_Normalizacja_ActionPerformed

    private void SkalaSzarosci_2_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkalaSzarosci_2_ActionPerformed
        panelu.skala_szarosci(2);
        jPanel1.repaint();
}//GEN-LAST:event_SkalaSzarosci_2_ActionPerformed

    private void SkalaSzarosci_1_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkalaSzarosci_1_ActionPerformed
        panelu.skala_szarosci(1);
        jPanel1.repaint();
}//GEN-LAST:event_SkalaSzarosci_1_ActionPerformed

    private void tresholdStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tresholdStateChanged
        int wartosc_tresholdu = Ramka.treshold.getValue();
        panelu.binaryzacja(panelu.zapamietany1, wartosc_tresholdu);
        jPanel1.repaint();
      //  op = new RescaleOp(1.1f, 0, null);


    }//GEN-LAST:event_tresholdStateChanged

    private void SegmentaacjaaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SegmentaacjaaMouseClicked
      // MyPanel.flood();
       jPanel1.repaint();
}//GEN-LAST:event_SegmentaacjaaMouseClicked

    private void Sobel_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sobel_ActionPerformed
        panelu.sobel();
        jPanel1.repaint();
}//GEN-LAST:event_Sobel_ActionPerformed

    private void Prewit_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Prewit_ActionPerformed
        panelu.prewit();
        jPanel1.repaint();
    }//GEN-LAST:event_Prewit_ActionPerformed

    private void Mean_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mean_ActionPerformed
        panelu.mean();
        jPanel1.repaint();
    }//GEN-LAST:event_Mean_ActionPerformed

    private void Mediana_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mediana_ActionPerformed
         panelu.mediana();
         jPanel1.repaint();
    }//GEN-LAST:event_Mediana_ActionPerformed

    private void Gaussian_blurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gaussian_blurActionPerformed
       panelu.GaussianBlur();
       jPanel1.repaint();
}//GEN-LAST:event_Gaussian_blurActionPerformed

    private void Erozja_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Erozja_ActionPerformed
panelu.ten_namalowany = new Erozja(panelu.ten_namalowany).run();
        //Erozja.erozja(panelu.ten_namalowany);
       jPanel1.repaint();
    }//GEN-LAST:event_Erozja_ActionPerformed

    private void Dylacja_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dylacja_ActionPerformed
        Dylatacja.dylatacja(panelu.ten_namalowany);
        jPanel1.repaint();
    }//GEN-LAST:event_Dylacja_ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        panelu.otw_oryg(panelu.ten_namalowany);
        jPanel1.repaint();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        panelu.ten_namalowany = new Laplace(panelu.ten_namalowany).run();
        jPanel1.repaint();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ramka().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Dodawanie_Odejmowanie_;
    private javax.swing.JMenuItem Dodawanie_obrazkow_;
    private javax.swing.JMenuItem Dylacja_;
    private javax.swing.JMenuItem Dzielenie_;
    private javax.swing.JMenuItem Erozja_;
    private javax.swing.JMenuItem Gaussian_blur;
    private javax.swing.JMenuItem Mean_;
    private javax.swing.JMenuItem Mediana_;
    private javax.swing.JMenuItem Menu_Otwórz_;
    private javax.swing.JMenuItem Mnozenie_;
    private javax.swing.JMenuItem Normalizacja_;
    private javax.swing.JMenuBar Plik;
    private javax.swing.JMenuItem Prewit_;
    private javax.swing.JMenu Segmentaacjaa;
    private javax.swing.JMenuItem SkalaSzarosci_1_;
    private javax.swing.JMenuItem SkalaSzarosci_2_;
    private javax.swing.JMenuItem Sobel_;
    private javax.swing.JMenuItem Zapamietaj_2_obrazek_;
    private javax.swing.JMenuItem Zapisywanie_;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JSlider treshold;
    // End of variables declaration//GEN-END:variables

}
