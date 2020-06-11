import React, { Component } from 'react';
import { Redirect,BrowserRouter as Router, Route, Switch ,Link} from 'react-router-dom';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import '../css/login.css'
import $ from 'jquery';
import Avatar from '@material-ui/core/Avatar';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
class Logout extends Component
{
    
    constructor(props)
    {
        super(props);
        
        
        
        
    }


componentWillMount()
{
    $.ajax({
        url: "http://101.132.98.60:12346/logout",
        type:"POST",
        xhrFields: {
            withCredentials: true
        },
        params:{"contentType": "application/json;charset=utf-8"},
        
        success: function f(data) {
            alert("success");

        }.bind(this)
    });
}

render()
    {
        
        
        
            return(
                
                <Redirect to={{pathname:'/',query:{id:" "}}}></Redirect>
                )
            }
};
export default Logout;