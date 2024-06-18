package ru.daniel.exchangeRateTracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.daniel.exchangeRateTracking.service.ParseData;

import java.io.IOException;

@Controller
@RequestMapping("/exchange")
public class ExchangeRateTracingController {

    private ParseData parseExchangeData;

    @Autowired
    public ExchangeRateTracingController(ParseData parseExchangeData) {
        this.parseExchangeData = parseExchangeData;
    }

    @GetMapping("/index")
    @ResponseBody
    public String currentDate(Model model) throws IOException {
        return parseExchangeData.parseCurrentData();

    }

    @GetMapping("/{start}&{end}")
    @ResponseBody
    public String someDate(@PathVariable("start") int start,
                         @PathVariable("end") int end, Model model) throws IOException {
        return parseExchangeData.anythingData(start, end);
    }

    @GetMapping("/report/{year}")
    @ResponseBody
    public String croneReport(@PathVariable("year") int year, Model model) throws IOException {
        return parseExchangeData.report(year);
    }
}
