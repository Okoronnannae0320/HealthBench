import java.util.PriorityQueue;
import java.util.Comparator;

// PatientPriorityQueue simulates hospital admission order.
public class PatientPriorityQueue implements DataStructure {

    private PriorityQueue<PatientRecord> heap;

    // Emergency patients come first, then older patients when priority ties.
    public PatientPriorityQueue() {
        heap = new PriorityQueue<>(new Comparator<PatientRecord>() {
            @Override
            public int compare(PatientRecord a, PatientRecord b) {

                int p1 = priorityValue(a.getAdmissionType());
                int p2 = priorityValue(b.getAdmissionType());

                if (p1 != p2) {
                    return p2 - p1; // higher priority first
                }

                if (a.getAge() != b.getAge()) {
                    return b.getAge() - a.getAge(); // older first
                }

                return a.getId().compareTo(b.getId());
            }
        });
    }

    // Convert admission type text into a numeric priority.
    private int priorityValue(String type) {
        switch (type.toLowerCase()) {
            case "emergency": return 3;
            case "urgent": return 2;
            case "elective": return 1;
            case "normal": return 1;
            default: return 0;
        }
    }

    // Add a patient to the heap using the priority rule.
    @Override
    public void insertRecord(PatientRecord record) {
        heap.add(record);
    }

    // Priority queues do not search by ID directly, so this scans the heap.
    @Override
    public PatientRecord searchRecord(String id) {
        for (PatientRecord r : heap) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    // Delete scans for the matching ID and removes that patient.
    @Override
    public boolean deleteRecord(String id) {
        for (PatientRecord r : heap) {
            if (r.getId().equals(id)) {
                heap.remove(r);
                return true;
            }
        }
        return false;
    }

    // Return the current heap contents as an array for traversal output.
    @Override
    public PatientRecord[] traverseRecords() {
        return heap.toArray(new PatientRecord[0]);
    }

    // Remove and return the next patient to be admitted.
    public PatientRecord processNextRecord() {
        return heap.poll();
    }

    // View the next patient without removing that record.
    public PatientRecord peekNextRecord() {
        return heap.peek();
    }

    // Tell the simulation whether any patients remain.
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
