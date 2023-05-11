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
    private BigInteger a;
    private boolean isPrime = true;
    private final FastModularExponentiation modular = new FastModularExponentiation();

    public boolean algorithm(BigInteger testingNumber){
        this.n = testingNumber;

        if((n.compareTo(BigInteger.ONE) < 0)  || n.compareTo(BigInteger.ONE) == 0){
            return false;
        } else if (Objects.equals(n, (BigInteger.TWO))) {
            return true;
        } else if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        setDAndS();

        for(var i = BigInteger.ZERO; i.compareTo(numberOfTests) < 0; i = i.add(BigInteger.ONE)) {

            a = generateRandomBigInteger(BigInteger.TWO, n.subtract(BigInteger.TWO));
            //a=BigInteger.valueOf(13);
            System.out.println("A: "+a+ "   N: "+n);

            boolean isComposite = isComposite();
            if(isComposite){
                isPrime = false;
            } else {
                isPrime = true;
                break;
            }
        }
        return isPrime;
    }

    private void setDAndS(){
        BigInteger quotient;
        BigInteger remainder;
        d = n.subtract(BigInteger.ONE);
        while (true){
            quotient = d.divide(BigInteger.TWO);
            remainder = d.mod(BigInteger.TWO);
            if(remainder.equals(BigInteger.ONE)){
                break;
            }
            s = s.add(BigInteger.ONE);
            d = quotient;
        }
        //System.out.println(s);
        //System.out.println(d);
    }

    private boolean isComposite(){

        if(Objects.equals(modular.algorithm(a, d, n), BigInteger.ONE)){
            return false;
        }

        var d = this.d;

        for(var i = BigInteger.ZERO; i.compareTo(s.subtract(BigInteger.ONE)) < 0 || i.compareTo(s.subtract(BigInteger.ONE)) == 0; i = i.add(BigInteger.ONE)){

            if(Objects.equals(modular.algorithm(a, d, n), n.subtract(BigInteger.ONE))){
                return false;
            }

            a = modular.algorithm(a, d, n);
            d = BigInteger.TWO;
        }

        //d = this.d;

        return true;
    }


    private BigInteger generateRandomBigInteger(BigInteger minVal, BigInteger maxVal) {
        BigInteger result;

        do {
            result = new BigInteger(maxVal.subtract(minVal).bitLength(), new SecureRandom())
                    .add(minVal);
        } while (result.compareTo(maxVal) > 0);

        return result;
    }
}
