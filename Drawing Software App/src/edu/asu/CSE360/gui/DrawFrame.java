/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.gui;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;  
import javax.swing.border.TitledBorder;

import edu.asu.CSE360.listener.DrawPanelTwoMouseListener;
import edu.asu.CSE360.listener.SelectColorListener;
import edu.asu.CSE360.listener.TabbedPaneListener;
import edu.asu.CSE360.listener.TabbedPaneMouseListener;
import edu.asu.CSE360.listener.TestButtonListener;
import edu.asu.CSE360.listener.TextAreaKeyLisener;
import edu.asu.CSE360.listener.TextOptionListener;
import edu.asu.CSE360.listener.ToolbarListener;


/**
 *
 * @author Adam
 */
public class DrawFrame extends JFrame {
    private static String[] colors = {"Choose A Color", "White", "Black", "Red", "Blue", "Light Gray"};
    public static int mouseClick = 1;
    JButton textButton1;
    JButton alignRightButton;
    JButton alignMiddleButton;
    JButton alignLeftButton;   
    JTextField textField1;
    JComboBox textSizeCB;
    JComboBox textFontCB;
    JComboBox textFormatCB;
    JComboBox textColorCB;
    JTextField tfWidthSize;
    JTextField tfHeightSize;
    JTextArea WidthSizeTA, HeightSizeTA, textAlignmentTA;
    
