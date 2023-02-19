public class Range {

    private int low;
    private int high;

    public Range(int low, int high) {
        if (low > high)
            throw new IllegalArgumentException("Low cannot be greater than high");
        this.low = low;
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]", low, high);
    }
}
