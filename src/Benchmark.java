package healthbench.benchmark;
import healthbench.model.PatientRecord;
import healthbench.structures.*;
import healthbench.util.DatasetLoader;

public class Benchmark {

    public static void main(String[] args) throws Exception {

        System.out.println("Loading full dataset...");
        PatientRecord[] full = DatasetLoader.loadRecords("dataset/healthcare_dataset.csv");
        System.out.println("Loaded: " + full.length + " records\n");

        PatientRecord[] data5000 = DatasetLoader.limit(full, 5000);
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

        // INSERT
        start = System.nanoTime();
        for (PatientRecord r : data) ds.insertRecord(r);
        end = System.nanoTime();
        long insertTime = end - start;



        // SEARCH TESTS (begin/mid/end/null)

        // Beginning
        start = System.nanoTime();
        ds.searchRecord(data[0].getId());
        end = System.nanoTime();
        long searchBeginning = end - start;

        // Middle
        start = System.nanoTime();
        ds.searchRecord(data[data.length / 2].getId());
        end = System.nanoTime();
        long searchMiddle = end - start;

        // End
        start = System.nanoTime();
        ds.searchRecord(data[data.length - 1].getId());
        end = System.nanoTime();
        long searchEnd = end - start;

        // Null (not found)
        start = System.nanoTime();
        ds.searchRecord("ID_NOT_FOUND_123456");
        end = System.nanoTime();
        long searchNull = end - start;


        // DELETE (delete first 100 items)
        start = System.nanoTime();
        for (int i = 0; i < 100 && i < data.length; i++) {
            ds.deleteRecord(data[i].getId());
        }
        end = System.nanoTime();
        long deleteTime = end - start;



        // SORTING (DynamicArray + LinkedList)

        long mergeSortTime = -1;
        long selectionSortTime = -1;

        if (name.equals("DynamicArray")) {
            DynamicArray da = (DynamicArray) ds;

            // Merge Sort
            start = System.nanoTime();
            da.sortByAge();
            end = System.nanoTime();
            mergeSortTime = end - start;

            // Selection Sort
            start = System.nanoTime();
            da.selectionSortByAge();
            end = System.nanoTime();
            selectionSortTime = end - start;
        }

        if (name.equals("LinkedListStructure")) {
            LinkedListStructure ll = (LinkedListStructure) ds;

            // Merge Sort
            start = System.nanoTime();
            ll.sortByAge();
            end = System.nanoTime();
            mergeSortTime = end - start;

            // Selection Sort
            start = System.nanoTime();
            ll.selectionSortByAge();
            end = System.nanoTime();
            selectionSortTime = end - start;
        }



        // PRINT RESULTS
        System.out.println(size + " records -> " +
                "Insert: " + insertTime + " ns | " +
                "Search Begin: " + searchBeginning + " ns | " +
                "Search Mid: " + searchMiddle + " ns | " +
                "Search End: " + searchEnd + " ns | " +
                "Search Null: " + searchNull + " ns | " +
                "Delete: " + deleteTime + " ns | " +
                "Merge Sort: " + mergeSortTime + " ns | " +
                "Selection Sort: " + selectionSortTime + " ns");

    }
}