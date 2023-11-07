package tn.esprit.devops_project.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.query.Param;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

public class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;
    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice());
        when(invoiceRepository.findAll()).thenReturn(invoices);
        List<Invoice> retrievedInvoices = invoiceService.retrieveAllInvoices();
        assertEquals(1, retrievedInvoices.size());
    }

    @Test
    void testCancelInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setArchived(false);
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        invoiceService.cancelInvoice(invoiceId);
        verify(invoiceRepository, times(1)).save(invoice);
        assertTrue(invoice.getArchived());
    }

    @Test
    void testRetrieveInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        Invoice retrievedInvoice = invoiceService.retrieveInvoice(invoiceId);
        assertEquals(invoice, retrievedInvoice);
    }



////////////////////////////////////////////////////////////////
    @Test
    void testGetTotalAmountInvoiceBetweenDates() {
        Date startDate = new Date();
        Date endDate = new Date();
        float totalAmount = 100.0f;
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(totalAmount);
        float result = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);
        assertEquals(totalAmount, result);
    }


}
