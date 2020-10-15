package algorithms.strings;

import algorithms.string.PalindromCheck;
import junit.framework.TestCase;

/**
 * @author thepn
 */
public class PalindromTest extends TestCase {

  public void testPalindroms(){
    final PalindromCheck check = new PalindromCheck();

    assertTrue(check.isPalindrom("lagerregal"));
    assertTrue(check.isPalindrom("otto"));
    assertTrue(check.isPalindrom("anna"));
    assertTrue(check.isPalindrom("rentner"));
    assertTrue(check.isPalindrom("lagerregal"));
    assertTrue(check.isPalindrom("1221"));
    assertTrue(check.isPalindrom("@@§§&&§§@@"));
    assertTrue(check.isPalindrom("a"));
    assertTrue(check.isPalindrom("bb"));
    assertTrue(check.isPalindrom(""));
  }

  public void testNoPalindroms(){
    final PalindromCheck check = new PalindromCheck();

    assertFalse(check.isPalindrom("abcde"));
  }
}
