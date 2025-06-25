package com.org.TMS.service;

import com.org.TMS.model.Bus;
import java.util.List;

public interface BusService {
    List<Bus> getAllBuses();
    Bus getBusByNo(String busNo);
    Bus saveBus(Bus bus);
    void deleteBus(String busNo);
}
