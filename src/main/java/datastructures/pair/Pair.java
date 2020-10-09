package datastructures.pair;

import java.util.Objects;

/**
 * @author thepn
 * <p>
 * Represents a simple Pair of two values: Key and Value
 * </p>
 */
public class Pair<K, V> {

  /**
   * key object
   */
  private final K key;

  /**
   * value object
   */
  private final V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String toString() {
    return "Pair{" +
        "key=" + key +
        ", value=" + value +
        '}';
  }

  /**
   * @return key
   */
  public K getKey() {
    return key;
  }

  /**
   * @return value
   */
  public V getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof Pair) {
      Pair pair = (Pair) o;

      if (!Objects.equals(key, pair.key)) {
        return false;
      }

      if (!Objects.equals(value, pair.value)) {
        return false;
      }

      return true;
    }

    return false;
  }
}
