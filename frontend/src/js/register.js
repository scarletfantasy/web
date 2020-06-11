import React, { Component } from 'react';
import { Redirect,BrowserRouter as Router, Route, Switch ,Link} from 'react-router-dom';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import '../css/register.css'
import $ from 'jquery';
import Avatar from '@material-ui/core/Avatar';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';

class Register extends Component
{
    constructor(props)
    {
        super(props)
        this.state={id:"",password:"",repeatpassword:"",email:""}
        this.register=this.register.bind(this)
        this.handlechange=this.handlechange.bind(this)
    }
    handlechange(e)
    {
        var s=e.target.id
        var value=e.target.value
        switch(s)
        {
            case "id":this.setState({id:value});break;
            case "password":this.setState({password:value});break;
            case "repeatpassword":this.setState({repeatpassword:value});break;
            case "email":this.setState({email:value});break;
        }

    }
    register()
    {
        var password=this.state.password
        var repeatpassword=this.state.repeatpassword
        var email=this.state.email
        var s=email.split("@")
        
        if(password!=repeatpassword)
        {
            alert("please check your password")
        }
        else if(s.length!=2)
        {
            alert("wrong format of email")
        }
        else if(s[1].split(".").length<2)
        {
            alert("wrong format of email")
        }
        else{
            $.ajax({
                url: "http://101.132.98.60:12346/jparegister",
                type:"POST",
                params:{"contentType": "application/json;charset=utf-8"},
                xhrFields: {
                    withCredentials: true
                },
                data:this.state,
                success: function f(data) {
                    alert(data);
    
                }.bind(this)
            });
        }
        
    }
    
    render()
    {
        return(
                <div>
                <Paper id="register">
                <FormControl margin="normal" required fullWidth>
                <InputLabel htmlFor="id">id</InputLabel>
                <Input type="text" id="id" value={this.state.id} onChange={this.handlechange}></Input>
                </FormControl>
                <FormControl margin="normal" required fullWidth>
                <InputLabel htmlFor="password">password</InputLabel>
                <Input type="text" id="password" value={this.state.password} onChange={this.handlechange}></Input>
                </FormControl>
                <FormControl margin="normal" required fullWidth>
                <InputLabel htmlFor="repeatpassword">repeatpassword</InputLabel>
                <Input type="text" id="repeatpassword" value={this.state.repeatpassword} onChange={this.handlechange}></Input>
                </FormControl>
                <FormControl margin="normal" required fullWidth>
                <InputLabel htmlFor="email">email</InputLabel>
                <Input type="text" id="email" value={this.state.email} onChange={this.handlechange}></Input>
                </FormControl>
                <Button onClick={this.register} variant="contained" color="primary">register</Button>
                </Paper>
                </div>

        )
    }
}
export default Register