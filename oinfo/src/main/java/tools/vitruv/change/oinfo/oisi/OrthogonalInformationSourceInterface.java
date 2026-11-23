package tools.vitruv.change.oinfo.oisi;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;

/**
 * An interface for classes that serve as facades between
 * ChangePropagationAction instances and OrthogonalInformationSource instances.
 * 
 * OrthogonalInformationSourceInterface (OISI) is supposed to setup:
 * 
 * 1) All objects that encapsulate orthogonal information sources (such as
 * Resource instances that are supposed to represent orthogonal information
 * sources in code)
 * 
 * 2) All other objects that enable querying the orthogonal information sources
 * (via OrthogonalInformationQuerySwitch)
 * 
 * There are multiple forms of OISIs:
 * 
 * - Component OISIs that consider no OrthogonalInformationSources and simply
 * pre- or post-process queries; such as caching query results, filtering query
 * results or formatting queries.
 * 
 * - Aggregator OISIs that take queries and distribute them to further OISIs.
 * These OISIs are prime candidates to become root OISIs, which receive the
 * original query from ChangePropagationActions.
 * 
 * - Basic OISIs that utilise query executors to perform queries on
 * OrthogonalInformationSources.
 */
public interface OrthogonalInformationSourceInterface {
    public boolean canHandleQuery(OrthogonalInformationQuery query);
    public OrthogonalInformationQuery getOrthInfo(OrthogonalInformationQuery query);
    public String getID();
    public void setID(String oisiID);
}
