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





public class mcl_push{


public final static int FILE_SIZE = 6022386;
public final static String FILE_TO_RECEIVE = "";  // you may change this

Timer xtimerx;//class loop.
Toolkit toolkit;

String client_address_connect = new String("127.0.0.1");
int client_port_connect = 10055;







mcl_push(){//*******************************************************************



	System.out.println("Push content to krypton node.");

	String status = test_server();

	if(status.equals("active")){

		int remote_content = Integer.parseInt(test_content_list());

		System.out.println("content list " + remote_content);

		if(remote_content > mcl.content[0].length){JOptionPane.showMessageDialog(null, "ERROR Remote server has more content then this database!");}
		else if(remote_content == mcl.content[0].length){JOptionPane.showMessageDialog(null, "Remote server is up to date with your content.");}
		else{

		   int my_content = mcl.content[0].length -1;

		   String push_file = push_content(my_content);

		   if(push_file.equals("yes")){send_content_file(mcl.content[0][my_content]);}		

		}////

	}//*************************
	else{JOptionPane.showMessageDialog(null, "Cannot find relay node!");}
	


}//*****************************************************************************









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











	public String test_content_list(){

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













	public String sign_message(String text){

	String returnx = new String("");

	try{

        byte[] message = text.getBytes();

	byte[] clear = Base64.decode(mcl.settingsx[0]);
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

	byte[] clear2 = Base64.decode(mcl.settingsx[1]);
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

        byte[] message2 = text.getBytes();

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













	public String push_content(int idx){

	System.out.println("Push Content...");

	String jsonText = new String("");
	String jsonTextx = new String("");

	int idxh = idx;


	    try{


		//sign the new item
		System.out.println("idx " + idxh);
		String signx = sign_message(mcl.content[0][idxh]);
		System.out.println("signx " + signx);

		JSONObject obj = new JSONObject();
		obj.put("push_sig", signx);




		obj.put("id", mcl.content[0][idxh]);
		obj.put("date", mcl.content[1][idxh]);
		obj.put("description", mcl.content[2][idxh]);

		//content file or URL
		if(!mcl.content[4][idxh].equals("[[url]]")){obj.put("content", Long.toString(mcl.content[3][idxh].length()));}
		else{obj.put("content", mcl.content[3][idxh]);}

		obj.put("save_id", mcl.content[4][idxh]);
		obj.put("signature", mcl.content[5][idxh]);

		StringWriter out = new StringWriter();
		obj.writeJSONString(out);
		jsonText = out.toString();



		JSONObject objx = new JSONObject();
		objx.put("request", "push");
		objx.put("pushc", jsonText);
		
		StringWriter outx = new StringWriter();
		objx.writeJSONString(outx);
		jsonTextx = outx.toString();



	    }catch(Exception e){e.printStackTrace();}




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
	sentence = jsonTextx;  
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


	}//**************************************













	public void send_content_file(String idx){


	int idxh = Integer.parseInt(idx);

	System.out.println("[Get File]");

	mcl_send_db_content sendx = new mcl_send_db_content(idxh);


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
