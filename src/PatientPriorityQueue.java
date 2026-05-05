package healthbench.structures;
import java.util.PriorityQueue;
import java.util.Comparator;
import healthbench.model.PatientRecord;

public class PatientPriorityQueue implements DataStructure {

    private PriorityQueue<PatientRecord> heap;

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

    private int priorityValue(String type) {
        switch (type.toLowerCase()) {
            case "emergency": return 3;
            case "urgent": return 2;
            case "elective": return 1;
            case "normal": return 1;
            default: return 0;
        }
    }

    @Override
    public void insertRecord(PatientRecord record) {
        heap.add(record);
    }

    @Override
    public PatientRecord searchRecord(String id) {
        for (PatientRecord r : heap) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

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

    @Override
    public PatientRecord[] traverseRecords() {
        return heap.toArray(new PatientRecord[0]);
    }
}

