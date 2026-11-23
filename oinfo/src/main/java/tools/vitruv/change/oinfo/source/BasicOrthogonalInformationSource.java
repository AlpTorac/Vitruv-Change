package tools.vitruv.change.oinfo.source;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class BasicOrthogonalInformationSource implements OrthogonalInformationSource {
    private final String sourceName;
    private final Set<String> metamodelURIs = new LinkedHashSet<>();

    public BasicOrthogonalInformationSource(String sourceName, Collection<String> metamodelURIs) {
        this.sourceName = sourceName;
        this.metamodelURIs.addAll(metamodelURIs);
    }

    @Override
    public Set<String> getSourceMetamodelURIs() {
        return Set.copyOf(metamodelURIs);
    }

    @Override
    public String getSourceName() {
        return sourceName;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getClass().getSimpleName())
                .append(" Name: '").append(sourceName).append("'")
                .append(" Metamodels: ").append(metamodelURIs)
                .toString();
    }
}
