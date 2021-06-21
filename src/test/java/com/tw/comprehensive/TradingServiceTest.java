package com.tw.comprehensive;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
class TradingServiceTest {
    @Test
    void should_logNewTrade_when_call_createTrade() {
        AuditService spyAuditService = spy(AuditService.class);
        TradingService tradingService = new TradingService(new TradeRepository(), spyAuditService);
        Trade mockTrade = mock(Trade.class);
        tradingService.createTrade(mockTrade);
        verify(spyAuditService, times(1)).logNewTrade(mockTrade);
    }
    @Test
    void should_return_repository_result_when_call_findTrade_given_id_1L() {
        TradeRepository tradeRepository = mock(TradeRepository.class);
        TradingService tradingService = new TradingService(tradeRepository, new AuditService());
        Trade expected = mock(Trade.class);
        when(tradeRepository.findById(1L)).thenReturn(expected);
        Trade result = tradingService.findTrade(1L);

        assertEquals(expected, result);
    }

    @Test
    void should_return_repository_result_when_call_createTrade_given_a_trade() {
        TradeRepository stubTradeRepository = mock(TradeRepository.class);
        TradingService tradingService = new TradingService(stubTradeRepository, new AuditService());
        Trade dummyTrade = mock(Trade.class);
        long expected = 1L;
        when(stubTradeRepository.createTrade(dummyTrade)).thenReturn(expected);

        Long result = tradingService.createTrade(dummyTrade);

        verify(stubTradeRepository, times(1)).createTrade(dummyTrade);
        assertEquals(expected, result);
    }
}