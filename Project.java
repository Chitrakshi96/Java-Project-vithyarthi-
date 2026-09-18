import java.io.*;
import java.util.*;

/*
 * HOSPITAL MANAGEMENT SYSTEM
 * Console-based Core Java Project
 *
 * Demonstrates:
 * 1. Abstraction
 * 2. Inheritance
 * 3. Encapsulation
 * 4. Polymorphism
 * 5. Custom Exception Handling
 * 6. File Handling
 */

public class HospitalManagementSystem {

    // =========================
    // ABSTRACT PERSON CLASS
    // =========================
    static abstract class Person {
        protected String id, name, gender, contact;
        protected int age;

        public Person(String id, String name, int age,
                      String gender, String contact) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.contact = contact;
        }

        public String getId() {
            return id;
        }

        public abstract void displayDetails();
    }

    // =========================
    // DOCTOR CLASS - INHERITANCE
    // =========================
    static class Doctor extends Person {
        private String specialization;
        private String availableSlots;

        public Doctor(String id, String name, int age, String gender,
                       String contact, String specialization,
                       String availableSlots) {
            super(id, name, age, gender, contact);
            this.specialization = specialization;
            this.availableSlots = availableSlots;
        }

        public String getSpecialization() {
            return specialization;
        }

        public String getAvailableSlots() {
            return availableSlots;
        }

        @Override
        public void displayDetails() {
            System.out.println("----------------------------------------");
            System.out.println("Doctor ID       : " + id);
            System.out.println("Name            : " + name);
            System.out.println("Age             : " + age);
            System.out.println("Gender          : " + gender);
            System.out.println("Contact         : " + contact);
            System.out.println("Specialization  : " + specialization);
            System.out.println("Available Slots : " + availableSlots);
            System.out.println("----------------------------------------");
        }
    }

    // =========================
    // PATIENT CLASS - INHERITANCE
    // =========================
    static class Patient extends Person {
        private String disease;
        private String address;

        public Patient(String id, String name, int age, String gender,
                       String contact, String disease, String address) {
            super(id, name, age, gender, contact);
            this.disease = disease;
            this.address = address;
        }

        @Override
        public void displayDetails() {
            System.out.println("----------------------------------------");
            System.out.println("Patient ID          : " + id);
            System.out.println("Name                : " + name);
            System.out.println("Age                 : " + age);
            System.out.println("Gender              : " + gender);
            System.out.println("Contact             : " + contact);
            System.out.println("Disease / Symptoms  : " + disease);
            System.out.println("Address             : " + address);
            System.out.println("----------------------------------------");
        }
    }

    // =========================
    // APPOINTMENT CLASS
    // =========================
    static class Appointment {
        private String appointmentId;
        private String patientId;
        private String doctorId;
        private String date;
        private String time;
        private String status;

        public Appointment(String appointmentId, String patientId,
                           String doctorId, String date,
                           String time, String status) {
            this.appointmentId = appointmentId;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.date = date;
            this.time = time;
            this.status = status;
        }

        public String getAppointmentId() {
            return appointmentId;
        }

        public String getPatientId() {
            return patientId;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void displayDetails() {
            System.out.println("----------------------------------------");
            System.out.println("Appointment ID : " + appointmentId);
            System.out.println("Patient ID     : " + patientId);
            System.out.println("Doctor ID      : " + doctorId);
            System.out.println("Date           : " + date);
            System.out.println("Time           : " + time);
            System.out.println("Status         : " + status);
            System.out.println("----------------------------------------");
        }
    }

    // =========================
    // CUSTOM EXCEPTIONS
    // =========================
    static class HospitalException extends Exception {
        public HospitalException(String message) {
            super(message);
        }
    }

    static class RecordNotFoundException extends HospitalException {
        public RecordNotFoundException(String message) {
            super(message);
        }
    }

    static class DoctorUnavailableException extends HospitalException {
        public DoctorUnavailableException(String message) {
            super(message);
        }
    }

    static class InvalidAppointmentException extends HospitalException {
        public InvalidAppointmentException(String message) {
            super(message);
        }
    }

    // =========================
    // FILE HANDLING
    // =========================
    static class FileManager {
        private static final String DOCTOR_FILE = "doctors.txt";
        private static final String PATIENT_FILE = "patients.txt";
        private static final String APPOINTMENT_FILE = "appointments.txt";

        public static void saveDoctors(ArrayList<Doctor> doctors)
                throws IOException {
            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(DOCTOR_FILE))) {

                for (Doctor d : doctors) {
                    writer.write(d.id + "," + d.name + "," + d.age + ","
                            + d.gender + "," + d.contact + ","
                            + d.specialization + "," + d.availableSlots);
                    writer.newLine();
                }
            }
        }

        public static void savePatients(ArrayList<Patient> patients)
                throws IOException {
            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(PATIENT_FILE))) {

                for (Patient p : patients) {
                    writer.write(p.id + "," + p.name + "," + p.age + ","
                            + p.gender + "," + p.contact + ","
                            + p.disease + "," + p.address);
                    writer.newLine();
                }
            }
        }

        public static void saveAppointments(
                ArrayList<Appointment> appointments) throws IOException {

            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(APPOINTMENT_FILE))) {

                for (Appointment a : appointments) {
                    writer.write(a.appointmentId + "," + a.patientId + ","
                            + a.doctorId + "," + a.date + ","
                            + a.time + "," + a.status);
                    writer.newLine();
                }
            }
        }

        public static void loadDoctors(ArrayList<Doctor> doctors)
                throws IOException {

            File file = new File(DOCTOR_FILE);
            if (!file.exists()) return;

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",", -1);

                    if (data.length >= 7) {
                        try {
                            doctors.add(new Doctor(
                                    data[0],
                                    data[1],
                                    Integer.parseInt(data[2]),
                                    data[3],
                                    data[4],
                                    data[5],
                                    data[6]
                            ));
                        } catch (NumberFormatException ignored) {
                            System.out.println(
                                    "Skipped invalid doctor record.");
                        }
                    }
                }
            }
        }

        public static void loadPatients(ArrayList<Patient> patients)
                throws IOException {

            File file = new File(PATIENT_FILE);
            if (!file.exists()) return;

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",", -1);

                    if (data.length >= 7) {
                        try {
                            patients.add(new Patient(
                                    data[0],
                                    data[1],
                                    Integer.parseInt(data[2]),
                                    data[3],
                                    data[4],
                                    data[5],
                                    data[6]
                            ));
                        } catch (NumberFormatException ignored) {
                            System.out.println(
                                    "Skipped invalid patient record.");
                        }
                    }
                }
            }
        }

        public static void loadAppointments(
                ArrayList<Appointment> appointments) throws IOException {

            File file = new File(APPOINTMENT_FILE);
            if (!file.exists()) return;

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",", -1);

                    if (data.length >= 6) {
                        appointments.add(new Appointment(
                                data[0], data[1], data[2],
                                data[3], data[4], data[5]
                        ));
                    }
                }
            }
        }
    }

    // =========================
    // MAIN DATA
    // =========================
    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();

    static int appointmentCounter = 1;

    // =========================
    // MAIN METHOD
    // =========================
    public static void main(String[] args) {

        loadData();

        System.out.println("\n==========================================");
        System.out.println("       HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        boolean running = true;

        while (running) {

            displayMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        addDoctor();
                        break;

                    case 2:
                        addPatient();
                        break;

                    case 3:
                        bookAppointment();
                        break;

                    case 4:
                        cancelAppointment();
                        break;

                    case 5:
                        viewAllAppointments();
                        break;

                    case 6:
                        viewAllPatients();
                        break;

                    case 7:
                        viewAllDoctors();
                        break;

                    case 8:
                        updateAppointmentStatus();
                        break;

                    case 9:
                        saveData();
                        running = false;
                        System.out.println(
                                "Thank you for using the system.");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please enter 1-9.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number.");

            } catch (HospitalException e) {

                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // =========================
    // MENU
    // =========================
    static void displayMenu() {

        System.out.println("\n==========================================");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Patient");
        System.out.println("3. Book Appointment");
        System.out.println("4. Cancel Appointment");
        System.out.println("5. View All Appointments");
        System.out.println("6. View All Patients");
        System.out.println("7. View All Doctors");
        System.out.println("8. Update Appointment Status");
        System.out.println("9. Save & Exit");
        System.out.println("==========================================");
        System.out.print("Enter choice: ");
    }

    // =========================
    // ADD DOCTOR
    // =========================
    static void addDoctor() throws HospitalException {

        System.out.println("\n--- ADD DOCTOR ---");

        System.out.print("Enter Doctor ID       : ");
        String id = scanner.nextLine().trim();

        if (findDoctor(id) != null) {
            throw new HospitalException(
                    "Doctor ID already exists.");
        }

        System.out.print("Enter Name            : ");
        String name = scanner.nextLine();

        int age = readAge();

        System.out.print("Enter Gender          : ");
        String gender = scanner.nextLine();

        System.out.print("Enter Contact         : ");
        String contact = scanner.nextLine();

        System.out.print("Enter Specialization  : ");
        String specialization = scanner.nextLine();

        System.out.print("Enter Available Slots : ");
        String slots = scanner.nextLine();

        doctors.add(new Doctor(
                id, name, age, gender, contact,
                specialization, slots
        ));

        System.out.println("Doctor added successfully.");
    }

    // =========================
    // ADD PATIENT
    // =========================
    static void addPatient() throws HospitalException {

        System.out.println("\n--- ADD PATIENT ---");

        System.out.print("Enter Patient ID      : ");
        String id = scanner.nextLine().trim();

        if (findPatient(id) != null) {
            throw new HospitalException(
                    "Patient ID already exists.");
        }

        System.out.print("Enter Name            : ");
        String name = scanner.nextLine();

        int age = readAge();

        System.out.print("Enter Gender          : ");
        String gender = scanner.nextLine();

        System.out.print("Enter Contact         : ");
        String contact = scanner.nextLine();

        System.out.print("Enter Disease/Symptoms: ");
        String disease = scanner.nextLine();

        System.out.print("Enter Address         : ");
        String address = scanner.nextLine();

        patients.add(new Patient(
                id, name, age, gender,
                contact, disease, address
        ));

        System.out.println("Patient added successfully.");
    }

    // =========================
    // BOOK APPOINTMENT
    // =========================
    static void bookAppointment() throws HospitalException {

        System.out.println("\n--- BOOK APPOINTMENT ---");

        System.out.print("Enter Patient ID : ");
        String patientId = scanner.nextLine().trim();

        if (findPatient(patientId) == null) {
            throw new RecordNotFoundException(
                    "No patient found with ID " + patientId);
        }

        System.out.print("Enter Doctor ID  : ");
        String doctorId = scanner.nextLine().trim();

        if (findDoctor(doctorId) == null) {
            throw new RecordNotFoundException(
                    "No doctor found with ID " + doctorId);
        }

        System.out.print("Enter Date (dd-mm-yyyy) : ");
        String date = scanner.nextLine().trim();

        System.out.print("Enter Time (HH:mm)      : ");
        String time = scanner.nextLine().trim();

        validateDate(date);
        validateTime(time);

        for (Appointment a : appointments) {

            if (a.getDoctorId().equalsIgnoreCase(doctorId)
                    && a.getDate().equals(date)
                    && a.getTime().equals(time)
                    && a.getStatus().equalsIgnoreCase("BOOKED")) {

                throw new DoctorUnavailableException(
                        "Doctor " + doctorId
                        + " is already booked at "
                        + time + " on " + date + ".");
            }
        }

        String appointmentId = "A" + appointmentCounter++;

        appointments.add(new Appointment(
                appointmentId,
                patientId,
                doctorId,
                date,
                time,
                "BOOKED"
        ));

        System.out.println(
                "Appointment booked successfully.");
        System.out.println(
                "Appointment ID: " + appointmentId);
    }

    // =========================
    // CANCEL APPOINTMENT
    // =========================
    static void cancelAppointment() throws HospitalException {

        System.out.println("\n--- CANCEL APPOINTMENT ---");

        System.out.print("Enter Appointment ID : ");
        String id = scanner.nextLine().trim();

        Appointment appointment = findAppointment(id);

        if (appointment == null) {
            throw new RecordNotFoundException(
                    "No appointment found with ID " + id);
        }

        appointment.setStatus("CANCELLED");

        System.out.println(
                "Appointment cancelled successfully.");
    }

    // =========================
    // UPDATE STATUS
    // =========================
    static void updateAppointmentStatus()
            throws HospitalException {

        System.out.println("\n--- UPDATE APPOINTMENT STATUS ---");

        System.out.print("Enter Appointment ID : ");
        String id = scanner.nextLine().trim();

        Appointment appointment = findAppointment(id);

        if (appointment == null) {
            throw new RecordNotFoundException(
                    "No appointment found with ID " + id);
        }

        System.out.print(
                "Enter Status (COMPLETED/CANCELLED): ");

        String status =
                scanner.nextLine().trim().toUpperCase();

        if (!status.equals("COMPLETED")
                && !status.equals("CANCELLED")) {

            throw new InvalidAppointmentException(
                    "Invalid status. Use COMPLETED or CANCELLED.");
        }

        appointment.setStatus(status);

        System.out.println(
                "Appointment status updated successfully.");
    }

    // =========================
    // VIEW APPOINTMENTS
    // =========================
    static void viewAllAppointments() {

        System.out.println("\n--- ALL APPOINTMENTS ---");

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (Appointment a : appointments) {
            a.displayDetails();
        }
    }

    // =========================
    // VIEW PATIENTS
    // =========================
    static void viewAllPatients() {

        System.out.println("\n--- ALL PATIENTS ---");

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        // Polymorphism:
        // Person reference points to Patient object.
        for (Person p : patients) {
            p.displayDetails();
        }
    }

    // =========================
    // VIEW DOCTORS
    // =========================
    static void viewAllDoctors() {

        System.out.println("\n--- ALL DOCTORS ---");

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        // Polymorphism:
        // Person reference points to Doctor object.
        for (Person p : doctors) {
            p.displayDetails();
        }
    }

    // =========================
    // SEARCH METHODS
    // =========================
    static Doctor findDoctor(String id) {

        for (Doctor d : doctors) {
            if (d.getId().equalsIgnoreCase(id)) {
                return d;
            }
        }

        return null;
    }

    static Patient findPatient(String id) {

        for (Patient p : patients) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }

        return null;
    }

    static Appointment findAppointment(String id) {

        for (Appointment a : appointments) {
            if (a.getAppointmentId()
                    .equalsIgnoreCase(id)) {
                return a;
            }
        }

        return null;
    }

    // =========================
    // INPUT VALIDATION
    // =========================
    static int readAge() {

        while (true) {

            try {

                System.out.print("Enter Age             : ");

                int age = Integer.parseInt(
                        scanner.nextLine().trim());

                if (age < 0 || age > 120) {
                    System.out.println(
                            "Enter age between 0 and 120.");
                    continue;
                }

                return age;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number.");
            }
        }
    }

    static void validateDate(String date)
            throws InvalidAppointmentException {

        if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {

            throw new InvalidAppointmentException(
                    "Invalid date. Use dd-mm-yyyy format.");
        }

        String[] parts = date.split("-");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        if (day < 1 || day > 31
                || month < 1 || month > 12) {

            throw new InvalidAppointmentException(
                    "Invalid day or month.");
        }
    }

    static void validateTime(String time)
            throws InvalidAppointmentException {

        if (!time.matches("\\d{2}:\\d{2}")) {

            throw new InvalidAppointmentException(
                    "Invalid time. Use HH:mm format.");
        }

        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        if (hour < 0 || hour > 23
                || minute < 0 || minute > 59) {

            throw new InvalidAppointmentException(
                    "Invalid time. Use HH:mm format.");
        }
    }

    // =========================
    // SAVE DATA
    // =========================
    static void saveData() {

        try {

            FileManager.saveDoctors(doctors);
            FileManager.savePatients(patients);
            FileManager.saveAppointments(appointments);

            System.out.println(
                    "All records saved successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Error saving data: " + e.getMessage());
        }
    }

    // =========================
    // LOAD DATA
    // =========================
    static void loadData() {

        try {

            FileManager.loadDoctors(doctors);
            FileManager.loadPatients(patients);
            FileManager.loadAppointments(appointments);

            // Continue appointment IDs after restarting.
            for (Appointment a : appointments) {

                String id = a.getAppointmentId();

                if (id.length() > 1
                        && id.substring(1).matches("\\d+")) {

                    int number =
                            Integer.parseInt(id.substring(1));

                    appointmentCounter =
                            Math.max(appointmentCounter,
                                    number + 1);
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error loading saved records: "
                    + e.getMessage());
        }
    }
}
