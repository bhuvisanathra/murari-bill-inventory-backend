package com.billapp.billapp.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import com.billapp.billapp.dao.*;
import com.billapp.billapp.dto.*;
import com.billapp.billapp.entities.*;

import jakarta.persistence.*;
import jakarta.transaction.*;


@Service
public class invoiceServiceImp implements invoiceServices{

    @Autowired
    clientDetailsDao bDao;

    @Autowired
    invoiceDetailsDao iDao;


    @Autowired
    invoiceListDao invoiceListDao;

    public invoiceServiceImp(){
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
public List<InvoiceDetailsDTO> getInvoiceDetails() {
    String jpql = "SELECT c, id, il FROM clientDetails c LEFT JOIN c.invoiceDetails id LEFT JOIN c.invoices il ON c.id = il.clientDetails.id";
    List<Object[]> resultList = entityManager.createQuery(jpql).getResultList();

    Map<Integer, InvoiceDetailsDTO> invoiceDetailsMap = new HashMap<>();

    for (Object[] result : resultList) {
        clientDetails clientDetails = (clientDetails) result[0];    
        invoiceDetails invoiceDetails = (invoiceDetails) result[1];
        invoiceList invoiceListItem = (invoiceList) result[2]; // Retrieve the individual invoiceList object
        
        if (!invoiceDetailsMap.containsKey(clientDetails.getId())) {
            // If the client ID is not in the map, create a new InvoiceDetailsDTO object
            InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
            dto.setCd(clientDetails);
            dto.setId(invoiceDetails);
            dto.setIl(new ArrayList<>());
            invoiceDetailsMap.put(clientDetails.getId(), dto);
        }
        
        // Add the invoiceListItem to the list in the corresponding InvoiceDetailsDTO object
        invoiceDetailsMap.get(clientDetails.getId()).getIl().add(invoiceListItem);
    }
    
    // Return the values of the map, which contain the aggregated data
    return new ArrayList<>(invoiceDetailsMap.values());
}



@Override
public InvoiceDetailsDTO getInvoiceDetail(int invoiceId) {
    String jpql = "SELECT c, id, il FROM clientDetails c LEFT JOIN FETCH c.invoiceDetails id LEFT JOIN FETCH c.invoices il WHERE c.id = :invoiceId";
    List<Object[]> resultList = entityManager.createQuery(jpql)
                                            .setParameter("invoiceId", invoiceId)
                                            .getResultList();
    if (resultList.isEmpty()) {
        return null;
    }
    Map<Integer, InvoiceDetailsDTO> invoiceDetailsMap = new HashMap<>();
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
    public clientDetails addInvoiceDetail(clientDetails InvoiceDetail) {
        bDao.save(InvoiceDetail);
        return InvoiceDetail;

    }

    @Override
    public clientDetails updateInvoiceDetail(clientDetails updatedInvoiceDetail) {
        bDao.save(updatedInvoiceDetail);
        return updatedInvoiceDetail;
    }

    @Override
    public void deleteInvoiceDetail(int invoiceId) {

        Optional<clientDetails> o = bDao.findById(invoiceId);
        bDao.delete(o.get());

    }

    @Override
    @Transactional
    public void saveInvoiceDetails(clientDetails clientDetails, invoiceDetails invoiceDetails, List<invoiceList> invoiceList) {
        if (clientDetails != null && clientDetails.getId() == 0) {
            bDao.save(clientDetails);
        }
        
        if (invoiceDetails != null) {
            invoiceDetails.setClientDetails(clientDetails);
            iDao.save(invoiceDetails); 
        }

        if (invoiceList != null && !invoiceList.isEmpty()) {
            for (invoiceList item : invoiceList) {
                item.setClientDetails(clientDetails);
            }
            invoiceListDao.saveAll(invoiceList);
        }
    }

}
