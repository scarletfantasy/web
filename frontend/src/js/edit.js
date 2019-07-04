import React, {
    Component
} from 'react';
import {
    BrowserRouter as Router,
    Route,
    Link,
    Redirect,
    withRouter
} from "react-router-dom";
import TextField from '@material-ui/core/TextField';
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
class Introduction extends Component{
    constructor(props){
        super(props);
        
        this.state={isbn:"",introduction:" "};
        
        this.handlechange=this.handlechange.bind(this);
        this.handlesave=this.handlesave.bind(this);
        
        
    }
    componentWillMount()
    {
        var isbn=this.props.isbn;
        this.setState({isbn:isbn});
        $.ajax({
            url: "http://localhost:8080/getdetail",
            type:"POST",
            params:{"contentType": "application/json;charset=utf-8"},
            data:{isbn:isbn},
            xhrFields: {
              withCredentials: true
            },
            success: function f(data) {
              if(data.comments!=null)
              {
                this.setState({introduction:data.comments.introduction});
              }
              else{
                this.setState({introduction:" "});
              }
              
    
            }.bind(this)
          })
    }
    componentWillReceiveProps(nextprop)
    {
        var isbn=nextprop.isbn;
        this.setState({isbn:isbn});
        $.ajax({
            url: "http://localhost:8080/getdetail",
            type:"POST",
            params:{"contentType": "application/json;charset=utf-8"},
            data:{isbn:isbn},
            xhrFields: {
              withCredentials: true
            },
            success: function f(data) {
              if(data.comments!=null)
              {
                this.setState({introduction:data.comments.introduction});
              }
              else{
                this.setState({introduction:" "});
              }
              
    
            }.bind(this)
          })
    }
    handlechange(e)
    {
        this.setState({introduction:e.target.value});
    }
    handlesave(e)
    {
        var isbn=this.state.isbn;
        var introduction=this.state.introduction;
        $.ajax({
            url: "http://localhost:8080/jpaintroduction",
            type: "POST",
            params: {
                "contentType": "application/json;charset=utf-8"
            },
            xhrFields: {
                withCredentials: true
            },
            data: {
                isbn:isbn,
                introduction:introduction
            },
            success: function f(data) {

                alert("success");

            }.bind(this)
        })
    }
    render()
    {
        
        
        return(
            <div>
                <Paper>
                <textarea id="board" onChange={this.handlechange} value={this.state.introduction} cols="5" rows="3" ></textarea>
                <br/>
                <Button onClick={this.handlesave}>save</Button>
                </Paper>
            </div>
        )
    }
}
class Edit extends Component {
    constructor(props) {
        super(props);
        this.state = {
            book: [],
            searchname: "",
            showintro:0
        };
        this.handlechange = this.handlechange.bind(this);
        this.handlesave = this.handlesave.bind(this);
        this.handledelete = this.handledelete.bind(this);
        this.reload = this.reload.bind(this);
        this.searchchange = this.searchchange.bind(this);
        this.newbook = this.newbook.bind(this);
        this.showintroduction=this.showintroduction.bind(this);
    }
    showintroduction(e)
    {
        this.setState({showintro:this.state.book[e.target.id].isbn});
        console.log(this.state.book[e.target.id].isbn)
    }
    newbook() {
        var books = this.state.book;
        var book = {
            bookname: "",
            isbn: "",
            number: 0,
            bookimg: "",
            price: ""
        }
        books.unshift(book)
        this.setState({
            book: books
        })
    }
    searchchange(e) {
        this.setState({
            searchname: e.target.value
        });
    }
    reload() {
        $.ajax({
            url: "http://localhost:8080/jpabooklist",
            type: "GET",
            params: {
                "contentType": "application/json;charset=utf-8"
            },
            xhrFields: {
                withCredentials: true
            },
            
            success: function f(data) {

                this.setState({
                    book: data
                });
                console.log(data);

            }.bind(this)
        })
    }
    handledelete(e) {
        var i = e.target.id;
        var isbn = this.state.book[i].isbn
        $.ajax({
            url: "http://localhost:8080/jpaeditdelete",
            type: "GET",
            xhrFields: {
                withCredentials: true
            },
            params: {
                "contentType": "application/json;charset=utf-8"
            },
            data: {
                isbn: isbn
            },
            success: function f(data) {

                alert("success")
                this.reload()

            }.bind(this)
        })
    }
    componentDidMount() {
        this.reload()
    }
    handlesave(e) {
        var i = e.target.id;
        var book = this.state.book[i];
        var tmp = JSON.stringify(book);
        var id="#"+i+"img"
        
        var formdata=new FormData();
        
        
        var filedata=$(id)[0].files[0]
        console.log(filedata)
        if(filedata!=undefined&&filedata.size>100000)
        {
            alert("the file is too big")
        }
        else if(filedata!=undefined)
        {
            formdata.append("img",$(id)[0].files[0]);
            formdata.append("isbn",book.isbn);
            formdata.append("houzhui",$(id)[0].value.split(".")[1])
            console.log(book.isbn);
        
            $.ajax({
                url:"http://localhost:8080/uploadimg",
                type:"POST",
                data:formdata,
                processData:false,
                contentType:false,
                xhrFields:{withCredentials:true},
                success: function f(data) {

                    alert(data);


                }
            })
        }
        
        $.ajax({
            url: "http://localhost:8080/jpaeditsave",
            type: "GET",
            
            
            xhrFields: {
                withCredentials: true
            },
            data: {
                book: tmp
            },
            success: function f(data) {

                alert("success");


            }
        })
        
        

    }
    handlechange(e) {
        var book = this.state.book;
        var tmp = e.target.id;
        var i = tmp.split(".")[0];
        var choose = tmp.split(".")[1];


        switch (choose) {
            case "bookname":
                book[i].bookname = e.target.value;
                break;
            case "isbn":
                book[i].isbn = e.target.value;
                break;
            case "number":
                book[i].number = e.target.value;
                break;
            case "bookimg":
                book[i].bookimg = e.target.value;
                break;
            case "price":
                book[i].price = e.target.value;
                break;
        }

        this.setState({
            book: book
        });
    }
    render() {
        var book = this.state.book;
        var searchname = this.state.searchname;
        var item = [];
        for (var i = 0; i < book.length; ++i) {
            if (searchname == "" || book[i].bookname.search(searchname) != -1) {
                item.push( 
                < TableRow >
                    < TableCell > 
                        < Input id = {i + ".bookname"}value = {book[i].bookname} onChange = {this.handlechange}/>
                    </TableCell >
                    <TableCell > 
                        < Input id = { i + ".isbn"} value = {book[i].isbn} onChange = {this.handlechange }/>
                    </TableCell >
                    <TableCell > 
                        < Input id = { i + ".number"} value = {book[i].number } onChange = { this.handlechange}/>
                    </TableCell >
                    <TableCell > < img id = "skimimg" src={"http://localhost:8080/findimg/"+book[i].isbn}/>
                    </TableCell >
                    < TableCell > 
                    < Input id = {i + ".price"}value = {book[i].price } onChange = {this.handlechange}/>
                    </TableCell >
                    <TableCell > 
                    <input type="file" id = {i+"img"} name={i+"img"}></input>
                    </TableCell >
                    <TableCell > 
                    <button id={i}onClick={this.showintroduction}>detail</button>
                    </TableCell >
                    < TableCell > < button id = { i}onClick = { this.handlesave} > save </button>
                    </TableCell >
                    <TableCell > < button id = {i}onClick = { this.handledelete} > delete </button>
                    </TableCell >
                    </TableRow>)
                }


            }
            var mainpart=[];
            mainpart.push(
                <div id = "skimpage">                
                <Paper>
                
                <Input value = {this.state.searchname} onChange = { this.searchchange } > </Input> 
                <br/>
                <Button onClick = { this.newbook} > new </Button> 
                <Table >
                
                <TableHead >
                <TableCell > bookname </TableCell> 
                <TableCell > isbn </TableCell> 
                <TableCell > number </TableCell> 
                <TableCell > img </TableCell> 
                <TableCell > price </TableCell> 
                <TableCell>upload</TableCell>
                <TableCell>introduction</TableCell>
                <TableCell > save </TableCell> 
                <TableCell > delete </TableCell> 
                </TableHead> 
                {item} 
                </Table>
                </Paper >
                </div>
            );
            if(this.state.showintro)
            {
                mainpart.push(
                    <div>
                        <Introduction isbn={this.state.showintro}></Introduction>
                    </div>
                    )
            }
            
            return ( 
                
                <div>
                {mainpart}
                </div>



            )
        }
    }
    export default Edit;