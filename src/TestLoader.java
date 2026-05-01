import healthbench.util.DatasetLoader;
import healthbench.model.PatientRecord;

public class TestLoader {
    public static void main(String[] args) throws Exception {

        PatientRecord[] records = DatasetLoader.loadRecords("dataset/healthcare_dataset.csv");


        System.out.println("Loaded: " + records.length + " records");

        // Print first 5 records
        for (int i = 0; i < 5; i++) {
            System.out.println(records[i].getId() + " - " + records[i].getAdmissionType());
        }
    }
}

