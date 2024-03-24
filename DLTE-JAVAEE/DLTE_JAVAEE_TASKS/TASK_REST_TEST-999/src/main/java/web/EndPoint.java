package web;

import javax.xml.ws.Endpoint;

public class EndPoint {

    // mentioning the url
    private static String url = "http://localhost:1212/transaction";

    public static void main(String[] args) {
        // creating Transaction Soap object
        TransactionSoap userSoap = new TransactionSoap();

        //web host acknowledgement
        System.out.println("Webservice hosted @ "+url);

        //endpoint
        Endpoint.publish(url, userSoap);
    }
}
