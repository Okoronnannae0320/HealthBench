package healthbench.util;
import healthbench.model.PatientRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class DatasetLoader {

    public static PatientRecord[] loadRecords(String filePath) throws IOException {

        PatientRecord[] records = new PatientRecord[10000];
        int count = 0;

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String header = br.readLine(); // skip header

        String line;
        int idCounter = 1;

        while ((line = br.readLine()) != null) {

            String[] f = line.split(",", -1);
            if (f.length < 15) continue;

            String id = "P" + idCounter++;

            PatientRecord r = new PatientRecord(
                    id,
                    f[0].trim(),
                    parseInt(f[1]),
                    f[2].trim(),
                    f[3].trim(),
                    f[4].trim(),
                    f[5].trim(),
                    f[6].trim(),
                    f[7].trim(),
                    f[8].trim(),
                    parseDouble(f[9]),
                    parseInt(f[10]),
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

    private static int parseInt(String s) {
        try { return Integer.parseInt(s.trim()); }
        catch (Exception e) { return 0; }
    }

    private static double parseDouble(String s) {
        try { return Double.parseDouble(s.trim()); }
        catch (Exception e) { return 0.0; }
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

   //controls the sizes used for the benchmark
    public static PatientRecord[] limit(PatientRecord[] full, int amount) {
        if (amount > full.length) amount = full.length;

        PatientRecord[] out = new PatientRecord[amount];
        for (int i = 0; i < amount; i++) {
            out[i] = full[i];
        }
        return out;
    }
}
