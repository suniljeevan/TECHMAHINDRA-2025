package com.org.TMS.service;

import com.org.TMS.model.Bus;
import com.org.TMS.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {
    private final BusRepository repo;

    @Autowired
    public BusServiceImpl(BusRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Bus> getAllBuses() {
        return repo.findAll();
    }

    @Override
    public Bus getBusByNo(String busNo) {
        return repo.findById(busNo).orElse(null);
    }

    @Override
    public Bus saveBus(Bus bus) {
        return repo.save(bus);
    }

    @Override
    public void deleteBus(String busNo) {
        repo.deleteById(busNo);
    }
}
