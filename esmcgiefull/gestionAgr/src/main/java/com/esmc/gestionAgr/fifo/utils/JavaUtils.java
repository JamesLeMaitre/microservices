package com.esmc.gestionAgr.fifo.utils;


import com.esmc.gestionAgr.fifo.entities.Fifo;
import com.esmc.gestionAgr.fifo.entities.Tour;
import com.esmc.gestionAgr.fifo.repositories.TourRepository;
import com.esmc.gestionAgr.fifo.services.SettingsService;
import com.esmc.gestionAgr.fifo.utils.enums.KsuType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JavaUtils {

    //    public final static double MAX_TOUR_SELLER_BCI = 7000000.0;
//    public final static double MAX_TOUR_BUYER_BAN = 385000000.0;
    private static final String ALPHANUMERIC = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String KEY = "56789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234";
    public static final int parameter_date = 3;
    private final TourRepository tourRepository;
    private final SettingsService settingsService;

    public static boolean isExistSeller(Tour tour, Fifo fifo) {
        return tour.getSellers().stream().noneMatch(s -> s.getKsu().getCodeKsu().equals(fifo.getKsu().getCodeKsu()));
    }

    public static boolean isExistBuyer(Tour tour, Fifo fifo) {
        return tour.getBuyers().stream().noneMatch(s -> s.getKsu().getCodeKsu().equals(fifo.getKsu().getCodeKsu()));
    }

    public static void checkTourAdd(Tour tour, Fifo fifo) {
        if (fifo.getKsuType() == KsuType.SELLER && isExistSeller(tour, fifo)) {
            tour.getSellers().add(fifo);
        } else if (fifo.getKsuType() == KsuType.BUYER && isExistBuyer(tour, fifo)) {
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

    public Stream<Tour> findTour(String name) {
        return tourRepository.findByName(name).stream();
    }


}
