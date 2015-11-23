/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

/**
 * 2.44. ControlType
 * 
 * Código que indica las actividades realizadas durante un control. Este tipo de datos está relacionado con los requisitos 102, 210 y 225.
 * 
 * ControlType::= OCTET STRING (SIZE(1))
 *
 * Asignación de valor - Alineación de octeto: 'cvpdxxxx'B (8 bits)
 *
 * 'c'B transferencia de los datos de la tarjeta:
 * 		'0'B: datos de la tarjeta no transferidos durante esta actividad de control,
 * 		'1'B: datos de la tarjeta transferidos durante esta actividad de control
 * 'v'B transferencia de los datos de la VU:
 * 		'0'B: datos de la VU no transferidos durante esta actividad de control,
 * 		'1'B: datos de la VU transferidos durante esta actividad de control
 * 'p'B impresión:
 * 		'0'B: no se imprimen datos durante esta actividad de control,
 * 		'1'B: se imprimen datos durante esta actividad de control
 * 'd'B visualización:
 * 		'0'B: no se visualizan datos durante esta actividad de control,
 *		'1'B: se visualizan datos durante esta actividad de control
 *'xxxx'B No se utiliza.
 *
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class ControlType {
	
	private String c,v,p,d;
	
	public ControlType(){
		
	}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public ControlType(byte[] bs){
		String s = String.format("%8s", Integer.toBinaryString(bs [0] & 0xFF)).replace(' ', '0');
		
		@SuppressWarnings("unused")
		int c,v,p,d;		
		c=Integer.valueOf(s.substring(0, 1));
		v=Integer.valueOf(s.substring(1, 2));
		p=Integer.valueOf(s.substring(2, 3));
		d=Integer.valueOf(s.substring(3, 4));
		
		//'c'B transferencia de los datos de la tarjeta:
		this.c=(c==0)?"datos de la tarjeta no transferidos durante esta actividad de control":
					"datos de la tarjeta transferidos durante esta actividad de control";
		//'v'B transferencia de los datos de la VU:
		this.v=(v==0)?"datos de la VU no transferidos durante esta actividad de control":
					"datos de la VU transferidos durante esta actividad de control";
		// 'p'B impresión:
		this.p=(p==0)?"no se imprimen datos durante esta actividad de control":
					"se imprimen datos durante esta actividad de control";
		// 'd'B visualización:
		this.d=(v==0)?"no se visualizan datos durante esta actividad de control":
					"se visualizan datos durante esta actividad de control";
		
	}
	
	/**
	 * Obtiene B transferencia de los datos de la tarjeta:
	 * 		'0'B: datos de la tarjeta no transferidos durante esta actividad de control,
	 * 		'1'B: datos de la tarjeta transferidos durante esta actividad de control 
	 * @return String
	 */
	public String getC() {
		return c;
	}
	/**
	 * B transferencia de los datos de la tarjeta:
	 * 		'0'B: datos de la tarjeta no transferidos durante esta actividad de control,
	 * 		'1'B: datos de la tarjeta transferidos durante esta actividad de control
	 * @param c
	 */
	public void setC(String c) {
		this.c = c;
	}
	/**
	 * Obtiene B transferencia de los datos de la VU:
	 * 		'0'B: datos de la VU no transferidos durante esta actividad de control,
	 * 		'1'B: datos de la VU transferidos durante esta actividad de control
	 * @return String
	 */
	public String getV() {
		return v;
	}
	/**
	 * Asigna B transferencia de los datos de la VU:
	 * 		'0'B: datos de la VU no transferidos durante esta actividad de control,
	 * 		'1'B: datos de la VU transferidos durante esta actividad de control
	 * @param v
	 */
	public void setV(String v) {
		this.v = v;
	}
	/**
	 * Obtiene B impresión:
	 * 		'0'B: no se imprimen datos durante esta actividad de control,
 	 * 		'1'B: se imprimen datos durante esta actividad de control
	 * @return String 
	 */
	public String getP() {
		return p;
	}
	/**
	 * B impresión:
	 * 		'0'B: no se imprimen datos durante esta actividad de control,
	 * 		'1'B: se imprimen datos durante esta actividad de control
	 * @param p
	 */
	public void setP(String p) {
		this.p = p;
	}
	/**
	 * Obtiene B visualización:
	 * 		'0'B: no se visualizan datos durante esta actividad de control,
	 *		'1'B: se visualizan datos durante esta actividad de control 
	 * @return String
	 */
	public String getD() {
		return d;
	}
	/**
	 * B visualización:
	 * 		'0'B: no se visualizan datos durante esta actividad de control,
	 *		'1'B: se visualizan datos durante esta actividad de control
	 * @param d
	 */
	public void setD(String d) {
		this.d = d;
	}
	
	@Override
	public String toString() {
		return "\nControlType [c=" + c + ", v=" + v + ", p=" + p + ", d=" + d
				+ "]";
	}
	
}
