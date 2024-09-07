package com.aerotech.model;

import java.io.File;

public class Display extends AbstractCommand {

  public Display(String dir1, String dir2) {
    super(dir1, dir2);
  }

  /*
   * Displays the duplicate file and notifies the user of what the original file is
   */
  public void runCommand(File original, File dupe) {
    System.out.println(dupe.getName() + " is a duplicate of " + original.getName());
  }

}
