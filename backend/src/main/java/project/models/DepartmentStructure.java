package project.models;

public class DepartmentStructure {

    private String value;
    private String label;

    private  DepartmentStructure[] children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public DepartmentStructure[] getChildren() {
        return children;
    }

    public void setChildren(DepartmentStructure[] children) {
        this.children = children;
    }
}
