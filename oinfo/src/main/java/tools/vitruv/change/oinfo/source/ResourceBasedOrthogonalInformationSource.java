package tools.vitruv.change.oinfo.source;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;

public class ResourceBasedOrthogonalInformationSource extends BasicOrthogonalInformationSource {
    private final Resource modelResource;

    public ResourceBasedOrthogonalInformationSource(Collection<String> metamodelURIs, Resource modelResource) {
        super(modelResource.getURI().toString(), metamodelURIs);
        this.modelResource = modelResource;
    }

    public Resource getSourceResource() {
        return modelResource;
    }

    @Override
    public String toString() {
        return super.toString() + " Resource URI: " + modelResource.getURI();
    }
}
