# gwfSecurity


## 浏览器安全认证默认配置
```
默认的用户名密码登录请求处理url: /authentication/form
默认的手机验证码登录请求处理url: /authentication/mobile
当请求需要身份认证时，默认跳转的url: /authentication/form
默认登录页面: /my-signIn.html
默认注册社交用户页面: /my-signUp.html
验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称: imageCode
验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称: smsCode
发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称: mobile
session并发登录的跳转地址: /session/gwf-session-invalid.html
session失效默认的跳转地址: /session/gwf-session-expired.html
查看社交用户绑定情况： /connect (需登录)
社交用户绑定url: /connect/{providerId} （需登录）
```


## 核心模块安全认证自定义配置
```
gwf.security.code.image.length  //图片验证码长度（可跟在请求url参数后修改）
gwf.security.code.image.width   //图片验证码宽度（可跟在请求url参数后修改）
gwf.security.code.image.height  //图片验证码高度（可跟在请求url参数后修改）
gwf.security.code.image.url     //需要图片验证码验证的url
gwf.security.code.image.expireIn  //超时时间

gwf.security.code.sms.length    //图片验证码长度（可跟在请求url参数后修改）
gwf.security.code.sms.url       //需要图片验证码验证的url
gwf.security.code.sms.expireIn  //超时时间

gwf.security.social.qq.app-id   //qq登录 appId
gwf.security.social.qq.app-secret  //qq登录 秘钥
gwf.security.social.qq.providerId  //qq登录 providerId

gwf.security.social.weixin.app-id  //微信扫码登录 appid
gwf.security.social.weixin.app-secret //微信扫码登录 秘钥
gwf.security.social.weixin.providerId  //微信扫码登录 providerId

gwf.security.social.filterProcessesUrl  //过滤器url  filterProcessesUrl/providerId 为社交用户登录的入口url

通过实现 org.springframework.social.connect.ConnectionSignUp接口并注册成spring的bean完成社交登录默认注册本地用户的逻辑
示例请见 demo模块 com.gwf.security.DemoConnectionSignUp

通过实现 org.springframework.security.core.userdetails.UserDetails;org.springframework.social.security.SocialUserDetailsService;接口自定义获取数据库用户信息的逻辑
示例请见 demo模块 com.gwf.security.MyUserDetailService

通过实现 com.gwf.security.core.vaildate.code.ValidateCodeGenerator并声明为 名称为imageValidateCodeGenerator的bean来自定义图片验证码生成逻辑
示例请见 com.gwf.security.core.vaildate.code.image.ImageCodeGenerator

通过实现 com.gwf.security.core.vaildate.code.sms.SmsCodeSender并声明为bean 来自定义发送短信验证码逻辑
示例请见 com.gwf.security.core.vaildate.code.sms.DefaultSmsCodeSender
```

## 浏览器安全认证自定义配置
```
gwf.security.browser.loginPage  //登录页面
gwf.security.browser.loginType  //登录类型 JSON/REDIRECT
gwf.security.browser.signUpUrl  //社交用户注册url
gwf.security.browser.signOutUrl //退出页面，不设置则退出返回json
gwf.security.browser.rememberMeSeconds //记住我时间

gwf.security.session.maximumSessions //同一个用户在系统中的最大session数，默认1
gwf.security.session.maxSessionsPreventsLogin //达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
gwf.security.session.sessionExpiredUrl //session并发登录时的跳转地址
gwf.security.session.sessionInvalidUrl //session失效时跳转的地址



通过实现 org.springframework.security.web.session.SessionInformationExpiredStrategy;接口，继承com.gwf.security.brower.session.AbstractSessionStrategy 来自定义并发登录的逻辑
示例请见 browser模块 com.gwf.security.brower.session.GwfExpiredSessionStrategy

通过实现 org.springframework.security.web.session.InvalidSessionStrategy;接口，继承com.gwf.security.brower.session.AbstractSessionStrategy 来自定义session的逻辑
示例请见 browser模块 com.gwf.security.brower.session.GwfInvalidSessionStrategy

通过实现 org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler接口 自定义登录失败逻辑
示例请见 com.gwf.security.brower.authentication.MyAuthenticationFailHandler

通过实现 org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler 自定义登录成功逻辑
示例请见 com.gwf.security.brower.authentication.MyAuthenticationSuccessHandler

通过实现 org.springframework.security.web.authentication.logout.LogoutSuccessHandler接口 自定义退出成功逻辑
示例请见 com.gwf.security.brower.logout.MyLogoutSuccessHandler

通过继承 org.springframework.web.servlet.view.AbstractView 抽象类自定义绑定解绑社交账户后的显示逻辑
示例请见 com.gwf.security.core.social.GwfConnectionStatusView 和 com.gwf.security.core.social.GwfConnectView
```


## 浏览器安全认证自定义配置
```
gwf.security.clients[i].clientId      //第i个客户端的clientId （oauth2认证授权请求需要）
gwf.security.clients[i].clientSecret  //第i个客户端的clientSecret（oauth2认证授权请求需要）
gwf.security.clients[i].accessTokenValidateSeconds  //第i个客户端的clientSecret的超时时间

gwf.security.storeType  //存储类型 redis,jwt二选一
gwf.security.jwtSigningKey  //jwt秘钥


通过实现 org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler接口 自定义登录失败逻辑
示例请见 com.gwf.security.app.authentication.MyAuthenticationFailHandler

通过实现 org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler 自定义登录成功逻辑
示例请见 com.gwf.security.app.authentication.MyAuthenticationSuccessHandler

通过实现 org.springframework.security.oauth2.provider.token.TokenEnhancer来自定义jwttoken存储用户信息
示例请见 com.gwf.security.app.jwt.MyJwtTokenEnhancer

引入 com.gwf.security.app.social.AppSignUpUtils 实现自定义注册逻辑
```