package projectw1;

public abstract class File implements Volume,Luminosita {
	protected String fileName;
	protected String tipo="";
	public File() {
		
	}
	protected abstract Object getTipo();
	protected abstract void show();
	
}
