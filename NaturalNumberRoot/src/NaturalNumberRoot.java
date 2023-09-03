import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Steven Masilonis
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";
        NaturalNumber copy = new NaturalNumber2(n); //saves our desired answer
        NaturalNumber lowBound = new NaturalNumber2(0); //lowest value starts 0
        NaturalNumber highBound = new NaturalNumber2(copy); //highest possible
        NaturalNumber midWay = new NaturalNumber2(); //our guess value
        final NaturalNumber one = new NaturalNumber2(1); //1 as a NN
        NaturalNumber difference = new NaturalNumber2(); //holds high - low
        NaturalNumber midWayRooted = new NaturalNumber2(); //holds tested value
        findDifference(highBound, lowBound, difference); //finds difference
        findMiddle(highBound, lowBound, midWay, r); //finds midpoint of bounds
        midWayRooted.copyFrom(midWay); //copy used to test guess
        midWayRooted.power(r); //tests guess
        /** while bounds are further than 1 apart */
        while (difference.compareTo(one) > 0) {
            if (midWayRooted.compareTo(copy) > 0) { //if guess is too high
                highBound.copyFrom(midWay); //set guess as new highbound
            } else { //if guess is too low
                lowBound.copyFrom(midWay); //set guess as new lowbound
            }
            /** lines 42-45 algorithm used again to test new guess */
            findDifference(highBound, lowBound, difference);
            findMiddle(highBound, lowBound, midWay, r);
            midWayRooted.copyFrom(midWay);
            midWayRooted.power(r);
        }
        /** if n is initially 1, answer is always 1 no rounding down needed */
        if (copy.compareTo(one) == 0) {
            n.copyFrom(one);
        } else {
            n.copyFrom(lowBound); //want to round down even if answer is .999
        }
    }

    /**
     * subtracts our bounds, set them to a copy and resets our object.
     *
     * @param highBound
     * @param lowBound
     * @param difference
     */
    public static void findDifference(NaturalNumber highBound,
            NaturalNumber lowBound, NaturalNumber difference) {
        highBound.subtract(lowBound); //finds difference
        difference.copyFrom(highBound); //copies to new variable
        highBound.add(lowBound); //we need to reset it
    }

    /**
     * finds the middle value between bounds using a copy then sets our midWay
     * value to it the object.
     *
     * @param highBound
     * @param lowBound
     * @param midWay
     * @param r
     */
    public static void findMiddle(NaturalNumber highBound,
            NaturalNumber lowBound, NaturalNumber midWay, int r) {
        final NaturalNumber two = new NaturalNumber2(2); //2 as a NN
        /** simpler than having to reset it */
        NaturalNumber copyOfHigh = new NaturalNumber2(highBound);
        copyOfHigh.add(lowBound); //finds average then rounds down
        copyOfHigh.divide(two);
        midWay.copyFrom(copyOfHigh);
        /** midWay never has to be reset because of the copy */
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
