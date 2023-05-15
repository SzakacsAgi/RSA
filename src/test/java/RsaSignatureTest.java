import Rsa.RsaSignature;
import Rsa.RsaKeyGenerator;
import algorithms.ChineseRemainderTheorem;
import algorithms.Euclidean;
import algorithms.MillerRabin;
import algorithms.FastModularExponentiation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class RsaSignatureTest {
    static RsaKeyGenerator rsaKeyGenerator;
    static RsaSignature RSASignature;
    static BigInteger p;
    static BigInteger q;
    static BigInteger n ;
    static BigInteger phiN;
    static BigInteger pk;
    static BigInteger sk;
    static BigInteger aInSetP;
    static BigInteger aInSetQ;
    static BigInteger messageToSign;
    static BigInteger signedMessage;
    static boolean signedMessageIsRight;

    @BeforeAll
    public static void executeRSASignatureAlgorithm(){
        rsaKeyGenerator = new RsaKeyGenerator();
        p = rsaKeyGenerator.getP();
        aInSetP = rsaKeyGenerator.finalAInsetP;
        q = rsaKeyGenerator.getQ();
        aInSetQ = rsaKeyGenerator.finalAInsetQ;
        n = rsaKeyGenerator.getN();
        phiN = rsaKeyGenerator.getPhiN();
        pk = rsaKeyGenerator.getPk();
        sk = rsaKeyGenerator.getSk();

        RSASignature = new RsaSignature();
        RSASignature.setP(p);
        RSASignature.setQ(q);
        RSASignature.setN(n);
        RSASignature.setPk(pk);
        RSASignature.setSk(sk);
        messageToSign = BigInteger.valueOf(20345);
        signedMessage = RSASignature.signature(messageToSign);
        signedMessageIsRight = RSASignature.isSignatureRight();
    }

    @Test
    @DisplayName("p is prime")
    public void pIsPrime() {
        var millerRabin = new MillerRabin();
        millerRabin.a = aInSetP;
        System.out.println("-------------------");
        System.out.println("p is prime test");
        Assertions.assertTrue(millerRabin.testAlgorithm(p, aInSetP));
    }

    @Test
    @DisplayName("q is prime")
    public void qIsPrime() {
        var millerRabin = new MillerRabin();
        millerRabin.a = aInSetQ;
        System.out.println("-------------------");
        System.out.println("q is prime test");
        Assertions.assertTrue(millerRabin.testAlgorithm(q, aInSetQ));
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

    @Test
    @DisplayName("pk is bigger then 1 and lower then phiN")
    public void pkIsInRightInterval() {
        //var fastModularExponentiation = new FastModularExponentiation();
        Assertions.assertTrue(pk.compareTo(BigInteger.TWO) > 0 || pk.compareTo(phiN) <= 0);
    }

    @Test
    @DisplayName("sk is bigger then 1 and lower then phiN")
    public void SkIsInRightInterval() {
        //var fastModularExponentiation = new FastModularExponentiation();
        Assertions.assertTrue(sk.compareTo(BigInteger.TWO) > 0 || sk.compareTo(phiN) <= 0);
    }

    @Test
    @DisplayName("signed message is right")
    public void signedMessageIsRight() {
        var chineseRemainderTheorem = new ChineseRemainderTheorem();
        Assertions.assertEquals(chineseRemainderTheorem.algorithm(messageToSign, q, p, sk), signedMessage);
    }

    @Test
    @DisplayName("signature is right")
    public void signatureIsRight() {
        var fastModularExponentiation = new FastModularExponentiation();
        Assertions.assertEquals(fastModularExponentiation.algorithm(signedMessage, pk, n), messageToSign);
    }

}
