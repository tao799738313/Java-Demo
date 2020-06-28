/**
 *
 */
package pers.hmm.shop.manager.security.core.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pers.hmm.shop.manager.security.core.properties.SecurityProperties;

/**
 * @author humm
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
