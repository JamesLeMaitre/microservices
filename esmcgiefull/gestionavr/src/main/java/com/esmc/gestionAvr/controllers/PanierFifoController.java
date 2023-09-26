package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.entities.PanierFifo;
import com.esmc.gestionAvr.feign.TeClient;
import com.esmc.gestionAvr.repositories.FifoRepository;
import com.esmc.gestionAvr.repositories.PanierFifoRepository;
import com.esmc.gestionAvr.services.FifoService;
import com.esmc.gestionAvr.servicesInterfaces.PanierFifoInterface;
import com.esmc.models.Formatter;
import constants.javaConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/fifopaniers/")
public class PanierFifoController extends DataFormatter<PanierFifo> {

    @Autowired
    private PanierFifoRepository panierFifoRepository;

    @Autowired
    private FifoRepository fifoRepository;

    @Autowired
    private TeClient teClient;

    @Autowired
    private FifoService fifoService;

    @Autowired
    PanierFifoInterface panierFifoInterface;

    @GetMapping("list")
    public Object listPanierFifo(){

        try {
                return  renderDataArray(true, panierFifoInterface.listPanierFifo(),"list panier fifio");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("by/detailAgr/{id}")
    public Object ListByIdDetailAgr(@PathVariable("id") Long id){
        try {
            return  renderDataArray(true, panierFifoInterface.getlistByIdDetailAgr(id),"List DetailAgr");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/by/{id}")
    public Object deleteByIdDetailAgr(@PathVariable("id") Long id){
        try {
            panierFifoInterface.deleteByIdDetailAgr(id);
            return  renderStringData(true, "success","Delete panier fifo");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
             String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }





    public void  treatPanierFifo () throws Exception {
        Date currentDate = new Date();
        currentDate.setDate(currentDate.getDate()-3);
        System.out.println("Date use to compare 21 : "+ currentDate.toString());
        List<PanierFifo> listPanierReady = new ArrayList<PanierFifo>();
        System.out.println("Déclare panier Fifo ");
       // try {
            listPanierReady = panierFifoRepository.getPanierFifoReadyToGoToFifoByDate(currentDate);
       /* }catch (Exception e){
            System.out.println(e.getMessage());
        }*/

        System.out.println("List paniers "+ listPanierReady.toString());
        for (PanierFifo panierFifo: listPanierReady){
            System.out.println("Date use to compare: "+ panierFifo.toString());
            boolean isSell = !panierFifo.getIsBuy();
            //recuperation des valeurs maximales
            Double valMaxFirst=0.0;
            Double valMaxSecond=0.0;

            if (isSell) {
                valMaxSecond = javaConst.venteBCIMaxAmount;
                valMaxFirst = teClient.getConversionBCIBAN(valMaxSecond);
            } else {
                valMaxFirst  = javaConst.achatBAnMaxAmount;
                valMaxSecond = teClient.getConversionBANBCI(valMaxFirst);
            }

            Double firstAmount = panierFifo.getActionAmountFirstType();
            Double secondAcount = panierFifo.getActionAmountSecondType();
            Fifo f = new Fifo();
            f.setIsBuy(!isSell);
            f.setAvr(panierFifo.getAvr());

            if(valMaxFirst < firstAmount) {

                // creation des fifo avec les montant maximales
                f.setDefaultAmountFirstType(valMaxFirst);
                f.setActionAmountFirstType(valMaxFirst);
                f.setDefaultAmountSecondType(valMaxSecond);
                f.setActionAmountSecondType(valMaxSecond);

                //mise du reste dans un panier
                panierFifo.setDefaultAmountFirstType( firstAmount - valMaxFirst);
                panierFifo.setActionAmountFirstType( firstAmount - valMaxFirst);
                panierFifo.setDefaultAmountSecondType(secondAcount - valMaxSecond);
                panierFifo.setActionAmountSecondType(secondAcount - valMaxSecond);
                panierFifoRepository.save(panierFifo);

            }else{
                f.setDefaultAmountFirstType(firstAmount);
                f.setActionAmountFirstType(firstAmount);
                f.setDefaultAmountSecondType(secondAcount);
                f.setActionAmountSecondType(secondAcount);
            }
            System.out.println("We Save the fifo ");
            fifoService.addFifo(f, panierFifo.getDetailAgr());
            System.out.println("create panier");
            if(panierFifo.getActionAmountFirstType()<=0){
                panierFifoRepository.delete(panierFifo);
            }
        }
    }
}
