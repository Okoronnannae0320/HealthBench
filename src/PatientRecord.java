package healthbench.model;

/**
 * PatientRecord.java
 * ====================
 * SHARED FOUNDATION — Used by ALL team members' data structures.
 *
 * Represents a single patient record from the Kaggle Healthcare Dataset.
 * Each row in the CSV is converted into one PatientRecord object.
 * All 15 columns from the dataset are captured.
 *
 * Dataset: https://www.kaggle.com/datasets/prasad22/healthcare-dataset
 *
 * @author Code Surgeons (Elias Okoro, Ange Kongo, Acka Bossombian)
 * @course COSC 214 — Data Structures and Algorithms
 * @instructor Dr. Ruth Agada
 * @date April 2026
 */
public class PatientRecord {

    // ─── Fields (All 15 CSV Columns + Generated ID) ──────────────
    private String patientId;          // Generated: "P00001", "P00002", etc.
    private String name;               // Col 0  — Patient full name
    private int age;                   // Col 1  — Age in years
    private String gender;             // Col 2  — "Male" or "Female"
    private String bloodType;          // Col 3  — "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    private String medicalCondition;   // Col 4  — "Diabetes", "Asthma", "Obesity", "Arthritis", "Cancer", "Hypertension"
    private String dateOfAdmission;    // Col 5  — Date string (e.g., "2024-03-15")
    private String doctor;             // Col 6  — Doctor name
    private String hospital;           // Col 7  — Hospital name (may contain commas)
    private String insuranceProvider;  // Col 8  — "Aetna", "Blue Cross", "Cigna", "UnitedHealthcare", "Medicare"
    private double billingAmount;      // Col 9  — Dollar amount (e.g., 18856.28)
    private int roomNumber;            // Col 10 — Room number (integer)
    private String admissionType;      // Col 11 — "Emergency", "Urgent", or "Elective"
    private String dischargeDate;      // Col 12 — Date string
    private String medication;         // Col 13 — "Aspirin", "Ibuprofen", "Lipitor", "Paracetamol", "Penicillin"
    private String testResults;        // Col 14 — "Normal", "Abnormal", or "Inconclusive"

    // ─── Constructor ─────────────────────────────────────────────
    public PatientRecord(String patientId, String name, int age, String gender,
                         String bloodType, String medicalCondition,
                         String dateOfAdmission, String doctor, String hospital,
                         String insuranceProvider, double billingAmount,
                         int roomNumber, String admissionType,
                         String dischargeDate, String medication,
                         String testResults) {
        this.patientId = patientId;
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

    // ─── Getters ─────────────────────────────────────────────────
    public String getPatientId()        { return patientId; }
    public String getName()             { return name; }
    public int getAge()                 { return age; }
    public String getGender()           { return gender; }
    public String getBloodType()        { return bloodType; }
    public String getMedicalCondition() { return medicalCondition; }
    public String getDateOfAdmission()  { return dateOfAdmission; }
    public String getDoctor()           { return doctor; }
    public String getHospital()         { return hospital; }
    public String getInsuranceProvider(){ return insuranceProvider; }
    public double getBillingAmount()    { return billingAmount; }
    public int getRoomNumber()          { return roomNumber; }
    public String getAdmissionType()    { return admissionType; }
    public String getDischargeDate()    { return dischargeDate; }
    public String getMedication()       { return medication; }
    public String getTestResults()      { return testResults; }

    // ─── Setters ─────────────────────────────────────────────────
    public void setPatientId(String patientId)               { this.patientId = patientId; }
    public void setName(String name)                         { this.name = name; }
    public void setAge(int age)                              { this.age = age; }
    public void setGender(String gender)                     { this.gender = gender; }
    public void setBloodType(String bloodType)               { this.bloodType = bloodType; }
    public void setMedicalCondition(String medicalCondition) { this.medicalCondition = medicalCondition; }
    public void setDateOfAdmission(String dateOfAdmission)   { this.dateOfAdmission = dateOfAdmission; }
    public void setDoctor(String doctor)                     { this.doctor = doctor; }
    public void setHospital(String hospital)                 { this.hospital = hospital; }
    public void setInsuranceProvider(String insuranceProvider){ this.insuranceProvider = insuranceProvider; }
    public void setBillingAmount(double billingAmount)       { this.billingAmount = billingAmount; }
    public void setRoomNumber(int roomNumber)                { this.roomNumber = roomNumber; }
    public void setAdmissionType(String admissionType)       { this.admissionType = admissionType; }
    public void setDischargeDate(String dischargeDate)       { this.dischargeDate = dischargeDate; }
    public void setMedication(String medication)             { this.medication = medication; }
    public void setTestResults(String testResults)           { this.testResults = testResults; }

    // ─── Utility Methods ─────────────────────────────────────────

    @Override
    public String toString() {
        return "PatientRecord{" +
                "id='" + patientId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", blood='" + bloodType + '\'' +
                ", condition='" + medicalCondition + '\'' +
                ", admission='" + admissionType + '\'' +
                ", hospital='" + hospital + '\'' +
                ", billing=" + String.format("%.2f", billingAmount) +
                ", room=" + roomNumber +
                ", medication='" + medication + '\'' +
                ", testResults='" + testResults + '\'' +
                '}';
    }

    /** Two PatientRecords are equal if they share the same patientId. */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PatientRecord other = (PatientRecord) obj;
        return this.patientId != null && this.patientId.equals(other.patientId);
    }

    /** Hash code based on patientId — critical for the Hash-Based Structure. */
    @Override
    public int hashCode() {
        return patientId != null ? patientId.hashCode() : 0;
    }
}
