package entities.ex4;

import entities.Exercise;

import javax.persistence.EntityManager;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Engine implements Exercise {
    private EntityManager em;

    public Engine(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    private void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Welcome to console GP interface");
            System.out.println("Please choose operation");
            System.out.print("0. EXIT\n" +
                    "1. Add new patient\n" +
                    "2. Add new medicament\n" +
                    "3. Add visitation\n" +
                    "4. Search patient info by full name (First + Last)\n" +
                    "5. Search visitations by date (YYYY-M-D)\n");
            Scanner sc = new Scanner(System.in);

            int num = Integer.parseInt(sc.nextLine());
            if (num == 0) {
                break;
            } else {
                switch (num) {
                    case 1:
                        addPatient(sc);
                        break;
                    case 2:
                        addMedicament(sc);
                        break;
                    case 3:
                        addVisitation(sc);
                        break;

                }
            }

        }
    }

    private void addVisitation(Scanner sc) {
        getEm().getTransaction().begin();
        System.out.print("Patient full name: ");
        String[] fullName = sc.nextLine().split(" ");
        Patient patient = getPatient(fullName); // patient to be visited

        System.out.print("Date (YYYY-M-D): ");
        int[] dateDate = Arrays.stream(sc.nextLine().split("-")).mapToInt(Integer::parseInt).toArray();
        LocalDateTime dateTime = LocalDateTime.of(dateDate[0],dateDate[1],dateDate[2],12,12);
        System.out.print("Comments: ");
        String comments = sc.nextLine();
        Visitation visitation = new Visitation(dateTime,comments);

        System.out.print("Diagnose name: ");
        String name = sc.nextLine();
        System.out.print("Diagnose comment: ");
        String comment= sc.nextLine();
        Diagnose diagnose = new Diagnose(name,comment);

        System.out.print("Medicaments needed(split with ,): ");
        String[] medicaments = sc.nextLine().trim().split(",");

        Set<Medicament> neededMedicaments = new LinkedHashSet<>();

        for (String currMed:medicaments){
            neededMedicaments.add(getMedicament(currMed));
        }
        diagnose.setMedicaments(neededMedicaments);
        getEm().persist(diagnose);
        visitation.setPatient(patient);
        visitation.setDiagnose(diagnose);

        getEm().persist(visitation);
        getEm().getTransaction().commit();
    }

    private Medicament getMedicament(String currMed) {
        return getEm().createQuery("SELECT m FROM Medicament AS m" ,Medicament.class)
                .getResultList().stream().filter(m->m.getName().equals(currMed)).findFirst().orElse(null);
    }

    private Patient getPatient(String[] params) {
        return getEm().createQuery("SELECT p FROM Patient AS p",Patient.class)
               .getResultList().stream().filter(p -> p.getFirstName().equals(params[0]) && p.getLastName().equals(params[1])).findFirst().orElse(null);

    }

    private void addMedicament(Scanner sc) {
        System.out.print("Medicament name: ");
        String name =sc.nextLine();
        Medicament medicament = new Medicament(name);
        insert(medicament);
    }

    private void insert(Object obj) {
        try {
            getEm().getTransaction().begin();
            if (obj.getClass().equals(Medicament.class)){
                getEm().persist((Medicament) obj);
            } else if (obj.getClass().equals(Patient.class)){
                getEm().persist((Patient) obj);
            }


            getEm().getTransaction().commit();
            System.out.printf("%s added successfully!\n",obj.getClass().getSimpleName());
        } catch (Exception e) {
            System.out.println("Something went wrong! Please try again!");
        }
    }

    private void addPatient(Scanner sc) {
        System.out.print("First name: ");
        String firstName = sc.nextLine();
        System.out.print("Last name: ");
        String lastName = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.println("Date of birth in format (YYYY-M-D):");
        int[] dateData = Arrays.stream(sc.nextLine().split("-")).mapToInt(Integer::parseInt).toArray();
        LocalDateTime dateOfBirth = LocalDateTime.of(dateData[0], dateData[1], dateData[2],12,12);
        System.out.print("Does the patient have medical insurance (yes/no): ");
        String medicalInsurance = sc.nextLine();
        Patient patient = new Patient(firstName, lastName, address, email, dateOfBirth, null, medicalInsurance);

       insert(patient);
    }

}
