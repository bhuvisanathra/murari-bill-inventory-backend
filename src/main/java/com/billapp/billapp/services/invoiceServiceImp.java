package com.billapp.billapp.services;

    import java.sql.Date;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestBody;

import com.billapp.billapp.dao.*;
import com.billapp.billapp.dto.*;
import com.billapp.billapp.entities.*;

import jakarta.persistence.*;
import jakarta.transaction.*;

@Service
public class invoiceServiceImp implements invoiceServices {

    @Autowired
    clientDetailsDao bDao;

    @Autowired
    invoiceDetailsDao iDao;

    @Autowired
    invoiceListDao invoiceListDao;

    public invoiceServiceImp() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<InvoiceDetailsDTO> getInvoiceDetails() {
        String jpql = "SELECT c, id, il FROM clientDetails c LEFT JOIN c.invoiceDetails id LEFT JOIN c.invoices il ON c.id = il.clientDetails.id";
        List<Object[]> resultList = entityManager.createQuery(jpql).getResultList();

        Map<Long, InvoiceDetailsDTO> invoiceDetailsMap = new HashMap<>();

        for (Object[] result : resultList) {
            clientDetails clientDetails = (clientDetails) result[0];
            invoiceDetails invoiceDetails = (invoiceDetails) result[1];
            invoiceList invoiceListItem = (invoiceList) result[2];
            if (!invoiceDetailsMap.containsKey(clientDetails.getId())) {
                InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
                dto.setCd(clientDetails);
                dto.setId(invoiceDetails);
                dto.setIl(new ArrayList<>());
                invoiceDetailsMap.put(clientDetails.getId(), dto);
            }
            invoiceDetailsMap.get(clientDetails.getId()).getIl().add(invoiceListItem);
        }
        return new ArrayList<>(invoiceDetailsMap.values());
    }

    @Override
    @SuppressWarnings("unchecked")
    public InvoiceDetailsDTO getInvoiceDetail(int invoiceId) {
        String jpql = "SELECT c, id, il FROM clientDetails c LEFT JOIN FETCH c.invoiceDetails id LEFT JOIN FETCH c.invoices il WHERE c.id = :invoiceId";
        List<Object[]> resultList = entityManager.createQuery(jpql)
                .setParameter("invoiceId", invoiceId)
                .getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        Map<Long, InvoiceDetailsDTO> invoiceDetailsMap = new HashMap<>();
        for (Object[] result : resultList) {
            clientDetails clientDetails = (clientDetails) result[0];
            invoiceDetails invoiceDetails = (invoiceDetails) result[1];
            invoiceList invoiceListItem = (invoiceList) result[2];
            System.out.println(invoiceListItem);
            if (!invoiceDetailsMap.containsKey(clientDetails.getId())) {
                InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
                dto.setCd(clientDetails);
                dto.setId(invoiceDetails);
                dto.setIl(new ArrayList<>());
                invoiceDetailsMap.put(clientDetails.getId(), dto);
            }
            invoiceDetailsMap.get(clientDetails.getId()).getIl().add(invoiceListItem);
        }
        return invoiceDetailsMap.values().iterator().next();
    }

    @Override
    public List<InvoiceDetailsDTO> getBillAnalysis() {
        String jpql = "SELECT c, id, il FROM clientDetails c "
                + "LEFT JOIN c.invoiceDetails id "
                + "LEFT JOIN c.invoices il "
                + "WHERE id.grandTotal IN "
                + "(SELECT MAX(id.grandTotal) FROM invoiceDetails id GROUP BY id.clientDetails) "
                + "ORDER BY id.grandTotal DESC"; // Adding ORDER BY clause to sort by grandTotal in descending order
        List<Object[]> resultList = entityManager.createQuery(jpql).getResultList();

        Map<Long, InvoiceDetailsDTO> invoiceDetailsMap = new HashMap<>();

        for (Object[] result : resultList) {
            clientDetails clientDetails = (clientDetails) result[0];
            invoiceDetails invoiceDetails = (invoiceDetails) result[1];
            invoiceList invoiceListItem = (invoiceList) result[2];
            if (!invoiceDetailsMap.containsKey(clientDetails.getId())) {
                InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
                dto.setCd(clientDetails);
                dto.setId(invoiceDetails);
                dto.setIl(new ArrayList<>());
                invoiceDetailsMap.put(clientDetails.getId(), dto);
            }
            invoiceDetailsMap.get(clientDetails.getId()).getIl().add(invoiceListItem);
        }
        return new ArrayList<>(invoiceDetailsMap.values());
    }



