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
class Realogin extends Component
{
    
    constructor(props)
    {
        super(props);
        var idpass=new Map();
        idpass.set("admin","123456");
        this.state={keypassword:idpass,tmpid:"",tmppassword:"",islogin:false};
        this.handlekeychange=this.handlekeychange.bind(this);
        this.handlevaluechange=this.handlevaluechange.bind(this);
        this.loginf=this.loginf.bind(this);
        
        
        
    }


handlekeychange(event)
{
    this.setState({tmpid:event.target.value});
};
handlevaluechange(event)
{
    this.setState({tmppassword:event.target.value});
};

loginf()
{
    var id=this.state.tmpid;
    var password=this.state.tmppassword;
    var keypassword=this.state.keypassword;
    
        
        
        $.ajax({
            url: "http://localhost:8080/sslogin",
            type:"POST",
            xhrFields: {
                withCredentials: true
            },
            params:{"contentType": "application/json;charset=utf-8"},
            data:{id:id,password:password},
            success: function f(data) {
                
                console.log(data)
                if(data==1)
                {
                    alert("welcome dear "+id);
                    
                    
                    
                    this.setState({islogin:true});
                    
                }
                else if(data==2)
                {
                    alert("you have been banned");
                }
                else
                {
                    alert("wrong id or password");
                }

            }.bind(this)
        });
        
    
        
        

        
    
};

render()
    {
        var islogin=this.state.islogin;
        var tmpid=this.state.tmpid;
        var tmppassword=this.state.tmppassword;
        
        if(!islogin)
        {
            return(
                <div id="logroot">
                <Paper id="login">
                <div>
                <Avatar id="icon">
                    <LockOutlinedIcon />
                </Avatar>
                </div>                    
                <div >
                <FormControl margin="normal" required fullWidth>
                <InputLabel htmlFor="id">id</InputLabel>
                <Input type="text" id="id" value={tmpid} onChange={this.handlekeychange}></Input>
                </FormControl>
                <br/>
                <FormControl margin="normal" required fullWidth>
                <InputLabel htmlFor="password">password</InputLabel>           
                <Input type="text" id="password" value={tmppassword} onChange={this.handlevaluechange}></Input> 
                </FormControl>
                <br/>          
                <Button onClick={this.loginf} variant="contained" color="primary">login</Button>
                
                </div>
                </Paper>
                </div>
            );
        }
        else{
            return(
                <Redirect to={{pathname:'/',query:{id:tmpid}}}></Redirect>
                )
        }
    };
};
export default Realogin;