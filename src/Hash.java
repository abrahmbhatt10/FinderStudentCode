import java.util.ArrayList;
/*
The below Hash class code was taken from:
https://www.geeksforgeeks.org/hash-table-data-structure/
 */
public class Hash {
        // Number of buckets
        private final int bucket;
        // Hash table of size bucket
        private final ArrayList<Integer>[] table;

        public Hash(int bucket)
        {
            this.bucket = bucket;
            this.table = new ArrayList[bucket];
            for (int i = 0; i < bucket; i++) {
                table[i] = new ArrayList<>();
            }
        }

        // hash function to map values to key
        public int hashFunction(int key)
        {
            return (key % bucket);
        }

        public void insertItem(int key)
        {
            // get the hash index of key
            int index = hashFunction(key);
            // insert key into hash table at that index
            table[index].add(key);
        }

        public void deleteItem(int key)
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
