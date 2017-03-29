
public class CommunicationPort {
	private ModuleAbstract module;
	private int idPort;
	private Connexion connection = null;
	private double sampleValue;
	
	public CommunicationPort(ModuleAbstract m, int idPort){
		module = m;
		this.idPort = idPort;
	}
	
	public void setValue(double v){
		sampleValue = v;
	}
	
	public double getValue(){
		return sampleValue;
	}
	
	public boolean isConnected(){
		if(connection == null){
			return false;
		}
		return true;
	}
	
	public void setConnexion(Connexion c){
		connection = c;
	}
	
	public Connexion getConnexion(){
		return connection;
	}
}
