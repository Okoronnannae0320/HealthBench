package healthbench.benchmark;
import healthbench.model.PatientRecord;
import healthbench.structures.*;
import healthbench.util.DatasetLoader;

public class Benchmark {

    public static void main(String[] args) throws Exception {

        System.out.println("Loading full dataset...");
        PatientRecord[] full = DatasetLoader.loadRecords("dataset/healthcare_dataset.csv");
        System.out.println("Loaded: " + full.length + " records\n");

        PatientRecord[] data5000  = DatasetLoader.limit(full, 5000);
        PatientRecord[] data10000 = DatasetLoader.limit(full, 10000);
        PatientRecord[] data20000 = DatasetLoader.limit(full, 20000);
        PatientRecord[] data30000 = DatasetLoader.limit(full, 30000);

        runAll("DynamicArray", new DynamicArray(), data5000, data10000, data20000, data30000);
        runAll("LinkedListStructure", new LinkedListStructure(), data5000, data10000, data20000, data30000);
        runAll("HashBasedStructure", new HashBasedStructure(), data5000, data10000, data20000, data30000);
        runAll("PatientPriorityQueue", new PatientPriorityQueue(), data5000, data10000, data20000, data30000);
    }

    private static void runAll(String name, DataStructure ds,
                               PatientRecord[] d5k, PatientRecord[] d10k,
                               PatientRecord[] d20k, PatientRecord[] d30k) {

        System.out.println("===== " + name + " =====");

        benchmark(ds, d5k, 5000, name);
        benchmark(ds, d10k, 10000, name);
        benchmark(ds, d20k, 20000, name);
        benchmark(ds, d30k, 30000, name);

        System.out.println();
    }

    private static void benchmark(DataStructure ds, PatientRecord[] data, int size, String name) {

        long start, end;

        // Insert
        start = System.nanoTime();
        for (PatientRecord r : data) ds.insertRecord(r);
        end = System.nanoTime();
        long insertTime = end - start;

        // Search
        start = System.nanoTime();
        for (int i = 0; i < 100 && i < data.length; i++) {
            ds.searchRecord(data[i].getId());
        }
        end = System.nanoTime();
        long searchTime = end - start;

        // Delete
        start = System.nanoTime();
        for (int i = 0; i < 100 && i < data.length; i++) {
            ds.deleteRecord(data[i].getId());
        }
        end = System.nanoTime();
        long deleteTime = end - start;

        // Sort (only for array + linked list)
        long sortTime = -1;
        if (name.equals("DynamicArray")) {
            DynamicArray da = (DynamicArray) ds;
            start = System.nanoTime();
            da.sortByAge();
            end = System.nanoTime();
            sortTime = end - start;
        }
        else if (name.equals("LinkedListStructure")) {
            LinkedListStructure ll = (LinkedListStructure) ds;
            start = System.nanoTime();
            ll.sortByAge();
            end = System.nanoTime();
            sortTime = end - start;
        }

        System.out.println(size + " records -> Insert: " + insertTime +
                " ns | Search: " + searchTime +
                " ns | Delete: " + deleteTime +
                (sortTime >= 0 ? " ns | Sort: " + sortTime + " ns" : " | Sort: N/A"));
    }
}
