public class Constant extends ModuleAbstract{
  private int n = 0;
  private double cst;
  public Constant(String name, double cst){
    super(name, 0, 1);
    this.cst = cst;
  }

  @Override
	public void exec(){
		setAndSendOutputPortValue(0, cst);
		n+=1;
	}

  public void reset(){
         setOutputPortValue(0,0);
   }
}
