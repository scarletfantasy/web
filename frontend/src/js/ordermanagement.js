import React, { Component } from 'react';
import {
    BrowserRouter as Router,
    Route,
    Link,
    Redirect,
    withRouter
  } from "react-router-dom";
  import '../css/order.css';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import $ from 'jquery'
class Ordermanagement extends Component
{
    constructor(props)
    {
        super(props);
        this.state={user:" ",cart:[],from:"2000-01-01",to:"2099-12-31",book:"",userid:""}
        this.handlechange=this.handlechange.bind(this)
        this.handlefrom=this.handlefrom.bind(this)
        this.handleto=this.handleto.bind(this)
        this.handleuser=this.handleuser.bind(this)
        this.handlebook=this.handlebook.bind(this)
        $.ajax({
            url: "http://localhost:8080/jpacurrentuser",
            type:"POST",
            params:{"contentType": "application/json;charset=utf-8"},
            xhrFields: {
              withCredentials: true
          },
            success: function f(data) {
              
              this.setState({user:data});
              
            }.bind(this)
          })
    }
    handlefrom(e)
    {
        this.setState({from:e.target.value})
    }
    handleto(e)
    {
        console.log(e.target.value)
        this.setState({to:e.target.value})
    }
    handlebook(e)
    {
        this.setState({book:e.target.value})
    }
    handleuser(e)
    {
        this.setState({userid:e.target.value})
    }
    componentDidMount()
    {
        $.ajax({
            url:"http://localhost:8080/jpashowhistory",
            type:"GET",
            xhrFields: {
                withCredentials: true
            },
              params:{"contentType": "application/json;charset=utf-8"},
               data:{isfinish:"1"},
              success: function f(data) {
                if(data=="login")
                {
                    alert("please login")
                }
                else{
                    this.setState({cart:data});
                console.log(data);
                }
                  
                
              }.bind(this)
          })
    }
    handlechange()
    {
        var shoppingcar=this.props.location.query.shoppingcar;
        var book=this.props.location.query.book;
        for(var i=0;i<shoppingcar.length;++i)
        {
            for(var j=0;j<book.length;++j)
            {
                if(shoppingcar[i].book.isbn==book[j].isbn)
                {
                    book[j].number-=shoppingcar[i].number;
                }
            }
        }
        this.setState({book:book,shoppingcar:shoppingcar,isfinish:true});
    }
    render()
    {
        var item=[];
        var tmp=this.state.cart;
        var from=this.state.from;
        var to=this.state.to;
        var ftime=new Date(from)
        var ttime=new Date(to)
        console.log(from)
        console.log(to)
        var count=0;
        var bookcount=0;
        var cost=0;
        var userid=this.state.userid;
        var book=this.state.book;
        for(var i=0;i<tmp.length;++i)
        {
            var time=new Date(tmp[i].time)
            if(ftime<time&&time<ttime&&(book==""||tmp[i].isbn.search(book)!=-1)&&(userid==""||tmp[i].userid.search(userid)!=-1))
            {
                if(this.state.user!="admin")
                {
                    item.push(
                        <TableRow>
                            <TableCell>{tmp[i].id}</TableCell>
                            <TableCell>{tmp[i].isbn}</TableCell>
                            <TableCell>{tmp[i].time}</TableCell>
                            <TableCell>{tmp[i].number}</TableCell>
                        </TableRow>
                    )
                }
                else{
                    item.push(
                        <TableRow>
                            <TableCell>{tmp[i].id}</TableCell>
                            <TableCell>{tmp[i].isbn}</TableCell>
                            <TableCell>{tmp[i].time}</TableCell>
                            <TableCell>{tmp[i].number}</TableCell>
                            <TableCell>{tmp[i].userid}</TableCell>
                        </TableRow>
                    )
                }
                
                ++count;
                bookcount=bookcount+tmp[i].number
                cost=cost+tmp[i].number*tmp[i].price
            }
            
            
        }
        cost = cost.toFixed(2);
        if(this.state.user!="admin")
        {
            return(
                <div>
                    <Paper id="select">
                    <div>
                    
                    
                    <FormControl margin="normal" required fullWidth>
                    <InputLabel htmlFor="from">from</InputLabel>
                    <Input value={this.state.from} onChange={this.handlefrom}>from</Input>
                    </FormControl>
                    <FormControl margin="normal" required fullWidth>
                    <InputLabel htmlFor="to">to</InputLabel>
                    <Input value={this.state.to} onChange={this.handleto}>to</Input>
                    </FormControl>
                    <h3>订单总数：{count}  购书总数：{bookcount}本  总花费：{cost}</h3>
                    
                </div>
                
                <Table>
                    <TableHead>
                        <TableCell>id</TableCell>
                        <TableCell>isbn</TableCell>
                        <TableCell>time</TableCell>
                        <TableCell>number</TableCell>
                    </TableHead>
                    {item}
                </Table>
                </Paper>
                
                </div>
            )
        }
        else{
            return(
                <div>
                    <Paper id="select">
                    <div>
                    
                    
                    <FormControl margin="normal" required fullWidth>
                    <InputLabel htmlFor="from">from</InputLabel>
                    <Input value={this.state.from} onChange={this.handlefrom}>from</Input>
                    </FormControl>
                    <FormControl margin="normal" required fullWidth>
                    <InputLabel htmlFor="to">to</InputLabel>
                    <Input value={this.state.to} onChange={this.handleto}>to</Input>
                    </FormControl>
                    <FormControl margin="normal" required fullWidth>
                    <InputLabel htmlFor="to">book</InputLabel>
                    <Input value={this.state.book} onChange={this.handlebook}>book</Input>
                    </FormControl>
                    <FormControl margin="normal" required fullWidth>
                    <InputLabel htmlFor="to">userid</InputLabel>
                    <Input value={this.state.userid} onChange={this.handleuser}>userid</Input>
                    </FormControl>
                    <h3>订单总数：{count}  购书总数：{bookcount}本  总花费：{cost}</h3>
                    
                </div>
                
                <Table>
                    <TableHead>
                        <TableCell>id</TableCell>
                        <TableCell>isbn</TableCell>
                        <TableCell>time</TableCell>
                        <TableCell>number</TableCell>
                        <TableCell>user</TableCell>
                    </TableHead>
                    {item}
                </Table>
                </Paper>
                
                </div>
                )
        }
        
    }
}
export default Ordermanagement;