package tools.vitruv.change.oinfo.oisi;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;

import java.util.List;

public interface MultiDelegatingOISI extends DelegatingOISI {
    public boolean removeDelegateOISI(OrthogonalInformationSourceInterface oisi);

    public boolean addDelegateOISI(OrthogonalInformationSourceInterface oisi);

    public OrthogonalInformationQuery aggregatePerformedQueries(List<OrthogonalInformationQuery> performedQueries);
}
