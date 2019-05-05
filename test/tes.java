package test;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class tes {
    public static void main( String args[] ) {

        int keySize = 512;
        SecureRandom random = new SecureRandom();
        // Choose two distinct prime numbers p and q.
        BigInteger p = BigInteger.probablePrime(keySize/2,random);
        BigInteger q = BigInteger.probablePrime(keySize/2,random);
        // Compute n = pq (modulus)
        BigInteger modulus = p.multiply(q);
        // Compute φ(n) = φ(p)φ(q) = (p − 1)(q − 1) = n - (p + q -1), where φ is Euler's totient function.
        // and choose an integer e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1; i.e., e and φ(n) are coprime.
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger publicExponent = getCoprime(m,random);
        // Determine d as d ≡ e−1 (mod φ(n)); i.e., d is the multiplicative inverse of e (modulo φ(n)).
        BigInteger privateExponent = publicExponent.modInverse(m);


        try {
            RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
            RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(modulus, privateExponent);

            KeyFactory factory = KeyFactory.getInstance("RSA");

            PublicKey pub = factory.generatePublic(spec);
            PrivateKey priv = factory.generatePrivate(privateSpec);

            System.out.println("Public Key : "+ byteArrayToHexString( pub.getEncoded() ));
            System.out.println("Private Key : "+ byteArrayToHexString( priv.getEncoded() ));
        }
        catch( Exception e ) {
            System.out.println(e.toString());
        }
    }

    public static BigInteger getCoprime(BigInteger m, SecureRandom random) {
        int length = m.bitLength()-1;
        BigInteger e = BigInteger.probablePrime(length,random);
        while (! (m.gcd(e)).equals(BigInteger.ONE) ) {
            e = BigInteger.probablePrime(length,random);
        }
        return e;
    }


    public static String byteArrayToHexString(byte[] bytes)
    {
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<bytes.length; i++)
        {
            if(((int)bytes[i] & 0xff) < 0x10)
                buffer.append("0");
            buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buffer.toString();
    }
}
