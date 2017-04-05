import java.util.Random;
public class Bruit extends ModuleAbstract{
  private int n = 0;

	public Bruit(String name){
		super(name, 2, 1);
	}

	private double calculEchantillon(){
		double esperance = getInputPortValue(0);
  	double variance = getInputPortValue(1);
    Random rd = new Random();
		double e = rd.nextGaussian()*variance + esperance;
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
