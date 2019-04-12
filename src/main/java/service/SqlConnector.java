package service;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static utils.Constants.*;

public class SqlConnector {

    private Connection conn = null;

    SqlConnector()  {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlserver://" + SQL_SERVER_NAME + ";" +
                    "databaseName=" + SQL_DB_NAME, SQL_USER_NAME, SQL_PASSWORD);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            JTextArea msg = new JTextArea("LOGOYA BAĞLANILAMADI \n" + e.getMessage());
            msg.setSize(400,300);
            msg.setLineWrap(true);
            msg.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(msg);

            JOptionPane.showMessageDialog(null, scrollPane);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        Connection conn = null;
        try {

            Statement stmt = conn.createStatement();

            stmt.execute(InvoicePostsql);

            /*ResultSet rs = stmt.executeQuery(getCariLogicalRef);

            while (rs.next()) {
                System.out.println(rs.getString("LOGICALREF"));
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    String getLastCode(String tableName, String targetField, String referenceField, String matchPattern)  {
        String query = SELECT_KEY + TOP_ONE_KEY + targetField + FROM_KEY + tableName
                + WHERE_KEY + targetField + LIKE_KEY + "'%" + matchPattern + "%'"
                + ORDER_BY_KEY + referenceField + DESC_KEY;

        Statement stmt = null;
        ResultSet rs = null;
        String result = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(getCariLogicalRef);
            result = rs.getString(targetField);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "'" + matchPattern+"0000" + (Integer.parseInt(result.split(matchPattern+"0000")[1]) + 1) + "'";
    }

    void executeInsertQuery(String tableName, HashMap<String, String> map) {

        StringBuilder firstPart = new StringBuilder(INSERT_INTO_KEY + tableName + " (");
        StringBuilder secondPart = new StringBuilder("VALUES (");


        for (Map.Entry<String, String> entry : map.entrySet()) {
            firstPart.append(entry.getKey()).append(" ,");
            secondPart.append(entry.getValue()).append(" ,");
        }

        firstPart.setCharAt(firstPart.length() - 1, ')');
        secondPart.setCharAt(secondPart.length() - 1, ')');

        String query = firstPart.append(" ").append(secondPart).toString();
        System.out.println(query);

    }

    public static String Carisql = "INSERT INTO [GO3].[dbo].[LG_001_CLCARD] (" +
            "           [ACTIVE] " +
            "           ,[CARDTYPE] " +
            "           ,[CODE] " +
            "           ,[DEFINITION_] " +
            "           ,[ADDR1] " +
            "           ,[ADDR2] " +
            "           ,[TAXNR] " +
            "           ,[EMAILADDR] " +
            "           ,[CLANGUAGE] " +
            "           ,[RECSTATUS] " +
            "           ,[CAPIBLOCK_CREATEDBY] " +
            "           ,[CAPIBLOCK_CREADEDDATE] " +
            "           ,[CAPIBLOCK_CREATEDHOUR] " +
            "           ,[CAPIBLOCK_CREATEDMIN] " +
            "           ,[CAPIBLOCK_CREATEDSEC] " +
            "           ,[PAYMENTTYPE] " +
            "           ,[CLORDFREQ] " +
            "           ,[LOWLEVELCODES1] " +
            "           ,[PURCHBRWS] " +
            "           ,[SALESBRWS] " +
            "           ,[IMPBRWS] " +
            "           ,[EXPBRWS] " +
            "           ,[FINBRWS] " +
            "           ,[TCKNO] " +
            "           ,[PROFILEID] " +
            "           ,[SHIPBEGTIME1] " +
            "           ,[SHIPENDTIME1] " +
            "           ,[GUID] " +
            "           ,[CREATEWHFICHE] " +
            "           ,[PROFILEIDDESP] )" +
            "     VALUES (" +
            "           0 " +
            "           ,3 " +
            "           ,'BRY0000000000635' " +//BRY<sonuncuyu çek bir arttır>
            "           ,'test test' " +//<isim soyisim>
            "           ,'adress' " + //<adresin tamamı>
            "           ,'il ilçe' " + //<il ilçe>
            "           ,'1111111111' " + //<vergi numarası, ikiside yoksa(vkn ve tc )ise 10x1>
            "           ,'a@b.com' " + //<mail>
            "           ,1 " +
            "           ,1 " +
            "           ,1 " +
            "           ,'2019-04-23T13:13:13' " + //<gg.aa.yyyy ss:dd:ss>
            "           ,13 " +
            "           ,13 " +
            "           ,13 " +
            "           ,1 " +
            "           ,1 " +
            "           ,635 " + //<çek bi sonrakini yaz>
            "           ,1 " +
            "           ,1 " +
            "           ,1 " +
            "           ,1 " +
            "           ,1 " +
            "           ,'' " + // <varsa yaz>
            "           ,2 " +
            "           ,134217728 " +
            "           ,288817152 " +
            "           ,'3d9e2d7b-653c-40c8-8db0-30c2a715b313' " + //<oluştur>
            "           ,1 " +
            "           ,1 " +
            ")";


    public static String InvoicePostsql = "INSERT INTO [GO3].[dbo].[LG_001_01_INVOICE] ( " +
            " GRPCODE " +
            " ,TRCODE " +
            " ,FICHENO " +
            " ,DATE_ " +
            " ,CLIENTREF " +
            " ,ENTEGSET " +
            " ,VAT " +
            " ,TOTALDISCOUNTED " +
            " ,TOTALVAT " +
            " ,GROSSTOTAL " +
            " ,NETTOTAL " +
            " ,TRNET" +
            " ,REPORTNET " +
            " ,REPORTRATE " +
            " ,CAPIBLOCK_CREATEDBY " +
            " ,GENEXCTYP " +
            " ,RECSTATUS " +
            " ,DEDUCTIONPART1 " +
            " ,DEDUCTIONPART2 " +
            " ,AFFECTRISK " +
            " ,APPROVE " +
            " ,DOCDATE " +
            " ,EINVOICE " +
            " ,ESTATUS " +
            " ,GUID " +
            " ) VALUES ( " +
            " 2 " +
            " ,8 " +
            " ,'CCH2019000000678' " +
            " ,'2019-04-23T13:13:13' " +
            " ,646" +
            " ,247 " +
            " ,18 " +
            " ,100 " +
            " ,18 " +
            " ,100 " +
            " ,118 " +
            " ,118 " +
            " ,118 " +
            " ,1 " +
            " ,1 " +
            " ,2 " +
            " ,1 " +
            " ,2 " +
            " ,3 " +
            " ,1 " +
            " ,1 " +
            " ,'2019-04-23T13:13:13' " +
            " ,0 " +
            " ,0 " +
            " ,'8cc362b0-7c47-419d-bed2-fd08c1fd0b86' " +
            " ) ";

    public static String getLastFicheNoSql = "SELECT TOP 1 FICHENO FROM [GO3].[dbo].[LG_001_01_INVOICE] WHERE FICHENO like 'CCH2019%' " +
            "ORDER BY LOGICALREF DESC";

    public static String getCariLogicalRef = "SELECT TOP 1 LOGICALREF FROM [GO3].[dbo].[LG_001_CLCARD] WHERE CODE like 'BRY0000000000634' ";
}
