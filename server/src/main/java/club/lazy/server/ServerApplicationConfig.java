package club.lazy.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "club.lazy")
// loader bean
public class ServerApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(ServerApplicationConfig.class);
}
