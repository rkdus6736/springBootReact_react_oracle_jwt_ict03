import React, {Component} from 'react';
import classNames from 'classnames';  // npm i classnames -- save   대소문자 주의 

class LoginForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            active: "login",
            id: "",
            password: "",
            firstName: "",
            lastName: "",            
            onLogin: props.onLogin,  // 사용자가 자격증명을 보낸후 상위구성요소가 로그인 양식을 숨길수 있다.
            onRegister: props.onRegister
        };
    };

    // 필드의 업데이트된 값을 state에 저장
    onChangeHandler = (e) => {
        let name = e.target.name;
        let value = e.target.value;
        this.setState({[name]: value});
    }

    // 로그인 처리
    onSubmitLogin = (e) => {
        console.log("<<< onSubmitLogin >>>");
        this.state.onLogin(e, this.state.id, this.state.password);
    }

    // 등록 처리
    onSubmitRegister = (e) => {
        console.log("<<< onSubmitRegister >>>");
        this.state.onRegister(
            e,
            this.state.id,
            this.state.password,
            this.state.firstName,
            this.state.lastName
        );
    };

    render() {
        return(
            <div className="row justify-content-center">
                <div className="col-4">
                    <ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">

                        {/* login 버튼 */}
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "login" ? "active" : "")}
                                id="tab-login" onClick={() => this.setState({active: "login"})}>Login</button>
                        </li>

                         {/* Register 버튼 */}
                         <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "register" ? "active" : "")}
                                id="tab-register" onClick={() => this.setState({active: "register"})}>Register</button>
                        </li>
                    </ul>                    
                </div>

                <div className="tab-content">
                    
                    {/* 로그인 폼 */}
                    <div className={classNames("tab-pane", "fade", this.state.active === "login" ? "show active" : "")} id="pills-login">

                            <form onSubmit={this.onSubmitLogin}>
                                <div className="form-outline mb-4">
                                    <input type="text" id="loginId" name="id" className="form-control" onChange={this.onChangeHandler} />
                                    <label className="form-label" htmlFor="loginId">ID</label>
                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="loginPassword" name="password" className="form-control" onChange={this.onChangeHandler} />
                                    <label className="form-label" htmlFor="loginPassword">Password</label>
                                </div>

                                <button type="submit" className="btn btn-primary btn-block mb-4">Sign in</button>
                            </form>
                    </div>

                    {/* 등록 폼 */}
                    <div className={classNames("tab-pane", "fade", this.state.active === "register" ? "show active" : "")} id="pills-register">
                           
                            <form onSubmit={this.onSubmitRegister}>
                                <div className="form-outline mb-4">
                                    <input type="text" id="registerId" name="id" className="form-control" onChange={this.onChangeHandler} />
                                    <label className="form-label" htmlFor="registerId">ID</label>
                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="registerPassword" name="password" className="form-control" onChange={this.onChangeHandler} />
                                    <label className="form-label" htmlFor="registerPassword">Password</label>
                                </div>

                                <div className="form-outline mb-4">
                                    <input type="text" id="firstName" name="firstName" className="form-control" onChange={this.onChangeHandler} />
                                    <label className="form-label" htmlFor="firstName">First Name</label>
                                </div>

                                <div className="form-outline mb-4">
                                    <input type="text" id="lastName" name="lastName" className="form-control" onChange={this.onChangeHandler} />
                                    <label className="form-label" htmlFor="lastName">Last Name</label>
                                </div>

                                <button type="submit" className="btn btn-primary btn-block mb-4">Sign up</button>
                            </form>
                    </div>
                </div>

            </div>
        )
    }

}

export default LoginForm;