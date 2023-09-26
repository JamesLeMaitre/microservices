package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.*;
import com.esmc.gestionAgr.fifo.repositories.*;
import com.esmc.gestionAgr.fifo.services.DetailSMEchangeService;
import com.esmc.gestionAgr.fifo.services.TerminalEchangeService;
import com.esmc.gestionAgr.fifo.utils.SupportMarchandConstant;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class DetailSMEchangeImpl implements DetailSMEchangeService {
    private final SupportMarchandEchangeRepository supportMarchandEchangeRepository;
    private final DetailSMEchangeRepository detailSMEchangeRepository;
    private final TerminalEchangeRepository terminalEchangeRepository;
    private final TerminalEchangeService terminalEchangeService;
    private final OrdreRepository ordreRepository;
    private final TypeOrdreRepository typeOrdreRepository;

    static String getAlphaNumericString() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        // Exemple d'une chaine aléatoire de 20 caractères
        for (int i = 0; i < 9; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public DetailSMEnchange mutationPourAchatFifo(Long idTeAcheteur, Long idTeVendeur, Double montant) throws IOException, WriterException {


        System.out.println("======================================== BUYER============================");
        System.out.println("buyer te : "+idTeAcheteur);
        System.out.println("======================================== BUYER============================");

        System.out.println("======================================== SELLER============================");
        System.out.println("seller te : "+idTeVendeur);
        System.out.println("======================================== SELLER============================");

        System.out.println("======================================== AMOUNT============================");
        System.out.println("seller te : " + montant);
        System.out.println("======================================== AMOUNT============================");
        DetailSMEnchange dsme = new DetailSMEnchange();

        DetailSMEnchange dt = new DetailSMEnchange();

        DetailSMEnchange dBLGBpV = new DetailSMEnchange();

        DetailSMEnchange deMPRgOPI = new DetailSMEnchange();

        SupportMarchandEnchage sm = supportMarchandEchangeRepository.findById(SupportMarchandConstant.QsupportMarchandBan).orElse(null);

        System.out.println("===========================SM================================");
        System.out.println("SM : " + sm);
        System.out.println("===========================SM================================");
        if (sm == null) {
            return null;
        }

        Double smTotal = detailSMEchangeRepository.sommeTotalSm(sm.getId(), idTeAcheteur);
        if (smTotal > 0 && montant <= smTotal) {


            DetailSMEnchange ds = mutationFondDisponible(sm.getId(), idTeAcheteur, montant);


            if (ds != null) {

                dt = this.createFondSortie(idTeAcheteur, ds.getId(), montant);
                System.out.println("===========================NIVEAU 1 DETAILSMECHANGE ================================");

                System.out.println("======================================== DT DETAIL SM 1============================");
                System.out.println("DT 1 : "+dt);

                // Creation de BCnr
                DetailSMEnchange dBCnr = this.create(idTeAcheteur, montant, SupportMarchandConstant.QsupportMarchandBCnr, dt.getId());
                dBCnr.setFondsFondDisponible(0.0);
                detailSMEchangeRepository.save(dBCnr);
                System.out.println("===========================NIVEAU 2 DETAILSMECHANGE ================================");

                // Creation de Fonds de sortie de BCnr
                DetailSMEnchange dBCnrFond = this.createFondSortie(idTeAcheteur, dBCnr.getId(), montant);
                System.out.println("===========================NIVEAU 3 DETAILSMECHANGE ================================");
                // Montant BCnrPRK

                Double montantPRM = terminalEchangeService.bcnrPrk(montant);
                    /**
                     * @Param
                     * @Exception : Never decomment this line
                     */
//                Double montantRenitialiserBCI = (montantPRM / 2187.5) * 70000;
                Double montantRenitialiserBCI = montantPRM;


                System.out.println("======================================MONTANT BCI==============================");
                System.out.println("montant bci : " + montantRenitialiserBCI);
                System.out.println("======================================MONTANT BCI==============================");

                DetailSMEnchange dBCnrPRk = this.create(idTeAcheteur, montantRenitialiserBCI, SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5, dBCnrFond.getId());
                dBCnrPRk.setFondsFondDisponible(0.0);
                detailSMEchangeRepository.save(dBCnrPRk);
                System.out.println("===========================NIVEAU 4 DETAILSMECHANGE ================================");

                // Creation de Fonds de sortie de BCnr PRK
                DetailSMEnchange dBCnrPRKFonds = this.createFondSortie(idTeAcheteur, dBCnrPRk.getId(), montantRenitialiserBCI);
                System.out.println("===========================NIVEAU 5 DETAILSMECHANGE ================================");
                // Creation de BLGCp Vendeur
                dBLGBpV = this.create(idTeVendeur, montantRenitialiserBCI, SupportMarchandConstant.supportMarchandBLGCp, dBCnrFond.getId());
                dBLGBpV.setFondsFondDisponible(0.0);
                detailSMEchangeRepository.save(dBLGBpV);
                System.out.println("===========================NIVEAU 6 DETAILSMECHANGE ================================");

                /**
                 * BCI -> BLGcp
                 *
                 * B
                 */

                DetailSMEnchange dBLGCpSortie = this.createFondSortie(idTeVendeur, dBLGBpV.getId(), montantRenitialiserBCI);
                System.out.println("===========================NIVEAU 7 DETAILSMECHANGE ================================");


                Ordre ordre = this.createOPi(SupportMarchandConstant.supportMarchandOPI, idTeVendeur, dBLGCpSortie.getId(), montantRenitialiserBCI);
                ordre.setFondsFondDisponible(0.0);
                ordreRepository.save(ordre);
                System.out.println("===========================NIVEAU 8 ORDRE ================================");

                System.out.println("======================================== ORDRE ============================");
                System.out.println("buyer te : " + ordre);
                log.info("Ordre : {}", ordre);
                System.out.println("======================================== ORDRE ============================");

                deMPRgOPI = this.create(idTeVendeur, montantRenitialiserBCI, SupportMarchandConstant.supportMarchandMPRg_OPI, ordre.getId());

                System.out.println("===========================NIVEAU 9 DETAILSMECHANGE ================================");


                System.out.println("======================================== DETAIL MPRG OPI ============================");
                System.out.println("deMPRgOPI te : " + deMPRgOPI);
                log.info("deMPRgOPI : {}", deMPRgOPI);
                System.out.println("======================================== DETAIL MPRG OPI ============================");
            } else {
                return null;
            }
        } else {
            return null;
        }
        return deMPRgOPI;
    }

    public DetailSMEnchange mutationFondDisponible(Long idSm, Long idTe, Double montant) {

        DetailSMEnchange ds = new DetailSMEnchange();

        Double montantGlobal = montant;

        List<DetailSMEnchange> listD = detailSMEchangeRepository.listdSMFondsDisponible(idSm, idTe);

        if (listD.size() > 0) {

            for (DetailSMEnchange d : listD) {
                if (d.getFondsFondDisponible() <= montantGlobal) {
                    montantGlobal = montantGlobal - d.getFondsFondDisponible();
                    d.setFondsSortie(-d.getFondsEntre());
                    d.setFondsFondDisponible(0.0);
                    ds = detailSMEchangeRepository.save(d);
                } else {
                    Double result = d.getFondsFondDisponible() - montantGlobal;
                    d.setFondsSortie(d.getFondsSortie() - montantGlobal);
                    d.setFondsFondDisponible(result);
                    ds = detailSMEchangeRepository.save(d);
                    break;
                }
            }

        }
        return ds;
    }

    public DetailSMEnchange createFondSortie(Long idTe, Long idDetailSmEchange, Double montant) throws IOException {

        DetailSMEnchange detailSMEnchange = null;

        String codeSMMaBan = getAlphaNumericString();
        while (isPresent(codeSMMaBan)) {
            codeSMMaBan = getAlphaNumericString();
        }

        byte[] res = toByteArray(montant);
        DetailSMEnchange d = detailSMEchangeRepository.detailSMEchangeByIdTe(idDetailSmEchange, idTe);

        if (d == null) {
            return null;
        }

        // if (montant <= d.getFondsFondDisponible()) {
        //
        // d.setFondsFondDisponible(d.getFondsFondDisponible() - montant);
        //
        // detailSMEnchangeRepository.save(d);

        detailSMEnchange = new DetailSMEnchange();
        detailSMEnchange.setTerminalEchange(d.getTerminalEchange());
        detailSMEnchange.setCoutUnitaire(montant);
        detailSMEnchange.setSourceEnree(montant);
        detailSMEnchange.setSourcceSortie(-montant);
        detailSMEnchange.setFondsEntre(montant);
        detailSMEnchange.setFondsFondDisponible(0.0);
        detailSMEnchange.setFondsSortie(-montant);
        detailSMEnchange.setQuantite(1);
        detailSMEnchange.setRefer(d.getId());
        detailSMEnchange.setSupportMarchandEnchage(d.getSupportMarchandEnchage());
        detailSMEnchange.setCodeSM(codeSMMaBan);
        detailSMEnchange.setCodeBar(String.valueOf(res));

        detailSMEnchange.setTotal(montant);

        //
        // }

        return detailSMEchangeRepository.save(detailSMEnchange);
    }

    public boolean isPresent(String codeSM) {
        Optional<DetailSMEnchange> opab = detailSMEchangeRepository.findDetailSMEnchangeByCodeSM(codeSM);
        return opab.isPresent();
    }

    private byte[] toByteArray(Double contents) throws IOException {
        BarcodeFormat format = BarcodeFormat.CODE_39;

        int width = 180;
        int height = 40;

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            Code39Writer writer = new Code39Writer();
            BitMatrix bitMatrix = writer.encode(String.valueOf(contents), format, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", output);
            return output.toByteArray();
        }
    }

    public DetailSMEnchange create(Long idTe, Double montant, Long idSm, Long refer) {

        try {
            String codeSMMaBan = getAlphaNumericString();
            while (isPresent(codeSMMaBan)) {
                codeSMMaBan = getAlphaNumericString();
            }
            byte[] res = toByteArray(montant);
            TerminalEchange te = terminalEchangeRepository.findById(idTe).orElse(null);

            System.out.println("TES : " + te.getId());

            if (te == null) {
                return null;
            }

            SupportMarchandEnchage supportMarchandEnchage = supportMarchandEchangeRepository.findById(idSm).get();

            System.out.println("SM : " + supportMarchandEnchage.getId());

            if (supportMarchandEnchage == null) {
                return null;
            }

            DetailSMEnchange detailSMEnchange = new DetailSMEnchange();
            detailSMEnchange.setTerminalEchange(te);
            detailSMEnchange.setCoutUnitaire(montant);
            detailSMEnchange.setSourceEnree(montant);
            detailSMEnchange.setSourcceSortie(-montant);
            detailSMEnchange.setFondsEntre(montant);
            detailSMEnchange.setFondsFondDisponible(montant);
            detailSMEnchange.setQuantite(1);
            detailSMEnchange.setRefer(refer);
            detailSMEnchange.setSupportMarchandEnchage(supportMarchandEnchage);
            detailSMEnchange.setCodeSM(codeSMMaBan);
            detailSMEnchange.setCodeBar(String.valueOf(res));
            detailSMEnchange.setTotal(montant);
            return detailSMEchangeRepository.save(detailSMEnchange);
        } catch (Exception e) {
            System.out.println("Somethings is wrong");
            return null;
        }

    }

    public Ordre createOPi(Long idSm, Long idTe, Long idDetailSM, Double montant) throws IOException, WriterException {

        String codeSMMaBan = getAlphaNumericString();
        while (isPresent(codeSMMaBan)) {
            codeSMMaBan = getAlphaNumericString();
        }

        byte[] res = toByteArray(montant);

        TerminalEchange t = terminalEchangeRepository.findById(idTe).orElse(null);

        SupportMarchandEnchage sm = supportMarchandEchangeRepository.findById(idSm).orElse(null);

        System.out.println("Id SM : " + sm.getId());

        System.out.println("Libelle SM : " + sm.getLibelle());

        TypeOrdre typeOrdre = typeOrdreRepository.getTypeOrdreByLibelle(sm.getLibelle());

        System.out.println("Id Type ordre : " + typeOrdre.getId());

        if (typeOrdre == null) {
            return null;
        }

        // DecimalFormat formatterP = new DecimalFormat("0000000000");

        Ordre o = new Ordre();

        o.setCodeOPI(codeSMMaBan);
        o.setMontantOPI(montant);
        o.setDateEmission(new Date());
        o.setDatePrelevementOPI(new Date());
        o.setCodeBar(String.valueOf(res));
        o.setTerminalEchange(t);
        o.setTypeOrdre(typeOrdre);
        o.setSourceEnree(montant);
        o.setSourcceSortie(-montant);
        o.setFondsEntre(montant);
        o.setFondsSortie(-montant);
        o.setFondsFondDisponible(montant);
        o.setRefer(idDetailSM);
        o.setSupportMarchandEnchage(sm);

        Ordre or = ordreRepository.save(o);
        String code = or.getId() + "";
        int numero = 10 - code.length();

        for (int i = 0; i < numero; i++) {
            code = "0" + code;
        }

        or.setNumeroOPI(code);

        return ordreRepository.save(or);
    }

    @Override
    public DetailSMEnchange mutationPourAchatFifoAdmin(Long idTeAcheteur, Long idTeVendeur, Double montant) throws IOException {

        DetailSMEnchange dsme = null;

        DetailSMEnchange dt = null;

        DetailSMEnchange dBLGBpV = null;

        // Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

        SupportMarchandEnchage sm = supportMarchandEchangeRepository.findById(SupportMarchandConstant.QsupportMarchandBan).orElse(null);

        if (sm == null) {
            return null;
        }

        Double smTotal = detailSMEchangeRepository.sommeTotalSm(sm.getId(), idTeAcheteur);

        // boolean resultMontantSm = this.checkSmTotal(idTeAcheteur, sm.getId(),
        // montant);

        if (smTotal > 0 && montant <= smTotal) {

            DetailSMEnchange ds = mutationFondDisponible(sm.getId(), idTeAcheteur, montant);

            // dsme = this.create(idTeAcheteur, montant, sm.getId(), ds.getId());
            //
            // DetailSMEnchange detailSMEnchange =
            // detailSMEnchangeRepository.findById(dsme.getId()).orElse(null);

            if (ds != null) {
                dt = this.createFondSortie(idTeAcheteur, ds.getId(), montant);

                // Creation de BCnr
                DetailSMEnchange dBCnr = this.create(idTeAcheteur, dt.getFondsEntre(),
                        SupportMarchandConstant.QsupportMarchandBCnr, dt.getId());

                // Creation de Fonds de sortie de BCnr
                DetailSMEnchange dBCnrFond = this.createFondSortie(idTeAcheteur, dBCnr.getId(), dBCnr.getFondsEntre());

                // Montant BCnrPRK

                Double montantPRM = terminalEchangeService.bcnrPrk(dBCnrFond.getFondsEntre());

                Double montantRenitialiserBCI = 0.0;
                montantRenitialiserBCI = (montantPRM / 2187.5) * 70000;

                DetailSMEnchange dBCnrPRk = this.create(idTeAcheteur, montantRenitialiserBCI,
                        SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5, dBCnrFond.getId());

                // Creation de Fonds de sortie de BCnr PRK
                DetailSMEnchange dBCnrPRKFonds = this.createFondSortie(idTeAcheteur, dBCnrPRk.getId(),
                        dBCnrPRk.getFondsEntre());

                // Creation de BLGCp Vendeur
                dBLGBpV = this.create(idTeVendeur, montantRenitialiserBCI,
                        SupportMarchandConstant.supportMarchandBLGCsc, dBCnrFond.getId());
            }

        } else {
            return null;
        }
        return dBLGBpV;
    }
}
