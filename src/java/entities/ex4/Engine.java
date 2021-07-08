package entities.ex4;

import entities.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.*;

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
            System.out.println("Welcome to console GP interface!");
            System.out.println("Please choose operation:");
            System.out.print("0. EXIT\n" +
                    "1. Add new patient\n" +
                    "2. Add new medicament\n" +
                    "3. Add visitation\n" +
                    "4. Add diagnose\n" +
                    "5. Search patient info by full name (First + Last)\n" +
                    "6. Search visited patients by visitation date \n");
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
                        System.out.print("Medicament name: ");
                        String name = sc.nextLine();
                        addMedicament(name);
                        break;
                    case 3:
                        try {
                            addVisitation(sc);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case  4:
                        addDiagnose(sc);
                          break;
                    case 5:
                        try {
                                printFullPatientInfo(sc);
                        }     catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        try {
                            listVisitations(sc);

                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                }
            }

        }
    }

    private void listVisitations(Scanner sc) {
        System.out.print("Choose date (YYYY-M-D): ");
        int[] dateDate = Arrays.stream(sc.nextLine().split("-")).mapToInt(Integer::parseInt).toArray();
        LocalDateTime dateTime = LocalDateTime.of(dateDate[0],dateDate[1],dateDate[2],12,12)        ;
                TypedQuery<Visitation> visitationTypedQuery = getEm().createQuery("SELECT v FROM Visitation AS v WHERE v.date = ?1",Visitation.class);
                List<Visitation> visitations = visitationTypedQuery.setParameter(1,dateTime).getResultList();
                if (visitations.size() > 0) {
                    visitations.forEach(visitation -> {
                        System.out.printf("%s %s\n", visitation.getPatient().getFirstName(), visitation.getPatient().getLastName());
                    });
                }

    }

    private void printFullPatientInfo(Scanner sc) throws Exception {
        System.out.print("Enter full name: ");
          String[] fullName = sc.nextLine().split("\\s+");
          Patient patient = getPatient(fullName);
          if (patient == null){
              throw new Exception("Patient does not exist! Please add patient first!");
          }
          System.out.printf("Patient name: %s %s\n" +
                  "Patient date of birth: %s/%s/%s\n",patient.getFirstName(),patient.getLastName(),patient.getDateOfBirth().getDayOfMonth(),patient.getDateOfBirth().getMonth(),patient.getDateOfBirth().getYear());
          System.out.println("*************************************");
          System.out.println("List of visitations:");
          if (patient.getVisitations() != null) {
              patient.getVisitations().forEach(v -> {
                  System.out.printf("   Date visited: %s/%s/%s\n" +
                          "   Diagnose: %s\n", v.getDate().getDayOfMonth(), v.getDate().getMonth(), v.getDate().getYear(), v.getDiagnose().getName());
                  System.out.println("          List ot medicaments:");
                  v.getDiagnose().getMedicaments().forEach(m -> {
                      System.out.printf("                       %s\n", m.getName());
                  });
                  System.out.println("--------------------------------");
              });
          }
    }

    private void addDiagnose(Scanner sc) {
        System.out.print("Diagnose name: ");
        String name = sc.nextLine();
        System.out.print("Comments: ");
        String comments = sc.nextLine();
        Diagnose diagnose = new Diagnose(name,comments);
        insert(diagnose);
    }

    private void addVisitation(Scanner sc) throws Exception {
        getEm().getTransaction().begin();
        System.out.print("Patient full name: ");
        String[] fullName = sc.nextLine().split(" ");
        Patient patient = getPatient(fullName); // patient to be visited
        if (patient == null){
           throw new Exception("Patient does not exist! Please add patient first!")  ;
        }
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
        getEm().persist(diagnose);
        System.out.print("Medicaments needed(split with ,): ");
        String[] medicaments = sc.nextLine().trim().split(",\\s+");

        Set<Medicament> neededMedicaments = new LinkedHashSet<>();

        for (String currMed:medicaments){
            Medicament medicament = getMedicament(currMed);
            if (medicament == null) {
               medicament  = new Medicament(currMed);
               getEm().persist(medicament);
            }
                if (medicament.getDiagnose() != null){
                    medicament.getDiagnose().add(diagnose);
                } else {
                    Set<Diagnose> diagnoses = new LinkedHashSet<>();
                    diagnoses.add(diagnose);
                    medicament.setDiagnose(diagnoses);
                }

            neededMedicaments.add(getMedicament(currMed));
        }

        if (diagnose.getMedicaments() != null){
            neededMedicaments.forEach(m -> diagnose.getMedicaments().add(m));
        } else {
            diagnose.setMedicaments(neededMedicaments);
        }


        insert(diagnose);
        visitation.setPatient(patient);
        visitation.setDiagnose(diagnose);

        insert(visitation);
        getEm().getTransaction().commit();
    }

    private Diagnose getDiagnose(String name) {
        return getEm().createQuery("SELECT d FROM Diagnose AS d",Diagnose.class)
                .getResultList().stream().filter(d->d.getName().equals(name)).findFirst().orElse(null);
    }

    private Medicament getMedicament(String currMed) {
        return getEm().createQuery("SELECT m FROM Medicament AS m" ,Medicament.class)
                .getResultList().stream().filter(m->m.getName().equals(currMed)).findFirst().orElse(null);
    }

    private Patient getPatient(String[] params) {
        return getEm().createQuery("SELECT p FROM Patient AS p",Patient.class)
               .getResultList().stream().filter(p -> p.getFirstName().equals(params[0]) && p.getLastName().equals(params[1])).findFirst().orElse(null);

    }

    private void addMedicament(String name) {
        Medicament medicament = new Medicament(name);
        insert(medicament);
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

    private void insert(Object obj) {
        try {
            getEm().persist(obj);
            System.out.printf("%s added successfully!\n",obj.getClass().getSimpleName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
