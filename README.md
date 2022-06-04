# SpringBootDemo

使用套件：
DB: MySQL

初始化資料：
init.sql

專案內容：
1. 建立SpringBoot 專案
2. 單一表格的CRUD
3. 自訂API錯誤處理回傳格式
4. 建立logging Logback [Spring Boot logging 記錄記起來](https://ithelp.ithome.com.tw/articles/10248974)
5. [Spring Security 的驗證與授權](https://chikuwa-tech-study.blogspot.com/2021/06/spring-boot-security-authentication-and-authorization.html)
6. [基於帳密的驗證與核發 JWT](https://chikuwa-tech-study.blogspot.com/2021/06/spring-boot-username-password-authentication-and-jwt.html)
	基本功能完成，驗證邏輯需再做調整
7. 加入單一表格CRUD的單元測試
8. ItemService - [JPA 使用 Specification 复杂查询和 Criteria 查询](https://blog.wuwii.com/jpa-specification.html)

---
待處理問題:
* 新增失敗sequence會跳，直執行SQL就會有這種狀況，待確認是否為可以解決的問題
