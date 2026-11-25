package tools.vitruv.change.oinfo.oisi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;

public class BasicReducingOISI implements ReducingOISI {
    private String oisiID;
    private AggregatorOISI aggrOISI;

    @Override
    public Set<OrthogonalInformationSourceInterface> getDelegateOISIs() {
        return new LinkedHashSet<>(Collections.singleton(aggrOISI));
    }

    @Override
    public boolean canHandleQuery(OrthogonalInformationQuery query) {
        return aggrOISI.canHandleQuery(query);
    }

    @Override
    public OrthogonalInformationQuery getOrthInfo(OrthogonalInformationQuery query) {
        var queryClone = query.clone();
        queryClone.addVisitedOISI(this);

        var resultQuery = aggrOISI.getOrthInfo(queryClone);
        @SuppressWarnings("unchecked")
        var reducedQuery = reducePerformedQueries(queryClone, (List<OrthogonalInformationQuery>) resultQuery.getResult());
        return reducedQuery;
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
    public OrthogonalInformationQuery reducePerformedQueries(OrthogonalInformationQuery incomingQuery, List<OrthogonalInformationQuery> performedQueries) {
        var reducedQuery = incomingQuery.clone();
        var results = new ArrayList<>();
        for (var pq : performedQueries) {
            var currentResult = pq.getResult();
            if (currentResult instanceof Collection colResult) {
                results.addAll(colResult);
            } else if (currentResult != null) {
                results.add(currentResult);
            }
        }
        reducedQuery.setResult(results);
        return reducedQuery;
    }

    @Override
    public void setDelegateAggregatorOISI(AggregatorOISI aggrOISI) {
        this.aggrOISI = aggrOISI;
    }
}
