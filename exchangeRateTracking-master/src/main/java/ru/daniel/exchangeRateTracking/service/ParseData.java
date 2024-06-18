package ru.daniel.exchangeRateTracking.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.daniel.exchangeRateTracking.model.CroneDB;
import ru.daniel.exchangeRateTracking.model.ExchangeDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
@Service
public class ParseData {

    @Autowired
    ExchangeService exchangeService;

    @Autowired
    CroneService croneService;

    @Scheduled(fixedDelay = 36000000)
    public String parseCurrentData() throws IOException {
        Date current = new Date();
        System.out.println("Current date: " + current);
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        String dayOfMonthStr = String.valueOf(dayOfMonth);

        Document doc = Jsoup.connect("https://www.cnb.cz/en/financial_markets/foreign_exchange_market/exchange_rate_fixing/daily.txt?date="
                + dayOfMonthStr + "." + month + "." + year)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Element element = doc.body();
        int index = element.text().indexOf("Australia");
        String rezultLine = element.text().substring(index, element.text().length());
        rezultLine = rezultLine.trim().replace("|", " ");
        String[] words = rezultLine.split("\\W+");
        JSONArray mJSONArray = new JSONArray();

        for (int i = 0; i < words.length; i++){
            if (i == words.length - 1) break;
            if (words[i + 1].equals("krone") || words[i + 1].equals("krona")) {
                ExchangeDB exchangeDB = new ExchangeDB();
                exchangeDB.setDate(current.toString());
                exchangeDB.setCountry(words[i]);
                exchangeDB.setCurrency(words[i + 1]);
                exchangeDB.setAmount(words[i + 2]);
                exchangeDB.setCode(words[i + 3]);
                exchangeDB.setRate(words[i + 4]);
                exchangeService.save(exchangeDB);
                i = i + 4;
            } else if (i % 5 == 0 || i == 0){
                mJSONArray.put(new JSONObject().put(String.valueOf(i),
                        words[i] + ", "
                        + words[i + 1] + ", "
                        + words[i + 2] + ", "
                        + words[i + 3] + ", "
                        + words[i + 4]));
            }
        }

        return mJSONArray.toString();
    }

    public String anythingData(int start, int end) throws IOException{
        Document docStart = Jsoup.connect("https://ratestats.com/czech-koruna/"
                        + start + "/")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        Elements divStart = docStart.getElementsByClass("b-current-rate__value");

        Document docEnd = Jsoup.connect("https://ratestats.com/czech-koruna/"
                        + end + "/")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        Elements divEnd = docEnd.getElementsByClass("b-current-rate__value");

        CroneDB croneDB = new CroneDB();
        croneDB.setStartDate(String.valueOf(start));
        croneDB.setStartCrone(divStart.text());
        croneDB.setEndDate(String.valueOf(end));
        croneDB.setEndCrone(divEnd.text());
        croneService.save(croneDB);

        return "Среднегодовой курс ЦБ в " + start + " году: " + divStart + "\n"
        + "Среднегодовой курс ЦБ в " + end + " году: " + divEnd;
    }

    public String report(int year) throws IOException {
        Document doc = Jsoup.connect("https://www.cnb.cz/en/financial_markets/foreign_exchange_market/exchange_rate_fixing/year.txt?year="
                        + year)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        String rezultLine = doc.body().text().substring(223, doc.body().text().length());
        rezultLine = rezultLine.trim().replace("|", " ");
        String[] words = rezultLine.split(" ");

        ArrayList<Double> first = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            double str1 = Double.parseDouble(words[i]);
            first.add(str1);
            i = i + 33;
        }
        Double max = first.stream()
                .max(Comparator.naturalOrder())
                .get();
        Double min = first.stream()
                .min(Comparator.naturalOrder())
                .get();

        double sum = 0;
        for (int j = 0; j < first.size(); j++) {
            sum += first.get(j);
        }
        Double middle = sum / first.size();

        JSONObject json = new JSONObject();
        json.put("Max", max);
        json.put("Min", min);
        json.put("Middle", middle);
        return json.toString();
    }


}