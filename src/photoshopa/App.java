package photoshopa;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;




public class App extends JFrame implements ActionListener{

	DisplayPanel displayPanel,tmp;
	JButton brightenButton, darkenButton, contrastIncButton, contrastDecButton,
        reverseButton, resetButton, czarnobialyButton, equalizeButton, stretchButton, histogramButton, blurButton, stampButton,
        sharpButton,horizontalFilterButton, verticalFilterButton, rightDiagonalFilterButton, 
                leftDiagonalFilterButton, otsuButton, medianowaButton, splotowaButton, progowanieButton,
                dylacjaButton, erozjaButton, obrotButton, skalowanieButton;
	JMenuBar myBar;
	JMenu myMenu;
        int ot = 0;
        float skala = 0;
        int windowHeight = 700;
        JSlider skalowanieSlider;
        JPanel panel;

	
	public App()
	{
            super();
            setTitle("Photoshop");
		
	    Container container = getContentPane();

	    displayPanel = new DisplayPanel();
	    container.add(displayPanel);
	    
	    panel = new JPanel();
            JPanel panel2 = new JPanel();
	 
	    panel.setBorder(new TitledBorder("Opcje"));
	    panel.setPreferredSize(new Dimension(200,600));
            
	    panel2.setBorder(new TitledBorder("Opcje"));
	    panel2.setPreferredSize(new Dimension(200,600));
	    
	    JToolBar rozjasnienie = new JToolBar();
	    
	    rozjasnienie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Rozjaśnienie"));
	    rozjasnienie.setPreferredSize(new Dimension(150,60));
	    rozjasnienie.setLayout(new GridLayout(0,2));

	

	    ImageIcon iconPlus = new ImageIcon("plus.png");
	    ImageIcon iconMinus = new ImageIcon("minus.png");
	    ImageIcon iconNegatyw = new ImageIcon("negatyw.png");
	    ImageIcon iconCzern = new ImageIcon("black.png");
	    ImageIcon iconClear = new ImageIcon("clear.png");
	    ImageIcon iconEqualize = new ImageIcon("equalize.png");
	    ImageIcon iconHistogram = new ImageIcon("histogram.png");
	    ImageIcon iconBlur = new ImageIcon("blur.png");
	    ImageIcon iconStamp = new ImageIcon("stamp.png");
	    ImageIcon iconSharp = new ImageIcon("sharp.png");
	    ImageIcon iconHorizontalFilter = new ImageIcon("horizontal.png");
	    ImageIcon iconVerticalFilter = new ImageIcon("vertical.png");
	    ImageIcon iconRightDiagonalFilter = new ImageIcon("rightdiagonal.png");
	    ImageIcon iconLeftDiagonalFilter = new ImageIcon("leftdiagonal.png");
	    ImageIcon iconOtsu = new ImageIcon("otsu.png");
            ImageIcon iconMedianowa = new ImageIcon("medianowa.png");
            ImageIcon iconSplotowa = new ImageIcon("splotowa.png");
            ImageIcon iconProgowanie = new ImageIcon("progowanie.png");
            ImageIcon iconDylacja = new ImageIcon("dylacja.png");
            ImageIcon iconErozja = new ImageIcon("erozja.png");
            ImageIcon iconObrot = new ImageIcon("obrot.png");
            ImageIcon iconSkalowanie = new ImageIcon("skalowanie.png");
            
	    brightenButton = new JButton(iconPlus);
	    brightenButton.addActionListener(new ButtonListener());
	    brightenButton.setBorder(new EmptyBorder(3, 3, 3, 3));
	    
	    darkenButton = new JButton(iconMinus);
	    darkenButton.addActionListener(new ButtonListener());
	    darkenButton.setBorder(new EmptyBorder(3, 3, 3, 3));
	    
	    contrastIncButton = new JButton(iconPlus);
	    contrastIncButton.addActionListener(new ButtonListener());
	    contrastIncButton.setBorder(new EmptyBorder(3, 3, 3, 3));
	    
	    contrastDecButton = new JButton(iconMinus);
	    contrastDecButton.addActionListener(new ButtonListener());
	    contrastDecButton.setBorder(new EmptyBorder(3, 3, 3, 3));
	    
	    reverseButton = new JButton(iconNegatyw);
	    reverseButton.addActionListener(new ButtonListener());
	    reverseButton.setBorder(new EmptyBorder(3, 3, 3, 3));
	    
	    resetButton = new JButton(iconClear);
	    resetButton.addActionListener(new ButtonListener());
	    resetButton.setBorder(new EmptyBorder(3,3,3,3));
	    
	    czarnobialyButton = new JButton(iconCzern);
	    czarnobialyButton.addActionListener(new ButtonListener());
	    czarnobialyButton.setBorder(new EmptyBorder(3,3,3,3));
	    
	    equalizeButton = new JButton(iconEqualize);
	    equalizeButton.addActionListener(new ButtonListener());
	    equalizeButton.setBorder(new EmptyBorder(3,3,3,3));
	    equalizeButton.setToolTipText("Wrównanie histogramu");
	    
	    histogramButton = new JButton(iconHistogram);
	    histogramButton.addActionListener(new ButtonListener());
	    histogramButton.setBorder(new EmptyBorder(3,3,3,3));
	    histogramButton.setToolTipText("Pokazanie histogramu obrazu");
	    
	    blurButton = new JButton(iconBlur);
	    blurButton.addActionListener(new ButtonListener());
	    blurButton.setBorder(new EmptyBorder(3,3,3,3));
	    blurButton.setToolTipText("Rozmycie");
	    
	    stampButton = new JButton(iconStamp);
	    stampButton.addActionListener(new ButtonListener());
	    stampButton.setBorder(new EmptyBorder(3,3,3,3));
	    stampButton.setToolTipText("Wytłaczanie");
	    
	    sharpButton = new JButton(iconSharp);
	    sharpButton.addActionListener(new ButtonListener());
	    sharpButton.setBorder(new EmptyBorder(3,3,3,3));
	    sharpButton.setToolTipText("Wyostrzanie");
	    
	    horizontalFilterButton = new JButton(iconHorizontalFilter);
	    horizontalFilterButton.addActionListener(new ButtonListener());
	    horizontalFilterButton.setBorder(new EmptyBorder(3,3,3,3));
	    horizontalFilterButton.setToolTipText("Wykrywanie krawędzi poziomych");
	    
	    verticalFilterButton = new JButton(iconVerticalFilter);
	    verticalFilterButton.addActionListener(new ButtonListener());
	    verticalFilterButton.setBorder(new EmptyBorder(3,3,3,3));
	    verticalFilterButton.setToolTipText("Wykrywanie krawędzi pionowych");
	    
	    leftDiagonalFilterButton = new JButton(iconLeftDiagonalFilter);
	    leftDiagonalFilterButton.addActionListener(new ButtonListener());
	    leftDiagonalFilterButton.setBorder(new EmptyBorder(3,3,3,3));
	    leftDiagonalFilterButton.setToolTipText("Wykrywanie krawędzi lewej przekątnej");
	    
	    rightDiagonalFilterButton= new JButton(iconRightDiagonalFilter);
	    rightDiagonalFilterButton.addActionListener(new ButtonListener());
	    rightDiagonalFilterButton.setBorder(new EmptyBorder(3,3,3,3));
	    rightDiagonalFilterButton.setToolTipText("Wykrywanie krawędzi prawej przekątnej");
            
            otsuButton = new JButton(iconOtsu);
	    otsuButton.addActionListener(new ButtonListener());
	    otsuButton.setBorder(new EmptyBorder(3, 3, 3, 3));
            otsuButton.setToolTipText("Otsu");
            
            progowanieButton = new JButton(iconProgowanie);
	    progowanieButton.addActionListener(new ButtonListener());
	    progowanieButton.setBorder(new EmptyBorder(3, 3, 3, 3));
            progowanieButton.setToolTipText("Progowanie");
            
            medianowaButton = new JButton(iconMedianowa);
	    medianowaButton.addActionListener(new ButtonListener());
	    medianowaButton.setBorder(new EmptyBorder(3,3,3,3));
	    medianowaButton.setToolTipText("Filtracja medianowa");
	    
            splotowaButton = new JButton(iconSplotowa);
	    splotowaButton.addActionListener(new ButtonListener());
	    splotowaButton.setBorder(new EmptyBorder(3,3,3,3));
	    splotowaButton.setToolTipText("Filtracja splotowa");
            
            dylacjaButton = new JButton(iconDylacja);
	    dylacjaButton.addActionListener(new ButtonListener());
	    dylacjaButton.setBorder(new EmptyBorder(3, 3, 3, 3));
            dylacjaButton.setToolTipText("Dylacja");
            
            erozjaButton = new JButton(iconErozja);
	    erozjaButton.addActionListener(new ButtonListener());
	    erozjaButton.setBorder(new EmptyBorder(3, 3, 3, 3));
            erozjaButton.setToolTipText("Erozja");
            
            obrotButton = new JButton(iconObrot);
	    obrotButton.addActionListener(new ButtonListener());
	    obrotButton.setBorder(new EmptyBorder(3, 3, 3, 3));
            obrotButton.setToolTipText("Obróć zdjęcie");
            
            skalowanieButton = new JButton(iconSkalowanie);
	    skalowanieButton.addActionListener(new ButtonListener());
	    skalowanieButton.setBorder(new EmptyBorder(3, 3, 3, 3));
            skalowanieButton.setToolTipText("Powiększ zdjęcie");
            
	    rozjasnienie.add(brightenButton);
	    rozjasnienie.add(darkenButton);
	    
	    panel.add(rozjasnienie);
	    
	    JToolBar kontrast = new JToolBar();
	    
	    kontrast.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Kontrast"));
	    kontrast.setPreferredSize(new Dimension(150,60));
	    kontrast.setLayout(new GridLayout(0,2));
	    
	    
	    kontrast.add(contrastIncButton);
	    kontrast.add(contrastDecButton);
	    
	    panel.add(kontrast,BorderLayout.SOUTH);

	    
	    
	    
	    JToolBar efekt = new JToolBar();
	    efekt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Efekt"));
	    efekt.setPreferredSize(new Dimension(150,60));
	    efekt.setLayout(new GridLayout(0,2));
	    
	    efekt.add(reverseButton);
            efekt.add(czarnobialyButton);
	    panel.add(efekt,BorderLayout.SOUTH);
            
	    
	    JToolBar histogramBar = new JToolBar();
	    histogramBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Histogram"));
	    histogramBar.setPreferredSize(new Dimension(150,60));
	    histogramBar.setLayout(new GridLayout(0,2));
	    
	    histogramBar.add(equalizeButton);
	    histogramBar.add(histogramButton);
	    panel.add(histogramBar,BorderLayout.SOUTH);
            
            JToolBar filtracjaBar = new JToolBar();
	    filtracjaBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Filtracja"));
	    filtracjaBar.setPreferredSize(new Dimension(150,80));
	    filtracjaBar.setLayout(new GridLayout(2,1));
            filtracjaBar.add(medianowaButton);
            filtracjaBar.add(splotowaButton);
            panel2.add(filtracjaBar,BorderLayout.SOUTH);
            
            JToolBar progowanie = new JToolBar();
	    progowanie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Progowanie"));
	    progowanie.setPreferredSize(new Dimension(150,80));
            otsuButton.addActionListener(new ButtonListener());
	    progowanie.setLayout(new GridLayout(2,1));
            otsuButton.setToolTipText("Otsu");
            progowanie.add(otsuButton);
            progowanie.add(progowanieButton);
            panel2.add(progowanie,BorderLayout.SOUTH);
            
            JToolBar morfologia = new JToolBar();
	    morfologia.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Operacje morfologiczne"));
	    morfologia.setPreferredSize(new Dimension(150,80));
	    morfologia.setLayout(new GridLayout(2,1));
	    
	    morfologia.add(dylacjaButton);
            morfologia.add(erozjaButton);
	    panel2.add(morfologia,BorderLayout.SOUTH);
            
	    
	    JToolBar filtryBar = new JToolBar();
	    filtryBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Filtry"));
	    filtryBar.setPreferredSize(new Dimension(150,60));
	    filtryBar.setLayout(new GridLayout(0,3));
	    
	    filtryBar.add(blurButton);
	    filtryBar.add(stampButton);
	    filtryBar.add(sharpButton);
	    filtryBar.add(horizontalFilterButton);
	    filtryBar.add(verticalFilterButton);
	    panel.add(filtryBar,BorderLayout.SOUTH);
	    
	    JToolBar wykrywanieBar = new JToolBar();
	    wykrywanieBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Wykrywanie krawędzi"));
	    wykrywanieBar.setPreferredSize(new Dimension(150,60));
	    wykrywanieBar.setLayout(new GridLayout(0,5));
	    
	    wykrywanieBar.add(horizontalFilterButton);
	    wykrywanieBar.add(verticalFilterButton);
	    wykrywanieBar.add(leftDiagonalFilterButton);
	    wykrywanieBar.add(rightDiagonalFilterButton);
	    panel.add(wykrywanieBar,BorderLayout.SOUTH);
            
            JToolBar obrot = new JToolBar();
	    obrot.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Obrót"));
	    obrot.setPreferredSize(new Dimension(150,60));
	    obrot.setLayout(new GridLayout(0,1));
	    obrot.add(obrotButton);
	    panel.add(obrot,BorderLayout.SOUTH);
            
            JToolBar skalowanie = new JToolBar();
	    skalowanie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Powiększ/Pomniejsz"));
	    skalowanie.setPreferredSize(new Dimension(150,120));
	    skalowanie.setLayout(new GridLayout(0,1));
            skalowanieSlider = new JSlider(-2, 2, 0);
            skalowanieSlider.setMajorTickSpacing(1);
            skalowanieSlider.setPaintLabels(true);
            skalowanie.add(skalowanieSlider);
	    skalowanie.add(skalowanieButton);
	    panel.add(skalowanie,BorderLayout.SOUTH);
	    
	    JToolBar resetBar = new JToolBar();
	    resetBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Reset"));
	    resetBar.setPreferredSize(new Dimension(150,60));
	    resetBar.setLayout(new GridLayout(0,1)); 
	    resetBar.add(resetButton);
	    panel2.add(resetBar,BorderLayout.SOUTH);
	    
	    
	    
	    container.add(BorderLayout.WEST, panel);
            container.add(BorderLayout.EAST, panel2);
//jmenu
        JMenu menu = new JMenu("Plik");
        menu.add(makeMenuItem("Otwórz"));
        menu.add(makeMenuItem("Zapisz"));
        menu.add(makeMenuItem("Wyjdź"));
	    
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);
	    
