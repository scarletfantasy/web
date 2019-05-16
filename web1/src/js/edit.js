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
import $ from 'jquery';
import '../css/edit.css'
class Edit extends Component
{
    constructor(props)
    {
        super(props);
        this.state={book:[],searchname:""};
        this.handlechange=this.handlechange.bind(this);
        this.handlesave=this.handlesave.bind(this);
        this.handledelete=this.handledelete.bind(this);
        this.reload=this.reload.bind(this);
        this.searchchange=this.searchchange.bind(this);
        this.newbook=this.newbook.bind(this);
    }
    newbook()
    {
        var books=this.state.book;
        var book={bookname:"",isbn:"",number:0,bookimg:"",price:""}
        books.unshift(book)
        this.setState({book:books})
    }
    searchchange(e)
    {
      this.setState({searchname:e.target.value});
    }
    reload()
    {
        $.ajax({
            url:"http://localhost:8080/jpabooklist",
            type:"GET",
              params:{"contentType": "application/json;charset=utf-8"},
              xhrFields: {
                withCredentials: true
            },
               data:{coo:"o"},
              success: function f(data) {
                
                  this.setState({book:data});
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
        var book=this.state.book[i];
        var tmp=JSON.stringify(book);
        console.log(tmp);
        
        
        $.ajax({
            url:"http://localhost:8080/jpaeditsave",
            type:"GET",
            params:{"contentType": "application/json;charset=utf-8"},
            xhrFields: {
                withCredentials: true
            },
            data:{book:tmp},
              success: function f(data) {
                
                  alert("success");

                
              }
          })
          
    }
    handlechange(e)
    {
        var book=this.state.book;
        var tmp=e.target.id;
        var i=tmp.split(".")[0];
        var choose=tmp.split(".")[1];
        
        
        switch(choose)
        {
            case "bookname" :book[i].bookname=e.target.value;break;
            case "isbn" :book[i].isbn=e.target.value;break;
            case "number" :book[i].number=e.target.value;break;
            case "bookimg" :book[i].bookimg=e.target.value;break;
            case "price" :book[i].price=e.target.value;break;
        }
        
        this.setState({book:book});
    }
    render()
    {
        var book=this.state.book;
        var searchname=this.state.searchname;
        var item=[];
        for(var i=0;i<book.length;++i)
        {
            if(searchname==""||book[i].bookname.search(searchname)!=-1)
            {
                item.push(<TableRow>
                    <TableCell ><Input id={i+".bookname"} value={book[i].bookname} onChange={this.handlechange}/></TableCell>
                    <TableCell ><Input id={i+".isbn"}  value={book[i].isbn} onChange={this.handlechange}/></TableCell>
                    <TableCell><Input id={i+".number"}  value={book[i].number} onChange={this.handlechange}/></TableCell>
                    <TableCell><Input id={i+".bookimg"}  value={book[i].bookimg} onChange={this.handlechange}/></TableCell>
                    <TableCell><Input id={i+".price"}  value={book[i].price} onChange={this.handlechange}/></TableCell>
                    <TableCell><button id={i} onClick={this.handlesave}>save</button></TableCell>
                    <TableCell><button id={i} onClick={this.handledelete}>delete</button></TableCell>
                    
                </TableRow>)
            }
            
            
        }
        return(
            <div id="skimpage">
                <Paper>
                    <Input value={this.state.searchname} onChange={this.searchchange} ></Input>
                    <br/>
                    <Button onClick={this.newbook}>new</Button>
                    <Table>   
                    <TableHead>
                    <TableCell>bookname</TableCell>
                    <TableCell>isbn</TableCell>
                    <TableCell>number</TableCell>
                    <TableCell>img</TableCell>
                    <TableCell>price</TableCell>
                    <TableCell>save</TableCell>
                    <TableCell>delete</TableCell>
                    </TableHead>
                    {item}
                    </Table>
                </Paper>
            </div>
            
            
            
        )
    }
}
export default Edit;