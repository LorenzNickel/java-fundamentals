package pattern.adapter;

/**
 * @author Aryaman19
 */

public class Service implements ServiceInterface {
    @Override
    public String serviceRequest() {
        return "This is a request from the service class";
    }

    @Override
    public String serviceMethod() {
        return "This is the service class method execution";
    }
}