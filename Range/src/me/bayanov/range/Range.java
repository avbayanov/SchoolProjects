package me.bayanov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        if (from <= to) {
            this.from = from;
            this.to = to;
        } else {
            this.from = to;
            this.to = from;
        }
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double input) {
        return input >= from && input <= to;
    }

    public Range getIntersection(Range range) {
        if (range == null) {
            throw new NullPointerException("Range must not be null");
        }

        if (isInside(range.from) || isInside(range.to) || range.isInside(from) || range.isInside(to)) {
            return new Range(Math.max(from, range.from), Math.min(to, range.to));
        } else {
            return null;
        }
    }

    public Range[] getUnion(Range range) {
        if (range == null) {
            throw new NullPointerException("Range must not be null");
        }

        if (isInside(range.from) || isInside(range.to) || range.isInside(from) || range.isInside(to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        } else {
            return new Range[]{
                    new Range(Math.min(from, range.from), Math.min(to, range.to)),
                    new Range(Math.max(from, range.from), Math.max(to, range.to))
            };
        }
    }

    public Range[] getDifference(Range range) {
        if (range == null) {
            throw new NullPointerException("Range must not be null");
        }

        if (to > range.from) {
            if (from < range.to) {
                if (from >= range.from) {
                    if (to > range.to) {
                        return new Range[]{new Range(range.to, to)};
                    } else {
                        return new Range[]{};
                    }
                } else {
                    if (to > range.to) {
                        return new Range[]{new Range(from, range.from), new Range(range.to, to)};
                    } else {
                        return new Range[]{new Range(from, range.from)};
                    }
                }
            } else {
                return new Range[]{new Range(from, to)};
            }
        } else {
            return new Range[]{new Range(from, to)};
        }
    }
}
