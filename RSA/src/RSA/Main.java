package RSA;

import algorithms.*;

import java.math.BigInteger;


public class Main {
    public static void main(String[] args) {
       // int gcd = new Euclidean().getGcd(200,0);
       // System.out.println(gcd);
       BigInteger x = new ExtendedEuclidean().getX(BigInteger.valueOf(221), BigInteger.valueOf(45));
       //System.out.println("X: "+x);
       BigInteger y = new ExtendedEuclidean().getY(BigInteger.valueOf(221), BigInteger.valueOf(45));
       //System.out.println("Y: "+y);

        var modular = new FastModularExponentiation();
        var isPrime = new MillerRabin();
        System.out.println(isPrime.algorithm(BigInteger.valueOf(65)));


        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541};

        /*for (var prime: primes) {
            BigInteger value = BigInteger.valueOf(prime);

            System.out.println("Number: "+value+" "+isPrime.algorithm(value));
        }*/

        //System.out.println("P: "+isPrime.algorithm(BigInteger.valueOf(105700787)));
        //System.out.println("Q: "+isPrime.algorithm(BigInteger.valueOf(124796783)));


        //var test = new ChineseRemainderTheorem().algorithm(BigInteger.valueOf(12), BigInteger.valueOf(11),BigInteger.valueOf(3),BigInteger.valueOf(7));
        //System.out.println(test);

        var rsaKeyGeerator = new RsaKeyGenerator();
        var publicKey= rsaKeyGeerator.getPublicKey();
        var privateKey = rsaKeyGeerator.getPrivateKey();
        var encryption = rsaKeyGeerator.encryption(BigInteger.valueOf(20));
        var decryption = rsaKeyGeerator.decryption();

    /*    System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();*/

       /* System.out.println(modular.algorithm(BigInteger.valueOf(23), BigInteger.valueOf(209),BigInteger.valueOf(211)));
        System.out.println(modular.algorithm(BigInteger.valueOf(2),BigInteger.valueOf(340),BigInteger.valueOf(341)));
        System.out.println(modular.algorithm(BigInteger.valueOf(3),BigInteger.valueOf(340),BigInteger.valueOf(341)));
        System.out.println(modular.algorithm(BigInteger.valueOf(129),BigInteger.valueOf(97),BigInteger.valueOf(171)));
        System.out.println(modular.algorithm(BigInteger.valueOf(9),BigInteger.valueOf(22),BigInteger.valueOf(79)));
        System.out.println(modular.algorithm(BigInteger.valueOf(8),BigInteger.valueOf(89),BigInteger.valueOf(23)));
        System.out.println(modular.algorithm(BigInteger.valueOf(7),BigInteger.valueOf(180),BigInteger.valueOf(181)));
        System.out.println(modular.algorithm(BigInteger.valueOf(5),BigInteger.valueOf(126),BigInteger.valueOf(127)));
        System.out.println(modular.algorithm(BigInteger.valueOf(8),BigInteger.valueOf(89),BigInteger.valueOf(23)));
        System.out.println(modular.algorithm(BigInteger.valueOf(12),BigInteger.valueOf(99),BigInteger.valueOf(197)));
        System.out.println(modular.algorithm(BigInteger.valueOf(7),BigInteger.valueOf(99),BigInteger.valueOf(197)));
        System.out.println(modular.algorithm(BigInteger.valueOf(11),BigInteger.valueOf(121),BigInteger.valueOf(243)));
        System.out.println(modular.algorithm(BigInteger.valueOf(15),BigInteger.valueOf(121),BigInteger.valueOf(243)));*/

    }
}