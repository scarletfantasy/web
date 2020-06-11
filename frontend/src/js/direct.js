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
    }
    componentWillReceiveProps(nextprop)
    {
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
      
      
      
      
    }
    handleClick = event => {
      this.setState({ anchorEl: event.currentTarget });
    };
  
    handleClose = () => {
      this.setState({ anchorEl: null });
  };
    
    logout()
    {
      this.setState({user:{id:" "}});
      $.ajax({
        url: "http://101.132.98.60:12346/logout",
        type:"POST",
        xhrFields: {
          withCredentials: true
      },
        params:{"contentType": "application/json;charset=utf-8"},
        
        success: function f(data) {
            alert("success");

        }.bind(this)
    });
    }
    render()
    {
      
      var user=this.state.user;
      var shoppingcar=this.state.shoppingcar;
      const  anchorEl  = this.state.anchorEl;
     if(user.id=="admin")
     {
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
          <div id="direct">{user.id}</div> 
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
              <MenuItem onClick={this.handleClose}><Link id="slink" to={{pathname:'/userinfo'}}>information</Link></MenuItem>
            </Menu>
          </div>
          
        </div>
        
        </AppBar>
        </div>
        
    )
    }
    else
    {
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
          <img id="icon" src="img/order.png"></img>
          <Link id="direct" to={{pathname:'/ordermanagement',}}>order</Link>
          </div>
          
          <div id="direct1">
          <img id="icon" src="img/guest.png"></img>
          <div id="direct">{user.id}</div> 
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
              <MenuItem onClick={this.handleClose}><Link id="slink" to={{pathname:'/userinfo'}}>information</Link></MenuItem>
            </Menu>
          </div>
          
        </div>
        
        </AppBar>
        </div>
        
    )
    }

     
    }
}
export default withRouter(Direct);