package algorithms.string;

/**
 * @author thepn
 * <p>
 * Palindrom-Checker. Checks if a string is equal to the reverse string. Examples for palindroms:
 * lagerregal, rentner, otto, anna... More information: <a href="https://en.wikipedia.org/wiki/Palindrome">Wikipedia</a>
 * </p>
 */
public class PalindromCheck {

  /**
   * Checks if a string is a palindrom. The algorithm runs in O(n).
   * @param input
   * @return true if input string is a palindrom
   */
  public boolean isPalindrom(String input) {
    final int length = input.length();

    if (length == 1) {
      return true;
    }

    for (int i = 0; i < length; i++) {
      if (input.charAt(i) != input.charAt(length - (i + 1))) {
        return false;
      }
    }

    return true;
  }

}
