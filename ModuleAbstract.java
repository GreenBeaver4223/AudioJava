
public abstract class ModuleAbstract {
	private int SAMPLE_FREQ = 44100;
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
	
	public static Connexion connect(ModuleAbstract mOutput, int idOutputPort, ModuleAbstract mInput, int idInputPort){
		Connexion c = new Connexion(mOutput.getOutputCommunicationPort(idOutputPort), mInput.getInputCommunicationPort(idInputPort));
		return c;
	}
	
	public void setAndSendOutputPortValue(int idOutputPort, double sample){
		CommunicationPort p = outputPorts[idOutputPort];
		p.setValue(sample);
		if(p.isConnected()){
			p.getConnexion().communicate();
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
