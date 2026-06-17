import java.sql.*;

public class AuditManager {

    public static void log(String action) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO audit_logs(username,action) VALUES(?,?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, "admin");
            pst.setString(2, action);

            pst.executeUpdate();

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    public static void viewLogs() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM audit_logs ORDER BY log_id"
                    );

            System.out.println("\n===== AUDIT LOGS =====");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("log_id")
                        + " | "
                        + rs.getString("username")
                        + " | "
                        + rs.getString("action")
                        + " | "
                        + rs.getTimestamp("log_time")
                );

            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

}
