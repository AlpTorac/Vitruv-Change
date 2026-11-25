package tools.vitruv.change.oinfo.oisi;

public interface MultiDelegatingOISI extends DelegatingOISI {
    public boolean removeDelegateOISI(OrthogonalInformationSourceInterface oisi);
    public boolean addDelegateOISI(OrthogonalInformationSourceInterface oisi);
}
