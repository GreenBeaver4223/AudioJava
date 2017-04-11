public abstract class FIRAbstract extends ModuleAbstract{
	protected double[] coeffArray;
	protected double[] inputSamples;
	private int head = 0;
	
	public FIRAbstract(String name,double[] coeffArray){
		super(name,1,1);
		this.coeffArray = coeffArray.clone();
		this.inputSamples = new double[this.coeffArray.length];
	}
	
	public int getFiltOrder(){
		if(this.coeffArray != null){
			return this.coeffArray.length-1;
		}else{
			return 0;
		}
	}
	public void exec(){
		// on stocke le nouvel echantillon dans le buffer
		double sum = 0.0;
		inputSamples[head] = getInputPortValue(0);
		for(int i = 0; i < head; i++){
			sum += coeffArray[i]*inputSamples[i];
		}
		// on sort l’echantillon
		setAndSendOutputPortValue(0,sum);
		// calcul de l’indice de la case dans laquelle stocker le prochaine
		// echantillon
		head = (head+1)%inputSamples.length;
	}
	public void reset(){
		super.reset();
		for(int i = 0; i < coeffArray.length; i++){
			coeffArray[i] = 0;
		}
		head = 0;
	}
}
