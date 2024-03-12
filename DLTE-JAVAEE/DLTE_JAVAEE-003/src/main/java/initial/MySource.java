package initial;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class MySource {
    List<String> defaulters;

    public MySource() {
        defaulters = Stream.of("Annapoorna", "Akash", "Nishmitha", "Rakesh").collect(Collectors.toList());
    }

    @WebMethod
    @WebResult(name = "String")
    public String addDefaulter(@WebParam(name = "String") String name) {
        defaulters.add(name);
        return name+" has been added to defaulter record";

    }
}