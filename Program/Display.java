package Program;

import java.util.Objects;

public class Display {
    String displayType;
    Resolution resolution;
    double size;
    double frequency;
    double brightness;

    public Display(String displayType, Resolution resolution, double size, double frequency, double brightness) {
        this.displayType = displayType;
        this.resolution = resolution;
        this.size = size;
        this.frequency = frequency;
        this.brightness = brightness;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public String getResolutionString() {
        return resolution.toString();
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    @Override
    public String toString() {
        return String.format("%s %f'' %s %f Hz %f cd/m²", displayType, size, this.getResolutionString(), frequency, brightness);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Display display = (Display) o;
        return Double.compare(size, display.size) == 0 && Double.compare(frequency, display.frequency) == 0 && Double.compare(brightness, display.brightness) == 0 && Objects.equals(displayType, display.displayType) && Objects.equals(resolution, display.resolution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayType, resolution, size, frequency, brightness);
    }
}
