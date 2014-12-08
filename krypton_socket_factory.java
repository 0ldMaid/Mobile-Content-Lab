import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Comparator;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.spongycastle.asn1.*;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.asn1.x9.X9IntegerConverter;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.ec.CustomNamedCurves;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.generators.RSAKeyPairGenerator;
import org.spongycastle.crypto.params.*;
import org.spongycastle.crypto.engines.RSAEngine;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.FixedPointUtil;
import org.spongycastle.math.ec.custom.sec.SecP256K1Curve;
import org.spongycastle.util.encoders.Base64;
import org.spongycastle.crypto.signers.PSSSigner;

import org.spongycastle.crypto.params.RSAKeyGenerationParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.spongycastle.crypto.KeyGenerationParameters;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

import org.apache.commons.codec.binary.Hex;






public class krypton_socket_factory{

final protected static char[] hexArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

static String url_socket = new String("localhost");
static int url_port = 9096;










public static PrivateKey getprivatekey(String privt){

PrivateKey priv_e = null;




	try{

		byte[] clear = Base64.decode(privt);
    		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);

    		KeyFactory fact = KeyFactory.getInstance("RSA");

    		PrivateKey priv = fact.generatePrivate(keySpec);
    		Arrays.fill(clear, (byte) 0);

		priv_e = priv;

	}catch(Exception e){e.printStackTrace();}

		return priv_e;


}//**************************************************











public void getKeyPair(){


// Construct data
try {



    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(1024);
    KeyPair keyPair = kpg.genKeyPair();

    System.out.println("");
    System.out.println("privateKey Base 64: " + Base64.toBase64String(keyPair.getPrivate().getEncoded()) );
    System.out.println("");
    System.out.println("Public Base 64: " + Base64.toBase64String(keyPair.getPublic().getEncoded()) );
    System.out.println("");

    String testx64 = new String("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSR1vc7A8lSR6BnkTI7GOALq5GjZsZBRr+pajiICkM4E9bjllEDpPcPY5zuFfIvSDH/KmW0ZoAEEWqyQGekd7/JLwVVXLoNeWaMS0/QQncpQ1cRoWm6fxak0CwCpY5ggjbbSYmAKoq+kClwC2VX635QiHz3jqJb4QhrImEZlJxIwIDAQAB");
    //String testx64 = new String("39a157ff804667b712f1bbb55a822e361e1a7b7e954f6d7dd781b91bfef71829");

		byte[] out = new byte[20];	
		byte[] sha256 = MessageDigest.getInstance("SHA-256").digest(testx64.getBytes());//keyPair.getPublic().getEncoded()

		String testx64sha = new String(byteArrayToHex(sha256));

		RIPEMD160Digest digest = new RIPEMD160Digest();
		digest.update(testx64sha.getBytes(), 0, testx64sha.getBytes().length);
		digest.doFinal(out, 0);

		String testx64rip160 = new String(byteArrayToHex(out));

    System.out.println("Public cc hash : " + byteArrayToHex(out));
    System.out.println("Public cc 58 : " + Base58Encode.encode(out));
    System.out.println("Public cc 64 : " + Base64.toBase64String(testx64rip160.getBytes()));
    System.out.println("");



    byte[] message = "20141024030313".getBytes("UTF8");

    Signature sig = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
    sig.initSign(keyPair.getPrivate());
    sig.update(message);
    byte[] signatureBytes = sig.sign();

    String signx = Base64.toBase64String(signatureBytes);


    System.out.println("");
    System.out.println("Singature: " + signx);
    System.out.println("");

    sig.initVerify(keyPair.getPublic());
    sig.update(message);

    System.out.println(sig.verify(signatureBytes));




} catch (Exception e){e.printStackTrace();}




}//**********************



















