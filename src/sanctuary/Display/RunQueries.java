package sanctuary.Display;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class RunQueries {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ch, c;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sanctuary", "root",
                "password");
        Statement stmt = con.createStatement();
        do {
            System.out.println("Type of Query\n1.Retrieve Data\n2.Insert,Update,Delete");
            System.out.println("Enter choice");
            ch = Integer.parseInt(br.readLine());
            if (ch == 1) {
                System.out.println("Enter query");
                String query = br.readLine();
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData rsm = rs.getMetaData();
                System.out.println("******************************************");
                int count = rsm.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    System.out.print(rsm.getColumnName(i) + "\t");
                }
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= count; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
                System.out.println("******************************************");
            } else if (ch == 2) {
                System.out.println("Enter query");
                String query = br.readLine();
                int rcount = stmt.executeUpdate(query);
                if (rcount > 0) {
                    System.out.println("Query successfully executed.");
                    System.out.println("Number of rows affected:" + rcount);
                }
            } else {
                System.out.println("Invalid Choice");
            }
            System.out.println("Do you want to continue?(1-yes/0-no)");
            c=Integer.parseInt(br.readLine());
        } while (c != 0);
        con.close();
    }
}