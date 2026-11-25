package tools.vitruv.change.oinfo.oisi;

import java.util.List;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;

public interface AggregatorOISI extends MultiDelegatingOISI {
    public OrthogonalInformationQuery aggregatePerformedQueries(OrthogonalInformationQuery delegatedQuery, List<OrthogonalInformationQuery> performedQueries);
}
