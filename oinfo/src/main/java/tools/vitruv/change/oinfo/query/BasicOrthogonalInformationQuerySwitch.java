package tools.vitruv.change.oinfo.query;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple OrthogonalInformationQuerySwitch implementation that forwards a
 * query to the first query executor that is eligible.
 */
public class BasicOrthogonalInformationQuerySwitch implements OrthogonalInformationQuerySwitch {
    private final List<OrthogonalInformationQueryExecutor> executors = new ArrayList<>();

    public void addQueryExecutor(OrthogonalInformationQueryExecutor executor) {
        executors.add(executor);
    }

    public void removeQueryExecutor(OrthogonalInformationQueryExecutor executor) {
        executors.remove(executor);
    }

    @Override
    public Object forwardQuery(OrthogonalInformationQuery query) {
        var executor = executors.stream().filter(e -> query.isExecutorEligible(e.getID())).findFirst().get();

        var result = executor.executeQuery(query);

        // Add the used query executor
        query.addUsedExecutor(executor);
        return result;
    }
}