    @Override
    public List<InvoiceDetailsDTO> getProductAnalysis() {
        String jpql = "SELECT il.productDetail, SUM(il.value), SUM(il.kgOrGram) FROM invoiceList il GROUP BY il.productDetail ORDER BY il.productDetail DESC";
        Query query = entityManager.createQuery(jpql);
        List<Object[]> resultList = query.getResultList();

        List<InvoiceDetailsDTO> productAnalysisList = new ArrayList<>();

        for (Object[] result : resultList) {
            String productDetail = (String) result[0];
            Double totalValue = (Double) result[1]; // Assuming value is of type Double
            Double totalQuantity = (Double) result[2]; // Assuming kgOrGram is of type Double

            // Create a new InvoiceDetailsDTO object
            InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
            dto.setProductDetail(productDetail);
            // Set the total value and total quantity
            dto.setTotalValue(totalValue);
            dto.setTotalQuantity(totalQuantity);

            // Add the DTO to the list
            productAnalysisList.add(dto);
        }

        return productAnalysisList;
    }

    @Override
    public List<InvoiceDetailsDTO> getSaleAnalysis() {
        String jpql = "SELECT COUNT(id.clientDetails), id.paymentType, SUM(id.grandTotal) FROM invoiceDetails id  GROUP BY id.paymentType";
        Query query = entityManager.createQuery(jpql);
        List<Object[]> resultList = query.getResultList();

        List<InvoiceDetailsDTO> saleAnalysisList = new ArrayList<>();

        for (Object[] result : resultList) {
            Long clientCount = (Long) result[0];
            String paymentType = (String) result[1];
            Double totalGrandTotal = (Double) result[2]; // Assuming grand_total is of type Double

            // Create a new InvoiceDetailsDTO object
            InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
            dto.setClientCount(clientCount);
            dto.setPaymentType(paymentType);
            dto.setTotalGrandTotal(totalGrandTotal);

            // Add the DTO to the list
            saleAnalysisList.add(dto);
        }

        return saleAnalysisList;
    }



    @Override
    public clientDetails addInvoiceDetail(clientDetails InvoiceDetail) {
        bDao.save(InvoiceDetail);
        return InvoiceDetail;

    }

