package algorithms;

import java.math.BigInteger;

public class FastModularExponentiation {

    public BigInteger algorithm(BigInteger base, BigInteger exponent, BigInteger modulus) {

        BigInteger result = BigInteger.ONE;

        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            if (exponent.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                result = result.multiply(base).mod(modulus);
            }
            base = base.multiply(base).mod(modulus);
            exponent = exponent.divide(BigInteger.TWO);
        }

        return result;

    }
}