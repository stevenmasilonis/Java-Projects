import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Steven Masilonis
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        NaturalNumber initialValue = new NaturalNumber2(0);
        NaturalNumber appliedValue = new NaturalNumber2(0);
        NaturalNumber result = new NaturalNumber2(0);
        final NaturalNumber zero = new NaturalNumber2(0); //constant 0 as a NN
        /**
         * recursion is used until there are no more children left our first
         * value is set to the first child this works because the XML tree is
         * assumed to be valid input, copies are made to avoid logic errors due
         * to pointing
         */
        if (exp.numberOfChildren() != 0) {
            initialValue.copyFrom(evaluate(exp.child(0)));
            appliedValue.copyFrom(evaluate(exp.child(1)));
            if (exp.label().equals("divide")) {
                if (appliedValue.compareTo(zero) == 0) {
                    Reporter.fatalErrorToConsole("Divide by 0 error");
                }
                result.copyFrom(initialValue);
                result.divide(appliedValue);
            }
            if (exp.label().equals("times")) {
                result.copyFrom(initialValue);
                result.multiply(appliedValue);
            }
            if (exp.label().equals("minus")) {
                if (initialValue.compareTo(appliedValue) < 0) {
                    Reporter.fatalErrorToConsole("Negative value error");
                } //cannot have a negative value
                result.copyFrom(initialValue);
                result.subtract(appliedValue);
            }
            if (exp.label().equals("plus")) {
                result.copyFrom(initialValue);
                result.add(appliedValue);
            }
        } else {
            result.setFromString((exp.attributeValue("value")));
        } //converts string to NN
        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
