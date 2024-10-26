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
        private final ArrayList<String>[] table;
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

        public void insertItem(String key)
        {
            // get the hash index of key
            int index = hashFunction(key);
            // insert key into hash table at that index
            table[index].add(key);
        }

        public void deleteItem(String key)
        {
            // get the hash index of key
            int index = hashFunction(key);

            // Check if key is in hash table
            if (!table[index].contains(key)) {
                return;
            }

            // delete the key from hash table
            table[index].remove(Integer.valueOf(key));
        }

        // function to display hash table
        public void displayHash()
        {
            for (int i = 0; i < bucket; i++) {
                System.out.print(i);
                for (int x : table[i]) {
                    System.out.print(" --> " + x);
                }
                System.out.println();
            }
        }
}
