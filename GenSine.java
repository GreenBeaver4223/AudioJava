public class GenSine extends ModuleAbstract{
	private int n = 0;

	public GenSine(String name){
		super(name, 2, 1);
	}

	public double calculEchantillon(){
		double freq = getInputPortValue(0);
  	    double amp = getInputPortValue(1);
		double e = amp*Math.sin(2*Math.PI*freq*n/44100.0);
        return e;
	}

	@Override
	public void exec(){
		setAndSendOutputPortValue(0, calculEchantillon());
		n+=1;
	}

  public void reset(){
    setOutputPortValue(0,0);
  }
}
