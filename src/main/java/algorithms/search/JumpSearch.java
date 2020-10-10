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
      if (previousStep >= arrayLength) {
        return -1;
      }
    }

    while (array[previousStep].compareTo(elementToSearch) < 0) {
      previousStep++;
      if (previousStep == Math.min(jumpStep, arrayLength)) {
        return -1;
      }
    }

    if (array[previousStep] == elementToSearch) {
      return previousStep;
    }
    return -1;
  }
}
