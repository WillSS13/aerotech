package com.aerotech;

import com.aerotech.model.Command;
import com.aerotech.model.Copy;
import com.aerotech.model.Delete;
import com.aerotech.model.Display;
import com.aerotech.util.Util;

public class App {
  public static void main(String[] args) {

    if (args.length != 3) {
      Util.printHelp();

    } else {

      Command cmd;
      String dir1 = args[1];
      String dir2 = args[2];

      // DISPLAY, DELETE, COPY
      if (args[0].equals("ls")) {
        cmd = new Display(dir1, dir2);
      } else if (args[0].equals("rm")) {
        cmd = new Delete(dir1, dir2);
      } else if (args[0].equals("cp")) {
        cmd = new Copy(dir1, dir2);
      } else {
        System.out.println("\nInvalid command\n");
        Util.printHelp();
        return;
      }

      if (cmd.hasValidDirectories()) {

        cmd.execute();

      } else {

        System.out.println("\nInvalid directory\n");
        Util.printHelp();

      }

    }
  }
}
