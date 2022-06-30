package net.apicode.jbasedconsole.system;

public interface SystemInformation {

  enum OperationSystem {
    WINDOWS("win"),
    LINUX("lin");

    private final String searchingName;

    OperationSystem(String searchingName) {
      this.searchingName = searchingName;
    }

    public String getSearchingName() {
      return searchingName;
    }
  }

  enum Architecture {
    AMD64("amd64"),
    AARCH64("aarch64");

    private final String searchingName;

    Architecture(String searchingName) {
      this.searchingName = searchingName;
    }

    public String getSearchingName() {
      return searchingName;
    }
  }

  OperationSystem getOperationSystem();

  Architecture getArchitecture();

  String getVersion();

  String getUserName();

  static SystemInformation current() {
    return SystemInformationImpl.instance;
  }






}
