insert into library(id,title,description) values(1,'Library','LibraryDesc'),(2,'Library2','LibraryDesc'),(3,'Library3','Library3Desc');

insert into books(id,title,description,published,library_id) values(5,'Book1','Book1desc',false,1),(6,'Book2','Book2desc',true,1),(7,'Book3','Book3desc',false,2),(8,'Book3','Book3desc',true,2);