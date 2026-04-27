# HealthBench
## Evaluating Data Structures Using a Healthcare Dataset

**Team:** Code Surgeons  
**Members:** Elias Okoro, Ange Kongo, Acka Bossombian  
**Course:** COSC 214 — Data Structures and Algorithms  
**Instructor:** Dr. Ruth Agada  
**University:** Bowie State University  
**Date:** April 2026

---

## 📌 Project Overview

HealthBench is a JavaFX desktop application that loads ~10,000 synthetic patient records
from a Kaggle healthcare dataset, converts each row into a PatientRecord object, and stores
the data in four custom-implemented data structures. Users can insert, search, delete, and
traverse records through a GUI, observe priority-based hospital admission simulation, and
benchmark algorithmic performance across all structures.

**Dataset:** https://www.kaggle.com/datasets/prasad22/healthcare-dataset

---

## ⚠️ CRITICAL CONSTRAINT

> All data structures must be built from scratch — NO Java Collection imports.  
> The ONLY exception is HashMap, which may be imported.  
> ArrayList, Queue, LinkedList, PriorityQueue, etc. must be CUSTOM-IMPLEMENTED.  
> Return types use PatientRecord[] (raw arrays), NOT List<>.

---

## 📁 Project Structure

HealthBench/
└── src/main/java/healthbench/
├── model/
│   └── PatientRecord.java              ← SHARED
├── structures/
│   ├── DataStructure.java              ← SHARED Interface
│   ├── HashBasedStructure.java          ← Elias
│   ├── PatientPriorityQueue.java        ← Elias
│   ├── DynamicArray.java                ← Acka
│   └── PatientLinkedList.java           ← Acka
├── util/
│   └── DatasetLoader.java              ← SHARED
├── gui/                                ← Ange
│   ├── MainWindow.java
│   ├── PriorityQueueWindow.java
│   └── BenchmarkWindow.java
└── benchmark/
└── BenchmarkRunner.java

---

## 📋 All Classes & Who Owns Them

### SHARED (Build First — Everyone Depends on These)

| Class             | Purpose |
|-------------------|---------|
| PatientRecord     | Data model — maps all 15 CSV columns + generated ID |
| DataStructure     | Interface all 4 structures implement |
| DatasetLoader     | CSV parser — returns PatientRecord[] |

### Elias's Classes

| Class                | Purpose |
|----------------------|---------|
| HashBasedStructure   | Custom hash table with separate chaining, O(1) avg ops |
| PatientPriorityQueue | Binary max-heap, Emergency > Urgent > Elective |

### Acka's Classes

| Class              | Purpose |
|--------------------|---------|
| DynamicArray       | Resizable array, auto-doubles capacity, O(1) amortized insert |
| PatientLinkedList   | Singly linked list with node-based traversal |

### Ange's Classes

| Class                 | Purpose |
|-----------------------|---------|
| MainWindow            | JavaFX main interface — load, select, operate |
| PriorityQueueWindow   | Displays Emergency/Urgent/Elective queues |
| BenchmarkWindow       | Timed comparison + bar chart visualization |
| BenchmarkRunner       | Backend timing logic for all 4 structures |

---

## 📊 Dataset — 15 CSV Columns

| Index | Column             | Type   | PatientRecord Field |
|-------|--------------------|--------|---------------------|
| 0     | Name               | String | name                |
| 1     | Age                | int    | age                 |
| 2     | Gender             | String | gender              |
| 3     | Blood Type         | String | bloodType           |
| 4     | Medical Condition  | String | medicalCondition    |
| 5     | Date of Admission  | String | dateOfAdmission     |
| 6     | Doctor             | String | doctor              |
| 7     | Hospital           | String | hospital            |
| 8     | Insurance Provider | String | insuranceProvider   |
| 9     | Billing Amount     | double | billingAmount       |
| 10    | Room Number        | int    | roomNumber          |
| 11    | Admission Type     | String | admissionType       |
| 12    | Discharge Date     | String | dischargeDate       |
| 13    | Medication         | String | medication          |
| 14    | Test Results       | String | testResults         |

---

## ⏱️ Time Complexity Summary

| Operation | Dynamic Array    | Linked List   | Hash-Based | Priority Queue |
|-----------|-----------------|---------------|------------|----------------|
| Insert    | O(1) amortized  | O(1) w/ tail  | O(1) avg   | O(log n)       |
| Search    | O(n)            | O(n)          | O(1) avg   | O(n)           |
| Delete    | O(n)            | O(n)          | O(1) avg   | O(n) + O(log n)|
| Traverse  | O(n)            | O(n)          | O(n + m)   | O(n)           |

---

## 🔑 Algorithms Used

| Algorithm                | Where Used           | Purpose                          |
|--------------------------|----------------------|----------------------------------|
| Linear Search            | DynArray, LinkedList | O(n) patient lookup              |
| Polynomial Rolling Hash  | Hash-Based Structure | O(1) key-to-index mapping        |
| Separate Chaining        | Hash-Based Structure | Collision handling via nodes      |
| Heap Bubble-Up/Down      | Priority Queue       | Maintain max-heap on insert/del  |
| Merge Sort               | Traversal/batch      | Stable O(n log n) sorted output  |

---

## 🏥 Priority Levels

| Admission Type | Priority | Processing Order |
|---------------|----------|-----------------|
| Emergency     | 3        | First            |
| Urgent        | 2        | Second           |
| Elective      | 1        | Last             |

Tiebreaker: older patients (higher age) processed first.

---

## 🔧 System Requirements

- Java 17+
- JavaFX
- IntelliJ IDEA (recommended)
- Git + GitHub
- 4 GB RAM minimum (8 GB recommended)
- 1280x720 display minimum

---

## 📚 References

- Oracle. (2026). JavaFX Documentation. Oracle Corporation.
- Prasad, P. (2026). Healthcare Dataset. Kaggle.
