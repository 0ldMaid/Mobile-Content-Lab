import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;  
import java.lang.Object.*;  
import java.net.*;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.imageio.*;
import java.awt.image.*;

import javax.swing.border.Border;

import java.util.Arrays;
import java.util.Comparator;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.spongycastle.util.encoders.Base64;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.lang.ProcessBuilder;












public class mcl extends JFrame implements ActionListener{

Process p;

Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
int xzx = 700;
int xzy = 650;
int cenx = (scrSize.width / 2) - (xzx / 2);
int ceny = (scrSize.height / 2) - (xzy / 2);

Timer xtimerx;//class loop.
Toolkit toolkit;


FlowLayout flow0 = new FlowLayout(0);
Font f_01 = new Font("Arial", Font.PLAIN, 12);
Font f_02 = new Font("Arial", Font.PLAIN, 18);

TrayIcon icon;

JPanel jpx0 = new JPanel();
JPanel jpx1 = new JPanel();
JPanel[] jpx1_content = new JPanel[0];
JPanel jpx2 = new JPanel();
JPanel jpx2_s = new JPanel();
JPanel jpx2_m = new JPanel();
JPanel jpx3 = new JPanel();
JScrollPane scrollPane_x = new JScrollPane(jpx1);

JMenuItem exit = new JMenuItem("Exit (Shutdown)");





ButtonGroup groupn = new ButtonGroup();
JRadioButtonMenuItem nodes_yes = new JRadioButtonMenuItem("Allow all nodes (yes)");
JRadioButtonMenuItem nodes_no = new JRadioButtonMenuItem("Allow all nodes (no)");

JMenuItem add_contentp = new JMenuItem("Add A Content Provider");

JMenuItem show_prikey = new JMenuItem("Show Account Private Key");
JMenuItem show_pubkey = new JMenuItem("Show Account Public Key");
JMenuItem signm = new JMenuItem("Sign Message");
JMenuItem verifym = new JMenuItem("Verify Message");
JMenuItem add_miner_url = new JMenuItem("Add Your Miner URL");
JMenuItem add_miner_user = new JMenuItem("Add Your Miner User");
JMenuItem add_miner_pass = new JMenuItem("Add Your Miner Pass");


JMenuItem kconsole = new JMenuItem("Krypton Console");
JMenuItem node_status = new JMenuItem("Network Status");




static String content[][] = new String[6][0];
static String settingsx[] = new String[0];
static String peersx[] = new String[0];

static String mining_speed = new String("Starting...");
static String versionx = new String("1.0.0.1");
static String idx = new String("");
static String xtypex = new String("user");

static String import1 = new String("");
static String import2 = new String("");
static String import3 = new String("");
static String import4 = new String("");
static String import5 = new String("");
static String import6 = new String("");

static int content_window_size = 0;
static int hide_console = 0;
static int console_showing = 1;
static int new_window_showing = 0;
static int old_content_length = 0;
int screen_x = 0;
int screen_y = 0;
int ix0 = 0;
int ix1 = 0;


JMenu fileMenu = new JMenu("File");
JMenu editMenu = new JMenu("Edit");
JMenu toolMenu = new JMenu("Tools");
JMenu databaseMenu = new JMenu("Database");
JMenu accountMenu = new JMenu("Account");
JMenu spaceMenu = new JMenu("|");
JMenu nodeMenu = new JMenu("Nodes");



JButton button1 = new JButton("New");
JButton button2 = new JButton("Push");
JButton button3 = new JButton("");
JButton button4 = new JButton("");
JButton button5 = new JButton("");
JButton button6 = new JButton("");
JButton button7 = new JButton("");



JLabel status1 = new JLabel("", JLabel.LEFT);
JLabel status2 = new JLabel("", JLabel.LEFT);
JLabel status3 = new JLabel("", JLabel.LEFT);
JLabel status4 = new JLabel("", JLabel.LEFT);
JLabel status5 = new JLabel("", JLabel.LEFT);
JLabel status6 = new JLabel("", JLabel.LEFT);
JLabel status7 = new JLabel("", JLabel.LEFT);
JLabel status8 = new JLabel("", JLabel.LEFT);
JLabel status9 = new JLabel("", JLabel.LEFT);

Label status_x1 = new Label("loading...", Label.LEFT);

Label status_ax1 = new Label("loading...", Label.LEFT);
Label status_ax2 = new Label("loading...", Label.LEFT);

JLabel mining_status = new JLabel("Starting...", JLabel.LEFT);

JLabel info_mining_1_l = new JLabel("Your computer will mine for LTC to support the provider.", JLabel.LEFT);
JLabel info_mining_2_l = new JLabel("Your Stats:", JLabel.LEFT);

JLabel info_urlx_l = new JLabel("Stratum Proxy:", JLabel.RIGHT);
JLabel info_user_l = new JLabel("User:", JLabel.RIGHT);
JLabel info_pass_l = new JLabel("Pass:", JLabel.RIGHT);


JTextField info_urlx = new JTextField("", 25);
JTextField info_user = new JTextField("", 10);
JTextField info_pass = new JTextField("", 8);







static Color st_gray1 = new Color(0.8f, 0.8f, 0.8f);//light gray for sites
static Color st_gray2 = new Color(0.99f, 0.99f, 0.99f);//darker gray for sites
Color xstripesc = new Color(0.0f, 0.0f, 0.0f);
Color xblue = new Color(0.1f, 0.1f, 0.16f);
Color bluex1 = new Color(0.0f, 0.0f, 0.3f);
Color bluex2 = new Color(0.1f, 0.17f, 0.39f);
Color bluex3 = new Color(0.6f, 0.67f, 0.9f);
Color bluex4 = new Color(0.3f, 0.5f, 0.6f);
Color darkgray04 = new Color(0.04f, 0.04f, 0.04f);//dark gray
Color darkgray08 = new Color(0.08f, 0.08f, 0.08f);//dark gray
Color darkgray01 = new Color(0.156f, 0.156f, 0.156f);//dark gray
Color darkgray70 = new Color(0.3f, 0.3f, 0.3f);
Color gray5 = new Color(0.5f, 0.5f, 0.5f);
Color gray6 = new Color(0.6f, 0.6f, 0.6f);
Color gray7 = new Color(0.7f, 0.7f, 0.7f);
Color gray8 = new Color(0.8f, 0.8f, 0.8f);
Color gray9 = new Color(0.9f, 0.9f, 0.9f);
Color blackx = new Color(0.0f, 0.0f, 0.0f);
Color whitex = new Color(1.0f, 1.0f, 1.0f);
Color purple = new Color(1.0f, 0.0f, 0.8f);
Color redx = new Color(1.0f, 0.2f, 0.216f);
Color yellowx = new Color(1.0f, 0.9f, 0.0f);
Color yellowx2 = new Color(0.8f, 0.7f, 0.0f);
Color tab_off = gray8;                               //for the tabs
Color tab_on = whitex;//bluex2                       //for the tabs
Color lightgreenx = new Color(0.5f, 0.9f, 0.5f);
Color darkgreenx = new Color(0.1f, 0.3f, 0.1f);
Color darkgreebnx = new Color(0.1f, 0.3f, 0.1f);
Color yellow = bluex4;   //the selected item





Icon imx0;
Icon imx1;
Icon imx2;
Icon imx3;

Icon lomx0;
Icon lomx1;
Icon lomx2;
Icon lomx3;









mcl(){//**************************************************************************


	super("Mobile Content Lab");
	setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	setBackground(Color.black);
	setSize(xzx, xzy);
	setLocation(cenx, ceny);
	setResizable(true);
	setAlwaysOnTop(false);
        addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){setVisible(true); p.destroy(); savex(); System.exit(0);}});

        requestFocus();

