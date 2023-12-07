package ProgramNew;
import java.util.Objects;

public class Definition {

    Parameter parameter;

    String stringValue;

    int intValue;

    double doubleValue;

    public Definition(Parameter parameter, Object value) {
        this.parameter = parameter;
        setValue(value);
    }

    public Parameter getParameter() {
        return parameter;
    }

    public String getParameterSection() {
        return parameter.section;
    }

    public String getParameterName() {
        return parameter.name;
    }

    public void setParameter(Parameter parameter) {
        Object curValue = getValue();
        this.parameter = parameter;
        setValue(curValue);
    }

    public Object getValue() {
        return switch (this.parameter.valueType) {
            case 1 -> this.intValue;
            case 2 -> this.doubleValue;
            default -> this.stringValue;
        };
    }

    public void setValue(Object value) {
        try {
            switch (this.parameter.valueType) {
                case 1: this.intValue = (int) value; this.doubleValue = 0; this.stringValue = ""; break;
                case 2: this.doubleValue = (double) value; this.intValue = 0; this.stringValue = ""; break;
                default: this.stringValue = value.toString(); this.intValue = 0; this.doubleValue = 0; break;
            }
        }
        catch (Exception ignored) {
            this.intValue = 0; this.doubleValue = 0; this.stringValue = "";
        }
    }

    @Override
    public String toString() {
        return parameter.toString()+ ": " + getValue().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Definition that = (Definition) o;
        return Objects.equals(parameter, that.parameter) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter, getValue().toString());
    }
}
