package io.omni.financia.config;

import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AppConfig {

    /*public DataSource dataSource(){
        OracleDataSource dataSource = null;
        try {
            dataSource = new OracleDataSource();
            Properties props = new Properties();
            String oracle_net_wallet_location =
                    System.getProperty("oracle.net.wallet_location");
            props.put("oracle.net.wallet_location", "(source=(method=file)(method_data=(directory="+oracle_net_wallet_location+")))");
            dataSource.setConnectionProperties(props);
            dataSource.setURL(url);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }*/
}
