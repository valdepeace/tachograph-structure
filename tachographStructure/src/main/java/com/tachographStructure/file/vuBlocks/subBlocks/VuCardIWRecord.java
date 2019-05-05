/**
 * 
 */
package com.tachographStructure.file.vuBlocks.subBlocks;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import com.tachographStructure.file.driverCardBlock.subBlocks.ActivityChangeInfo;
import com.tachographStructure.file.driverCardBlock.subBlocks.FullCardNumber;
import com.tachographStructure.file.driverCardBlock.subBlocks.HolderName;
import com.tachographStructure.file.vuBlocks.VuSizes;
import com.tachographStructure.helpers.OdometerShort;
import com.tachographStructure.helpers.RealTime;
import com.tachographStructure.helpers.Number;

/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */

/**
 *
 * 2.120. VuCardIWRecord
 * Información almacenada en una unidad intravehicular y relativa a un ciclo de inserción y
 * extracción de una tarjeta de conductor o de una tarjeta del centro de ensayo en la unidad intravehicular (requisito 081).
 * VuCardIWRecord::= SEQUENCE {
 * cardHolderName HolderName,
 * fullCardNumber FullCardNumber,
 * cardExpiryDate TimeReal,
 * cardInsertionTime TimeReal,
 * vehicleOdometerValueAtInsertion OdometerShort,
 * cardSlotNumber CardSlotNumber,
 * cardWithdrawalTime TimeReal,
 * vehicleOdometerValueAtWithdrawal OdometerShort,
 * previousVehicleInfo PreviousVehicleInfo
 * manualInputFlag ManualInputFlag
 * }
 * cardHolderName es el nombre y los apellidos del titular de la tarjeta de conductor o de la tarjeta del centro de ensayo, según los datos almacenados en la propia tarjeta.
 * fullCardNumber es el tipo de tarjeta, el nombre del Estado miembro que la expidió y el número de tarjeta, según los datos almacenados en la propia tarjeta.
 * cardExpiryDate es la fecha de caducidad de la tarjeta, según los datos almacenados en la propia tarjeta.
 * cardInsertionTime es la fecha y la hora de inserción.
 * vehicleOdometerValueAtInsertion es la lectura del cuentakilómetros del vehículo en el momento de insertar la tarjeta.
 * cardSlotNumber es la ranura donde se inserta la tarjeta.
 * cardWithdrawalTime es la fecha y la hora de extracción.
 * vehicleOdometerValueAtWithdrawal es la lectura del cuentakilómetros del vehículo en el momento de extraer la tarjeta.
 * previousVehicleInfo contiene información sobre el vehículo anterior que utilizara el conductor, según los datos almacenados en la tarjeta.
 * manualInputFlag es una bandera que indica si el titular de la tarjeta ha introducido manualmente alguna actividad del conductor en el momento de insertar la tarjeta.
 *
 */
public class VuCardIWRecord {

   private HolderName cardHolderName;
   private FullCardNumber fullCardNumber;
   private Date cardExpeiryDate;
   private Date cardInsertionTime;
   private int vehicleOdometerValueAtInsertion;
   private String cardSlotNumber;
   private Date cardWithdrawalTime;
   private int vehicleOdometerValueAtWithdrawal;
   private PreviousVehicleInfo previousVehicleInfo;
   private String manualInputFlag;


