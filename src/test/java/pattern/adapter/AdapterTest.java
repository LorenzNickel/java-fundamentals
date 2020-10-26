package pattern.adapter;

import junit.framework.TestCase;

/**
 * @author Aryaman19
 */

public class AdapterTest extends TestCase {

    public void test() {

        ServiceInterface service = new Service();
        ClientInterface adapter = new Adapter(service);

        assertEquals(service.serviceRequest(), adapter.clientRequest());
        assertEquals(service.serviceMethod(), adapter.clientMethod());

    }
}
