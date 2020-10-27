package algorithms.encryption;

import junit.framework.TestCase;

/**
 * @author Ninju
 */
public class CaesarCipherTest extends TestCase {

  public void testCaesarCipher() {
    assertEquals("abc", CaesarCipher.encrypt("def", 3));
    assertEquals("I like apples", CaesarCipher.decrypt("D gdfz vkkgzn", 5));
    assertEquals("Hello", CaesarCipher.encrypt(CaesarCipher.decrypt("Hello", 15), 15));
    assertEquals("Test", CaesarCipher.decrypt("Test", 26 * 7));
    assertEquals(CaesarCipher.decrypt("ABCDEF", 13), CaesarCipher.encrypt("ABCDEF", 13));

  }

}
