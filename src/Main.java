import java.io.*;

public class Main {
    // We don't support more than this number of fields per line...
    public static final int MAX_FIELDS = 50;

    public static void main(String[] args) {
        try {
            int linesRead = cut("data/in1.txt", "data/out.txt", "2", ',');
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
        return 0;
    }
}
