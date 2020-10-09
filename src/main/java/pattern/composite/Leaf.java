package pattern.composite;

/**
 * @author Lorenz Nickel
 */
public class Leaf extends Base {

  protected Leaf(int id) {
    super(id);
  }

  @Override
  public String list(int level) {
    return "[level=" + level + ", id=" + super.id + "]";
  }


}
