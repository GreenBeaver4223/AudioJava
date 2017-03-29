
public class GenSineBasic extends ModuleAbstract{
	private double freq;
	private double amp;
	private int n = 0;

	public GenSineBasic(double frequence, double amp){
		super("GenSineBasic", 0, 1);
		freq = frequence;
		this.amp = amp;
	}

	@Override
	public void exec(){
		double e = amp*Math.sin(2*Math.Pi*freq*n/(double)SAMPLE_FREQ);
		setAndSendOutputPortValue(0, e);
		n+=1;
	}
}
