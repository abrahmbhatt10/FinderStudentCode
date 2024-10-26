import java.io.BufferedReader;
import java.io.IOException;
/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [Agastya]
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";
    public Hash mTable;
    public Finder() {}

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        if(br == null){
            return;
        }
        /*
        I modified a bit of the code from:
        while loop:
        https://www.geeksforgeeks.org/bufferedreader-readline-method-in-java-with-examples/

         string split:
         https://www.geeksforgeeks.org/split-string-java-examples/
         */
        String dataSTR;
        mTable = new Hash();
        while (br.ready()){
            dataSTR = br.readLine();
            String[] arrOfStr = dataSTR.split(",");
            if((keyCol < arrOfStr.length) && (valCol < arrOfStr.length)){
                mTable.insertItem(arrOfStr[keyCol], arrOfStr[valCol]);
            }
        }
        br.close();
    }

    public String query(String key){
        if(mTable != null){
            if(mTable.getValue(key) != null){
                return mTable.getValue(key);
            }
        }
        return INVALID;
    }
}