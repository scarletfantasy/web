import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Realogin from './js/login';
import Skimpage from './js/bookskim';
import Shoppingcar from './js/shoppingcar';
import Home from './js/home'
import Usermanage from './js/usermanage'
import {
  HashRouter as Router,
  Route,
  Link,
  Redirect,
  withRouter
} from "react-router-dom";
import Direct from './js/direct';
import Detail from './js/detail';
import Edit from './js/edit';
import Ordermanagement from './js/ordermanagement'
import Register from './js/register'
import Logout from './js/logout'
import { browserHistory } from 'react-router'
import Userinfo from './js/userinfo'


class App extends Component {
  constructor(props)
  {
    super(props);
    
    
  }
  
  render() {
    return (
      <div id="window">
      
      
      <Router >
      
      <Route  path="/" component={Direct}></Route>
      <Route  path="/bookskim" component={Skimpage}></Route>          
      <Route  path="/login" component={Realogin}></Route>
      <Route  path="/shoppingcar" component={Shoppingcar}></Route>
      <Route  path="/detail" component={Detail}></Route>
      <Route  path="/edit" component={Edit}></Route>
      <Route  path="/ordermanagement" component={Ordermanagement}></Route>
      <Route exact path="/" component={Home}></Route>
      <Route path="/register" component={Register}></Route>
      <Route path="/logout" component={Logout}></Route>
      <Route path="/usermanagement" component={Usermanage}></Route>
      <Route path="/userinfo" component={Userinfo}></Route>
        
        
      </Router>
      
      
      </div>
    );
  }
}

export default App;