public static String postx(PrivateKey priv, String keyx1, String address, String return_address, String message){

String data;
URL url;
OutputStreamWriter wr;
BufferedReader rd;
URLConnection conn;


String out = new String("error");

// Construct data
try {


	byte[] message2 = message.getBytes("UTF8");

    	Signature sigx = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
    	sigx.initSign(priv);
    	sigx.update(message2);
    	byte[] signatureBytesx = sigx.sign();
    	//System.out.println("Public: " + Base64.toBase64String(pub.getEncoded()));
    	//System.out.println("Singature: " + Base64.toBase64String(signatureBytesx));

    	String signxx = Base64.toBase64String(signatureBytesx);




//RSA cipher


	String message_text = new String(message);
	String key_iv = new String("");


	try{


      		//Initialize SecureRandom
      		//This is a lengthy operation, to be done only upon
      		//initialization of the application
      		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

      		//generate a random number
      		String randomNum = new Integer(prng.nextInt()).toString();

      		//get its digest
      		MessageDigest sha = MessageDigest.getInstance("SHA-1");
      		byte[] result =  sha.digest(randomNum.getBytes());

      		System.out.println("Random number: " + randomNum);
      		System.out.println("Message digest: " + byteArrayToHex(result));

		String key1 = byteArrayToHex(result);
		key1 = key1.substring(0, 16);




      		SecureRandom prng2 = SecureRandom.getInstance("SHA1PRNG");
      		String randomNum2 = new Integer(prng2.nextInt()).toString();

      		MessageDigest sha2 = MessageDigest.getInstance("SHA-1");
      		byte[] result2 =  sha2.digest(randomNum2.getBytes());

		String key2 = byteArrayToHex(result2);
		key2 = key2.substring(0, 16);

		key_iv = key2;
		System.out.println("key_iv " + key_iv);

		//encode

		byte[] key = key1.getBytes();
		byte[] iv2 = key2.getBytes();

		message_text = Base64.toBase64String( encrypt( message.getBytes() , keyx1.getBytes(), iv2) );

		System.out.println(message_text);


	}catch(Exception e){e.printStackTrace();}



//RSA cipher





	try{



	String jsonText = new String("");

	JSONObject obj = new JSONObject();
	obj.put("request_id","send");
	obj.put("user_to", address);
	obj.put("user_from", return_address);
	obj.put("message_body", message_text);
	obj.put("rsa_iv", key_iv);
	obj.put("message_sig", signxx);

	StringWriter out_string = new StringWriter();
	obj.writeJSONString(out_string);
	jsonText = out_string.toString();
	System.out.println(jsonText);




	String sentence;   
	String modifiedSentence;   

	BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
	Socket clientSocket = new Socket(url_socket, url_port);   
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
	sentence = jsonText;  
	outToServer.writeBytes(sentence + '\n');   
	modifiedSentence = inFromServer.readLine();
	out = modifiedSentence;
	System.out.println("FROM SERVER: " + modifiedSentence);
	clientSocket.close();

	}catch(Exception e){System.out.println("Socket error"); e.printStackTrace();}



} catch (Exception e){e.printStackTrace();}



return out;

}//*****************postx****************************************************************************************














public static String[] inbox(String address){


String data;
URL url;
OutputStreamWriter wr;
BufferedReader rd;
URLConnection conn;


String json_messages = new String("");
String[] messages = new String[0];



// Construct data
try {


	String jsonText = new String("");

	JSONObject obj = new JSONObject();
	obj.put("request_id","inbox");
	obj.put("user_to", address);

	StringWriter out_string = new StringWriter();
	obj.writeJSONString(out_string);
	jsonText = out_string.toString();
	System.out.println(jsonText);



	String sentence;   
	String modifiedSentence;   

	BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
	Socket clientSocket = new Socket(url_socket, url_port);   
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
	sentence = jsonText;  
	outToServer.writeBytes(sentence + '\n');   
	modifiedSentence = inFromServer.readLine();
	System.out.println("FROM SERVER: " + modifiedSentence);
	clientSocket.close();




	json_messages = modifiedSentence;

	//System.out.println(json_messages);

	JSONParser parser = new JSONParser();
	Object objr = parser.parse(json_messages);
 
	JSONObject jsonObject = (JSONObject) objr;
  
	String status = (String) jsonObject.get("status").toString();
	String arrmes = (String) jsonObject.get("messages").toString();
	System.out.println("x" + status + "x");
	System.out.println("x" + arrmes + "x");

	if(status.equals("pass")){

		Object obj2 = parser.parse(arrmes);
         	JSONArray array = (JSONArray)obj2;

		messages = new String[array.size()];


		for(int loop = 0; loop < array.size(); loop++){

        		//System.out.println(array.get(loop));
			messages[loop] = new String(array.get(loop).toString());

		}//********************************************

	}//***********************
	

} catch (Exception e){e.printStackTrace();}




return messages;


}//*****************postx********************



















