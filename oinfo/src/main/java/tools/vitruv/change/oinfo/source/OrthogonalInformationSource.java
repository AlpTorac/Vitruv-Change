package tools.vitruv.change.oinfo.source;

import java.util.Set;

/**
 * An interface for classes that encapsulate orthogonal information sources.
 * Orthogonal information sources may come in arbitrary forms. The implementors
 * of this class grant access to the properties of the orthogonal information
 * sources, as well as any artifacts they may include (such as model Resource
 * instances).
 */
public interface OrthogonalInformationSource {
    public Set<String> getSourceMetamodelURIs();

    public String getSourceName();
}