//package eu.accesa.internship.epidemicrelief.service.impl;
//
//import eu.accesa.internship.epidemicrelief.model.Product;
//import eu.accesa.internship.epidemicrelief.repository.ProductRepository;
//import eu.accesa.internship.epidemicrelief.rest.consuming.RESTClient;
//import eu.accesa.internship.epidemicrelief.service.JobService;
//import eu.accesa.internship.epidemicrelief.soap.consuming.SOAPClient;
//import eu.accesa.internship.wsdl.GetProductResponse;
//import eu.accesa.internship.wsdl.ListOfUuid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//@Configuration
//@EnableScheduling
//public class DefaultJobService implements JobService {
//    @Value("${batch.size}")
//    private int bachSize;
//    Logger logger = LoggerFactory.getLogger(DefaultJobService.class);
//    private final SOAPClient client;
//    private final RESTClient restClient = new RESTClient();
//    private final ProductRepository productRepository;
//    private int offset = 0;
//    @Value("${choose.api.type}")
//    private String chooseApi;
//
//    @Autowired
//    public DefaultJobService(SOAPClient client, ProductRepository productRepository) {
//        this.client = client;
//        this.productRepository = productRepository;
//    }
//
//    //@Scheduled(cron = "0 0 0 * * *", zone = "Europe/Romania")
//    //@Scheduled(fixedRateString = "PT5S", zone = "Europe/Romania")
//    @Override
//    @Scheduled(cron = "*/10 * * * * *", zone = "GMT+3")
//    //@CircuitBreaker(name = MAIN_SERVICE)
//    public void updateProduct() {
//        logger.info("Cronjob started");
//        if (chooseApi.equalsIgnoreCase("REST")) {
//            restUpdateProduct();
//        } else {
//            soapUpdateProduct();
//        }
//        logger.info("Cronjob finished");
//    }
//
//    @Override
//    public void restUpdateProduct() {
//        logger.info("REST cronjob");
//        List<Product> products = getNProducts(bachSize, offset);
//        offset += bachSize;
//        List<String> request = createRestRequest(products);
//        ResponseEntity<List<Product>> response = executeRequest(request);
//        try {
//            update(response);
//        } catch (Exception e) {
//            logger.error(String.valueOf(e), e);
//        }
//    }
//
//    private List<String> createRestRequest(List<Product> products) {
//        logger.info("Creating request for products: " + products.toString());
//        logger.info("Request created with: " + products);
//        List<String> productNames = new ArrayList<>();
//        for (Product p : products) {
//            productNames.add(p.getUuid());
//        }
//        return productNames;
//    }
//
//    private ResponseEntity<List<Product>> executeRequest(List<String> request) {
//        logger.info("Executing request for request: " + request.toString());
//        return restClient.getProducts(request);
//    }
//
//    //todo regandit
//    private void update(ResponseEntity<List<Product>> response) throws Exception {
//        List<Product> products = response.getBody();
//        if (products != null) {
//            for (Product p : products) {
//                Optional<Product> getProduct = productRepository.findProductByUuid(p.getUuid());
//                if (getProduct.isPresent()) {
//                    logger.info("Updating product's stock: name:" + p.getName() + " uuid:" + p.getUuid());
//                    logger.info("Old stock: " + getProduct.get().getStock() + " -> New stock: " + p.getStock());
//                    getProduct.get().setStock(p.getStock());
//                    productRepository.save(getProduct.get());
//                }
//            }
//        } else {
//            throw new Exception("Null pointer Exception occurred");
//        }
//    }
//
//    @Override
//    public void soapUpdateProduct() {
//        logger.info("SOAP cronjob");
//        List<Product> products = getNProducts(bachSize, offset);
//
//        offset += bachSize;
//        ListOfUuid listName = createRequest(products);
//        GetProductResponse response = executeRequest(listName);
//        update(response);
//    }
//
//
//    private void update(GetProductResponse response) {
//        List<eu.accesa.internship.wsdl.Product> products = response.getList().getProduct();
//
//        for (eu.accesa.internship.wsdl.Product p : products) {
//            Optional<Product> product = productRepository.findByUuid(p.getUuid());
//            if (product.isPresent()) {
//                logger.info("Updating product's stock: name:" + p.getName() + " uuid:" + p.getUuid());
//                logger.info("Old stock: " + product.get().getStock() + " -> New stock: " + p.getStock());
//                product.get().setStock(p.getStock());
//                productRepository.save(product.get());
//            }
//        }
//    }
//
//    private GetProductResponse executeRequest(ListOfUuid request) {
//        logger.info("Executing request: " + request.toString());
//        GetProductResponse response;
//        response = client.getProducts(request);
//        return response;
//    }
//
//    private ListOfUuid createRequest(List<Product> products) {
//        logger.info("Creating request for products: " + products.toString());
//        ListOfUuid listName = new ListOfUuid();
//
//        for (Product product : products) {
//            listName.getUuid().add(product.getUuid());
//        }
//        return listName;
//    }
//
//    private List<Product> getNProducts(int bachSize, int offset) {
//        return productRepository.getNProducts(bachSize, offset);
//    }
//}
