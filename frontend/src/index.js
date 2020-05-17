import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Form from 'antd/es/form'
//import App from './App';
import * as serviceWorker from './serviceWorker';
import RegistrationForm from './App';


//var mountNode = document.getElementById('container');

const WrappedRegistrationForm = Form.create({ name: 'register' })(RegistrationForm);
ReactDOM.render(<WrappedRegistrationForm />, document.getElementById("root"));


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
