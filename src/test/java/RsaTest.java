import Rsa.RsaKeyGenerator;
import Rsa.Rsa;
import algorithms.Euclidean;
import algorithms.FastModularExponentiation;
import algorithms.MillerRabin;
import java.math.BigInteger;
import org.junit.jupiter.api.*;

public class RsaTest {
    static  RsaKeyGenerator rsaKeyGenerator;
    static Rsa RSA;
    static  BigInteger p;
    static BigInteger q;
    static BigInteger n ;
    static BigInteger phiN;
    static BigInteger pk;
    static BigInteger sk;
    static BigInteger aInSetP;
    static BigInteger aInSetQ;
    static BigInteger messageToEncryption;
    static BigInteger encryption;
    static BigInteger decryption;

    @BeforeAll
    public static void executeRSAAlgorithm(){
        rsaKeyGenerator = new RsaKeyGenerator();
        p = rsaKeyGenerator.getP();
        aInSetP = rsaKeyGenerator.finalAInsetP;
        q = rsaKeyGenerator.getQ();
        aInSetQ = rsaKeyGenerator.finalAInsetQ;
        n = rsaKeyGenerator.getN();
        phiN = rsaKeyGenerator.getPhiN();
        pk = rsaKeyGenerator.getPk();
        sk = rsaKeyGenerator.getSk();

        RSA = new Rsa();
        RSA.setP(p);
        RSA.setQ(q);
        RSA.setN(n);
        RSA.setPk(pk);
        RSA.setSk(sk);
        messageToEncryption = BigInteger.valueOf(20345);
        encryption = RSA.encryption(messageToEncryption);
        decryption = RSA.decryption();
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
    @DisplayName("encryptioned message is m on the power of pk mod n")
    public void encryptionedMessageIsRight(){
        var fastModularExponentiation = new FastModularExponentiation();
        Assertions.assertEquals(fastModularExponentiation.algorithm(messageToEncryption, pk, n), encryption);
    }

    @Test
    @DisplayName("decryption is return with the value of messageToEncryption")
    public void decryptionMessageIsRight(){
        Assertions.assertEquals(decryption, messageToEncryption);
    }
}