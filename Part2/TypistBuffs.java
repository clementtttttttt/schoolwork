class TypistBuffs{
	TypingStyle ts;
	KeyboardType kt;
	boolean hasWS, hasED, hasNC;
	
	public int getTotalAccuracyBuff(){
		int ret = ts.getAccuracyPenalty() + kt.getAccuracyPenalty();
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
		
	
};
