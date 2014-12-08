import java.io.*;
import javax.swing.*;
import java.awt.*;
//import javax.sound.sampled.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import javax.imageio.ImageIO;
import javax.imageio.*;
import java.awt.image.*;






public class mcl_import_content extends JFrame implements ActionListener{

File f1 = new File("");
JFileChooser fc;

JFrame frame_1 = new JFrame();
//JFileChooser file_chooser = new JFileChooser(file_open_1);

Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
int xzx = 380;
int xzy = 400;
int cenx = (scrSize.width / 2) - (xzx / 2);
int ceny = (scrSize.height / 2) - (xzy / 2);

int runs = 50000;
String main = new String("");

String cx0 = new String();
String cx1 = new String();
String cx2 = new String();
String cx3 = new String();
String cx4 = new String();

int section_number = 0;
int ix0 = 0;
int ix1 = 0;
int ix2 = 0;
int ix3 = 0;
int ix4 = 0;
int fg = 0;
int fs = 0;
int kcountxx = 0;
int kcountxx2 = 0;
int thisx = 0;
int loopc1 = 0;

JButton b0 = new JButton("Browse");
JButton b1 = new JButton("Import Content");
JButton b2 = new JButton();

JComboBox cb_column;
//JComboBox cb_sitese;

Label l0 = new Label();
Label l1 = new Label();
Label l2 = new Label();
Label l3 = new Label();

Label l5 = new Label();
Label l6 = new Label();
Label l7 = new Label();

Label importx = new Label();

TextField url = new TextField("", 28);
TextField description1 = new TextField("", 28);
TextField description2 = new TextField("", 28);
TextField description3 = new TextField("", 28);

TextField export = new TextField("", 28);

File filex1;
File filex2;
String str0;


Color blackx = new Color(0.0f, 0.0f, 0.0f);
Color c1 = new Color(0.1f, 0.1f, 0.1f);
Color c2 = new Color(0.09f, 0.09f, 0.13f);
Color gray5 = new Color(0.5f, 0.5f, 0.5f);
Color gray6 = new Color(0.6f, 0.6f, 0.6f);
Color gray7 = new Color(0.7f, 0.7f, 0.7f);
Color gray8 = new Color(0.8f, 0.8f, 0.8f);
Color gray9 = new Color(0.9f, 0.9f, 0.9f);

Icon imx0;