//the program icon 
	Image imageppx = new ImageIcon(this.getClass().getResource("images/hex.png")).getImage();
	setIconImage(imageppx);

	imx0 = new ImageIcon(this.getClass().getResource("images/message-no.png"));
	imx1 = new ImageIcon(this.getClass().getResource("images/message-yes.png"));
	imx2 = new ImageIcon(this.getClass().getResource("images/settings.png"));

	lomx0 = new ImageIcon(this.getClass().getResource("images/mining_stats.png"));
	lomx1 = new ImageIcon(this.getClass().getResource("images/internet_earth.png"));
	lomx2 = new ImageIcon(this.getClass().getResource("images/save.png"));
	lomx3 = new ImageIcon(this.getClass().getResource("images/file_delete.png"));




	Long starttime = System.currentTimeMillis();

	flow0.setHgap(5);
	flow0.setVgap(1);

	mining_status.setPreferredSize(new Dimension(180, 40));
	mining_status.setForeground(Color.BLACK);
	mining_status.setFont(f_02);
	mining_status.setIcon(lomx0);

	status1.setPreferredSize(new Dimension(380, 20));
	status1.setForeground(Color.BLACK);
	status1.setIcon(imx0);
	status1.setFont(f_01);

	status2.setPreferredSize(new Dimension(380, 20));
	status2.setForeground(Color.BLACK);
	status2.setIcon(imx0);
	status2.setFont(f_01);

	status3.setPreferredSize(new Dimension(380, 20));
	status3.setForeground(Color.BLACK);
	status3.setIcon(imx0);
	status3.setFont(f_01);

	status4.setPreferredSize(new Dimension(380, 20));
	status4.setForeground(Color.BLACK);
	status4.setIcon(imx0);
	status4.setFont(f_01);

	status5.setPreferredSize(new Dimension(380, 20));
	status5.setForeground(Color.BLACK);
	status5.setIcon(imx0);
	status5.setFont(f_01);

	status6.setPreferredSize(new Dimension(380, 20));
	status6.setForeground(Color.BLACK);
	status6.setIcon(imx0);
	status6.setFont(f_01);

	status7.setPreferredSize(new Dimension(380, 20));
	status7.setForeground(Color.BLACK);
	status7.setIcon(imx0);
	status7.setFont(f_01);

	status8.setPreferredSize(new Dimension(380, 20));
	status8.setForeground(Color.BLACK);
	status8.setIcon(imx0);
	status8.setFont(f_01);

	status9.setPreferredSize(new Dimension(380, 20));
	status9.setForeground(Color.BLACK);
	status9.setIcon(imx0);
	status9.setFont(f_01);


	info_mining_1_l.setPreferredSize(new Dimension(400, 15));
	info_mining_2_l.setPreferredSize(new Dimension(150, 15));


	info_urlx_l.setPreferredSize(new Dimension(100, 20));
	info_user_l.setPreferredSize(new Dimension(100, 20));
	info_pass_l.setPreferredSize(new Dimension(65, 20));

	Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

	info_urlx.setBorder(border);
	info_user.setBorder(border);
	info_pass.setBorder(border);


	status_x1.setPreferredSize(new Dimension(440, 20));
	status_x1.setForeground(Color.BLACK);
	//status_x1.setIcon(imx2);

	status_ax1.setPreferredSize(new Dimension(416, 18));
	status_ax1.setForeground(Color.BLACK);
	//status_ax1.setIcon(imx0);

	status_ax2.setPreferredSize(new Dimension(416, 18));
	status_ax2.setForeground(Color.BLACK);
	//status_ax2.setIcon(imx0);



	button1.setPreferredSize(new Dimension(140, 30));
	button1.setOpaque(true);
	button1.setBackground(gray8);//darkgray08
	button1.setForeground(blackx);//darkgray08
	button1.setToolTipText("Add new content");
	button1.addActionListener(this);

	button2.setPreferredSize(new Dimension(70, 30));
	button2.setOpaque(true);
	button2.setBackground(gray8);//darkgray08
	button2.setForeground(blackx);//darkgray08
	button2.setToolTipText("Button");
	button2.addActionListener(this);

	button3.setPreferredSize(new Dimension(70, 30));
	button3.setOpaque(true);
	button3.setBackground(gray8);//darkgray08
	button3.setForeground(blackx);//darkgray08
	button3.setToolTipText("Button");
	button3.addActionListener(this);

	button4.setPreferredSize(new Dimension(70, 30));
	button4.setOpaque(true);
	button4.setBackground(gray8);//darkgray08
	button4.setForeground(blackx);//darkgray08
	button4.setToolTipText("Button");
	button4.addActionListener(this);

	button5.setPreferredSize(new Dimension(70, 30));
	button5.setOpaque(true);
	button5.setBackground(gray8);//darkgray08
	button5.setForeground(blackx);//darkgray08
	button5.setToolTipText("Button");
	button5.addActionListener(this);

	button6.setPreferredSize(new Dimension(70, 30));
	button6.setOpaque(true);
	button6.setBackground(gray8);//darkgray08
	button6.setForeground(blackx);//darkgray08
	button6.setToolTipText("Button");
	button6.addActionListener(this);

	button7.setPreferredSize(new Dimension(70, 30));
	button7.setOpaque(true);
	button7.setBackground(gray8);//darkgray08
	button7.setForeground(blackx);//darkgray08
	button7.setToolTipText("Button");
	button7.addActionListener(this);







	scrollPane_x.setPreferredSize(new Dimension(100, 360));
	//scrollPane_x.setBackground(bluex2);
	scrollPane_x.setFont(new Font("Arial", Font.PLAIN, 14));







	//JPanel jpx0 = new JPanel();
	jpx0.setPreferredSize(new Dimension(385, 263));
	jpx0.setBackground(gray8);//darkgray08
	jpx0.add(button1);
	jpx0.add(button2);
	jpx0.add(button3);
	jpx0.add(button4);
	jpx0.add(button5);
	jpx0.add(button6);
	jpx0.add(button7);


	//JPanel jpx1 = new JPanel();
	jpx1.setPreferredSize(new Dimension(90, 263));
	jpx1.setBackground(gray8);//darkgray08
	//add later



	//JPanel jpx2_s = new JPanel();
	jpx2_s.setPreferredSize(new Dimension(400, 80));
	jpx2_s.setBackground(gray7);//darkgray08
	jpx2_s.add(info_mining_1_l);
	jpx2_s.add(info_urlx_l);      jpx2_s.add(info_urlx);
	jpx2_s.add(info_user_l);      jpx2_s.add(info_user);
	jpx2_s.add(info_pass_l);      jpx2_s.add(info_pass);

	//JPanel jpx2_m = new JPanel();
	jpx2_m.setPreferredSize(new Dimension(200, 80));
	jpx2_m.setBackground(gray7);//darkgray08
	jpx2_m.add(info_mining_2_l);
	jpx2_m.add(mining_status);

	//JPanel jpx2 = new JPanel();
	jpx2.setPreferredSize(new Dimension(480, 80));
	jpx2.setBackground(gray7);//darkgray08
	jpx2.add(jpx2_s); 	      jpx2.add(jpx2_m);




	flow0.setVgap(0);
	//JPanel jpx3 = new JPanel();
	jpx3.setLayout(flow0);
	jpx3.setPreferredSize(new Dimension(480, 20));
	jpx3.setBackground(gray7);//darkgray08
	jpx3.add(status_x1);




	this.getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener(){

		@Override
		public void ancestorMoved(HierarchyEvent e) {
		
			refreshx();
				
		}

		@Override
		public void ancestorResized(HierarchyEvent e) {
				
			refreshx();
		
		}//override************************************			
	});//**************************************************************************


        JMenuBar menuBar = new JMenuBar();


        fileMenu.add(exit);
	exit.addActionListener(this);
	exit.setFont(new Font("Arial", Font.PLAIN, 11));


	groupn.add(nodes_yes);
	groupn.add(nodes_no);




	add_contentp.setFont(new Font("Arial", Font.PLAIN, 11));
	add_contentp.addActionListener(this);

	show_prikey.setFont(new Font("Arial", Font.PLAIN, 11));
	show_prikey.addActionListener(this);

	show_pubkey.setFont(new Font("Arial", Font.PLAIN, 11));
	show_pubkey.addActionListener(this);

	signm.setFont(new Font("Arial", Font.PLAIN, 11));
	signm.addActionListener(this);

	verifym.setFont(new Font("Arial", Font.PLAIN, 11));
	verifym.addActionListener(this);

	add_miner_url.setFont(new Font("Arial", Font.PLAIN, 11));
	add_miner_url.addActionListener(this);

	add_miner_user.setFont(new Font("Arial", Font.PLAIN, 11));
	add_miner_user.addActionListener(this);

	add_miner_pass.setFont(new Font("Arial", Font.PLAIN, 11));
	add_miner_pass.addActionListener(this);




	accountMenu.add(add_contentp);
	accountMenu.addSeparator();
	accountMenu.add(show_prikey);
	accountMenu.add(show_pubkey);
	accountMenu.add(signm);
	accountMenu.add(verifym);
	//accountMenu.addSeparator();
	//accountMenu.add(add_miner_url);
	//accountMenu.add(add_miner_user);
	//accountMenu.add(add_miner_pass);





	nodeMenu.add(kconsole);
	nodeMenu.add(node_status);
	//nodeMenu.addSeparator();

	kconsole.setFont(new Font("Arial", Font.PLAIN, 11));
	kconsole.addActionListener(this);
	node_status.setFont(new Font("Arial", Font.PLAIN, 11));
	node_status.addActionListener(this);



        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(toolMenu);
        menuBar.add(databaseMenu);
        menuBar.add(accountMenu);
        menuBar.add(spaceMenu);
        menuBar.add(nodeMenu);

	spaceMenu.setEnabled(false);

	Container cp = getContentPane();
	cp.setLayout(new FlowLayout());
	cp.setBackground(gray8);
	cp.setForeground(gray7);
	cp.add(jpx0);
	cp.add(scrollPane_x);	
	cp.add(jpx2);
	cp.add(jpx3);




	setVisible(true);




	//load database
	try{

	   mcl_load_db_settings load = new mcl_load_db_settings();
	   mcl_load_db_content loadc = new mcl_load_db_content();

	}catch(Exception e){e.printStackTrace();}//*****************

	if(settingsx.length < 1){

	   mcl_build_database build = new mcl_build_database();
	   mcl_load_db_settings load = new mcl_load_db_settings();
	   mcl_load_db_content loadc = new mcl_load_db_content();

	}//*********************
	else{System.out.println("DB OK");}

	rebuild_content();



	if(settingsx[0].equals("xxx1")){build_keys();}


	info_urlx.setText(settingsx[6]);
	info_user.setText(settingsx[7]);
	info_pass.setText(settingsx[8]);


	//start the miner
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_mining(), 0);

	//start the engine
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_engine(), 0);

	//start the server
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_server(), 0);

	//start the client
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_client(), 0);

	//start the proxy
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_miner_proxy(), 0);


}//*****************************************************************************
















	public void refreshx(){

	//sizes
	Dimension jd = new Dimension(1,1);
	jd = getSize();

	screen_x = (int) jd.getWidth();
	screen_y = (int) jd.getHeight();

	scrollPane_x.setPreferredSize(new Dimension(screen_x -14, screen_y - 220));

	jpx0.setPreferredSize(new Dimension(screen_x -10, 40));
	jpx1.setPreferredSize(new Dimension(screen_x -32, content_window_size));
	jpx2.setPreferredSize(new Dimension(screen_x -10, 80));
	jpx3.setPreferredSize(new Dimension(screen_x -10, 20));




	}//********************




	public void savex(){

	System.out.println("Save");


	settingsx[6] = info_urlx.getText();
	settingsx[7] = info_user.getText();
	settingsx[8] = info_pass.getText();

	mcl_save_db_settings dbc = new mcl_save_db_settings();


	}//*****************





	public void loadx(){

	System.out.println("Load");

	mcl_load_db_content dbx = new mcl_load_db_content();


	}//*****************




	public void build_keys(){

	status_x1.setText("build keys");

	try{

    	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    	kpg.initialize(1024);
    	KeyPair keyPair = kpg.genKeyPair();

    	System.out.println("");
    	System.out.println("privateKey Base 64: " + Base64.toBase64String(keyPair.getPrivate().getEncoded()) );
    	System.out.println("");
    	System.out.println("Public Base 64: " + Base64.toBase64String(keyPair.getPublic().getEncoded()) );
    	System.out.println("");

	settingsx[0] = Base64.toBase64String(keyPair.getPrivate().getEncoded());
	settingsx[1] = Base64.toBase64String(keyPair.getPublic().getEncoded());
	settingsx[5] = Base64.toBase64String(keyPair.getPublic().getEncoded());

	}catch(Exception e){e.printStackTrace();}

	savex();

	}//**********************







	class RemindTask_mining extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************

	status_x1.setText("start miner");

	try{
	LTC_Miner minerx = new LTC_Miner("http://127.0.0.1:8332","triplebogeygame.1:1",5000,30000,2,1.0);
	}catch(Exception e){e.printStackTrace();}

	}//runx***************************************************************************************************
        }//remindtask



	class RemindTask_engine extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************

	while(true){

	if(content[0].length > old_content_length){rebuild_content(); old_content_length = content[0].length;}	

	info_urlx.setText(settingsx[6]);
	info_user.setText(settingsx[7]);
	info_pass.setText(settingsx[8]);

	mining_status.setText(mining_speed);

	try{Thread.sleep(1000);} catch(InterruptedException e){}


	}//***********

	}//runx***************************************************************************************************
        }//remindtask








	class RemindTask_server extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************

	mcl_server mclc = new mcl_server();

	}//runx***************************************************************************************************
        }//remindtask


	class RemindTask_client extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************

	if(!settingsx[1].equals(settingsx[5])){mcl_client mclc = new mcl_client();}

	}//runx***************************************************************************************************
        }//remindtask










	class RemindTask_window extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************


		status_x1.setText("open window");

		mcl_import_content mic = new mcl_import_content();


		while(new_window_showing == 1){

		try{Thread.sleep(1000);} catch(InterruptedException e){}

		}//****************************



		if(import1.length() == 0){JOptionPane.showMessageDialog(null, "You did not add a description!");}
		else if(import4.length() == 0 && import5.length() == 0){JOptionPane.showMessageDialog(null, "There is no content to add!");}
		else if(import6.length() == 0 && import4.length() == 0){JOptionPane.showMessageDialog(null, "There is no export file name!");}
		else{make_new_content();}


	}//runx***************************************************************************************************
        }//remindtask







	class RemindTask_miner_proxy extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************

	status_x1.setText("start miner proxy");
	System.out.println("start miner proxy");

	try {

      	String line;
      	p = Runtime.getRuntime().exec("mining_proxy.exe");


	System.out.println("printing");

      	BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

      	while ((line = error.readLine()) != null) {
        System.out.println("line " + line);
      	}//****************************************

      	while ((line = input.readLine()) != null) {
        System.out.println("line " + line);
      	}//****************************************

      	input.close();


    	}catch (Exception err) {err.printStackTrace();}




	}//runx***************************************************************************************************
        }//remindtask
















	public void new_content_window(){

	if(new_window_showing == 0){

	//start the engine
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_window(), 0);

	}//*************************
	else{System.out.println("Window in use.");}




	//new_window_showing
	//make_new_content();

	}//******************************






	public String get_file(){

	status_x1.setText("import file");

	String content1 = new String("");

	try{

        File file = new File(import5);
 
        FileInputStream fis = new FileInputStream(file);
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        try {

            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }

        } catch (IOException ex) {
            System.out.println("ERROR 1234");
        }

        	byte[] bytes = bos.toByteArray();
 
		content1 = Base64.toBase64String(bytes);

		System.out.println("size " + content1.length());

		fis.close();
		bos.close();

	}catch(Exception e){e.printStackTrace();}


	return content1;

	}//********************






	public void make_new_content(){

	status_x1.setText("save new content");

	if(!settingsx[1].equals(settingsx[5])){JOptionPane.showMessageDialog(null, "You can not add content, because you are not the content provider.\nYou are already following someone else.");}
	else{

	System.out.println("Make New Content");

	int oldi = content[0].length;
	int newi = content[0].length + 1;
	String[][] newc = new String[content.length][newi];

	int runx = 0;

	for(int xloop0 = 0; xloop0 < oldi; xloop0++){//*************

	newc[0][xloop0] = content[0][xloop0];
	newc[1][xloop0] = content[1][xloop0];
	newc[2][xloop0] = content[2][xloop0];
	newc[3][xloop0] = content[3][xloop0];
	newc[4][xloop0] = content[4][xloop0];
	newc[5][xloop0] = content[5][xloop0];

	runx++;

	}//*********************************************************


	DateFormat dateFormatx = new SimpleDateFormat("yyyy.MM.dd-HH:mm.ss");
	Date datex = new Date();
	System.out.println(dateFormatx.format(datex));


	String contentx = new String("");
	String signx = new String("");

	if(import5.length() > 0){

		contentx = get_file();
		signx = sign_message(contentx);

	}//**********************
	else{//

		byte[] bytes = import4.getBytes();
		contentx = Base64.toBase64String(bytes);
		signx = sign_message(contentx);

	}//else



	newc[0][runx] = new String(Integer.toString(runx));
	newc[1][runx] = new String(dateFormatx.format(datex));
	newc[2][runx] = new String(import1 + "\n" + import2 + "\n" + import3);
	newc[3][runx] = new String(contentx);
	newc[4][runx] = new String(import6);
	newc[5][runx] = new String(signx);

	content = newc;


	mcl_save_db_content save_content = new mcl_save_db_content(runx);

	rebuild_content();

	}//else

	}//****************************







	public String sign_message(String text){

	String returnx = new String("");

	try{

        byte[] message = Base64.decode(text);

	byte[] clear = Base64.decode(settingsx[0]);
    	PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
    	KeyFactory fact = KeyFactory.getInstance("RSA");
    	PrivateKey priv = fact.generatePrivate(keySpec);
    	Arrays.fill(clear, (byte) 0);

    	Signature sigx = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
    	sigx.initSign(priv);
    	sigx.update(message);
    	byte[] signatureBytesx = sigx.sign();
    	//System.out.println("Public: " + Base64.toBase64String(pub.getEncoded()));
    	//System.out.println("Singature 3: " + Base64.toBase64String(signatureBytesx));

	byte[] clear2 = Base64.decode(settingsx[1]);
    	X509EncodedKeySpec keySpec2 = new X509EncodedKeySpec(clear2);
    	KeyFactory fact2 = KeyFactory.getInstance("RSA");
    	PublicKey publ2 = fact2.generatePublic(keySpec2);
    	Arrays.fill(clear2, (byte) 0);

        sigx.initVerify(publ2);
        sigx.update(message);

        System.out.println("SIGN >>> " + sigx.verify(signatureBytesx));
    	String signxx = Base64.toBase64String(signatureBytesx);

	returnx = signxx;


	System.out.println("key " + mcl.settingsx[5]);
	System.out.println("content " + text);
	System.out.println("sig " + signxx);



	//test

        byte[] message2 = Base64.decode(text);

	byte[] clear3 = Base64.decode(mcl.settingsx[5]);
    	X509EncodedKeySpec keySpec3 = new X509EncodedKeySpec(clear3);
    	KeyFactory fact3 = KeyFactory.getInstance("RSA");
    	PublicKey publ3 = fact3.generatePublic(keySpec3);
    	Arrays.fill(clear3, (byte) 0);

	byte[] signatureBytesx2 = Base64.decode(signxx);

    	Signature sigx3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
        sigx3.initVerify(publ3);
        sigx3.update(message2);
        System.out.println(">> ST >> " + sigx3.verify(signatureBytesx2));

	//test









	}catch(Exception e){e.printStackTrace();}



	return returnx;

	}//**************************************











	public void rebuild_content(){

	status_x1.setText("set display");

	int cont = 0;

	try{
	cont = content[0].length;
	}catch(Exception e){e.printStackTrace(); cont = 0;}

	System.out.println("cont " + cont);


	Font f_x = new Font("Arial", Font.PLAIN, 12);
	Font f_xl = new Font("Arial", Font.PLAIN, 25);

	JLabel[] date = new JLabel[cont];
	JLabel[] number = new JLabel[cont];

	JLabel[] long_description_l = new JLabel[cont];
	JTextArea[] long_description = new JTextArea[cont];

	JLabel[] links = new JLabel[cont];
	JButton[] open_1 = new JButton[cont];
	JButton[] open_2 = new JButton[cont];
	JButton[] open_3 = new JButton[cont];



	jpx1.removeAll();
	jpx1_content = new JPanel[cont];


		content_window_size = 0;

		for(int xloop0 = (cont -1); xloop0 > -1; xloop0--){//*******************************************

		date[xloop0] = new JLabel(content[1][xloop0], JLabel.LEFT);
		date[xloop0].setPreferredSize(new Dimension(130, 25));
		date[xloop0].setFont(f_x);

		number[xloop0] = new JLabel("#" + content[0][xloop0], JLabel.LEFT);
		number[xloop0].setPreferredSize(new Dimension(130, 20));
		number[xloop0].setFont(f_xl);



		long_description_l[xloop0] = new JLabel("Description:", JLabel.LEFT);
		long_description_l[xloop0].setPreferredSize(new Dimension(370, 25));
		long_description_l[xloop0].setFont(f_x);


		long_description[xloop0] = new JTextArea("",3,33);
		//long_description[xloop0].setEnabled(false);
		long_description[xloop0].setText(content[2][xloop0]);
		JScrollPane scrollPanex = new JScrollPane(long_description[xloop0]);


		links[xloop0] = new JLabel("Export Content", JLabel.RIGHT);
		links[xloop0].setPreferredSize(new Dimension(100, 25));
		links[xloop0].setFont(f_x);

		open_1[xloop0] = new JButton("");
		open_1[xloop0].setPreferredSize(new Dimension(40, 40));
		open_1[xloop0].setIcon(lomx1);
		open_1[xloop0].setMargin(new Insets(0, 0, 0, 0));
		open_1[xloop0].setHorizontalTextPosition(JButton.CENTER);
		open_1[xloop0].setBackground(gray9);
		open_1[xloop0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 0));
		open_1[xloop0].setEnabled(false);
		if(content[4][xloop0].equals("[[url]]")){open_1[xloop0].setEnabled(true);}

		open_2[xloop0] = new JButton("");
		open_2[xloop0].setPreferredSize(new Dimension(40, 40));
		open_2[xloop0].setIcon(lomx2);
		open_2[xloop0].setMargin(new Insets(0, 0, 0, 0));
		open_2[xloop0].setHorizontalTextPosition(JButton.CENTER);
		open_2[xloop0].setBackground(gray9);
		open_2[xloop0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 0));
		open_2[xloop0].setEnabled(false);
		if(!content[4][xloop0].equals("[[url]]")){open_2[xloop0].setEnabled(true);}

		open_3[xloop0] = new JButton("");
		open_3[xloop0].setPreferredSize(new Dimension(40, 40));
		open_3[xloop0].setIcon(lomx3);
		open_3[xloop0].setMargin(new Insets(0, 0, 0, 0));
		open_3[xloop0].setHorizontalTextPosition(JButton.CENTER);
		open_3[xloop0].setBackground(gray9);
		open_3[xloop0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 0));
		open_3[xloop0].setEnabled(false);




		final int holdx = xloop0;

		open_1[xloop0].addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){

		
			byte[] bytes = Base64.decode(content[3][holdx]);

			String urlx = new String(bytes);
		
			try{
			java.awt.Desktop.getDesktop().browse(new URI(urlx));
			}catch(Exception ex){ex.printStackTrace();}
		
			//JOptionPane.showMessageDialog(null, "Content Exported: " + urlx);


		  }//****************************************
		});

		open_2[xloop0].addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){

		

			mcl_export_db_content exportx = new mcl_export_db_content(holdx);



		  }//****************************************
		});




		jpx1_content[xloop0] = new JPanel();
		jpx1_content[xloop0].setLayout(flow0);
		jpx1_content[xloop0].setPreferredSize(new Dimension(650, 80));
		jpx1_content[xloop0].setBackground(gray9);//darkgray08
		jpx1_content[xloop0].add(date[xloop0]);
		jpx1_content[xloop0].add(long_description_l[xloop0]);
		jpx1_content[xloop0].add(links[xloop0]);
		jpx1_content[xloop0].add(number[xloop0]);
		jpx1_content[xloop0].add(scrollPanex);
		jpx1_content[xloop0].add(open_1[xloop0]);
		jpx1_content[xloop0].add(open_2[xloop0]);
		jpx1_content[xloop0].add(open_3[xloop0]);



		jpx1.add(jpx1_content[xloop0]);

		content_window_size = content_window_size + 85;

		}//for******************************************************************************************

	content_window_size = content_window_size + 5;

	refreshx();

	

	for(int xloop0 = 0; xloop0 < cont; xloop0++){//***********
	long_description_l[xloop0].setText("Description: ");
	}//for****************************************************

	for(int xloop0 = 0; xloop0 < cont; xloop0++){//***********
	long_description_l[xloop0].setText("Description:");
	}//for****************************************************





	}//***************************








	public void add_content_key(){

	String response2 = JOptionPane.showInputDialog(null, "Enter the Public Key of the party you wish to support.", "Public Key", JOptionPane.QUESTION_MESSAGE);

	try{

	    if(response2.length() > 0){settingsx[5] = response2;}//************************

	}catch(Exception e){System.out.println("Nothing was entered.");}

	}//***************************







	public void push_new_content(){

	if(settingsx[1].equals(settingsx[5])){mcl_push pushx = new mcl_push();}
	else{JOptionPane.showMessageDialog(null, "You are not the content provider!");}		

	}//****************************









	public void add_miner_url_go(){

	String response2 = JOptionPane.showInputDialog(null, "Enter the Miner URL of your mining pool.", "Miner URL", JOptionPane.QUESTION_MESSAGE);

	if(response2.length() > 0){settingsx[6] = response2;}//************************

	}//***************************



	public void add_miner_user_go(){

	String response2 = JOptionPane.showInputDialog(null, "Enter the Miner User Name of your mining pool.", "Miner User", JOptionPane.QUESTION_MESSAGE);

	if(response2.length() > 0){settingsx[7] = response2;}//************************

	}//***************************



	public void add_miner_pass_go(){

	String response2 = JOptionPane.showInputDialog(null, "Enter the Miner Password of your mining pool.", "Miner Password", JOptionPane.QUESTION_MESSAGE);

	if(response2.length() > 0){settingsx[8] = response2;}//************************

	}//***************************





//***************************************************************************************************************************************
//***************************************************************************************************************************************

//handel all the button clicks. 
public void actionPerformed(ActionEvent event){


	if(event.getSource() == add_contentp)               {add_content_key();}
	if(event.getSource() == add_miner_url)              {add_miner_url_go();}
	if(event.getSource() == add_miner_user)             {add_miner_user_go();}
	if(event.getSource() == add_miner_pass)             {add_miner_pass_go();}




	if(event.getSource() == button1)                    {new_content_window();}
	if(event.getSource() == button2)                    {push_new_content();}
	if(event.getSource() == button3)                    {}
	if(event.getSource() == button4)                    {}
	if(event.getSource() == button5)                    {}
	if(event.getSource() == button6)                    {}
	if(event.getSource() == button7)                    {}



	if(event.getSource() == exit)                       {System.exit(0);}
	if(event.getSource() == kconsole)                   {krypton k = new krypton();}



}//********************************************





//start the program.
    public static void main(String[] args) {

	mcl black = new mcl();

    }//main




}//last
