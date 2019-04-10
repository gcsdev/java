package models;

import play.data.format.Formats;
import javax.persistence.ManyToOne;

import java.util.Date;

import javax.persistence.Entity;

/**
 * Detail entity managed by Ebean
 */
@Entity
public class Detail extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Integer register;
    private Integer establishment;
    private String initialDateTransaction;
    private String saleDate;
    private Integer eventHour;
    private Integer eventType;
    private Integer transactionType;
    private String serieNumber;
    private String transactionCode;
    private String orderCode;
    private Integer transactionValue;
    private Integer parcelValue;
    private String payment;
    private String plan;
    private String parcel;
    private Integer parcelAmount;
    private String paymentDataPrevision;
    private Integer taxaParcelamentoComprador;
    private Integer ticketRate;
    private Integer transactionOriginalValue;
    private Integer salesmanParcelRate;
    private Integer intermediatesRate;
    private Integer intermediation;
    private Integer salesmanTicketRate;
    private Integer applicationPass;
    private Integer transactionLiquidValue;
    private Integer paymentStatus;
    private String filler0;
    private Integer paymentTyper;
    private String financialInstituition;
    private String inputChannel;
    private Integer reader;
    private Integer capture;
    private String logicNumber;
    private String NSU;
    private String filler1;
    private String binCard;
    private String holderCard;
    private String authCode;
    private String cvCode;
    private Integer reserved;

    @ManyToOne
    public Header header;

    @ManyToOne
    public Trailler trailler;

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public Integer getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Integer establishment) {
        this.establishment = establishment;
    }

    public String getInitialDateTransaction() {
        return initialDateTransaction;
    }

    public void setInitialDateTransaction(String initialDateTransaction) {
        this.initialDateTransaction = initialDateTransaction;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getEventHour() {
        return eventHour;
    }

    public void setEventHour(Integer eventHour) {
        this.eventHour = eventHour;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public String getSerieNumber() {
        return serieNumber;
    }

    public void setSerieNumber(String serieNumber) {
        this.serieNumber = serieNumber;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Integer transactionValue) {
        this.transactionValue = transactionValue;
    }

    public Integer getParcelValue() {
        return parcelValue;
    }

    public void setParcelValue(Integer parcelValue) {
        this.parcelValue = parcelValue;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    public Integer getParcelAmount() {
        return parcelAmount;
    }

    public void setParcelAmount(Integer parcelAmount) {
        this.parcelAmount = parcelAmount;
    }

    public String getPaymentDataPrevision() {
        return paymentDataPrevision;
    }

    public void setPaymentDataPrevision(String paymentDataPrevision) {
        this.paymentDataPrevision = paymentDataPrevision;
    }

    public Integer getTaxaParcelamentoComprador() {
        return taxaParcelamentoComprador;
    }

    public void setTaxaParcelamentoComprador(Integer taxaParcelamentoComprador) {
        this.taxaParcelamentoComprador = taxaParcelamentoComprador;
    }

    public Integer getTicketRate() {
        return ticketRate;
    }

    public void setTicketRate(Integer ticketRate) {
        this.ticketRate = ticketRate;
    }

    public Integer getTransactionOriginalValue() {
        return transactionOriginalValue;
    }

    public void setTransactionOriginalValue(Integer transactionOriginalValue) {
        this.transactionOriginalValue = transactionOriginalValue;
    }

    public Integer getSalesmanParcelRate() {
        return salesmanParcelRate;
    }

    public void setSalesmanParcelRate(Integer salesmanParcelRate) {
        this.salesmanParcelRate = salesmanParcelRate;
    }

    public Integer getIntermediatesRate() {
        return intermediatesRate;
    }

    public void setIntermediatesRate(Integer intermediatesRate) {
        this.intermediatesRate = intermediatesRate;
    }

    public Integer getIntermediation() {
        return intermediation;
    }

    public void setIntermediation(Integer intermediation) {
        this.intermediation = intermediation;
    }

    public Integer getSalesmanTicketRate() {
        return salesmanTicketRate;
    }

    public void setSalesmanTicketRate(Integer salesmanTicketRate) {
        this.salesmanTicketRate = salesmanTicketRate;
    }

    public Integer getApplicationPass() {
        return applicationPass;
    }

    public void setApplicationPass(Integer applicationPass) {
        this.applicationPass = applicationPass;
    }

    public Integer getTransactionLiquidValue() {
        return transactionLiquidValue;
    }

    public void setTransactionLiquidValue(Integer transactionLiquidValue) {
        this.transactionLiquidValue = transactionLiquidValue;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getFiller0() {
        return filler0;
    }

    public void setFiller0(String filler0) {
        this.filler0 = filler0;
    }

    public Integer getPaymentTyper() {
        return paymentTyper;
    }

    public void setPaymentTyper(Integer paymentTyper) {
        this.paymentTyper = paymentTyper;
    }

    public String getFinancialInstituition() {
        return financialInstituition;
    }

    public void setFinancialInstituition(String financialInstituition) {
        this.financialInstituition = financialInstituition;
    }

    public String getInputChannel() {
        return inputChannel;
    }

    public void setInputChannel(String inputChannel) {
        this.inputChannel = inputChannel;
    }

    public Integer getReader() {
        return reader;
    }

    public void setReader(Integer reader) {
        this.reader = reader;
    }

    public Integer getCapture() {
        return capture;
    }

    public void setCapture(Integer capture) {
        this.capture = capture;
    }

    public String getLogicNumber() {
        return logicNumber;
    }

    public void setLogicNumber(String logicNumber) {
        this.logicNumber = logicNumber;
    }

    public String getNSU() {
        return NSU;
    }

    public void setNSU(String NSU) {
        this.NSU = NSU;
    }

    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public String getBinCard() {
        return binCard;
    }

    public void setBinCard(String binCard) {
        this.binCard = binCard;
    }

    public String getHolderCard() {
        return holderCard;
    }

    public void setHolderCard(String holderCard) {
        this.holderCard = holderCard;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getCvCode() {
        return cvCode;
    }

    public void setCvCode(String cvCode) {
        this.cvCode = cvCode;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

}