	mcl_import_content(){//********************************************************************************************************
	
	super("Import Content");
	setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	setBackground(Color.black);
	setSize(xzx, xzy);
	setLocation(cenx, ceny);
	setResizable(false);
	frame_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	Image imageppx = new ImageIcon(this.getClass().getResource("images/hex.png")).getImage();
	setIconImage(imageppx);

        addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){mcl.new_window_showing = 0;}});//






	l0 = new Label("---- OR ----", Label.CENTER);
	l0.setPreferredSize(new Dimension(180, 20));


	l1 = new Label("Description 1", Label.LEFT);
	l1.setPreferredSize(new Dimension(80, 20));

	l2 = new Label("Description 2", Label.LEFT);
	l2.setPreferredSize(new Dimension(80, 20));

	l3 = new Label("Description 3", Label.LEFT);
	l3.setPreferredSize(new Dimension(80, 20));


	l5 = new Label("Content URL", Label.LEFT);
	l5.setPreferredSize(new Dimension(80, 20));

	l6 = new Label("Import File", Label.LEFT);
	l6.setPreferredSize(new Dimension(60, 20));

	l7 = new Label("Export Name._", Label.LEFT);
	l7.setPreferredSize(new Dimension(80, 20));


	importx = new Label("/", Label.LEFT);
	importx.setPreferredSize(new Dimension(155, 20));




	b0.setPreferredSize(new Dimension(80, 20));
	b0.setOpaque(true);
	b0.setBackground(gray8);//darkgray08
	b0.setForeground(blackx);//darkgray08
	b0.setToolTipText("Import");
	b0.addActionListener(this);


	b1.setPreferredSize(new Dimension(170, 30));
	b1.setOpaque(true);
	b1.setBackground(gray8);//darkgray08
	b1.setForeground(blackx);//darkgray08
	b1.setToolTipText("Import Content");
	b1.addActionListener(this);





        JPanel jp2 = new JPanel();
	jp2.setPreferredSize(new Dimension(370, 150));
	jp2.setBackground(gray8);
	jp2.add(l1);     	jp2.add(description1);
	jp2.add(l2);     	jp2.add(description2);
	jp2.add(l3);     	jp2.add(description3);

        JPanel jp3 = new JPanel();
	jp3.setPreferredSize(new Dimension(370, 35));
	jp3.setBackground(gray8);
	jp3.add(l5);     	jp3.add(url);


        JPanel jpor = new JPanel();
	jpor.setPreferredSize(new Dimension(370, 20));
	jpor.setBackground(gray7);
	jpor.add(l0);


        JPanel jp4 = new JPanel();
	jp4.setPreferredSize(new Dimension(370, 100));
	jp4.setBackground(gray8);
	jp4.add(l6);     	jp4.add(importx); jp4.add(b0);
	jp4.add(l7);     	jp4.add(export);

        JPanel jp5 = new JPanel();
	jp5.setPreferredSize(new Dimension(370, 40));
	jp5.setBackground(gray8);
	jp5.add(b1);




	Container cp = getContentPane();
	cp.setLayout(new FlowLayout());
	cp.setBackground(gray7);
	cp.setForeground(gray7);
	cp.add(jp2);
	cp.add(jp3);
	cp.add(jpor);
	cp.add(jp4);
	cp.add(jp5);


	mcl.new_window_showing = 1;
	setVisible(true);


	}//postid**************************************************************************************************************











	public void import_file(){



	fc = new JFileChooser(f1);

   	ix0 = fc.showOpenDialog(frame_1);

	try{
   	f1 = fc.getSelectedFile();
	}catch(Exception e){e.printStackTrace();}

	System.out.println("ix0 " + ix0 + " " + f1.exists());
	System.out.println("f1 " + f1.toString());



	}//***********************








	public void save_info(){

		mcl.import1 = "";
		mcl.import2 = "";
		mcl.import3 = "";
		mcl.import4 = "";
		mcl.import6 = "";


		if(f1.exists() && ix0 == 0){

		   System.out.println("Get File...");

		   importx.setText(f1.toString());

		   int findx = f1.toString().lastIndexOf("\\");
		   String export_name = f1.toString().substring(findx + 1, f1.toString().length());

		   export.setText(export_name);
		
			mcl.import1 = description1.getText();
			mcl.import2 = description2.getText();
			mcl.import3 = description3.getText();
			mcl.import4 = url.getText();
			mcl.import5 = f1.toString();
			mcl.import6 = export_name;
			
		}//*************************
		else if(url.getText().length() > 0){

			System.out.println("Get URL...");

			mcl.import1 = description1.getText();
			mcl.import2 = description2.getText();
			mcl.import3 = description3.getText();
			mcl.import4 = url.getText();
			mcl.import6 = "[[url]]";

		}//*********************************
		else{System.out.println("NO CONTENT...");}



	}//*********************








//***************************************************************************************************************************************
//***************************************************************************************************************************************

//handel all the button clicks. 
public void actionPerformed(ActionEvent event){


	if(event.getSource() == b0)                    {import_file(); save_info();}
	if(event.getSource() == b1)                    {save_info(); pullThePlug();}



}//********************************************





    public static void main(String[] args){

	mcl_import_content xr = new mcl_import_content();

    }//main






    public void pullThePlug() {


    System.out.println("PULL THE PLUG");

    mcl.new_window_showing = 0;

    WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

    }//pull








}//last