import java.sql.*;
import java.util.Scanner;

public class LoginManager {

    public static boolean login() {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("===== LOGIN =====");

            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {

                System.out.println("\nLogin Successful!");

                AuditManager.log(
                        "User Logged In : " + username
                );

                con.close();

                return true;

            }

            System.out.println("\nInvalid Username or Password");

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return false;

    }

}
