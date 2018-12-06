package bsu.elliptic;

public class Module {

    // a/b mod m
    public static long divide(long a, long b, long m) {
        return a * inverse(b, m) % m;
    }

    private static long pow(long base, long p, long m) {
        if (p == 1) {
            return base;
        }
        if (p % 2 == 0) {
            long t = pow(base, p / 2, m);
            return t * t % m;
        } else {
            return pow(base, p - 1, m) * base % m;
        }
    }

    private static long inverse(long x, long m) {
        return pow(x, m - 2, m);
    }
}
