import java.util.ArrayList;
/*
The below Hash class code was taken from:
https://www.geeksforgeeks.org/hash-table-data-structure/

I have modified it to fit our problem set.
 */
public class Hash {
        // Number of buckets
        private final int bucket = 1000000;
        // Hash table of size bucket
        private final ArrayList<DataPair>[] table;
        private long p = 54321102419L;
        private int R = 256;

        public Hash()
        {
            this.table = new ArrayList[bucket];
        }

        // Converts to hash
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
            return index;
        }

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

        public String getValue(String key){
            DataPair pPair;
            int index = hashFunction(key);
            if(table[index] == null){
                return null;
            }
            for(int i = 0; i < table[index].size(); i++){
                pPair = table[index].get(i);
                if(pPair.getKey() == key){
                    return pPair.getVal();
                }
            }
            return null;
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
