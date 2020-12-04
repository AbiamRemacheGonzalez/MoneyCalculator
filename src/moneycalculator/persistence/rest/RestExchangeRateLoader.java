package moneycalculator.persistence.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistence.ExchangeRateLoader;

public class RestExchangeRateLoader implements ExchangeRateLoader{

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            double read = read(from.getCode(),to.getCode());
            return new ExchangeRate(from, to, read(from.getCode(),to.getCode()));
        } catch (IOException ex) {
            return null;
        }
    }

    private double read(String from, String to) throws IOException {
        String line = read(new URL("https://free.currconv.com/api/v7/convert?apiKey=do-not-use-this-key&q=" +from +"_" + to +"&compact=y"));
        return Double.parseDouble( line.substring(line.indexOf(to)+12, line.indexOf("}")));
        
    }

    private String read(URL url) throws IOException {
        InputStream is = url.openStream();
        byte[] buffer =new byte[1024];
        int length = is.read(buffer);
        return new String(buffer,0,length);
    }
    
}
