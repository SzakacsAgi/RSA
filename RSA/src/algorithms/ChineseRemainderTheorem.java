package algorithms;

import java.math.BigInteger;

public class ChineseRemainderTheorem {

    private final FastModularExponentiation fastModularExponentiation = new FastModularExponentiation();
    private final ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();

    public BigInteger algorithm(BigInteger m, BigInteger q, BigInteger p, BigInteger d){
        var n = p.multiply(q);
        System.out.println("n: "+n);
        System.out.println("m: "+m);
        System.out.println("q: "+q);
        System.out.println("p: "+p);
        System.out.println("d: "+d);
        var c1 = fastModularExponentiation.algorithm(m, (d.mod(p.subtract(BigInteger.ONE))), p);
        //System.out.println(d.mod(p.subtract(BigInteger.ONE)));
        System.out.println("c1: "+c1);
        var c2 = fastModularExponentiation.algorithm(m, (d.mod(q.subtract(BigInteger.ONE))), q);
        System.out.println("c2: "+c2);
        var y1 = extendedEuclidean.getX(q,p);
        System.out.println("y1: "+y1);
        var y2 = extendedEuclidean.getY(q,p);
        System.out.println("y2: "+y2);
        return (c1.multiply(y1).multiply(q)).add((c2.multiply(y2).multiply(p))).mod(n);
    }
}
