Q1：如何部署本项目？
不同的编译器有不同的打开方式，本项目为maven项目，具体打开方式请自行百度，maven配置文件为pom.xml,依赖库等会自动安装。

Q2：数据库如何配置？
1.在数据库中创建数据库admin_manage
2.修改\adminManage\src\main\resources\META-INF\properties\hibernate.properties中
username=root
password=Aa36221512
为实际的数据用户名和密码。
表会自动创建。
