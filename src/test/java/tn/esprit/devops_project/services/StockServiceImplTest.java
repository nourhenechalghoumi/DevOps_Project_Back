package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Test
    void addStock() {
        Stock stockToAdd = new Stock();
        stockToAdd.setTitle("Title");

        Stock savedStock = new Stock();
        savedStock.setIdStock(1L);
        savedStock.setTitle("Title");

        // Mock the behavior of the repository when saving a stock
        when(stockRepository.save(stockToAdd)).thenReturn(savedStock);

        // Call the service method
        Stock addedStock = stockService.addStock(stockToAdd);

        // Verify the service behavior
        assertEquals(addedStock.getIdStock(), 1L);
        assertEquals(addedStock.getTitle(), "Title");
    }

    @Test
    void retrieveStock() {
        Stock stockToRetrieve = new Stock();
        stockToRetrieve.setIdStock(1L); // Set the ID
        stockToRetrieve.setTitle("stock 1");


        // Mock the behavior of the repository when retrieving a stock by ID
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stockToRetrieve));

        // Call the service method
        Stock retrievedStock = stockService.retrieveStock(1L);

        // Verify the service behavior
        assertEquals(retrievedStock.getIdStock(), 1L);
        assertEquals(retrievedStock.getTitle(), "stock 1");
    }

    @Test
    void retrieveAllStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("stock 1");

        // Mock the behavior of the repository when retrieving all stocks
        when(stockRepository.findAll()).thenReturn(Collections.singletonList(stock));

        // Call the service method
        List<Stock> allStocks = stockService.retrieveAllStock();

        // Verify the service behavior
        assertEquals(allStocks.size(), 1);
        assertEquals(allStocks.get(0).getIdStock(), 1L);
        assertEquals(allStocks.get(0).getTitle(), "stock 1");
    }
}
