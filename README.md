# LockerApplication

# 1. 프로젝트 개요

## 1.1. 프로젝트 설명

본 프로젝트는 웹 서버와 앱, IoT 장치를 Block chain을 통해 IoT 장치인 locker들을 Databse로 관리하고, 
웹 서비스에 가입된 회원들과 연동하여 locker를 구매 및 개폐제어를 할 수 있게 하는 것이 목적입니다.

그 중, 이 프로그램은 웹 서비스를 담당합니다.

## 1.2. 프로젝트에 쓰인 목록

* [**Spring boot**](https://projects.spring.io/spring-boot/)
* [**PostgreSQL**](https://www.postgresql.org/)
* [**Mobirise**](https://www.mobirise.org/)

## 1.3. 파일 리스트
```
src/
|-- main/
|------ java/
|---------- com.example.demo
|-------------- config/
|------------------ SecurityConfig.java
|-------------- controller/
|------------------ AjaxController.java
|------------------ MainController.java
|------------------ UserResourceController.java
|-------------- dao/
|------------------ AppRoleDAO.java
|------------------ AppUserDAO.java
|------------------ RoleRepository.java
|------------------ UserRepository.java
|-------------- entity/
|------------------ AppRole.java
|------------------ AppUser.java
|------------------ UserRole.java
|-------------- service/
|------------------ RepositoryServiceImpl.java
|------------------ SignInSuccess.java
|------------------ UserDetailsServiceImpl.java
|-------------- utils/
|------------------ EncrytedPasswordUtils.java
|------------------ WebUtils.java
|-------------- LockerApplication.java
|------ resources/
|---------- static/
|---------- templates/
|-------------- admin/
|------------------ test.html
|-------------- enroll/
|------------------ info.html
|-------------- main/
|------------------ aboutUs.html
|------------------ features.html
|------------------ index.html
|-------------- portal/
|------------------ create.html
|-------------- ajaxtest.html
|-------------- index3.html
|-------------- login.html
|---------- application.properties
|------ webapp
|---------- css/
|---------- enroll/
|---------- fonts/
|---------- images/
|---------- js/
|---------- loginForm/
|---------- main/
|---------- portal/
.mvn/
.settings/
target/
mvnw
mvnw.cmd
pom.xml
README.md
```