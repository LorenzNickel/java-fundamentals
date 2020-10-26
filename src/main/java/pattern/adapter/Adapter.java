package pattern.adapter;

/**
 * @author Aryaman19
 */

public class Adapter implements ClientInterface {

  ServiceInterface service;

  public Adapter(ServiceInterface service) {
    this.service = service;
  }

  @Override
  public String clientRequest() {
    return service.serviceRequest();
  }

  @Override
  public String clientMethod() {
    return service.serviceMethod();
  }
}