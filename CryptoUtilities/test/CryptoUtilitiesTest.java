import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Steven Masilonis
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }
    /*
     * Boundary test
     */

    @Test
    public void testReduceToGCD_9124152_4353453() {
        NaturalNumber n = new NaturalNumber2(9124152);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(4353453);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Additional boundary test
     */
    @Test
    public void testReduceToGCD_1000000_240() {
        NaturalNumber n = new NaturalNumber2(1000000);
        NaturalNumber nExpected = new NaturalNumber2(80);
        NaturalNumber m = new NaturalNumber2(240);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Boundary test
     */
    @Test
    public void testIsEven_1808787() {
        NaturalNumber n = new NaturalNumber2(1808787);
        NaturalNumber nExpected = new NaturalNumber2(1808787);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * second boundary test
     */
    @Test
    public void testIsEven_263473472() {
        NaturalNumber n = new NaturalNumber2(263473472);
        NaturalNumber nExpected = new NaturalNumber2(263473472);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }
    /*
     * Boundary test
     */

    @Test
    public void testPowerMod_17_0_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }
    /*
     * Additional boundary test
     */

    @Test
    public void testPowerMod_0_18_19() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }
    /*
     * Additional boundary test
     */

    @Test
    public void testPowerMod_3462623_25634634_523626() {
        NaturalNumber n = new NaturalNumber2();
        n.setFromString("3462623");
        NaturalNumber nExpected = new NaturalNumber2();
        n.setFromString("141385");
        NaturalNumber p = new NaturalNumber2();
        p.setFromString("25634634");
        NaturalNumber pExpected = new NaturalNumber2();
        pExpected.setFromString("25634634");
        NaturalNumber m = new NaturalNumber2();
        m.setFromString("523626");
        NaturalNumber mExpected = new NaturalNumber2();
        mExpected.setFromString("523626");
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * tests isWitnessToCompositeness
     */

    @Test
    public void testWitness_5_80() {
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber wExpected = new NaturalNumber2(5);
        NaturalNumber n = new NaturalNumber2(80);
        NaturalNumber nExpected = new NaturalNumber2(80);
        Boolean check = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(true, check);
    }

    /*
     * test for false case
     */

    @Test
    public void testWitness_3_91() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(91);
        NaturalNumber nExpected = new NaturalNumber2(91);
        Boolean check = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(false, check);
    }

    /*
     * Tests isPrime2
     */

    @Test
    public void isPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean choice = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, choice);
    }

    /*
     * Boundary test
     */

    @Test
    public void isPrime2_111143() {
        NaturalNumber n = new NaturalNumber2(111143);
        NaturalNumber nExpected = new NaturalNumber2(111143);
        boolean choice = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, choice);
    }

    /*
     * Test for false
     */

    @Test
    public void isPrime2_60() {
        NaturalNumber n = new NaturalNumber2(60);
        NaturalNumber nExpected = new NaturalNumber2(60);
        boolean choice = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, choice);
    }

    /*
     * NextLikelyPrime tests
     */

    @Test
    public void testNextLikelyPrime_13() {
        NaturalNumber n = new NaturalNumber2(13);
        NaturalNumber nExpected = new NaturalNumber2(17);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    /*
     * Boundary test
     */

    @Test
    public void testNextLikelyPrime_108887() {
        NaturalNumber n = new NaturalNumber2(108887);
        NaturalNumber nExpected = new NaturalNumber2(108893);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}
