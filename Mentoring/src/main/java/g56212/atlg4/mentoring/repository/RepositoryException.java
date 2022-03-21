package g56212.atlg4.mentoring.repository;

public class RepositoryException extends Exception{
    public RepositoryException(){
        super();
    }
        

    public RepositoryException(String msg) {
        super(msg);
    }

   
    public RepositoryException(Exception exception) {
        super(exception);
    }


    
}
