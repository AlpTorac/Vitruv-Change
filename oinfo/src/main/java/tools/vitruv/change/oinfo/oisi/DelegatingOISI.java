package tools.vitruv.change.oinfo.oisi;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;
import tools.vitruv.change.oinfo.source.OrthogonalInformationSource;
import tools.vitruv.change.oinfo.query.OrthogonalInformationQuerySwitch;

import java.util.Set;

public interface DelegatingOISI extends OrthogonalInformationSourceInterface {
    public Set<OrthogonalInformationSourceInterface> getDelegateOISIs();
}
