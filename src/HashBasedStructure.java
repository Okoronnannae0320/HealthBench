package healthbench.structures;
import healthbench.model.PatientRecord;

public class HashBasedStructure implements DataStructure {

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

    public HashBasedStructure() {
        table = new Node[1000]; // good starting size
        size = 0;
    }

    private int hash(String id) {
        int h = 0;
        for (int i = 0; i < id.length(); i++) {
            h = (31 * h + id.charAt(i)) % table.length;
        }
        return h;
    }

    @Override
    public void insertRecord(PatientRecord record) {
        int index = hash(record.getId());
        Node newNode = new Node(record);

        newNode.next = table[index];
        table[index] = newNode;

        size++;
    }

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
