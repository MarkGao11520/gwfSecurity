package com.gwf.security.app.social;

import com.gwf.security.app.AppSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaowenfeng on 2017/12/10.
 */
@Component
public class AppSignUpUtils {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    /**
     * 保存数据
     * @param request
     * @param connectionData
     */
    public void saveConnectionData(WebRequest request, ConnectionData connectionData){
        redisTemplate.opsForValue().set(getKey(request),connectionData,10, TimeUnit.MINUTES);
    }


    /**
     * 拿出数据
     * @param request
     * @param userId
     */
    public void doPostSignUp(WebRequest request,String userId){
        String key = getKey(request);
        if(!redisTemplate.hasKey(key)){
            throw new AppSecurityException("无法找到缓存的第三方社交账户信息");
        }
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
        Connection connection = connectionFactoryLocator
                .getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);
        redisTemplate.delete(key);
    }

    private String getKey(WebRequest request) {
        String deviceId = request.getHeader("deviceId");
        if(StringUtils.isEmpty(deviceId)){
            throw new AppSecurityException("设备参数不能为空");
        }
        return "gwf:security:social.connect."+deviceId;
    }

}
