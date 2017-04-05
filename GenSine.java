public class GenSine extends ModuleAbstract{
	private int n = 0;

	public GenSine(String name){
		super(name, 2, 1);
	}

	@Override
	public void exec(){
    double freq = getInputPortValue(0);
  	double amp = getInputPortValue(1);
		double e = amp*Math.sin(2*Math.PI*freq*n/44100.0);
		setAndSendOutputPortValue(0, e);
		n+=1;
	}

    public void reset(){
           setOutputPortValue(0,0);
     }
}
