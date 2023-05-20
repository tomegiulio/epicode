package com.w6d5.w6d5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w6d5.w6d5.enumerated.State;
import com.w6d5.w6d5.model.Device;
import com.w6d5.w6d5.repository.DeviceRepository;

@Service
public class DeviceService {
@Autowired
DeviceRepository dr;

public Device save(Device d) {
	return dr.save(d);
}
public Optional<Device> getById(int id) {
	return dr.findById(id);
	
}
public List<Device> getall() {
	return dr.findAll();
	
}

public Device update(int id,Device devic) throws Exception {
	Optional<Device> deviceResult = dr.findById(id);
	if (deviceResult.isPresent()) {
		Device device = deviceResult.get();
		device.setType(devic.getType());
		device.setState(devic.getState());
		dr.save(device);
		return device;
	}else {
		throw new Exception("Elemento non aggiornato");
	}
	}
public Device repair(int id) throws Exception {
	Optional<Device> deviceResult = dr.findById(id);
	if (deviceResult.isPresent()) {
		Device device = deviceResult.get();
		device.setState(State.MAINTENANCE);
		dr.save(device);
		return device;
	}else {
		throw new Exception("Elemento non aggiornato");
	}
	}
public Device destroy(int id) throws Exception {
	Optional<Device> deviceResult = dr.findById(id);
	if (deviceResult.isPresent()) {
		Device device = deviceResult.get();
		device.setState(State.DISCONTINUED);
		dr.save(device);
		return device;
	}else {
		throw new Exception("Elemento non aggiornato");
	}
	}

public void delete(int id) {
	dr.deleteById(id);
}

}
