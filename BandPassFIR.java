public class BandPassFIR extends FIRAbstract{
  private double largeur;
  private double f_r;

  public BandPassFIR(String name, double f_r, double largeur){
    super(name,1,1);
    this.f_r = f_r;
    this.largeur = largeur;
    updatecoeffArray(f_r, largeur);
  }

  public BandPassFIR(String name, double largeur){
    super(name,2,1);
    this.largeur = largeur;
    updatecoeffArray(getInputPortValue(1), largeur);
  }

  public BandPassFIR(String name){
    super(name,3,1);
    updatecoeffArray(getInputPortValue(1), getInputPortValue(2));
  }

  public void updatecoeffArray(double f_r, double largeur){
          //Normalize f_r and largeur so that pi is equal to the Nyquist angular frequency
          f_r = f_r/SAMPLE_FREQ;
          largeur = largeur/SAMPLE_FREQ;

          double f_1 = f_r-largeur; // freq coupure à gauche
          double f_2 = f_r+largeur; // freq coupure à droite

          double omega_1 = 2*Math.PI*f_1;
          double omega_2 = 2*Math.PI*f_2;
          int middle = (coeffArray.length -1) / 2 ;   /*rappel : longueur impaire*/

          // Calculate taps
          // Due to symmetry, only need to calculate half the window
          for ( int i=0 ; i< middle ; i++) {
                  double val1 = Math.sin( omega_1 * (i-middle) ) / ( Math.PI * (i-middle) ) ;
                  double val2 = Math.sin( omega_2 * (i-middle) ) / ( Math.PI* (i-middle) ) ;

                  // hamming windowing
                  double weight= 0.54 - 0.46* Math.cos( ( 2. * Math.PI * i ) / coeffArray.length   ) ;

                  //weight= 1.0 ;
                  coeffArray[i]= ( val2 - val1 ) * weight ;
                  coeffArray[coeffArray.length-i-1]=  coeffArray[i] ;

          }
          coeffArray[middle]= 2.0 * ( f_2 - f_1 ) ;

          //Scale filter to obtain an unity gain at center of passband
          double realSum= 0,imagSum= 0  ;
          for( int i = 0 ; i < coeffArray.length ; i ++ ) {
                  double argExp= -2. * Math.PI * i * f_r ;
                  realSum+= Math.cos( argExp  ) * coeffArray[i] ;
                  imagSum+= Math.sin( argExp  ) * coeffArray[i] ;
          }

          double sum= Math.sqrt( realSum * realSum + imagSum * imagSum ) ;
          for(int i = 0 ; i < coeffArray.length ; i ++) {
                  coeffArray[i] = coeffArray[i] / sum ;
          }
          //System.out.println("BandPassFilterControlled fc == " + f_r + " norm. factor == " + sum ) ;
  }

  @Override
	public void exec(){
		if(isConnectedInputPort(2)){
      updatecoeffArray(getInputPortValue(1), getInputPortValue(2));
      super.exec();
    }
    else if(isConnectedInputPort(1)){
      updatecoeffArray(getInputPortValue(1), largeur);
      super.exec();
    }
    else{
      super.exec();
    }
	}

  public void reset(){
		super.reset();
		largeur = 0;
    f_r = 0;
	}
}
