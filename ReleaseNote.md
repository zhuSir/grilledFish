<!--更新记录-->
## 2020-06-09 ：
 * C#首页优化，推荐房源取数修改(需要更新存储过程:D_GetRecommendHouse)
 * C#带看客户房源取数修改，过滤非在售房源
 * 世纪网公众号消息出现“鑫家网”字样问题，修改为配置项
 * 修改一键拨号，先点击经纪人一键拨号，然后在点击业主一键拨号，跳过验证问题
 * 修改点击房源详情界面出现索引超长问题
 * 小程序租赁修改楼盘名称校验,修改时间选择报错问题
 + 小程序租赁添加带看功能
 
 ##2020-06-15
  +锁定列表锁定按钮新增防止重复点击功能
  *c#修复带看客户出现的不明数字问题
  *pc修复取消角色人和跟进弹窗鼠标移至提交按钮字样消息问题
  *小程序修复成交房源楼盘搜索出现空白选项问题
  *pc端修复暂不售房源转在售按钮被置灰问题
  *pc端和小程序修复楼层搜索不准确和列表出现楼层未空问题(这个需要执行sql脚本D_WY_Room_Update，修改房源表的楼层属性，并且同步更新es数据)
  *PC端修复审核列表取代申请通过后原图和旧图一样的问题(需执行脚本SaleUploadVideoTbl.sql,insetTable和执行插入脚本)
  
## 2020-06-17
* 公司logo和背景图能前台配置(需要发c#，需要更新login.html页面注意检查登陆器代码，需要更新globleSkin.js)
* 个人跟单房源跟进需要个人跟单才能重置,个人跟单房源通话只有跟单人通话30秒才能重置跟进时间((需要更新存储过程D_InsertTelFollow)
* 首页核心盘改外网房源。外网房源索引改成全部在售
* 业绩修复底价比对计算错误的问题(需要发c#)
* 解决小程序我的资源楼盘查询无法选到房间号的问题
* 解决小程序暂不售查询含有我售房源
*小程序解决分享房源生成海报排版问题和经纪人显示的号码为真实号码问题
*PC端验真详情界面小区介绍，户型结构等字段未显示问题
*PC端房源详情点击修改房屋证件未保存问题
*解决世纪网验真列表已验真但是系统里找不到这套房子的问题

##  2020-06-18
+编辑按钮实现数据权限控制
*修复公司管理皮肤配置，图片回显问题
*修复小程序待验真编辑按钮被置灰问题
*修复房源申请跟单人头像无法显示问题
*修复人员异动详情界面显示空白问题

## 2020-06-23
*解决了 Bug （加急）新房源系统-》新系统管理-》人员异动 新增人员无法审核通过 
*解决了 Bug 扫码进入系统首页 强制阅读公告的页面和右上角退出系统功能隐藏 
*解决了 Bug 小程序-房源跟单人和实勘人不一致的情况下，跟单人还可以编辑删除实勘图片，请修改 2881
*解决了 Bug 房源详情页-》申请钥匙人 审核通过后，头像未显示 2871
*解决了 Bug 房源详情页 申请实勘人可以连续上传两个视频 2872
*解决了 Bug 小程序-资源库》店公共盘，在售无跟单、暂不售里的房源详情页“添加印象”无法操作，报错，请修改 2873
*评论了 Bug 【正式服】PC后台》新房源系统》审核列表，20几条数据出现null字样需求处理 2896
*指派了 Bug 【正式服】PC后台》新房源系统》审核列表，20几条数据出现null字样需求处理 2896
*解决了 Bug PC后台》新房源系统详情页申请委托人和取代委托人时“委托类型”没有选择也能保存成功，请修改 2895
*解决了 Bug pc后台》我的房源》审核列表，“审核类型”出现null字样，请修改 2866
* 完善业绩审核页面跳转房源详情
* 修复新增用户介绍人搜索功能
*业绩历史合同添加新增了可以搜索选择房源，历史合同添加后房源转在售。历史合同的企业管理费用比例变的可的。修改了历史合同的业绩计算公式。
*扫码登录修改，查询登录用户时过滤openid为空的情况，防止登录到未绑定微信号的账户上
