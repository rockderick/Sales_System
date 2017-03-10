
package mx.com.iusacell.catalogo.utilerias;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import mx.com.iusacell.catalogo.utilerias.crypto.CryptoUtils;

/**
 * This class is used so that the SAEO system can login
 * using the Logon Webservice (Verifica Usuario Service) 
 * 
 * @author rj148m
 * @version: 29/11/2016/A
 *
 */
public class MasterKey {
	

	
	final static Logger log = Logger.getLogger(MasterKey.class);
	

	/**
	 * @param loginUser
	 * @param loginPassword
	 * @param ip
	 * @return
	 */
	public boolean validateKey(String loginUser, String loginPassword, String ip){
		
	
		boolean validKey=false; 
		
		  // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory;
        SOAPConnection soapConnection;
		try {
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
			
			 soapConnection = soapConnectionFactory.createConnection();

		        // Send SOAP Message to SOAP Server
		        
                
			 /*
			  *QA URL address 
			  String url = "http://10.203.24.211:39008/VerificaUsuarioService/svAutenticacion";
			  *
			  **/
			 
			 //String url = "http://10.203.24.211:39008/VerificaUsuarioService/svAutenticacion";
			 
             /*
              * Production URL address
              */	
			 String url = "http://10.188.17.250:38111/VerificaUsuarioService/svAutenticacion";
		     SOAPMessage soapResponse = soapConnection.call(createSOAPRequestMasterKey(loginUser,loginPassword,ip), url);
              
			    
		        // print SOAP Response
		        System.out.print("Response SOAP Message:");
		        soapResponse.writeTo(System.out);
		        System.out.println("Soap Response: "+soapResponse.getSOAPBody());
		        //log.info("Soap Response: "+soapResponse.getSOAPBody());
		        
		        //ByteArrayOutputStream out = new ByteArrayOutputStream();
		        //soapResponse.writeTo(out);
		        //String strMsg = new String(out.toByteArray());
		        //System.out.println("strMsg: "+strMsg);
		        System.out.println("Response: "+soapResponse.getSOAPBody());
		        log.info("Response: "+soapResponse.getSOAPBody());
		        
		        String test = soapResponse.getSOAPBody().toString();
		        ValidacionLlaveVO keyValidation = mapperWsVo(test);
		        //System.out.println("\n User auth: "+keyValidation.getAuth());
		        validKey = keyValidation.getAuth();
		        log.info("\n User auth: "+validKey);
		        
		        soapConnection.close();
		        
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
	         
	         
		return validKey;
	}
	
	
	 /**
	 * Returns a SOAPMessage that it is the response from the webservice 
	 * in http://services.auth.iusacell.com.mx  
	 * the service  in QA is the following URL: http://10.203.24.211:39008/VerificaUsuarioService/svAutenticacion?wsdl
	 * and for production purposes the URL is:  http://10.188.17.250:39111/VerificaUsuarioService/svAutenticacion?wsdl 
	 * @param loginUser	The user to validate		
	 * @param loginPassword The password to validate
	 * @param ip The ip of the host that executes the webservice
	 * @return   The response in a SOAPMessage response 
	 * @throws Exception
	 */
	private static SOAPMessage createSOAPRequestMasterKey(String loginUser, String loginPassword, String ip) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://services.auth.iusacell.com.mx";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("ser", serverURI);

        /*
         Constructed SOAP Request Message:
              <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.auth.iusacell.com.mx">
              <soapenv:Header/>
              <soapenv:Body>
              	<ser:ValidaLlaveMaestraIusacell>
              		<ser:in0>?</ser:in0>
              		<ser:in1>?</ser:in1>
              		<ser:in2>?</ser:in2>
              		<ser:in3>?</ser:in3>
              		<ser:in4>?</ser:in4>
              		<ser:in5>?</ser:in5>
              		<ser:in6>?</ser:in6>
              		<ser:in7>?</ser:in7>
              		</ser:ValidaLlaveMaestraIusacell>
             </soapenv:Body>
             </soapenv:Envelope>
		     
		     in0 Usuario
		     in1 Contraseña
		     in2 Id de Aplicación
		     in3 Llave de la aplicacion
		     in4 IP
		     in5 Número de Empleado
		     in6 Llave Maestra
		     in7 Token de Seguridad
         */        
        
       
        String user = "/esmfxtAcJam1PtmdnsCr3sP=/";  //Usuario
        String password = "/esmfxtAcJam1PtmdnsCr3sP=/"; //Contraseña
        
        /*
         * QA  Web service data
         * String keyAplication = "QIwh0xvjuz#zr=eskqW0Xxc7*r=J+A"; //Llave aplicacion
         * String idApplication="47";         
         */
        
        //String keyAplication = "QIwh0xvjuz#zr=eskqW0Xxc7*r=J+A"; //Llave aplicacion
        //String idApplication="47";   
        
        /*
         * Production Web service data 
         */
        String keyAplication = "lFB*@DGfv5xh(b6F+IvwYSTCSB0wgT"; //Llave aplicacion
        String idApplication="30";
        
        byte keyUser [] = user.substring(0,16).getBytes();  
        byte kcAplication [] = keyAplication.substring(0,16).getBytes();
        
        
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("ValidaLlaveMaestraIusacell", "ser");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("in0", "ser");
        soapBodyElem1.addTextNode(user);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("in1", "ser");
        soapBodyElem2.addTextNode(password);
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("in2", "ser");
        soapBodyElem3.addTextNode(idApplication);
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("in3", "ser");
        //the key is precomputed cause bad use of cryptography give always the same value
        // it is a waste calculating the cipher text so it is precalculated
        // given the following after encrypting result: PRZSw5PPxFDq1bXG2I/ZngEspfHaleGA42kD2NjwM10=
        //CryptoUtils.encrypt("QIwh0xvjuz#zr=eskqW0Xxc7*r=J+A",keyUser); the key is precomputed
        // QA soapBodyElem4.addTextNode("PRZSw5PPxFDq1bXG2I/ZngEspfHaleGA42kD2NjwM10=");
        //soapBodyElem4.addTextNode(CryptoUtils.encrypt("QIwh0xvjuz#zr=eskqW0Xxc7*r=J+A",keyUser));
        //soapBodyElem4.addTextNode("PRZSw5PPxFDq1bXG2I/ZngEspfHaleGA42kD2NjwM10=");
        soapBodyElem4.addTextNode("q0kbnIbvYEQnjWP56zB/FyGDhHp9FmBGZQ/eoSQi5p4="); //Production
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("in4", "ser");
        soapBodyElem5.addTextNode(ip);
        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("in5", "ser");
        soapBodyElem6.addTextNode(loginUser);
        SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("in6", "ser");
        soapBodyElem7.addTextNode(CryptoUtils.encrypt(loginPassword,kcAplication));
        SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("in7", "ser");
        soapBodyElem8.addTextNode(CryptoUtils.encrypt(tokenGeneration(loginUser),keyUser));

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + "ValidaLlaveMaestraIusacell");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message:");
        //log.info("Request SOAP Message: ");
        soapMessage.writeTo(System.out);
        //System.out.println();
        

