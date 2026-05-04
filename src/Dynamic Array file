public class DynamicArray implements DataStructure {

    private PatientRecord[] records;
    private int size;

    public DynamicArray() {
        records = new PatientRecord[10];
        size = 0;
    }

    public void insertRecord(PatientRecord record) {
        if (size == records.length) {
            resize();
        }
        records[size] = record;
        size++;
    }

    public PatientRecord searchRecord(String id) {
        for (int i = 0; i < size; i++) {
            if (records[i].getId().equals(id)) {
                return records[i];
            }
        }
        return null;
    }

    public boolean deleteRecord(String id) {
        for (int i = 0; i < size; i++) {
            if (records[i].getId().equals(id)) {
                for (int j = i; j < size - 1; j++) {
                    records[j] = records[j + 1];
                }
                records[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public PatientRecord[] traverseRecords() {
        PatientRecord[] result = new PatientRecord[size];
        for (int i = 0; i < size; i++) {
            result[i] = records[i];
        }
        return result;
    }

    private void resize() {
        PatientRecord[] newRecords = new PatientRecord[records.length * 2];
        for (int i = 0; i < records.length; i++) {
            newRecords[i] = records[i];
        }
        records = newRecords;
    }
}
