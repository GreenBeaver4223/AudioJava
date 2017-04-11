public class EnvelopADSR extends ModuleAbstract{
	private int nbStep = 0;
	private double attackDuration; //en secondes
	private double attackLevel;
	private double decayDuration;
	private double sustainDuration;
	private double sustainLevel;
	private double releaseDuration;
	
	public EnvelopADSR(String name,double attackDuration, double attackLevel, double decayDuration, double sustainDuration, double sustainLevel, double releaseDuration){
        super(name,0,1);
		this.attackDuration = attackDuration;
		this.attackLevel = attackLevel;
		this.decayDuration = decayDuration;
		this.sustainDuration = sustainDuration;
		this.sustainLevel = sustainLevel;
		this.releaseDuration = releaseDuration;
    }
	
	public EnvelopADSR(String name, double noteDuration, double attackLevel, double sustainLevel){
        super(name,0,1);
		this.attackDuration = 0.1; // en secondes
		this.attackLevel = attackLevel;
		this.decayDuration = 0.05;
		this.sustainLevel = sustainLevel;
		this.releaseDuration = 0.5;
		this.sustainDuration = noteDuration - this.attackDuration - this.decayDuration;
		if(this.sustainDuration < 0){
			this.sustainDuration = 0.0;
			throw new IllegalArgumentException("noteDuration should be > 0.1+0.05");
		}
	}
	
	@Override
	public void exec(){
		double timeSec = (double) nbStep/44100; // dure ecoulee depuis le debut en secondes

		if( timeSec >= attackDuration + decayDuration + sustainDuration + releaseDuration ){
			setAndSendOutputPortValue(0,0.);
		return;
		}
		double y1 , y0 , x0 , x1;
		if( timeSec < attackDuration ){
			x0 = 0;
			y0 = 0;
			x1 = attackDuration;
			y1 = attackLevel;
		}else if( timeSec < (attackDuration + decayDuration) ){
			x0 = attackDuration;
			y0 = attackLevel;
			x1 = attackDuration + decayDuration;
			y1 = sustainLevel;
		}else if( timeSec < (attackDuration + decayDuration + sustainDuration)){
			x0 = attackDuration + decayDuration;
			y0 = sustainLevel;
			x1 = attackDuration + decayDuration + sustainDuration;
			y1 = sustainLevel;
		}else{
			x0 = attackDuration + decayDuration + sustainDuration;
			y0 = sustainLevel;
			x1 = attackDuration + decayDuration + sustainDuration + releaseDuration;
			y1 = 0;
		}

		double output = (y1 - y0) /(x1 - x0)*(timeSec - x0) + y0;
		setAndSendOutputPortValue(0,output);
		nbStep++;
	}
	
	public void reset(){
		nbStep = 0;
	}
}
