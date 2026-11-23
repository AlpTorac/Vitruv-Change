package tools.vitruv.change.oinfo.query;

import tools.vitruv.change.oinfo.oisi.OrthogonalInformationSourceInterface;
import tools.vitruv.change.oinfo.source.OrthogonalInformationSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrthogonalInformationQuery implements Cloneable {
    /**
     * Reserved for query-specific details and tool-specific details
     */
    private final Map<Object, Object> queryParameters = new LinkedHashMap<Object, Object>();
    private final Map<Object, Object> queryDetails = new LinkedHashMap<Object, Object>();

    private Object result;
    private final List<OrthogonalInformationSourceInterface> visitedOISIs;
    private final Set<OrthogonalInformationSource> consideredOISs;
    private List<OrthogonalInformationQuery> queryVersions;
    private final List<OrthogonalInformationQueryExecutor> usedExecutors;

    /**
     * IDs of the executors that are allowed to perform this query.
     * 
     * If empty, all
     * non-excluded executors are considered to be ID-wise eligible (meaning that
     * they are eligible, unless other conditions are specified, in concrete OISIs
     * for instance)
     */
    private final Set<String> includedExecutorIDs;

    /**
     * IDs of the executors that are not allowed to perform this query.
     */
    private final Set<String> excludedExecutorIDs;

    /**
     * The OISI that should process this query first.
     */
    private String rootOISIID;

    public OrthogonalInformationQuery() {
        this(null);
    }

    protected OrthogonalInformationQuery(List<OrthogonalInformationQuery> queryVersions) {
        this.result = null;

        this.includedExecutorIDs = new LinkedHashSet<>();
        this.excludedExecutorIDs = new LinkedHashSet<>();

        this.visitedOISIs = new ArrayList<>();

        this.consideredOISs = new LinkedHashSet<>();

        if (queryVersions == null) {
            this.queryVersions = new ArrayList<>();
        } else {
            this.queryVersions = queryVersions;
        }

        this.queryVersions.add(this);

        this.usedExecutors = new ArrayList<>();
    }

    public Map<Object, Object> getQueryParametersMap() {
        return this.queryParameters;
    }

    public Map<Object, Object> getQueryDetailsMap() {
        return this.queryDetails;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void addVisitedOISI(OrthogonalInformationSourceInterface oisi) {
        this.visitedOISIs.add(oisi);
    }

    public void addUsedExecutor(OrthogonalInformationQueryExecutor executor) {
        this.usedExecutors.add(executor);
    }

    public List<OrthogonalInformationQueryExecutor> getUsedExecutors() {
        return List.copyOf(usedExecutors);
    }

    public void addConsideredOIS(OrthogonalInformationSource ois) {
        this.consideredOISs.add(ois);
    }

    public Set<OrthogonalInformationSource> getConsideredOISs() {
        return this.consideredOISs;
    }

    public String getRootOISIID() {
        return rootOISIID;
    }

    public void setRootOISIID(String rootOISIID) {
        this.rootOISIID = rootOISIID;
    }

    public void includeQueryExecutor(String queryExecutorID) {
        this.includedExecutorIDs.add(queryExecutorID);
        this.excludedExecutorIDs.remove(queryExecutorID);
    }

    public void excludeQueryExecutor(String queryExecutorID) {
        this.includedExecutorIDs.remove(queryExecutorID);
        this.excludedExecutorIDs.add(queryExecutorID);
    }

    public List<OrthogonalInformationQuery> getQueryVersions() {
        return List.copyOf(queryVersions);
    }

    public List<OrthogonalInformationSourceInterface> getVisitedOISIs() {
        return List.copyOf(this.visitedOISIs);
    }

    public boolean isExecutorEligible(String executorID) {
        if (excludedExecutorIDs.contains(executorID))
            return false;
        if (includedExecutorIDs.isEmpty())
            return true;
        return includedExecutorIDs.contains(executorID);
    }

    /**
     * @return Whether this query and the given query (in theory) retrieve the same
     *         piece of information.
     */
    public boolean queriesSameAs(OrthogonalInformationQuery query) {
        if (query == this)
            return true;

        return
        // Compare root OISI IDs
        this.rootOISIID.equals(query.rootOISIID) &&
        // Compare included executor IDs
                this.includedExecutorIDs.containsAll(query.includedExecutorIDs) &&
                query.includedExecutorIDs.containsAll(this.includedExecutorIDs) &&
                // Compare excluded executor IDs
                this.excludedExecutorIDs.containsAll(query.excludedExecutorIDs) &&
                query.excludedExecutorIDs.containsAll(this.excludedExecutorIDs) &&
                // Compare query parameter content
                this.queryParameters.entrySet().containsAll(query.queryParameters.entrySet()) &&
                query.queryParameters.entrySet().containsAll(this.queryParameters.entrySet());
    }

    @Override
    public OrthogonalInformationQuery clone() {
        var queryClone = new OrthogonalInformationQuery(this.queryVersions);
        queryClone.includedExecutorIDs.addAll(this.includedExecutorIDs);
        queryClone.excludedExecutorIDs.addAll(this.excludedExecutorIDs);
        queryClone.queryParameters.putAll(this.getQueryParametersMap());
        queryClone.queryDetails.putAll(this.getQueryDetailsMap());
        queryClone.result = this.getResult();
        queryClone.usedExecutors.addAll(this.usedExecutors);
        queryClone.visitedOISIs.addAll(this.visitedOISIs);
        queryClone.rootOISIID = this.rootOISIID;
        return queryClone;
    }

    @Override
    public String toString() {
        // TODO: Refactor and use StringBuilder, once finalised
        var result = "";
        for (int i = 0; i < queryVersions.size(); i++) {
            var map = new LinkedHashMap<String, Object>();
            var currentQ = queryVersions.get(i);
            if (currentQ == this) {
                map.put("queryVersion (this)", i);
            } else {
                map.put("queryVersion", i);
            }
            map.put("parameters", currentQ.queryParameters);
            map.put("details", currentQ.queryDetails);
            map.put("result", currentQ.getResult());

            map.put("rootOISIID", currentQ.getRootOISIID());
            map.put("visitedOISIs", currentQ.getVisitedOISIs());
            map.put("consideredOISs", currentQ.consideredOISs);
            map.put("includedExecutors", currentQ.includedExecutorIDs);
            map.put("excludedExecutors", currentQ.excludedExecutorIDs);
            map.put("usedExecutors", currentQ.usedExecutors);
            result = result + map.toString();
        }
        return result;
    }
}
