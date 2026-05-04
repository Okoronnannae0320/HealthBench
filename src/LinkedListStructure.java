public class LinkedListStructure implements DataStructure {

    private Node head;
    private int size;

    private class Node {
        PatientRecord record;
        Node next;

        Node(PatientRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    public LinkedListStructure() {
        head = null;
        size = 0;
    }

    public void insertRecord(PatientRecord record) {
        Node newNode = new Node(record);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
    }

    public PatientRecord searchRecord(String id) {
        Node current = head;

        while (current != null) {
            if (current.record.getId().equals(id)) {
                return current.record;
            }
            current = current.next;
        }

        return null;
    }

    public boolean deleteRecord(String id) {
        if (head == null) return false;

        if (head.record.getId().equals(id)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;

        while (current.next != null) {
            if (current.next.record.getId().equals(id)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public PatientRecord[] traverseRecords() {
        PatientRecord[] result = new PatientRecord[size];
        Node current = head;
        int index = 0;

        while (current != null) {
            result[index++] = current.record;
            current = current.next;
        }

        return result;
    }
}
