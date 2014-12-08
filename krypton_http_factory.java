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






public class krypton_http_factory{

static String url_api = new String("http://www.alchemistwebshops.com/kr/api_system_v1.php");






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

}//***************************************************









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


	String voodoo = new String(message);
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

		voodoo = Base64.toBase64String( encrypt( message.getBytes() ,keyx1.getBytes(),iv2) );

		System.out.println(voodoo);


	}catch(Exception e){e.printStackTrace();}



//RSA cipher




           data = URLEncoder.encode("request_id", "UTF-8")    + "=" + URLEncoder.encode("send", "UTF-8");
    data += "&" + URLEncoder.encode("user_to", "UTF-8")       + "=" + URLEncoder.encode(address, "UTF-8");
    data += "&" + URLEncoder.encode("user_from", "UTF-8")     + "=" + URLEncoder.encode(return_address, "UTF-8");
    data += "&" + URLEncoder.encode("message_body", "UTF-8")  + "=" + URLEncoder.encode(voodoo, "UTF-8");
    data += "&" + URLEncoder.encode("rsa_iv", "UTF-8")        + "=" + URLEncoder.encode(key_iv, "UTF-8");
    data += "&" + URLEncoder.encode("message_sig", "UTF-8")   + "=" + URLEncoder.encode(signxx, "UTF-8");


    // Send data
    System.out.println("KRYPTON CMD SEND");
    url = new URL(url_api);
    conn = url.openConnection();
    conn.setDoOutput(true);
    wr = new OutputStreamWriter(conn.getOutputStream());
    wr.write(data);
    wr.flush();

    // Get the response
    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    while ((line = rd.readLine()) != null) {

	out = line;

    }//*************************************
    wr.close();
    rd.close();

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




           data = URLEncoder.encode("request_id", "UTF-8") + "=" + URLEncoder.encode("inbox", "UTF-8");
    data += "&" + URLEncoder.encode("user_to", "UTF-8")    + "=" + URLEncoder.encode(address, "UTF-8");


    // Send data
    System.out.println("KRYPTON CMD INBOX");
    url = new URL(url_api);
    conn = url.openConnection();
    conn.setDoOutput(true);
    wr = new OutputStreamWriter(conn.getOutputStream());
    wr.write(data);
    wr.flush();

    // Get the response
    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    while ((line = rd.readLine()) != null) {

	json_messages = line;

    }//*************************************
    wr.close();
    rd.close();




	//System.out.println(json_messages);

	JSONParser parser = new JSONParser();
	Object obj = parser.parse(json_messages);
 
	JSONObject jsonObject = (JSONObject) obj;
  
	String status = (String) jsonObject.get("status").toString();
	String arrmes = (String) jsonObject.get("array").toString();
	//System.out.println("x" + status + "x");
	//System.out.println("x" + xxxxxx + "x");

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


String data;
URL url;
OutputStreamWriter wr;
BufferedReader rd;
URLConnection conn;

String json_messages = new String("");
String[] read_message = new String[5];

read_message[0] = new String("error1");
read_message[1] = new String("error2");
read_message[2] = new String("error3");
read_message[3] = new String("error4");
read_message[4] = new String("error5");



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
    //System.out.println("Public cc 58 : " + Base58.encode(out));
    //System.out.println("Public cc 64 : " + Base64.toBase64String(testx64rip160.getBytes()));
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

    	String signxx = Base64.toBase64String(signatureBytesx);


	byte[] clear2 = Base64.decode(testx64);
    	X509EncodedKeySpec keySpec2 = new X509EncodedKeySpec(clear2);
    	KeyFactory fact2 = KeyFactory.getInstance("RSA");
    	PublicKey publ = fact2.generatePublic(keySpec2);
    	Arrays.fill(clear2, (byte) 0);


        //sigx.initVerify(keyPair.getPublic());
        sigx.initVerify(publ);
        sigx.update(message);

        //System.out.println(sigx.verify(signatureBytesx));



           data = URLEncoder.encode("request_id", "UTF-8") + "=" + URLEncoder.encode("get", "UTF-8");
    data += "&" + URLEncoder.encode("public_key", "UTF-8") + "=" + URLEncoder.encode(publx, "UTF-8");
    data += "&" + URLEncoder.encode("item_id", "UTF-8")    + "=" + URLEncoder.encode(id, "UTF-8");
    data += "&" + URLEncoder.encode("item_sig", "UTF-8")   + "=" + URLEncoder.encode(signxx, "UTF-8");



    // Send data
    System.out.println("KRYPTON CMD GET");
    url = new URL(url_api);
    conn = url.openConnection();
    conn.setDoOutput(true);
    wr = new OutputStreamWriter(conn.getOutputStream());
    wr.write(data);
    wr.flush();

    // Get the response
    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    while ((line = rd.readLine()) != null) {

	//System.out.println(line);
	json_messages = line;

    }
    wr.close();
    rd.close();




	JSONParser parser = new JSONParser();
	Object obj = parser.parse(json_messages);
 
	JSONObject jsonObject = (JSONObject) obj;
  
	String status = (String) jsonObject.get("status").toString();


	if(status.equals("pass")){

		read_message[0] = (String) jsonObject.get("date").toString();
		read_message[1] = (String) jsonObject.get("to").toString();
		try{read_message[2] = (String) jsonObject.get("from").toString();}catch(Exception e){read_message[2] = "";}
		read_message[3] = (String) jsonObject.get("message").toString();
		try{read_message[4] = (String) jsonObject.get("rsa_iv").toString();}catch(Exception e){read_message[4] = "";}

	}//***********************



} catch (Exception e){e.printStackTrace();}




	//decode 

	if(read_message[4].length() > 0){

	try{

	byte[] key = rsa_key.getBytes();
	byte[] iv2 = read_message[4].getBytes();


	String vodoo2 = new String( decrypt(Base64.decode(read_message[3]),keyx1.getBytes(),iv2) );
	//System.out.println(vodoo2);

	read_message[3] = vodoo2;

	}catch(Exception e){e.printStackTrace();}

	}//******************************

	//decode 


return read_message;


}//*****************postx**************************************************************************


















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