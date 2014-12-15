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

import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.imageio.*;
import java.awt.image.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;

import org.spongycastle.util.encoders.Base64;

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










public class mcl_server{

Timer xtimerx;//class loop.
Toolkit toolkit;

public final static int FILE_SIZE = 6022386;
public final static String FILE_TO_SEND = "";  // you may change this

String client_address_connect = new String("127.0.0.1");
int server_port_connect = 10055;
String user_ip = new String("");

int wait_send_file = 0;
String idx = new String("");

String push_sig;
String id;
String date;
String description;
String content;
String save_id;
String signature;




mcl_server(){//*****************************************************************





while(true){   


	wait_send_file = 0;


	try{          

	String jsonText = new String("");
	String responsex = new String("0");

	ServerSocket welcomeSocket = new ServerSocket(server_port_connect, 0, InetAddress.getByAddress(new byte[] {0x00,0x00,0x00,0x00}) );          
	Socket connectionSocket = welcomeSocket.accept();
	user_ip = connectionSocket.getRemoteSocketAddress().toString();
	BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
	DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());             
	String clientSentence = inFromClient.readLine();
	String capitalizedSentence;   

	JSONParser parser = new JSONParser();

	try {



		responsex = "x000";

		Object obj = parser.parse(clientSentence);
 
		JSONObject jsonObject = (JSONObject) obj;
  
		String request = (String) jsonObject.get("request");
		System.out.println("get " + request);


		try{

		idx = (String) jsonObject.get("id");
		String new_content = (String) jsonObject.get("pushc");


			if(request.equals("status")){responsex = "active";}

			else if(request.equals("content_list")){responsex = Integer.toString(mcl.content[0].length);}

			else if(request.equals("get_idx")){responsex = export_content(idx);}

			else if(request.equals("push")){responsex = get_content(new_content);}

			else if(request.equals("mining")){responsex = get_mining();}
	
			else{responsex = "x002";}


		}catch (Exception e) {e.printStackTrace(); responsex = "x100";}


	}catch (Exception e) {e.printStackTrace(); responsex = "x500";}



		JSONObject obj = new JSONObject();
		obj.put("response", responsex);

		StringWriter out = new StringWriter();
		obj.writeJSONString(out);
		jsonText = out.toString();
		System.out.println("SEND RESPONSE " + responsex);

           
	//System.out.println("SERVER GETS: " + clientSentence);             
	//capitalizedSentence = clientSentence.toUpperCase() + '\n';             
	outToClient.writeBytes(jsonText + '\n'); 


	welcomeSocket.close();


	if(wait_send_file == 1){start_file_server(idx);}

	}catch(Exception e){e.printStackTrace();}



}//*****while




}//*****************************************************************************








	public String get_mining(){

	String jsonText = new String("");

	try{

		JSONObject obj = new JSONObject();
		obj.put("url", mcl.settingsx[6]);
		obj.put("user", mcl.settingsx[7]);
		obj.put("pass", mcl.settingsx[8]);

		StringWriter out = new StringWriter();
		obj.writeJSONString(out);
		jsonText = out.toString();


	}catch(Exception e){e.printStackTrace();}

	return jsonText;

	}//**********************








	public String get_content(String jsonc){

	System.out.println("Provider is sending content...");

	String jsonText = new String("");



	try{


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonc);
		JSONObject jsonObject = (JSONObject) obj;

		push_sig = (String) jsonObject.get("push_sig");
		System.out.println("JSON " + push_sig);

		id = (String) jsonObject.get("id");
		System.out.println("JSON " + id);

		date = (String) jsonObject.get("date");
		System.out.println("JSON " + date);

		description = (String) jsonObject.get("description");
		System.out.println("JSON " + description);

		content = (String) jsonObject.get("content");
		System.out.println("JSON " + content);

		save_id = (String) jsonObject.get("save_id");
		System.out.println("JSON " + save_id);

		signature = (String) jsonObject.get("signature");
		System.out.println("JSON " + signature);



	Boolean signt = test_sig_byte(id, push_sig);
	System.out.println("signt " + signt);

	int my_content = mcl.content[0].length;


	System.out.println("new item " + id);
	System.out.println("my_content " + my_content);
	System.out.println("last item " + mcl.content[0][(my_content -1)]);


	if(Integer.parseInt(id) == my_content && signt == true){//*****************************


	System.out.println("Yes wait for new content. open port.");

	jsonText = "yes";


	//start the client
	toolkit = Toolkit.getDefaultToolkit();
	xtimerx = new Timer();
	xtimerx.schedule(new RemindTask_server_file(), 0);


	}//************************************************************************************
	else{System.out.println("Pushed content cannot be verified!");}

	}catch(Exception e){e.printStackTrace();}


	

	return jsonText;

	}//***********************









	class RemindTask_server_file extends TimerTask{
	Runtime rxrunti = Runtime.getRuntime();

	public void run(){//**************************************************************************************

		System.out.println("Wait for IMPORT");



		//save new item
	
		int oldi = mcl.content[0].length;
		int newi = mcl.content[0].length + 1;
		String[][] newc = new String[mcl.content.length][newi];

		int runx = 0;

		for(int xloop0 = 0; xloop0 < oldi; xloop0++){//*************

		newc[0][xloop0] = mcl.content[0][xloop0];
		newc[1][xloop0] = mcl.content[1][xloop0];
		newc[2][xloop0] = mcl.content[2][xloop0];
		newc[3][xloop0] = mcl.content[3][xloop0];
		newc[4][xloop0] = mcl.content[4][xloop0];
		newc[5][xloop0] = mcl.content[5][xloop0];

		runx++;

		}//*********************************************************




		try{

		newc[0][runx] = new String(id);
		newc[1][runx] = new String(date);
		newc[2][runx] = new String(description);
		newc[3][runx] = new String(content);
		newc[4][runx] = new String(save_id);
		newc[5][runx] = new String(signature);

		if(!newc[4][runx].equals("[[url]]")){newc[3][runx] = start_import_server(newc[4][runx]);}


		System.out.println(newc[3][runx]);
		System.out.println(newc[5][runx]);

		Boolean signk = test_sig_b64(newc[3][runx], newc[5][runx]);

		if(signk == true){
		mcl.content = newc;
		mcl_save_db_content save_content = new mcl_save_db_content(runx);
		}//***************
		else{JOptionPane.showMessageDialog(null, "Content cannot be verified!");}


		File file = new File(System.getProperty("user.dir") + "\\content\\" + newc[4][runx] + ".download");
	        System.out.println(file.exists() + "!!");
		System.out.println("DELETE FILE.." + file.delete());


		}catch(Exception e){e.printStackTrace();}





	}//runx***************************************************************************************************
        }//remindtask










	public Boolean test_sig_byte(String content, String sigx1){

	Boolean returnx = false;

	try{

	//System.out.println("content " + content);

        byte[] message = content.getBytes();

	byte[] clear3 = Base64.decode(mcl.settingsx[5]);
    	X509EncodedKeySpec keySpec3 = new X509EncodedKeySpec(clear3);
    	KeyFactory fact3 = KeyFactory.getInstance("RSA");
    	PublicKey publ3 = fact3.generatePublic(keySpec3);
    	Arrays.fill(clear3, (byte) 0);

	byte[] signatureBytesx = Base64.decode(sigx1);

    	Signature sigx3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
        sigx3.initVerify(publ3);
        sigx3.update(message);


	returnx = sigx3.verify(signatureBytesx);
        System.out.println(">> S >> " + returnx);

	}catch(Exception e){e.printStackTrace();}



	return returnx;

	}//***********************






	public Boolean test_sig_b64(String content, String sigx1){

	Boolean returnx = false;

	try{

	//System.out.println("content " + content);

        byte[] message = Base64.decode(content);

	byte[] clear3 = Base64.decode(mcl.settingsx[5]);
    	X509EncodedKeySpec keySpec3 = new X509EncodedKeySpec(clear3);
    	KeyFactory fact3 = KeyFactory.getInstance("RSA");
    	PublicKey publ3 = fact3.generatePublic(keySpec3);
    	Arrays.fill(clear3, (byte) 0);

	byte[] signatureBytesx = Base64.decode(sigx1);

    	Signature sigx3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
        sigx3.initVerify(publ3);
        sigx3.update(message);


	returnx = sigx3.verify(signatureBytesx);
        System.out.println(">> S >> " + returnx);

	}catch(Exception e){e.printStackTrace();}



	return returnx;

	}//***********************


















	public String export_content(String idx){


	String jsonText = new String("");

	int idxh = Integer.parseInt(idx);


	    try{

		JSONObject obj = new JSONObject();
		obj.put("id", mcl.content[0][idxh]);
		obj.put("date", mcl.content[1][idxh]);
		obj.put("description", mcl.content[2][idxh]);

		//content file or URL
		if(!mcl.content[4][idxh].equals("[[url]]")){wait_send_file = 1; obj.put("content", Long.toString(mcl.content[3][idxh].length()));}
		else{obj.put("content", mcl.content[3][idxh]);}

		obj.put("save_id", mcl.content[4][idxh]);
		obj.put("signature", mcl.content[5][idxh]);

		StringWriter out = new StringWriter();
		obj.writeJSONString(out);
		jsonText = out.toString();



	    }catch(Exception e){e.printStackTrace();}





	return jsonText;


	}//**************************************











	public void start_file_server(String idx){

	int idxh = Integer.parseInt(idx);

    	FileInputStream fis = null;
    	BufferedInputStream bis = null;
    	OutputStream os = null;
    	ServerSocket servsock = null;
    	Socket sock = null;

        System.out.println("Waiting...");

        try {

      	  servsock = new ServerSocket((server_port_connect + 1));

          sock = servsock.accept();
          System.out.println("Accepted connection : " + sock);
          // send file
          //File myFile = new File (FILE_TO_SEND);

          //byte[] mybytearray = new byte[(int)myFile.length()];

          //fis = new FileInputStream(myFile);
          //bis = new BufferedInputStream(fis);
          //bis.read(mybytearray,0,mybytearray.length);


	  //System.out.println("send " + mcl.content[3][idxh]);


	  mcl_load_db_file filef = new mcl_load_db_file();
	  String sendx_file = filef.load(Integer.parseInt(mcl.content[0][idxh]));
	  System.out.println(sendx_file);


	  byte[] mybytearray = Base64.decode(sendx_file);
	  //byte[] mybytearray = mcl.content[3][idxh].getBytes();

          os = sock.getOutputStream();
          System.out.println("Sending (" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Done.");


      	  if(servsock != null) servsock.close();
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();

        }catch(Exception e){e.printStackTrace();}


        System.out.println("Exit.");



	}//*****************************










	public String start_import_server(String save_name){

	String base64_return = new String();
	int bytesRead;
    	int current = 0;
    	FileOutputStream fos = null;
    	BufferedOutputStream bos = null;
    	ServerSocket servsock = null;
    	Socket sock = null;

        System.out.println("Waiting...");


        try {

      	servsock = new ServerSocket((server_port_connect + 1));

        sock = servsock.accept();
        System.out.println("Accepted connection : " + sock);


      	// receive file
      	byte[] mybytearray = new byte[FILE_SIZE];
      	InputStream is = sock.getInputStream();
      	fos = new FileOutputStream(System.getProperty("user.dir") + "\\content\\" + save_name + ".download");
      	bos = new BufferedOutputStream(fos);
      	bytesRead = is.read(mybytearray,0,mybytearray.length);
      	current = bytesRead;

     	   do{
           bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
           if(bytesRead >= 0) current += bytesRead;
      	   }while(bytesRead > -1);


      	   bos.write(mybytearray, 0 , current);
      	   bos.flush();
      	   System.out.println("File downloaded (" + current + " bytes read)");


	if (servsock != null) {servsock.close();}
      	if (fos != null) {fos.close();}
      	if (bos != null) {bos.close();}
      	if (sock != null) {sock.close();}

	base64_return = get_file(System.getProperty("user.dir") + "\\content\\" + save_name + ".download");


        }catch(Exception e){e.printStackTrace();}


        System.out.println("Exit.");

	return base64_return;

	}//*****************************








	public String get_file(String file1){


	String content1 = new String("");

	try{

        File file = new File(file1);
 
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

        } catch (IOException ex) {System.out.println("ERROR 1234");}


        	byte[] bytes = bos.toByteArray();
 
		content1 = Base64.toBase64String(bytes);

		System.out.println("size " + content1.length());

		fis.close();
		bos.close();


	}catch(Exception e){e.printStackTrace();}


	return content1;

	}//********************







}//last
