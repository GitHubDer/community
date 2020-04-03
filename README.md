## 社区

## 资料
[百度](https://baidu.com)\
[谷歌](https://www.google.com)\
[Spring 文档](https://spring.io/guides)\
[es社区](https://elasticsearch.cn/explore)\
[Spring Web](https://spring.io/guides/gs/serving-web-content)\
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)\
[Bootstrap](https://v3.bootcss.com/getting-started)\
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)\
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)\
[Markdown 插件](http://editor.md.ipandao.com/)

## 工具
[Git](https://git-scm.com/download)\
[Visual-Paradigm](https://www.visual-paradigm.com)\
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)\
[Lombok](https://www.projectlombok.org/)\
[inconfont](https://www.iconfont.cn/)

## 脚本
```sql
create table USER
(
	ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT
);
```
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
```git command
git status
git add .
git commit -m 'commit name'
git push -u origin master
```