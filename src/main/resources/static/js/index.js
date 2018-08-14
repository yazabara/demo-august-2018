class Field extends React.Component {

    constructor(props) {
        super(props);

        this.handleChange = e => {
            this.setState({
                value: e.target.value
            });
        };

        this.state = {
            value: props.value
        };
    }

    render() {
        return React.createElement("div",
            null,
            React.createElement("label", {
                    htmlFor:
                    this.props.id
                },
                this.props.label
            ),
            React.createElement("input", {
                id: this.props.id,
                onChange: this.handleChange,
                value: this.state.value
            }));
    }
}

ReactDOM.render(React.createElement(Field, {
    id: "login-input",
    label: "Login",
    value: "user"
}), document.querySelector('#root'));