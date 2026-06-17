import java.sql.*;
import java.util.Scanner;

public class QuestionManager {

    public static void addQuestion() {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter Subject ID: ");
            int subjectId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Difficulty (Easy/Medium/Hard): ");
String difficulty = sc.nextLine();

System.out.print("Enter Marks: ");
int marks = Integer.parseInt(sc.nextLine());

System.out.print("Enter Question: ");
String question = sc.nextLine();
            Connection con = DBConnection.getConnection();

            String sql =
        "INSERT INTO questions(subject_id,difficulty,marks,question_text) VALUES(?,?,?,?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, subjectId);
            pst.setString(2, difficulty);
            pst.setInt(3, marks);
pst.setString(4, question);
            pst.executeUpdate();

            System.out.println("Question Added Successfully");
            AuditManager.log("Added Question");
            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    public static void viewQuestions() {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
    "SELECT q.question_id, s.subject_name, q.difficulty, q.marks, q.question_text " +
    "FROM questions q JOIN subjects s " +
    "ON q.subject_id=s.subject_id";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\nQUESTION BANK");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("question_id")
                        + " | "
                        + rs.getString("subject_name")
                        + " | "
                        + rs.getString("difficulty")
                        + " | "
                        + rs.getInt("marks")
+ " Marks | "
                        + rs.getString("question_text")
                );

            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

    }
}
