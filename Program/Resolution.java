package Program;

import java.util.Objects;

public class Resolution {
    int width;
    int height;
    String marketingName;

    public Resolution(int width, int height, String marketingName) {
        this.width = width;
        this.height = height;
        this.marketingName = marketingName;
    }

    @Override
    public String toString() {
        return (marketingName == null || marketingName.isBlank()) ? String.format("%dx%d", width, height) : String.format("%s (%dx%d)", marketingName, width, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resolution that = (Resolution) o;
        return width == that.width && height == that.height && Objects.equals(marketingName, that.marketingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, marketingName);
    }
}
