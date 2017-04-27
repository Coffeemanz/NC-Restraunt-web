package by.training.nc.dev3.exceptions;

public class DaoEcxeption  extends RuntimeException{

	public DaoEcxeption() {
    }

    public DaoEcxeption(String msg) {
        super(msg);
    }

    public DaoEcxeption(Exception e) {
        super(e);
    }
	


}
