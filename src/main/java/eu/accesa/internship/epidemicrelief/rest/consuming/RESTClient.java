//package eu.accesa.internship.epidemicrelief.rest.consuming;
//
//import eu.accesa.internship.epidemicrelief.model.Product;
//import eu.accesa.internship.epidemicrelief.soap.consuming.SOAPClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//public class RESTClient {
//    private static final Logger log = LoggerFactory.getLogger(RESTClient.class);
//
//    public ResponseEntity<List<Product>> getProducts(List<String> uuids) {
//        log.info("Get Rest request for: " + uuids);
//        RestTemplate restTemplate = new RestTemplate();
//        String fooResourceUrl = "http://localhost:8082/products?uuids=";
//        String products = createURL(uuids);
//        log.info("Request for url: " + fooResourceUrl + products);
//        return restTemplate.exchange(fooResourceUrl + products, HttpMethod.GET,
//                null, new ParameterizedTypeReference<>() {
//                });
//    }
//
//    private String createURL(List<String> name) {
//        StringBuilder names = new StringBuilder();
//        for (String s : name) {
//            names.append(s);
//            names.append(",");
//        }
//        if (names.length() > 0) {
//            names.deleteCharAt(names.length() - 1);
//        }
//        return names.toString();
//    }
//}
