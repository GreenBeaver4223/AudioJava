public class Delay extends ModuleAbstract{
	private double[] circularBuffer;
	private int head = 0;
    public Delay(String name,double delay){
        super(name,1,1);
		this.circularBuffer = new double[(int)delay*SAMPLE_FREQ];
    }

	public void exec(){
		// on stocke le nouvel echantillon dans le buffer
		circularBuffer[head] = getInputPortValue(0) ;
		// l’echantillon a sortir est celui qui a ete stocke il y a
		// circularBuffer.length pas
		double outputSample = circularBuffer[(head+circularBuffer.length)%circularBuffer.length] ;
		// on sort l’echantillon
		setAndSendOutputPortValue(0,outputSample);
		// calcul de l’indice de la case dans laquelle stocker le prochaine
		// echantillon
		head = (head+1)%circularBuffer.length;
	}

	public void reset(){
		super.reset();
		this.head = 0;
		if(this.circularBuffer != null){
			for(int i =0; i < this.circularBuffer.length; i++){
				this.circularBuffer[i] = 0;
			}
		}
	}

}
