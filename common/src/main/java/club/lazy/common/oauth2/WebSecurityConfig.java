//package club.lazy.common.oauth2;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    // 设置 http 验证规则
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 关闭csrf验证
//        http.csrf().disable()
//                // 对请求进行验证
//                .authorizeRequests()
//                // 所有 / 的请求 都放行
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                // 权限检查
//                .antMatchers("/hello").hasAnyAuthority("AUTH_WRITE")
//                // 角色检查
//                .antMatchers("/word").hasRole("ADMIN")
//                .anyRequest().authenticated();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//
//    }
//}
