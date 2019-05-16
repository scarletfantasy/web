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
  import '../css/detail.css'
  import $ from 'jquery'
  import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
class Detail extends Component
{
    constructor(props)
    {
        super(props);
        this.state={book:this.props.location.query.book,number:0,leave:false}
        this.handlechange=this.handlechange.bind(this);
        this.handleclick=this.handleclick.bind(this);
        
    }
    
    handlechange(e)
    {
        var re = /^[0-9]+.?[0-9]*$/;
        if(!re.test(e.target.value))
        {
            alert("not a number");
        }
        else if(e.target.value>this.state.book.number)
        {
            alert("out of range");
        }
        else{
            this.setState({number:e.target.value});
        }
        
    }
    handleclick()
    {
        var book=this.state.book;
        var sbook=JSON.stringify(book);
        var id=this.state.id;
        var number=this.state.number;
        var time=new Date();
        var stime=time.toString();
        console.log(stime);
        
        $.ajax({
            url: "http://localhost:8080/jpaaddtocart",
            type:"POST",
            xhrFields: {
                withCredentials: true
            },
            params:{"contentType": "application/json;charset=utf-8"},
            data:{book:sbook,number:number,time:stime},
            success: function f(data) {
                if(data=="login")
                {
                    alert("please log in")
                }
                else{
                    alert("success")
                }

            }.bind(this)
        });
        this.setState({leave:true})
    }
    render()
    {
        var book=this.props.location.query.book;
        if(!this.state.leave)
        {
            return(
                <div>
                    <Paper id="detail">
                    <h4>{book.bookname}</h4>
                    <img src={book.bookimg}></img>
                    <br/>
                    <FormControl margin="normal" required fullWidth>
                    <InputLabel htmlFor="amount">库存:剩余数量{book.number}</InputLabel>  
                    <Input value={this.state.number} onChange={this.handlechange}></Input>
                    </FormControl>
                    <br/>
                    <Button onClick={this.handleclick} variant="contained" color="primary">submit</Button>
                    </Paper>
                    
                </div>
                );
        }
        else{
            
            return(
                <Redirect to={{pathname:'/',query:{id:this.props.location.query.id,order:{book:book,number:this.state.number}}}}></Redirect>
            )
        }

            
        
        
        
    }
}
export default Detail;