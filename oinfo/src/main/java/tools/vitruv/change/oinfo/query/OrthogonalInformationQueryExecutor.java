package tools.vitruv.change.oinfo.query;

/**
 * An interface for classes that encapsulate the means to perform model queries.
 * 
 * OrthogonalInformationQueryExecutors should contain all details associated
 * with performing queries:
 * 
 * 1) Any details on the potential underlying model querying tool
 * 
 * 2) Any details on external files that are needed to perform queries (except
 * for model Resources, see OrthogonalInformationSources for that)
 */
public interface OrthogonalInformationQueryExecutor {
    public default boolean canHandleQuery(OrthogonalInformationQuery query) {
        return query.isExecutorEligible(getID());
    }
    public Object executeQuery(OrthogonalInformationQuery query);
    public String getID();
    public void setID(String id);
}
