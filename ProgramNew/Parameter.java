package ProgramNew;

import java.util.Objects;

public class Parameter {
    String section;
    String name;
    int valueType;

    public Parameter(String section, String name, int valueType) {
        this.section = section;
        this.name = name;
        this.valueType = valueType;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValueType() {
        return this.valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        String typeStr = switch (this.valueType) {
            case 1 -> "Integer";
            case 2 -> "Double";
            default -> "String";
        };
        return String.format("%s %s %s", typeStr, this.section, this.name);
    }

    public String toStringReqLength(int requiredLength) {
        String result = this.toString();
        int length = result.length();
        if (length > requiredLength) {
            return result.substring(requiredLength);
        }
        else {
            return result + " ".repeat(requiredLength-length);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(section, parameter.section) && Objects.equals(name, parameter.name) &&
                Objects.equals(valueType, parameter.valueType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section, name, valueType);
    }

}
