package Rsa;

import algorithms.*;
import java.math.BigInteger;
import java.util.Random;

public class RsaKeyGenerator {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phiN;
    private BigInteger pk;
    private BigInteger sk;
    public MillerRabin millerRabin = new MillerRabin();
    private Euclidean euclidean = new Euclidean();
    private ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();
    public BigInteger finalAInsetQ;
    public BigInteger finalAInsetP;

    public RsaKeyGenerator(){
        setP();
        setQ();
        setN();
        setPhiN();
        getPublicKey();
        getSecretKey();
    }

    public BigInteger getPublicKey(){
        boolean isRelativePrime = false;
        BigInteger bigInteger = BigInteger.ONE;
        while (!isRelativePrime || bigInteger.compareTo(BigInteger.TWO) < 0 || bigInteger.compareTo(getPhiN()) >= 0){
            Random random = new Random();
            bigInteger = new BigInteger(8, random);
            isRelativePrime = euclidean.getGcd(getPhiN(), bigInteger).equals(BigInteger.ONE) ;
        }
        setPk(bigInteger);

        return pk;
    }

    public BigInteger getSecretKey(){
        BigInteger secretKey = BigInteger.ONE;
        while (secretKey.compareTo(BigInteger.TWO) < 0 || secretKey.compareTo(getPhiN()) >= 0){
            setPk(getPublicKey());
            secretKey = extendedEuclidean.algorithm(getPhiN(), getPk())[1];
        }
        setSk(secretKey);
        System.out.println("Public key (e): "+getPk());
        System.out.println("Secret key (d): "+getSk());
        return getSk();
    }

    public BigInteger getP() {
        return p;
    }
    public void setP() {
        boolean isPrime = false;
        BigInteger bigInteger = null;
        while (!isPrime) {
            Random random = new Random();
            bigInteger = new BigInteger(30, random);
            //System.out.println("Biginteger value in setP: "+bigInteger);
            isPrime = millerRabin.algorithm(bigInteger);
        }
        this.p = bigInteger;
        finalAInsetP = millerRabin.a;

        System.out.println("p: "+this.p);
    }

    public BigInteger getQ() {
        return q;
    }
    public void setQ() {
        boolean isPrime = false;
        BigInteger bigInteger = null;
        while (!isPrime) {
            Random random = new Random();
            bigInteger = new BigInteger(30, random);
            isPrime = millerRabin.algorithm(bigInteger);
        }
        this.q = bigInteger;
        finalAInsetQ = millerRabin.a;
        System.out.println("q: "+this.q);
    }

    public BigInteger getSk() {
        return sk;
    }
    public void setSk(BigInteger sk) {
        this.sk = sk;
    }
    public BigInteger getPk() {
        return pk;
    }
    public void setPk(BigInteger pk) {
        this.pk = pk;
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