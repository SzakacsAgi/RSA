package algorithms;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;
import  java.lang.*;

public class MillerRabin {
    private final BigInteger numberOfTests = BigInteger.valueOf(8);
    private BigInteger n;
    private BigInteger s = BigInteger.ZERO;
    private BigInteger d;
    public BigInteger a;
    private boolean isPrime = true;
    private final FastModularExponentiation modular = new FastModularExponentiation();

    public boolean algorithm(BigInteger testingNumber) {
        this.n = testingNumber;

        if ((n.compareTo(BigInteger.ONE) < 0) || n.compareTo(BigInteger.ONE) == 0) {
            return false;
        } else if (Objects.equals(n, (BigInteger.TWO))) {
            return true;
        }else if (Objects.equals(n, (BigInteger.valueOf(3)))) {
            return true;
        }
        else if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        setDAndS();

        for (var i = BigInteger.ZERO; i.compareTo(numberOfTests) < 0; i = i.add(BigInteger.ONE)) {
            a = generateRandomBigInteger(BigInteger.TWO, n.subtract(BigInteger.TWO));
            //a=BigInteger.valueOf(1843);

            boolean isComposite = isComposite();
            if (isComposite) {
                isPrime = false;
            } else {
                isPrime = true;
                break;
            }
        }
        return isPrime;
    }

    private void setDAndS() {
        BigInteger quotient;
        BigInteger remainder;
        d = n.subtract(BigInteger.ONE);
        while (true) {
            quotient = d.divide(BigInteger.TWO);
            remainder = d.mod(BigInteger.TWO);
            if (remainder.equals(BigInteger.ONE)) {
                break;
            }
            s = s.add(BigInteger.ONE);
            d = quotient;
        }
        //System.out.println(s);
        //System.out.println(d);
    }

    private boolean isComposite() {

        if (Objects.equals(modular.algorithm(a, d, n), BigInteger.ONE)) {
            return false;
        }

        var d = this.d;

        for (var i = BigInteger.ZERO; i.compareTo(s.subtract(BigInteger.ONE)) < 0 || i.compareTo(s.subtract(BigInteger.ONE)) == 0; i = i.add(BigInteger.ONE)) {

            if (Objects.equals(modular.algorithm(a, d, n), n.subtract(BigInteger.ONE))) {
                return false;
            }

            a = modular.algorithm(a, d, n);
            d = BigInteger.TWO;
        }

        return true;
    }


    private BigInteger generateRandomBigInteger(BigInteger minVal, BigInteger maxVal) {
        BigInteger result;

        do {
            result = new BigInteger(maxVal.subtract(minVal).bitLength(), new SecureRandom())
                    .add(minVal);
//            System.out.println("eoooooooooooo");
//            System.out.println(result+" "+maxVal);
        } while (result.compareTo(maxVal) > 0);
//        System.out.println("END WHILE");
        return result;
    }


    public boolean testAlgorithm(BigInteger testingNumber, BigInteger a) {
        this.n = testingNumber;

        if ((n.compareTo(BigInteger.ONE) < 0) || n.compareTo(BigInteger.ONE) == 0) {
            return false;
        } else if (Objects.equals(n, (BigInteger.TWO))) {
            return true;
        } else if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        setDAndS();

            this.a = a;
            boolean isComposite = isComposite();
            if (isComposite) {
                isPrime = false;
            } else {
                isPrime = true;
            }

        System.out.println("N: " + n + " a: " + a + " prime: " + isPrime);
        return isPrime;
    }
}