                    #HealthBench
#Evaluating Data Structures Using a Healthcare Dataset

Team: Code Surgeons  
Members: Elias Okoro, Ange Kongo, Acka Bossombian  
Course: COSC 214 — Data Structures and Algorithms  
Instructor: Dr. Ruth Agada  
University: Bowie State University  
Date: April 2026

---

# Project Overview
HealthBench is a Java-based performance evaluation system that analyzes
how different data structures behave when processing real-world healthcare data. The 
system loads55,500 synthetic patient records from a Kaggle dataset, converts each row into
a PatientRecord object, and stores the data in four custom-built data structures:
-DynamicArray
-LinkedListStructure
-HashBasedStructure
-PatientPriorityQueue
The backend supports:
- Insert
- Search
- Delete
- Traverse
- Sort (Merge Sort by age)
- Priority-based hospital admission simulation
- Full performance benchmarking

Dataset Source:  
[https://www.kaggle.com/datasets/prasad22/healthcare-dataset](https://www.kaggle.com/datasets/prasad22/healthcare-dataset)

---

# Critical Constraints
- HashMap(only for the hash-based structure)
- Raw arrays
- Custom resizing
- Custom linked nodes
- Custom heap implementation

### 
All traversal methods must return:
PatientRecord[]
---

# Project Structure
HealthBench
── model/
   ── PatientRecord.java

── structures/
   ── DynamicArray.java
   ── LinkedListStructure.java
   ── HashBasedStructure.java
   ── PatientPriorityQueue.java

── util/
   ── CSVUtils.java
   ── DatasetLoader.java

── benchmark/
   ── Benchmark.java

── gui/
   ── MainWindow.java
   ── InsertWindow.java
   ── SearchWindow.java 
   ── DeleteWindow.java
   ── PriorityQueueWindow.java
   ── BenchmarkWindow.java

── HealthBenchApp.java


# Benchmarking
The Benchmark class evaluates each structure using datasets of:

- 5,000
- 10,000
- 20,000
- 30,000

For each structure, it measures:

-Insert Time
- Search Time
- Delete Time
- Sort Time (DynamicArray + LinkedListStructure only)

All times are measured in nanoseconds (ns).
---

# Features Implemented

- Full CSV parsing (55,500 rows)
- Custom data structures (no Java Collections)
- Merge Sort (ascending by age)
- Priority Queue (max-heap)
- Hash table with separate chaining
- Benchmarking engine
- JavaFX GUI for user interaction
- Priority-based hospital admission simulation

---

# How to Run
#Run Benchmark (backend testing)
Code:
java healthbench.benchmark.Benchmark

#Run GUI (frontend)
Code:
java HealthBenchApp

# Notes
-Benchmark is the official evaluation tool for the project.
---

# Conclusion

HealthBench demonstrates how different data structures behave under real-world workloads using 
a large healthcare dataset. By implementing all structures manually and benchmarking them across 
multiple dataset sizes, the project provides a clear comparison of algorithmic performance in 
practical scenarios. This backend forms the foundation for a full JavaFX application that 
supports interactive patient record management and priority-based hospital simulation.

---
