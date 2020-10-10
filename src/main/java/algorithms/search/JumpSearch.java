package algorithms.search;

public class JumpSearch {

  public static int jumpSearch(int[] integers, int elementToSearch) {

    int arrayLength = integers.length;
    int jumpStep = (int) Math.sqrt(integers.length);
    int previousStep = 0;

    while (integers[Math.min(jumpStep, arrayLength) - 1] < elementToSearch) {
      previousStep = jumpStep;
      jumpStep += (int)(Math.sqrt(arrayLength));
      if (previousStep >= arrayLength)
        return -1;
    }
    while (integers[previousStep] < elementToSearch) {
      previousStep++;
      if (previousStep == Math.min(jumpStep, arrayLength))
        return -1;
    }

    if (integers[previousStep] == elementToSearch)
      return previousStep;
    return -1;
  }

  public static void main(String[] args) {

    int indexFound = jumpSearch(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 90}, 67);
    System.out.println("I have found element 67 at Index: " + indexFound);
  }

}
