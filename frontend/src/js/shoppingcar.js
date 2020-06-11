import React, { Component } from 'react';
import {
    BrowserRouter as Router,
    Route,
    Link,
    Redirect,
    withRouter
  } from "react-router-dom";
import '../css/shoppingcar.css'
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import $ from 'jquery'
class Shoppingcar extends Component
{
    constructor(props)
    {
        super(props);
        this.state={isfinish:false,cart:[]}
        this.handlechange=this.handlechange.bind(this)
        this.delete=this.delete.bind(this)
        this.reload=this.reload.bind(this)

    }
    reload()
    {
        $.ajax({
            url:"http://101.132.98.60:12346/jpashowcart",
            type:"GET",
            xhrFields: {
                withCredentials: true
            },
              params:{"contentType": "application/json;charset=utf-8"},
               data:{isfinish:0},
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
    delete(e)
    {
        var i=e.target.id
        console.log(i)
        var order=this.state.cart[i]
        console.log(order)
        var id=order.id
        console.log(id)
        $.ajax({
            url:"http://101.132.98.60:12346/jpadeleteorder",
            type:"POST",
            xhrFields: {
                withCredentials: true
            },
              params:{"contentType": "application/json;charset=utf-8"},
               data:{id:id},
              success: function f(data) {
                
                  alert(data)
                  this.reload()
                
              }.bind(this)
          })
    }
    componentDidMount()
    {
        this.reload()
    }
    
    handlechange()
    {
        
        $.ajax({
            url:"http://101.132.98.60:12346/jpacleancart",
            type:"POST",
            xhrFields: {
                withCredentials: true
            },
            params:{"contentType": "application/json;charset=utf-8"},
               
              success: function f(data) {
                if(data=="login")
                {
                    alert("please login")
                }
                else{
                    console.log(data)
                if(data.length>0)
                {
                    alert("some book dont have enough number")
                }
                this.reload()
                }
                
                
              }.bind(this)
          })
          
          /*this.setState({cart:[]});
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
        this.setState({book:book,shoppingcar:shoppingcar,isfinish:true});*/
    }
    render()
    {
        var item=[];
        /*var tmp=this.props.location.query.shoppingcar;*/
        var tmp=this.state.cart;
        
        for(var i=0;i<tmp.length;++i)
        {
            var cost=tmp[i].number*tmp[i].book.price;
            cost = cost.toFixed(2);
            item.push(
                <TableRow>
                    <TableCell>{tmp[i].book.isbn}</TableCell>
                    <TableCell>{tmp[i].time}</TableCell>
                    <TableCell>{tmp[i].number}</TableCell>
                    <TableCell>{tmp[i].book.price}</TableCell>
                    <TableCell>{cost}</TableCell>
                    <TableCell ><button id={i} onClick={this.delete}>delete</button></TableCell>
                </TableRow>
            )
        }
        if(!this.state.isfinish)
        {
            return(
                <div>
                <Table>
                    <TableHead>
                        <TableCell>isbn</TableCell>
                        <TableCell>time</TableCell>
                        <TableCell>amount</TableCell>
                        <TableCell>price</TableCell>
                        <TableCell>total cost</TableCell>
                        <TableCell>delete</TableCell>
                        
                    </TableHead>
                    {item}
                </Table>
                <Button onClick={this.handlechange} variant="contained" color="primary">pay</Button>
                
                </div>
            )
        }
        else{
            return(
                <Redirect to={{pathname:'/',query:{book:this.state.book,shoppingcar:[]}}}></Redirect>
                )
        }
        
    }
}
export default Shoppingcar;