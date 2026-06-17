import java.sql.*;
import java.util.Scanner;

public class SubjectManager {

    public static void addSubject() {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter Subject Name: ");
            String subject = sc.nextLine();

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO subjects(subject_name) VALUES(?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, subject);

            pst.executeUpdate();

            System.out.println("Subject Added Successfully");
            AuditManager.log("Added Subject : " + subject);
            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    public static void viewSubjects() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM subjects"
                    );

            System.out.println("\nSUBJECT LIST");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("subject_id")
                        + " - "
                        + rs.getString("subject_name")
                );

            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

}