        return soapMessage;
    }
	 
	
	/**
	 * Maps the response from the VerificaUsuarioService to a ValueObject
	 * named ValidacionLlaveVO. The response comes in XML format.
	 * @param ws_response  A String that represent the response in XML format 
	 * @return 			   returns a ValidacionLlaveVO that holds the information of the employee and the masterkey(Llave maestra)
	 * @throws Exception
	 */
	private static ValidacionLlaveVO mapperWsVo(String ws_response) throws Exception{

	     ValidacionLlaveVO llaveMaestra =new ValidacionLlaveVO();


	     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	     domFactory.setNamespaceAware(true);
	     DocumentBuilder builder = domFactory.newDocumentBuilder();
	     System.out.println("ws_response: "+ws_response);
	     Document body = builder.parse(new InputSource(new StringReader(ws_response)));

	     NodeList returnList = body.getElementsByTagName("soap:Body");
	     Node node = null;

	     if(returnList != null && returnList.getLength() > 0){
	      NodeList ns1List = returnList.item(0).getChildNodes();

	      if(ns1List != null && ns1List.getLength() > 0){

	       node= ns1List.item(0);
	       NodeList ns0List = node.getChildNodes();

	       if(ns0List != null && ns0List.getLength() > 0){
	        node = ns0List.item(0);
	        NodeList returnList3 = node.getChildNodes();

			     for(int j=0; j < returnList3.getLength(); j++){
		
			      Node node3 = (Node) returnList3.item(j);
		
			      if(node3.getNodeName().equals("auth") && node3.getFirstChild() != null){
			      	 
			      	 llaveMaestra.setAuth(toBoolean(node3.getFirstChild().getNodeValue(), node3.getNodeName()));
			      	
			      }
			      if(node3.getNodeName().equals("cambioLlave") && node3.getFirstChild() != null){
				        llaveMaestra.setCambioLlave(toBoolean(node3.getFirstChild().getNodeValue(), node3.getNodeName()));
				      }
			      if(node3.getNodeName().equals("fechaCad") && node3.getFirstChild()!= null){
				       llaveMaestra.setFechaCad(node3.getFirstChild().getNodeValue());
				      }
			      if(node3.getNodeName().equals("mensaje") && node3.getFirstChild() != null){
			       llaveMaestra.setMensaje(node3.getFirstChild().getNodeValue());
			      }
			      
			   }
	        }
	      }

	     }

	  return llaveMaestra;
	} 
	 

   
	 /**
	 * This token is generated with the concatenation of the
     * date in UTC format plus the employee number (numero de empleado)
	 * @param user The user of that want to access to the SAEO system
	 * @return
	 */
	private static String tokenGeneration(String user)
	 {
	    	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			Date fechaAct = new Date();
			SimpleDateFormat isoFormat = new SimpleDateFormat("ddMMyyyyHHmm");
			
			return isoFormat.format(fechaAct) + user;
			
	 }
	 
	 
	 /**
	 * A simple method that takes a String that has two possible values (True, False) and
	 *  a convert in to a boolean value. This method it is used to parse the value from
	 *  the xml label that comes from the response of the webservice
	 * @param booleanString A boolean String (true,false)
	 * @param label	The label that comes fromo the webservice. 	
	 * @return
	 * @throws Exception
	 */
	private static boolean toBoolean(String booleanString,String label) throws Exception {
		try {
			return Boolean.valueOf(booleanString).booleanValue();
		} catch (IllegalArgumentException e) {
			throw new Exception(
					"La respuesta del servicio no tiene el formato esperado. El Campo " + label + "no es un tipo boleano");
		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
		
		
	}


}

