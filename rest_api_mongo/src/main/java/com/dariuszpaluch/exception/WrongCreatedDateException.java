package com.dariuszpaluch.exception;

/**
 * Created by dariusz on 12.05.2017.
 */
public class WrongCreatedDateException extends RuntimeException {

  public WrongCreatedDateException() {
    super("Nieprawidłowa data. Data musi być przeszła.");
  }

  public WrongCreatedDateException(String message) {
    super(message);
  }
}