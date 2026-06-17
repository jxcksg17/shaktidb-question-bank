import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
if(!LoginManager.login()) {

    return;

}
        Scanner sc = new Scanner(System.in);

        while(true) {

           System.out.println("\n===== Secure Question Bank =====");

System.out.println("1. Add Subject");
System.out.println("2. View Subjects");
System.out.println("3. Add Question");
System.out.println("4. View Questions");
System.out.println("5. Generate Question Paper");
System.out.println("6. View Audit Logs");
System.out.println("7. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            sc.nextLine();

            switch(choice) {

    case 1:
        SubjectManager.addSubject();
        break;

    case 2:
        SubjectManager.viewSubjects();
        break;

    case 3:
        QuestionManager.addQuestion();
        break;

    case 4:
        QuestionManager.viewQuestions();
        break;

    case 5:
    PaperGenerator.generatePaper();
    break;

case 6:
    AuditManager.viewLogs();
    break;

case 7:
    System.exit(0);

    default:
        System.out.println("Invalid Choice");
}
        }

    }

}
