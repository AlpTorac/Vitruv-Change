package tools.vitruv.change.oinfo.query;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple OrthogonalInformationQuerySwitch implementation that forwards a
 * query to the first query executor that is eligible.
 */
public class BasicOrthogonalInformationQuerySwitch implements OrthogonalInformationQuerySwitch {
    private final Set<OrthogonalInformationQueryExecutor> executors = new LinkedHashSet<>();

    public boolean addQueryExecutor(OrthogonalInformationQueryExecutor executor) {
        return executors.add(executor);
    }

    public boolean removeQueryExecutor(OrthogonalInformationQueryExecutor executor) {
        return executors.remove(executor);
    }

    public List<OrthogonalInformationQueryExecutor> getAllQueryExecutors() {
        return List.copyOf(executors);
    }

    @Override
    public Object forwardQuery(OrthogonalInformationQuery query) {
        var executor = executors.stream().filter(e -> e.canHandleQuery(query)).findFirst().get();

        var result = executor.executeQuery(query);

        // Add the used query executor
        query.addUsedExecutor(executor);
        return result;
    }

    @Override
    public boolean canHandleQuery(OrthogonalInformationQuery query) {
        return executors.stream().anyMatch(e -> e.canHandleQuery(query));
    }
}
