package healthbench.structures;

import healthbench.model.PatientRecord;

public interface DataStructure {
    void insertRecord(PatientRecord record);
    PatientRecord searchRecord(String id);
    boolean deleteRecord(String id);
    PatientRecord[] traverseRecords();
}
