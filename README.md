# WareHouseWms
框架、架构、工具
MVVM架构、jetpackUI工具库、okhttp3+retrofit2+RxJava网络框架、ARouter组件化、Composing builds版本统一管理
## [MVVM详细介绍](https://www.jianshu.com/p/ff6de219f988)
有助于将图形用户界面的开发与业务逻辑或后端逻辑（数据模型）的开发分离开来
 1. model:
模型是指代表真实状态内容的领域模型（面向对象），或指代表内容的数据访问层（以数据为中心）。
 2. view:
就像在MVC和MVP模式中一样，视图是用户在屏幕上看到的结构、布局和外观（UI）
 3. viewModel:
视图模型是暴露公共属性和命令的视图的抽象。MVVM没有MVC模式的控制器，也没有MVP模式的presenter，有的是一个绑定器。在视图模型中，绑定器在视图和数据绑定器之间进行通信。
binding:
声明性数据和命令绑定隐含在MVVM模式中。在Microsoft解决方案堆中，绑定器是一种名为XAML的标记语言。 绑定器使开发人员免于被迫编写样板式逻辑来同步视图模型和视图。在微软的堆之外实现时，声明性数据绑定技术的出现是实现该模式的一个关键因素。
## [jetpackUI工具库官网](https://developer.android.google.cn/guide/navigation)
利用fragment+navigation进行导航
 1. 可视化的页面导航图，方便我们理清页面之间的关系
 2. 通过destination和action完成页面间的导航
 3. 方便添加页面切换动画
 4. 页面间类型安全的参数传递
 5. 通过Navigation UI类，对菜单/底部导航/抽屉蓝菜单导航进行统一的管理
## okhttp3+retrofit2+RxJava网络框架
 - okhttp3
 1. 支持HTTP/2，允许所有同一个主机地址的请求共享同一个socket连接
 2. 连接池减少请求延时
 3. 透明的GZIP压缩减少响应数据的大小
 4. 缓存响应内容，避免一些完全重复的请求
 - retrofit2
 1. 超级解耦 ，接口定义、接口参数、接口回调不在耦合在一起
 2. 可以配置不同的httpClient来实现网络请求，如okhttp、httpclient
 3. 支持同步、异步、Rxjava
 4. 可以配置不同反序列化工具类来解析不同的数据，如json、xml
 5. 请求速度快，使用方便灵活简洁
 - RxJava
 RxJava 是一个 基于事件流、实现异步操作的库
## [ARouter官网](https://github.com/alibaba/ARouter)
组件化就是要将项目的各个功能拆成多个模块
## [Composing builds详细介绍](https://gitee.com/cooljiatao/gradle-build)
VersionPlugin版本统一管理
 1.支持单向跟踪 
 2.自动补全 
 3.依赖更新时，不会从新构建整个项目，这是相比buildSrc最大的优势

 
