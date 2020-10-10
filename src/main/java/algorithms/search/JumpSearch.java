package algorithms.search;

public class JumpSearch {

  public int searchFor(int[] integers, int elementToSearch) {

    if (integers == null) {
      throw new IllegalArgumentException("Parameter array must not be null");
    }

    if (integers.length == 0){
      return -1;
    }

    int arrayLength = integers.length;
    int jumpStep = (int) Math.sqrt(integers.length);
    int previousStep = 0;

    while (integers[Math.min(jumpStep, arrayLength) - 1] < elementToSearch) {
      previousStep = jumpStep;
      jumpStep += (int) (Math.sqrt(arrayLength));
      if (previousStep >= arrayLength) {
        return -1;
      }
    }
    while (integers[previousStep] < elementToSearch) {
      previousStep++;
      if (previousStep == Math.min(jumpStep, arrayLength)) {
        return -1;
      }
    }

    if (integers[previousStep] == elementToSearch) {
      return previousStep;
    }
    return -1;
  }
}