public static String[] getid(String privx, String keyx1, String publx, String id, String rsa_key){


//String data;
//URL url;
//OutputStreamWriter wr;
//BufferedReader rd;
//URLConnection conn;

String json_messages = new String("");
String[] read_message = new String[6];
String status = new String("error");

read_message[0] = new String("error1");
read_message[1] = new String("error2");
read_message[2] = new String("error3");
read_message[3] = new String("error4");
read_message[4] = new String("error5");
read_message[5] = new String("error6");


// Construct data
try {



    	String testx64 = publx;
    	//System.out.println(testx64);


	byte[] out = new byte[20];	
	byte[] sha256 = MessageDigest.getInstance("SHA-256").digest(testx64.getBytes());//keyPair.getPublic().getEncoded()

	String testx64sha = new String(byteArrayToHex(sha256));

	RIPEMD160Digest digest = new RIPEMD160Digest();
	digest.update(testx64sha.getBytes(), 0, testx64sha.getBytes().length);
	digest.doFinal(out, 0);

	String testx64rip160 = new String(byteArrayToHex(out));

    	//System.out.println("Public cc hash : " + byteArrayToHex(out));
    	//System.out.println("Public cc 58 : " + Base58Encode.encode(out));
    	//System.out.println("Public cc 64 : " + Base64Encode.toBase64String(testx64rip160.getBytes()));
    	//System.out.println("");




        byte[] message = id.getBytes("UTF8");

	byte[] clear = Base64.decode(privx);
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

	byte[] clear2 = Base64.decode(testx64);
    	X509EncodedKeySpec keySpec2 = new X509EncodedKeySpec(clear2);
    	KeyFactory fact2 = KeyFactory.getInstance("RSA");
    	PublicKey publ2 = fact2.generatePublic(keySpec2);
    	Arrays.fill(clear2, (byte) 0);

        //sigx.initVerify(keyPair.getPublic());
        sigx.initVerify(publ2);
        sigx.update(message);

        System.out.println("SIGN >>> " + sigx.verify(signatureBytesx));
    	String signxx = Base64.toBase64String(signatureBytesx);





// test 2

	byte[] clear3 = Base64.decode(testx64);
    	X509EncodedKeySpec keySpec3 = new X509EncodedKeySpec(clear3);
    	KeyFactory fact3 = KeyFactory.getInstance("RSA");
    	PublicKey publ3 = fact3.generatePublic(keySpec3);
    	Arrays.fill(clear3, (byte) 0);


    	Signature sigx3 = Signature.getInstance("SHA1WithRSA");//MD5WithRSA
        sigx3.initVerify(publ3);
        sigx3.update(message);
        System.out.println(">> S >> " + sigx3.verify(signatureBytesx));










	String jsonText = new String("");

	JSONObject obj = new JSONObject();
	obj.put("request_id","get");
	obj.put("public_key", publx);
	obj.put("item_id", id);
	obj.put("item_sig", signxx);

	StringWriter out_string = new StringWriter();
	obj.writeJSONString(out_string);
	jsonText = out_string.toString();
	System.out.println(jsonText);




	String sentence;   
	String modifiedSentence;   

	BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
	Socket clientSocket = new Socket(url_socket, url_port);   
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
	sentence = jsonText;  
	outToServer.writeBytes(sentence + '\n');   
	modifiedSentence = inFromServer.readLine();
	System.out.println("FROM SERVER: " + modifiedSentence);
	clientSocket.close();

	//System.out.println(modifiedSentence);
	json_messages = modifiedSentence;



	JSONParser parser = new JSONParser();
	Object objr = parser.parse(json_messages);
	JSONObject jsonObject = (JSONObject) objr;
  


	try{
	status = (String) jsonObject.get("status").toString();
	}catch(Exception e){status = "error";}
	




	if(status.equals("pass")){

		String item = (String) jsonObject.get("item").toString();

		JSONParser parser2 = new JSONParser();
		Object objr2 = parser.parse(item);
		JSONObject jsonObject2 = (JSONObject) objr2;

		read_message[0] = (String) jsonObject2.get("id").toString();
		read_message[1] = (String) jsonObject2.get("to").toString();
		try{read_message[2] = (String) jsonObject2.get("from").toString();}catch(Exception e){read_message[2] = "";}
		read_message[3] = (String) jsonObject2.get("message").toString();
		try{read_message[4] = (String) jsonObject2.get("iv").toString();}catch(Exception e){read_message[4] = "";}
		try{read_message[5] = (String) jsonObject2.get("sig").toString();}catch(Exception e){read_message[5] = "";}

	}//***********************

		System.out.println("");
		System.out.println("rm " + read_message[0]);
		System.out.println("rm " + read_message[1]);
		System.out.println("rm " + read_message[2]);
		System.out.println("rm " + read_message[3]);
		System.out.println("rm " + read_message[4]);
		System.out.println("rm " + read_message[5]);
		System.out.println("");



} catch (Exception e){e.printStackTrace();}




	//decode 

	if(read_message[4].length() > 0 && status.equals("pass")){

	    try{

	    	byte[] key = rsa_key.getBytes();
	    	byte[] iv2 = read_message[4].getBytes();


	    	String message_text2 = new String( decrypt(Base64.decode(read_message[3]),keyx1.getBytes(),iv2) );
	    	//System.out.println(message_text2);

	    	read_message[3] = message_text2;

	    }catch(Exception e){e.printStackTrace();}

	}//******************************

	//decode 


return read_message;


}//*****************postx*************************************************************************


















