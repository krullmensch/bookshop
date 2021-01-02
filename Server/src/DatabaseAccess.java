import datenbankklassen.MySQL.DatabaseConnector;
import datenbankklassen.MySQL.QueryResult;

import java.util.Date;

public class DatabaseAccess {

    private static DatabaseConnector d;

    public DatabaseAccess(String pDatenbank) {
        super();
        d = new DatabaseConnector("127.0.0.1", 3306, "schule", "marvin", "test");
    }

    public static String einloggen(String dbQuery) {
        d.executeStatement(dbQuery);
        if (d.getErrorMessage() == null) {
            QueryResult res = d.getCurrentQueryResult();
            String[][] password = res.getData();
            return password.toString();
        }
        return d.getErrorMessage();
    }

    public static String userRegistrieren(String dbQuery) {
        d.executeStatement(dbQuery);
        QueryResult res = d.getCurrentQueryResult();
        if (d.getErrorMessage() == null) {
            if (res == null) {
                return null;
            } else {
                return res.toString();
            }
        }
        return d.getErrorMessage();
    }

    public static String sucheAutor(String dbQuery) {
        d.executeStatement(dbQuery);
        QueryResult res = d.getCurrentQueryResult();
        if (d.getErrorMessage() == null) {
            return res.toString();
        }
        return d.getErrorMessage();
    }

    public static String sucheTitel(String dbQuery) {
        d.executeStatement(dbQuery);
        QueryResult res = d.getCurrentQueryResult();
        if (d.getErrorMessage() == null) {
            return res.toString();
        }
        return d.getErrorMessage();
    }


    public static String sucheISBN(String dbQuery) {
        d.executeStatement(dbQuery);
        QueryResult res = d.getCurrentQueryResult();
        if (d.getErrorMessage() == null) {
            return res.toString();
        }
        return d.getErrorMessage();
    }


    public static String sucheGenre(String dbQuery) {
        d.executeStatement(dbQuery);
        QueryResult res = d.getCurrentQueryResult();
        if (d.getErrorMessage() == null) {
            return res.toString();
        }
        return d.getErrorMessage();
    }

    public static String bestellung(String dbQuery) {
        d.executeStatement(dbQuery);
        QueryResult res = d.getCurrentQueryResult();
    }
}