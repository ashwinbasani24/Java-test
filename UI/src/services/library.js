import http from "../http-common";

class LibraryDataService {
  
    getAllLibary(){
        return http.get("/library");
    }
    getBook(id) {
        return http.get(`/library/${id}`);
      }
}

export default new LibraryDataService();

