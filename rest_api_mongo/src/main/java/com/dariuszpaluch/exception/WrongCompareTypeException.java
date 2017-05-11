package com.dariuszpaluch.exception;

public class WrongCompareTypeException extends RuntimeException {

  public WrongCompareTypeException() {
    super("Nieprawidłowa wartość typu porównania. Dozwolone: 0-<, 1-=, 2->");
  }
}
