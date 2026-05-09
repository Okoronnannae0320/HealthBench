// DynamicArray stores patient records in a resizable array.
public class DynamicArray implements DataStructure {

    // The array can have unused capacity, while size counts active records.
    private PatientRecord[] records;
    private int size;

    // Start with a small capacity and grow when more space is needed.
    public DynamicArray() {
        records = new PatientRecord[10];
        size = 0;
    }

    // Add a record at the next available index.
    public void insertRecord(PatientRecord record) {
        if (size == records.length) {
            resize();
        }
        records[size] = record;
        size++;
    }

    // Linear search checks each active record until the ID is found.
    public PatientRecord searchRecord(String id) {
        for (int i = 0; i < size; i++) {
            if (records[i].getId().equals(id)) {
                return records[i];
            }
        }
        return null;
    }

    // Delete a record by shifting later records left.
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

    // Return only the active records, not the unused array capacity.
    public PatientRecord[] traverseRecords() {
        PatientRecord[] result = new PatientRecord[size];
        for (int i = 0; i < size; i++) {
            result[i] = records[i];
        }
        return result;
    }


    // Double the internal array capacity when it becomes full.
    private void resize() {
        PatientRecord[] newRecords = new PatientRecord[records.length * 2];
        for (int i = 0; i < records.length; i++) {
            newRecords[i] = records[i];
        }
        records = newRecords;
    }

    // Sort records by age using merge sort.
    public void sortByAge() {
        if (size <= 1) return;
        mergeSort(0, size - 1);
    }

    // Recursively split the array before merging sorted sections.
    private void mergeSort(int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    // Merge two sorted sections back into the main array.
    private void merge(int left, int mid, int right) {
        PatientRecord[] temp = new PatientRecord[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (records[i].getAge() <= records[j].getAge()) {
                temp[k++] = records[i++];
            } else {
                temp[k++] = records[j++];
            }
        }

        while (i <= mid) temp[k++] = records[i++];
        while (j <= right) temp[k++] = records[j++];

        for (int x = 0; x < temp.length; x++) {
            records[left + x] = temp[x];
        }
    }
}
