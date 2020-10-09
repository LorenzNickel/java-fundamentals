package pattern.composite;

/**
 * @author Lorenz Nickel
 */
public abstract class Base {

  final int id;

  protected Base(final int id) {
    this.id = id;
  }

  public abstract String list(final int level);
}
