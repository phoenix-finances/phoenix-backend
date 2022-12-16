package io.omni.financia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests()
                .antMatchers("/users")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

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
