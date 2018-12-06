package bsu.elliptic;

public class Point {

    enum Type {USUAL, INFINITE}

    long x;
    long y;
    Type type;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
        this.type = Type.USUAL;
    }

    public Point(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (type == Type.INFINITE) {
            return "O";
        }
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Point p = (Point) obj;
        if (type == Type.INFINITE && p.type == Type.INFINITE) {
            return true;
        }
        return type == p.type && x == p.x && y == p.y;
    }
}
