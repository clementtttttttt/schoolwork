class TypistBuffs{
	TypingStyle ts;
	KeyboardType kt;
	Typist.Sponsors sponsor;
	boolean hasWS, hasED, hasNC;
	
	TypistBuffs(){
		ts = TypingStyle.HUNT_AND_PECK;
		kt = KeyboardType.MEMBRANE;
		sponsor = Typist.Sponsors.NONE;
		hasWS = hasED = hasNC = false;
	}
	
	TypistBuffs(TypingStyle ts,KeyboardType  kt,boolean  a,boolean b,boolean c){
		this.ts = ts;
		this.kt = kt;
		hasWS = a;
		hasED = b;
		hasNC = c;
	}
	
	public int getTotalAccuracyBuff(){
		int ret = ts.getAccuracyBuff() + kt.getAccuracyBuff();
		if(hasNC){
			++ret; //NC headphones reduce mistype chance
		}
		return ret;
	}
	public int getTotalSpeedBuff(){
		
		return kt.getSpeedBonus();
	}
	
	public int getTotalBurnOutBuff(){
		return ts.getBurnOutBuff() + (hasWS?1:0);
	}
	public TypingStyle getTypingStyle(){
		return ts;
	}
	
	public KeyboardType getKeyboardType(){
		return kt;
	}
	
	
	public boolean isWS(){
		return hasWS;
	}
	public boolean isED(){
		return hasED;
	}	
	public boolean isNC(){
		return hasNC;
	}
	
	public void setTypingStyle(TypingStyle newStyle){
		ts = newStyle;
	}
	
	public void setKeyboardType(KeyboardType newKeyboard){
		kt = newKeyboard;
	}
	
	public void setWS(boolean value){
		hasWS = value;
	}
	
	public void setED(boolean value){
		hasED = value;
	}
	
	public void setNC(boolean value){
		hasNC = value;
	}

	public Typist.Sponsors getSponsor(){
		return sponsor;
	}

	public void setSponsor(Typist.Sponsors value){
		sponsor = value;
	}
};
