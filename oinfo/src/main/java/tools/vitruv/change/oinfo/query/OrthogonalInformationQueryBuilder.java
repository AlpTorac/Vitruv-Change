package tools.vitruv.change.oinfo.query;

import java.util.Map;

public class OrthogonalInformationQueryBuilder {
    private OrthogonalInformationQuery query;

    protected OrthogonalInformationQuery getQuery() {
        return query;
    }

    public OrthogonalInformationQueryBuilder newQuery() {
        this.query = new OrthogonalInformationQuery();
        return this;
    }

    public OrthogonalInformationQueryBuilder withParam(Object paramObject, Object value) {
        this.query.getQueryParametersMap().put(paramObject, value);
        return this;
    }

    public OrthogonalInformationQueryBuilder withParams(Map<Object, Object> paramToVal) {
        this.query.getQueryParametersMap().putAll(paramToVal);
        return this;
    }

    public OrthogonalInformationQueryBuilder withOISIID(String id) {
        this.query.setRootOISIID(id);
        return this;
    }

    public OrthogonalInformationQueryBuilder includeQueryExecutor(String queryExecutorID) {
        this.query.includeQueryExecutor(queryExecutorID);
        return this;
    }

    public OrthogonalInformationQueryBuilder excludeQueryExecutor(String queryExecutorID) {
        this.query.excludeQueryExecutor(queryExecutorID);
        return this;
    }

    public OrthogonalInformationQuery build() {
        return query;
    }
}
