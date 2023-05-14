package org.example.algorithms;

import org.example.additional.Pair;

import java.math.BigInteger;

public class DivideConquerOne {
    private static final BigInteger SMALL_ENOUGH = BigInteger.TEN;
    public static String multiply(String x, String y) {
        int n = Math.max(x.length(), y.length());
        if (n == 1) {
            return String.valueOf(Integer.parseInt(x) * Integer.parseInt(y));
        }
        int m = n / 2;
        String a = x.substring(0, x.length() - m);
        String b = x.substring(x.length() - m);
        String c = y.substring(0, y.length() - m);
        String d = y.substring(y.length() - m);
        String ac = multiply(a, c);
        String bd = multiply(b, d);
        String z = multiply(add(a, b), add(c, d));
        String middleTerm = subtract(subtract(z, ac), bd);
        return add(add(padRight(ac, 2 * m), padRight(middleTerm, m)), bd);
    }

    private static String add(String x, String y) {
        int n = Math.max(x.length(), y.length());
        x = padLeft(x, n);
        y = padLeft(y, n);
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = n - 1; i >= 0; i--) {
            int sum = Character.getNumericValue(x.charAt(i)) + Character.getNumericValue(y.charAt(i)) + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    private static String subtract(String x, String y) {
        int n = Math.max(x.length(), y.length());
        x = padLeft(x, n);
        y = padLeft(y, n);
        StringBuilder sb = new StringBuilder();
        int borrow = 0;
        for (int i = n - 1; i >= 0; i--) {
            int diff = Character.getNumericValue(x.charAt(i)) - Character.getNumericValue(y.charAt(i)) - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            sb.append(diff);
        }
        return sb.reverse().toString().replaceFirst("^0+(?!$)", "");
    }

    private static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s).replace(' ', '0');
    }

    /**
     * Divides a given number in half (if it has even length) and pads it with leading zeros if necessary.
     * @param num The number to divide.
     * @return A pair of BigIntegers, representing the two halves of the given number.
     */
    private static Pair<BigInteger, BigInteger> divideNumber(BigInteger num) {
        String numStr = num.toString();
        int len = numStr.length();
        int halfLen = len / 2;
        BigInteger firstHalf = new BigInteger(padLeft(numStr.substring(0, halfLen), len));
        BigInteger secondHalf = new BigInteger(padLeft(numStr.substring(halfLen), len));
        return new Pair<>(firstHalf, secondHalf);
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }


    /**
     * Multiplies two big integers using the divide and conquer algorithm.
     * @param x The first big integer.
     * @param y The second big integer.
     * @return The product of x and y.
     */
    public static BigInteger multiply(BigInteger x, BigInteger y) {
        // Base case: if the input is small enough, use the default multiplication algorithm
        if (x.bitLength() <= SMALL_ENOUGH || y.bitLength() <= SMALL_ENOUGH) {
            return x.multiply(y);
        }

        // Divide the input numbers into two halves
        Pair<BigInteger, BigInteger> xParts = divideNumber(x);
        Pair<BigInteger, BigInteger> yParts = divideNumber(y);
        BigInteger a = xParts.getLeft();
        BigInteger b = xParts.getRight();
        BigInteger c = yParts.getLeft();
        BigInteger d = yParts.getRight();

        // Recursively compute the products of the halves
        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);
        BigInteger abcd = multiply(a.add(b), c.add(d));

        // Combine the results
        BigInteger firstTerm = ac.shiftLeft(2 * (b.bitLength()));
        BigInteger secondTerm = abcd.subtract(ac).subtract(bd).shiftLeft(b.bitLength());
        BigInteger result = firstTerm.add(secondTerm).add(bd);

        return result;
    }


}
