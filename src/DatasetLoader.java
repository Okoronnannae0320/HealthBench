package healthbench.util;

import healthbench.model.PatientRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DatasetLoader.java
 * ===================
 * SHARED UTILITY — Loads the Kaggle Healthcare CSV into PatientRecord objects.
 *
 * NOTE: Ange owns the GUI-integrated version. This standalone loader lets
 * ALL team members test their structures independently.
 *
 * @author Code Surgeons
 */
public class DatasetLoader {

    public static List<PatientRecord> loadRecords(String filePath) throws IOException {
        List<PatientRecord> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine(); // Skip header row
            if (headerLine == null) {
                System.out.println("Warning: CSV file is empty.");
                return records;
            }

            String line;
            int idCounter = 1;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                try {
                    PatientRecord record = parseLine(line, idCounter);
                    if (record != null) {
                        records.add(record);
                        idCounter++;
                    }
                } catch (Exception e) {
                    System.out.println("  Skipping row " + idCounter + ": " + e.getMessage());
                }
            }
        }

        System.out.println("Dataset loaded: " + records.size() + " patient records.");
        return records;
    }

    /**
     * CSV column indices (0-based):
     *   0=Name, 1=Age, 2=Gender, 3=Blood Type, 4=Medical Condition,
     *   5=Date of Admission, 6=Doctor, 7=Hospital, 8=Insurance Provider,
     *   9=Billing Amount, 10=Room Number, 11=Admission Type,
     *   12=Discharge Date, 13=Medication, 14=Test Results
     */
    private static PatientRecord parseLine(String line, int idCounter) {
        String[] fields = line.split(",");
        if (fields.length < 12) return null;

        String patientId = String.format("P%04d", idCounter);
        int age = Integer.parseInt(fields[1].trim());
        String gender = fields[2].trim();
        String medicalCondition = fields[4].trim();
        String dateOfAdmission = fields[5].trim();
        String hospital = fields[7].trim();
        double billingAmount = Double.parseDouble(fields[9].trim());
        String admissionType = fields[11].trim();

        return new PatientRecord(patientId, age, gender, medicalCondition,
                hospital, admissionType, billingAmount, dateOfAdmission);
    }

    /** For manually inserting records through the GUI. */
    public static PatientRecord createPatientRecord(String patientId, int age,
                                                    String gender, String medicalCondition,
                                                    String hospital, String admissionType,
                                                    double billingAmount, String dateOfAdmission) {
        return new PatientRecord(patientId, age, gender, medicalCondition,
                hospital, admissionType, billingAmount, dateOfAdmission);
    }
}
