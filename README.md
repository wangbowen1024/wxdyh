# 微信个人订阅号开发
## 目录
1. [简述](#简述)
2. [部署项目](#部署项目)
	* 需要准备的环境以及工具
	* 微信订阅号的注册以及基本配置
	* 修改项目配置信息
	* 项目的部署
3. [自定义开发教程](#自定义开发教程)
	* 成为管理员
	* 登陆后台管理页面
	* 添加素材
	* 查看素材
	* 添加规则
	* 自定义业务（主要目的）
	* 自定义响应返回内容

## 简述
为了方便实现个人订阅号的功能扩张，简化后台开发难度，可以让使用JAVA语言的开发者，快速用上简单的符合自己想法的个人订阅号。但是由于个人订阅号的权限少得可怜，这里主要封装了消息接收与回复部分的功能，并提供了前端后台管理页面。
* 以下是素材消息回复示例：

![素材消息回复示例](http://wangbowen.cn/image/wxdyh/p11.jpg?3)

* 以下是自定义业务功能开发（自动回复机器人）示例：

![登陆页面](http://wangbowen.cn/image/wxdyh/p12.jpg)

## 部署项目
### 需要准备的环境以及工具
* JDK1.8的环境
* MySql数据库
* Git
* Maven
* Tomcat
* 一台服务器（我用的是腾讯云学生机）

### 微信订阅号的注册以及基本配置
1. 在微信公众平台注册订阅号（个人）
2. 开启接收语音识别结果（开启后用户向订阅号发送的语音消息后台可以识别转化成文字进行处理：开发>接口权限>对话服务>接收消息>接收语音识别结果>开启）
3. 在 开发>基本配置 中需要将你的服务器的IP地址添加到IP白名单中，因为所有调用微信接口都要在白名单的IP中才能够调用

### 修改项目配置信息
1. 选好文件储存的位置，使用git将项目克隆到本地（是真的慢）
	```
	git clone https://github.com/wangbowen1024/wxdyh.git
	```

	注意：可能git会抛出错误 error: RPC failed; curl 18 transfer closed with outstanding read data remaining 原因是缓冲区大小不够，可以设置大点，我这里设置了300M
	```
	git config --global http.postBuffer 314572800
	```
2. 配置数据库
	* 修改配置文件（wxdyh\src\main\resources\jdbcConfig.properties）
	```
	user=你的数据库用户名
	password=你的数据密码
	```

	* 执行SQL脚本创建数据库(wxdyh\wxdyhSQL.sql)
3. 配置微信订阅号的基本信息
	* 修改配置文件（wxdyh\src\main\resources\gzhConfig.properties）
	```
	登陆微信公众平台后，根据数据进行填写

	WXGZH_ID=这个是订阅号ID（在设置>公众号设置>最下那个原始ID）
	ENCODING_AES_KEY=以下参数都能在（开发>基本配置中找到）
	APP_ID=
	APP_SECRET=
	TOKEN=（这个在公众平台上随便写，我是随便生成了一个UUID，这个是用来生成aceess_token的）
	```
4. html页面URL路径修改（推荐使用一些编辑软件，如记事本，使用一键全部替换）
	* 打开index.html(wxdyh\src\main\webapp\index.html)
	```
	将其中的http://localhost:8080改成你项目实际访问路径，注意必须以http://或https://开头，分别支持80端口和443端口。
	（我这里改成了http://wangbowen.cn/wxdyh 其中wangbowen.cn是域名,wxdyh是项目名）
	```

	* 打开admin.html(wxdyh\src\main\webapp\index.html)
	```
	同上进行替换
	
	```

### 项目的部署
1. 返回项目根目录（有pom.xml文件的那一层）对项目进行打包
```
依次运行如下命令
mvn clean compile
mvn clean package
```
完成后，查看信息获取打包路径：[INFO] Building war: D:\XXX\wxdyh\target\wxdyh.war
2. 将 wxdyh.war 复制到tomcat下的webapp目录下
3. 启动tomcat（运行tomcat安装目录下的bin目录下的startup.bat）
4. 等待一段时间后显示类似如下内容即为启动成功
```
15-Aug-2019 00:56:00.703 信息 [ContainerBackgroundProcessor[StandardEngine[Catalina]]] org.apache.catalina.startup.HostConfig.deployWAR Deployment of web application archive [C:\apache-tomcat-9.0.0.M26\webapps\wxdyh.war] has finished in [65,875] ms
```
5. 打开浏览器输入项目首页URL（我的是http://wangbowen.cn/wxdyh即上面替换的URL），如果成功访问如下登陆界面即为部署成功

![登陆页面](http://wangbowen.cn/image/wxdyh/p1.png?2)

6. 登陆微信开发平台，在基本配置中修改URL为“上面替换的URL/api”（如我的是http://wangbowen.cn/wxdyh/api）消息加密为明文，如需加密可以自行参考官方文档。然后提交（可能会遇到无效URL，多试几次可能是网络问题）然后点击启用。至此全部完成

## 自定义开发教程
### 成为管理员
第一个关注公众的用户直接成为管理员，开发者可以直接修改数据库中的user表中的authority字段为admin来添加其他管理员，以便登陆后台系统（本来想做一个添加管理员的页面，太懒拉，有兴趣的童鞋可以开发后我来整合噢）
![成为管理员](http://wangbowen.cn/image/wxdyh/p2.jpg?1213)

### 登陆后台管理页面
1. 管理员向公众号发送“登陆”二字，可以获取登陆令牌

![获取登陆令牌](http://wangbowen.cn/image/wxdyh/p3.jpg?1)

2. 打开浏览器访问登陆地址，输入令牌登陆系统（30分钟内自动登陆，不用再次输入令牌。如果发现页面点击按钮没有反应，应该是有效期过了，要重新返回登陆界面登陆）

![后台管理界面](http://wangbowen.cn/image/wxdyh/p4.png?1)

### 添加素材
* 在微信订阅号中素材有：图片、语音、视频、图文等
* 上传这些素材目前可以登陆微信公众平台的素材管理进行上传（有接口可以在网页中上传，但是懒得写），上传的方式有两种：
	* 要么直接从本地文件上传
	* 要么向订阅号发送图片、语音、视频，在消息管理中找到对应的消息，直接进行转存到素材库

### 查看素材
* 点击左侧素材管理中的4个分类，右侧会出现对应的素材名称和素材ID
* 由于采用了缓存机制，所以在微信公众平台中对素材进行修改后，这边需要点击刷新按钮来更新资源
![查看素材](http://wangbowen.cn/image/wxdyh/p5.png)

### 添加规则
* 规则有3类：全匹配、半匹配（包含关系）、正则表达式
* 在添加规则中输入规则后可以输入模拟消息点击检测来判断是否符合预期匹配结果
* 回复内容：如果是文字直接输入（支持换行），如果是其他素材类型，在素材管理中复制素材ID即可（接下来演示文字和图片素材两种）
	* 示例1：
![添加规则演示](http://wangbowen.cn/image/wxdyh/p6.png)
![添加规则演示](http://wangbowen.cn/image/wxdyh/p7.png)
	* 示例2：
![添加规则演示](http://wangbowen.cn/image/wxdyh/p8.png)
![添加规则演示](http://wangbowen.cn/image/wxdyh/p9.png)

### 自定义业务（主要目的）
* 为了方便没有接触过订阅号开发的JAVA开发者，这里将数据接收与回复做了封装，只需要简单的一个函数即可实现符合自己满意的业务功能
* 在源码com.wxdyh.controller.MessageController类中处理用户发送的消息，只需要修改processMessage方法中的内容就能达到自己想要的效果。
```java
// 用户发送的文字消息内容
String message = (String) request.getAttribute("message");
// 发送者用户ID
String sender = (String) request.getAttribute("sender");
```
解析：如果用户向订阅号发送文字消息，那么message就是该文字内容。如果用户发送语音消息，且在微信开发平台开启了接收语音识别结果，那么message就是用户的语音翻译后的文字信息（注意语音最后一个句号。已经被过滤）
```java
/**======  自定义匹配规则以及对应的业务,注意匹配顺序和逻辑关系(或者自行转发到其他Controller进行处理)  ========*/
// 获取token
if ("token".equals(message)) {
    return replayMessage(MaterialEnum.TEXT, TokenUtil.getAccessToken(), sender);
}
/** ==================================      E      N      D      ============================================ */
```
解析：在注释之间添加根据用户发送的消息message来判断，从而实现相关的业务逻辑，示例中是获取token的方法，可以用于本地接口调试

示例：

![自定义获取token](http://wangbowen.cn/image/wxdyh/p10.png)

```java
/** ***************************************  以下添加自定义功能函数  ******************************************** */
```
解析：在该类最下方提供了自定义函数的地方

### 自定义响应返回内容
* 根据用户的内容我们也可以返回素材：图片、语音、图文、文字等
* 使用以下语句来返回内容
```java
return replayMessage(MaterialEnum type, Object obj, String toUserName);
``` 
函数解析：
```java
/**
 * 回复消息
 * @param type 回复类型
 * @param obj 回复的内容（文字、语音、图片传String,视频传Video,图文传Item）
 * @param toUserName 发送者ID，即上面的sender字符串（这个参数是固定写成sender）
 * @return
 */
private BaseResponseMessage replayMessage(MaterialEnum type, Object obj, String toUserName) {...}
```
* 返回文字示例
```java
return replayMessage(MaterialEnum.TEXT, "你要回复的是文字信息", sender); 
```
* 返回语音示例
```java
return replayMessage(MaterialEnum.VOICE, "你要回复的是语音素材ID，可以在后台管理素材中获取", sender);
```
* 返回图片示例
```java
return replayMessage(MaterialEnum.IMAGE, "你要回复的是图片素材ID，可以在后台管理素材中获取", sender);
```
* 返回视频示例
```java
Video video = new Video("视频素材ID", "视频素材标题", "视频描述");
return replayMessage(MaterialEnum.VIDEO, video, sender);
```
* 返回图文示例
```java
Item item = new Item("图文标题", "图文描述", "缩略图URL地址", "文章URL地址");
return replayMessage(MaterialEnum.NEWS, item, sender);
```
其中URL信息可以通过网页F12，对响应json进行查看。开发者也可以自行设计数据库存储或添加一个函数来根据素材ID获取其他信息（后续有时间就进行优化，其实规则部分就是采用这种方式，可以看parseRuleToResponseNeed(Rule rule)方法实现）
