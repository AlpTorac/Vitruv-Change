package tools.vitruv.change.oinfo.query;

/**
 * An interface for classes that map queries to their executors.
 * 
 * For simplicity, forward one query to one OrthogonalInformationQueryExecutor.
 * Note that using multiple executors for a query is still possible in multiple
 * ways:
 * 
 * 1) By defining a composite executor, which takes one query and
 * uses multiple executors to perform the query. Useful for executor
 * compositions that should be used throughout change propagation (i.e. in
 * different OISIs).
 * 
 * 2) By defining special OrthogonalInformationQuerySwitch types that process
 * the queries in a certain order. Useful when executor IDs should be spared in
 * the ChangePropagationActions, since the switch defines which executors
 * perform the query and in which order they process it.
 */
public interface OrthogonalInformationQuerySwitch {
    public Object forwardQuery(OrthogonalInformationQuery query);
}
