package tools.vitruv.change.oinfo.oisi;

public interface SingleDelegatingOISI extends DelegatingOISI {
    public void setDelegateOISI(OrthogonalInformationSourceInterface oisi);
    public OrthogonalInformationSourceInterface getDelegateOISI();
}
