// HashBasedStructure stores patient records by ID using chained buckets.
public class HashBasedStructure implements DataStructure {

    private static final int DEFAULT_CAPACITY = 1000;
    private static final double MAX_LOAD_FACTOR = 0.75;

    // Each node stores one record inside a bucket chain.
    private static class Node {
        PatientRecord record;
        Node next;

        Node(PatientRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    private Node[] table;
    private int size;

    // Start with a fixed number of buckets.
    public HashBasedStructure() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // Build a deterministic bucket index from the patient ID.
    private int hash(String id) {
        int h = 0;
        for (int i = 0; i < id.length(); i++) {
            h = (31 * h + id.charAt(i)) % table.length;
        }
        return h;
    }

    // Insert a new record or replace an existing record with the same ID.
    @Override
    public void insertRecord(PatientRecord record) {
        if ((size + 1.0) / table.length > MAX_LOAD_FACTOR) {
            resize();
        }

        int index = hash(record.getId());
        Node current = table[index];

        while (current != null) {
            if (current.record.getId().equals(record.getId())) {
                current.record = record;
                return;
            }
            current = current.next;
        }

        Node newNode = new Node(record);
        newNode.next = table[index];
        table[index] = newNode;

        size++;
    }

    // Rebuild the table with more buckets when it becomes crowded.
    private void resize() {
        Node[] oldTable = table;
        table = new Node[oldTable.length * 2];
        size = 0;

        for (int i = 0; i < oldTable.length; i++) {
            Node current = oldTable[i];
            while (current != null) {
                insertRecord(current.record);
                current = current.next;
            }
        }
    }

    // Search only the bucket that matches the patient ID hash.
    @Override
    public PatientRecord searchRecord(String id) {
        int index = hash(id);
        Node current = table[index];

        while (current != null) {
            if (current.record.getId().equals(id)) {
                return current.record;
            }
            current = current.next;
        }

        return null;
    }

    // Remove a matching record by relinking its bucket chain.
    @Override
    public boolean deleteRecord(String id) {
        int index = hash(id);
        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if (current.record.getId().equals(id)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }

    // Visit every bucket and collect every stored record.
    @Override
    public PatientRecord[] traverseRecords() {
        PatientRecord[] result = new PatientRecord[size];
        int idx = 0;

        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            while (current != null) {
                result[idx++] = current.record;
                current = current.next;
            }
        }

        return result;
    }
}
