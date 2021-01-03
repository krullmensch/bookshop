import datenbankklassen.MySQL.DatabaseConnector;
import datenbankklassen.MySQL.QueryResult;

public class DatabaseAccess {

    private static DatabaseConnector d;

    public DatabaseAccess(String pDatenbank) {
        super();
        d = new DatabaseConnector("127.0.0.1", 3306, "schule", "marvin", "test");
    }

    public String getCell(String dbQuery) {
        d.executeStatement(dbQuery);
        String[][] res = d.getCurrentQueryResult().getData();
        return res[0][0];
    }

    public String einloggen(String dbQuery) {
        d.executeStatement(dbQuery);
        if (d.getErrorMessage() == null) {
            String[][] res = d.getCurrentQueryResult().getData();
            return res[0][0];
        }
        return d.getErrorMessage();
    }

    public String userRegistrieren(String dbQuery) {
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

    public Produkt suche(String dbQuery) {
        d.executeStatement(dbQuery);
        String[][] res = d.getCurrentQueryResult().getData();
        Produkt p = null;
        if (d.getErrorMessage() == null) {
            for (int i = 0; i < res[0].length; i++) {
                p = new Produkt(res[i][0], res[i][1], res[i][2], res[i][3], res[i][4], res[i][5], res[i][6], res[i][7], res[i][8], res[i][9], res[i][10], res[i][11], res[i][12], res[i][13]);
            }
        } else {
            return null;
        }
        return p;
    }
}