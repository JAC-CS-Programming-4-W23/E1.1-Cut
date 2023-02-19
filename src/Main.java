import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // We don't support more than this number of fields per line...
    public static final int MAX_FIELDS = 50;

    public static void main(String[] args) {
        try {
            int linesRead = cut("data/in1.txt", "data/out.txt", "2,3,1", ',');
            System.out.printf("Read %d lines.", linesRead);
        }
        catch (IOException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Extract specified fields from the input data structured in columns by a delimiter.
     * @param inputFileName The file to read the data from, if blank then read from standard input.
     * @param outputFileName The file to write the data to, if blank then write to standard output.
     * @param format Determines the format of the output file. Comma delimited numbers and/or number
     *               range corresponding to the columns in the input data. Ex: "1,3,2" would output
     *               columns 1 and 3 and 2 in the output (comma delimited). You can specify ranges "1-3"
     *               or open-ended ranges "-5" or "2-". Columns can be repeated in the output.
     * @param delimiter The character delimiting the columns.
     * @return The number of lines read.
     * @throws IOException If there is a problem reading or writing the files.
     */
    public static int cut(String inputFileName, String outputFileName, String format, char delimiter) throws IOException {

        int lineNumber = 0;

        Scanner scanner;
        // Optional: read from STDIN if no sile specified.
        if (inputFileName == null || inputFileName.isBlank())
            scanner = new Scanner(System.in);
        else
            scanner = new Scanner(new FileReader(inputFileName));

        scanner.useDelimiter(String.valueOf(delimiter));

        PrintWriter writer;
        // Optional: write to STDOUT if no sile specified.
        if (outputFileName == null || outputFileName.isBlank())
            writer = new PrintWriter(System.out);
        else
            writer = new PrintWriter(new FileWriter(outputFileName));

        // Extract format fields from the input.
        String[] formatFields = format.split(",");
        Range[] ranges = new Range[formatFields.length];

        for (int i = 0; i < formatFields.length; i++) {
            // Process either column number ranges or single column numbers.
            if (formatFields[i].contains("-")) {
                String[] bounds = formatFields[i].split("-");
                int low = Integer.parseInt(bounds[0]);
                int high = Integer.parseInt(bounds[1]);
                ranges[i] = new Range(low, high);
            }
            else {
                int value = Integer.parseInt(formatFields[i]);
                ranges[i] = new Range(value, value);
            }
        }

        String[] fields = new String[MAX_FIELDS];
        int current = 0;

        while (scanner.hasNext()) {
            String token = scanner.next();

            // Since the delimiter is ',' we might have two tokens separated by a newline.
            // If so, print the lines and start the next one!
            int posNewline = token.indexOf('\n');

            if (posNewline >= 0) {
                String next = token.substring(posNewline + 1);
                token = token.substring(0, posNewline);
                fields[current++] = token;

                // Output line according to format.
                outputFields(fields, current, ranges, writer);

                Arrays.fill(fields, 0, current-1, "");
                fields[0] = next;
                current = 1;

                lineNumber++;
            }
            else {
                fields[current++] = token;
            }
        }

        // Output last line if exists.
        if(current > 1) { // TODO: possibility of single column input...
            outputFields(fields, current, ranges, writer);
        }

        scanner.close();
        writer.close();

        return lineNumber;
    }

    /**
     * Output the fields given by the set ranges.
     * @param fields The fields for the current input line.
     * @param lastPos The number of fields in the line.
     * @param ranges The ranges to output.
     * @param out The output stream.
     */
    private static void outputFields(String[] fields, int lastPos, Range[] ranges, PrintWriter out) {
        // Print line according to format.
        boolean first = true;

        for (Range r : ranges) {
            for (int j = r.getLow(); j <= r.getHigh(); j++) {
                if (!first) {
                    out.print(",");
                }

                first = false;

                if (j > lastPos)
                    throw new IllegalStateException("Invalid column number: " + j);

                out.print(fields[j - 1]);
            }
        }

        out.println();
    }
}
