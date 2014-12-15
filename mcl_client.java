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

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.spongycastle.util.encoders.Base64;





public class mcl_client{


public final static int FILE_SIZE = 6022386;
public final static String FILE_TO_RECEIVE = "";  // you may change this

Timer xtimerx;//class loop.
Toolkit toolkit;

String client_address_connect = new String("127.0.0.1");
int client_port_connect = 10055;







mcl_client(){//*****************************************************************


	while(true){   

	System.out.println("MCL Network Client...");



	   try{

		String test = test_server();
		get_mining_info();

		if(test.equals("active")){

		String list = test_content();

		    System.out.println("list " + list);

			if(Integer.parseInt(list) > mcl.content[0].length){

				get_content( (mcl.content[0].length) );

			}//************************************************
			else{System.out.println("Content up to date.");}


		}//***********************



	   }catch(Exception e){e.printStackTrace();}


	try{Thread.sleep(10000);} catch (InterruptedException e){}


	}//*****while

}//*****************************************************************************







	public void get_mining_info(){

	String jsonText = new String("");

	try{

	JSONObject obj = new JSONObject();
	obj.put("request","mining");

	StringWriter out = new StringWriter();
	obj.writeJSONString(out);
	jsonText = out.toString();
	System.out.println(jsonText);

	}catch(Exception e){System.out.println("JSON ERROR");}


	String sentence = new String("");
	String modifiedSentence = new String("");  
	String JsonSentence = new String(""); 


	try{

	System.out.println("address: " + client_port_connect);
	System.out.println("address: " + client_address_connect);

	BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
	Socket clientSocket = new Socket("localhost", client_port_connect);   
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
	sentence = jsonText;  
	outToServer.writeBytes(sentence + '\n');   
	modifiedSentence = inFromServer.readLine();   
	System.out.println("FROM SERVER: " + modifiedSentence);
	clientSocket.close();


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(modifiedSentence);
		JSONObject jsonObject = (JSONObject) obj;
  
		String response = (String) jsonObject.get("response");
		System.out.println("JSON " + response);



		JSONParser parser2 = new JSONParser();
		Object obj2 = parser.parse(response);
		JSONObject jsonObject2 = (JSONObject) obj2;
  
		String url = (String) jsonObject2.get("url");
		System.out.println("JSON " + url);

		String user = (String) jsonObject2.get("user");
		System.out.println("JSON " + user);

		String pass = (String) jsonObject2.get("pass");
		System.out.println("JSON " + pass);


		mcl.settingsx[6] = url;
		mcl.settingsx[7] = user;
		mcl.settingsx[8] = pass;



	}catch(Exception e){System.out.println("Cannot find node!"); JsonSentence = "not active!";}

	}//***************************



















	public String test_server(){

	String jsonText = new String("");


	try{

	JSONObject obj = new JSONObject();
	obj.put("request","status");

	StringWriter out = new StringWriter();
	obj.writeJSONString(out);
	jsonText = out.toString();
	System.out.println(jsonText);

	}catch(Exception e){System.out.println("JSON ERROR");}


	String sentence = new String("");
	String modifiedSentence = new String("");  
	String JsonSentence = new String(""); 


	try{

	System.out.println("address: " + client_port_connect);
	System.out.println("address: " + client_address_connect);

	BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
	Socket clientSocket = new Socket("localhost", client_port_connect);   
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
	sentence = jsonText;  
	outToServer.writeBytes(sentence + '\n');   
	modifiedSentence = inFromServer.readLine();   
	System.out.println("FROM SERVER: " + modifiedSentence);
	clientSocket.close();


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(modifiedSentence);
		JSONObject jsonObject = (JSONObject) obj;
  
		String response = (String) jsonObject.get("response");
		System.out.println("JSON " + response);

		JsonSentence = response;
		jsonText = JsonSentence;

	}catch(Exception e){System.out.println("Cannot find node!"); JsonSentence = "not active!";}



	return jsonText;


	}//************************











