import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// DatasetLoader reads the CSV file and converts each row into a PatientRecord object.
public class DatasetLoader {

    private static final String DEFAULT_DATASET_PATH = "dataset/healthcare_dataset.csv";

    public static PatientRecord[] loadRecords(String filePath) throws IOException {
        // Resolve the dataset path so the program can run from IntelliJ or the command line.
        Path datasetPath = resolveDatasetPath(filePath);

        // Start with space for 10,000 records and resize if the dataset is larger.
        PatientRecord[] records = new PatientRecord[10000];
        int count = 0;

        BufferedReader br = Files.newBufferedReader(datasetPath);
        String header = br.readLine(); // skip header

        String line;
        int idCounter = 1;

        while ((line = br.readLine()) != null) {

            // Parse each CSV line safely, including fields that contain commas inside quotes.
            String[] f = parseCsvLine(line);
            if (f.length < 15) continue;

            // The source dataset has no unique ID, so the loader generates one.
            String id = "P" + idCounter++;

            // Convert the selected CSV columns into one standardized PatientRecord.
            PatientRecord r = new PatientRecord(
                    id,
                    f[0].trim(),
                    CSVUtils.toInt(f[1]),
                    f[2].trim(),
                    f[3].trim(),
                    f[4].trim(),
                    f[5].trim(),
                    f[6].trim(),
                    f[7].trim(),
                    f[8].trim(),
                    CSVUtils.toDouble(f[9]),
                    CSVUtils.toInt(f[10]),
                    f[11].trim(),
                    f[12].trim(),
                    f[13].trim(),
                    f[14].trim()
            );

            if (count == records.length) {
                records = resize(records);
            }

            records[count++] = r;
        }

        br.close();
        return trim(records, count);
    }

    public static PatientRecord[] loadRecords() throws IOException {
        return loadRecords(DEFAULT_DATASET_PATH);
    }

    private static Path resolveDatasetPath(String filePath) throws FileNotFoundException {
        // Try multiple common locations so the dataset can be found in different run setups.
        String requested = (filePath == null || filePath.isBlank())
                ? DEFAULT_DATASET_PATH
                : filePath;

        Path[] candidates = {
                Paths.get(requested),
                Paths.get(DEFAULT_DATASET_PATH),
                Paths.get("..", "dataset", "healthcare_dataset.csv"),
                Paths.get("healthcare_dataset.csv")
        };

        for (Path candidate : candidates) {
            if (Files.isRegularFile(candidate)) {
                return candidate;
            }
        }

        throw new FileNotFoundException(
                "Could not find healthcare_dataset.csv. Tried: " +
                        requested + ", " +
                        DEFAULT_DATASET_PATH + ", " +
                        "../dataset/healthcare_dataset.csv, " +
                        "healthcare_dataset.csv"
        );
    }

    private static String[] parseCsvLine(String line) {
        // Custom CSV parser used to handle quoted commas without external libraries.
        String[] fields = new String[16];
        int count = 0;
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                if (count == fields.length) fields = resize(fields);
                fields[count++] = current.toString();
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        if (count == fields.length) fields = resize(fields);
        fields[count++] = current.toString();

        String[] out = new String[count];
        for (int i = 0; i < count; i++) out[i] = fields[i];
        return out;
    }

    private static String[] resize(String[] arr) {
        String[] out = new String[arr.length * 2];
        for (int i = 0; i < arr.length; i++) out[i] = arr[i];
        return out;
    }

    private static PatientRecord[] resize(PatientRecord[] arr) {
        PatientRecord[] newArr = new PatientRecord[arr.length * 2];
        for (int i = 0; i < arr.length; i++) newArr[i] = arr[i];
        return newArr;
    }

    private static PatientRecord[] trim(PatientRecord[] arr, int size) {
        PatientRecord[] out = new PatientRecord[size];
        for (int i = 0; i < size; i++) out[i] = arr[i];
        return out;
    }

    // Controls the number of records used for demos and benchmarks.
    public static PatientRecord[] limit(PatientRecord[] full, int amount) {
        if (amount > full.length) amount = full.length;

        PatientRecord[] out = new PatientRecord[amount];
        for (int i = 0; i < amount; i++) {
            out[i] = full[i];
        }
        return out;
    }
}