   public VuCardIWRecord(byte[] bytes) throws UnsupportedEncodingException {
       int start=0;

       this.cardHolderName=new HolderName(Arrays.copyOfRange(bytes, start, start+=VuSizes.CARDHOLDERNAME.getSize()));
       this.fullCardNumber=new FullCardNumber(Arrays.copyOfRange(bytes, start, start+=VuSizes.FULLCARDNUMBER_TREP2.getSize()));
       this.cardExpeiryDate=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=VuSizes.CARDEXPIRYTDATE.getSize()));
       this.cardInsertionTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=VuSizes.CARDINSERTIONTIME.getSize()));
       this.vehicleOdometerValueAtInsertion=OdometerShort.getOdometerShort(Arrays.copyOfRange(bytes, start, start+=VuSizes.VEHICLEODOMETERVALUEATINSERTION.getSize()));
       int n=Number.getShort_8(Arrays.copyOfRange(bytes, start, start+=VuSizes.CARDSLOTNUMBER.getSize()));
       if(n==0){this.cardSlotNumber="driverSlot (0)";}else{this.cardSlotNumber="co-driverSlot (1)";};
       this.cardWithdrawalTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=VuSizes.CARDWITHDRAWALTIME_VUCARDWDATA.getSize()));
       this.vehicleOdometerValueAtWithdrawal=OdometerShort.getOdometerShort(Arrays.copyOfRange(bytes, start, start+=VuSizes.VEHICLEODOMETERVALUEATWITHDRAWAL.getSize()));
       this.previousVehicleInfo=new PreviousVehicleInfo(Arrays.copyOfRange(bytes, start, start+=VuSizes.PREVIOUSVEHICLEINFO.getSize()));
       n=Number.getShort_8(Arrays.copyOfRange(bytes, start, start+=VuSizes.MANUALINPUTFLAG.getSize()));
       if(n==0){this.manualInputFlag="noEntry (0)";}else{this.manualInputFlag="manualEntries (1)";};

   }

   /**
    * @return the cardHolderName
    */
   public HolderName getCardHolderName() {
       return cardHolderName;
   }

   /**
    * @param cardHolderName the cardHolderName to set
    */
   public void setCardHolderName(HolderName cardHolderName) {
       this.cardHolderName = cardHolderName;
   }

   /**
    * @return the fullCardNumber
    */
   public FullCardNumber getFullCardNumber() {
       return fullCardNumber;
   }

   /**
    * @param fullCardNumber the fullCardNumber to set
    */
   public void setFullCardNumber(FullCardNumber fullCardNumber) {
       this.fullCardNumber = fullCardNumber;
   }

   /**
    * @return the cardExpeiryDate
    */
   public Date getCardExpeiryDate() {
       return cardExpeiryDate;
   }

   /**
    * @param cardExpeiryDate the cardExpeiryDate to set
    */
   public void setCardExpeiryDate(Date cardExpeiryDate) {
       this.cardExpeiryDate = cardExpeiryDate;
   }

   /**
    * @return the cardInsertionTime
    */
   public Date getCardInsertionTime() {
       return cardInsertionTime;
   }

   /**
    * @param cardInsertionTime the cardInsertionTime to set
    */
   public void setCardInsertionTime(Date cardInsertionTime) {
       this.cardInsertionTime = cardInsertionTime;
   }

   /**
    * @return the vehicleOdometerValueAtInsertion
    */
   public int getVehicleOdometerValueAtInsertion() {
       return vehicleOdometerValueAtInsertion;
   }

   /**
    * @param vehicleOdometerValueAtInsertion the vehicleOdometerValueAtInsertion to set
    */
   public void setVehicleOdometerValueAtInsertion(int vehicleOdometerValueAtInsertion) {
       this.vehicleOdometerValueAtInsertion = vehicleOdometerValueAtInsertion;
   }

   /**
    * @return the cardSlotNumber
    */
   public String getCardSlotNumber() {
       return cardSlotNumber;
   }

   /**
    * @param cardSlotNumber the cardSlotNumber to set
    */
   public void setCardSlotNumber(String cardSlotNumber) {
       this.cardSlotNumber = cardSlotNumber;
   }

   /**
    * @return the cardWithdrawalTime
    */
   public Date getCardWithdrawalTime() {
       return cardWithdrawalTime;
   }

   /**
    * @param cardWithdrawalTime the cardWithdrawalTime to set
    */
   public void setCardWithdrawalTime(Date cardWithdrawalTime) {
       this.cardWithdrawalTime = cardWithdrawalTime;
   }

   /**
    * @return the vehicleOdometerValueAtWithdrawal
    */
   public int getVehicleOdometerValueAtWithdrawal() {
       return vehicleOdometerValueAtWithdrawal;
   }

   /**
    * @param vehicleOdometerValueAtWithdrawal the vehicleOdometerValueAtWithdrawal to set
    */
   public void setVehicleOdometerValueAtWithdrawal(int vehicleOdometerValueAtWithdrawal) {
       this.vehicleOdometerValueAtWithdrawal = vehicleOdometerValueAtWithdrawal;
   }

   /**
    * @return the previousVehicleInfo
    */
   public PreviousVehicleInfo getPreviousVehicleInfo() {
       return previousVehicleInfo;
   }

   /**
    * @param previousVehicleInfo the previousVehicleInfo to set
    */
   public void setPreviousVehicleInfo(PreviousVehicleInfo previousVehicleInfo) {
       this.previousVehicleInfo = previousVehicleInfo;
   }

   /**
    * @return the manualInputFlag
    */
   public String getManualInputFlag() {
       return manualInputFlag;
   }

   /**
    * @param manualInputFlag the manualInputFlag to set
    */
   public void setManualInputFlag(String manualInputFlag) {
       this.manualInputFlag = manualInputFlag;
   }


   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
       return "VuCardIWRecord [cardHolderName=" + cardHolderName + ", fullCardNumber=" + fullCardNumber
               + ", cardExpeiryDate=" + cardExpeiryDate + ", cardInsertionTime=" + cardInsertionTime
               + ", vehicleOdometerValueAtInsertion=" + vehicleOdometerValueAtInsertion + ", cardSlotNumber="
               + cardSlotNumber + ", cardWithdrawalTime=" + cardWithdrawalTime + ", vehicleOdometerValueAtWithdrawal="
               + vehicleOdometerValueAtWithdrawal + ", previousVehicleInfo=" + previousVehicleInfo
               + ", manualInputFlag=" + manualInputFlag + "]";
   }



}
