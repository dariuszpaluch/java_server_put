package com.dariuszpaluch.exception;

public class WrongGradeException extends RuntimeException {

  public WrongGradeException() {
    super("Nieprawidłowa wartość oceny");
  }
}
