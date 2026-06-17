import java.sql.*;

public class PaperGenerator {

    public static void generatePaper() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM questions ORDER BY difficulty";

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            System.out.println(
                    "\n===== GENERATED QUESTION PAPER ====="
            );

            while(rs.next()) {

                System.out.println(
                        rs.getString("difficulty")
                        + " : "
                        + rs.getString("question_text")
                );

            }
            AuditManager.log("Generated Question Paper");
            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

}
