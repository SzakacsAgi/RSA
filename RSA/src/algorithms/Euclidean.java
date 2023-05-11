package algorithms;

import java.math.BigInteger;

public class Euclidean {
    BigInteger gcd;

    public BigInteger getGcd(BigInteger a, BigInteger b) {
        gcd = a;
        if (!b.equals(BigInteger.ZERO)){
            getGcd(b, a.mod(b));
        }
        return gcd;
    }
}
