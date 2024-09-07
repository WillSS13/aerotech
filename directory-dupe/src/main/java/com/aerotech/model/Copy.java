package com.aerotech.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.aerotech.util.Util;

public class Copy extends AbstractCommand {

  public Copy(String dir1, String dir2) {
    super(dir1, dir2);
  }

  /*
   * Begins the process of copying files from dir2 to dir1
   */
  @Override
  public void execute() {
    processDirectory(dir2, dir1);
  }

  /*
   * Search the top-level source directory (dir1) for duplicates based on content,
   * and
   * copy new files from target directory (dir2).
   * 
   * @param sourceDir - the directory to search
   * 
   * @param targetFile - the file to compare with
   */
  @Override
  public void searchDirectory(File sourceDir, File targetFile) {
      boolean isDuplicate = false;
      boolean hasSameName = false;
  
      if (sourceDir.isDirectory()) {
          for (File sourceFile : sourceDir.listFiles()) {
              if (!sourceFile.isDirectory()) {
                  try {
                      if (Util.sameContent(sourceFile, targetFile)) {
                          isDuplicate = true;
                          break;
                      }
  
                      // If file names are the same, mark it
                      if (sourceFile.getName().equals(targetFile.getName())) {
                          hasSameName = true; // Same name, but might have different content
                      }
  
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
  
      // If the content is different but the name is the same, we need to append a suffix
      if (!isDuplicate && hasSameName) {
          runCommand(targetFile, sourceDir);
      }
      // If no duplicate and no same name found, copy the file directly
      else if (!isDuplicate && targetFile.length() > 0) {
          runCommand(targetFile, sourceDir);
      }
  }
  
  /*
  * Utility function to generate a unique file name by appending a suffix (_copy or _1).
  * Prevents overwriting files with the same name that have differing contents.
  */
  private String getUniqueFileName(File sourceDir, String originalFileName) {
      String fileName = originalFileName;
      String fileExtension = "";
      int dotIndex = originalFileName.lastIndexOf('.');
  
      // If the file has an extension, separate the name and extension
      if (dotIndex > 0 && dotIndex < originalFileName.length() - 1) {
          fileName = originalFileName.substring(0, dotIndex);
          fileExtension = originalFileName.substring(dotIndex);
      }
  
      int counter = 1;
      File newFile = new File(sourceDir, fileName + "_copy" + fileExtension);
  
      // Keep generating new file names until one is not found in the source directory
      while (newFile.exists()) {
          newFile = new File(sourceDir, fileName + "_copy_" + counter + fileExtension);
          counter++;
      }
  
      return newFile.getName();
  }
  

  /*
   * Copy the targetFile to sourceDir
   */
  @Override
  public void runCommand(File targetFile, File sourceDir) {
    String newFileName = targetFile.getName();
    File newFile = new File(sourceDir, newFileName);

    if (newFile.exists()) {
      newFileName = getUniqueFileName(sourceDir, targetFile.getName());
      newFile = new File(sourceDir, newFileName);
    }

    try {
      Files.copy(targetFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
      System.out.println("Copied: " + targetFile.getPath() + " -> " + newFile.getPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
  * Recursively process dir2 and compare with dir1 to copy non-duplicates
  *
  * @param currentDir - the directory to process
  * @param targetDir - the directory to copy to
  */
  @Override
  public void processDirectory(File currentDir, File targetDir) {
    for (File file : currentDir.listFiles()) {
      if (file.isDirectory()) {
        File newDir = new File(targetDir, file.getName());
        if (!newDir.exists()) {
          newDir.mkdirs();
        }
        processDirectory(file, newDir);
      } else {
        searchDirectory(targetDir, file);
      }
    }
  }
}