//tools








private static byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data) throws Exception{

    int minSize = cipher.getOutputSize(data.length);
    byte[] outBuf = new byte[minSize];
    int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);
    int length2 = cipher.doFinal(outBuf, length1);
    int actualLength = length1 + length2;
    byte[] result = new byte[actualLength];
    System.arraycopy(outBuf, 0, result, 0, result.length);
    return result;
}

private static byte[] decrypt(byte[] cipher, byte[] key, byte[] iv) throws Exception{

    PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
    CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
    aes.init(false, ivAndKey);
    return cipherData(aes, cipher);
}

private static byte[] encrypt(byte[] plain, byte[] key, byte[] iv) throws Exception{

    PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
    CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
    aes.init(true, ivAndKey);
    return cipherData(aes, plain);
}








byte[] sh256e(byte[] pub){


		 byte[] out = new byte[20];

		 try {

			 byte[] sha256 = MessageDigest.getInstance("SHA-256").digest(pub);//.getEncoded()
			 RIPEMD160Digest digest = new RIPEMD160Digest();
			 digest.update(sha256, 0, sha256.length);

			 digest.doFinal(out, 0);
			 
		} catch (Exception e) {
			 throw new RuntimeException(e); // Cannot happen.
		}
		
		
		return out;



}//******************




static final String HEXES = "0123456789ABCDEF";
public static String getHex( byte [] raw ) {
    if ( raw == null ) {
        return null;
    }
    final StringBuilder hex = new StringBuilder( 2 * raw.length );
    for ( final byte b : raw ) {
        hex.append(HEXES.charAt((b & 0xF0) >> 4))
            .append(HEXES.charAt((b & 0x0F)));
    }
    return hex.toString();
}



public static String bytesToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for ( int j = 0; j < bytes.length; j++ ) {
        int v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
}



public static String byteArrayToHex(byte[] a) {
   StringBuilder sb = new StringBuilder(a.length * 2);
   for(byte b: a)
      sb.append(String.format("%02x", b & 0xff));
   return sb.toString();
}


public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
    }
    return data;
}










}//last