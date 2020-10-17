package problems.romanNumeralConverter;

import static org.junit.Assert.*;

import org.junit.Test;
import problems.romanNumeralConverter.impl.Converter;

public class ConverterTest {

  Converter converter = new Converter();
  @Test
  public void InputIGet1() {


    assertEquals(1, converter.convert("I"));
  }

  @Test
  public void InputIIIGet3() {

    assertEquals(3, converter.convert("III"));
  }

  @Test
  public void InputVGet5() {

    assertEquals(5, converter.convert("V"));
  }

  @Test
  public void InputVIIGet7() {

    assertEquals(7, converter.convert("VII"));
  }

  @Test
  public void InputIVGet4() {

    assertEquals(4, converter.convert("IV"));
  }

  @Test
  public void InputIXGet4() {

    assertEquals(4, converter.convert("IV"));
  }

  @Test
  public void InputIXGet9() {

    assertEquals(4, converter.convert("IV"));
  }

  @Test
  public void InputXXXIXGet39() {

    assertEquals(39, converter.convert("XXXIX"));
  }

  @Test
  public void InputXXVIIIGet28() {

    assertEquals(28, converter.convert("XXVIII"));
  }

  @Test
  public void InputXLGet40() {

    assertEquals(40, converter.convert("XL"));
  }

  @Test
  public void InputLXXXGet80() {

    assertEquals(80, converter.convert("LXXX"));
  }

  @Test
  public void InputXCGet90() {

    assertEquals(90, converter.convert("XC"));
  }

  @Test
  public void InputCXCIGet191() {

    assertEquals(191, converter.convert("CXCI"));
  }

  @Test
  public void InputCDGet400() {

    assertEquals(400, converter.convert("CD"));
  }

  @Test
  public void InputCMGet900() {

    assertEquals(900, converter.convert("CM"));
  }

  @Test
  public void InputMCMIIIGet1903() {

    assertEquals(1903, converter.convert("MCMIII"));
  }
}