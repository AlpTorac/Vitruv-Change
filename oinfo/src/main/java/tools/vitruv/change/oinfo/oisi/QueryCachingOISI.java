package tools.vitruv.change.oinfo.oisi;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;
import tools.vitruv.change.oinfo.source.OrthogonalInformationSource;
import tools.vitruv.change.oinfo.query.OrthogonalInformationQuerySwitch;

import java.util.LinkedHashSet;
import java.util.Set;

public class QueryCachingOISI implements SingleDelegatingOISI {
    private String oisiID;
    private OrthogonalInformationSourceInterface delegateOISI;
    private final Set<OrthogonalInformationQuery> queryCache = new LinkedHashSet<>();

    @Override
    public OrthogonalInformationQuery getOrthInfo(OrthogonalInformationQuery query) {
        var duplicateQuery = queryCache.stream().filter(q -> q.queriesSameAs(query)).findFirst().orElse(null);
        if (duplicateQuery != null) {
            return duplicateQuery.clone();
        }
        // TODO: Check whether any eligible executors are present in oisi's switch
        var resultingQuery = delegateOISI.getOrthInfo(query);
        if (resultingQuery.getResult() != null) {
            queryCache.add(resultingQuery);
        }
        return resultingQuery;
    }

    @Override
    public void setID(String oisiID) {
        this.oisiID = oisiID;
    }

    @Override
    public String getID() {
        return this.oisiID;
    }

    @Override
    public Set<OrthogonalInformationSourceInterface> getDelegateOISIs() {
        return Set.of(this.delegateOISI);
    }

    @Override
    public void setDelegateOISI(OrthogonalInformationSourceInterface oisi) {
        this.delegateOISI = oisi;
    }

    @Override
    public OrthogonalInformationSourceInterface getDelegateOISI() {
        return this.delegateOISI;
    }
}
