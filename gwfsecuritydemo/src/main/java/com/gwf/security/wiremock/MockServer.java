package com.gwf.security.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created by gaowenfeng on 2017/10/11.
 * 链接wiremock服务端的客户端
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        configureFor(9091);
        removeAllMappings();   //把之前的配置都情况
        mock("/order/1","01");
        mock("/order/2","02");

    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/"+file+".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(),"UTF-8"),"\n");
        stubFor(get(urlPathEqualTo(url)).
                willReturn(aResponse().withBody(content).
                        withStatus(200)));
    }
}
