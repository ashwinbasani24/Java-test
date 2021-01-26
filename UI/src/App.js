import React,{Component}  from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css"
import "./App.css";

import Library from "./components/librarylist";
import BookList from "./components/booklist";

class App extends Component{
  render(){
    return (

      <div>

        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/library" className="navbar-brand">
            Library App
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/library"} className="nav-link">
                Library
              </Link>
            </li>
           </div>
        </nav>


        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/library"]} component={Library} />
            <Route path="/library/:id" component={BookList} />
          </Switch>
        </div>
      </div>
    )
  }
}
export default App;
