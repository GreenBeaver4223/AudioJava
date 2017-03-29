
public abstract class ModuleAbstract {
	private static int SAMPLE_FREQ = 44100;
	private String nom;
	private CommunicationPort[] inputPorts;
	private CommunicationPort[] outputPorts;

	public ModuleAbstract(String nom, int nbInputPort, int nbOutputPort){
		this.nom = nom;
		inputPorts = new CommunicationPort[nbInputPort];
		outputPorts = new CommunicationPort[nbOutputPort];
	}

	public String getName(){
		return nom;
	}

	public CommunicationPort getInputCommunicationPort(int id){
		return inputPorts[id];
	}

	public CommunicationPort getOutputCommunicationPort(int id){
		return outputPorts[id];
	}

	public static void connect(ModuleAbstract mOutput, int idOutputPort, ModuleAbstract mInput, int idInputPort){
		Connexion c = new Connexion(mOutput.getOutputCommunicationPort(idOutputPort), mInput.getInputCommunicationPort(idInputPort));
		mOutput.getOutputCommunicationPort(idOutputPort).setConnexion(c);
		mInput.getInputCommunicationPort(idInputPort).setConnexion(c);
	}

	public static void connect(CommunicationPort c1, CommunicationPort c2){
		Connexion c = new Connexion(c1, c2);
		c1.setConnexion(c);
		c2.setConnexion(c);
	}

	public void setAndSendOutputPortValue(int idOutputPort, double sample){
		CommunicationPort p = outputPorts[idOutputPort];
		p.setValue(sample);
		if(p.isConnected()){
			p.getConnexion().communicate();
		}
		else{
			throw new IllegalArgumentException("Unable to communicate : port not connected");
		}
	}

	public double getInputPortValue(int idInputPort){
		return inputPorts[idInputPort].getValue();
	}

	public void setInputPortValue(int idInputPort, double s){
		inputPorts[idInputPort].setValue(s);
	}

	public abstract void exec();
}
