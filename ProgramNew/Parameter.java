package ProgramNew;

import java.util.Objects;

public class Parameter {
    String section;
    String name;

    public Parameter(String section, String name) {
        this.section = section;
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("%s %s", this.section, this.name);
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
        return Objects.equals(section, parameter.section) && Objects.equals(name, parameter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section, name);
    }

}
