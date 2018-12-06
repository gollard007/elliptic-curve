package bsu.elliptic;

import java.util.ArrayList;
import java.util.List;

public class EllipticCurve {

    int p, a, b;
    private List<Point> points;

    // initialize the elliptic curve by the prime number p
    // and the coefficients a, b of the defining equation
    // y^2=x^3+a*x+b
    public EllipticCurve(int p, int a, int b) {
        this.p = p;
        this.a = a;
        this.b = b;
        this.points = initPoints();
    }

    private List<Point> initPoints() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(Point.Type.INFINITE));
        for (int i = 0; i< p; i++) {
            long nY = (i*i*i + a*i + b) % p;
            for (int j = 0; j< p; j++) {
                if (j*j % p == nY) {
                    points.add(new Point(i,j));
                }
            }
        }
        return points;
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public Point add(Point p1, Point p2) {
        // P + O = O + P = P
        if (p1.type == Point.Type.INFINITE) return p2;
        if (p2.type == Point.Type.INFINITE) return p1;

        if (p1.y == 0 && p2.y == 0) {
            return new Point(Point.Type.INFINITE);
        }

        long s;
        if (p1.x == p2.x) {
            if (p1.y == -p2.y) return new Point(Point.Type.INFINITE);
            s = (3*p1.x*p1.x + a) / 2 * p1.y;
        } else {
            s = (p1.y - p2.y) / (p1.x - p2.x);
        }
        long pX = s*s - p1.x - p2.x;
        long pY = -p1.y + s*(p1.x - pX);
        return new Point(pX, pY);
    }

    long getOrder(Point point) {
        if (point.type == Point.Type.INFINITE) return 1;
        return p;
//        long startOrder = 1;
//        Point doublePoint = add(point, point);
//        if (doublePoint.type == Point.Type.INFINITE) return 2;
//
//        Point tmp1; // нечетный порядок
//        Point tmp2 = doublePoint; //четный порядок
//
//
//        boolean t = true;
//        while (t) {
//            tmp1 = add(tmp2, point);
//            tmp2 = add(tmp2, doublePoint);
//            if (tmp2.type == Point.Type.INFINITE || tmp1.type == Point.Type.INFINITE) {
//                t =false;
//            }
//            startOrder+=2;
//        }
//        return startOrder;
    }

}
