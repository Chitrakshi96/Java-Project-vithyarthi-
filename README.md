# Hospital Management System (Java)

A console-based Hospital Management System built in core Java. It demonstrates
object-oriented programming (inheritance), custom exception handling, and file
handling. Hospital staff can register doctors and patients, book and manage
appointments, and all data is saved to text files so it persists between runs.

---

## 1. Overview

- **Language:** Java (core Java only — no external libraries or frameworks)
- **Interface:** Console / terminal (menu-driven)
- **Data storage:** Plain `.txt` files (no database required)
- **Requires:** Java Development Kit (JDK) 14 or later

---

## 2. Prerequisites

You only need the **Java Development Kit (JDK)** installed. Nothing else —
no database, no build tool, no internet connection is required to run this
project.

### Check if Java is already installed
Open a terminal (Command Prompt / PowerShell on Windows, Terminal on macOS/Linux) and run:
```bash
java -version
javac -version
```
If both commands print a version number **14 or higher**, skip to [Section 4](#4-project-setup).
If you get a "command not found" error, install the JDK using the steps below.

### Installing the JDK

**Windows:**
1. Download the JDK installer from [https://adoptium.net](https://adoptium.net) (choose the latest LTS version, e.g., JDK 17 or 21).
2. Run the installer and follow the prompts (keep default options).
3. During installation, ensure the option **"Add to PATH"** is checked.
4. Restart your terminal and verify with `java -version`.

**macOS:**
1. Install [Homebrew](https://brew.sh) if you don't already have it.
2. Run:
   ```bash
   brew install openjdk@17
   ```
3. Follow the on-screen instructions to link it to your PATH (Homebrew will print the exact command, usually something like):
   ```bash
   echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
   source ~/.zshrc
   ```
4. Verify with `java -version`.

**Linux (Debian/Ubuntu):**
```bash
sudo apt update
sudo apt install openjdk-17-jdk -y
java -version
```

**Linux (Fedora/RHEL):**
```bash
sudo dnf install java-17-openjdk-devel -y
java -version
```

---

## 3. Dependencies

**None.** This project uses only the Java Standard Library (`java.util`,
`java.io`). There is no `pom.xml`, `build.gradle`, or `package.json` — nothing
extra needs to be downloaded or installed beyond the JDK itself.

---

## 4. Project Setup

1. Download or clone this repository to your computer.
2. Extract it (if zipped) to a folder of your choice.
3. Confirm the folder structure looks like this:
   ```
   HospitalManagementSystem/
   ├── hospital/
   │   ├── Person.java
   │   ├── Doctor.java
   │   ├── Patient.java
   │   ├── Appointment.java
   │   ├── HospitalException.java
   │   ├── InvalidAppointmentException.java
   │   ├── DoctorUnavailableException.java
   │   ├── RecordNotFoundException.java
   │   ├── FileManager.java
   │   └── HospitalManagementSystem.java
   └── README.md
   ```
4. No configuration files, API keys, or environment variables are needed.

---

## 5. How to Compile

Open a terminal and navigate (`cd`) into the **`HospitalManagementSystem`**
folder — the one that contains the `hospital` subfolder (not inside it):

```bash
cd path/to/HospitalManagementSystem
```

Compile all Java files with a single command:
```bash
javac hospital/*.java
```

If compilation succeeds, you will see no output and a set of new `.class`
files will appear inside the `hospital` folder. If you see errors, confirm
you are running JDK 14+ (`java -version`) and that you are in the correct
folder.

---

## 6. How to Run

From the same folder (**`HospitalManagementSystem`**, not inside `hospital`), run:

```bash
java hospital.HospitalManagementSystem
```

You should see a menu appear in the terminal:
```
===== HOSPITAL MANAGEMENT SYSTEM =====
1. Add Doctor
2. Add Patient
3. Book Appointment
4. Cancel Appointment
5. View All Appointments
6. View All Patients
7. View All Doctors
8. Update Appointment Status
9. Save & Exit
Enter choice:
```

---

## 7. Using the Application

Enter the number corresponding to the action you want, then follow the
prompts. A typical first-time workflow:

1. Choose **1** to add a doctor (enter an ID like `D1`, name, age, gender, contact, specialization, available slots).
2. Choose **2** to add a patient (enter an ID like `P1`, name, age, gender, contact, disease/symptoms, address).
3. Choose **3** to book an appointment — you'll be asked for the patient ID, doctor ID, date (`dd-mm-yyyy`), and time (`HH:mm`).
4. Choose **5** to view all appointments and confirm it was booked.
5. Choose **8** to mark an appointment as `COMPLETED` or `CANCELLED`, or choose **4** to cancel it directly.
6. Choose **9** to save all data and exit.

Notes:
- IDs (`D1`, `P1`, `A1`, etc.) must be entered manually and should be unique — the system does not auto-generate patient/doctor IDs, only appointment IDs.
- The system will show a clear error message (instead of crashing) if you enter an ID that doesn't exist, try to double-book a doctor at the same date/time, or enter an invalid status.

---

## 8. Data Storage (File Handling)

When you choose **Save & Exit**, three text files are created/updated in the
same folder you ran the program from:

| File | Contents |
|---|---|
| `patients.txt` | One line per patient (comma-separated fields) |
| `doctors.txt` | One line per doctor (comma-separated fields) |
| `appointments.txt` | One line per appointment (comma-separated fields) |

These files are created automatically — no manual setup needed. The next
time you run the program, it automatically reads these files and reloads
all previously saved records, so your data persists across sessions.

---

## 9. Troubleshooting

| Problem | Likely Cause / Fix |
|---|---|
| `javac: command not found` | JDK is not installed or not added to PATH. Reinstall and check "Add to PATH". |
| `error: package hospital does not exist` or class not found | You ran the command from inside the `hospital` folder instead of its parent. `cd ..` and try again. |
| `Unsupported class file version` | Your `java` and `javac` versions differ, or your JDK is older than 14. Update the JDK. |
| Program starts with no doctors/patients | This is normal on first run — the `.txt` files don't exist yet. Add records via the menu. |
| Changes seem lost after restarting | Make sure you chose **9. Save & Exit** rather than force-closing the terminal window. |

---

## 10. Project Structure Summary

| File | Purpose |
|---|---|
| `Person.java` | Abstract base class with shared fields (id, name, age, gender, contact) |
| `Doctor.java` | Extends `Person`; adds specialization and available slots |
| `Patient.java` | Extends `Person`; adds disease/symptoms and address |
| `Appointment.java` | Represents a booking between a patient and a doctor |
| `HospitalException.java` | Base custom checked exception |
| `InvalidAppointmentException.java` | Thrown for invalid date/time/status input |
| `DoctorUnavailableException.java` | Thrown when a doctor is already booked at that time |
| `RecordNotFoundException.java` | Thrown when a patient/doctor/appointment ID doesn't exist |
| `FileManager.java` | Reads and writes patients, doctors, and appointments to text files |
| `HospitalManagementSystem.java` | Main class with the menu-driven console interface |
