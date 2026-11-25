package tools.vitruv.change.oinfo.oisi;

import java.util.List;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;

public interface ReducingOISI extends DelegatingOISI {
    public void setDelegateAggregatorOISI(AggregatorOISI aggrOISI);
    public OrthogonalInformationQuery reducePerformedQueries(OrthogonalInformationQuery incomingQuery, List<OrthogonalInformationQuery> performedQueries);
}
