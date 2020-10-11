package algorithms.search;

/**
 * Provides a simple implementation of a JumpSearch algorithm, this is designed to
 * traverse through a Sorted Array and locate a value.
 * It differs from a normal linear search in that it will determine a 'jump' amount
 * and then jump through the input array until the number its on is greater than that
 * it wishes to search for, in this case it will jump back and perform a linear search
 * until it finds what it wants or determines it doesnt exist and exits.
 * In the worst case, we have to do n/m jumps and if the last checked value is greater
 * than the element to be found, we perform m-1 comparisons more for linear search.
 * Therefore the total number of comparisons worst case will be ((n/m) + m-1)
 *
 * @param <E>
 */
public class JumpSearch <E extends Comparable<? super E>> {

  private final E[] array;
  private int iterationsToFind;

  public JumpSearch(E[] array) {
    if (array == null) {
      throw new IllegalArgumentException("Parameter array must not be null");
    }
    this.array = array;
  }

  public int searchFor(final E elementToSearch) {

    iterationsToFind = 0;

    if (elementToSearch == null) {
      throw new IllegalArgumentException("Parameter toSearch most not be null");
    }

    if (array.length == 0) {
      return -1;
    }

    int arrayLength = array.length;
    int jumpStep = (int) Math.sqrt(array.length);
    int previousStep = 0;

    while (array[Math.min(jumpStep, arrayLength) - 1].compareTo(elementToSearch) < 0) {
      previousStep = jumpStep;
      jumpStep += (int) (Math.sqrt(arrayLength));
      iterationsToFind++;
      if (previousStep >= arrayLength) {
        return -1;
      }
    }

    while (array[previousStep].compareTo(elementToSearch) < 0) {
      previousStep++;
      iterationsToFind++;
      if (previousStep == Math.min(jumpStep, arrayLength)) {
        return -1;
      }
    }

    if (array[previousStep] == elementToSearch) {
      return previousStep;
    }
    return -1;
  }

  public int getIterationsToFind(){
    return iterationsToFind;
  }
}
