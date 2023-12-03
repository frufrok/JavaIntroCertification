package Program;

import java.util.Objects;

public class Display {
    String displayType;
    Resolution resolution;
    double size;
    double frequency;
    int brightness;

    public Display(String displayType, Resolution resolution, double size, double frequency, int brightness) {
        this.displayType = displayType;
        this.resolution = resolution;
        this.size = size;
        this.frequency = frequency;
        this.brightness = brightness;
    }

    public static Display[] generateRandomDisplays(int count) {
        if (count > 0) {
            String[] displayTypes = {"LCD", "LED", "AMOLED", "RETINA", "IPS LCS"};
            Resolution[] resolutions = {
                    new Resolution(1280, 720, "HD"),
                    new Resolution(1920, 1080, "FullHD"),
                    new Resolution(3840, 2160, "4K UHD"),
                    new Resolution(7680, 4320, "8K UHD")
            };
            Double[] displaySizes = {10.0, 13.0, 15.6, 17.0, 19.0};
            Double[] frequencies = {59.9, 60.0, 120.0, 144.0};
            Integer[] brightnesses = {300, 350, 400, 450, 500};

            Display[] result = new Display[count];
            for (int i = 0; i < count; i++) {
                result[i] = new Display(
                        MyUtils.getRandom(displayTypes),
                        MyUtils.getRandom(resolutions),
                        MyUtils.getRandom(displaySizes),
                        MyUtils.getRandom(frequencies),
                        MyUtils.getRandom(brightnesses));
            }
            return result;
        }
        else return null;
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

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
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
