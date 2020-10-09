package problems.coinsamount.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import problems.coinsamount.CoinsAmount;

/**
 * @author thepn
 */
public class BruteForce implements CoinsAmount {

  public static <T> List<List<T>> combination(List<T> values, int size) {

    if (0 == size) {
      return Collections.singletonList(Collections.<T>emptyList());
    }

    if (values.isEmpty()) {
      return Collections.emptyList();
    }

    List<List<T>> combination = new ArrayList<List<T>>();

    T actual = values.iterator().next();

    List<T> subSet = new ArrayList<T>(values);
    subSet.remove(actual);

    List<List<T>> subSetCombination = combination(subSet, size - 1);

    for (List<T> set : subSetCombination) {
      List<T> newSet = new ArrayList<T>(set);
      newSet.add(0, actual);
      combination.add(newSet);
    }

    combination.addAll(combination(subSet, size));

    return combination;
  }


  @Override
  public int calculateAmount(int number, List<Integer> coinTypesOriginal) {
    // filter coin types
    List<Integer> coinTypes = filterLargeCoinTypes(number, coinTypesOriginal);

    if (coinTypes.size() == 0) {
      return 0;
    }

    // generate a list of coins that contains each coin value n times
    // where n = number / coin value
    List<Integer> base = new ArrayList<>();

    // time complexity = O(coin amount^2)
    for (Integer coinType : coinTypes) {
      int amount = Math.floorDiv(number, coinType);
      for (int i = 0; i < amount; i++) {
        base.add(coinType);
      }
    }

    List<List<Integer>> lists = new ArrayList<>();

    for (int i = 1; i <= base.size(); i++) {
      lists.addAll(combination(new ArrayList<>(base), i));
    }

    // check for each list in lists if the sum of entries is number and search the minimum amount of entries
    int min = Integer.MAX_VALUE;

    for (List<Integer> integers : lists) {
      int sum = integers.stream().mapToInt(Integer::intValue).sum();

      if (sum == number) {
        if (integers.size() < min) {
          min = integers.size();
        }
      }
    }

    return min;
  }
}
