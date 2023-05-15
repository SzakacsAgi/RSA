package algorithms;

import java.math.BigInteger;

public class ChineseRemainderTheorem {

    private final FastModularExponentiation fastModularExponentiation = new FastModularExponentiation();
    private final ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();

    public BigInteger algorithm(BigInteger m, BigInteger q, BigInteger p, BigInteger d){
        var n = p.multiply(q);
        var c1 = fastModularExponentiation.algorithm(m, (d.mod(p.subtract(BigInteger.ONE))), p);
        var c2 = fastModularExponentiation.algorithm(m, (d.mod(q.subtract(BigInteger.ONE))), q);
        var y2 = extendedEuclidean.algorithm(p,q)[0];
        var y1 = extendedEuclidean.algorithm(p,q)[1];

        return (c1.multiply(y1).multiply(q)).add((c2.multiply(y2).multiply(p))).mod(n);
    }
}
