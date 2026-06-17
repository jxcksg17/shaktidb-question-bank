import java.sql.*;
import java.util.Scanner;

public class PaperGenerator {

    private static int totalMarks = 0;

    public static void generatePaper() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con =
                    DBConnection.getConnection();

            totalMarks = 0;

            System.out.print("Enter Subject ID: ");
            int subjectId =
                    Integer.parseInt(sc.nextLine());

            System.out.print("Enter Easy Questions: ");
            int easy =
                    Integer.parseInt(sc.nextLine());

            System.out.print("Enter Medium Questions: ");
            int medium =
                    Integer.parseInt(sc.nextLine());

            System.out.print("Enter Hard Questions: ");
            int hard =
                    Integer.parseInt(sc.nextLine());

            int questionNo = 1;

            System.out.println(
                    "\n================================="
            );

            System.out.println(
                    "      GENERATED QUESTION PAPER"
            );

            System.out.println(
                    "================================="
            );

            questionNo =
                    printQuestions(
                            con,
                            subjectId,
                            "Easy",
                            easy,
                            questionNo
                    );

            questionNo =
                    printQuestions(
                            con,
                            subjectId,
                            "Medium",
                            medium,
                            questionNo
                    );

            questionNo =
                    printQuestions(
                            con,
                            subjectId,
                            "Hard",
                            hard,
                            questionNo
                    );

            System.out.println(
                    "\n---------------------------------"
            );

            System.out.println(
                    "TOTAL MARKS = "
                    + totalMarks
            );

            System.out.println(
                    "---------------------------------"
            );

            AuditManager.log(
                    "Generated Question Paper"
            );

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    private static int printQuestions(
            Connection con,
            int subjectId,
            String difficulty,
            int limit,
            int questionNo
    ) throws Exception {

        String sql =
                "SELECT question_text, marks " +
                "FROM questions " +
                "WHERE subject_id=? " +
                "AND difficulty=? " +
                "ORDER BY RANDOM() " +
                "LIMIT ?";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setInt(1, subjectId);
        pst.setString(2, difficulty);
        pst.setInt(3, limit);

        ResultSet rs =
                pst.executeQuery();

        System.out.println(
                "\nSECTION "
                + difficulty.toUpperCase()
        );

        System.out.println(
                "-------------------------"
        );

        while(rs.next()) {

            int marks =
                    rs.getInt("marks");

            totalMarks += marks;

            System.out.println(
                    questionNo
                    + ". "
                    + AESUtil.decrypt(
    rs.getString("question_text")
)                    + " ("
                    + marks
                    + " Marks)"
            );

            questionNo++;

        }

        return questionNo;

    }

}
