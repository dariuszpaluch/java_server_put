package com.dariuszpaluch.utils;

public class GradeValid {
  private static double[] gradeValues = {2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0};

  static public boolean gradeValue(double grade) {
    for(double availableValue : gradeValues) {
      if(availableValue == grade) {
        return true;
      }
    }

    return false;
  }
}
