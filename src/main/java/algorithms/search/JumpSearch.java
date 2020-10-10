package algorithms.search;

public class JumpSearch <E extends Comparable<? super E>> {

  private final E[] array;

  public JumpSearch(E[] array) {
    if (array == null) {
      throw new IllegalArgumentException("Parameter array must not be null");
    }
    this.array = array;
  }

  public int searchFor(final E elementToSearch) {

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
    }

    while (array[previousStep].compareTo(elementToSearch) < 0) {
      previousStep++;
    }

    if (array[previousStep] == elementToSearch) {
      return previousStep;
    }
    return -1;
  }
}
