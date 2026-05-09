// Benchmark measures operation time for each structure under the same workloads.
public class Benchmark {

    // Dataset sizes used for small, medium, and full benchmark tests.
    private static final int[] DATASET_SIZES = {1000, 5000, 10000};
    private static final int RUNS_PER_TEST = 3;
    private static final int SAMPLE_OPERATIONS = 100;

    // Load the dataset once, then benchmark every structure.
    public static void main(String[] args) throws Exception {
        System.out.println("Loading full dataset...");
        PatientRecord[] full = DatasetLoader.loadRecords("dataset/healthcare_dataset.csv");
        System.out.println("Loaded: " + full.length + " records");
        System.out.println("Runs per test: " + RUNS_PER_TEST + "\n");

        runAll("DynamicArray", full);
        runAll("LinkedListStructure", full);
        runAll("HashBasedStructure", full);
        runAll("PatientPriorityQueue", full);
    }

    // Run all benchmark sizes for one selected structure.
    private static void runAll(String name, PatientRecord[] fullDataset) {
        System.out.println("===== " + name + " =====");

        for (int size : DATASET_SIZES) {
            PatientRecord[] data = DatasetLoader.limit(fullDataset, size);
            BenchmarkResult total = new BenchmarkResult();

            for (int run = 0; run < RUNS_PER_TEST; run++) {
                total.add(benchmark(createStructure(name), data, name));
            }

            BenchmarkResult average = total.average(RUNS_PER_TEST);
            System.out.println(formatResult(size, average));
        }

        System.out.println();
    }

    // Create a fresh structure so each benchmark starts empty.
    private static DataStructure createStructure(String name) {
        if (name.equals("DynamicArray")) {
            return new DynamicArray();
        }
        if (name.equals("LinkedListStructure")) {
            return new LinkedListStructure();
        }
        if (name.equals("HashBasedStructure")) {
            return new HashBasedStructure();
        }
        if (name.equals("PatientPriorityQueue")) {
            return new PatientPriorityQueue();
        }
        throw new IllegalArgumentException("Unknown data structure: " + name);
    }

    // Measure insert, search, traversal, deletion, and optional sorting.
    private static BenchmarkResult benchmark(DataStructure ds, PatientRecord[] data, String name) {
        long start;
        long end;

        start = System.nanoTime();
        for (PatientRecord r : data) {
            ds.insertRecord(r);
        }
        end = System.nanoTime();
        long insertTime = end - start;

        int operations = Math.min(SAMPLE_OPERATIONS, data.length);

        start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            ds.searchRecord(data[i].getId());
        }
        end = System.nanoTime();
        long searchTime = end - start;

        start = System.nanoTime();
        ds.traverseRecords();
        end = System.nanoTime();
        long traversalTime = end - start;

        start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            ds.deleteRecord(data[i].getId());
        }
        end = System.nanoTime();
        long deleteTime = end - start;

        long sortTime = -1;
        if (name.equals("DynamicArray")) {
            DynamicArray da = (DynamicArray) ds;
            start = System.nanoTime();
            da.sortByAge();
            end = System.nanoTime();
            sortTime = end - start;
        } else if (name.equals("LinkedListStructure")) {
            LinkedListStructure ll = (LinkedListStructure) ds;
            start = System.nanoTime();
            ll.sortByAge();
            end = System.nanoTime();
            sortTime = end - start;
        }

        return new BenchmarkResult(insertTime, searchTime, deleteTime, traversalTime, sortTime);
    }

    // Format averaged times for console output.
    private static String formatResult(int size, BenchmarkResult result) {
        String output = size + " records average -> Insert: " + result.insertTime +
                " ns | Search: " + result.searchTime +
                " ns | Delete: " + result.deleteTime +
                " ns | Traverse: " + result.traversalTime + " ns";

        if (result.sortTime >= 0) {
            output += " | Sort: " + result.sortTime + " ns";
        } else {
            output += " | Sort: N/A";
        }

        return output;
    }

    // BenchmarkResult stores timing values for one run or average result.
    private static class BenchmarkResult {
        long insertTime;
        long searchTime;
        long deleteTime;
        long traversalTime;
        long sortTime;

        BenchmarkResult() {
            this(0, 0, 0, 0, -1);
        }

        BenchmarkResult(long insertTime, long searchTime, long deleteTime,
                        long traversalTime, long sortTime) {
            this.insertTime = insertTime;
            this.searchTime = searchTime;
            this.deleteTime = deleteTime;
            this.traversalTime = traversalTime;
            this.sortTime = sortTime;
        }

        // Add another run's times into the total.
        void add(BenchmarkResult other) {
            insertTime += other.insertTime;
            searchTime += other.searchTime;
            deleteTime += other.deleteTime;
            traversalTime += other.traversalTime;

            if (other.sortTime >= 0) {
                if (sortTime < 0) {
                    sortTime = 0;
                }
                sortTime += other.sortTime;
            }
        }

        // Convert the total times into average times.
        BenchmarkResult average(int runs) {
            long averageSortTime = sortTime >= 0 ? sortTime / runs : -1;
            return new BenchmarkResult(
                    insertTime / runs,
                    searchTime / runs,
                    deleteTime / runs,
                    traversalTime / runs,
                    averageSortTime
            );
        }
    }
}
