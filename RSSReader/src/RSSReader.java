import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Steven Masilonis
 *
 */
public final class RSSReader {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSReader() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";
        //used for the html page through this code
        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        int i = getChildElement(channel, "title"); //saving child elements
        int k = getChildElement(channel, "description");
        int j = getChildElement(channel, "link");
        String title, description; //holds leaf values
        int count = channel.numberOfChildren(); //for loop later on
        if (channel.child(i).numberOfChildren() > 0) { //checks for children
            title = channel.child(i).child(0).toString();
        } else {
            title = "Empty Title"; //no children no title
        }
        out.print(title);
        out.print("</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>"); //always going to have a link
        out.print("<a href=\"" + channel.child(j).child(0) + "\">");
        out.print(title);
        out.print("</a>");
        out.print("</h1>");
        out.println("<p>");
        if (channel.child(k).numberOfChildren() > 0) { //same logic as prior if
            description = (channel.child(k).child(0)).toString();
        } else {
            description = "No description";
        }
        out.print(description);
        out.print("</p>");
        out.println("<table border=\"1\">"); //begins our table
        out.println("<tr>");
        out.println("<th>Date</th>");
        out.println("<th>Source</th>");
        out.println("<th>News</th>");
        out.println("</tr>");

        for (int q = 0; q < count; q++) { //runs through every item

            if (channel.child(q).label().equals("item")) {

                processItem(channel.child(q), out); //used for table
            }
        }
        outputFooter(out); //finishes the html code

        /*
         * TODO: fill in body
         */
    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        out.println("</table>"); //finishes our html code
        out.println("</body>");
        out.println("</html>");
        /*
         * TODO: fill in body
         */
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";
        int k = xml.numberOfChildren();
        int index = -1; //default value, means doesn't exist(yet)
        for (int i = 0; i < k; i++) {
            if (xml.child(i).label().equals(tag)) { //equals our desired tag
                index = i; //saves and later returns index
                i = k; //cuts our loop
            }
        }
        return index;
        /*
         * TODO: fill in body
         */
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";
        out.println("<tr>");
        out.print("<td>"); //for table rows
        int i = getChildElement(item, "title"); //saves index of child
        int k = getChildElement(item, "description"); //element
        int j = getChildElement(item, "pubDate");
        int l = getChildElement(item, "link");
        int m = getChildElement(item, "source");
        if (j != -1) { //if tag exists
            if (item.child(j).numberOfChildren() > 0) {
                out.print(item.child(j).child(0)); //if tag has child
            } else {
                out.print("No date available");
            }
        } else {
            out.print("No date available");
        }
        out.println("</td>");
        out.println("<td>");
        if (m != -1) { //same logic as prior if structure
            //stores our link then names it
            out.print(
                    "<a href=\"" + item.child(m).attributeValue("url") + "\">");
            if (item.child(m).numberOfChildren() > 0) {
                out.print(item.child(m).child(0) + "</a>");
            } else {
                out.print("No source available");
                out.print("</a>"); //no name, but has link
            }
        } else {
            out.print("No source available");
        }
        out.println("</td>");
        out.println("<td>");

        if (l != -1) { //link tag may not exist
            out.print("<a href=\"" + item.child(l).child(0) + "\">");
        } //if tag exists, will have link though
        if (i != -1) { //if title tag exist
            if (item.child(i).numberOfChildren() > 0) {
                out.print(item.child(i).child(0)); //if title tag has children
            } else if (k != -1) { //if no title child then we need to try desc
                if (item.child(k).numberOfChildren() > 0) {
                    out.print(item.child(k).child(0)); //if we have descr then
                } //then we are good
            }
        } else if (k != -1) { //if none of the above check for descr tag
            if (item.child(k).numberOfChildren() > 0) {
                out.print(item.child(k).child(0)); //check for children
            }
        } else { //if we still have nothing at all, we use this
            out.print("No title available");
        }
        if (l != -1) { //we don't need this if there is no link tag
            out.print("</a>");
        }
        out.println("</td>");
        out.println("</tr>"); //ends table
        /*
         * TODO: fill in body
         */
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * TODO: fill in body
         */
        out.print("Input URL");
        String url = in.nextLine();
        out.println("Name the output file (include .html)");
        String filename = in.nextLine();
        XMLTree feed = new XMLTree1(url); //puts link in xml tree
        SimpleWriter filenameOut = new SimpleWriter1L(filename);
        if ((feed.label().equals("rss")) //check for valid rss and version
                && (feed.attributeValue("version").equals("2.0"))) {
            outputHeader(feed.child(0), filenameOut);

        } else {
            out.println("This url is not an RSS 2.0 feed");
        }
        in.close();
        out.close();
    }

}