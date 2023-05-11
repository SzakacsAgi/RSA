package RSA;

import algorithms.*;

import java.math.BigInteger;
import java.util.Random;

public class RsaKeyGenerator {

    private BigInteger p;
    private BigInteger q;
    private MillerRabin millerRabin = new MillerRabin();
    private ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();
    private Euclidean euclidean = new Euclidean();
    private FastModularExponentiation fastModularExponentiation = new FastModularExponentiation();
    private ChineseRemainderTheorem chineseRemainderTheorem = new ChineseRemainderTheorem();
    private BigInteger n;

    public BigInteger getEncryption() {
        return encryption;
    }

    public void setEncryption(BigInteger encryption) {
        this.encryption = encryption;
    }

    private BigInteger encryption;

    public BigInteger getDecryption() {
        return decryption;
    }

    public void setDecryption(BigInteger decryption) {
        this.decryption = decryption;
    }

    private BigInteger decryption;

    public BigInteger getSk() {
        return sk;
    }

    public void setSk(BigInteger sk) {
        this.sk = sk;
    }

    private BigInteger sk;

    public BigInteger getPk() {
        return pk;
    }

    public void setPk(BigInteger pk) {
        this.pk = pk;
    }

    private BigInteger pk;


    private BigInteger phiN;


    public RsaKeyGenerator(){
        setP();
        setQ();
        setN();
        setPhiN();
        getPublicKey();
        getPrivateKey();
    }

    public BigInteger getPublicKey(){
        boolean isRelativePrime = false;
        BigInteger bigInteger = null;
        while (!isRelativePrime) {
            Random random = new Random();
            bigInteger = new BigInteger(8, random);
            isRelativePrime = euclidean.getGcd(getPhiN(), bigInteger).equals(BigInteger.ONE) ;
        }
        setPk(bigInteger);
        System.out.println("Public key (e): "+getPk());
        return pk;
    }

    public BigInteger getPrivateKey(){

        //this.p  =BigInteger.valueOf(45);
        //this.q = BigInteger.valueOf(221);
        setSk(extendedEuclidean.getY(getPhiN(), getPk()));
        System.out.println("Secret key (d): "+getSk());
        System.out.println("x: "+extendedEuclidean.getX(getQ(), getP()));
        System.out.println("y: "+extendedEuclidean.getY(getQ(), getP()));
        return getSk();
    }

    public BigInteger encryption(BigInteger m){
        System.out.println();
        System.out.println();
        setEncryption(fastModularExponentiation.algorithm(m, getPk(), getN()));
        System.out.println("Encryptioned message: "+getEncryption());
        return getEncryption();
    }

    public BigInteger decryption(){
        setDecryption(chineseRemainderTheorem.algorithm(getEncryption(), getQ(), getP(), getSk()));
        System.out.println("Decryptioned message: "+getDecryption());
        return getDecryption();
    }









    public BigInteger getP() {
        return p;
    }

    public void setP() {
        boolean isPrime = false;
        BigInteger bigInteger = null;
        while (!isPrime) {
            Random random = new Random();
            bigInteger = new BigInteger(15, random);
            isPrime = millerRabin.algorithm(bigInteger);
        }
        this.p = bigInteger;
        System.out.println("P: "+this.p);
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ() {
        boolean isPrime = false;
        BigInteger bigInteger = null;
        while (!isPrime) {
            Random random = new Random();
            bigInteger = new BigInteger(8, random);
            isPrime = millerRabin.algorithm(bigInteger);
        }
        this.q = bigInteger;
        System.out.println("Q: "+this.q);
    }

    public BigInteger getN() {
        return n;
    }

    public void setN() {
        this.n = getP().multiply(getQ());
        System.out.println("n: "+getN());
    }

    public BigInteger getPhiN() {
        return phiN;
    }

    public void setPhiN() {
        this.phiN = this.p.subtract(BigInteger.ONE).multiply(this.q.subtract(BigInteger.ONE));
        System.out.println("phiN: "+this.phiN);
    }


}
