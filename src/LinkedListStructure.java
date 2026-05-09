// LinkedListStructure stores patient records in a chain of nodes.
public class LinkedListStructure implements DataStructure {

    private Node head;
    private int size;

    // Each node stores one record and a reference to the next node.
    private class Node {
        PatientRecord record;
        Node next;

        Node(PatientRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    // Begin with an empty list.
    public LinkedListStructure() {
        head = null;
        size = 0;
    }

    // Append the new record to the end of the list.
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

    // Linear search follows node links until the ID is found.
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

    // Delete a record by relinking nodes around the matching ID.
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

    // Convert the linked list into an array for display and benchmarking.
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

    // Sort records by age using merge sort.
    public void sortByAge() {
        head = mergeSortIterative(head);
    }

    // Iteratively merge sorted blocks until the whole list is sorted.
    private Node mergeSortIterative(Node head) {
        if (head == null || head.next == null) return head;

        int length = getLength(head);

        Node dummy = new Node(null);
        dummy.next = head;

        for (int step = 1; step < length; step *= 2) {
            Node prev = dummy;
            Node curr = dummy.next;

            while (curr != null) {
                Node left = curr;
                Node right = split(left, step);
                curr = split(right, step);

                prev.next = merge(left, right);
                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }

        return dummy.next;
    }

    // Split the list after a fixed number of nodes.
    private Node split(Node head, int step) {
        if (head == null) return null;

        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }

        Node second = head.next;
        head.next = null;
        return second;
    }

    // Merge two sorted node chains by patient age.
    private Node merge(Node a, Node b) {
        Node dummy = new Node(null);
        Node tail = dummy;

        while (a != null && b != null) {
            if (a.record.getAge() <= b.record.getAge()) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    // Count nodes so merge sort knows how many records are stored.
    private int getLength(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
