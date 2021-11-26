# 说明

<hr width = 37% />

> 这是一个简易的HTTP文件服务器，支持文件的上传于下载，以及相关文件信息的查看

## 服务端提供的三个接口

### 1.上传文件

`[FileServerService](http://localhost:8080/fs/file/upload)`

POST请求需要携带表单域中的文件数据

### 2.下载文件

`[FileServerService](http://localhost:8080/fs/file/download)`

GET请求需要携带UUID

### 3.查看信息

`[FileServerService](http://localhost:8080/fs/file/info)`

GET请求需要携带UUID

## 数据库转储文件

提供了[SQL文件](./assets/FileServer.sql)，方便本地化测试。(文件位于assets目录下)

# 测试使用

<hr width = 37% />

## 进入浏览器访问客户端地址

客户端地址：`[FileServerClient](http://localhost:5907/fileClient)`

## 选择一个文件后上传

![image-1](.\assets\1.png)

可以看到回显的Uid即为上传成功

![image-2](.\assets\2.png)

也可以在后台看到日志info

![image-3](.\assets\3.png)

## 接下来点击下载

![image-4](.\assets\4.png)

可以看到下载后的文件和上传的文件一致	

![image-6](.\assets\6.png)

## 点击查看json可以看到文件元信息

![image-7](.\assets\7.png)

![image-5](.\assets\5.png)

同样的，所有操作在后台都有记录

![image-8](.\assets\8.png)

## 打开数据库查看数据信息

![image-9](.\assets\9.png)

也可以看到文件信息都成功保存了。





