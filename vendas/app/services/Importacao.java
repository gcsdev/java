package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.*;

import akka.stream.impl.io.InputStreamSinkStage.Data;
import models.Header;
import models.Detail;
import models.Trailler;

import repository.HeaderRepository;
import repository.DetailRepository;
import repository.TraillerRepository;

import play.Play;
import play.inject.ApplicationLifecycle;
import scala.xml.Source;

public class Importacao {

    ArrayList<Integer> informationSizeHeader;
    ArrayList<Integer> informationSizeDetail;
    ArrayList<Integer> informationSizeTrailler;

    private final HeaderRepository headerRepository;
    private final DetailRepository detailRepository;
    private final TraillerRepository traillerRepository;

    @Inject
    public Importacao(HeaderRepository headerRepository, DetailRepository detailRepository,
            TraillerRepository traillerRepository) {

        this.headerRepository = headerRepository;
        this.detailRepository = detailRepository;
        this.traillerRepository = traillerRepository;

        createInformationSizeListHeader();
        createInformationSizeListDetail();
        createInformationSizeListTrailler();
    }

    /**
     * This method is used to import sales of the file
     **/
    public void importSaleFile() {
        ArrayList<Detail> objectDetails = new ArrayList<Detail>();
        Header headerSaved = null;
        Trailler traillerSaved = null;
        try {

            FileReader arq = new FileReader("app/file/processoSeletivoEquals.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            headerSaved = headerProccess(linha);
            System.out.println("Header");
            while (linha != null) {
                linha = lerArq.readLine();
                if (linha != null) {
                    if (linha.charAt(0) == '1') {
                        // System.out.println("Detalhes");
                        objectDetails.add(detailProccess(linha));
                    } else if (linha.charAt(0) == '9') {
                        System.out.println("Trailler");
                        traillerSaved = traillerProccess(linha);
                    }
                }
            }

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        persistDetail(objectDetails, headerSaved, traillerSaved);
    }

    /**
     * This method is used to proccess header object
     * 
     * @param lineTrailler - line of the file
     * @return header - triller object created
     * 
     **/
    public Header headerProccess(String linhaHeader) {
        Header header = new Header();
        header.setRegister(0);
        boolean eof = false;
        int indiceInicial = 2;
        int indicefinal = informationSizeHeader.get(1) + indiceInicial - 1;
        int indiceLista = 1;

        while (!eof) {
            System.out.println("Indice" + indiceLista);
            String value = linhaHeader.substring(indiceInicial, indicefinal);
            System.out.println(value);
            header = createHeaderObject(header, indiceLista, value);
            indiceInicial = indicefinal;
            indiceLista++;
            indicefinal = informationSizeHeader.get(indiceLista) + indiceInicial;
            if (indiceLista >= informationSizeHeader.size() - 1) {
                eof = true;
            }
        }
        System.out.println(header);
        // persistir objeto header criado
        Long id = headerRepository.insert(header);
        header.id = id;
        return header;
    }

    /**
     * This method is used to create list of the information's size to header
     * 
     **/
    private void createInformationSizeListHeader() {
        this.informationSizeHeader = new ArrayList<>(12);
        this.informationSizeHeader.add(1);
        this.informationSizeHeader.add(10);
        this.informationSizeHeader.add(8);
        this.informationSizeHeader.add(8);
        this.informationSizeHeader.add(8);
        this.informationSizeHeader.add(7);
        this.informationSizeHeader.add(5);
        this.informationSizeHeader.add(2);
        this.informationSizeHeader.add(21);
        this.informationSizeHeader.add(3);
        this.informationSizeHeader.add(5);
        this.informationSizeHeader.add(453);
    }

    /**
     * This method is used to proccess detail object
     * 
     * @param lineTrailler - line of the file
     * @return detail - triller object created
     * 
     **/
    public Detail detailProccess(String linhaDetalhes) {
        Detail detail = new Detail();
        detail.setRegister(1);
        boolean eof = false;
        int indiceInicial = 1;
        int indicefinal = informationSizeDetail.get(1) + indiceInicial;
        int indiceLista = 1;
        while (!eof) {
            // System.out.println(linhaDetalhes.substring(indiceInicial, indicefinal));
            // System.out.println("Indice : " + indiceLista);
            String value = linhaDetalhes.substring(indiceInicial, indicefinal);
            // System.out.println("Value : " + value);
            detail = createDetailObject(detail, indiceLista, value);
            indiceInicial = indicefinal;
            indiceLista++;
            indicefinal = informationSizeDetail.get(indiceLista) + indiceInicial;
            if (indiceLista >= informationSizeDetail.size() - 1) {
                eof = true;
            }
        }
        return detail;
    }

    /**
     * This method is used to create list of the information's size to Detail
     * 
     **/
    private void createInformationSizeListDetail() {
        this.informationSizeDetail = new ArrayList<>(41);
        this.informationSizeDetail.add(1);
        this.informationSizeDetail.add(10);
        this.informationSizeDetail.add(8);
        this.informationSizeDetail.add(8);
        this.informationSizeDetail.add(6);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(8);
        this.informationSizeDetail.add(32);
        this.informationSizeDetail.add(20);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(1);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(8);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(13);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(30);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(2);
        this.informationSizeDetail.add(32);
        this.informationSizeDetail.add(11);
        this.informationSizeDetail.add(3);
        this.informationSizeDetail.add(6);
        this.informationSizeDetail.add(4);
        this.informationSizeDetail.add(6);
        this.informationSizeDetail.add(32);
        this.informationSizeDetail.add(139);

    }

    /**
     * This method is used to create list of the information's size to Trailler
     * 
     **/
    private void createInformationSizeListTrailler() {
        informationSizeTrailler = new ArrayList<>(3);
        informationSizeTrailler.add(1);
        informationSizeTrailler.add(11);
        informationSizeTrailler.add(518);
    }

    /**
     * This method is used to proccess trailler object
     * 
     * @param lineTrailler - line of the file
     * @return Trailler - triller object created
     * 
     **/
    public Trailler traillerProccess(String linhaTrailler) {
        Trailler trailler = new Trailler();
        trailler.setRegisterType(9);
        boolean eof = false;
        int indiceInicial = 2;
        int indicefinal = informationSizeTrailler.get(1) + indiceInicial - 1;
        int indiceLista = 1;
        while (!eof) {
            String value = linhaTrailler.substring(indiceInicial, indicefinal);
            trailler.setRegisterTotal(Integer.valueOf(value));
            indiceInicial = indicefinal;
            indiceLista++;
            indicefinal = informationSizeTrailler.get(indiceLista) + indiceInicial;
            if (indiceLista >= informationSizeTrailler.size() - 1) {
                eof = true;
            }
        }
        // persistir trailler
        Long id = traillerRepository.insert(trailler);
        trailler.id = id;
        return trailler;
    }

    /**
     * This method is used to set attributes in the header object
     * 
     * @param objectHeader - header object
     * @param pos          - position of the line
     * @param value        - value of attribute
     * 
     **/
    public Header createHeaderObject(Header objectHeader, Integer pos, String value) {
        switch (pos) {
        case 1:
            objectHeader.setEstablishment(Integer.valueOf(value));
            break;
        case 2:
            String year = value.substring(0, 4);
            String month = value.substring(4, 6);
            String day = value.substring(6, 8);
            System.out.println("Year" + year);
            System.out.println("month" + month);
            System.out.println("day" + day);
            objectHeader.setDateProcessing(day + "/" + month + "/" + year);
            break;
        case 3:
            objectHeader.setInitialPeriod(Integer.valueOf(value));
            break;
        case 4:
            objectHeader.setFinalPeriod(Integer.valueOf(value));
            break;
        case 5:
            objectHeader.setSequence(Integer.valueOf(value));
            break;
        case 6:
            objectHeader.setEnterprise(value);
            break;
        case 7:
            objectHeader.setExtractType(Integer.valueOf(value));
            break;
        case 8:
            objectHeader.setFiller(value);
            break;
        case 9:
            objectHeader.setLayoutVersion(value);
            break;
        case 10:
            objectHeader.setReleaseVersion(value);
            break;
        case 11:
            objectHeader.setReserved(value);
            break;
        default:
            break;
        }
        return objectHeader;
    }

    /**
     * This method is used to set attributes inthe detail object
     * 
     * @param objectDetail - detail object
     * @param pos          - posicion of the line
     * @param value        - value of attribute
     * 
     **/
    public Detail createDetailObject(Detail objectDetail, Integer pos, String value) {
        switch (pos) {
        case 1:
            objectDetail.setEstablishment(Integer.valueOf(value));
            break;
        case 2:
            String year = value.substring(0, 4);
            String month = value.substring(4, 6);
            String day = value.substring(6, 8);
            System.out.println("Year" + year);
            System.out.println("month" + month);
            System.out.println("day" + day);

            // date.ge
            objectDetail.setInitialDateTransaction(day + "/" + month + "/" + year);
            // System.out.println("date" + date.);

            break;
        case 3:
            year = value.substring(0, 4);
            month = value.substring(4, 6);
            day = value.substring(6, 8);
            System.out.println("Year" + year);
            System.out.println("month" + month);
            System.out.println("day" + day);

            objectDetail.setSaleDate(day + "/" + month + "/" + year);
            break;
        case 4:
            objectDetail.setEventHour(Integer.valueOf(value));
            break;
        case 5:
            objectDetail.setEventType(Integer.valueOf(value));
            break;
        case 6:
            objectDetail.setTransactionType(Integer.valueOf(value));
            ;
            break;
        case 7:
            objectDetail.setSerieNumber(value);
            break;
        case 8:
            objectDetail.setTransactionCode(value);
            break;
        case 9:
            objectDetail.setOrderCode(value);
            break;
        case 10:
            objectDetail.setTransactionValue(Integer.valueOf(value));
            break;
        case 11:
            objectDetail.setParcelValue(Integer.valueOf(value));
            break;
        case 12:
            objectDetail.setPayment(value);
            break;
        case 13:
            objectDetail.setPlan(value);
            break;
        case 14:
            objectDetail.setParcel(value);
            break;
        case 15:
            objectDetail.setParcelAmount(Integer.valueOf(value));
            break;
        case 16:
            year = value.substring(0, 4);
            month = value.substring(4, 6);
            day = value.substring(6, 8);
            System.out.println("Year" + year);
            System.out.println("month" + month);
            System.out.println("day" + day);

            objectDetail.setPaymentDataPrevision(day + "/" + month + "/" + year);
            break;
        case 17:
            objectDetail.setTaxaParcelamentoComprador(Integer.valueOf(value));
            break;
        case 18:
            objectDetail.setTicketRate(Integer.valueOf(value));
            break;
        case 19:
            objectDetail.setTransactionOriginalValue(Integer.valueOf(value));
            break;
        case 20:
            objectDetail.setSalesmanParcelRate(Integer.valueOf(value));
            break;
        case 21:
            objectDetail.setIntermediatesRate(Integer.valueOf(value));
            break;
        case 22:
            objectDetail.setIntermediation(Integer.valueOf(value));
            break;
        case 23:
            objectDetail.setSalesmanTicketRate(Integer.valueOf(value));
            break;
        case 24:
            objectDetail.setApplicationPass(Integer.valueOf(value));
            break;
        case 25:
            objectDetail.setTransactionLiquidValue(Integer.valueOf(value));
            break;
        case 26:
            objectDetail.setPaymentStatus(Integer.valueOf(value));
            break;
        case 27:
            objectDetail.setFiller0(value);
            break;
        case 28:
            objectDetail.setPaymentTyper(Integer.valueOf(value));
            break;
        case 29:
            objectDetail.setFinancialInstituition(value);
            break;
        case 30:
            objectDetail.setInputChannel(value);
            break;
        case 31:
            objectDetail.setReader(Integer.valueOf(value));
            break;
        case 32:
            objectDetail.setCapture(Integer.valueOf(value));
            break;
        case 33:
            objectDetail.setLogicNumber(value);
            break;
        case 34:
            objectDetail.setNSU(value);
            break;
        case 35:
            objectDetail.setFiller1(value);
            break;
        case 36:
            objectDetail.setBinCard(value);
            break;
        case 37:
            objectDetail.setHolderCard(value);
            break;
        case 38:
            objectDetail.setAuthCode(value);
            break;
        case 39:
            objectDetail.setCvCode(value);
            break;
        case 40:
            objectDetail.setReserved(Integer.valueOf(value));
            break;

        default:
            break;
        }
        return objectDetail;
    }

    /**
     * This method is used to insert detail objects
     * 
     * @param objectDetails - details object list
     * @param header        - header
     * @param trailler      - traiiler
     * 
     **/
    public void persistDetail(ArrayList<Detail> objectDetails, Header header, Trailler trailler) {
        for (Detail detail : objectDetails) {
            detail.trailler = trailler;
            detail.header = header;
            detailRepository.insert(detail);
        }
    }

}