    @Override
    @SuppressWarnings({ "unchecked", "unused" })
    public Long updateInvoiceDetail(Long clientId, Map<String, Object> payload) {
        Optional<clientDetails> clientDetailsOptional = bDao.findById(clientId);
        Optional<invoiceDetails> invoiceDetailsOptional = iDao.findById(clientId);
        System.out.println("\n\n\n" + payload.get("invoiceDetails.id") + "\n\n\n");
        if (clientDetailsOptional.isPresent() || invoiceDetailsOptional.isPresent()) {
            clientDetails clientDetails = clientDetailsOptional.get();
            invoiceDetails invoiceDetails = invoiceDetailsOptional.get();
            if (payload.containsKey("clientDetails")) {
                Map<String, Object> clientDetailsMap = (Map<String, Object>) payload.get("clientDetails");
                clientDetails.setClientName((String) clientDetailsMap.get("clientName"));
                clientDetails.setClientAddress((String) clientDetailsMap.get("clientAddress"));
                clientDetails.setClientGST((String) clientDetailsMap.get("clientGST"));
                clientDetails.setClientPOS((String) clientDetailsMap.get("clientPOS"));
                clientDetails.setClientState((String) clientDetailsMap.get("clientState"));
                clientDetails.setClientStateCode((String) clientDetailsMap.get("clientStateCode"));
                clientDetails.setClientDate(Date.valueOf((String) clientDetailsMap.get("invoiceDate")));
                bDao.save(clientDetails);
            }
            if (payload.containsKey("invoiceDetails")) {
                Map<String, Object> invoiceDetailsMap = (Map<String, Object>) payload.get("invoiceDetails");
                invoiceDetails.setPaymentType((String) invoiceDetailsMap.get("paymentType"));
                invoiceDetails.setGrandTotal(invoiceDetailsMap.get("grandTotal") != null
                        ? getDoubleValue(invoiceDetailsMap.get("grandTotal"))
                        : null);
                invoiceDetails.setRoundOff(
                        invoiceDetailsMap.get("roundOff") != null ? getDoubleValue(invoiceDetailsMap.get("roundOff"))
                                : null);
                invoiceDetails.setShippingCharges(invoiceDetailsMap.get("shippingCharges") != null
                        ? getDoubleValue(invoiceDetailsMap.get("shippingCharges"))
                        : null);
                invoiceDetails.setTotalAfterDiscount(invoiceDetailsMap.get("totalAfterDiscount") != null
                        ? getDoubleValue(invoiceDetailsMap.get("totalAfterDiscount"))
                        : null);
                invoiceDetails.setTotalCgst(
                        invoiceDetailsMap.get("totalCgst") != null ? getDoubleValue(invoiceDetailsMap.get("totalCgst"))
                                : null);
                invoiceDetails.setTotalDiscount(invoiceDetailsMap.get("totalDiscount") != null
                        ? getDoubleValue(invoiceDetailsMap.get("totalDiscount"))
                        : null);
                invoiceDetails.setTotalSgst(
                        invoiceDetailsMap.get("totalSgst") != null ? getDoubleValue(invoiceDetailsMap.get("totalSgst"))
                                : null);
                invoiceDetails.setTotalValue(invoiceDetailsMap.get("totalValue") != null
                        ? getDoubleValue(invoiceDetailsMap.get("totalValue"))
                        : null);
                iDao.save(invoiceDetails);
            }

            if (payload.containsKey("invoiceList")) {
                List<Map<String, Object>> invoiceListMap = (List<Map<String, Object>>) payload.get("invoiceList");

                for (Map<String, Object> itemMap : invoiceListMap) {
                    Long srNo = ((Number) itemMap.get("srNo")).longValue();

                    if (srNo != null) {
                        Optional<invoiceList> invoiceOptional = invoiceListDao.findBySrNoAndClientDetailsId(srNo,
                                clientId);
                        if (invoiceOptional.isPresent()) {
                            invoiceList invoice = invoiceOptional.get();
                            invoice.setProductDetail((String) itemMap.get("productDetail"));
                            invoice.setKgOrGram(
                                    itemMap.get("kgOrGram") != null ? getDoubleValue(itemMap.get("kgOrGram")) : null);
                            invoice.setRate(itemMap.get("rate") != null ? getDoubleValue(itemMap.get("rate")) : null);
                            invoice.setValue(
                                    itemMap.get("value") != null ? getDoubleValue(itemMap.get("value")) : null);
                            invoice.setDiscount(
                                    itemMap.get("disc") != null ? getDoubleValue(itemMap.get("disc")) : null);
                            invoiceListDao.save(invoice);
                        } else {
                            invoiceList newInvoice = new invoiceList();
                            newInvoice.setSrNo(srNo.doubleValue());
                            newInvoice.setClientDetails(clientDetails);
                            newInvoice.setProductDetail((String) itemMap.get("productDetail"));
                            newInvoice.setKgOrGram(
                                    itemMap.get("kgOrGram") != null ? getDoubleValue(itemMap.get("kgOrGram")) : null);
                            newInvoice
                                    .setRate(itemMap.get("rate") != null ? getDoubleValue(itemMap.get("rate")) : null);
                            newInvoice.setValue(
                                    itemMap.get("value") != null ? getDoubleValue(itemMap.get("value")) : null);
                            newInvoice.setDiscount(
                                    itemMap.get("disc") != null ? getDoubleValue(itemMap.get("disc")) : null);
                            invoiceListDao.save(newInvoice);
                        }
                    } else {
                        System.out.println("srNo is null");
                    }
                }
            }

            return clientDetails.getId();
        } else

        {
            return null;
        }
    }

    private Double getDoubleValue(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        } else if (value instanceof Double) {
            return (Double) value;
        } else {
            return null;
        }
    }

    @Override
    public void deleteInvoiceDetail(Long invoiceId) {

        Optional<clientDetails> o = bDao.findById(invoiceId);
        bDao.delete(o.get());

    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public Map<String, Object> saveInvoiceDetails(@RequestBody Map<String, Object> payload) {
        Map<String, Object> clientDetailsMap = (Map<String, Object>) payload.get("clientDetails");
        Map<String, Object> invoiceDetailsMap = (Map<String, Object>) payload.get("invoiceDetails");
        List<Map<String, Object>> invoiceListMap = (List<Map<String, Object>>) payload.get("invoiceList");

        clientDetails clientDetails = new clientDetails(clientDetailsMap);
        invoiceDetails invoiceDetails = new invoiceDetails(invoiceDetailsMap);
        List<invoiceList> invoiceList = new ArrayList<>();

        if (invoiceListMap != null) {
            for (Map<String, Object> item : invoiceListMap) {
                invoiceList.add(new invoiceList(item));
            }
        }

        if (clientDetails != null && clientDetails.getId() == null) {
            bDao.save(clientDetails);
        }

        // Save invoice details and associate with client details
        if (invoiceDetails != null) {
            invoiceDetails.setClientDetails(clientDetails);
            iDao.save(invoiceDetails);
        }

        // Save invoice list and associate each item with client details
        if (invoiceList != null && !invoiceList.isEmpty()) {
            for (invoiceList item : invoiceList) {
                item.setClientDetails(clientDetails);
            }
            invoiceListDao.saveAll(invoiceList);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("invoiceIdAfterDB", clientDetails != null ? clientDetails.getId() : null);
        result.put("invoiceDetailsIdAfterDB", invoiceDetails != null ? invoiceDetails.getId() : null);
        return result;
    }

}
