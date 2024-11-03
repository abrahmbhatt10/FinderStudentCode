import java.util.ArrayList;

/*
The below Hash class code was taken from:
https://www.geeksforgeeks.org/hash-table-data-structure/

I have modified it to fit our problem set.
 */
public class OldHash {
    // Number of buckets
    private final int bucket = 1000000;
    // Hash table of size bucket
    private final ArrayList<DataPair>[] table;
    private long p = 54321102419L;
    private int R = 256;
    private String invalidSTR;

    /*
        Default constructor that creates the empty hash table
     */
    public OldHash()
    {
        this.table = new ArrayList[bucket];
    }

    /*
        This is set to the finder class invalid key string.
        The constant string set in the finder class can be used in this class.
     */
    public String getInvalidSTR() {
        return invalidSTR;
    }

    // Initialized to the finder invalid string
    public void setInvalidSTR(String invalidSTR) {
        this.invalidSTR = invalidSTR;
    }

    /* Using the horner's hash method for strings
        Revise the return code to be % bucket size so
        the hash code is converted to the index for the hash table.
     */
    public int hashFunction(String key){
        long h = 0;
        int index = 0;
        for(int i = 0; i < key.length(); i++){
            h = (h * R + key.charAt(i)) % p;
        }
        /*
           Below code from the book Algorithms Sedgewick & Wayne, pg. 461.
        */
        index = (int) ((h & 0x7fffffff) % bucket);
        //System.out.println("Index: " + index + " Key " + key);
        return index;
    }

    // Inserts the data pair object with key values into the hash table.
    public void insertItem(String key, String value)
    {
        // get the hash index of key
        int index = hashFunction(key);

        if(table[index] == null){
            table[index] = new ArrayList<DataPair>();
        }
        // insert key into hash table at that index
        table[index].add(new DataPair(key, value));
    }

    // returns the value for a given string from the hashtable.
    public String getValue(String key){
        DataPair pPair;
        int index = hashFunction(key);
        if(table[index] == null){
            return getInvalidSTR();
        }
        for(int i = 0; i < table[index].size(); i++){
            pPair = table[index].get(i);
            if(key.equals(pPair.getKey())){
                return pPair.getVal();
            }
        }
        return getInvalidSTR();
    }

    // function to display hash table
    // Used for debugging
    public void displayHash()
    {
        for (int i = 0; i < bucket; i++) {
            System.out.print(i);
            for (DataPair x : table[i]) {
                System.out.print(" --> " + x.getKey() + ' ' + x.getVal());
            }
            System.out.println();
        }
    }
}
