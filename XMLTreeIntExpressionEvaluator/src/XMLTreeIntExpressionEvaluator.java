import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Steven Masilonis
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        int initialValue = 0;
        int appliedValue = 0;
        int result = 0;
        if (exp.numberOfChildren() != 0) {
            /**
             * recursion is used until there are no more children left our first
             * value is set to the first child this works because the XML tree
             * is assumed to be valid input
             */
            initialValue = evaluate(exp.child(0));
            appliedValue = evaluate(exp.child(1));
            if (exp.label().equals("divide")) {
                result = initialValue / appliedValue;
            }
            if (exp.label().equals("times")) {
                result = initialValue * appliedValue;
            }
            if (exp.label().equals("minus")) {
                result = initialValue - appliedValue;
            }
            if (exp.label().equals("plus")) {
                result = initialValue + appliedValue;
            }
        } else { //if no children then set value to be returned
            result = Integer.parseInt(exp.attributeValue("value"));
        } //converts string to int
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
