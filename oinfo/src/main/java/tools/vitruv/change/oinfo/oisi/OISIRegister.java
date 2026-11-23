package tools.vitruv.change.oinfo.oisi;

import tools.vitruv.change.oinfo.query.OrthogonalInformationQuery;
import tools.vitruv.change.oinfo.source.OrthogonalInformationSource;
import tools.vitruv.change.oinfo.query.OrthogonalInformationQuerySwitch;

import java.util.ArrayList;
import java.util.List;

public class OISIRegister {
    private final List<OrthogonalInformationSourceInterface> rootOISIs = new ArrayList<>();

    public void addOISI(OrthogonalInformationSourceInterface oisi) {
        rootOISIs.add(oisi);
    }

    public void removeOISI(OrthogonalInformationSourceInterface oisi) {
        rootOISIs.remove(oisi);
    }

    public OrthogonalInformationQuery forwardQueryToOISIs(OrthogonalInformationQuery query) {
        return rootOISIs.stream().filter(oisi -> oisi.getID().equals(query.getRootOISIID())).findFirst().get()
                .getOrthInfo(query);
    }

    public void clearOISIs() {
        rootOISIs.clear();
    }
}
