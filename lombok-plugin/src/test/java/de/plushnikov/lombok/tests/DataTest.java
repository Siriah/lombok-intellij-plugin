package de.plushnikov.lombok.tests;

import de.plushnikov.lombok.LombokParsingTestCase;

import java.io.IOException;

/**
 * Unit tests for IntelliJPlugin for Lombok, based on lombok test classes
 */
public class DataTest extends LombokParsingTestCase {

  protected boolean shouldCompareCodeBlocks() {
    return false;
  }

  public void testDataExtended() throws IOException {
    doTest();
  }

  public void testDataIgnore() throws IOException {
    doTest();
  }

  public void testDataOnEnum() throws IOException {
    doTest();
  }

  public void testDataOnLocalClass() throws IOException {
    doTest();
  }

  public void testDataPlain() throws IOException {
    doTest();
  }

  public void testDataWithGetter() throws IOException {
    doTest();
  }

  public void testDataWithGetterNone() throws IOException {
    doTest();
  }

  public void testDataStaticConstructor() throws IOException {
    // Test for issue #9
    doTest();
  }
}