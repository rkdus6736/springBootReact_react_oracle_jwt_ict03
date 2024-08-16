import React, {Component} from "react";

class WelcomeContent extends Component{

    render(){
        return(
            <div className="row justy-content-md-center">
                <div className="jumbotron jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-4">Welcome</h1>
                        <p className="lead">login to see protected content.</p>
                    </div>
                </div>

            </div>
        )
    }
}

export default WelcomeContent;