package tools.vitruv.change.oinfo.oisi;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;
import tools.vitruv.change.oinfo.source.OrthogonalInformationSource;
import tools.vitruv.change.oinfo.query.OrthogonalInformationQuerySwitch;

import java.util.Set;

public interface AccessorOISI extends OrthogonalInformationSourceInterface {
    public boolean removeOrthInfoSource(OrthogonalInformationSource source);
    public boolean addOrthInfoSource(OrthogonalInformationSource source);
    public Set<OrthogonalInformationSource> getOrthInfoSources();
}
