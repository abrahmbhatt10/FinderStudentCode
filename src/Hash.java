import java.util.ArrayList;
/*
The below Hash class code was taken from:
https://www.geeksforgeeks.org/hash-table-data-structure/

I have modified it to fit our problem set.
 */
public class Hash {
    // Instance Variables
    private int DEFAULT_TABLE_SIZE = 5;
    private int tableSize;
    // Number of occupied entries
    private int n;
    private String[] keys;
    private String[] values;
    private long p = 54321102419L;
    private int R = 256;
    private String invalidSTR;

    /*
        Default constructor that creates the empty hash table
     */
    public Hash()
    {
        tableSize = DEFAULT_TABLE_SIZE;
        keys = new String[tableSize];
        values = new String[tableSize];
        n = 0;
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

    /*
        Double the size of the keys and values array.
     */
    public void resize(){
        /*
            Save the existing keys and values in oldKeys and oldValues;
         */
        String[] oldKeys = keys;
        String[] oldValues = values;
        /*
            Create new arrays with double the size of the initial key and value arrays.
         */
        keys = new String[tableSize * 2];
        values = new String[tableSize * 2];
        // Updates value of table size based on this new doubled size.
        tableSize *= 2;
        n = 0;
        /*
            Copy the old values into this new array.
         */
        for(int i = 0; i < oldKeys.length; i++){
            if(oldKeys[i] != null){
                add(oldKeys[i], oldValues[i]);
            }
        }
    }


    /* Using the horner's hash method for strings
        Revise the return code to be % bucket size so
        the hash code is converted to the index for the hash table.
     */
    public int hash(String key){
        long h = 0;
        int index = 0;
        for(int i = 0; i < key.length(); i++){
            h = (h * R + key.charAt(i)) % p;
        }
        /*
           Below code from the book Algorithms Sedgewick & Wayne, pg. 461.
        */
        index = (int) (h % tableSize);
        return index;
    }

    // Inserts the data pair object with key values into the hash table.
    /*
        I used some help from Algorithms: 4th Edition by Robert Sedgewick and Kevin Wayne
     */
    public void add(String key, String value)
    {
        // Checks if the table is at least 1/2 full
        if(n >= (tableSize / 2)){
            resize();
        }
        /*
            Saves the value of the first empty space after linear probing.
         */
        int i;
        /*
            Skip through the values till you find a null.
            If there is a duplicate match for key, then update the value.
         */
        for(i = hash(key); keys[i] != null; i = ((i + 1) % tableSize)){
            /*
                This if statement will be true for duplicate key matches.
             */
            if(keys[i].equals(key)){
                values[i] = value;
                return;
            }
        }
        /*
            Insert the new key values at location i.
         */
        keys[i] = key;
        values[i] = value;
        n++;
    }

    // returns the value for a given string from the hashtable.
    /*
        I used some help from Algorithms: 4th Edition by Robert Sedgewick and Kevin Wayne
     */
    public String get(String key){
        /*
            Compare the key value to find the correct position.
         */
        for(int i = hash(key); keys[i] != null; i = (i + 1) % tableSize){
            if(keys[i].equals(key)){
                return values[i];
            }
        }
        return getInvalidSTR();
    }
}
