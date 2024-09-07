package com.aerotech.model;

import java.io.File;

public class Delete extends AbstractCommand {

  public Delete(String dir1, String dir2) {
    super(dir1, dir2);
  }

  /*
   * Deletes the duplicate file and notifies the user
   * 
   * @param original - the original file
   * @param dupe - the duplicate file
   * 
   */
  public void runCommand(File original, File dupe) {
    dupe.delete();
    System.out.println("Deleted: " + dupe.getName() + ", a duplicate of " + original.getName());
  }

}
