package Rsa;

import algorithms.ChineseRemainderTheorem;
import algorithms.FastModularExponentiation;
import java.math.BigInteger;

public class RsaSignature {
    private BigInteger m;
    private BigInteger signedMessage;
    private boolean isSignatureRight;
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger pk;
    private BigInteger sk;
    private ChineseRemainderTheorem chineseRemainderTheorem = new ChineseRemainderTheorem();
    private FastModularExponentiation fastModularExponentiation = new FastModularExponentiation();


    public BigInteger signature(BigInteger m) {
        setM(m);
        setSignedMessage(chineseRemainderTheorem.algorithm(getM(), getQ(), getP(), getSk()));
        return getSignedMessage();
    }

    public boolean isSignatureRight(){
        setSignatureRight(fastModularExponentiation.algorithm(getSignedMessage(), getPk(), getN()).equals(getM()));
        System.out.println("Original message is: "+fastModularExponentiation.algorithm(getSignedMessage(), getPk(), getN()));
        return getSignatureRight();
    }

    public BigInteger getSignedMessage() { return signedMessage; }
    public void setSignedMessage(BigInteger signedMessage) {
        this.signedMessage = signedMessage;
        System.out.println("Signed message is: "+getSignedMessage());
    }
    public boolean getSignatureRight() { return isSignatureRight; }
    public void setSignatureRight(boolean signatureRight) {
        isSignatureRight = signatureRight;
    }

    public BigInteger getM() { return m; }
    public void setM(BigInteger m) {
        this.m = m;
        System.out.println("Message to sign: "+getM());
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

}
