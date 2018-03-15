 
## 一个简单的java nio开发的NoSQL demo
#### 简单参考了Redis，主要是一些指令，这里直接采用字符串表示
## 特点
#### 具体的指令和存储完全由用户通过config.xml定义，具体的数据结构和存储逻辑位于RedisClass目录下，系统也提供了几个简单的实现支持key-value,list等，
#### 系统会先读取config.xml生成指令到方法的映射，先检查是否指令是否定义，再利用java反射自动加载自定义的类并调用
#### 主线程采用java NIO, Selector多路复用技术 提高并发量
#### 对消息对象进行了一定数目的缓存，提高利用率
#### 严格来说只是个半成品，只提供了一个简单的通信框架，具体的逻辑细节抽象出来，略显粗超，只是用来锻炼一下所学知识，有时间再完善
