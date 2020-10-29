package algorithms.encryption;

/**
 * Static implementation of the Caesar cipher
 *
 * @author Ninju
 */
public class CaesarCipher {

  /**
   * Encrypts the input with a left shift of shiftValue
   *
   * @param input      a string to be encrypted
   * @param shiftValue the value with which the input should be encrypted
   * @return the encrypted input
   */
  public static String encrypt(String input, int shiftValue) {
    String res = "";
    if (input == null) {
      return null;
    }
    for (char current : input.toCharArray()) {
      int encrypted = current;
      if (current >= 65 && current <= 90) { //current is upper case
        encrypted -= shiftValue;
        while (encrypted < 65) {
          encrypted += 26;
        }
        while (encrypted > 90) {
          encrypted -= 26;
        }
      } else if (current >= 97 && current <= 122) { //current is lower case
        encrypted -= shiftValue;
        while (encrypted < 97) {
          encrypted += 26;
        }
        while (encrypted > 122) {
          encrypted -= 26;
        }
      }

      res += (char) encrypted;
    }
    return res;
  }

  /**
   * Decrypts the input with a left shift of shiftValue
   *
   * @param input      a string to be decrypted
   * @param shiftValue the value with which the input should be decrypted
   * @return the decrypted input
   */
  public static String decrypt(String input, int shiftValue) {
    return encrypt(input, -shiftValue);
  }

}
