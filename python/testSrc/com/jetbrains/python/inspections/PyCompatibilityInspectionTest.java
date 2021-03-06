// Copyright 2000-2017 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.jetbrains.python.inspections;

import com.jetbrains.python.fixtures.PyInspectionTestCase;
import com.jetbrains.python.psi.LanguageLevel;
import org.jetbrains.annotations.NotNull;

/**
 * User : catherine
 */
public class PyCompatibilityInspectionTest extends PyInspectionTestCase {

  public void testDictCompExpression() {
    setLanguageLevel(LanguageLevel.PYTHON27);
    doTest();
  }

  public void testSetLiteralExpression() {
    setLanguageLevel(LanguageLevel.PYTHON27);
    doTest();
  }

  public void testSetCompExpression() {
    setLanguageLevel(LanguageLevel.PYTHON27);
    doTest();
  }

  public void testExceptBlock() {
    setLanguageLevel(LanguageLevel.PYTHON27);
    doTest();
  }

  public void testImportStatement() {
    setLanguageLevel(LanguageLevel.PYTHON27);
    doTest();
  }

  public void testImportErrorCaught() {
    setLanguageLevel(LanguageLevel.PYTHON27);
    doTest();
  }

  public void testStarExpression() {
    setLanguageLevel(LanguageLevel.PYTHON35);
    doTest();
  }

  public void testBinaryExpression() {
    setLanguageLevel(LanguageLevel.PYTHON27);
    doTest();
  }

  public void testNumericLiteralExpression() {
    doTest();
  }

  public void testStringLiteralExpression() {
    doTest();
  }

  public void testListCompExpression() {
    doTest();
  }

  public void testRaiseMultipleArgs() {
    doTest();
  }

  public void testRaiseFrom() {
    setLanguageLevel(LanguageLevel.PYTHON32);
    doTest();
  }

  public void testReprExpression() {
    doTest();
  }

  public void testWithStatement() {
    setLanguageLevel(LanguageLevel.PYTHON27);
    doTest();
  }

  public void testPyClass() {
    doTest();
  }

  public void testPrintStatement() {
    doTest();
  }

  public void testFromImportStatement() {
    doTest();
  }

  public void testAssignmentStatement() {
    doTest();
  }

  public void testTryExceptStatement() {
    doTest();
  }

  public void testImportElement() {
    doTest();
  }

  public void testCallExpression() {
    setLanguageLevel(LanguageLevel.PYTHON30);
    doTest();
  }

  public void testBasestring() {
    doTest();
  }

  public void testConditionalExpression() {
    doTest();
  }

  public void testClassBaseList() {
    doTest();
  }

  // PY-7763
  public void testEllipsisAsStatementPy2() {
    doTest(LanguageLevel.PYTHON33);
  }

  // PY-8606
  public void testEllipsisInSubscriptionPy2() {
    doTest(LanguageLevel.PYTHON33);
  }

  // PY-11047
  public void testRelativeImport() {
    doTest();
  }

  // PY-15390
  public void testMatMul() {
    doTest(LanguageLevel.PYTHON35);
  }

  public void testAsyncAwait() {
    doTest(LanguageLevel.PYTHON35);
  }

  public void testDoubleStarUnpacking() {
    doTest(LanguageLevel.PYTHON35);
  }

  public void testArgumentsUnpackingGeneralizations() {
    doTest(LanguageLevel.PYTHON35);
  }

  // PY-19523
  public void testBz2Module() {
    doTest();
  }

  public void testUnderscoreBz2Module() {
    doTest();
  }

  // PY-19486
  public void testBackportedEnum() {
    doTest();
  }

  // PY-18880
  public void testBackportedTyping() {
    doTest();
  }

  public void testUnderscoresInNumericLiterals() {
    doTest(LanguageLevel.PYTHON36);
  }

  public void testVariableAnnotations() {
    doTest(LanguageLevel.PYTHON36);
  }

  // PY-20770
  public void testYieldInsideAsyncDef() {
    doTest(LanguageLevel.PYTHON36);
  }

  // PY-20770
  public void testAsyncComprehensions() {
    doTest(LanguageLevel.PYTHON36);
  }

  // PY-20770
  public void testAwaitInComprehensions() {
    doTest(LanguageLevel.PYTHON36);
  }

  // PY-16098
  public void testWarningAboutAsyncAndAwaitInPy35() {
    doTest(LanguageLevel.PYTHON35);
  }

  // PY-16098
  public void testWarningAboutAsyncAndAwaitInPy36() {
    doTest(LanguageLevel.PYTHON36);
  }

  // PY-22302
  public void testNoWarningAboutEllipsisInFunctionTypeComments() {
    doTest();
  }

  // PY-23355
  public void testNoWarningAboutStarredExpressionsInFunctionTypeComments() {
    doTest();
  }

  public void testBuiltinLong() {
    doTest();
  }

  // PY-26510
  public void testTryExceptEmptyRaise() {
    doTest();
  }

  // PY-26510
  public void testTryFinallyEmptyRaisePy2() {
    doTest();
  }

  // PY-26510
  public void testTryFinallyEmptyRaisePy3() {
    doTest(LanguageLevel.PYTHON30);
  }

  private void doTest(@NotNull LanguageLevel level) {
    runWithLanguageLevel(level, this::doTest);
  }

  @NotNull
  @Override
  protected Class<? extends PyInspection> getInspectionClass() {
    return PyCompatibilityInspection.class;
  }
}
