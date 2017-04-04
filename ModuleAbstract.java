
public abstract class ModuleAbstract {
	private static int SAMPLE_FREQ = 44100;
	private String nom;
	private CommunicationPort[] inputPorts;
	private CommunicationPort[] outputPorts;

	public ModuleAbstract(String nom, int nbInputPort, int nbOutputPort){
		this.nom = nom;
		inputPorts = new CommunicationPort[nbInputPort];
        if(inputPorts != null){
            for(int i = 0; i < inputPorts.length; i++){
                inputPorts[i] = new CommunicationPort(i);
            }
        }
		outputPorts = new CommunicationPort[nbOutputPort];
        if(outputPorts != null){
            for(int i = 0; i < outputPorts.length; i++){
                outputPorts[i] = new CommunicationPort(i);
            }
        }
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
    
    public int getNbInputPorts(){
        if(inputPorts != null) return inputPorts.length;
        return 0;
    }
    
    public int getNbOutputPorts(){
        if(outputPorts != null) return outputPorts.length;
        return 0;
    }

    public boolean isConnectedInputPort(int inputPortId){
        if(this.inputPorts[inputPortId] != null) return this.inputPorts[inputPortId].isConnected();
        return false;
    }


    public boolean isConnectedOutputPort(int outputPortId){
        if(this.outputPorts[outputPortId] != null) return this.outputPorts[outputPortId].isConnected();
        return false;
    }

	public static Connexion connect(ModuleAbstract mOutput, int idOutputPort, ModuleAbstract mInput, int idInputPort){
		Connexion c = new Connexion(mOutput.getOutputCommunicationPort(idOutputPort), mInput.getInputCommunicationPort(idInputPort));
		mOutput.getOutputCommunicationPort(idOutputPort).setConnexion(c);
		mInput.getInputCommunicationPort(idInputPort).setConnexion(c);
		return c;
	}

	public static Connexion connect(CommunicationPort c1, CommunicationPort c2){
		Connexion c = new Connexion(c1, c2);
        if(c1 == null || c2 == null){
            throw new IllegalArgumentException("Null Port");
        }
		c1.setConnexion(c);
		c2.setConnexion(c);
		return c;
	}

	public Connexion connectOutput(int idOutputPort, ModuleAbstract mInput, int idInputPort){
		Connexion c = new Connexion(outputPorts[idOutputPort], mInput.getInputCommunicationPort(idInputPort));
		outputPorts[idOutputPort].setConnexion(c);
		mInput.getInputCommunicationPort(idInputPort).setConnexion(c);
		return c;
	}

	public Connexion connectInput(ModuleAbstract mOutput, int idOutputPort, int idInputPort){
		Connexion c = new Connexion(mOutput.getOutputCommunicationPort(idOutputPort),inputPorts[idInputPort]);
		mOutput.getOutputCommunicationPort(idOutputPort).setConnexion(c);
		inputPorts[idInputPort].setConnexion(c);
		return c;
	}

	public void setAndSendOutputPortValue(int idOutputPort, double sample){
		CommunicationPort p = outputPorts[idOutputPort];
		p.setValue(sample);
		if(p.isConnected()){
			p.getConnexion().communicate();
		}
	//	else{
	//		throw new IllegalArgumentException("Unable to communicate : port not connected");
	//	}
	}

	public double getInputPortValue(int idInputPort){
		return inputPorts[idInputPort].getValue();
	}

	public void setInputPortValue(int idInputPort, double s){
		inputPorts[idInputPort].setValue(s);
	}

	public double getOutputPortValue(int idOutputPort){
		return outputPorts[idOutputPort].getValue();
	}

	public void setOutputPortValue(int idOutputPort, double s){
		outputPorts[idOutputPort].setValue(s);
	}

	public abstract void exec();

    public abstract void reset();
}