    Integer[] textSizeInt = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50};
    String[] textSizeStr = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30",
                            "31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50",};
    String[] textFontStr1 = {"Serif","Sans Serif","Monospaced"};
    String[] textFontStr2 = {"Font.SERIF","Font.SANS_SERIF","Font.MONOSPACED"};
    String[] textFormatStr1 = {"Plain","Bold","Italic"};
    String[] textFormatStr2 = {"Font.PLAIN","Font.BOLD","Font.ITALIC"};
    String[] textColorStr1 = {"Black","Red","Blue","Green"};
    Font font;

    int textFieldWidth = 75;
    int textFieldHeight = 20;
    int tfLocationX = 30;
    int tfLocationY = 30;
    int newClicked = 0;
        
    DrawPanelOne panelOne;    
    DrawPanelTwo panelTwo;
    
    public int returnNewClicked(){
        return this.newClicked;
    }
    
    private static DataFlavor[] dndFlavor;
    
    //Frame constructor
    public DrawFrame(){            
        super("Project 4");
        dndFlavor = new DataFlavor[5];
    	try {
    		dndFlavor[0] = new DataFlavor (DataFlavor.javaJVMLocalObjectMimeType+";class=edu.asu.CSE360.gui.IconCircle");
    		dndFlavor[1] = new DataFlavor (DataFlavor.javaJVMLocalObjectMimeType+";class=edu.asu.CSE360.gui.IconSquare");
    		dndFlavor[2] = new DataFlavor (DataFlavor.javaJVMLocalObjectMimeType+";class=edu.asu.CSE360.gui.IconConnection");
    		dndFlavor[3] = DataFlavor.stringFlavor;
    		dndFlavor[4] = DataFlavor.imageFlavor;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
        initGui();
    }
    
    public static DataFlavor[] getDataFlavor () {
    	return dndFlavor;
    }
    
    /**
     * for project 4
     */
    public void initGui() {
        try {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // top panel for 10 testing buttons
            JPanel testPanel = new JPanel (new GridLayout(1, 12));
            TestButtonListener tbListener = new TestButtonListener();
            JButton jb = new JButton ("Q1");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("Q2");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("Q3");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("Q4");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("Q5");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("Q6");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("Q7");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("Q8");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("Q9");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("Q10");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
            jb = new JButton ("   ");
            jb.setEnabled(false);
            testPanel.add(jb);
            jb = new JButton ("Eraser");
            jb.addActionListener(tbListener);
            testPanel.add(jb);
             
            this.getContentPane().add(testPanel,"North");
            
            //Create SplitPane for dividing option menu from drawing canvas
            MainPanel mPanel = new MainPanel(JSplitPane.HORIZONTAL_SPLIT);
            mPanel.setResizeWeight(0.5);
            this.getContentPane().add(mPanel,"Center");
            
            //	Create status panel for displaying score 
            StatusPanel sp = new StatusPanel (new FlowLayout(FlowLayout.LEFT)); 
            sp.setTestButtonPanel(testPanel);
            ImageIcon ic = new ImageIcon(getClass().getResource("sadFace.png"));
            sp.setSadFace(ic);
            ic = new ImageIcon(getClass().getResource("happyFace.png"));
            sp.setHapyFace(ic);
            this.getContentPane().add(sp, "South");
            
            // set left panel 
            panelOne = new DrawPanelOne();
            mPanel.setLeftComponent(panelOne);
            
            JPanel northPanel = new JPanel(new BorderLayout());
            panelOne.add(northPanel, "North");
            // add Shape Selection Panel
            JPanel shapePanel = getShapePanel();            
            northPanel.add(shapePanel, BorderLayout.CENTER);
            panelOne.setShapeSelectionPanel(shapePanel);
            
            
            
            
            // set icons for the shapePanel
            URL url = getClass().getResource("circle.png");
            IconCircle pic1;
            ImageIcon ii1 = new ImageIcon(getClass().getResource("circle.png"));
           	pic1 = new IconCircle(ii1);
            panelOne.addSelectionItem(pic1);
            IconSquare si;
            url = getClass().getResource("square.png");
           	ii1 = new ImageIcon(url);
           	si = new IconSquare(ii1);
            panelOne.addSelectionItem(si); 
            
            // set draw option panel
            DrawOptionsPanel doPanel = new DrawOptionsPanel ();
            shapePanel.add(doPanel.getRelationshipOptionsMenu());
            
            // set java code panel
            JavaCodeArea sf = new JavaCodeArea();
            panelOne.add(new JScrollPane(sf), BorderLayout.CENTER);
            sf.addKeyListener(new TextAreaKeyLisener ());
            tbListener.setJcArea(sf);
            sf.getObsObj().addObserver(sp);
            
            // set right panel
            DrawPanelTwo panelTwo = new DrawPanelTwo();
            panelTwo.setOptionsPanel(doPanel);
            panelTwo.addMouseListener(new DrawPanelTwoMouseListener(doPanel) );
            panelTwo.getObsObj().addObserver(sp);
            mPanel.setRightComponent(new JScrollPane(panelTwo) );
            tbListener.setdPanel(panelTwo);
            
        } catch (Exception ex) {
        	ex.printStackTrace();
            Logger.getLogger(DrawFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void initGui(String usedForProject2_3) {
        try {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);

            panelOne = new DrawPanelOne();
            //Create SplitPane for dividing option menu from drawing canvas
            MainPanel mPanel = new MainPanel(JSplitPane.HORIZONTAL_SPLIT);
           
            //Create tabbed pane for canvas
            DrawingTabbedPanel dtPanel = new DrawingTabbedPanel(JTabbedPane.BOTTOM);
            TabbedPaneListener testPanel = new TabbedPaneListener();
            dtPanel.addChangeListener(testPanel);
            dtPanel.addMouseListener(new TabbedPaneMouseListener());
            
            //Create toolbar
            ToolbarListener tbListener = new ToolbarListener(dtPanel);
            JToolBar tb = new JToolBar();
            add(tb, BorderLayout.PAGE_START);
            //Create buttons on toolbar
            JButton jb = new JButton("Delete Tab");
            tb.add(jb);
            jb.addActionListener(tbListener);
            jb = new JButton("Duplicate Tab");
            jb.addActionListener(tbListener);
            tb.add(jb);
            jb = new JButton("Delete Components");
            jb.addActionListener(tbListener);
            tb.add(jb);

            //Organize panels
            panelOne.setOpaque(true);
//            panelOne.setBackground(Color.white);
            
            this.getContentPane().add(mPanel,"Center");
            
            StatusPanel sp = new StatusPanel (new FlowLayout(FlowLayout.LEFT)); 
            this.getContentPane().add(sp, "South");
            
            mPanel.setTopComponent(panelOne);
            JPanel northPanel = new JPanel(new BorderLayout());
            panelOne.add(northPanel, BorderLayout.NORTH);
            JPanel tmpPanel = new JPanel (new GridLayout(2,1));
            
            // add Draw Option Panel
            DrawOptionsPanel dop = new DrawOptionsPanel();
            northPanel.add(tmpPanel, BorderLayout.NORTH);
            
            tmpPanel.add(dop);
            dtPanel.setOptionsPanel(dop);
            
            //	add Background Color Selection Panel 
            JPanel cPanel = getColorPanel(dtPanel);
            tmpPanel.add(cPanel); 
            
            // add Text Attributes Panel 
            JPanel textPanel = getTextAttributesPanel(dtPanel);
            northPanel.add(textPanel, BorderLayout.SOUTH);
            
            // add DrawTabbedPanel
            mPanel.setBottomComponent(dtPanel);            
            dtPanel.add("+",new JPanel());
            JavaCodeArea sf = new JavaCodeArea();
            panelOne.add(new JScrollPane(sf), BorderLayout.CENTER);
            ((DrawPanelTwo)dtPanel.getComponent(1)).getObsObj().addObserver(sf);
            ((DrawPanelTwo)dtPanel.getComponent(1)).getObsObj().addObserver(sp);
            
            // add Shape Selection Panel
            JPanel shapePanel = getShapePanel();
            northPanel.add(shapePanel, BorderLayout.CENTER);
            panelOne.setShapeSelectionPanel(shapePanel);
            URL url = getClass().getResource("circle.png");
            IconCircle pic1;
            ImageIcon ii1;
           	ii1 = new ImageIcon(url);
           	pic1 = new IconCircle(ii1);
            panelOne.addSelectionItem(pic1);
            IconSquare si;
            url = getClass().getResource("square.png");
           	ii1 = new ImageIcon(url);
           	si = new IconSquare(ii1);
            panelOne.addSelectionItem(si);                                   
        } catch (Exception ex) {
        	ex.printStackTrace();
            Logger.getLogger(DrawFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private JPanel getColorPanel(DrawingTabbedPanel dtPanel) {
        JPanel panelOne = new JPanel();        
        TitledBorder border = new TitledBorder("Misc Options:");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        panelOne.setBorder(border);
      
        panelOne.add(new JLabel ("Background Color: "));
        JComboBox<String> colorBox = new JComboBox<String>(colors);         
        colorBox.addItemListener(new SelectColorListener(dtPanel));
        panelOne.add(colorBox);
        
        
        //Creates JButton to capture screen, Image is saved wherever project folder is
        JButton screenCapture = new JButton("Export As JPG");
        screenCapture.setPreferredSize(new Dimension(115,50));
        screenCapture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    Robot robot = new Robot();
                    String fileName = "Screenshot.jpg";
                    Rectangle screenRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                    BufferedImage screen = robot.createScreenCapture(screenRectangle);
                    //BufferedImage screen = new Robot().createScreenCapture( new Rectangle( panelTwo.getX(), panelTwo.getY(),panelTwo.getWidth(), panelTwo.getHeight() ) );
                    ImageIO.write(screen, "jpg", new File(fileName));
                    JOptionPane.showMessageDialog(null, "Image saved to Project Folder!");
                } catch (AWTException | IOException ex){
                }
            }
        });
        panelOne.add(screenCapture);
        
        //Save current JFrame 
        JButton save = new JButton("Save");
        save.setPreferredSize(new Dimension(65,50));
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(new File("savedFrame"));
                fc.setDialogTitle("Save File");
                int result = fc.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                   
                    try {
                        FileWriter fileWriter = new FileWriter("");
                        fileWriter.write("Testing");
                        fileWriter.close();
                        JOptionPane.showMessageDialog(null, "File Saved");
                        System.out.println("Save Succesfull!");
                    } catch (Exception ex1) {
                        JOptionPane.showMessageDialog(null, ex1.getMessage());
                    }
                }                
            }
        });
        panelOne.add(save);
        
        //Open a previous JFrame
        JButton open = new JButton("Open");
        open.setPreferredSize(new Dimension(65,50));
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(new File(""));
                fc.setDialogTitle("Open File");
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    System.out.println("Open Succesfull!");
                    try {
                        //Opening the frame will go here

                        JOptionPane.showMessageDialog(null, "File Opened");
                    } catch (Exception ex1) {
                        JOptionPane.showMessageDialog(null, ex1.getMessage());
                    }
                }                
            }
        });
        panelOne.add(open);
        
        //Opens User Manual
        JButton helpButton = new JButton("Help");
        helpButton.setPreferredSize(new Dimension(65,50));
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL("https://support.draw.io/display/DO/Draw.io+Online+User+Manual").toURI());
                } catch (IOException ex) {
                    Logger.getLogger(DrawFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(DrawFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        });
        panelOne.add(helpButton); 
        return panelOne; 
    }
    
    private JPanel getTextAttributesPanel(DrawingTabbedPanel dtPanel) {
        JPanel panelOne = new JPanel(new GridLayout(5,4));
        TitledBorder border = new TitledBorder("Text Options:");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        panelOne.setBorder(border);
        TextOptionListener toListener = new TextOptionListener (dtPanel);
        
        //Adding text size combo box into panelOne
        panelOne.add (new JLabel("Font Size"));
            textSizeCB = new JComboBox(textSizeStr);
            textSizeCB.setSelectedIndex(11);
            textSizeCB.setName("fontSize");
//            textSizeCB.addActionListener(toListener);
            panelOne.add(textSizeCB);
            
            //Adding text font combo box into panelOne
            panelOne.add (new JLabel("Font Name: "));
            textFontCB = new JComboBox(textFontStr1);
            textFontCB.setSelectedIndex(0);
            textFontCB.setName("fontName");
//            textFontCB.addActionListener(toListener);
            panelOne.add(textFontCB);
            
            //Adding text format combo box into panelOne
            panelOne.add (new JLabel("Font Face: "));
            textFormatCB = new JComboBox(textFormatStr1);
            textFormatCB.setSelectedIndex(0);
            textFormatCB.setName("fontFace");
//            textFormatCB.addActionListener(toListener);
            panelOne.add(textFormatCB);
            
            //Adding text color combo box into panelOne
            panelOne.add (new JLabel("Font Color: "));
            textColorCB = new JComboBox(textColorStr1);
            textColorCB.setSelectedIndex(0);
//            textColorCB.addActionListener(toListener);
            panelOne.add(textColorCB);
            
            
            
            //Adding Text Alignment Text Area
            panelOne.add (new JLabel("Text Aligment: "));
            //Adding left text alignment button
            alignLeftButton = new JButton("L");
//            textSizeCB.addActionListener(toListener);
            panelOne.add(alignLeftButton);
            
            
            //Adding middle text allighnment button
            alignMiddleButton = new JButton("M");
//            alignMiddleButton.addActionListener(toListener);
            panelOne.add(alignMiddleButton);
            
            //Adding right text allighnment button
            alignRightButton = new JButton("R");
//            alignRightButton.addActionListener(toListener);
            panelOne.add(alignRightButton);
            
            //Adding width size text area
            panelOne.add (new JLabel("Text Field Width: "));
            tfWidthSize = new JTextField("               ");
            panelOne.add(tfWidthSize);
            
            //Adding height size text area
            panelOne.add (new JLabel("Text Field Height: "));
            tfHeightSize = new JTextField("                ");
            panelOne.add(tfHeightSize);
            
            panelOne.add (new JLabel());
            
          //Adding textButton1 into panelOne
            textButton1 = new JButton("Insert Text"); 
            textButton1.addActionListener(toListener);
            panelOne.add(textButton1); 
        return panelOne;
    }
    
    private JPanel getShapePanel() {
        JPanel result = new JPanel(new GridLayout(0,3));
        TitledBorder border = new TitledBorder("Shape Options:");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        result.setBorder(border);        
        return result;
    }
    
    //
    // getters
    public JComboBox getTextSizeCB () {
    	return textSizeCB;
    }
    public JComboBox getTextFontCB() {
    	return textFontCB;
    }
    public JComboBox getTextFormatCB() {
    	return textFormatCB;
    }
    public JComboBox getTextColorCB() {
    	return textColorCB;
    }
    public JTextField getTfWidthSize () {
    	return tfWidthSize;
    }
    public JTextField getTfHeightSize (){
    	return tfHeightSize;
    }
}
