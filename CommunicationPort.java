
public class CommunicationPort {
	private int idPort;
	private Connexion connection = null;
	private double sampleValue;

	public CommunicationPort(int idPort){
		this.idPort = idPort;
	}

	public void setValue(double v){
		sampleValue = v;
	}

	public double getValue(){
		return sampleValue;
	}

	public boolean isConnected(){
		if(this.connection == null){
			return false;
		}
		return true;
	}

	public void setConnexion(Connexion c){
		this.connection = c;
	}

	public Connexion getConnexion(){
		return this.connection;
	}
}
