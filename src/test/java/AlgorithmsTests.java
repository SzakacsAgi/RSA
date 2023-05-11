import RSA.RsaKeyGenerator;
import algorithms.Euclidean;
import algorithms.ExtendedEuclidean;
import algorithms.FastModularExponentiation;
import algorithms.MillerRabin;
import RSA.RsaKeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class AlgorithmsTests {

    RsaKeyGenerator rsaKeyGenerator = new RsaKeyGenerator();
    BigInteger p = rsaKeyGenerator.getP();
    BigInteger q = rsaKeyGenerator.getQ();
    BigInteger n = rsaKeyGenerator.getN();
    BigInteger phiN = rsaKeyGenerator.getPhiN();
    BigInteger pk = rsaKeyGenerator.getPk();
    BigInteger sk = rsaKeyGenerator.getSk();
    BigInteger messageToEncryption = BigInteger.valueOf(20);
    //BigInteger encryption = rsaKeyGenerator.encryption(messageToEncryption);


    @Test
    @DisplayName("p is prime")
    public void pIsPrime() {
        var millerRabin = new MillerRabin();
        Assertions.assertTrue(millerRabin.algorithm(p));
    }

    @Test
    @DisplayName("q is prime")
    public void qIsPrime() {
        var millerRabin = new MillerRabin();
        Assertions.assertTrue(millerRabin.algorithm(q));
    }

    @Test
    @DisplayName("n is multiple to p and q")
    public void nIsMultipleToPandQ() {
        Assertions.assertEquals(p.multiply(q), n);
    }

    @Test
    @DisplayName("phiN is multiple to p-1 and q-1")
    public void phiNIsMultipleToPMinusOneandQMinusOne() {
        Assertions.assertEquals(p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)), phiN);
    }

    @Test
    @DisplayName("pk is relative prime with phiN")
    public void pkIsRelativePrimeWithPhiN() {
        var euclidean = new Euclidean();
        Assertions.assertEquals(euclidean.getGcd(pk, phiN), BigInteger.ONE);
    }

    @Test
    @DisplayName("sk multiply with pk is 1 modulus phiN")
    public void skMultiplyWithPkIsOne() {
        //var fastModularExponentiation = new FastModularExponentiation();
        Assertions.assertEquals(sk.multiply(pk).mod(phiN), BigInteger.ONE);
    }






    //Extended Euclidean
    ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();
    BigInteger a = BigInteger.valueOf(221);
    BigInteger b = BigInteger.valueOf(45);
    @Test
    @DisplayName("ax + by = 1 if the first parameter is bigger than second")
    public void ExtendedEuclideanFirstParameterIsBiggerThanSecond() {
        var x = extendedEuclidean.getX(a, b);
        var y = extendedEuclidean.getY(a, b);
        Assertions.assertEquals(a.multiply(x).add(b.multiply(y)), BigInteger.ONE);
    }

    @Test
    @DisplayName("ax + by = 1 if the second parameter is bigger than first")
    public void ExtendedEuclideanSecondParameterIsBiggerThanFirst() {
        var x = extendedEuclidean.getX(b, a);
        var y = extendedEuclidean.getY(b, a);
        Assertions.assertEquals(a.multiply(x).add(b.multiply(y)), BigInteger.ONE);
    }


}
