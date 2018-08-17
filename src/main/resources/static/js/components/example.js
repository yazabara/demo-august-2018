var Example = function Example(props) {
    return React.createElement(
        "div",
        null,
        React.createElement(
            Reactstrap.Alert,
            {color: "primary"},
            "This is a primary Reactstrap.Alert \u2014 check it out!"
        ),
        React.createElement(
            Reactstrap.Alert,
            {color: "secondary"},
            "This is a secondary Reactstrap.Alert \u2014 check it out!"
        ),
        React.createElement(
            Reactstrap.Alert,
            {color: "success"},
            "This is a success Reactstrap.Alert \u2014 check it out!"
        ),
        React.createElement(
            Reactstrap.Alert,
            {color: "danger"},
            "This is a danger Reactstrap.Alert \u2014 check it out!"
        ),
        React.createElement(
            Reactstrap.Alert,
            {color: "warning"},
            "This is a warning Reactstrap.Alert \u2014 check it out!"
        ),
        React.createElement(
            Reactstrap.Alert,
            {color: "info"},
            "This is a info Reactstrap.Alert \u2014 check it out!"
        ),
        React.createElement(
            Reactstrap.Alert,
            {color: "light"},
            "This is a light Reactstrap.Alert \u2014 check it out!"
        ),
        React.createElement(
            Reactstrap.Alert,
            {color: "dark"},
            "This is a dark Reactstrap.Alert \u2014 check it out!"
        )
    );
};