package org.example.algorithms;

import java.math.BigInteger;

public class DivideConquerTwo {
    private static final BigInteger TWO = BigInteger.valueOf(2);

    public static BigInteger divideConquerTwo(BigInteger num1, BigInteger num2) {
        int n = Math.max(num1.bitLength(), num2.bitLength());
        if (n <= 2000) { // Base case for small enough numbers
            return num1.multiply(num2);
        }

        n = (n / 2) + (n % 2); // Round up to nearest even number
        BigInteger b = num1.shiftRight(n);
        BigInteger a = num1.subtract(b.shiftLeft(n));
        BigInteger d = num2.shiftRight(n);
        BigInteger c = num2.subtract(d.shiftLeft(n));

        BigInteger ac = divideConquerTwo(a, c);
        BigInteger bd = divideConquerTwo(b, d);
        BigInteger abcd = divideConquerTwo(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(n)).add(bd.shiftLeft(n*2));
    }
}
