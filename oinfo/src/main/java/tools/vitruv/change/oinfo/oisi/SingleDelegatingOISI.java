package tools.vitruv.change.oinfo.oisi;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;
import tools.vitruv.change.oinfo.source.OrthogonalInformationSource;
import tools.vitruv.change.oinfo.query.OrthogonalInformationQuerySwitch;

public interface SingleDelegatingOISI extends DelegatingOISI {
    public void setDelegateOISI(OrthogonalInformationSourceInterface oisi);
    public OrthogonalInformationSourceInterface getDelegateOISI();
}
