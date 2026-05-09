// PatientRecord represents one standardized row from the healthcare dataset.
public class PatientRecord {

    // Store the dataset fields needed by the application and report.
    private String id;
    private String name;
    private int age;
    private String gender;
    private String bloodType;
    private String medicalCondition;
    private String dateOfAdmission;
    private String doctor;
    private String hospital;
    private String insuranceProvider;
    private double billingAmount;
    private int roomNumber;
    private String admissionType;
    private String dischargeDate;
    private String medication;
    private String testResults;

    // Construct a complete patient record from parsed CSV values.
    public PatientRecord(String id, String name, int age, String gender,
                         String bloodType, String medicalCondition,
                         String dateOfAdmission, String doctor, String hospital,
                         String insuranceProvider, double billingAmount,
                         int roomNumber, String admissionType,
                         String dischargeDate, String medication,
                         String testResults) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bloodType = bloodType;
        this.medicalCondition = medicalCondition;
        this.dateOfAdmission = dateOfAdmission;
        this.doctor = doctor;
        this.hospital = hospital;
        this.insuranceProvider = insuranceProvider;
        this.billingAmount = billingAmount;
        this.roomNumber = roomNumber;
        this.admissionType = admissionType;
        this.dischargeDate = dischargeDate;
        this.medication = medication;
        this.testResults = testResults;
    }

    // Getters let every data structure read record values without changing them.
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getBloodType() { return bloodType; }
    public String getMedicalCondition() { return medicalCondition; }
    public String getDateOfAdmission() { return dateOfAdmission; }
    public String getDoctor() { return doctor; }
    public String getHospital() { return hospital; }
    public String getInsuranceProvider() { return insuranceProvider; }
    public double getBillingAmount() { return billingAmount; }
    public int getRoomNumber() { return roomNumber; }
    public String getAdmissionType() { return admissionType; }
    public String getDischargeDate() { return dischargeDate; }
    public String getMedication() { return medication; }
    public String getTestResults() { return testResults; }

    // Print a compact record summary for console output.
    @Override
    public String toString() {
        return id + " | " + age + " | " + gender + " | " +
                medicalCondition + " | " + hospital + " | " +
                admissionType + " | $" + billingAmount;
    }
}
