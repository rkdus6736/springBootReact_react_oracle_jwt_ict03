import React, {Component} from 'react';
import WelcomeContent from './WelcomeContent';
import LoginForm from './LoginForm';
import Buttons from './Buttons';
import { request, setAuthToken } from '../helpers/axios_helper';

class AppContent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            componentToShow: "welcome"
        }
    }

    login = () => {
        this.setState({componentToShow: "login"})
    }
   
    logout = () => {
        this.setState({componentToShow: "welcome"})
    }

    onLogin = (e, id, password) => {
        e.preventDefault();     // 폼 제출 시 페이지가 새로고침되지 않도록 기본 동작을 막아, JavaScript로 요청을 처리
        request(        // 사용자가 입력한 ID와 비밀번호를 포함한 POST 요청을 /login 엔드포인트로 보냄.
            "POST",
            "/login",
            {
                id: id,
                password: password
            })
            .then((res) => {
                this.setState({componentToShow: "messages"});   // 로그인 성공 시, 상태를 "messages"로 변경하여 메시지 화면을 표시
                setAuthToken(res.data.token);       // 서버로부터 받은 인증 토큰을 저장
            })
            .catch((error) => {
                this.setState({componentToShow: "welcome"});    // 로그인 실패 시, 상태를 "welcome"으로 변경하여 환영 화면을 다시 표시
                setAuthToken(null);  // 인증 토큰을 null로 설정하여 인증 상태를 초기화
            }
        );
    }
    
    onRegister = (e, id, password, firstName, lastName) => {
        e.preventDefault();
        request(
            "POST",
            "/register",
            {
                id: id,
                password: password,
                firstName: firstName,
                lastName: lastName
            })
            .then((res) => {
                this.setState({componentToShow: "messages"});
                setAuthToken(res.data.token);
            })
            .catch((error) => {
                this.setState({componentToShow: "welcome"});
                setAuthToken(null);
            }
        );
    }

    render() {
        return (
            <div>
                <Buttons login={this.login}
                         logout={this.logout}
                />

                {this.state.componentToShow === "welcome" && <WelcomeContent />}
                {this.state.componentToShow === "login" && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister} />}
                
            </div>
        )
    }
}

export default AppContent;