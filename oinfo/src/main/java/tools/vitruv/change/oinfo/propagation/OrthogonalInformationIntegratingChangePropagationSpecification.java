package tools.vitruv.change.oinfo.propagation;

import tools.vitruv.change.oinfo.oisi.OISIRegister;
import tools.vitruv.change.propagation.ChangePropagationSpecification;

public interface OrthogonalInformationIntegratingChangePropagationSpecification extends ChangePropagationSpecification {
  public void setOISIRegister(OISIRegister oisiRegister);
  public OISIRegister getOISIRegister();
}
