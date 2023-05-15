package algorithms;

import java.math.BigInteger;

public class ExtendedEuclidean extends Euclidean{
public BigInteger[] algorithm(BigInteger a, BigInteger b){
    BigInteger x0 = BigInteger.ONE, x1 = BigInteger.ZERO, y0 = BigInteger.ZERO, y1 = BigInteger.ONE, s = BigInteger.ONE;

    while (!b.equals(BigInteger.ZERO)) {
        BigInteger r = a.mod(b);
        BigInteger q = a.divide(b);
        a = b;
        b = r;

        BigInteger x = x1, y = y1;
        x1 = q.multiply(x1).add(x0);
        y1 = q.multiply(y1).add(y0);
        x0 = x;
        y0 = y;
        s = s.negate();
    }

    BigInteger x = s.multiply(x0);
    BigInteger y = s.negate().multiply(y0);

    BigInteger[] result = {x, y};
    return result;
}

}
