package com.dariuszpaluch.utils;

import java.util.Date;

public class BeforeDateValid {
  public static boolean valid(Date date) {
    return new Date().after(date);
  }
}
