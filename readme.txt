1)APP的每一次HTTP请求都会实现：BusiGateService类
	实现方法：public Map<String, String> busi(Map<String, String> para, LoginInfo user)
			throws AppException
	入参：para:表示APP传入的参数键值对。
	user:表示用户登陆信息
	
	返回结果：map信息。如果值是返回列表或者对象，转换成JSON格式。

2）实现该方法后：在conf/spring里建立配置文件：service-ezy.xml，bean的ID格式：req_"业务编码"_service

3）完成后写文档，文档类似于 重庆法院APP接口文档.docx