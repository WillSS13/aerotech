package com.aerotech.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Util {
  // CREDIT:
  // https://www.reddit.com/r/java/comments/289nez/faster_way_to_compare_file_contents/
  // Compares the contents of two files
  public static boolean sameContent(final File a, final File b) throws IllegalArgumentException, IOException {

    if (a == null || b == null) {
      throw new IllegalArgumentException("At least one of the arguments is null: a=" + a + "; b=" + b);
    }
    if (!a.exists() || !b.exists()) {
      throw new IllegalArgumentException(
          "At least one of the files does not exist: a=" + a.exists() + "; b=" + b.exists());
    }
    if (!a.isFile() || !b.isFile()) {
      throw new IllegalArgumentException(
          "At least one of the files is not a file: a=" + a.isFile() + "; b=" + b.isFile());
    }

    if (a.length() != b.length()) {
      return false;
    }

    final byte[] dataRAWa = Files.readAllBytes(a.toPath());
    final byte[] dataRAWb = Files.readAllBytes(b.toPath());

    for (int i = 0; i < dataRAWa.length; i++) {
      if (dataRAWa[i] != dataRAWb[i]) {
        return false;
      }
    }
    return true;
  }

  /*
   * Prints the help message for the user
   */
  public static boolean printHelp() {
    System.out.println("Usage: java -jar directory-dupe.jar [command] [dir1] [dir2]");
    System.out.println("Commands:");
    System.out.println("  ls - Display duplicate files in dir1 and dir2");
    System.out.println("  rm - Delete duplicate files in dir1 and dir2");
    System.out.println("  cp - Copy non-duplicate files from dir2 to dir1");
    return true;
  }

}
