// Common interface that every project data structure must implement.
public interface DataStructure {

    // Add a new PatientRecord to the structure.
    void insertRecord(PatientRecord record);

    // Find a PatientRecord using its generated ID.
    PatientRecord searchRecord(String id);

    // Remove a PatientRecord using its generated ID.
    boolean deleteRecord(String id);

    // Return all records for display or benchmarking.
    PatientRecord[] traverseRecords();
}
