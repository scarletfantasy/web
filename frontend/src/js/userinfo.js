import React, { Component } from 'react';
import Paper from '@material-ui/core/Paper';
import '../css/userinfo.css'
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import { makeStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import Grid from '@material-ui/core/Grid';
import Input from "@material-ui/core/Input";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from '@material-ui/core/InputLabel';
import Home from './home.js'
import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';
import Toolbar from '@material-ui/core/Toolbar';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import $ from 'jquery'
/*
信息保存在state中，可以自行添加props或ajax
*/



class Userinfo extends Component {
    constructor(props) {
        super(props);
        this.state={user:{id:" "}};
      
        
        $.ajax({
            url: "http://101.132.98.60:12346/jpacurrentuser",
            type:"POST",
            params:{"contentType": "application/json;charset=utf-8"},
            xhrFields: {
            withCredentials: true
        },
            success: function f(data) {
            
            this.setState({user:{id:data}});

            }.bind(this)
        })
        this.handleclick=this.handleclick.bind(this);
    }
    handleclick()
    {
        var id="#userimg";
        var filedata=$(id)[0].files[0];
        var formdata=new FormData();
        formdata.append("img",$(id)[0].files[0]);
        formdata.append("id",this.state.user.id);
        formdata.append("houzhui",$(id)[0].value.split(".")[1])
        $.ajax({
            url: "http://101.132.98.60:12346/uploaduserimg",
            type:"POST",
                data:formdata,
                processData:false,
                contentType:false,
                xhrFields:{withCredentials:true},
                success: function f(data) {

                    alert(data);


                }
        })
    }
    render()
    {
        return(
            <div id="userinforoot">
            <Paper>
            <img id="userimage" src={"finduserimg/"+this.state.user.id} />
            <br/>
            <input type="file" id = "userimg" name="userimg"></input>
            <br/>
            <Button onClick={this.handleclick} variant="contained" color="primary">上传</Button>
            <div>
                <h3>id</h3>
                <div>{this.state.user.id}</div>
            </div>
            </Paper>
            </div>
        )
    }
}
export default Userinfo;
