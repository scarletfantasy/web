import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Route,
  Link,
  Redirect,
  withRouter
} from "react-router-dom";
import '../css/direct.css';
import Home from './home.js'
import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';
import Toolbar from '@material-ui/core/Toolbar';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import $ from 'jquery'
class Direct extends Component
{

    constructor(props)
    {
      var arr=new Array();
      super(props);
      this.state={user:{id:" "},anchorEl: null};
      
      this.logout=this.logout.bind(this);
    }
    componentWillReceiveProps(nextprop)
    {
      var tmp=nextprop.location.query||"";
      
      var id=tmp.id||this.state.user.id;
      console.log(id);
      /*var order=tmp.order||"";
      
      var shoppingcar=tmp.shoppingcar||this.state.shoppingcar;
      
      if(order!="")
      {
        
        shoppingcar.push(order);
        
      }
      if(id==" ")
      {
        shoppingcar=[];
      }*/
      
      this.setState({user:{id:id}});
      
    }
    handleClick = event => {
      this.setState({ anchorEl: event.currentTarget });
    };
  
    handleClose = () => {
      this.setState({ anchorEl: null });
  };
    
    logout()
    {
      this.setState({user:{id:""}});
      $.ajax({
        url: "http://localhost:8080/jpalogout",
        type:"POST",
        params:{"contentType": "application/json;charset=utf-8"},
        
        success: function f(data) {
            alert("success");

        }.bind(this)
    });
    }
    render()
    {
      
      var tmp=this.state.user;
      var shoppingcar=this.state.shoppingcar;
      const  anchorEl  = this.state.anchorEl;
     /*<div id="direct1">
              <img id="icon" src="img/login.png"></img>
              <Link id="direct" to={{pathname:'/login'}}>login</Link>
              </div>
              <div id="direct1">
              <img id="icon" src="img/login.png"></img>
              <Link id="direct" to={{pathname:'/register'}}>register</Link>
              </div>             
              <div id="direct1">
              <Button id="directbutton" onClick={this.logout}>logout</Button>
              </div>*/ 
      return(
            
            
            
            <div id="alldirect">
            <AppBar id="alldirect">            
            <div>
              <div id="direct1">
              <img id="icon" src="img/skim.png"></img>
              <Link id="direct" to={{pathname:'/bookskim'}} >skim</Link>
              </div>
              <div id="direct1">
              <img id="icon" src="img/home.png"></img>
              <Link id="direct" to={{pathname:'/'}}>home</Link>
              </div>
              <div id="direct1">
              <img id="icon" src="img/cart.png"></img>
              <Link id="direct" to={{pathname:'/shoppingcar'}}>cart</Link>
              </div>
              <div id="direct1">
              <img id="icon" src="img/edit.png"></img>
              <Link id="direct" to={{pathname:'/edit'}}>edit</Link>
              </div>
              <div id="direct1">
              <img id="icon" src="img/order.png"></img>
              <Link id="direct" to={{pathname:'/ordermanagement',}}>order</Link>
              </div>
              <div id="direct1">
              <img id="icon" src="img/order.png"></img>
              <Link id="direct" to={{pathname:'/usermanagement',}}>super</Link>
              </div>
              <div id="direct1">
              <img id="icon" src="img/guest.png"></img>
              <div id="direct">{tmp.id}</div> 
              </div>
              
        <div id="direct1">
        <Button id="directbutton"
          aria-owns={anchorEl ? 'simple-menu' : undefined}
          aria-haspopup="true"
          onClick={this.handleClick}
        >
          user
        </Button>
        <Menu
          id="simple-menu"
          anchorEl={anchorEl}
          open={Boolean(anchorEl)}
          onClose={this.handleClose}
        >
          <MenuItem onClick={this.handleClose}><Link id="slink" to={{pathname:'/login'}}>login</Link></MenuItem>
          <MenuItem onClick={this.handleClose}><Link id="slink" to={{pathname:'/register'}}>register</Link></MenuItem>
          <MenuItem onClick={this.handleClose}><Link id="slink" to={{pathname:'/logout'}}>logout</Link></MenuItem>
        </Menu>
        </div>
              
            </div>
            
            </AppBar>
            </div>
            
        )
    }
}
export default withRouter(Direct);