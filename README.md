#网约车项目  

> 技术点：  
1、SpringBoot  
2、SpringCloud   
    eureka server & client(源码阅读&调优)  
    zuul  
    ribbon  
    RestTemplate & Feign调用  
3、redis   
    存短信验证码、token   



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
         
