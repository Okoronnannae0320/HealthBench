import java.util.Scanner;

// Main.java is the console entry point that connects the dataset, structures, and user menu.
public class Main {

    // The demo and benchmark focus on 10,000 records, as required by the project scope.
    private static final int DEMO_RECORD_LIMIT = 10000;

    private static PatientRecord[] fullDataset;
    private static PatientRecord[] demoRecords;
    private static DynamicArray dynamicArray;
    private static LinkedListStructure linkedList;
    private static HashBasedStructure hashTable;
    private static PatientPriorityQueue priorityQueue;

    public static void main(String[] args) throws Exception {
        printHeader();
        loadDatasetAndBuildStructures();

        // Scanner reads all menu choices and patient ID inputs from the console.
        try (Scanner scanner = new Scanner(System.in)) {
            runMenu(scanner, args);
        }
    }

    private static void printHeader() {
        System.out.println("======================================");
        System.out.println("HealthBench Patient Records System");
        System.out.println("======================================");
    }

    private static void loadDatasetAndBuildStructures() throws Exception {
        // Load the full CSV dataset, then keep a controlled sample for the demo structures.
        fullDataset = DatasetLoader.loadRecords();
        demoRecords = DatasetLoader.limit(fullDataset, DEMO_RECORD_LIMIT);

        // Create one instance of each data structure used in the project.
        dynamicArray = new DynamicArray();
        linkedList = new LinkedListStructure();
        hashTable = new HashBasedStructure();
        priorityQueue = new PatientPriorityQueue();

        // Insert the same PatientRecord objects into every structure for a fair comparison.
        for (PatientRecord record : demoRecords) {
            dynamicArray.insertRecord(record);
            linkedList.insertRecord(record);
            hashTable.insertRecord(record);
            priorityQueue.insertRecord(record);
        }

        System.out.println("Dataset loaded: " + fullDataset.length + " records");
        System.out.println("Active records in each structure: " + demoRecords.length);
    }

