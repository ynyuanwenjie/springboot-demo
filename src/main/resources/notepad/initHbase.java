--drop table
disable 'users'
drop 'users'

create 'users','info'

put 'users','Ada','info:id','1'
put 'users','Ada','info:password','admin@123'
put 'users','Ada','info:age','22'

put 'users','Ygreet','info:id','2'
put 'users','Ygreet','info:password','admin@123'
put 'users','Ygreet','info:age','21'

put 'users','Snow','info:id','3'
put 'users','Snow','info:password','admin@123'
put 'users','Snow','info:age','30'

put 'users','Harry','info:id','4'
put 'users','Harry','info:password','admin@123'
put 'users','Harry','info:age','30'

put 'users','baoliping','info:id','1'
put 'users','baoliping','info:password','admin@123'
put 'users','baoliping','info:age','22'

put 'users','chengjianping','info:id','2'
put 'users','chengjianping','info:password','admin@123'
put 'users','chengjianping','info:age','22'

put 'users','yuanwenjie','info:id','3'
put 'users','yuanwenjie','info:password','admin@123'
put 'users','yuanwenjie','info:age','22'

put 'users','liuling','info:id','0012'
put 'users','liuling','info:password','liuling@123'
put 'users','liuling','info:age','22'

get 'users','yuanwenjie'
get 'users','yuanwenjie','info:password'
get 'users','yuanwenjie','info:age','info:password'




----------------[okcu,users]----------------------------------------------
ROW                                                          COLUMN+CELL                                                                                                                                                                    
 Ada                                                         column=info:age, timestamp=1473726864406, value=22                                                                                                                             
 Ada                                                         column=info:id, timestamp=1473726864242, value=1                                                                                                                               
 Ada                                                         column=info:password, timestamp=1473726864337, value=admin@123                                                                                                                 
 Harry                                                       column=info:age, timestamp=1473726864893, value=30                                                                                                                             
 Harry                                                       column=info:id, timestamp=1473726864808, value=4                                                                                                                               
 Harry                                                       column=info:password, timestamp=1473726864832, value=admin@123                                                                                                                 
 Snow                                                        column=info:age, timestamp=1473726864789, value=30                                                                                                                             
 Snow                                                        column=info:id, timestamp=1473726864717, value=3                                                                                                                               
 Snow                                                        column=info:password, timestamp=1473726864755, value=admin@123                                                                                                                 
 Ygreet                                                      column=info:age, timestamp=1473726864624, value=21                                                                                                                             
 Ygreet                                                      column=info:id, timestamp=1473726864518, value=2                                                                                                                               
 Ygreet                                                      column=info:password, timestamp=1473726864581, value=admin@123                                                                                                                 
 baoliping                                                   column=info:age, timestamp=1473726865022, value=22                                                                                                                             
 baoliping                                                   column=info:id, timestamp=1473726864987, value=1                                                                                                                               
 baoliping                                                   column=info:password, timestamp=1473726865004, value=admin@123                                                                                                                 
 chengjianping                                               column=info:age, timestamp=1473726865123, value=22                                                                                                                             
 chengjianping                                               column=info:id, timestamp=1473726865056, value=2                                                                                                                               
 chengjianping                                               column=info:password, timestamp=1473726865108, value=admin@123                                                                                                                 
 liulinig                                                    column=info:age, timestamp=1473726865274, value=22                                                                                                                             
 liulinig                                                    column=info:id, timestamp=1473726865237, value=0012                                                                                                                            
 liulinig                                                    column=info:password, timestamp=1473726865259, value=liuling@123                                                                                                               
 yuanwenjie                                                  column=info:age, timestamp=1473726865219, value=22                                                                                                                             
 yuanwenjie                                                  column=info:id, timestamp=1473726865145, value=3                                                                                                                               
 yuanwenjie                                                  column=info:password, timestamp=1473726865193, value=admin@123  
