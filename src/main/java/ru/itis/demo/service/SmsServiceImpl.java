package ru.itis.demo.service;

//import com.mashape.unirest.http.Unirest;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;

//@Service
//public class SmsServiceImpl implements SmsService {

//    @Value("${sms.aero.email}")
//    private String smsAeroEmail;
//
//    @Value("${sms.aero.api-key}")
//    private String smsAeroApiKey;
//
//    @Value("${sms.aero.from}")
//    private String smsAeroFrom;
//
//    @Value("${sms.aero.url}")
//    private String smsAeroUrl;
//
//    private String getUrl(String text,String number){
//        String url = smsAeroUrl
//                + number
//                +"&text=" + text
//                + "&sign=" + smsAeroFrom
//                + "&channel=DIRECT";
//        url = url.replaceAll(" ", "%20");
//        return url;
//    }
//
//    @SneakyThrows
//    @Override
//    public String sendSms(String text, String number) {
//        return Unirest.get(getUrl(text, number)).basicAuth(smsAeroEmail, smsAeroApiKey).asJson().toString();
//    }
//}
