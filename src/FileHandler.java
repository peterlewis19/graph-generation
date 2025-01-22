import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileHandler {
    public static void readFromFile(String fileName){
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
        ) {
            String line = br.readLine();


            while (line != null) {
                System.out.println(line);

                line = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String fileName, String text, boolean append) {
        // write text to fileName, overwriting (append = false) or appending (append = true)
        try (
                FileWriter fw = new FileWriter(fileName, append);
                PrintWriter pw = new PrintWriter(fw);
        ) {
            pw.println(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readToArrayList(String fileName){
        ArrayList<String> allLines = new ArrayList<>();

        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
        ) {
            String line = br.readLine();

            while (line != null) {
                allLines.add(line);

                line = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return allLines;
    }


    public static void writeFromArrayList(String fileName, ArrayList<String> allText, boolean append) {
        // write text to fileName, overwriting (append = false) or appending (append = true)
        try (
                FileWriter fw = new FileWriter(fileName, append);
                PrintWriter pw = new PrintWriter(fw);
        ) {
            for (String line: allText){
                pw.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFromList(String fileName, Node[] allNodes, boolean append) {
        // write text to fileName, overwriting (append = false) or appending (append = true)
        try (
                FileWriter fw = new FileWriter(fileName, append);
                PrintWriter pw = new PrintWriter(fw);
        ) {
            for (Node line: allNodes){
                pw.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendToFile(String fileName, String text) {
        // write text to fileName, overwriting (append = false) or appending (append = true)
        try (
                FileWriter fw = new FileWriter(fileName, true);
                PrintWriter pw = new PrintWriter(fw);
        ) {
            pw.println(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readNthLine(String fileName, int n){
        int count = 0;
        String line = "test";

        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
        ) {
            line = br.readLine();

            for (int i=1; i<n; i++) {
                line = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    public static void readEveryOther(String fileName){
        char character = 'a';

        try (RandomAccessFile rf = new RandomAccessFile("text.txt", "rws")){
            for (int i=0; i< 10; i=i+2) {
                rf.seek(i);
                character = (char) rf.read();
                System.out.print(character);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //System.out.println(character);
    }

    public static void replaceSpacewithX(String fileName){
        char character = 'a';

        try (RandomAccessFile rf = new RandomAccessFile("text.txt", "rws")){
            for (int i=0; i< 30; i++) {
                //move pointer to ith byte
                character = (char) rf.read(); //also +1 to count

                if (character==' '){
                    //moves pointer backwards to write
                    rf.seek(i);
                    rf.write((int)'X');
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void readInReverse(String fileName){
        char character = 'a';

        try (RandomAccessFile rf = new RandomAccessFile("text.txt", "rws")){
            for (int i=30; i>0; i--) {
                rf.seek(i);
                character = (char) rf.read(); //also +1 to count
                System.out.print(character);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void convertToFixedWidth(String fileName){
        int columnWidth = 10;
        ArrayList<String> allLines = readToArrayList(fileName);

        for (String line: allLines){
            String[] namesAsAList = line.split(",");

            for (String name: namesAsAList){
                name = padToWidth(name, columnWidth);
            }
        }
    }

    public static String padToWidth(String inputString, int columnWidth){
        int spacesToPlace = columnWidth-inputString.length();
        String padding = "";
        String outputString;

        for (int i=0; i< spacesToPlace; i++){
            padding = padding+" ";
        }

        outputString = inputString+padding;
        return outputString;
    }






}