	public String test_content(){

	String jsonText = new String("");


	try{

	JSONObject obj = new JSONObject();
	obj.put("request","content_list");

	StringWriter out = new StringWriter();
	obj.writeJSONString(out);
	jsonText = out.toString();
	System.out.println(jsonText);

	}catch(Exception e){System.out.println("JSON ERROR");}


	String sentence = new String("");
	String modifiedSentence = new String("");  
	String JsonSentence = new String(""); 


	try{

	System.out.println("address: " + client_port_connect);
	System.out.println("address: " + client_address_connect);

	BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
	Socket clientSocket = new Socket("localhost", client_port_connect);   
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
	sentence = jsonText;  
	outToServer.writeBytes(sentence + '\n');   
	modifiedSentence = inFromServer.readLine();   
	System.out.println("FROM SERVER: " + modifiedSentence);
	clientSocket.close();


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(modifiedSentence);
		JSONObject jsonObject = (JSONObject) obj;
  
		String response = (String) jsonObject.get("response");
		System.out.println("JSON " + response);

		JsonSentence = response;
		jsonText = JsonSentence;

	}catch(Exception e){System.out.println("Cannot find node!"); JsonSentence = "not active!";}




	return jsonText;

	}//************************

















	public void get_content(int content_id){

	String jsonText = new String("");


	try{

	JSONObject obj = new JSONObject();
	obj.put("request","get_idx");
	obj.put("id", Integer.toString(content_id));

	StringWriter out = new StringWriter();
	obj.writeJSONString(out);
	jsonText = out.toString();
	System.out.println(jsonText);

	}catch(Exception e){System.out.println("JSON ERROR");}


	String sentence = new String("");
	String modifiedSentence = new String("");  
	String JsonSentence = new String(""); 


	try{

	System.out.println("address: " + client_port_connect);
	System.out.println("address: " + client_address_connect);

	BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
	Socket clientSocket = new Socket("localhost", client_port_connect);   
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
	sentence = jsonText;  
	outToServer.writeBytes(sentence + '\n');   
	modifiedSentence = inFromServer.readLine();   
	System.out.println("FROM SERVER: " + modifiedSentence);
	clientSocket.close();


		JSONParser parserx = new JSONParser();
		Object objx = parserx.parse(modifiedSentence);
		JSONObject jsonObjectx = (JSONObject) objx;
  
		String response = (String) jsonObjectx.get("response");
		System.out.println("JSON " + response);



		JSONParser parser = new JSONParser();
		Object obj = parser.parse(response);
		JSONObject jsonObject = (JSONObject) obj;

		String id = (String) jsonObject.get("id");
		System.out.println("JSON " + id);

		String date = (String) jsonObject.get("date");
		System.out.println("JSON " + date);

		String description = (String) jsonObject.get("description");
		System.out.println("JSON " + description);

		String content = (String) jsonObject.get("content");
		System.out.println("JSON " + content);

		String save_id = (String) jsonObject.get("save_id");
		System.out.println("JSON " + save_id);

		String signature = (String) jsonObject.get("signature");
		System.out.println("JSON " + signature);




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

		if(!newc[4][runx].equals("[[url]]")){newc[3][runx] = get_content_file(newc[4][runx]);}



		Boolean signk = test_sig(newc[3][runx], newc[5][runx]);

		if(signk == true){
		mcl.content = newc;
		mcl_save_db_content save_content = new mcl_save_db_content(runx);
		}//***************
		else{JOptionPane.showMessageDialog(null, "Content cannot be verified!");}


		File file = new File(System.getProperty("user.dir") + "\\content\\" + newc[4][runx] + ".download");
	        System.out.println(file.exists() + "!!");
		System.out.println("DELETE FILE.." + file.delete());




		}catch(Exception e){e.printStackTrace();}


	}catch(Exception e){e.printStackTrace();}





	}//************************











	public Boolean test_sig(String content, String sigx1){

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













	public String get_content_file(String save_name){

	System.out.println("[Get File]");

	String base64_return = new String();
	int bytesRead;
    	int current = 0;
    	FileOutputStream fos = null;
    	BufferedOutputStream bos = null;
    	Socket sock = null;


    	try {
      	sock = new Socket("localhost", (client_port_connect + 1));
      	System.out.println("Connecting...");

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




	base64_return = get_file(System.getProperty("user.dir") + "\\content\\" + save_name + ".download");

	

      	if (fos != null) fos.close();
      	if (bos != null) bos.close();
      	if (sock != null) sock.close();

    	}catch(Exception e){e.printStackTrace();}





	return base64_return;

	}//************************










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
