package dev.server.quiz.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
    @Bean
    public UserDetailsManager usersCustom(DataSource dataSource){

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        users.setUsersByUsernameQuery("select username, password, enabled from usuario u where username=?");
        users.setAuthoritiesByUsernameQuery("select u.username, p.nombre from usuarios_perfiles up " +
                "inner join usuario u on u.id = up.usuario_id " +
                "inner join perfil p on p.id = up.perfil_id " +
                "where u.username=?");

        return users;
    }
}
