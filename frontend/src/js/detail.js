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
        this.state={book:{},number:0,leave:false,introduction:"",comments:[],comment:""}
        this.handlechange=this.handlechange.bind(this);
        this.handleclick=this.handleclick.bind(this);
        this.loaddata=this.loaddata.bind(this);
        this.handletextchange=this.handletextchange.bind(this);
        this.handletextclick=this.handletextclick.bind(this);
        
    }
    componentWillMount()
    {
        this.loaddata();
    }
    loaddata()
    {
        var isbn=window.location.href.split("#")[1].split("/")[2];
        console.log(isbn);
        $.ajax({
            url: "http://101.132.98.60:12346/getdetail",
            type:"POST",
            params:{"contentType": "application/json;charset=utf-8"},
            data:{isbn:isbn},
            xhrFields: {
              withCredentials: true
            },
            success: function f(data) {
              if(data.comments!=null)
              {
                this.setState({book:data.book,introduction:data.comments.introduction,comments:data.comments.comments});
              }
              else{
                this.setState({book:data.book,introduction:""});
              }
              
    
            }.bind(this)
          })
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
            url: "http://101.132.98.60:12346/jpaaddtocart",
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
    handletextchange(e)
    {
        this.setState({comment:e.target.value});
    }
    handletextclick()
    {
        var isbn=window.location.href.split("#")[1].split("/")[2];
        $.ajax({
            url: "http://101.132.98.60:12346/jpaaddcomment",
            type:"POST",
            xhrFields: {
                withCredentials: true
            },
            params:{"contentType": "application/json;charset=utf-8"},
            data:{comment:this.state.comment,isbn:isbn},
            success: function f(data) {
                alert("success");

            }.bind(this)
        });
    }
    render()
    {
        
        var item=[];

        var comments=this.state.comments;
        if(comments!=null)
        {
            for(var i=0;i<comments.length;++i)
            {
                item.push(
                    <div>
                    <Paper id="talk">
                    <div>{i+"楼"}</div>
                    <div>{comments[i]}</div>
                    </Paper>
                    </div>
                )
            }
        }
        
        var book=this.state.book;
        if(!this.state.leave)
        {
            return(
                <div>
                    <Paper id="detail">
                    <h4>{book.bookname}</h4>
                    <img src={"http://101.132.98.60:12346/findimg/"+book.isbn}></img>
                    <br/>
                    <div>{this.state.introduction}</div>
                    <br/> 
                    <FormControl margin="normal" required fullWidth>
                                      
                    <InputLabel htmlFor="amount">库存:剩余数量{book.number}</InputLabel>                      
                    <Input value={this.state.number} onChange={this.handlechange}></Input>
                    </FormControl>
                    <br/>
                    <Button onClick={this.handleclick} variant="contained" color="primary">submit</Button>
                    {item}
                    <textarea id="board" onChange={this.handletextchange} value={this.state.comment} cols="5" rows="3" ></textarea>
                    <Button onClick={this.handletextclick} variant="contained" color="primary">发表</Button>
                    </Paper>
                    
                    
                </div>
                );
        }
        else{
            
            return(
                /*<Redirect to={{pathname:'/',query:{id:this.props.location.query.id,order:{book:book,number:this.state.number}}}}></Redirect>*/
                <Redirect to={{pathname:'/'}}></Redirect>
            )
        }

            
        
        
        
    }
}
export default Detail;