package net.apicode.jbasedconsole.system;

import java.io.File;
import java.util.function.Consumer;
import net.apicode.jbasedconsole.system.LibraryAdapter.DefaultLibraryAdapter;

public class NativeLibrary {

  private static LibraryAdapter libraryAdapter = new DefaultLibraryAdapter();

  private final String name;
  private final File libraryFile;


  public NativeLibrary(String name, Consumer<String> loader) {
    SystemInformation sysInfo = SystemInformation.current();
    this.name = name;
    this.libraryFile = libraryAdapter.getLibraryFile(name, sysInfo.getOperationSystem(), sysInfo.getArchitecture());
    loader.accept(libraryFile.getAbsolutePath());
  }

  @Deprecated
  public NativeLibrary(File file, Consumer<String> loader) {
    this.name = file.getName();
    this.libraryFile = file;
    loader.accept(file.getAbsolutePath());
  }

  public File getLibraryFile() {
    return libraryFile;
  }

  public String getName() {
    return name;
  }

  public static LibraryAdapter getLibraryAdapter() {
    return libraryAdapter;
  }

  public static void setLibraryAdapter(LibraryAdapter libraryAdapter) {
    NativeLibrary.libraryAdapter = libraryAdapter;
  }
}
