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
        e.preventDefault();
        request(
            "POST",
            "/login",
            {
                id: id,
                password: password
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