package openkb.drill;

import java.sql.*;
import java.util.*;

public class MapRDrillJDBCExample {
    static final String JDBC_DRIVER = "com.mapr.drill.jdbc41.Driver";
    static final String DB_URL = "jdbc:drill:drillbit=v1.poc.com:31010;auth=maprsasl;sasl_encrypt=true";

    static final String USER = "mapr";
    static final String PASS = "mapr";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            Properties props = new Properties();
            props.setProperty("user", "mapr");
            conn = DriverManager.getConnection(DB_URL,props);

            stmt = conn.createStatement();
            String sql = "select hostname from sys.drillbits";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String hs = rs.getString("hostname");
                System.out.println("hostname: " + hs);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null)
                    stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
