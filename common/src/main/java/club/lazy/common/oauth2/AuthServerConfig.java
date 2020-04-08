package club.lazy.common.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


@Configuration
//  代表整个认证服务端
@EnableAuthorizationServer //授权认证中心
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 权限认证控制器
     */
    private AuthenticationManager authenticationManager;

    /**
     *  用户验证账号密码
     */
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthServerConfig(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 配置访问端点和令牌服务
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /*
            allowFormAuthenticationForClients 开启表单验证
         */
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端详情服务
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*
          client信息， 可以存储数据库，可以redis，也可以是内存中
         */
        clients.inMemory()
                .withClient("appid")
                .secret("{noop}secret")
                // 有效时间
                .accessTokenValiditySeconds(72000)
                .authorizedGrantTypes("password", "authorization_code", "client_credentials", "implicit", "refresh_token")
                .scopes("all");
    }

    /**
     * 配置token
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
