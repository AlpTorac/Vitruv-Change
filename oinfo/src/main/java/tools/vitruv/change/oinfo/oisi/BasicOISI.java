package tools.vitruv.change.oinfo.oisi;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;
import tools.vitruv.change.oinfo.source.OrthogonalInformationSource;
import tools.vitruv.change.oinfo.query.OrthogonalInformationQuerySwitch;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A concrete OISI implementation that considers a list of
 * OrthogonalInformationSource instances to address incoming queries, based on
 * an OrthogonalInformationQuerySwitch.
 */
public class BasicOISI implements AccessorOISI {
    private String oisiID;

    private final Set<OrthogonalInformationSource> orthInfoSources = new LinkedHashSet<>();
    private OrthogonalInformationQuerySwitch querySwitch;

    public void setQuerySwitch(OrthogonalInformationQuerySwitch querySwitch) {
        this.querySwitch = querySwitch;
    }

    @Override
    public void setID(String oisiID) {
        this.oisiID = oisiID;
    }

    @Override
    public OrthogonalInformationQuery getOrthInfo(OrthogonalInformationQuery query) {
        // Clone the query to preserve the original query
        var queryClone = query.clone();

        // Indicate that this OISI was visited
        queryClone.addVisitedOISI(this);

        /*
         * Indicate that the orthogonal information sources within this OISI were
         * considered. Do not extract query modification operations, since it may
         * depend on OISI implementation.
         * 
         * This basic OISI trivially adds all OISs it contains. In reality, OISIs
         * could have to delegate to other OISIs or only conditionally consider
         * certain OISs.
         */
        this.getOrthInfoSources().forEach(queryClone::addConsideredOIS);

        /*
         * Perform the query via query switch.
         * 
         * Let the query switch set the used executors, since it encapsulates which
         * executors may apply to which queries.
         */
        var result = querySwitch.forwardQuery(queryClone);

        /*
         * Set the query result in the OISI, since OISIs may post-process query results
         * themselves.
         * 
         * OISIs should finalise the queries, since they are the facades between
         * ChangePropagationActions and the OrthogonalInformationSources.
         */
        queryClone.setResult(result);

        return queryClone;
    }

    @Override
    public Set<OrthogonalInformationSource> getOrthInfoSources() {
        return orthInfoSources;
    }

    @Override
    public boolean removeOrthInfoSource(OrthogonalInformationSource source) {
        return orthInfoSources.remove(source);
    }

    @Override
    public boolean addOrthInfoSource(OrthogonalInformationSource source) {
        return orthInfoSources.add(source);
    }

    @Override
    public String getID() {
        return oisiID;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getClass().getSimpleName())
                .append(" ID: '").append(this.getID()).append("'")
                .append(" OISs: ").append(this.getOrthInfoSources())
                .toString();
    }

    @Override
    public boolean canHandleQuery(OrthogonalInformationQuery query) {
        return this.querySwitch.canHandleQuery(query);
    }
}
