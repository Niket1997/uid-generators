package org.niket.services;

import org.niket.interfaces.TicketRepository;
import org.niket.interfaces.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public int getUID() throws Exception {
        return ticketRepository.getUID();
    }
}
