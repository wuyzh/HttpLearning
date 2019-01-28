#1. 使用
- 1、优点
支持SPDY, 可以合并多个到同一个主机的请，使用连接池技术减少请求的延迟(如果SPDY是可用的话) ，使用GZIP压缩减少传输的数据量，缓存响应避免重复的网络请求、拦截器等等。
- 2、缺点
第一缺点是消息回来需要切到主线程，主线程要自己去写，第二传入调用比较复杂。

#2.简单的封装
- 进行封装要注意以下两点
  1. 避免重复代码调用
  2. 将请求结果回调改为UI线程
  3. 结果Response只能在子线程内解析执行，在UI线程执行的话会报网络线程异常。
  4. 我做的一个超级简单的封装将在`OkHttp3框架的使用实例`这一节中将代码和实现思路贴出。

#3. OkHttp3框架的使用实例
- [OkHttp3的实例使用](https://www.jianshu.com/p/c1cfb5423a19)
- [OkHttp3回调回UI线程的简单封装](https://www.jianshu.com/p/df0ff5ab5f25)
#4. OkHttp3框架的使用流程以及各功能的简单介绍
- [OkHttp3框架的使用流程](https://www.jianshu.com/p/f6ea0a5dfb0a)
#5.源码解析
[源码解析时希望能跟着代码的流程走一下](https://upload-images.jianshu.io/upload_images/11008949-09aac9d22ca36b10.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- Request

- [OkHttpClient](https://www.jianshu.com/p/bf764daa7675)
https://www.jianshu.com/p/bf764daa7675
_OkHttp客户端，负责属性设置，生成最终请求实例Call_
- [Dispatcher](https://www.jianshu.com/p/efc6faa7a8a0)
https://www.jianshu.com/p/efc6faa7a8a0
_请求任务调度器，按照规则分发和保存请求_
- [ConnectionPool](https://www.jianshu.com/p/57b7db8b2c9c)
https://www.jianshu.com/p/57b7db8b2c9c
_连接池_



- [RealCall](https://www.jianshu.com/p/bd2015815a03)
https://www.jianshu.com/p/bd2015815a03
_最终网络请求对象_
- RealInterceptorChain拦截器链表
- 上面的东西介绍完之后介绍进入到RealInterceptorChain,顺便介绍一下RealInterceptorChain


- RetryAndFollowUpInterceptor   -- StreamAllocation
负责失败重试以及重定向。
- BridgeInterceptor 
连接桥，它负责把用户构造的请求转换为发送给服务器的请求，把服务器返回的响应转换为对用户友好的响应。 转换的过程就是添加一些服务端需要的header信息。
- CacheInterceptor -- 缓存是怎么走的
CacheInterceptor就是用来负责读取缓存以及更新缓存的
- ConnectInterceptor  --HttpCodec怎么来的，RealConnection怎么来的，怎么连接上网络的
在RetryAndFollowUpInterceptor里初始化了一个StreamAllocation对象，我们说在这个StreamAllocation对象里初始化了一个Socket对象用来做连接，但是并没有真正的连接，等到处理完hader和缓存信息之后，才调用ConnectInterceptor来进行真正的连接
- CallServerInterceptor
负责从服务器读取响应的数据