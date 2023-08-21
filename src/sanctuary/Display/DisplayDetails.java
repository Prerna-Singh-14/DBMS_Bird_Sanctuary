package sanctuary.Display;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
public class DisplayDetails {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sanctuary", "root",
                    "password");
            Statement stmt = con.createStatement();
            System.out.println("Enter Table Name:");
            String t_name=br.readLine();
            String query = "select * from "+t_name+";";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsm=rs.getMetaData();
            System.out.println("******************************************");
            int count = rsm.getColumnCount();
            for(int i = 1; i<=count; i++) {
                System.out.print(rsm.getColumnName(i)+"\t");
            }
            System.out.println();
            while(rs.next())
            {
                for(int i=1;i<=count;i++)
                {
                    System.out.print(rs.getString(i)+"\t");
                }
                System.out.println();
            }
            System.out.println("******************************************");
            con.close();
    }
}
