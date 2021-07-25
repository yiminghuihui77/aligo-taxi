#网约车项目  

> 技术点：  
1、SpringBoot  
2、SpringCloud   
    eureka server & client(源码阅读&调优)  
    zuul   (源码阅读)
      pre | route✨ | post | error  
      route类型过滤器的三种场景：  
      1、路由到微服务  
      2、路由到具体地址  
      3、路由到zuul自己的接口  
    ribbon 自定义负载均衡策略  
    RestTemplate & Feign调用  
3、redis   
    存短信验证码、token   
4、灰度发布   
   场景一：用户端 -> 网关(ribbon) -> 服务  
         定义zuul路由类型过滤器 +   RibbonFilterContextHolder.getCurrentContext().add( "version", version );  
         相关类：
              cloud-zuul: GrayFilter  
   场景二：服务A(ribbon) -> 服务B
         1、将http请求头中的version参数存储到ThreadLocal中
         2、服务A端自定义ribbon IRule，获取当前线程上下文中的version
            获取可达服务B的列表，获取每个Server的medata-map中定义的version
            两version比较一致，则该Server为目标服务B
         注意：服务A中指定@RibbonClient(name = "service-verification-code", configuration = RibbonConfig.class)
              即对下游服务service-verification-code采取RibbonConfig中配置的路由策略   
         相关类：
             A: api-passenger项目：  VersionAnnotation、VersionAspect、RibbonConfig、GrayRule、GrayController  
             B: service-verification-code项目：  GrayController  
5、限流
   1）、网关层限流  
          使用Guava的RateLimiter令牌桶实现限流（RateLimiterFilter）  
          使用alibaba的sentinel实现限流（SentinelRateFilter）
   2）、微服务接口层面限流  
           Guava的RateLimiter + AOP切面实现限流  
           sentinel的接口注解实现:  @SentinelResource


> 功能模块  
1、登录模块  
    发送短信验证码  
    验证短信验证码  
    登录(token)   zuul实现API网关校验
2、预估计费  
    存储用户使用频次高的起点&重点路线（APP端推荐）   
    web端可配置计费规则    
    购买高德/百度地图服务    
      电子围栏，区域内叫车   
      起点经纬度->终点经纬度，获取规划路线
      计费的数据类型：1、BigDecimal   2、long(精确到分)  
         
