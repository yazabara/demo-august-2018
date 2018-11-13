var Example = function Example(props) {
    return React.createElement(
        Reactstrap.Form,
        null,
        React.createElement(
            Reactstrap.FormGroup,
            null,
            React.createElement(
                Reactstrap.Label,
                {"for": "username"},
                "Email"
            ),
            React.createElement(Reactstrap.Input, {
                type: "input",
                name: "username",
                id: "username",
                placeholder: "enter your username"
            })
        ),
        React.createElement(
            Reactstrap.FormGroup,
            null,
            React.createElement(
                Reactstrap.Label,
                {"for": "password"},
                "Password"
            ),
            React.createElement(Reactstrap.Input, {
                type: "password",
                name: "password",
                id: "password",
                placeholder: "enter your password"
            })
        ),
        React.createElement(
            Reactstrap.Button,
            "/login",
            "Submit"
        )
    );
};