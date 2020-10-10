package pattern.composite;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Lorenz Nickel
 */
public class CompositeElement extends Base {

  private final Collection<Base> children;

  public CompositeElement(final int id) {
    super(id);
    this.children = new ArrayList<>();
  }

  @Override
  public String list(int level) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[level=" + level + ", id=" + super.id + ", children=[");
    for (final Base child : this.children) {
      stringBuilder.append(child.list(level + 1));
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  public Collection<Base> getChildren() {
    return this.children;
  }

  public void addChild(final Base element) {
    this.children.add(element);
  }

  public void removeChild(final Base element) {
    this.children.remove(element);
  }


}
