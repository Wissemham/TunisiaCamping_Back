/*package esprit.tunisiacamp.paypalISK;


import com.paypal.base.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
public class PaypalConfig {
    @Value("${paypal.client.id}")
    String clientId;
    @Value("${paypal.client.secret}")
    String clientSecret;
    @Value("${paypal.mode}")
    String mode;
    @Bean
    public Map<String,String> paypalSDKConfig(){
        Map<String,String> configMap=new HashMap<>();
        configMap.put("mode",mode);
        return configMap;
    }
    @Bean
    public OAuthTokenCredential oAuthTokenCredential(){
        return new OAuthTokenCredential(clientId,clientSecret,paypalSDKConfig());
    }
    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context=new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap((paypalSDKConfig()));
        return context;
    }
}*/
