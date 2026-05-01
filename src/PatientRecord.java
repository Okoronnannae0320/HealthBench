package healthbench.model;

public class PatientRecord {

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

    public String getId() { return id; }
    public int getAge() { return age; }
    public String getAdmissionType() { return admissionType; }
}
