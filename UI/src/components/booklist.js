import React,{Component} from 'react';
import LibararyDataService from '../services/library';

export default class Book extends Component {
    constructor(props) {
        super(props);
        this.getBook = this.getBook.bind(this);
        this.setActiveBook = this.setActiveBook.bind(this);
        this.state = {
            books:[],
            currentBook:null,
            currentIndex: -1,
        };
    }
    componentDidMount() {
        this.getBook(this.props.match.params.id);
    }
    getBook(id) {
        LibararyDataService.getBook(id)
          .then(response => {
            this.setState({
              books: response.data.books
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      }

      setActiveBook(book,index){
        this.setState({
            currentBook: book,
            currentIndex: index
          });

      }
      render(){
          const {books,currentBook,currentIndex} = this.state;
          return(
                <div className="list row">
<div className="col-md-6">
                <h4>Book List</h4>
                <ul className="list-group">
                    {
                        books && books.map((book,index)=>(
                         <li
                            className={
                                "list-group-item " +
                                (index === currentIndex ? "active" : "")
                              }
                            onClick={() => this.setActiveBook(book, index)}
                            key={index}
                          >
                            {book.title}
                          </li>
                        ))}
                </ul>
            </div>
            <div className="col-md-6">
                 {
                     currentBook ? (
                    <div>
                  <h4>Book Details</h4>
                  <div>
                    <label>
                      <strong>Title:</strong>
                    </label>{" "}
                    {currentBook.title}
                  </div>
                  <div>
                    <label>
                      <strong>Description:</strong>
                    </label>{" "}
                    {currentBook.description}
                  </div>
                  <div>
                    <label>
                      <strong>Status:</strong>
                    </label>{" "}
                    {currentBook.published? "Published" : "Pending" }
                  </div>
                </div>
                     ):
                 ( <div>
                     <br />
                     <p>Please click on a Book...</p>
                   </div>
                 )}
            </div>
            
                </div>
          )
          

      }
}