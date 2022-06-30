package net.apicode.jbasedconsole.system;

import java.io.File;
import net.apicode.jbasedconsole.system.SystemInformation.Architecture;
import net.apicode.jbasedconsole.system.SystemInformation.OperationSystem;

public interface LibraryAdapter {

  File getLibraryFile(String name, OperationSystem operationSystem, Architecture architecture);

  class DefaultLibraryAdapter implements LibraryAdapter{

    @Override
    public File getLibraryFile(String name, OperationSystem operationSystem, Architecture architecture) {
      String filename;
      if(operationSystem == OperationSystem.WINDOWS) {
        filename = name + ".dll";
      } else if(operationSystem == OperationSystem.LINUX) {
        filename = name + ".so";
      } else {
        throw new UnsupportedSystemException();
      }

      File dir = new File("libs");
      if(!dir.exists()) {
        dir.mkdir();
      }
      return new File(dir, filename);
    }
  }

}
