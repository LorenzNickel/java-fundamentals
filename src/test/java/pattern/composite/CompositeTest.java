package pattern.composite;

import junit.framework.TestCase;

/**
 * @author Lorenz Nickel
 */
public class CompositeTest extends TestCase {

  public void test() {
    CompositeElement root = new CompositeElement(1);
    CompositeElement child = new CompositeElement(2);
    root.addChild(child);
    root.addChild(new Leaf(3));
    root.addChild(new Leaf(4));
    child.addChild(new Leaf(5));
    child.addChild(new Leaf(6));

    assertEquals("[level=0, id=1, children=[[level=1, id=2, children=[[level=2, id=5][level=2, id=6]][level=1, id=3][level=1, id=4]]",
        root.list(0));
  }

}
