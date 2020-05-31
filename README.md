# springboot-demo

#### 问题：获取不到实体对应的类？   
S：修改MapperScan的导入类型为：`import tk.mybatis.spring.annotation.MapperScan`;
#### 问题：修改Redis配置为本地后，无法访问Redis？ 
S：切换本地redis后，配置应改成
    spring.redis.host=127.0.0.1
    spring.redis.port=6379
    spring.redis.password=jincong
    spring.redis.timeout=2000
    spring.redis.jedis.pool.max-active=10
    spring.redis.jedis.pool.max-idle=10
    spring.redis.jedis.pool.max-wait=3
    spring.redis.database=0
####: 添加Swagger2相关GAV后，仍然无法正常显示http://localhost:8080/swagger-ui.html页面？
S : 首先检查主启动类是否添加@Swagger2注解，然后检查是否覆写WebMvcConfigurationSupport类，
  添加
`     
 @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/**").addResourceLocations(
              "classpath:/static/");
      registry.addResourceHandler("swagger-ui.html").addResourceLocations(
              "classpath:/META-INF/resources/");
      registry.addResourceHandler("/webjars/**").addResourceLocations(
              "classpath:/META-INF/resources/webjars/");
      super.addResourceHandlers(registry);
  }
 `
     
+ #### 问题：@JsonFormat 注解无效
S: @JsonFormat：这个注解来自jackson包下；
   @JSONField：这个注解来自fastjson包下；
   
    `@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") 
    @JSONField(format="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private Date birthday;`


+ #### 问题：日志规范 

`    
日志输出格式：
		%d表示日期时间，
		%thread表示线程名，
		%-5level：级别从左显示5个字符宽度
		%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
		%msg：日志消息，
		%n是换行符
    -->
    %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n 
`

