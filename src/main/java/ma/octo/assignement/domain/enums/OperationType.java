package ma.octo.assignement.domain.enums;

public enum OperationType {

  VIREMENT("Virement"),
  VERSEMENT("Versement");

  private String type;

  OperationType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type ;
  }
}
