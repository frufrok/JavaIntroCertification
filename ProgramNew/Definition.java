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

    public void setParameter(Parameter parameter) {
        Object curValue = getValue();
        this.parameter = parameter;
        setValue(curValue);
    }

    public Object getValue() {
        return switch (this.parameter.valueType) {
            case 1 -> (Object) this.intValue;
            case 2 -> (Object) this.doubleValue;
            default -> (Object) this.stringValue;
        };
    }

    public void setValue(Object value) {
        try {
            switch (this.parameter.valueType) {
                case 1: this.intValue = (int) value; this.doubleValue = 0; this.stringValue = "";
                case 2: this.doubleValue = (double) value; this.intValue = 0; this.stringValue = "";
                default: this.stringValue = value.toString(); this.intValue = 0; this.doubleValue = 0;
            }
        }
        catch (Exception ignored) {}
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
