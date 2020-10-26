package pattern.adapter;

/**
 * @author Aryaman19
 */

public class Client implements ClientInterface {

    @Override
    public String clientRequest() {
        return "This is a request from the client class";
    }

    @Override
    public String clientMethod() {
        return "This is the client class method execution";
    }
}
