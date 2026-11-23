package tools.vitruv.change.oinfo.oisi;

import java.util.Set;

public interface DelegatingOISI extends OrthogonalInformationSourceInterface {
    public Set<OrthogonalInformationSourceInterface> getDelegateOISIs();
}
