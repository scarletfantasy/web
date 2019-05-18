import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import '../css/bookskim.css';
import {
  BrowserRouter as Router,
  Route,
  Link,
  Redirect,
  withRouter
} from "react-router-dom";
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import $ from 'jquery';
class Bookskim extends Component {
    constructor(props)
    {
        super(props);
        
    }
    
      render() {
        console.log(3);
      console.log(this.props.book);
        var bookurl=this.props.url;
      return (

        <TableRow >
          <TableCell><p >{this.props.book.bookname}</p></TableCell>
          <TableCell><p >{this.props.book.isbn}</p></TableCell>
          <TableCell><p >{this.props.book.number}</p></TableCell>
          <TableCell><img id="skimimg" src={this.props.book.bookimg}></img></TableCell>
          <TableCell><p >price:{this.props.book.price}</p></TableCell>
          
          <TableCell><Link  to={{pathname:'/detail',query:{book:this.props.book}}}>detail</Link></TableCell>
          
          
        </TableRow>
      );
    };
  };
  class Allbookskim extends Component{
    constructor(props)
    {
        super(props);

        this.handlesort=this.handlesort.bind(this);
        
    }
    handlesort(e)
    {
      var s=e.target.innerHTML;
      this.props.sort(s);
    }
    componentWillReceiveProps(nextprop)
    {
      
    }
    render() {
      var item=[];
      var searchname=this.props.searchname;
      var book=this.props.book;
      
      if(searchname=="")
      {
        
        for(i=0;i<book.length;++i)
      {
        console.log(book[i].bookname);
        var name=book[i].bookname;
          var bookurl=book[i].bookimg;
          var price=book[i].price;
          if(book[i].number>0)
          {
            item.push(<Bookskim book={book[i]} />);
            
          }
          
      }
      }
      else{
        for(var i=0;i<book.length;++i)
        {
          var name=book[i].bookname;
          var bookurl=book[i].bookimg;
          var price=book[i].price;
          if(name.search(searchname)!=-1&&book[i].number>0)
          {
            item.push(<Bookskim book={book[i]} />)
          }
          
        }
      }
      return (
        <Table>
          <TableHead>
          <TableRow>
            <TableCell ><Button onClick={this.handlesort}>bookname</Button></TableCell>
            <TableCell ><Button onClick={this.handlesort}>isbn</Button></TableCell>
            <TableCell ><Button onClick={this.handlesort}>number</Button></TableCell>
            <TableCell >img</TableCell>
            <TableCell ><Button onClick={this.handlesort}>price</Button></TableCell>
            
            <TableCell >detail</TableCell>
            
          </TableRow>
          </TableHead>
          {item}
        </Table>
      );
    };
  };
  
  
  class Search extends Component
  {
  
      constructor(props)
      {
        super(props);
        this.handleinputchange=this.handleinputchange.bind(this);
        
      }
      handleinputchange(e)
      {
        this.props.searchchange(e.target.value);
      }
      
      render()
      {
        
        return(
              <div id="search">
              <FormControl margin="normal" required fullWidth>
                <InputLabel htmlFor="search">search</InputLabel>  
              <Input value={this.props.searchname} onChange={this.handleinputchange} id="directsearch"></Input>
              </FormControl>
              </div>
              
          )
      }
  }
  
  class Skimpage extends Component
  {
    constructor(props)
    {
      super(props);
      this.state={searchname:"",book:[],desc:{bookname:1,price:1,number:1,isbn:1}};
      
      
      
      this.searchchange=this.searchchange.bind(this);
      this.sort=this.sort.bind(this);
    }
    componentDidMount()
    {
      $.ajax({
        url:"http://localhost:8080/jpabooklist",
        type:"GET",
          params:{"contentType": "application/json;charset=utf-8"},
          xhrFields: {
            withCredentials: true
        },
          success: function f(data) {
            
            console.log(data);  
            this.setState({book:data});
            
          }.bind(this)
      })
    }
    searchchange(s)
    {
      this.setState({searchname:s});
    }
    sort(s)
    {
      var book=this.state.book;
      var desc=this.state.desc;
      desc[s]*=-1;
      var flag=desc[s];

      book.sort((a,b)=>{if(a[s]>b[s])
        return 1*flag;
        else if(a[s]==b[s])
        return 0;
        else return -1*flag;
      
      })
      this.setState({book:book,desc:desc});
    }
    render()
    {
      var searchname=this.state.searchname;
      var book=this.state.book;
      
      
      return(
        <div id="skimpage">
          <Paper>
          <Search searchname={searchname} searchchange={this.searchchange} ></Search>
          <Allbookskim searchname={searchname} book={book} sort={this.sort} ></Allbookskim>
          </Paper>
        </div>
        
      );
      
    }
  }
  
  
  export default Skimpage;
  
  
  