package Rsa;

import algorithms.ChineseRemainderTheorem;
import algorithms.FastModularExponentiation;

import java.math.BigInteger;

public class Rsa {

    private BigInteger encryption;
    private BigInteger decryption;
    private FastModularExponentiation fastModularExponentiation = new FastModularExponentiation();
    private ChineseRemainderTheorem chineseRemainderTheorem = new ChineseRemainderTheorem();
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger pk;
    private BigInteger sk;

    public BigInteger encryption(BigInteger m){
        setEncryption(fastModularExponentiation.algorithm(m, getPk(), getN()));
        System.out.println("Encryptioned message: "+getEncryption());
        return getEncryption();
    }

    public BigInteger decryption(){
        setDecryption(chineseRemainderTheorem.algorithm(getEncryption(), getQ(), getP(), getSk()));
        System.out.println("Decryptioned message: "+getDecryption());
        return getDecryption();
    }

    public BigInteger getP() { return p; }
    public void setP(BigInteger p) { this.p = p; }
    public BigInteger getQ() { return q; }
    public void setQ(BigInteger q) { this.q = q; }
    public BigInteger getN() { return n; }
    public void setN(BigInteger n) { this.n = n; }
    public BigInteger getPk() { return pk; }
    public void setPk(BigInteger pk) { this.pk = pk; }
    public BigInteger getSk() { return sk; }
    public void setSk(BigInteger sk) { this.sk = sk; }
    public BigInteger getEncryption() {
        return encryption;
    }
    public void setEncryption(BigInteger encryption) {
        this.encryption = encryption;
    }
    public BigInteger getDecryption() {
        return decryption;
    }
    public void setDecryption(BigInteger decryption) {
        this.decryption = decryption;
    }

}