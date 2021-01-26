import React,{Component} from 'react';
import LibararyDataService from '../services/library';
import {Link} from 'react-router-dom';

export default class Library extends Component{
    constructor(props){
        super(props);
        this.retrieveLibrary = this.retrieveLibrary.bind(this);
        this.setActiveLibrary= this.setActiveLibrary.bind(this);
        this.state ={
            library:[],
            currentLibrary:null,
            currentIndex: -1,
        }
    }

    componentDidMount(){
        this.retrieveLibrary();
    }

    retrieveLibrary() {
        LibararyDataService.getAllLibary()
        .then(response=>{
            this.setState({library:response.data});
            console.log(response.data);
        })
        .catch(e=>{
            console.log(e);
        })
        
    }
    setActiveLibrary(library,index){
        this.setState({
            currentLibrary: library,
            currentIndex: index
          });
    }

    render(){
        const {library,currentLibrary,currentIndex} = this.state;
        return(
            <div className="list row">
            <div className="col-md-6">
                <h4>Library List</h4>
                <ul className="list-group">
                    {
                        library && library.map((lib,index)=>(
                         <li
                            className={
                                "list-group-item " +
                                (index === currentIndex ? "active" : "")
                              }
                            onClick={() => this.setActiveLibrary(lib, index)}
                            key={index}
                          >
                            {lib.title}
                          </li>
                        ))}
                </ul>
            </div>
            <div className="col-md-6">
                 {
                     currentLibrary ? (
                    <div>
                  <h4>Library Details</h4>
                  <div>
                    <label>
                      <strong>Title:</strong>
                    </label>{" "}
                    {currentLibrary.title}
                  </div>
                  <div>
                    <label>
                      <strong>Description:</strong>
                    </label>{" "}
                    {currentLibrary.description}
                  </div>
                  <Link
                    to={"/library/" + currentLibrary.id}
                    className="badge badge-warning"
                  >
                    Click to see Books Related 
                  </Link>
                </div>
                     ):
                 ( <div>
                     <br />
                     <p>Please click on a Library...</p>
                   </div>
                 )}
            </div>
            </div>     
        )
        

    }
}