public class GenSquare extends GenSine{
  public GenSquare(String name){
    super(name);
  }

  @Override
  private double calculEchantillon(){
    double e = super.calculEchantillon();
		return Math.signum(e)*getInputPortValue(1);
	}
  
}
