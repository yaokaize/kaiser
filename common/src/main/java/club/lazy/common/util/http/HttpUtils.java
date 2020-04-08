package club.lazy.common.util.http;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * http请求辅助工具类
 */
@Component
public class HttpUtils {

    public static String sendPostRequest(String url, Map<String, Object> params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpMethod httpMethod = HttpMethod.POST;
        // 已表单的方式提交
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // 将请求头部和参数合成一个请求
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> responseEntity = client.exchange(url, httpMethod, httpEntity, String.class);
        return responseEntity.getBody();
    }
}
