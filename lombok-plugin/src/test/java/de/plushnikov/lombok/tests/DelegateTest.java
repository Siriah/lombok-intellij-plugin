package de.plushnikov.lombok.tests;

import de.plushnikov.lombok.LombokParsingTestCase;

/**
 * Unit tests for IntelliJPlugin for Lombok, based on lombok test classes
 */
public class DelegateTest extends LombokParsingTestCase {

  public void testDelegateOnGetter() throws Exception {
    //TODO known problem, try to fix later
    doTest();
  }

  public void testDelegateOnGetterNone() throws Exception {
    doTest();
  }

  public void testDelegateOnMethods() throws Exception {
    doTest();
  }

  public void testDelegateRecursion() throws Exception {
    doTest();
  }

  public void testDelegateTypesAndExcludes() throws Exception {
    doTest();
  }

  public void testDelegateWithDeprecated() throws Exception {
    doTest();
  }

  public void testDelegateWithException() throws Exception {
    doTest();
  }
}