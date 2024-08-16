# npx create-react-app front-end
# cd front-end
# npm start

# npm i classnames --save       
- package.json에 "classnames": "^2.5.1", 추가됨
# npm i bootstrap               
- "bootstrap": "^5.3.3", 추가됨


# public 에서 favicon.ico / index.html 제외한 나머지 4개파일 삭제

# src > App.css, App.js, index.js 제외한 나머지 5개 파일 삭제



#--------------------------

# App.css 수정
# Header.js 작성
# AppContent.js 작성
# WelcomeContent.js 작성


#---------------------------
# react_login_tbl 테이블 생성

# 스프링부트 contoller / AuthController
-- http://localhost:8081  실행
-- 시큐리티 username:user , password:부트에서 생성한 비밀번호 붙여넣기(콘솔에서 제공)
*** 플젝생성시 pom.xml에 <dependency> jwt:시큐리티 추가(중요), 부트 로고 아래에 security password가 자동 생성

# npm install axios 
- 스프링부트랑 통신 "axios": "^1.7.4", 추가됨

# helpers/axios_helper.js 작성
- 백엔드랑 통신
- 로그인 완료시  JWT를 저장한다.

# 스프링부트 config/WebConfig.java 작성

# components/LoginForm.js 작성
# npm i classnames -- save  대소문자 주의
-- "classnames": "^2.5.1", 추가됨

# config / SecurityConfig.java

