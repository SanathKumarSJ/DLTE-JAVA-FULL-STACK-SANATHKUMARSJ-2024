package web;

import javax.xml.ws.Endpoint;

public class EndPoint {

    // mention the URL
    public static String url="http://localhost:1231/dao";

    public static void main(String[] args) {
        // create object for Soap service

        SOAPService userSoap = new SOAPService();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url, userSoap);

    }
}
