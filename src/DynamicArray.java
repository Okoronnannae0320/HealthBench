package healthbench.structures;
import healthbench.model.PatientRecord;

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

    // Merge Sorting Code by age 0-120
    public void sortByAge() {
        if (size <= 1) return;
        mergeSort(0, size - 1);
    }

    private void mergeSort(int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }

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

    public void selectionSortByAge() {
        if (size <= 1) return;

        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < size; j++) {
                if (records[j].getAge() < records[minIndex].getAge()) {
                    minIndex = j;
                }
            }

            // swap
            PatientRecord temp = records[minIndex];
            records[minIndex] = records[i];
            records[i] = temp;
        }
    }
}
