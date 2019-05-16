import React, { Component } from 'react';
import {
    BrowserRouter as Router,
    Route,
    Link,
    Redirect,
    withRouter
  } from "react-router-dom";
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import $ from 'jquery';
import '../css/edit.css'
class Usermanage extends Component
{
    constructor(props)
    {
        super(props);
        this.state={users:[]};
        this.handlechange=this.handlechange.bind(this);
        this.handlesave=this.handlesave.bind(this);
        this.handledelete=this.handledelete.bind(this);
        this.reload=this.reload.bind(this)
    }
    reload()
    {
        $.ajax({
            url:"http://localhost:8080/jpashowuser",
            type:"GET",
              params:{"contentType": "application/json;charset=utf-8"},
              xhrFields: {
                withCredentials: true
            },
               data:{coo:"o"},
              success: function f(data) {
                
                  this.setState({users:data});
                console.log(data);
                
              }.bind(this)
          })
    }
    handledelete(e)
    {
        var i=e.target.id;
        var isbn=this.state.book[i].isbn
        $.ajax({
            url:"http://localhost:8080/jpaeditdelete",
            type:"GET",
            xhrFields: {
                withCredentials: true
            },
              params:{"contentType": "application/json;charset=utf-8"},
               data:{isbn:isbn},
              success: function f(data) {
                
                  alert("success")
                  this.reload()
                
              }.bind(this)
          })
    }
    componentDidMount()
    {
        this.reload()
    }
    handlesave(e)
    {
        var i=e.target.id;
        var user=this.state.users[i];
        var tmp=JSON.stringify(user);
        console.log(tmp);
        
        
        $.ajax({
            url:"http://localhost:8080/jpauseredit",
            type:"GET",
            params:{"contentType": "application/json;charset=utf-8"},
            xhrFields: {
                withCredentials: true
            },
            data:{user:tmp},
              success: function f(data) {
                
                  alert("success");

                
              }
          })
          
    }
    handlechange(e)
    {
        var users=this.state.users;
        var tmp=e.target.name;
        debugger;
        var i=tmp.split(".")[0];
        var choose=tmp.split(".")[1];
        
        
        switch(choose)
        {
            case "id" :users[i].id=e.target.value;break;
            case "password" :users[i].password=e.target.value;break;
            case "role" :users[i].role=e.target.value;break;
        
        }
        
        this.setState({users:users});
    }
    render()
    {
        var users=this.state.users;
        var item=[];
        for(var i=0;i<users.length;++i)
        {
            var rolestring=i+".role";
            item.push(<TableRow>
                <TableCell ><Input name={i+".id"} value={users[i].id} onChange={this.handlechange}/></TableCell>
                <TableCell ><Input name={i+".password"}  value={users[i].password} onChange={this.handlechange}/></TableCell>
                <TableCell><Select name={i+".role"} value={users[i].role} onChange={this.handlechange} inputProps={{name:i+".role"}}>
            <MenuItem  value="">
              <em>None</em>
            </MenuItem>
            <MenuItem  value={"ADMIN"}>ADMIN</MenuItem>
            <MenuItem  value={"USER"}>USER</MenuItem>
            <MenuItem  value={"BAN"}>BAN</MenuItem>
            </Select>
            </TableCell>
                
                <TableCell><button id={i} onClick={this.handlesave}>save</button></TableCell>
                <TableCell><button id={i} onClick={this.handledelete}>delete</button></TableCell>
                
            </TableRow>)
        }
        return(
            <Table>
                
                <TableHead>
                <TableCell>id</TableCell>
                <TableCell>password</TableCell>
                <TableCell>role</TableCell>
                
                <TableCell>save</TableCell>
                <TableCell>delete</TableCell>
                </TableHead>
                {item}
            </Table>
        )
    }
}
export default Usermanage;