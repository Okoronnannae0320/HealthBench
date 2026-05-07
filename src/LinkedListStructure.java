package healthbench.structures;
import healthbench.model.PatientRecord;

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

    //Merge Sorting Code by age  0-120
    public void sortByAge() {
        head = mergeSortIterative(head);
    }

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

    private Node split(Node head, int step) {
        if (head == null) return null;

        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }

        Node second = head.next;
        head.next = null;
        return second;
    }

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

    private int getLength(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public void selectionSortByAge() {
        // Convert linked list to array
        PatientRecord[] arr = traverseRecords();

        // Sort array using selection sort
        selectionSort(arr);

        // Rebuild linked list from sorted array
        head = null;
        size = 0;
        for (PatientRecord r : arr) {
            insertRecord(r);
        }
    }

    private void selectionSort(PatientRecord[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j].getAge() < arr[minIndex].getAge()) {
                    minIndex = j;
                }
            }

            PatientRecord temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}


