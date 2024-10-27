public class DataPair {
    private String key;
    private String val;

    /*
    This is the java data object that stores the key-value pair.
     */
    public DataPair(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }
}
