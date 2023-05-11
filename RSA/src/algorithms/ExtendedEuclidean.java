package algorithms;

import java.math.BigInteger;

public class ExtendedEuclidean extends Euclidean{
    BigInteger x0 = BigInteger.ONE;
    BigInteger x1 = BigInteger.ZERO;
    BigInteger y0 = BigInteger.ZERO;;
    BigInteger y1 = BigInteger.ONE;
    BigInteger s = BigInteger.ONE;
    BigInteger q;
    BigInteger r;
    BigInteger x;
    BigInteger y;

    public BigInteger getX(BigInteger a, BigInteger b){
        if(b.compareTo(a) > 0 ){
            BigInteger storage = BigInteger.ONE;
            storage = a;
            a = b;
            b = storage;
        }
        while(!b.equals(BigInteger.ZERO)){
            r = a.mod(b);
            q = a.divide(b);
            a = b;
            b = r;
            x = x1;
            x1 = (q.multiply(x1)).add(x0);
            x0 = x;
            s = s.multiply(BigInteger.valueOf(-1));
        }
        x = s.multiply(x0);
        return x;
    }

    public BigInteger getY(BigInteger a, BigInteger b){
        if(b.compareTo(a) > 0 ){
            BigInteger storage = BigInteger.ONE;
            storage = a;
            a = b;
            b = storage;
        }
        while(!b.equals(BigInteger.ZERO)){
            r = a.mod(b);
            q = a.divide(b);
            a = b;
            b = r;
            y = y1;
            y1 = (q.multiply(y1)).add(y0);
            y0 = y;
            s = s.multiply(BigInteger.valueOf(-1));
        }
        y = s.multiply(y0).multiply(BigInteger.valueOf(-1));
        return y;
    }



}
