package org.tacografo.tiposdatos;

public class Integer_32 {

	
	public Integer_32(){
		
	}
	
	public static int getInteger_32(byte[] bytes){
		int i= (bytes[0]<<24)&0xff000000|
			       (bytes[1]<<16)&0x00ff0000|
			       (bytes[2]<< 8)&0x0000ff00|
			       (bytes[3]<< 0)&0x000000ff;
		return i;
	}
}
