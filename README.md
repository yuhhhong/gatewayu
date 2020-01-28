# gatewayu
为什么要基于spring webflux？

  api网关作为后端的入口，提供鉴权、限流等访问控制处理，通过后将请求转发给后端。在高并发和潜在的高延迟场景下，api网关要实现高性能高吞吐量的一个基本要求是全链路异步，不要阻塞线程，zuul采用同步阻塞模式，不是太好。
  
  spring5基于reactor3，开发了webflux，支持响应式编程，响应式提倡异步非阻塞。webflux还支持背压（back-pressure），订阅者订阅的时候， 生产者才提供数据，不会出现数据提供太快，订阅端处理不来抛异常的情况。
  
  如果用作普通业务模块，spring webflux不是很适合，因为还没有异步的JDBC api，所以请求到了数据库层面还是会被阻塞住的，性能相比原先的mvc差距就不大。

使用了什么技术？

  springboot spring webflux + reactor
  
  webclient发送网络请求
  
  webfilter实现过滤器
  
请求

  比如：http://localhost:8080/user/getAllUser 这样的一个请求
  
  会先经过RequestAuthFilter过滤器处理，若鉴权失败则转发到控制器ExceptionController处理返回错误信息。
  
  经过过滤器后会根据请求uri提取各种请求信息（如httpMethod，httpHeaders，要请求哪个业务模块等），然后根据业务模块信息去获取具体业务模块的path，现在是根据配置文件获取，后面再实现根据注册中心等组件获取（抽象出一个DiscoveryHandler接口，方便解耦）。
  
  然后根据请求信息，使用webclient发送非阻塞的网络请求，把收到的结果返回。全程异步非阻塞。
  
后续

  异常处理不是很优雅，等有空再完善一下
  
  获取服务的部分再实现一下
  
  使用什么工具发送请求的部分没有做到解耦
