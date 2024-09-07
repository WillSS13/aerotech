package com.aerotech.model;

import java.io.File;

public interface Command {
  boolean hasValidDirectories();
  void execute();
  void processDirectory(File dir1, File dir2);
  void searchDirectory(File dir, File f);

  /*
   * Executes the necessary command (DISPLAY, DELETE, COPY)
   * 
   * @param original - the original file
   * @param dupe - the duplicate file
   */
  void runCommand(File original, File dupe);
}
