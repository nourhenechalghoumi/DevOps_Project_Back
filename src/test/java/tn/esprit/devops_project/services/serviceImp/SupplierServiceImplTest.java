package tn.esprit.devops_project.services.serviceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplierServiceImplTest {
    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        // Add some test suppliers to the list
        suppliers.add(new Supplier());
        when(supplierRepository.findAll()).thenReturn(suppliers);
        List<Supplier> retrievedSuppliers = supplierService.retrieveAllSuppliers();
        assertEquals(1, retrievedSuppliers.size()); // Check if the list contains one supplier
    }

    @Test
    void testAddSupplier() {
        Supplier supplier = new Supplier();
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        Supplier addedSupplier = supplierService.addSupplier(supplier);
        assertEquals(supplier, addedSupplier);
    }

    @Test
    void testUpdateSupplier() {
        Supplier supplier = new Supplier();
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        Supplier updatedSupplier = supplierService.updateSupplier(supplier);
        assertEquals(supplier, updatedSupplier);
    }

    @Test
    void testDeleteSupplier() {
        Long supplierId = 1L;
        doNothing().when(supplierRepository).deleteById(supplierId);
        supplierService.deleteSupplier(supplierId);
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Test
    void testRetrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(supplierId)).thenReturn(java.util.Optional.of(supplier));
        Supplier retrievedSupplier = supplierService.retrieveSupplier(supplierId);
        assertEquals(supplier, retrievedSupplier);
    }
}
