package me.bayanov.range;

public class Range {

    private float from;
    private float to;

    public Range(float from, float to) {
        if (from <= to) {
            this.from = from;
            this.to = to;
        } else {
            this.from = to;
            this.to = from;
        }
    }

    public float getFrom() {
        return from;
    }

    public float getTo() {
        return to;
    }

    public float getLength() {
        return to - from;
    }

    public boolean isInside(float input) {
        return input >= from && input <= to;
    }

    public Range getIntersection(Range input) {
        if (input == null) {
            return null;
        }

        if (isInside(input.from)) {
            if (isInside(input.to)) {
                return new Range(input.from, input.to);
            } else {
                return new Range(input.from, to);
            }
        } else {
            if (isInside(input.to)) {
                return new Range(from, input.to);
            } else {
                if (input.isInside(from)) {
                    return new Range(from, to);
                } else {
                    return null;
                }
            }
        }
    }

    public Range[] getUnion(Range input) {
        if (input == null) {
            return null;
        }

        if (isInside(input.from)) {
            if (isInside(input.to)) {
                return new Range[]{new Range(from, to)};
            } else {
                return new Range[]{new Range(from, input.to)};
            }
        } else {
            if (isInside(input.to)) {
                return new Range[]{new Range(input.from, to)};
            } else {
                if (input.isInside(from)) {
                    return new Range[]{new Range(input.from, input.to)};
                } else {
                    return new Range[]{new Range(from, to), new Range(input.from, input.to)};
                }
            }
        }
    }

    public Range[] getComplement(Range input) {
        if (input == null) {
            return null;
        }

        if (isInside(input.from)) {
            if (isInside(input.to)) {
                return new Range[]{new Range(from, input.from), new Range(input.to, to)};
            } else {
                return new Range[]{new Range(from, input.from)};
            }
        } else {
            if (isInside(input.to)) {
                return new Range[]{new Range(from, input.to)};
            } else {
                if (input.isInside(from)) {
                    return new Range[] {};
                } else {
                    return new Range[]{new Range(from, to)};
                }
            }
        }
    }
}
