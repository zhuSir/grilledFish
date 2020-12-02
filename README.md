# b-web 新房源系统
>###关于文档维护说明：
大家可以编写md文档来说明一些公用的方法或者代码功能以及设计，markdown语言速成可以移步
[MarkDown语言速成](./document/MDBOOK.md)

>###swagger2地址：
http://localhost:8086/docs.html

>###代码生成器地址：
http://localhost:8086/generate

目前只可以生成entity，字段需要修改为正确的驼峰式;

>###分页说明：
 目前提供一种基于MybatisPlus构造器的分页方法，简化sql，加快开发，有需要的同学可以移步
 [分页说明](./document/PageHelp.md) 

>###接口设计手册：
 [接口命名说明](./document/APIGuidelines.md)
 
>###登录相关：
 [相关说明](./document/LoginRelated.md) 
  
>###图片说明：
 图片都存在阿里云OSS服务器上，因此可以用配置过的参数做缩略图和水印图等等，

```
//小图
export const smallThumb = '?x-oss-process=style/thumb';
//中图
export const midThumb = '?x-oss-process=style/middlethumb';
//大图
export const bigThumb = '?x-oss-process=style/bigthumb';
//水印
export const water = '?x-oss-process=style/water';
```
比如一张图URL 路径为 
`http://img.0be.cn/FileUpload/image/xxxx.jpg`
那么他的最小缩略图就是 
`http://img.0be.cn/FileUpload/image/xxxx.jpg?x-oss-process=style/thumb`

>###参数获取说明：
需要传入公司id 和规则编号 子类型没有的话填空
```
    SetUpService
    /**
     * 获取系统参数
     * @param companyId 公司id
     * @param ruleNo 规则编码
     * @param paramStr 规则字符串（一般定位具体哪个规则）
     * @return
     */
    SysRuleParamsDto getSysRuleParams(Integer companyId,Integer ruleNo,String paramStr);
```


>###权限控制说明：
[相关说明](./document/RuleHelp.md)

https://shimo.im/docs/TrX83ktVWyyDt8Cv/ 《权限控制说明文档》



>###项目一键部署发布：
 [相关说明](./document/PublishCI.md) 
 
 
>###数据库修改相关脚步统一存放处：
 [相关说明](./document/sql/) 
 
 >###任务调度中心说明：

  为了统一管理定时作业，将定时作业交由调度中心管理，只需要完成作业步骤，作业步骤可以在
  `BWebApplicationTests`中debug，功能正常后在调度中心配置执行时间即可。
  本地要被调度中心执行可以用内网穿透。
  地址：http://121.41.13.216:8085/xxl-job-admin
  
  
  账号：baseuser
  
  
  密码：base8899
  
  
  [相关说明](https://www.jianshu.com/p/fa7186bea84b) 
  
  >###微信扫码相关：
   [相关说明](./document/ScanUploadRelate.md) 