    private static void runMenu(Scanner scanner, String[] args) throws Exception {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = prompt(scanner, "Choose an option: ");

            // Each menu option calls the method responsible for that project feature.
            switch (choice) {
                case "1":
                    showDatasetSummary();
                    break;
                case "2":
                    runCoreOperationsDemo();
                    break;
                case "3":
                    searchById(scanner);
                    break;
                case "4":
                    deleteById(scanner);
                    break;
                case "5":
                    traverseSelectedStructure(scanner);
                    break;
                case "6":
                    runPriorityQueueSimulation(demoRecords);
                    break;
                case "7":
                    runSortingDemo(demoRecords);
                    break;
                case "8":
                    Benchmark.main(args);
                    break;
                case "9":
                    loadDatasetAndBuildStructures();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting HealthBench.");
                    break;
                default:
                    if (choice.isEmpty()) {
                        running = false;
                    } else {
                        System.out.println("Invalid option. Try again.");
                    }
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== Main Menu =====");
        System.out.println("1. Show dataset summary");
        System.out.println("2. Run core operations demo");
        System.out.println("3. Search patient by ID");
        System.out.println("4. Delete patient by ID");
        System.out.println("5. Traverse selected structure");
        System.out.println("6. Run priority queue admission simulation");
        System.out.println("7. Run merge sort demo");
        System.out.println("8. Run full benchmark");
        System.out.println("9. Reload dataset and rebuild structures");
        System.out.println("0. Exit");
    }

    private static String prompt(Scanner scanner, String message) {
        System.out.print(message);
        if (!scanner.hasNextLine()) {
            System.out.println();
            return "";
        }
        return scanner.nextLine().trim();
    }

    private static void showDatasetSummary() {
        System.out.println();
        System.out.println("===== Dataset Summary =====");
        System.out.println("Full dataset records: " + fullDataset.length);
        System.out.println("Records loaded into each active structure: " + demoRecords.length);
        System.out.println("First record: " + fullDataset[0]);
        System.out.println("Last loaded demo record: " + demoRecords[demoRecords.length - 1]);
    }

    private static void runCoreOperationsDemo() {
        System.out.println();
        // Run insert, search, delete, and traversal on each structure.
        runStructureDemo("DynamicArray", new DynamicArray(), demoRecords);
        runStructureDemo("LinkedListStructure", new LinkedListStructure(), demoRecords);
        runStructureDemo("HashBasedStructure", new HashBasedStructure(), demoRecords);
        runStructureDemo("PatientPriorityQueue", new PatientPriorityQueue(), demoRecords);
    }

    private static void runStructureDemo(String name, DataStructure structure, PatientRecord[] records) {
        // Insert all demo records before testing the required operations.
        for (PatientRecord record : records) {
            structure.insertRecord(record);
        }

        // Use predictable IDs so the demo output is easy to explain.
        String searchId = records[0].getId();
        String deleteId = records[records.length - 1].getId();

        PatientRecord found = structure.searchRecord(searchId);
        boolean deleted = structure.deleteRecord(deleteId);
        int remainingRecords = structure.traverseRecords().length;

        System.out.println("===== " + name + " Demo =====");
        System.out.println("Inserted records: " + records.length);
        System.out.println("Search " + searchId + ": " + (found != null ? "found" : "not found"));
        System.out.println("Delete " + deleteId + ": " + (deleted ? "deleted" : "not found"));
        System.out.println("Traverse count after delete: " + remainingRecords);
        System.out.println();
    }

    private static void searchById(Scanner scanner) {
        // Search the selected patient ID in all active structures.
        String id = prompt(scanner, "Enter patient ID to search, for example P25: ");
        if (id.isEmpty()) {
            System.out.println("No ID entered.");
            return;
        }

        System.out.println();
        System.out.println("===== Search Results For " + id + " =====");
        printSearchResult("DynamicArray", dynamicArray.searchRecord(id));
        printSearchResult("LinkedListStructure", linkedList.searchRecord(id));
        printSearchResult("HashBasedStructure", hashTable.searchRecord(id));
        printSearchResult("PatientPriorityQueue", priorityQueue.searchRecord(id));
    }

    private static void printSearchResult(String name, PatientRecord record) {
        if (record == null) {
            System.out.println(name + ": not found");
        } else {
            System.out.println(name + ": " + record);
        }
    }

    private static void deleteById(Scanner scanner) {
        // Delete the selected patient ID from all active structures.
        String id = prompt(scanner, "Enter patient ID to delete from all active structures: ");
        if (id.isEmpty()) {
            System.out.println("No ID entered.");
            return;
        }

        System.out.println();
        System.out.println("===== Delete Results For " + id + " =====");
        System.out.println("DynamicArray: " + deleteMessage(dynamicArray.deleteRecord(id)));
        System.out.println("LinkedListStructure: " + deleteMessage(linkedList.deleteRecord(id)));
        System.out.println("HashBasedStructure: " + deleteMessage(hashTable.deleteRecord(id)));
        System.out.println("PatientPriorityQueue: " + deleteMessage(priorityQueue.deleteRecord(id)));
    }

    private static String deleteMessage(boolean deleted) {
        return deleted ? "deleted" : "not found";
    }

    private static void traverseSelectedStructure(Scanner scanner) {
        // Let the user choose which structure should be traversed.
        DataStructure structure = chooseStructure(scanner);
        if (structure == null) {
            return;
        }

        int limit = readPositiveInt(scanner, "How many records should be displayed? ", 10);
        PatientRecord[] records = structure.traverseRecords();

        System.out.println();
        System.out.println("===== Traversal Output =====");
        System.out.println("Total traversed records: " + records.length);

        int displayCount = Math.min(limit, records.length);
        for (int i = 0; i < displayCount; i++) {
            System.out.println((i + 1) + ". " + records[i]);
        }
    }

    private static DataStructure chooseStructure(Scanner scanner) {
        System.out.println();
        System.out.println("Choose structure:");
        System.out.println("1. DynamicArray");
        System.out.println("2. LinkedListStructure");
        System.out.println("3. HashBasedStructure");
        System.out.println("4. PatientPriorityQueue");

        String choice = prompt(scanner, "Structure option: ");
        switch (choice) {
            case "1":
                return dynamicArray;
            case "2":
                return linkedList;
            case "3":
                return hashTable;
            case "4":
                return priorityQueue;
            default:
                System.out.println("Invalid structure option.");
                return null;
        }
    }

    private static int readPositiveInt(Scanner scanner, String message, int defaultValue) {
        String input = prompt(scanner, message);
        if (input.isEmpty()) {
            return defaultValue;
        }

        try {
            int value = Integer.parseInt(input);
            return value > 0 ? value : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static void runSortingDemo(PatientRecord[] records) {
        // Copy records into sortable structures so the original active structures stay unchanged.
        DynamicArray arrayForSort = new DynamicArray();
        LinkedListStructure listForSort = new LinkedListStructure();

        for (PatientRecord record : records) {
            arrayForSort.insertRecord(record);
            listForSort.insertRecord(record);
        }

        arrayForSort.sortByAge();
        listForSort.sortByAge();

        System.out.println();
        System.out.println("===== Merge Sort Demo =====");
        System.out.println("DynamicArray first age after sort: " +
                arrayForSort.traverseRecords()[0].getAge());
        System.out.println("LinkedListStructure first age after sort: " +
                listForSort.traverseRecords()[0].getAge());
    }

    private static void runPriorityQueueSimulation(PatientRecord[] records) {
        // Use a small sample to show how admission priority changes processing order.
        PatientPriorityQueue simulationQueue = new PatientPriorityQueue();
        int simulationSize = Math.min(25, records.length);

        for (int i = 0; i < simulationSize; i++) {
            simulationQueue.insertRecord(records[i]);
        }

        System.out.println();
        System.out.println("===== Priority Queue Admission Simulation =====");
        System.out.println("Rule: Emergency > Urgent > Elective/Normal, then older patients first.");

        for (int i = 1; i <= 5 && !simulationQueue.isEmpty(); i++) {
            PatientRecord next = simulationQueue.processNextRecord();
            System.out.println(i + ". " + next.getId() +
                    " | " + next.getAdmissionType() +
                    " | age " + next.getAge() +
                    " | " + next.getMedicalCondition());
        }
    }
}
