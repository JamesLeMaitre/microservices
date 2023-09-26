package com.esmc.gestionAvr.utils;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.entities.Tour;
import com.esmc.gestionAvr.feign.TeClient;
import com.esmc.gestionAvr.repositories.SettingsRepository;
import com.esmc.gestionAvr.repositories.TourRepository;
import com.esmc.gestionAvr.utils.enums.KsuType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JavaUtils {

    private final TourRepository tourRepository;
    private final SettingsRepository settingsRepository;
    private final TeClient teRestClient;
    public static final String API_BASE_URL = "/api/v1/";

    public final static double MAX_TOUR_SELLER_BCI = 7000000.0;
    public final static double MAX_TOUR_BUYER_BAN = 385000000.0;
    public final static double CONVERT_BAN_TO_BCI = (3 * 4.5) / 100;
    private static final String ALPHANUMERIC = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String KEY = "56789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234";
    private static final String MINIMAL_VALUE = "PARAM_QUOTA_MINIMAL_ACHAT_PRODUIT_PREOPERATIONNEL_EN_LIGNE";


//    public static boolean isExistSeller(Tour tour, Fifo fifo) {
//        return tour.getSellers().
//                stream().
//                noneMatch(s -> s.getKsu().getId()
//                        .equals(fifo.getKsu().getId()));
//    }
public static boolean isExistSeller(Tour tour, Fifo fifo) {
    for (Fifo seller : tour.getSellers()) {
        if (seller.getKsu().getId().equals(fifo.getKsu().getId())) {
            return false;
        }
    }
    return true;
}

    public static boolean isExistBuyer(Tour tour, Fifo fifo) {
        System.out.println(fifo);
        return tour.getBuyers().stream()
                .noneMatch(s -> s.getKsu().getId()
                .equals(fifo.getKsu().getId()));
    }

    public static void checkTourAdd(Tour tour, Fifo fifo) {
        if (fifo.getKsuType() == KsuType.SELLER ) {
            tour.getSellers().add(fifo);
        } else if (fifo.getKsuType() == KsuType.BUYER ) {

            tour.getBuyers().add(fifo);
        }
    }


    public static <T> T convertToType(String obj, Class<T> t) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(obj, t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    public static List<Fifo> getFifoList(Collection<String> tour) {
        return tour.stream().map(s -> Base64.getDecoder().decode(s)).map(String::new).map(json -> convertToType(json, Fifo.class)).toList();
    }


    public Stream<Tour> findTour(String name) {
        return tourRepository.findByName(name).stream();
    }

    public static boolean isExistBuyerOrNot(Tour tour, Tour basket) {
        return tour.getBuyers().stream().findAny().isPresent() || basket.getBuyers().stream().findAny().isPresent();
    }

    public static String cutStringFromHyphen(String input) {
        return input.contains("/") ? input.substring(input.indexOf("/") + 1) : input;
    }


    public static String transformString(String input) {
        String uuidWithoutHyphen = cutStringFromHyphen(input).replace("-", "");
        StringBuilder result = new StringBuilder();
        IntStream.range(0, uuidWithoutHyphen.length()).filter(i -> i % 4 == 0).forEach(i -> result.append(uuidWithoutHyphen, i, i + 4).append("-"));
        return result.toString();
    }


    public static String decrypt(String input) {
        return input.chars().mapToObj(c -> (char) c).map(c -> (KEY.indexOf(c) != -1) ? ALPHANUMERIC.charAt(KEY.indexOf(c)) : c).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

//    public Optional<Double> getValue() {
//        return settingsRepository.findByCode(MINIMAL_VALUE).map(s->Double.parseDouble(s.getCode()));
//    }
//
//    public Optional<Double> getValue() {
//        return settingsRepository.findByCode(MINIMAL_VALUE)
//                .map(setting -> Double.parseDouble(setting.getValue()))
//                .orElseThrow(() -> new IllegalArgumentException("Code not found: " + MINIMAL_VALUE)).describeConstable();
//    }
    public Optional<Double> getValue() {
        return Optional.ofNullable(settingsRepository.findByCode(MINIMAL_VALUE))
                .map(setting -> Double.parseDouble(setting.getValue()))
                .orElseThrow(() -> new IllegalArgumentException("Code not found: " + MINIMAL_VALUE)).describeConstable();
    }


//    public Optional<Ksu> getBuyerKsu(AchatProduitInput data){
//        Long idTe = teRestClient.getTeByDetailAgr(data.getIdDetailAgr()).getId();
////        Long idTe = te.getId();
//        DetailsAgr d = detailAgrClient.getDetailById(id);
//        Long idKsu = d.getKsu();
//        com.esmc.gestionAvr.entities.Ksu ksu = ksuRepository.findKsuById(idKsu);
//    }



}