	    addWindowListener(new WindowEventHandler());
	    setSize(displayPanel.getWidth(), displayPanel.getHeight() + 25);
	    show();
	    
	}
	
    private JMenuItem makeMenuItem(String name) {
        JMenuItem m = new JMenuItem(name);
        m.addActionListener(this);
        return m;
    }
    
	public static void main(String[] args) {
		new App();

	}
	
	  class ButtonListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		      JButton button = (JButton) e.getSource();

		      if (button.equals(brightenButton)) {
		        displayPanel.brightenLUT();
		        displayPanel.applyFilter();
		        displayPanel.repaint();
		      } else if (button.equals(darkenButton)) {
		        displayPanel.darkenLUT();
		        displayPanel.applyFilter();
		        displayPanel.repaint();
		      } else if (button.equals(contrastIncButton)) {
		        displayPanel.contrastIncLUT();
		        displayPanel.applyFilter();
		        displayPanel.repaint();
		      } else if (button.equals(contrastDecButton)) {
		        displayPanel.contrastDecLUT();
		        displayPanel.applyFilter();
		        displayPanel.repaint();
		      } else if (button.equals(reverseButton)) {
		        displayPanel.reverseLUT();
		        displayPanel.applyFilter();
		        displayPanel.repaint();
		      } else if (button.equals(resetButton)) {
		        displayPanel.reset();
		        displayPanel.repaint();
		      } else if(button.equals(czarnobialyButton)){
		    	  displayPanel.blackAndWhite();
		    	  displayPanel.repaint();
		      }else if(button.equals(equalizeButton)){
		    	  displayPanel.wyrHist();
		    	  displayPanel.repaint();
		      }else if(button.equals(histogramButton)){
		    	  final JFrame hist= new JFrame("Histogram"); 
		    	     hist.setSize(600, 600);
		    	     hist.setBounds(1000, 0, 260, 340);
		    	     hist.setBackground(Color.black);
		    	     displayPanel.odcieniePikseli(displayPanel.bi); 
   		    	  HistogramP nowy=new HistogramP(displayPanel.histogram[0],displayPanel.histogram[1],displayPanel.histogram[2]);
   		    	  hist.add(nowy);
   		    	  hist.setVisible(true);
		      } else if (button.equals(otsuButton)) {
                        displayPanel.otsu(127);
                      } else if (button.equals(progowanieButton)) {
		        displayPanel.progowanie();
		        displayPanel.repaint();
                      }else if(button.equals(medianowaButton)){
		    	  displayPanel.medianowy();
		    	  displayPanel.repaint();
                      }else if(button.equals(splotowaButton)){
		    	  displayPanel.splotowy();
		    	  displayPanel.repaint();
		      }else if(button.equals(blurButton)){
		    	  displayPanel.Rozmycie();
		      }else if(button.equals(stampButton)){
		    	  displayPanel.Wytlaczanie();
		      }else if(button.equals(sharpButton)){
		    	  displayPanel.Wyostrzanie();
		      }else  if(button.equals(horizontalFilterButton)){
		    	  displayPanel.KrawedzPoziome();
		      }else if(button.equals(verticalFilterButton)){
		    	  displayPanel.KrawedzPionowe();
		      }else if (button.equals(leftDiagonalFilterButton)){
		    	  displayPanel.KrawedzLewaPrzekatna();
		      }else if (button.equals(rightDiagonalFilterButton)){
		    	  displayPanel.KrawedzPrawaPrzekatna();
                      }else if (button.equals(dylacjaButton)){
		    	  displayPanel.dylacja(1);
                          displayPanel.repaint();
		      }else if (button.equals(erozjaButton)){
		    	  displayPanel.erozja(1);
                          displayPanel.repaint();
		      }else if (button.equals(obrotButton)){
		    	  displayPanel.obrot();
                          displayPanel.repaint();
                      }else if (button.equals(skalowanieButton)){


                          skala=skalowanieSlider.getValue();

                          if(skala > 0)
                          {
                              skala *= 2;
                          }
                          if(skala < 0)
                          {
                              skala = -skala;
                              skala = 0.5f/skala;
                          }


		    	  displayPanel.skalowanie(skala);
                          displayPanel.repaint();
                          displayPanel.revalidate();
                      }
		      
		    }
		  }
	
	class WindowEventHandler extends WindowAdapter {
	    public void windowClosing(WindowEvent e) {
	      System.exit(0);
	    }
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		 String command = e.getActionCommand();
		 if (command.equals("Wyjdź")) {
	            System.exit(0);
	        } else if (command.equals("Otwórz")) {
	            
	        	JFileChooser chooser = new JFileChooser();
	        	chooser.setCurrentDirectory(new File("./textures"));
	    		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG","jpg");
	    	        chooser.setFileFilter(filter);
	        	chooser.showOpenDialog(new JFrame());

	            File Plik= new File(chooser.getSelectedFile().getAbsolutePath());

				if(Plik!=null)
                                {
					displayPanel.loadImage(Plik);
                                        displayPanel.revalidate();
                                }
	            
	        } else if (command.equals("Zapisz")) {
	              	JFileChooser chooser = new JFileChooser();
	        	chooser.setCurrentDirectory(new File("./textures"));
	    		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG","jpg");
	    	        chooser.setFileFilter(filter);
	        	chooser.showSaveDialog(new JFrame());

	            File Plik= new File(chooser.getSelectedFile().getAbsolutePath()+".jpg");

				if(Plik!=null)
                                displayPanel.saveImage(Plik);
	        }
	       
	}


}
