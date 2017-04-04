
public class GenSineBasic extends ModuleAbstract{
	private double freq;
	private double amp;
	private int n = 0;

	public GenSineBasic(String name, double frequence, double amp){
		super(name, 0, 1);
		freq = frequence;
		this.amp = amp;
	}

	@Override
	public void exec(){
		double e = amp*Math.sin(2*Math.PI*freq*n/44100.0);
		setAndSendOutputPortValue(0, e);
		n+=1;
	}
}
