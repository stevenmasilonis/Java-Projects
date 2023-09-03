import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Junit test cases.
 *
 * @author stevenmasilonis.
 *
 */
public class StringReassemblyTest {
    /**
     * constants to avoid magic numbers.
     */
    public static final int THREE = 3;
    /**
     * constants to avoid magic numbers.
     */
    public static final int FOUR = 4;
    /**
     * constants to avoid magic numbers.
     */
    public static final int FIVE = 5;
    /**
     * constants to avoid magic numbers.
     */
    public static final int SIX = 6;

    /**
     * routine case.
     */
    @Test
    public void testCombination() {
        String firstString = "toofast";
        String secondString = "fastforyou";
        int overlap = FOUR;
        String result = StringReassembly.combination(firstString, secondString,
                overlap);
        assertEquals(result, "toofastforyou");

    }

    /**
     * boundary case.
     */
    @Test
    public void testCombination2() {
        String firstString = "fall";
        String secondString = "len";
        int overlap = 1;
        String result = StringReassembly.combination(firstString, secondString,
                overlap);
        assertEquals(result, "fallen");

    }

    /**
     * boundary case.
     */
    @Test
    public void testCombination3() {
        String firstString = "Rainbow";
        String secondString = "ainbows";
        int overlap = SIX;
        String result = StringReassembly.combination(firstString, secondString,
                overlap);
        assertEquals(result, "Rainbows");

    }

    /**
     * challenge.
     */
    @Test
    public void testCombination4() {
        String firstString = "Rainbow six";
        String secondString = "six siege";
        int overlap = THREE;
        String result = StringReassembly.combination(firstString, secondString,
                overlap);
        assertEquals(result, "Rainbow six siege");

    }

    /**
     * Boundary case, substring in set.
     */
    @Test
    public void testAddToSet() {
        String firstString = "Rainbow";
        String secondString = "Rainbow six siege";
        Set<String> test = new Set2<String>();
        test.add(firstString);
        Set<String> testResult = new Set2<String>();
        testResult.add(secondString);
        StringReassembly.addToSetAvoidingSubstrings(test, secondString);
        assertEquals(test, testResult);
    }

    /**
     * Routine case no substrings.
     */
    @Test
    public void testAddToSet2() {
        String firstString = "steve";
        String secondString = "random";
        Set<String> test = new Set2<String>();
        test.add(firstString);
        Set<String> testResult = new Set2<String>();
        testResult.add(secondString);
        testResult.add(firstString);
        StringReassembly.addToSetAvoidingSubstrings(test, secondString);
        assertEquals(test, testResult);
    }

    /**
     * Boundary case string adds and there are substrings.
     */
    @Test
    public void testAddToSet3() {
        String firstString = "Rainbow";
        String secondString = "Rain";
        Set<String> test = new Set2<String>();
        test.add(firstString);
        Set<String> testResult = new Set2<String>();
        testResult.add(firstString);
        StringReassembly.addToSetAvoidingSubstrings(test, secondString);
        assertEquals(test, testResult);
    }

    /**
     * challenge.
     */
    @Test
    public void testAddToSet4() {
        Set<String> test = new Set2<String>();
        test.add("RANDOM");
        test.add("ANOTHER");
        test.add("THERE");
        test.add("HOUSE");
        test.add("THREES");
        test.add("BIGHOUSE");
        Set<String> testResult = new Set2<String>();
        testResult.add("RANDOM");
        testResult.add("ANOTHER");
        testResult.add("THERE");
        testResult.add("THREES");
        testResult.add("BIGHOUSE");
        StringReassembly.addToSetAvoidingSubstrings(test, "BIGHOUSE");
        assertEquals(test, testResult);
    }

    /**
     * Routine case.
     */
    @Test
    public void testPrint() {
        String cases = "Rainbow~Six";
        SimpleWriter out = new SimpleWriter1L("data/test1method4.txt");
        StringReassembly.printWithLineSeparators(cases, out);
        SimpleReader in = new SimpleReader1L("data/test1method4.txt");
        String[] test = new String[2];
        String[] test2 = new String[2];
        test2[0] = "Rainbow";
        test2[1] = "Six";
        int i = 0;
        while (!in.atEOS()) {
            test[i] = in.nextLine();
            i++;
        }
        assertEquals(test2[0], test[0]);
        assertEquals(test2[1], test[1]);
        in.close();
        out.close();
    }

    /**
     * Routine case.
     */
    @Test
    public void testprintWithLine() {
        SimpleWriter out = new SimpleWriter1L("data/test1method4.txt");
        String firstString = "Rainbow Six";
        String secondString = "Rainbow " + "Six";
        out.print(secondString);
        assertEquals(firstString, secondString);
        out.close();
    }
}
