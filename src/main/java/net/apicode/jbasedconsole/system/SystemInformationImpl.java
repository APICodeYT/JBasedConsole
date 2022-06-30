package net.apicode.jbasedconsole.system;

import java.util.Locale;

class SystemInformationImpl implements SystemInformation {

  static final SystemInformationImpl instance = new SystemInformationImpl();

  private OperationSystem operationSystem;
  private Architecture architecture;
  private final String version;
  private final String user;

  private SystemInformationImpl() throws UnsupportedSystemException {
    final String osName = System.getProperty("os.name");
    final String osArch = System.getProperty("os.arch");
    version = System.getProperty("os.version");
    user = System.getProperty("user.name");

    for (OperationSystem value : OperationSystem.values()) {
      if(osName.toLowerCase(Locale.ROOT).contains(value.getSearchingName())) {
        operationSystem = value;
        break;
      }
    }
    for (Architecture value : Architecture.values()) {
      if(osArch.toLowerCase(Locale.ROOT).contains(value.getSearchingName())) {
        architecture = value;
        break;
      }
    }
    if(operationSystem == null) throw new UnsupportedSystemException("OS: " + osName);
    if(architecture == null) throw new UnsupportedSystemException("Arch: " + osArch);
  }

  @Override
  public OperationSystem getOperationSystem() {
    return operationSystem;
  }

  @Override
  public Architecture getArchitecture() {
    return architecture;
  }

  @Override
  public String getVersion() {
    return version;
  }

  @Override
  public String getUserName() {
    return user;
  }
}
