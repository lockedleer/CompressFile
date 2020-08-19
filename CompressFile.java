import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Given a very large text file that may not fit in available memory,
 * create a file that contains the distinct words from the original
 * file sorted in the ascending order
 *
 * @author  Daniel Brennan
 * @version 1.0
 * @since   2020-08-17
 */
public class CompressFile {

    /**
     * This method will read the contents of the file that needs to be compressed.
     * This is done by grabbing each line within file as a str and then converting
     * that to a List. This also removes duplicates as new elements are read from
     * the file.
     * @param fileName path including name of file that needs to be compressed.
     * @return list of string elements.
     */
    public List<String> getFileContents(String fileName) {
        List<String> contents = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                contents.addAll(createList(str));
                contents = removeDuplicates(contents);
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found for parameter fileName: [ " + fileName + " ].");
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file: [ " + fileName + " ].");
        }
        return contents;
    }

    /**
     * This method creates a list of string elements that are split apart by whitespace and punctuation
     * (i.e period, comma, question mark, exclamation, etc.) from one line of text in the file.
     * Choose to keep values such as "Shouldn't" since t and Shouldn are not full word values.
     * @param str One line of text from file.
     * @return list of string elements.
     */
    public List<String> createList(String str) {
        return new ArrayList<>(Arrays.asList(str.split("[\\s.,!;?:/]+")));
    }

    /**
     * This method removes duplicates from the list of string elements, by converting the list
     * into a hashset value, thereby automatically removing duplicate values. Returns the harhset
     * parsed back into a list of string elements.
     * @param words list of string elements from file.
     * @return list of string elements.
     */
    public List<String> removeDuplicates(List<String> words) {
        HashSet<String> distinctWords = new HashSet<>(words);
        return new ArrayList<>(distinctWords);
    }

    /**
     * This method sorts the list of string elements by using the collections built in functionality.
     * @param words list of string elements from file.
     * @return list of string elements.
     */
    public List<String> sortAlphabetically(List<String> words) {
        Collections.sort(words);
        return words;
    }

    /**
     * This method takes the list of string elements from the original file and writes those to a
     * new file, will overwrite values into file if pre-existing.
     * @param words list of string elements from file.
     */
    public void outputToNewFile(List<String> words) {
        try {
            File newFile = new File("compressedText.txt");
            FileWriter writer = new FileWriter(newFile.getName());
            for (String word : words) {
                writer.write(word);
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while creating new file.");
        }
    }

    /**
     *  This is the main method which makes use CompressFile methods.
     * @param args optional use.
     */
    public static void main(String[] args) {
        CompressFile cf = new CompressFile();

        String fileName = "text.txt";
        if (args == null || args.length == 0) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL location = classLoader.getResource(fileName);
            fileName = location != null ? location.getPath().substring(1) : null;
        } else {
            fileName = args[0];
        }

        List<String> words;
        words = cf.getFileContents(fileName);
        Collections.sort(words);
        cf.outputToNewFile(words);
    }
}
