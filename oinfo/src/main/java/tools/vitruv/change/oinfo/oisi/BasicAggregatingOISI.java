package tools.vitruv.change.oinfo.oisi;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;

public class BasicAggregatingOISI implements AggregatorOISI {
    private String oisiID;
    private final Set<OrthogonalInformationSourceInterface> delegateOISIs = new LinkedHashSet<>();

    @Override
    public boolean removeDelegateOISI(OrthogonalInformationSourceInterface oisi) {
        return this.delegateOISIs.remove(oisi);
    }

    @Override
    public boolean addDelegateOISI(OrthogonalInformationSourceInterface oisi) {
        return this.delegateOISIs.add(oisi);
    }

    @Override
    public Set<OrthogonalInformationSourceInterface> getDelegateOISIs() {
        return new LinkedHashSet<>(delegateOISIs);
    }

    @Override
    public boolean canHandleQuery(OrthogonalInformationQuery query) {
        return delegateOISIs.stream().anyMatch(o -> o.canHandleQuery(query));
    }

    @Override
    public OrthogonalInformationQuery getOrthInfo(OrthogonalInformationQuery query) {
        var queryClone = query.clone();
        queryClone.addVisitedOISI(this);

        var queryResults = new ArrayList<OrthogonalInformationQuery>();
        for (var oisi : delegateOISIs.stream().filter(o -> o.canHandleQuery(query)).toList()) {
            var delegatedQuery = queryClone.clone();
            var resultingQuery = oisi.getOrthInfo(delegatedQuery);
            queryResults.add(resultingQuery);
        }

        var aggregatedResultQuery = aggregatePerformedQueries(queryClone, queryResults);
        return aggregatedResultQuery;
    }

    @Override
    public String getID() {
        return this.oisiID;
    }

    @Override
    public void setID(String oisiID) {
        this.oisiID = oisiID;
    }

    @Override
    public OrthogonalInformationQuery aggregatePerformedQueries(OrthogonalInformationQuery delegatedQuery, List<OrthogonalInformationQuery> performedQueries) {
        var masterQuery = delegatedQuery.clone();
        var queryResults = new ArrayList<OrthogonalInformationQuery>();
        queryResults.addAll(performedQueries);
        masterQuery.setResult(queryResults);
        return masterQuery;
    }
}
