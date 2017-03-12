package banco_de_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por fornecer acesso as "configurações" do banco de dados
 * para as demais classes
 *
 * @author Hernany
 */
public class ConfiguracaoBD {

    public Connection pegaconexao() throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection("jdbc:mysql://agendasoft.mysql.uhserver.com:3306/agendasoft", "agendasoft", "fgr11qas@");
        } catch (Exception ex) {
            Logger.getLogger(ConfiguracaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String url = "jdbc:mysql://agendasoft.mysql.uhserver.com:3306/agendasoft";
    public static String user = "agendasoft";
    public static String password = "fgr11qas@";
}
