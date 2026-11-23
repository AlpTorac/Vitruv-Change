package tools.vitruv.change.oinfo.oisi;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class OISIRegister {
    private final Set<OrthogonalInformationSourceInterface> rootOISIs = new LinkedHashSet<>();

    public boolean addOISI(OrthogonalInformationSourceInterface oisi) {
        return rootOISIs.add(oisi);
    }

    public boolean removeOISI(OrthogonalInformationSourceInterface oisi) {
        return rootOISIs.remove(oisi);
    }

    public List<OrthogonalInformationSourceInterface> getAllOISIs() {
        return List.copyOf(rootOISIs);
    }

    public OrthogonalInformationQuery forwardQueryToOISIs(OrthogonalInformationQuery query) {
        return rootOISIs.stream().filter(oisi -> oisi.getID().equals(query.getRootOISIID())).findFirst().get()
                .getOrthInfo(query);
    }

    public void clearOISIs() {
        rootOISIs.clear();
    }

    public boolean canHandleQuery(OrthogonalInformationQuery query) {
        return rootOISIs.stream().anyMatch(oisi -> oisi.getID().equals(query.getRootOISIID()));
    }
}
